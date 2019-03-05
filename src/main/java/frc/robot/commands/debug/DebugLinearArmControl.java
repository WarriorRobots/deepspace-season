package frc.robot.commands.debug;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DebugLinearArmControl extends Command {
    
    private DoubleSupplier input;

    public DebugLinearArmControl(DoubleSupplier input) {
        requires(Robot.arm);
        this.input = input;
    }

    @Override
    protected void execute() {
        // Robot.arm.rotateArmLinear(input.getAsDouble())
        double angle = Robot.arm.getArmAngle();
        double speed = input.getAsDouble();
        if (angle < 0) {
            if (speed < 0) {
                Robot.arm.stopArm();
            } else {
                Robot.arm.rotateArmLinear(speed);
            }
        } else {
            Robot.arm.rotateArmLinear(speed);
        }
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