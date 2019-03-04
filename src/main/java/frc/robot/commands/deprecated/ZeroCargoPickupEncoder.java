package frc.robot.commands.deprecated;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ZeroCargoPickupEncoder extends InstantCommand {

    public ZeroCargoPickupEncoder() {
        requires(Robot.arm);
    }

    @Override
    protected void execute() {
        Robot.arm.resetPickupPosition();
    }

}