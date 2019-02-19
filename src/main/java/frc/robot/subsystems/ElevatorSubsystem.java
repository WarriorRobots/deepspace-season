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
import frc.robot.Constants;
import frc.robot.commands.elevator.StabilizeElevator;

/**
 * Contains the winch motor used to raise the elevator, and the limit switch
 * used to detect its lowest point.
 */
public class ElevatorSubsystem extends Subsystem {

	private static final int WINCH_PORT = 7;
	private static final int LIMIT_SWITCH_PORT = 4;

	private WPI_TalonSRX winch;
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
		winch.config_kP(Constants.PID_ID, 0, Constants.TIMEOUT_MS);
		winch.config_kI(Constants.PID_ID, 0, Constants.TIMEOUT_MS);
		winch.config_kD(Constants.PID_ID, 0, Constants.TIMEOUT_MS);
	}

	/**
	 * Runs in Robot.java every tick, checking the Hall effect sensor and resetting
	 * the encoder when it is triggered.
	 */
	public void loop() {
		// TODO what if the hall effect breaks? it returns true permanently, velocity
		// limiter
		if (getElevatorPosition() < 0) {
			DriverStation.reportWarning("The elevator is at a negative position?" + "Check encoder and Hall effect sensor.",
					false);
		}

		// resets the encoder when hall effect switch is triggered
		if (isElevatorFloored()) {
			resetEncoder();
		}
	}

	/**
	 * Moves the elevator to the position specified.
	 * 
	 * @param position Intended position of the elevator, in (units)TODO
	 */
	public void moveElevatorTo(double position) {
		winch.set(ControlMode.Position, position);
	}

	/**
	 * Shuts off the elevator winch motor.
	 */
	public void stopElevator() {
		winch.stopMotor();
	}

	/**
	 * Returns the position of the elevator in (ticks)TODO
	 */
	public int getElevatorPosition() { //TODO int or double
		return winch.getSelectedSensorPosition();
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

	@Deprecated
	public void moveElevator(double speed) {
		winch.set(speed);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new StabilizeElevator());
	}
}
