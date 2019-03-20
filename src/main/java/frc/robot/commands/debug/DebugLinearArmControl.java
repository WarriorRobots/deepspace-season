package frc.robot.commands.debug;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DebugLinearArmControl extends Command {

	private DoubleSupplier input;

	/**
	 * Given a lambda function reading a joystick, this command 
	 * will drive the arm motor at a percentage speed.
	 * <p> Safety is built in to avoid crashing the arm.
	 * @param input A lambda function <code>() -> getSomeValue()</code> that returns
	 *              a number between -1 (upwards) and 1 (outwards).
	 */
	public DebugLinearArmControl(DoubleSupplier input) {
		requires(Robot.arm);
		this.input = input;
	}

	@Override
	protected void initialize() {
		System.out.println("Debug: Starting " + this.getClass().getSimpleName());
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
		System.out.println("Debug: Finishing " + this.getClass().getSimpleName());
    }

    @Override
    protected void interrupted() {
		Robot.arm.stopArm();
		System.out.println("Debug: Canceling " + this.getClass().getSimpleName());
	}

}