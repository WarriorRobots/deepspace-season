package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveClimbTo extends Command {

	private double target;

	/**
	 * Move the climb to the specified position in inches.
	 * @param inches Should always be negative, where 0 is the absolute top position.
	 */
	public MoveClimbTo(double inches) {
		requires(Robot.climb);
		this.target = inches;
	}

	@Override
	protected void initialize() {
		System.out.println("Climb: Starting " + this.getClass().getSimpleName());
	}

	@Override
	protected void execute() {
		Robot.climb.moveClimbTo(target);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.climb.stopClimb();
		System.out.println("Climb: Finishing " + this.getClass().getSimpleName());
	}

	@Override
	protected void interrupted() {
		Robot.climb.stopClimb();
		System.out.println("Climb: Canceling " + this.getClass().getSimpleName());
	}
}
