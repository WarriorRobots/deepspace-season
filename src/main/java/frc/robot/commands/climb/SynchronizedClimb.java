package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SynchronizedClimb extends Command {

  private double target, initialClimbPos, initialElevPos;

  public SynchronizedClimb() {
    requires(Robot.climb);
    requires(Robot.elevator);
    this.target = -20; // XXX
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
    Robot.elevator.stopElevator();
  }
}

/* TODO fix
change = final - initial
c = f - i

Cc = Cf - Ci
Ec = Ef - Ei
Cc = Ec
Cf - Ci = Ef - Ei
Cf - Ci + Ei = Ef
*/