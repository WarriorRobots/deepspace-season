/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.hatchpickup;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class SubgroupExtendHatchPickup extends Command {

  private int counter;

  /**
   * Subgroup command to extend the hatch pickup. Don't run this, use a
   * GroupCommand instead.
   */
  public SubgroupExtendHatchPickup() {
    requires(Robot.pneumatics);
  }

  @Override
  protected void initialize() {
    counter = 0;
  }

  @Override
  protected void execute() {
    counter++;
    Robot.pneumatics.extendPickup();
  }

  @Override
  protected boolean isFinished() {
    return counter > QuickAccessVars.PNEUMATIC_LOOP_COUNT;
  }

  @Override
  protected void end() {
    Robot.pneumatics.neutralizePickup();
  }

}
