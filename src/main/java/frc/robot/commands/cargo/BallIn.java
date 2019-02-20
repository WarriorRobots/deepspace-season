package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class BallIn extends Command {

    public BallIn() {
        requires(Robot.cargoPickup);
    }

    @Override
    protected void execute() {
        Robot.cargoPickup.runPickupMotor(0.9);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}