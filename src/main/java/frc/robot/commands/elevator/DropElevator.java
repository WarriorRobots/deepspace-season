package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class DropElevator extends Command {

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