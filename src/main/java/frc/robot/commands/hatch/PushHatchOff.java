/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/** Push a hatch off of the hatch placer */
public class PushHatchOff extends Command {

  /** Count variable for the loop of pneumatic */
  private int i;

  public PushHatchOff() {
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
    // The purpose of running the pneumatic in a loop format is to garantee the
    // pneumatic fires
    // (1 loop is not enough time for the pneumatic to fire)
    // Execute of for loop
    // for (---, ---, ---) {Exec};
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
  }

  @Override
  protected void end() {
    // set solonoid to neutral to increase lifespan
    Robot.hatchPlacer.neutralizePneumatics();
  }

}
