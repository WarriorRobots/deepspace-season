package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DefaultIdleCargoPickupWheels extends Command {

    public DefaultIdleCargoPickupWheels() {
        requires(Robot.cargoPickup);
    }

    @Override
    protected void execute() {
        Robot.cargoPickup.runPickup(0.1);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.cargoPickup.stopPickup();
    }

}