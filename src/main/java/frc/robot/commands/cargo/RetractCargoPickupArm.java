package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class RetractCargoPickupArm extends Command {

	/**
	 * Rotates the arm to zero degrees (straight upwards).
	 */
	public RetractCargoPickupArm() {
		requires(Robot.arm);
	}

	@Override
	protected void initialize() {
		System.out.println("Arm: Starting " + this.getClass().getSimpleName());
	}

	@Override
	protected void execute() {
		Robot.arm.rotateArmTo(QuickAccessVars.ARM_DEFAULT_ANGLE);
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
