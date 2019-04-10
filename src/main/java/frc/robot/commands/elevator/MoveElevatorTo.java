package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class MoveElevatorTo extends Command {

	/** The position, in inches, that the elevator will move to. */
	private double target;

	/**
	 * Move the elevator's central assembly to a certain number of inches <b>above the floor</b>.
	 * @param positionInches How far ABOVE THE FLOOR the elevator should be, in inches.
	 */
	public MoveElevatorTo(double positionInches) {
		requires(Robot.elevator);
		this.target = positionInches - QuickAccessVars.ELEVATOR_BASE_HEIGHT;
		Robot.ELEVATOR_SETPOINT = positionInches;
	}

	@Override
	protected void initialize() {
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