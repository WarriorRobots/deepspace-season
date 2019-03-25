package frc.robot.commands.autonomous.deprecated;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.autonomous.CameraApproach;
import frc.robot.commands.autonomous.deprecated.LineFollowCommand;

@Deprecated
public class CameraNFollower extends CommandGroup {
	/**
	 * Command group to Cammera follow and then pass it over to the line follower.
	 */
	public CameraNFollower() {
		addSequential(new CameraApproach());
		addSequential(new LineFollowCommand());
	}
}
