package frc.robot.commands.hatchplacer;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class LoosenScissors extends Command {

	/**
	 * Loosen the scissor mechanism, meaning that any hatch will no longer be held
	 * in place.
	 * <p>
	 * The scissors will conflict with the cargo pickup arm if they are in this
	 * position.
	 */
	private int counter;

	public LoosenScissors() {
		requires(Robot.pneumatics);
	}

	@Override
	protected void initialize() {
		counter = 0;
		System.out.println("Pneumatics: Starting " + this.getClass().getSimpleName());
	}

	@Override
	protected void execute() {
		Robot.pneumatics.loosenScissors();
		counter++;
	}

	@Override
	protected boolean isFinished() {
		return (counter > QuickAccessVars.PNEUMATIC_LOOP_COUNT);
	}

	@Override
	protected void end() {
		Robot.pneumatics.neutralizeScissors();
		System.out.println("Pneumatics: Finishing " + this.getClass().getSimpleName());
	}

	@Override
	protected void interrupted() {
		Robot.pneumatics.neutralizeScissors();
		System.out.println("Pneumatics: Canceling " + this.getClass().getSimpleName());
	}

}
