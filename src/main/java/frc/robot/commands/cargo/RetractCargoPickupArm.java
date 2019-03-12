package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RetractCargoPickupArm extends Command {

	/**
	 * Rotates the arm to zero degrees (straight upwards).
	 */
	public RetractCargoPickupArm() {
		requires(Robot.arm);
	}

	@Override
	protected void execute() {
		Robot.arm.rotateArmTo(0);
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
