/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Push the left joystick vertically to drive the left wheels. Push the right
 * joystick vertically to drive the right wheels.
 */
public class DoubleJoystickDrive extends Command {
  public DoubleJoystickDrive() {
    requires(Robot.drivetrain);
  }

  @Override
  protected void execute() {
    Robot.drivetrain.tankDriveTeleop(Robot.input.getXboxLeftY()*0.5, Robot.input.getXboxRightY()*0.5);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.drivetrain.stopDrive();
  }
}
