package frc.robot.commands.deprecated;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ResetArmEncoder extends InstantCommand {

    public ResetArmEncoder() {
        requires(Robot.cargoPickup);
    }

    @Override
    protected void execute() {
        Robot.cargoPickup.resetPickupPosition();
    }

}