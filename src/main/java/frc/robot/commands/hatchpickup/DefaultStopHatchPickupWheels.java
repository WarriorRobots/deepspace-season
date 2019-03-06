package frc.robot.commands.hatchpickup;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DefaultStopHatchPickupWheels extends Command {

    /**
     * Default command for the HatchPickupSubsystem. This stops the hatch pickup
     * wheels, which is necessary to ensure they don't get jammed up in the plastic
     * ramp.
     */
    public DefaultStopHatchPickupWheels() {
        requires(Robot.hatchPickupWheels);
    }

    @Override
    protected void execute() {
        Robot.hatchPickupWheels.stopPickup();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}