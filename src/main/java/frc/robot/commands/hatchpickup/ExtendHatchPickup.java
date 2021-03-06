package frc.robot.commands.hatchpickup;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.QuickAccessVars;
import frc.robot.commands.hatchplacer.LoosenScissors;

public class ExtendHatchPickup extends CommandGroup {

	/**
	 * Extend the hatch pickup pneumatics, loosen the scissors, delay a split second, then begin running
	 * the wheels.
	 * <p>
	 * The delay is necessary to ensure the wheels are touching the ground;
	 * otherwise, they will catch on the ramp and chew it up.
	 */
	public ExtendHatchPickup() {
		addParallel(new LoosenScissors());
		addParallel(new SubgroupExtendHatchPickup());
		addSequential(new WaitCommand(QuickAccessVars.HATCH_PICKUP_DELAY));
		addSequential(new LoosenScissors()); // loosen scissors as drivers have requested
		// this may cause issues of the scissor commands interrupting the wheels but 
		// drivers have stated that the only time the scissors will be pressed is when they
		// the hatch pickup is up so it should not be an issue
		addSequential(new RunHatchPickupWheels());
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