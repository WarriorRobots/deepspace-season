package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class MoveElevatorTo extends Command {

    /** The position, in inches, that the elevator will move to. */
    private double target;

    /** If this is set to true, the elevator will immediately shut off. */
    private boolean terminateFlag = false; // initially false unless something sets it to true

    /**
     * Move the elevator's central assembly to a certain number of inches <b>above
     * the floor</b>.
     * 
     * @param positionInches How far above the floor the elevator should be, in
     *                       inches.
     */
    public MoveElevatorTo(double positionInches) {
        requires(Robot.elevator);

        if (positionInches == 0) {
            DriverStation.reportError("Use ResetElevator() instead of MoveElevatorTo(0)", false);
            terminateFlag = true;
        }

        this.target = (positionInches - QuickAccessVars.SCISSORS_HEIGHT) / 2;
    }

    @Override
    protected void initialize() {
        if (target < QuickAccessVars.ELEVATOR_MINIMUM_TARGET) {
            DriverStation.reportError(
                    "WWDEBUG: Elevator attempted to move to unsafe position: " + target, false);
            terminateFlag = true;
        }
    }

    @Override
    protected void execute() {
        if (terminateFlag) {
            Robot.elevator.stopElevator();
        } else {
            Robot.elevator.moveElevatorTo(target);
        }
    }

    @Override
    protected boolean isFinished() {
        // if this ever returns true, something went wrong
        return false;
    }

    @Override
    protected void end() {
        Robot.elevator.stopElevator();
    }

}