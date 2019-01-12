package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.autonomous.DriveAuto;
import frc.robot.commands.autonomous.TurnAuto;
import frc.robot.commands.pneumatics.LowerHood;
import frc.robot.commands.scoring.StopAllScoringMotors;
import frc.robot.commands.scoring.feed.RunFeedAtDefault;
import frc.robot.commands.scoring.shooter.RunShooterAtVelocity;

public class LefttoLeftSwitch extends CommandGroup {

	public LefttoLeftSwitch() {
		addParallel(new LowerHood());
		addSequential(new DriveAuto(149.25));
		addSequential(new TurnAuto(90));
		addParallel(new RunShooterAtVelocity(Constants.ShooterRig.SWITCH_SPEED)); // rev shooter early, to reduce wasted time
		addSequential(new DriveAuto(30.75), 2);
		addParallel(new RunFeedAtDefault());
		addSequential(new WaitCommand(0.5));
		addSequential(new StopAllScoringMotors());
	}
	
}