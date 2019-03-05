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

/** Make the hatch pickup move from being vertical to being on the ground */
public class ExtendHatchPickup extends Command {
  
  private int counter;

  public ExtendHatchPickup() {
    requires(Robot.hatchPickup);
  }

  @Override
  protected void initialize() {
    counter = 0;
  }

  @Override
  protected void execute() {
    counter++;
    Robot.hatchPickup.extendPickup();
  }

  @Override
  protected boolean isFinished() {
    return counter > QuickAccessVars.PNEUMATIC_LOOP_COUNT;
  }

  @Override
  protected void end() {
    Robot.hatchPickup.neutralizePneumatics();
  }

}
