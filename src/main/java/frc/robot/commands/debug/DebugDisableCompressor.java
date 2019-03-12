package frc.robot.commands.debug;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class DebugDisableCompressor extends InstantCommand {

	/**
	 * Runs for one loop, shutting off the robot compressor.
	 */
	public DebugDisableCompressor() {
		requires(Robot.pneumatics);
	}

	@Override
	protected void execute() {
		Robot.pneumatics.disableCompressor();
		System.out.println("WWDEBUG: DebugDisableCompressor");
	}

}