package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.drive.StopDrive;
import frc.robot.commands.scoring.StopAllScoringMotors;

public class EmergencyResetAll extends CommandGroup {

	public EmergencyResetAll() {
		addParallel(new StopDrive());
		addParallel(new StopAllScoringMotors());
	}
	
	@Override
	protected void end() {
		Scheduler.getInstance().removeAll();
		System.out.println("=====RESETTING ALL COMMANDS=====");
	}
	
	@Override
	protected void interrupted() {
		this.end();
	}
	
}
