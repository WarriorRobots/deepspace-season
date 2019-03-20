package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AdjustClimbRelative extends Command {

	private double initialPosition, adjustBy, target;

	/**
	 * Adjust the climb position relative to its current position.
	 * @param adjustBy How far up or down the elevator will move from its current
	 *                 position. Positive for up, negative for down.
	 */
	public AdjustClimbRelative(double adjustBy) {
		requires(Robot.climb);
		this.adjustBy = adjustBy;
	}

	@Override
	protected void initialize() {
		initialPosition = Robot.climb.getClimbPosition();
		target = initialPosition + adjustBy;
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
		System.out.println("Climb: Canceling " + this.getClass().getSimpleName());
	}

}