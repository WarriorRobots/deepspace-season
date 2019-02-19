/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/** Launch a hatch off of the hatch placer */
public class HatchLaunch extends Command {
  
  /** Count variable for the loop of pneumatic */
  private int i;

  public HatchLaunch() {
    super();
    requires(Robot.hatchPlacer);
  }

  
  @Override
  protected void initialize() {
    // Initialization of for loop
    // for (Init, ---, ---) {---};
    i = 0;
  }

  @Override
  protected void execute() {
    // The purpose of running the pneumatic in a loop format is to garantee the pneumatic fires
    // (1 loop is not enough time for the pneumatic to fire)

    // Execute of for loop
    // for (---, ---, ---) {Exec};
    Robot.hatchPlacer.releaseHatch();
    Robot.hatchPlacer.extendLauncher();

    // Increment of for loop
    // for (---, ---, Inc) {---};
    i++;
  }

  @Override
  protected boolean isFinished() {
    // Condition of for loop
    // for (---, Cond, ---) {---};
    return (i > 5);
    // 5 is the approximate number of loops a pneumatic takes to fire
  }

  @Override
  protected void end() {
    // set solonoid to neutral to increase lifespan
    Robot.hatchPlacer.neutralizePneumatics();
  }

}