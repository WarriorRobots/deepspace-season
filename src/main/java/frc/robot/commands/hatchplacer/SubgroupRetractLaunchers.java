package frc.robot.commands.hatchplacer;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class SubgroupRetractLaunchers extends InstantCommand {

	/** Count variable for the loop of pneumatic */
	private int counter;

	/**
	 * Subgroup command that retracts the launcher pistons. 
	 * Do not use this; use the GroupCommand instead.
	 */
	public SubgroupRetractLaunchers() {
		requires(Robot.launchers);
	}

	@Override
	protected void initialize() {
		counter = 0;
		System.out.println("Launchers: Starting " + this.getClass().getSimpleName());
	}

	@Override
	protected void execute() {
		Robot.launchers.retractLaunchers();
		counter++;
	}

	@Override
	protected boolean isFinished() {
		return (counter > QuickAccessVars.PNEUMATIC_LOOP_COUNT);
	}

	@Override
	protected void end() {
		Robot.launchers.neutralizeLaunchers();
		System.out.println("Launchers: Finishing " + this.getClass().getSimpleName());
	}

	@Override
	protected void interrupted() {
		Robot.launchers.neutralizeLaunchers();
		System.out.println("Launchers: Canceling " + this.getClass().getSimpleName());
	}

}
