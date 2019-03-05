package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

/**
 * Push the right joystick vertically to drive forwards/backwards, horizontally to turn.
 */
public class ArcadeDrive extends Command {
  public ArcadeDrive() {
    requires(Robot.drivetrain);
  }

  @Override
  protected void execute() {
    Robot.drivetrain.arcadeDriveTeleop(
      Robot.input.getRightY(QuickAccessVars.ARCADE_FORWARD_MODIFIER),
      Robot.input.getRightX(QuickAccessVars.ARCADE_TURN_MODIFIER)
    );
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
