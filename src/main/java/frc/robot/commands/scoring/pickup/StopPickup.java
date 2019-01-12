package frc.robot.commands.scoring.pickup;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class StopPickup extends InstantCommand {

	public StopPickup() {
		requires(Robot.pickup);
	}
	
	@Override
	protected void execute() {
		Robot.pickup.stop();
	}

}