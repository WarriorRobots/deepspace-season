package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Push the right joystick vertically to drive forwards/backwards, horizontally to turn.
 */
public class SingleJoystickDrive extends Command {
  public SingleJoystickDrive() {
    requires(Robot.drivetrain);
  }

  @Override
  protected void execute() {
    Robot.drivetrain.arcadeDriveTeleop(Robot.input.getRightY(0.5), Robot.input.getRightX(0.5));
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.drivetrain.stopDrive();
  }
}
