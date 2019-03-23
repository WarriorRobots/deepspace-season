package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class RaiseElevatorTo extends Command {

	/** The position, in inches, that the elevator will move to. */
	private double target;

	/** Initial position of the elevator when the command is called. */
	private double initial;

	/**
	 * Raise the elevator's central assembly to a certain number of inches <b>above the floor</b>.
	 * This command only works going up however.
	 * @param positionInches How far ABOVE THE FLOOR the elevator should be, in inches.
	 */
	public RaiseElevatorTo(double positionInches) {
		requires(Robot.elevator);
		this.target = positionInches - QuickAccessVars.ELEVATOR_BASE_HEIGHT;
	}

	@Override
	protected void initialize() {
		System.out.println("Elevator: Starting " + this.getClass().getSimpleName());
		initial = Robot.elevator.getElevatorPosition();
	}

	@Override
	protected void execute() {
		if (target > initial) {
			Robot.elevator.moveElevatorTo(target);
		} else {
			Robot.elevator.moveElevatorTo(initial); // act like the stailize command if the elevator starts higher
		}
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