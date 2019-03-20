package frc.robot.commands.hatchpickup;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

@Deprecated
public class ReverseHatchPickupWheels extends Command {

	/**
	 * Runs the hatch pickup wheels in reverse. This can cause mechanical damage, so
	 * do not use unless for debugging purposes.
	 */
	public ReverseHatchPickupWheels() {
		requires(Robot.hatchPickupWheels);
	}

	@Override
	protected void initialize() {
		System.out.println("HatchPickup: Starting " + this.getClass().getSimpleName());
	}

	@Override
	protected void execute() {
		Robot.hatchPickupWheels.runPickup(-1);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.hatchPickupWheels.stopPickup();
		System.out.println("HatchPickup: Finishing " + this.getClass().getSimpleName()); // interrupt calls this too
	}

	@Override
	protected void interrupted() {
		Robot.hatchPickupWheels.stopPickup();
		System.out.println("HatchPickup: Canceling " + this.getClass().getSimpleName());
	}

}
