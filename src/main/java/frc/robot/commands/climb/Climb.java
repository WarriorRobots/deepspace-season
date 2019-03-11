/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Climb extends Command {

  private double target;

  public Climb(double target) {
    requires(Robot.climb);
    this.target = target;
  }

  @Override
  protected void initialize() {
    DriverStation.reportWarning("moving to " + target, false);
  }

  @Override
  protected void execute() {
    Robot.climb.moveClimbTo(target);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.climb.stopClimb();
  }
}