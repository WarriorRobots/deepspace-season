package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveElevatorTo extends Command {

    private double position;
    private static final int MINIMUM_POSITION = 4; // a bit above 0

    public MoveElevatorTo(double positionInches) {
        requires(Robot.elevator);
        if (positionInches == 0) {
            DriverStation.reportError("Use ResetElevator instead of MoveElevatorTo(0)", false);
        } else if (positionInches < MINIMUM_POSITION) { // TODO find max
            DriverStation.reportError("FIND PROGRAMMER IMMEDIATELY: Elevator attempted to move to unsafe position: " + positionInches, false);
            this.position = MINIMUM_POSITION;
        } else {
            this.position = positionInches;
        }
    }

    @Override
    protected void execute() {
        Robot.elevator.moveElevatorTo(position);
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