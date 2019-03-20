package frc.robot.commands.hatchpickup;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class SubgroupExtendHatchPickup extends Command {

	private int counter;

	/**
	 * Subgroup command to extend the hatch pickup. Don't run this, use a
	 * GroupCommand instead.
	 */
	public SubgroupExtendHatchPickup() {
		requires(Robot.pneumatics);
	}

	@Override
	protected void initialize() {
		counter = 0;
		System.out.println("Pneumatics: Starting " + this.getClass().getSimpleName());
	}

	@Override
	protected void execute() {
		counter++;
		Robot.pneumatics.extendPickup();
	}

	@Override
	protected boolean isFinished() {
		return counter > QuickAccessVars.PNEUMATIC_LOOP_COUNT;
	}

	@Override
	protected void end() {
		Robot.pneumatics.neutralizePickup();
		System.out.println("Pneumatics: Finishing " + this.getClass().getSimpleName());
	}

	@Override
	protected void interrupted() {
		Robot.pneumatics.neutralizePickup();
		System.out.println("Pneumatics: Canceling " + this.getClass().getSimpleName());
	}

}
