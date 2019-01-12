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

public class RighttoRightSwitch extends CommandGroup {
	
	public RighttoRightSwitch() {
		addParallel(new LowerHood());
		addSequential(new DriveAuto(142.25)); //was 142.25 //was also 102 for straight
		addSequential(new TurnAuto(-90));
		addParallel(new RunShooterAtVelocity(Constants.ShooterRig.SWITCH_SPEED)); // rev shooter early, to reduce wasted time
		addSequential(new DriveAuto(30.75), 2);
		addParallel(new RunFeedAtDefault());
		addSequential(new WaitCommand(0.5));
		addSequential(new StopAllScoringMotors()); 
	}

}