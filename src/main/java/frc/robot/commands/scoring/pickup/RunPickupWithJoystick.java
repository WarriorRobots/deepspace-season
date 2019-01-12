package frc.robot.commands.scoring.pickup;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunPickupWithJoystick extends Command {
	
	public RunPickupWithJoystick() {
		requires(Robot.pickup);
	}
		
	@Override
	protected void execute() {
		Robot.pickup.runAtPercentage(Robot.oi.getXboxRightY(0.8));
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Robot.pickup.stop();
	}
}
