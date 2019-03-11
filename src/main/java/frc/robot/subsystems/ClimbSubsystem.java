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
import frc.robot.commands.climb.LinearClimb;

// TODO documentation
public class ClimbSubsystem extends Subsystem {
  
  public static final double CLICKS_PER_INCH = 1024;

  private static final int WINCH_PORT = 12; // XXX check port
  // FIXME talk to alex about semantics, PORT vs ID
  
  private WPI_TalonSRX winch; // TODO semantics

  // TODO documentation
  public ClimbSubsystem() {

    winch = new WPI_TalonSRX(WINCH_PORT);

    winch.config_kP(Constants.PID_ID, QuickAccessVars.CLIMB_P, Constants.TIMEOUT_MS);
	// FIXME put in code for inversion & sensorphase
  }

  /**
	 * Moves the climb to the position specified.
	 * 
	 * @param position Intended position of the climb, in inches
	 */
	public void moveClimbTo(double inches) {
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
		winch.set(speed);
  }
  
  @Override
	public void initDefaultCommand() {
		setDefaultCommand(new LinearClimb());
		// setDefaultCommand(new DefaultStabilizeClimb()); XXX prepare default climb with P value
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
