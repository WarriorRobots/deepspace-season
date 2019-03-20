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
		requires(Robot.pneumatics);
	}

	@Override
	protected void initialize() {
		counter = 0;
		System.out.println("Pneumatics: Starting " + this.getClass().getSimpleName());
	}

	@Override
	protected void execute() {
		Robot.pneumatics.retractLaunchers();
		counter++;
	}

	@Override
	protected boolean isFinished() {
		return (counter > QuickAccessVars.PNEUMATIC_LOOP_COUNT);
	}

	@Override
	protected void end() {
		Robot.pneumatics.neutralizeLaunchers();
		System.out.println("Pneumatics: Finishing " + this.getClass().getSimpleName());
	}

	@Override
	protected void interrupted() {
		Robot.pneumatics.neutralizeLaunchers();
		System.out.println("Pneumatics: Canceling " + this.getClass().getSimpleName());
	}

}
