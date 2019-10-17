package frc.robot.commands.hatchplacer;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.QuickAccessVars;
import frc.robot.util.AutoHandler;

public class PlaceHatchOnVelcroAUTO extends CommandGroup {

	/**
	 * Command to place a hatch on the velcro strips.
	 * @param safemode If this is set to true, the hatch cannot be placed without a
	 *                 signal from the line followers.
	 */
	public PlaceHatchOnVelcroAUTO(boolean safemode) {
		this.setInterruptible(false);
		addParallel(new SubgroupExtendLaunchers(safemode));
		addSequential(new WaitCommand(QuickAccessVars.PLACE_HATCH_DELAY));
		addParallel(new LoosenScissors());
		addSequential(new WaitCommand(QuickAccessVars.PLACE_HATCH_DELAY));
		addSequential(new SubgroupRetractLaunchers());
	}

	@Override
	protected void initialize() {
		System.out.println("Pneumatics: Starting " + this.getClass().getSimpleName());
		if (AutoHandler.getInstance().getLaunchAutoHatch() == false) end(); // make sure the robot doens't shoot the hatch into thin air
	}

	@Override
	protected void end() {
		System.out.println("Pneumatics: Finishing " + this.getClass().getSimpleName());
	}

	@Override
	protected void interrupted() {
		System.out.println("Pneumatics: Canceling " + this.getClass().getSimpleName());
		Scheduler.getInstance().add(new SubgroupRetractLaunchers());
	}

}