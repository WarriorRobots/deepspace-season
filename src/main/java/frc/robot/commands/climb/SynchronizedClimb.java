package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class SynchronizedClimb extends Command {

	private double initialClimbPos, initialElevPos;

	private double target;

	/**
	 * Move the elevator and climb down in sync.
	 */
	public SynchronizedClimb(double target) {
		requires(Robot.climb);
		requires(Robot.elevator);

		this.target = target;
	}

	@Override
	protected void initialize() {
		initialClimbPos = Robot.climb.getClimbPosition();
		initialElevPos = Robot.elevator.getElevatorPosition();
		System.out.println("Climb: Starting " + this.getClass().getSimpleName());
	}

	@Override
	protected void execute() {
		Robot.climb.moveClimbTo(target);
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