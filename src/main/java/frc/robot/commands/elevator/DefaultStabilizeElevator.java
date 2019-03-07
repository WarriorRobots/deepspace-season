package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DefaultStabilizeElevator extends Command {

    private double initialPosition;

    /**
     * Holds the elevator winch in a stable position using motor power to fight
     * gravity.
     */
    public DefaultStabilizeElevator() {
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