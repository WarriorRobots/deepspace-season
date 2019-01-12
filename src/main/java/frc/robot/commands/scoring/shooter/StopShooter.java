package frc.robot.commands.scoring.shooter;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class StopShooter extends InstantCommand {

	public StopShooter() {
		requires(Robot.shooter);
	}
	
	@Override
	protected void execute() {
		Robot.shooter.stop();
	}

}