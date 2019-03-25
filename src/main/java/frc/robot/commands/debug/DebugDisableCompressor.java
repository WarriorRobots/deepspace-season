package frc.robot.commands.debug;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

@Deprecated // do not use except for troubleshooting
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
		System.out.println("Debug: Running " + this.getClass().getSimpleName());
	}

}