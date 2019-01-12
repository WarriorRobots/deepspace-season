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

public class LefttoRightSwitch extends CommandGroup {
	
	public LefttoRightSwitch() {
		addParallel(new LowerHood()); // lower hood for switch shot
		addSequential(new DriveAuto(50)); // drive forward to center of starting zone
		addSequential(new TurnAuto(90)); // turn right
		addSequential(new DriveAuto(170));
		addSequential(new TurnAuto(-90)); // turn left, facing right switch plate
		addParallel(new RunShooterAtVelocity(Constants.ShooterRig.SWITCH_SPEED)); // rev shooter early
		addSequential(new DriveAuto(63), 2.0); // drive until bumper hits wall
		addParallel(new RunFeedAtDefault()); // launch cube
		addSequential(new WaitCommand(0.5));
		addSequential(new StopAllScoringMotors()); // stop motors to conserve power
	}

}