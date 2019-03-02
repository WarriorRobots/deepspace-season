/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.hatchpickup;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/** Make the hatch pickup move from being on the ground to being up */
public class RetractHatchPickup extends Command {

  private int counter;

  public RetractHatchPickup() {
    requires(Robot.hatchPickup);
  }

  @Override
  protected void initialize() {
    counter = 0;
  }

  @Override
  protected void execute() {
    counter++;
    Robot.hatchPlacer.loosenScissors();
    Robot.hatchPickup.retractIntake();
  }

  @Override
  protected boolean isFinished() {
    return counter > 5;
  }

  @Override
  protected void end() {
    Robot.hatchPickup.neutralizePneumatics();
    Robot.hatchPlacer.neutralizePneumatics();
  }

}
