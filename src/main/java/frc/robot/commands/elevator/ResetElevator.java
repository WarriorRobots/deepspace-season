package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ResetElevator extends Command {

    private static final int THRESHOLD = 1000;

    public ResetElevator() {
        requires(Robot.elevator);
    }

    @Override
    protected void execute() {
        int position = Robot.elevator.getElevatorPosition();
        if (position > THRESHOLD) {
            Robot.elevator.moveElevatorTo(THRESHOLD);
        } else if (position < 0) {
            Robot.elevator.moveElevator(-0.1);
        } else {
            Robot.elevator.stopElevator();
        }
    }

    @Override
    protected boolean isFinished() {
        return Robot.elevator.isElevatorFloored();
    }

    @Override
    protected void end() {
        Robot.elevator.stopElevator();
    }

}