/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.hatchplacer;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

/** Set hatch placer back into a neutral position, used after launching */
public class SubgroupRetractLaunchers extends InstantCommand {

  /** Count variable for the loop of pneumatic */
  private int counter;

  /**
   * Subgroup command that retracts the launcher pistons. Do not use this; use the
   * GroupCommand instead.
   */
  public SubgroupRetractLaunchers() {
    requires(Robot.pneumatics);
  }

  @Override
  protected void initialize() {
    // Initialization of for loop
    // for (Init, ---, ---) {---};
    counter = 0;
  }

  @Override
  protected void execute() {
    // The purpose of running the pneumatic in a loop format is to garantee the
    // pneumatic fires
    // (1 loop is not enough time for the pneumatic to fire)

    // Execute of for loop
    // for (---, ---, ---) {Exec};
    Robot.pneumatics.retractLaunchers();

    // Increment of for loop
    // for (---, ---, Inc) {---};
    counter++;
  }

  @Override
  protected boolean isFinished() {
    // Condition of for loop
    // for (---, Cond, ---) {---};
    return (counter > QuickAccessVars.PNEUMATIC_LOOP_COUNT);
    // 5 is the approximate number of loops a pneumatic takes to fire
  }

  @Override
  protected void end() {
    // set solonoid to neutral to increase lifespan
    Robot.pneumatics.neutralizeLaunchers();
  }

}
