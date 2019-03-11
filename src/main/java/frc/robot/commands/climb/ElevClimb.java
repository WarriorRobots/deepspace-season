/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevClimb extends Command {

  private double target, initialClimbPos, initialElevPos;

  public ElevClimb(double target) {
    requires(Robot.climb);
    requires(Robot.elevator);
    this.target = target;
  }

  @Override
  protected void initialize() {
    initialClimbPos = Robot.climb.getClimbPosition();
    initialElevPos = Robot.elevator.getElevatorPosition();
  }

  @Override
  protected void execute() {
    Robot.climb.moveClimbTo(target);
    Robot.elevator.moveElevatorTo(Robot.climb.getClimbPosition() - initialClimbPos + initialElevPos);
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

/*
change = final - initial
c = f - i

Cc = Cf - Ci
Ec = Ef - Ei
Cc = Ec
Cf - Ci = Ef - Ei
Cf - Ci + Ei = Ef
*/