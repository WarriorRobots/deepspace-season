package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.enums.ItemType;

public class MoveElevatorTo extends Command {

    /**
     * If the elevator receives a signal to move below this number, the command will
     * terminate.
     */
    private static final int MINIMUM_SAFE_TARGET = 4; // a bit above 0

    /** The position, in inches, that the elevator will move to. */
    private double target;

    /** If this is set to true, the elevator will immediately shut off. */
    private boolean terminateFlag = false; // initially false unless something sets it to true

    /**
     * Move the specific scoring item (HATCH or CARGO) to a certain number of inches
     * <b>relative to the floor</b>.
     * 
     * @param positionInches How far above the floor the specified item should be,
     *                       in inches.
     * @param itemType       What item the robot will be holding (ItemType.CARGO or
     *                       ItemType.HATCH).
     */
    public MoveElevatorTo(double positionInches, ItemType itemType) {
        requires(Robot.elevator);

        if (positionInches == 0) {
            DriverStation.reportError("Use ResetElevator() instead of MoveElevatorTo(0)", false);
            terminateFlag = true;
        }

        switch (itemType) { // XXX check these values
        default:
            DriverStation.reportError("MoveElevatorTo() constructor does not specify an item", false);
            terminateFlag = true;
        case HATCH:
            this.target = (positionInches - 13) / 2;
            break;
        case CARGO:
            this.target = (positionInches - 21.5) / 2;
            break;
        }
    }

    @Override
    protected void initialize() {
        if (target < MINIMUM_SAFE_TARGET) {
            DriverStation.reportError(
                    "FIND PROGRAMMER IMMEDIATELY: Elevator attempted to move to unsafe position: " + target, false);
            terminateFlag = true;
        }
    }

    @Override
    protected void execute() {
        Robot.elevator.moveElevatorTo(target);
    }

    @Override
    protected boolean isFinished() {
        // if this ever returns true, something went wrong
        return terminateFlag;
    }

    @Override
    protected void end() {
        Robot.elevator.stopElevator();
    }

}