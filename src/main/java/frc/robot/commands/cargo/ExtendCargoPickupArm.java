package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ExtendCargoPickupArm extends Command {

  private double angle;

  /**
   * Rotates the arm to the specified angle.
   * @param angle Should always be positive.
   */
  public ExtendCargoPickupArm(double angle) {
    requires(Robot.arm);
    this.angle = angle;
  }

  @Override
  protected void execute() {
    Robot.arm.rotateArmTo(angle);
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
