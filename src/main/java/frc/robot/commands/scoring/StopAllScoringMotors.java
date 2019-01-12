package frc.robot.commands.scoring;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.scoring.feed.StopFeed;
import frc.robot.commands.scoring.pickup.StopPickup;
import frc.robot.commands.scoring.shooter.StopShooter;

/**
 * Set all motors to 0 (use in CommandGroup).
 */
public class StopAllScoringMotors extends CommandGroup {

	public StopAllScoringMotors() {
		addParallel(new StopPickup());
		addParallel(new StopFeed());
		addParallel(new StopShooter());
	}
	
}
