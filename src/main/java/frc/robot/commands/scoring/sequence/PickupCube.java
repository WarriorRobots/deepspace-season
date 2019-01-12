package frc.robot.commands.scoring.sequence;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Constants;
import frc.robot.commands.scoring.StopAllScoringMotors;
import frc.robot.commands.scoring.feed.RunFeedAtPercentage;
import frc.robot.commands.scoring.pickup.RunPickupAtPercentage;
import frc.robot.commands.scoring.shooter.RunShooterAtPercentage;

/**
 * Pickup the cube from ground, and prevent it from flying out the top by accident.
 */
public class PickupCube extends CommandGroup {

	public PickupCube() {
		addParallel(new RunPickupAtPercentage(Constants.ShooterRig.PICKUP_PERCENT_SPEED));
		addParallel(new RunFeedAtPercentage(Constants.ShooterRig.FEED_PERCENT_SPEED));
		addParallel(new RunShooterAtPercentage(-0.1));
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Scheduler.getInstance().add(new StopAllScoringMotors());
	}
	
	@Override
	protected void interrupted() {
		this.end();
	}
	
}
