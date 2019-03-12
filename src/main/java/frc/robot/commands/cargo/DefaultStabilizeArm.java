package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DefaultStabilizeArm extends Command {

  private double initialPosition;

  /**
   * Holds the arm in a stable position using motor power to fight gravity.
   */
  public DefaultStabilizeArm() {
    requires(Robot.arm);
  }

  @Override
  protected void initialize() {
    initialPosition = Robot.arm.getArmAngle();
  }

  @Override
  protected void execute() {
    Robot.arm.stabilizeArm(initialPosition);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.arm.stopArm();
  }
}
