package frc.robot.commands.debug;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DebugLinearArmControl extends Command {

    private DoubleSupplier input;

    /**
     * Given a lambda function reading a joystick, this command will drive the arm
     * motor at a percentage speed.
     * <p>
     * Minimal safeties are in place to avoid driving the arm too far backwards, but
     * please exercise caution.
     * 
     * @param input A lambda function <code>() -> getSomeValue()</code> that returns
     *              a number between -1 (upwards) and 1 (outwards).
     */
    public DebugLinearArmControl(DoubleSupplier input) {
        requires(Robot.arm);
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