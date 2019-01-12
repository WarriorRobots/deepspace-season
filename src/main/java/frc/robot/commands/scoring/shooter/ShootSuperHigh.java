package frc.robot.commands.scoring.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.enums.ShotHeight;

/**
 * Set target to SUPER HIGH and rev the shooter.
 */
public class ShootSuperHigh extends Command {

	public ShootSuperHigh() {
		requires(Robot.shooter);
	}
	
	@Override
	protected void initialize() {
		Robot.shooter.setCurrentTarget(ShotHeight.SUPER_HIGH);
	}
	
	@Override
	protected void execute() {
		Robot.shooter.shootForCurrentTarget();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.shooter.stop();
	}
	
}
