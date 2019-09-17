package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class HomeElevator extends Command {

	/**
	 * The position, in inches above the bottom of the elevator,
	 * the PID will target to when going home.
	 */
	private double target = 1; // TODO consider putting 'target' and 'tolerance' in QuickAccessVars

	/** The amount of inches above the elevator will start drifting the rest of the way down. */
	private double tolerance = 0.5;

	/**
	 * Drops the elevator to its lowest point. Use this instead of
	 * <code>MoveElevatorTo(0)</code>.
	 * <p>
	 * At first, the winch will drive downwards with the PID;
	 * near the very bottom, where the momentum is mostly removed, it will slowly travel
	 * down to the very bottom.
	 */
	public HomeElevator() {
		requires(Robot.elevator);
	}

	@Override
	protected void initialize() {
		System.out.println("Elevator: Starting " + this.getClass().getSimpleName());
	}

	@Override
	protected void execute() {
		/*
		the use of the PID for a majority of the travel allows the system to slow down it's momentum
		and not crash
		*/
		
		// if the elevator is above the "dead" zone, use the pid to get it there
		if (Robot.elevator.getElevatorPosition() > target+tolerance) {
			Robot.elevator.moveElevatorTo(target);
		}
		// if the elevator is in the "dead" zone but above 0, slowly let it down
		else if (Robot.elevator.getElevatorPosition() > 0) {
			Robot.elevator.adjustElevatorLinear(QuickAccessVars.ELEVATOR_DOWNWARD_DRIFT_SPEED);
		}
		// if the elevator is at/below 0, it stops
		else {
			Robot.elevator.stopElevator();
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