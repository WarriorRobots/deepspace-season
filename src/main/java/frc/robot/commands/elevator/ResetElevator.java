package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ResetElevator extends Command {

    public ResetElevator() {
        requires(Robot.elevator);
    }

    @Override
    protected void execute() {
        Robot.elevator.moveElevatorTo(0);
    }

    @Override
    protected boolean isFinished() {
        return Robot.elevator.isElevatorFloored();
    }

}