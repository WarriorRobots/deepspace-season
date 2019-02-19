package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveElevatorTo extends Command {

    private double position;

    public MoveElevatorTo(double position) { //TODO make into real life units
        requires(Robot.elevator);
        this.position = position;
    }

    @Override
    protected void execute() {
        Robot.elevator.moveElevatorTo(position);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}