package frc.robot.commands.deprecated;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LinearArmControl extends Command {
    
    private DoubleSupplier input;

    public LinearArmControl(DoubleSupplier input) {
        requires(Robot.arm);
        requires(Robot.drivetrain);//XXX remove this
        this.input = input;
    }

    @Override
    protected void execute() {
        Robot.arm.rotateArmLinear(input.getAsDouble());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.arm.stopArm();
    }

}