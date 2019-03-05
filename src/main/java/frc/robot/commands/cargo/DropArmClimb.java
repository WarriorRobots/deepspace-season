/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

/** Put the cargo pickup into the Horizontal position */
public class DropArmClimb extends Command {

  public DropArmClimb() {
    requires(Robot.arm);
  }

  @Override
  protected void execute() {
    Robot.arm.rotateArmTo(QuickAccessVars.ARM_CLIMB_ANGLE);
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
