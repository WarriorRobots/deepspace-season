package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class StabilizeElevator extends Command {

    private int initialPosition;

    public StabilizeElevator() {
        requires(Robot.elevator);
    }

    @Override
    protected void initialize() {
        initialPosition = Robot.elevator.getElevatorPosition();
    } 

    @Override
    protected void execute() {
        Robot.elevator.moveElevatorTo(initialPosition);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.elevator.stopElevator();
    }

}