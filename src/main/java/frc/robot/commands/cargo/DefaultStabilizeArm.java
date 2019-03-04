/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/** Stabilize the cargo pickup */
public class DefaultStabilizeArm extends Command {

  private double initialPosition;

  public DefaultStabilizeArm() {
    requires(Robot.arm);
  }

  @Override
  protected void initialize() {
    initialPosition = Robot.arm.getArmAngle();
  }

  @Override
  protected void execute() {
    Robot.arm.rotateArmTo(initialPosition);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
  
  @Override
  protected void end() {
    Robot.arm.stopArm();
  }
}
