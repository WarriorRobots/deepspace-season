package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DefaultIdleCargoPickupWheels extends Command {

    public DefaultIdleCargoPickupWheels() {
        requires(Robot.cargoIntake);
    }

    @Override
    protected void execute() {
        Robot.cargoIntake.runIntake(0.1);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.cargoIntake.stopIntake();
    }

}