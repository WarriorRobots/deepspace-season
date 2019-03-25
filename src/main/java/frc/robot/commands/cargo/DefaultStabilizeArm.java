package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DefaultStabilizeArm extends Command {

	private double initialPosition;

	/**
	 * Holds the arm in a stable position using motor power to fight gravity.
	 */
	public DefaultStabilizeArm() {
		requires(Robot.arm);
	}

	@Override
	protected void initialize() {
		initialPosition = Robot.arm.getArmAngle();
		System.out.println("Arm: Starting " + this.getClass().getSimpleName());
	}

	@Override
	protected void execute() {
		Robot.arm.stabilizeArm(initialPosition);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.arm.stopArm();
		System.out.println("Arm: Finishing " + this.getClass().getSimpleName());
	}

	@Override
	protected void interrupted() {
		Robot.arm.stopArm();
		System.out.println("Arm: Canceling " + this.getClass().getSimpleName());
	}

}
