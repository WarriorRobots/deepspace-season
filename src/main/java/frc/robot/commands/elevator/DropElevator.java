package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class DropElevator extends Command {

	/**
	 * Drops the elevator to its lowest point. Use this instead of
	 * <code>MoveElevatorTo(0)</code>.
	 * <p>
	 * At first, the winch will drive downwards at high speed; near the bottom, the
	 * winch will slow down and let the elevator drift towards the bottom with
	 * gravity and inertia.
	 */
	public DropElevator() {
		requires(Robot.elevator);
	}

	@Override
	protected void initialize() {
		System.out.println("Elevator: Starting " + this.getClass().getSimpleName());
	}

	@Override
	protected void execute() {
		// Elevator stops running if it thinks it is below its minimum to let it drift slowly down to the bottom
		// (This is implemented due to the behavior displayed in Match 34 of AZ West)
		if (Robot.elevator.belowMinimum()) {
			Robot.elevator.stopElevator();
		}
		// Elevator runs slower if it thinks it is close to the bottom of its travel
		else if (Robot.elevator.getElevatorPosition() < QuickAccessVars.ELEVATOR_DOWNWARD_DRIFT_THRESHOLD) {
			Robot.elevator.adjustElevatorLinear(QuickAccessVars.ELEVATOR_DOWNWARD_DRIFT_SPEED);
		}
		// Elevator runs downwards at its fast speed if not prohibited by previous statements
		else {
			Robot.elevator.adjustElevatorLinear(QuickAccessVars.ELEVATOR_DROP_SPEED);
		}
	}

	@Override
	protected boolean isFinished() {
		// This command should only be finished when the elevator reaches the bottom of its travel
		return Robot.elevator.isElevatorFloored();
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