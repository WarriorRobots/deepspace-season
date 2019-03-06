package frc.robot.commands.hatchpickup;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DefaultStopHatchPickupWheels extends Command {

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