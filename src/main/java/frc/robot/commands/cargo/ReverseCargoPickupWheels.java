package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ReverseCargoPickupWheels extends Command {

	/**
	 * Runs the cargo pickup wheels backwards at full speed,
	 * hopefully for scoring a ball into a cargo/rocket bay.
	 */
	public ReverseCargoPickupWheels() {
		requires(Robot.cargoPickupWheels);
	}

	@Override
	protected void initialize() {
		System.out.println("CargoPickup: Starting " + this.getClass().getSimpleName());
	}

	@Override
	protected void execute() {
		Robot.cargoPickupWheels.runPickup(-1);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.cargoPickupWheels.stopPickup();
		System.out.println("Arm: Finishing " + this.getClass().getSimpleName());
	}

	@Override
	protected void interrupted() {
		Robot.arm.stopArm();
		System.out.println("Arm: Canceling " + this.getClass().getSimpleName());
	}

}