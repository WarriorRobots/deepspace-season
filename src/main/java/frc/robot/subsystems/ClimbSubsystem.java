/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Constants;
import frc.robot.QuickAccessVars;
import frc.robot.commands.climb.DefaultStabilizeClimb;

public class ClimbSubsystem extends Subsystem {

	public static final double CLICKS_PER_INCH = 1024;

	private static final int WINCH_PORT = 12;
	// FIXME talk to alex about semantics, PORT vs ID

	private WPI_TalonSRX winch;

	public ClimbSubsystem() {

		winch = new WPI_TalonSRX(WINCH_PORT);
		winch.setInverted(true); // FIXME quickaccess
		winch.setSensorPhase(true);

		winch.config_kP(Constants.PID_ID, QuickAccessVars.CLIMB_P, Constants.TIMEOUT_MS);
	}

	/**
	 * Moves the climb to the position specified.
	 * 
	 * @param position Intended position of the climb, in inches
	 */
	public void moveClimbTo(double inches) {
		if (belowMinimum(inches)) {
			winch.set(ControlMode.Position, toClicks(-22));
			System.out.println("Climb moving to " + inches + ", cutting short to prevent crash!");
		} else if (aboveMaximum(inches)) {
			winch.set(ControlMode.Position, toClicks(0));
			System.out.println("Climb moving to " + inches + ", cutting short to prevent crash!");
		} else {
			winch.set(ControlMode.Position, toClicks(inches));
		}
	}

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
	 * Returns the position of the climb in inches
	 */
	public double getClimbPosition() {
		return toInches(winch.getSelectedSensorPosition());
	}

	/**
	 * Zeroes out the climb encoder.
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
			if (speed > 0) {
				winch.stopMotor();
			} else {
				winch.set(speed);
			}
		} else if (belowMinimum(pos)) {
			if (speed < 0) {
				winch.stopMotor();
			} else {
				winch.set(speed);
			}
		} else {
			winch.set(speed);
		}
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DefaultStabilizeClimb());
	}

	/** true is bad */
	public boolean belowMinimum(double inches) {
		return inches < -22;
	}

	/** true is bad */
	public boolean aboveMaximum(double inches) {
		return inches > 0;
	}

	public double toInches(int clicks) {
		return clicks / CLICKS_PER_INCH;
	}

	public int toClicks(double inches) {
		return (int) Math.round(inches * CLICKS_PER_INCH);
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		builder.addDoubleProperty("position", () -> getClimbPosition(), null);
		builder.addDoubleProperty("clicks", () -> winch.getSelectedSensorPosition(), null);
		builder.addDoubleProperty("speed", () -> winch.get(), null);
	}
}
