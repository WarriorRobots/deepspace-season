package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Constants;
import frc.robot.QuickAccessVars;
import frc.robot.commands.elevator.DefaultStabilizeElevator;

/**
 * Contains the winch motor used to raise the elevator, and the limit switch
 * used to detect its lowest point.
 */
public class ElevatorSubsystem extends Subsystem {

	public static final double CLICKS_PER_INCH = 512;

	private static final int WINCH_ID = 7;
	private static final int LIMIT_SWITCH_PORT = 4;

	/** Commanded position of the elevator */
	private double ELEVATOR_SETPOINT = 0; // default values will get overridden quickly, this just prevents null errors
	/** Does the elevator have a commanded position? */
	private boolean DOES_SETPOINT_EXIST = true;

	private WPI_TalonSRX winch;
	/** Magnetic "Hall effect" sensor. */
	private DigitalInput limitSwitch;

	/**
	 * Instantiates new subsystem; make ONLY ONE.
	 * <p>
	 * <code> public static final ElevatorSubsystem elevator = new
	 * ElevatorSubsystem();
	 */
	public ElevatorSubsystem() {
		winch = new WPI_TalonSRX(WINCH_ID);
		limitSwitch = new DigitalInput(LIMIT_SWITCH_PORT);
		winch.setInverted(QuickAccessVars.ELEVATOR_WINCH_INVERTED);
		winch.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.PID_ID, Constants.TIMEOUT_MS);
		winch.setSensorPhase(QuickAccessVars.ELEVATOR_ENCODER_INVERTED);
		winch.config_kP(Constants.PID_ID, QuickAccessVars.ELEVATOR_P, Constants.TIMEOUT_MS);
	}

	/**
	 * Runs in Robot.java every tick, checking the Hall effect sensor and
	 * resetting the encoder when it is triggered.
	 */
	public void resetEncoderWhenFloored() {
		if (isElevatorFloored()) {
			// don't modify ELEVATOR_SETPOINT because adjustElevatorLinear() and
			// stabilizeElevator() will handle it
			resetEncoder();
		}
	}

	/**
	 * Moves the elevator to the position specified.
	 * This has safeties built in to avoid crashing the elevator.
	 * @param inches From 0 (the elevator's bottom position) 
	 * 		  to a positive number (distance traveled on the inner carriage).
	 */
	public void moveElevatorTo(double inches) {
		enableSetpointMode();
		if (belowMinimum(inches)) {
			// because the elevator doesn't move past min/max, assume the setpoint is AT min/max
			ELEVATOR_SETPOINT = QuickAccessVars.ELEVATOR_MINIMUM_TARGET;
			winch.set(ControlMode.Position, toClicks(QuickAccessVars.ELEVATOR_MINIMUM_TARGET));
			System.out.println("Elevator moving to " + inches + ", cutting short to prevent crash!");
		} else if (aboveMaximum(inches)) {
			ELEVATOR_SETPOINT = QuickAccessVars.ELEVATOR_MAXIMUM_TARGET;
			winch.set(ControlMode.Position, toClicks(QuickAccessVars.ELEVATOR_MAXIMUM_TARGET));
			System.out.println("Elevator moving to " + inches + ", cutting short to prevent crash!");
		} else {
			ELEVATOR_SETPOINT = inches;
			winch.set(ControlMode.Position, toClicks(inches));
		}
	}

	/**
	* Drives the winch motor at a constant speed.
	* This has safeties built in to prevent crashing the elevator.
	* @param speed Percentage speed of the winch, from -1 (down) to 1 (up).
	*/
	public void adjustElevatorLinear(double speed) {
		// linear motion does NOT use setpoints
		disableSetpointMode();
		double pos = getElevatorPosition();
		if (belowMinimum(pos)) {
			if (speed > 0) {
				winch.set(speed);
			} else {
				winch.stopMotor();
				System.out.println("Elevator over-driving down, cutting short to prevent crash! " + pos + " " + speed);
			}
		} else if (aboveMaximum(pos)) {
			if (speed < 0) {
				winch.set(speed);
			} else {
				winch.stopMotor();
				System.out.println("Elevator over-driving up, cutting short to prevent crash! " + pos + " " + speed);
			}
		} else {
			winch.set(speed);
		}
	}

	/**
	 * Holds the elevator at the position specified,
	 * for use only in the StabilizeElevator command.
	 * @param inches Should always be positive.
	 */
	public void stabilizeElevator(double inches) {
		/* why no safety? When enabled the elevator should NOT move,
		 * no matter where it is. If safeties are implemented, the
		 * elevator (if at zero) will move to the minimum; that's not what we want.
		 * Best solution I've found is to create a method without safeties, and only
		 * use it for stabilization (aka non-movement) commands. */
		enableSetpointMode();
		ELEVATOR_SETPOINT = inches;
		winch.set(ControlMode.Position, toClicks(inches));
	}

	/**
	 * Shuts off the elevator winch motor.
	 */
	public void stopElevator() {
		// don't modify ELEVATOR_SETPOINT, this runs when commands end and it will wipe out stored data
		winch.stopMotor();
	}

	/**
	 * Returns the position of the elevator in inches relative to the bottom frame (NOT THE FLOOR)
	 */
	public double getElevatorPosition() {
		return toInches(winch.getSelectedSensorPosition());
	}

	/**
	 * Tells where the elevator was last commanded to go. 
	 * <p>Remembers this value from the commands referenced below.
	 * <p>Use {@link #doesSetpointExist()} to check if this is OK to use!
	 * @return The last commanded position of the elevator in inches from the bottom of the elevator.
	 * 
	 * @see #doesSetpointExist()
	 */
	public double getElevatorSetpoint() {
		return ELEVATOR_SETPOINT;
	}

	/**
	 * Returns whether the elevator is currently using setpoint logic.
	 * <p>If this returns false, do NOT run {@link #getElevatorSetpoint()}
	 * 
	 * @see #getElevatorSetpoint()
	 */
	public boolean doesSetpointExist() {
		return DOES_SETPOINT_EXIST;
	}

	/**
	 * Run this inside any method which uses setpoints.
	 * <p>Make sure ELEVATOR_SETPOINT is set to a number.
	 */
	private void enableSetpointMode() {
		DOES_SETPOINT_EXIST = true;
		// This is used as a safe backup height if someone forgets to set ELEVATOR_SETPOINT.
		// it is generally OK for the elevator to be at this position.
		ELEVATOR_SETPOINT = QuickAccessVars.LOCK_AND_RAISE_HEIGHT;
	}

	/**
	 * Run this inside any method which does NOT use setpoints.
	 * <p>ELEVATOR_SETPOINT is set inside this method, do not modify it.
	 */
	private void disableSetpointMode() {
		DOES_SETPOINT_EXIST = false;
		// This is used as a safe backup height just in case a safety doesn't work:
		// it is generally OK for the elevator to be at this position.
		ELEVATOR_SETPOINT = QuickAccessVars.LOCK_AND_RAISE_HEIGHT;
	}

	/**
	 * Zeroes out the elevator encoder.
	 */
	private void resetEncoder() {
		winch.setSelectedSensorPosition(0);
	}

	/**
	 * Returns true if the limit switch at the bottom of the elevator is being triggered.
	 */
	public boolean isElevatorFloored() {
		return !limitSwitch.get(); // flipped because sensor reads true if there is NO magnet
	}

	/**
	 * Returns true if the specified distance is below the lower bound of motion.
	 * <p>True is BAD. Use this in code to avoid crashing the elevator.
	 * @param inches Any distance measurement related to the elevator.
	 */
	public boolean belowMinimum(double inches) {
		return inches < QuickAccessVars.ELEVATOR_MINIMUM_TARGET;
	}

	/**
	 * Returns true if the specified distance is above the upper bound of motion.
	 * <p>True is BAD. Use this in code to avoid crashing the elevator.
	 * @param inches Any distance measurement related to the elevator.
	 */
	public boolean aboveMaximum(double inches) {
		return inches > QuickAccessVars.ELEVATOR_MAXIMUM_TARGET;
	}

	/**
	 * Converts encoder clicks to inches traveled (of the inner carriage).
	 * @param clicks Encoder clicks measured from the output axle.
	 */
	public double toInches(int clicks) {
		return clicks / CLICKS_PER_INCH;
	}

	/**
	 * Converts inches to encoder clicks.
	 * @param inches Inches traveled, measured from the inner carriage (NOT the chain).
	 */
	public int toClicks(double inches) {
		return (int) Math.round(inches * CLICKS_PER_INCH);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DefaultStabilizeElevator());
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		builder.addDoubleProperty("position", () -> getElevatorPosition(), null);
		builder.addDoubleProperty("clicks", () -> winch.getSelectedSensorPosition(), null);
		builder.addBooleanProperty("floored?", () -> isElevatorFloored(), null);
		builder.addDoubleProperty("speed", () -> winch.getMotorOutputPercent(), null);
		builder.addBooleanProperty("has setpoint?", () -> doesSetpointExist(), null);
		builder.addDoubleProperty("setpoint", () -> getElevatorSetpoint(), null);
	}
}
