package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class SynchronizedClimb extends Command {

	private double initialClimbPos, initialElevPos;

	/**
	 * Move the elevator and climb down in sync.
	 */
	public SynchronizedClimb() {
		requires(Robot.climb);
		requires(Robot.elevator);
	}

	@Override
	protected void initialize() {
		initialClimbPos = Robot.climb.getClimbPosition();
		initialElevPos = Robot.elevator.getElevatorPosition();
		System.out.println("Climb: Starting " + this.getClass().getSimpleName());
	}

	@Override
	protected void execute() {
		Robot.climb.moveClimbTo(QuickAccessVars.CLIMB_TARGET_HEIGHT);
		// current - initial climb position gets the difference
		// then add initial elevator position to find out where the elevator should be
		Robot.elevator.moveElevatorTo(Robot.climb.getClimbPosition() - initialClimbPos + initialElevPos);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.climb.stopClimb();
		Robot.elevator.stopElevator();
		System.out.println("Climb: Finishing " + this.getClass().getSimpleName());
	}

	@Override
	protected void interrupted() {
		Robot.climb.stopClimb();
		Robot.elevator.stopElevator();
		System.out.println("Climb: Canceling " + this.getClass().getSimpleName());
	}
}