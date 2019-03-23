package frc.robot.commands.hatchpickup;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.hatchplacer.LoosenScissors;

public class RetractHatchPickup extends CommandGroup {

	/**
	 * Raise the hatch pickup pneumatics, loosen the scissors, and stop the pickup
	 * wheels. All of these are necessary to prevent mechanical conflicts.
	 */
	public RetractHatchPickup() {
		addParallel(new DefaultStopHatchPickupWheels());
		addParallel(new LoosenScissors());
		addParallel(new SubgroupRetractHatchPickup());
	}

	@Override
	protected void initialize() {
		System.out.println("Pneumatics: Starting " + this.getClass().getSimpleName());
	}

	@Override
	protected void end() {
		System.out.println("Pneumatics: Finishing " + this.getClass().getSimpleName());
	}

	@Override
	protected void interrupted() {
		System.out.println("Pneumatics: Canceling " + this.getClass().getSimpleName());
	}

}