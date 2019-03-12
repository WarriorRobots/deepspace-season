package frc.robot.commands.hatchpickup;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.hatchplacer.LoosenScissors;

public class GroupRetractHatchPickup extends CommandGroup {

	/**
	 * Raise the hatch pickup pneumatics, loosen the scissors, and stop the pickup
	 * wheels. All of these are necessary to prevent mechanical conflicts.
	 */
	public GroupRetractHatchPickup() {
		addParallel(new DefaultStopHatchPickupWheels());
		addParallel(new LoosenScissors());
		addParallel(new SubgroupRetractHatchPickup());
	}

}