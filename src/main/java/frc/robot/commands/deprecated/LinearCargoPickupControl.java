package frc.robot.commands.deprecated;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LinearCargoPickupControl extends Command {
    
    private DoubleSupplier input;

    public LinearCargoPickupControl(DoubleSupplier input) {
        requires(Robot.cargoPickup);
        this.input = input;
    }

    @Override
    protected void execute() {
        Robot.cargoPickup.rotatePickupLinear(input.getAsDouble());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.cargoPickup.stopArmRotator();
    }

}