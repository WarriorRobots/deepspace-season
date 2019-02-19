package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LowerElevator extends Command {

    public LowerElevator() {
        requires(Robot.elevator);
    }

    @Override
    protected void execute() {
        if (Robot.elevator.getElevatorPosition() > 0) {
            Robot.elevator.moveElevator(-0.2);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}