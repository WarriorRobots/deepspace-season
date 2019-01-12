package frc.robot.commands.scoring.feed;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class RunFeedAtDefault extends Command {

	public RunFeedAtDefault() {
		requires(Robot.feed);
	}

	@Override
	protected void execute() {
		Robot.feed.runAtPercentage(Constants.ShooterRig.FEED_PERCENT_SPEED);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Robot.feed.stop();
	}
}
