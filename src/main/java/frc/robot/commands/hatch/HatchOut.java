/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/** Make the hatch pickup move from being vertical to being on the ground */
public class HatchOut extends Command {
  public HatchOut() {
    requires(Robot.hatchPickup);
    //requires(Robot.hatchplacer);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    //TODO make sure the scisors are released before bringing the pickup down
    Robot.hatchPickup.extendPickup();
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
