package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AdjustElevatorRelative extends Command {

	private double initialPosition, adjustBy, target;

	/**
	 * Adjust the elevator position relative to its current position.
	 * @param adjustBy How far up or down the elevator will move from its current
	 *                 position. Positive for up, negative for down.
	 */
	public AdjustElevatorRelative(double adjustBy) {
		requires(Robot.elevator);
		this.adjustBy = adjustBy;
	}

	@Override
	protected void initialize() {
		initialPosition = Robot.elevator.getElevatorSetpoint();
		target = initialPosition + adjustBy; // works with negatives too
		System.out.println("Elevator: Starting " + this.getClass().getSimpleName());
	}

	@Override
	protected void execute() {
		Robot.elevator.moveElevatorTo(target);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.elevator.stopElevator();
		System.out.println("Elevator: Finishing " + this.getClass().getSimpleName());
	}

	@Override
	protected void interrupted() {
		Robot.elevator.stopElevator();
		System.out.println("Elevator: Canceling " + this.getClass().getSimpleName());
	}

}