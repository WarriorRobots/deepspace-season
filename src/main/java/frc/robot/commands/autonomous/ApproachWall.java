package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;
import frc.robot.util.SynchronousPIDF;
import edu.wpi.first.wpilibj.Timer;


/** Approach the wall keeping the target centered using 2 different PIDs. */
public class ApproachWall extends Command {

	/** PID used for approaching the wall. */
	private SynchronousPIDF PIDapproach;
	/** PID for keeping the target centered */
	private SynchronousPIDF PIDcenter;

	private Timer timer;
	/** Calculated PID output from {@link #PIDapproach} should stored in value. */
	private double valueapproach;
	/** Calculated PID output from {@link #PIDcenter} should stored in value. */
	private double valuecenter;

    public ApproachWall() {
		requires(Robot.drivetrain);
		requires(Robot.camera);

		valueapproach = 0;
		valuecenter = 0;

		PIDapproach = new SynchronousPIDF(
			QuickAccessVars.KP_APPROACH,
			QuickAccessVars.KI_APPROACH,
			QuickAccessVars.KD_APPROACH
		);
		PIDcenter = new SynchronousPIDF(
			QuickAccessVars.KP_CENTER,
			QuickAccessVars.KI_CENTER,
			QuickAccessVars.KD_CENTER
		);

		timer = new Timer();
	}

	@Override
	protected void initialize() {
		//PID.setIzone(minimumI, maximumI);
		//PIDapproach.setOutputRange(-1, 1);
		PIDapproach.setSetpoint(QuickAccessVars.SETPOINT_APPROACH); // Robot should aim to be be 50 in away from the target

		//PIDcenter.setOutputRange(-1, 1);
		PIDcenter.setSetpoint(QuickAccessVars.SETPOINT_CENTER); // Robot should aim to keep the target centered on the crosshair

		timer.start();
		
	}
	
	@Override
	protected void execute() {
	
		if (Robot.camera.canSeeObject()) {
			valueapproach = PIDapproach.calculate(Robot.camera.getTargetDistance(),timer.get());
			valuecenter = PIDcenter.calculate(Robot.camera.getObjectX(), timer.get());
			//System.out.println(value);
			//System.out.println(Robot.camera.getTargetDistance());
		} else {
			// Set value to zero if the target can not be seen so robot does not go crazy
			valueapproach = 0; 
			
			// Don't 0 valuecenter because it should "remember" what direction it's attempting to turn.
		}

		Robot.drivetrain.arcadeDriveRaw(valueapproach, -valuecenter);

	}
	
    @Override
	protected boolean isFinished() {
		return false;
		/*
		return (Robot.camera.getTargetDistance() < QuickAccessVars.SETPOINT_APPROACH &&
			PIDapproach.onTarget(QuickAccessVars.TOLERANCE_APPROACH));
		*/
	}
	
	@Override
	protected void end() {
		timer.stop();
		PIDapproach.reset();
		PIDcenter.reset();
		valueapproach=0;
		valuecenter=0;
		Robot.drivetrain.stopDrive();
	}

}