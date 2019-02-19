package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RaiseElevator extends Command {

    public RaiseElevator() {
        requires(Robot.elevator);
    }

    @Override
    protected void execute() {
        Robot.elevator.moveElevator(0.2);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}