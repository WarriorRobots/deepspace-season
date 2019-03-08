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
    protected void execute() {
        if (Robot.elevator.getElevatorPosition() > QuickAccessVars.ELEVATOR_SAFE_MINIMUM) {
            Robot.elevator.adjustElevatorLinear(QuickAccessVars.DROP_ELEVATOR_SPEED);
        } else {
            Robot.elevator.adjustElevatorLinear(QuickAccessVars.ELEVATOR_BELOW_MINIMUM_DRIFT_SPEED);
        }
    }

    @Override
    protected boolean isFinished() {
        return Robot.elevator.isElevatorFloored() || Robot.elevator.getElevatorPosition() < 0;
    }

    @Override
    protected void end() {
        Robot.elevator.stopElevator();
    }

}