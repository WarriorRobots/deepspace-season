package frc.robot.commands.scoring.feed;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class StopFeed extends InstantCommand {

	public StopFeed() {
		requires(Robot.feed);
	}
	
	@Override
	protected void execute() {
		Robot.feed.stop();
	}

}