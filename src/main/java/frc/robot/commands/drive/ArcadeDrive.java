package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArcadeDrive extends Command {
  public ArcadeDrive() {
    requires(Robot.drivetrain);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.drivetrain.arcadeDriveTeleop(Robot.input.getRightY(0.5), Robot.input.getRightX(0.5));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.stopDrive();
  }
}
