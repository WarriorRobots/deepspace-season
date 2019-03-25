package frc.robot.commands.debug;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DebugLinearElevatorControl extends Command {

	private DoubleSupplier input;

	/**
	 * Given a lambda function reading a joystick, this command will drive the
	 * elevator winch motor at a percentage speed.
	 * <p> Safeties are built in to avoid crashing the elevator.
	 * @param input A lambda function <code>() -> getSomeValue()</code> that returns
	 *              a number between -1 (downwards) and 1 (upwards).
	 */
	public DebugLinearElevatorControl(DoubleSupplier input) {
		requires(Robot.elevator);
		this.input = input;
	}

	@Override
	protected void initialize() {
		System.out.println("Debug: Starting " + this.getClass().getSimpleName());
	}

	@Override
	protected void execute() {
		Robot.elevator.adjustElevatorLinear(input.getAsDouble());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.elevator.stopElevator();
		System.out.println("Debug: Finishing " + this.getClass().getSimpleName());
    }

    @Override
    protected void interrupted() {
		Robot.elevator.stopElevator();
		System.out.println("Debug: Canceling " + this.getClass().getSimpleName());
	}

}