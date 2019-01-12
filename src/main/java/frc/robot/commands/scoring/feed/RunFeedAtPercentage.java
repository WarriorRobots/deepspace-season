package frc.robot.commands.scoring.feed;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunFeedAtPercentage extends Command {

	private double percentage;
	
	public RunFeedAtPercentage(double percentage) {
		requires(Robot.feed);
		this.percentage = percentage;
	}

	@Override
	protected void execute() {
		Robot.feed.runAtPercentage(percentage);
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
