package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Stop the winch and arm motors.
 */
public class StopClimb extends InstantCommand {

	public StopClimb() {
		requires(Robot.climb);
	}
	
	@Override
	protected void execute() {
		Robot.climb.stopAll();
	}

}
