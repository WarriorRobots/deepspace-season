/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.control;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/** Make the hatch pickup move from being on the ground to being up */
public class HatchIn extends Command {

  public HatchIn() {
    requires(Robot.hatchpickup);
    //requires(Robot.hatchplacer);
    //requires(Robot.elevator);
    //requires(Robot.ballpickup);
  }


  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    //TODO Flip ball pickup forward (so it doesn't collide with elevator )
    //TODO Bring elevator down before doing pickup
    //TODO Flip ball pickup backwards (so it is out of the way of the hatch placer)
    //TODO Release scissors so the hatch can be placed on it
    Robot.hatchpickup.bringUp();
  }

  @Override
  protected boolean isFinished() {
    //TODO make sure the sequence of the events in execute is completed
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
