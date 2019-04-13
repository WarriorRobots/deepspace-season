package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AdjustArmRelative extends Command {

	private double initialAngle, adjustByAngle, target;

	/**
	 * Adjust the arm angle relative to its current position.
	 * @param adjustByAngleDegrees How many degrees the arm will adjust by. Positive for out&down,
	 * 				   negative for back&up.
	 */
	public AdjustArmRelative(double adjustByAngleDegrees) {
		requires(Robot.arm);
		this.adjustByAngle = adjustByAngleDegrees;
	}

	@Override
	protected void initialize() {
		initialAngle = Robot.arm.getArmAngle();
		target = initialAngle + adjustByAngle; // works with negatives too
		System.out.println("Arm: Starting " + this.getClass().getSimpleName());
	}

	@Override
	protected void execute() {
		Robot.arm.rotateArmTo(target);
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