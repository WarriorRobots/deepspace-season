package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CancelClimb extends CommandGroup {

	/**
	 * Cancel the climb function by raising the legs back to 0 inches.
	 */
	public CancelClimb() {
		addParallel(new MoveClimbTo(0));
	}

}