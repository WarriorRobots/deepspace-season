package frc.robot.commands.debug;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class DebugEnableCompressor extends InstantCommand {

	/**
	 * Runs for one loop, enabling the robot compressor
	 * (which will not turn on unless there is low pressure).
	 */
	public DebugEnableCompressor() {
		requires(Robot.pneumatics);
	}

	@Override
	protected void execute() {
		Robot.pneumatics.enableCompressor();
		System.out.println("WWDEBUG: DebugEnableCompressor");
	}

}