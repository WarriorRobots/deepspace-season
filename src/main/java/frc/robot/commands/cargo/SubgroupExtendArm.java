package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SubgroupExtendArm extends Command {

	private double angle;

	/**
	 * Rotates the arm to the specified angle.
	 * @param angle Should always be positive.
	 */
	public SubgroupExtendArm(double angle) {
		requires(Robot.arm);
		this.angle = angle;
	}

	@Override
	protected void initialize() {
		System.out.println("Arm: Starting " + this.getClass().getSimpleName());
	}

	@Override
	protected void execute() {
		Robot.arm.rotateArmTo(angle);
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
