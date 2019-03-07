package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class DefaultIdleCargoPickupWheels extends Command {

    /**
     * Default command for CargoPickupSubsystem; runs the wheels inwards at a slow
     * speed so the ball doesn't fall out.
     */
    public DefaultIdleCargoPickupWheels() {
        requires(Robot.cargoPickupWheels);
    }

    @Override
    protected void execute() {
        Robot.cargoPickupWheels.runPickup(QuickAccessVars.CARGO_PICKUP_IDLE_SPEED);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.cargoPickupWheels.stopPickup();
    }

}