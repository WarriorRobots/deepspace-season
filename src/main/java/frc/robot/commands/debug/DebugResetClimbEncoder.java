package frc.robot.commands.debug;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class DebugResetClimbEncoder extends InstantCommand {

	public DebugResetClimbEncoder() {
		requires(Robot.climb);
	}

	@Override
	protected void execute() {
		Robot.climb.resetEncoder();
		System.out.println("Debug: Running " + this.getClass().getSimpleName());
	}

}
