package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Constants;
import frc.robot.QuickAccessVars;
import frc.robot.commands.climb.DefaultStabilizeClimb;

/**
 * Contains the motorized winch used to drive the climb mechanism downwards.
 */
public class ClimbSubsystem extends Subsystem {

	public static final double CLICKS_PER_INCH = 1024.0;

	private static final int WINCH_ID = 12;

	private WPI_TalonSRX winch;

	public ClimbSubsystem() {
		winch = new WPI_TalonSRX(WINCH_ID);
		winch.setInverted(QuickAccessVars.CLIMB_WINCH_INVERTED);
		winch.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.PID_ID, Constants.TIMEOUT_MS);
		winch.setSensorPhase(QuickAccessVars.CLIMB_ENCODER_INVERTED);
		winch.config_kP(Constants.PID_ID, QuickAccessVars.CLIMB_P, Constants.TIMEOUT_MS);
	}

	/**
	 * Moves the climb to the position specified.
	 * Has safety built in to avoid crashing the mechanism.
	 * @param inches From 0 (the uppermost point) to a negative number (moves the climb legs downward.)
	 */
	public void moveClimbTo(double inches) {
		if (belowMinimum(inches)) {
			winch.set(ControlMode.Position, toClicks(QuickAccessVars.CLIMB_MINIMUM_TARGET));
			System.out.println("Climb moving to " + inches + ", cutting short to prevent crash!");
		} else if (aboveMaximum(inches)) {
			winch.set(ControlMode.Position, toClicks(QuickAccessVars.CLIMB_MINIMUM_TARGET));
			System.out.println("Climb moving to " + inches + ", cutting short to prevent crash!");
		} else {
			winch.set(ControlMode.Position, toClicks(inches));
		}
	}

	/**
	 * Holds the climb legs at the specified position.
	 * This has no safeties, so be careful!
	 * @param inches Should always be negative.
	 */
	public void stabilizeClimb(double inches) {
		winch.set(ControlMode.Position, toClicks(inches));
	}

	/**
	 * Shuts off the climb winch motor.
	 */
	public void stopClimb() {
		winch.stopMotor();
	}

	/**
	 * Returns the position of the climb in inches, where 0 is the uppermost point
	 * and negative numbers mean a downward extension.
	 */
	public double getClimbPosition() {
		return toInches(winch.getSelectedSensorPosition());
	}

	/**
	 * Resets the climb encoder to 0 inches.
	 */
	public void resetEncoder() {
		winch.setSelectedSensorPosition(0);
	}

	/**
	 * Drives the winch motor at a constant speed. This has no safeties & can damage
	 * the robot, so be careful!
	 * 
	 * @param speed Percentage speed of the winch, from -1 (down) to 1 (up).
	 */
	public void adjustClimbLinear(double speed) {
		double pos = getClimbPosition();
		if (aboveMaximum(pos)) {
			if (speed < 0) {
				winch.set(speed);
			} else {
				winch.stopMotor();
				System.out.println("Climb over-driving up, cutting short to prevent crash! " + pos + " " + speed);
			}
		} else if (belowMinimum(pos)) {
			if (speed > 0) {
				winch.set(speed);
			} else {
				winch.stopMotor();
				System.out.println("Climb over-driving down, cutting short to prevent crash! " + pos + " " + speed);
			}
		} else {
			winch.set(speed);
		}
	}

	/**
	 * Returns true if the specified distance is below the lower bound of motion.
	 * <p>True is BAD. Use this in code to avoid crashing the climb.
	 * @param inches Any distance measurement related to the climb.
	 */
	public boolean belowMinimum(double inches) {
		return inches < QuickAccessVars.CLIMB_MINIMUM_TARGET;
	}

	/**
	 * Returns true if the specified distance is above the upper bound of motion.
	 * <p>True is BAD. Use this in code to avoid crashing the climb.
	 * @param inches Any distance measurement related to the climb.
	 */
	public boolean aboveMaximum(double inches) {
		return inches > QuickAccessVars.CLIMB_MAXIMUM_TARGET;
	}

	/**
	 * Converts encoder clicks to inches.
	 * @param clicks Encoder clicks measured from the output axle.
	 */
	public double toInches(int clicks) {
		return clicks / CLICKS_PER_INCH;
	}

	/**
	 * Converts inches to encoder clicks.
	 * @param inches Inches traveled, measured from the chain.
	 */
	public int toClicks(double inches) {
		return (int) Math.round(inches * CLICKS_PER_INCH);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DefaultStabilizeClimb());
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		builder.addDoubleProperty("position", () -> getClimbPosition(), null);
		builder.addDoubleProperty("clicks", () -> winch.getSelectedSensorPosition(), null);
		builder.addDoubleProperty("speed", () -> winch.getMotorOutputPercent(), null);
	}
}
