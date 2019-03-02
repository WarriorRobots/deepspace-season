package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveElevatorTo extends Command {

    private int position;
    private static final int MINIMUM_POSITION = 3000; // a bit above 0
    private static final int MAXIMUM_POSITION = 30000;

    public MoveElevatorTo(int positionClicks) { //TODO make into real life units
        requires(Robot.elevator);
        if (positionClicks < MINIMUM_POSITION || positionClicks > MAXIMUM_POSITION) { // TODO find max
            DriverStation.reportError("FIND PROGRAMMER IMMEDIATELY: Elevator attempted to move to unsafe position: " + positionClicks, false);
            this.position = MINIMUM_POSITION;
        } else {
            this.position = positionClicks;
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

}