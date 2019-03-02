/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Constants;
import frc.robot.commands.elevator.StabilizeElevator;

/**
 * Contains the winch motor used to raise the elevator, and the limit switch
 * used to detect its lowest point.
 */
public class ElevatorSubsystem extends Subsystem {

	public static final double CLICKS_PER_INCH = 1024;

	private static final double ELEVATOR_P = 0.4;
	private static final double ELEVATOR_I = 0;
	private static final double ELEVATOR_D = 0;

	private static final int WINCH_PORT = 7;
	private static final int LIMIT_SWITCH_PORT = 4;

	private WPI_TalonSRX winch;
	/** Hall effect sensor */
	private DigitalInput limitSwitch;

	/**
	 * Instantiates new subsystem; make ONLY ONE.
	 * <p>
	 * <code> public static final ElevatorSubsystem elevator = new
	 * ElevatorSubsystem();
	 */
	public ElevatorSubsystem() {
		winch = new WPI_TalonSRX(WINCH_PORT);
		limitSwitch = new DigitalInput(LIMIT_SWITCH_PORT);

		winch.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.PID_ID, Constants.TIMEOUT_MS);
		winch.setSensorPhase(true);
		winch.config_kP(Constants.PID_ID, ELEVATOR_P, Constants.TIMEOUT_MS);
		winch.config_kI(Constants.PID_ID, ELEVATOR_I, Constants.TIMEOUT_MS);
		winch.config_kD(Constants.PID_ID, ELEVATOR_D, Constants.TIMEOUT_MS);
	}

	/**
	 * Runs in Robot.java every tick, checking the Hall effect sensor and resetting
	 * the encoder when it is triggered.
	 */
	public void loop() {
		// limiter
		if (getElevatorPosition() < -100) {
			DriverStation.reportError(
					"The elevator is at a negative position?" + "Check encoder and Hall effect sensor.", false);
		}

		// resets the encoder when hall effect switch is triggered
		if (isElevatorFloored()) {
			resetEncoder();
		}
	}

	/**
	 * Moves the elevator to the position specified.
	 * 
	 * @param position Intended position of the elevator, in inches
	 */
	public void moveElevatorTo(double inches) {
		winch.set(ControlMode.Position, toClicks(inches));
	}

	/**
	 * Shuts off the elevator winch motor.
	 */
	public void stopElevator() {
		winch.stopMotor();
	}

	/**
	 * Returns the position of the elevator in inches
	 */
	public double getElevatorPosition() {
		return toInches(winch.getSelectedSensorPosition());
	}

	/**
	 * Zeroes out the elevator encoder.
	 */
	private void resetEncoder() {
		winch.setSelectedSensorPosition(0);
	}

	/**
	 * Returns if the limit switch at the bottom of the elevator is being triggered.
	 */
	public boolean isElevatorFloored() {
		// NOTE digital sensors are inverted; all ports read true by default
		return !limitSwitch.get();
	}

	// TODO documentation here and other
	public void adjustElevatorLinear(double speed) {
		winch.set(speed); // TODO set constraints
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new StabilizeElevator());
	}

	public double toInches(int clicks) {
		return clicks / CLICKS_PER_INCH;
	}

	public int toClicks(double inches) {
		return (int) Math.round(inches * CLICKS_PER_INCH);
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("elevator-subsystem");
		builder.addDoubleProperty("elevator position inches", () -> getElevatorPosition(), null);
		builder.addDoubleProperty("elevator position clicks", () -> winch.getSelectedSensorPosition(), null);
		builder.addBooleanProperty("floored?", () -> isElevatorFloored(), null);
		builder.addDoubleProperty("winch speed", () -> winch.get(), null);
	}
}
