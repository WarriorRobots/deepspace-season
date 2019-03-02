package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ReverseCargoPickupWheels extends Command {

    public ReverseCargoPickupWheels() {
        requires(Robot.cargoPickup);
    }

    @Override
    protected void execute() {
        Robot.cargoPickup.runPickupMotor(-1);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}