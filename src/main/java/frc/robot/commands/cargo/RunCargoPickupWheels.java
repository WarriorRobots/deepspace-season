package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunCargoPickupWheels extends Command {

    /**
     * Runs the cargo pickup wheels inwards at full speed, 
     * hopefully for sucking a ball in.
     */
    public RunCargoPickupWheels() {
        requires(Robot.cargoPickupWheels);
    }

    @Override
    protected void execute() {
        Robot.cargoPickupWheels.runPickup(1);
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