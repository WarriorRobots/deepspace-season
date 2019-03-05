package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class DropElevator extends Command {

    private static final double THRESHOLD_INCHES = 3.5;

    public DropElevator() {
        requires(Robot.elevator);
    }

    @Override
    protected void execute() {
        if (Robot.elevator.getElevatorPosition() > THRESHOLD_INCHES) {
            Robot.elevator.adjustElevatorLinear(QuickAccessVars.DROP_ELEVATOR_SPEED);
        } else {
            Robot.elevator.adjustElevatorLinear(-0.05);
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