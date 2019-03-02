package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ReverseCargoPickupWheels extends Command {

    private double initialPosition;

    public ReverseCargoPickupWheels() {
        requires(Robot.arm);
    }

    @Override
    protected void initialize() {
        initialPosition = Robot.arm.getPickupPosition();
    }

    @Override
    protected void execute() {
        Robot.arm.runPickupMotor(-1);
        Robot.arm.rotatePickupTo(initialPosition);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.arm.stopPickup();
    }

}