package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.util.SynchronousPIDF;

/** Approach the target keeping the target centered and stopping at a distance using 2 different PIDs. */
public class CameraStopAtDistance extends Command {

	/** PID used for approaching the target. */
	private SynchronousPIDF PIDapproach;
	/** PID for keeping the target centered */
	private SynchronousPIDF PIDcenter;

	private Timer timer;
	/** Calculated PID output from {@link #PIDapproach} should stored in value. */
	private double valueApproach;
	/** Calculated PID output from {@link #PIDcenter} should stored in value. */
	private double valueCenter;

	public CameraStopAtDistance() {
		requires(Robot.drivetrain);
		requires(Robot.camera);

		valueApproach = 0;
		valueCenter = 0;

		PIDapproach = new SynchronousPIDF(QuickAccessVars.KP_APPROACH, QuickAccessVars.KI_APPROACH,
				QuickAccessVars.KD_APPROACH);
		PIDcenter = new SynchronousPIDF(QuickAccessVars.KP_CENTER, QuickAccessVars.KI_CENTER,
				QuickAccessVars.KD_CENTER);

		timer = new Timer();
	}

	@Override
	protected void initialize() {
		System.out.println("Camera: Starting " + this.getClass().getSimpleName());
		Robot.camera.setPipeline(CameraSubsystem.PIPELINE_CENTER);

		// distance in in inches
		PIDapproach.setSetpoint(QuickAccessVars.SETPOINT_APPROACH);
		PIDcenter.setSetpoint(0); // 0 means keep the target centered

		timer.start();

	}

	@Override
	protected void execute() {

		if (Robot.camera.canSeeObject()) {
			valueApproach = PIDapproach.calculate(Robot.camera.getTargetDistance(), timer.get());
			valueCenter = PIDcenter.calculate(Robot.camera.getObjectX(), timer.get());
		} else {
			valueApproach = 0;

			// TODO The following line should be tested for accuracy in what is intended 
			// Don't 0 valuecenter because it should "remember" what direction it's
			// attempting to turn.
		}

		Robot.drivetrain.arcadeDriveRaw(-valueApproach, -valueCenter);

	}

	@Override
	protected boolean isFinished() {
		return false;
    }

	@Override
	protected void end() {
        System.out.println("Camera: Finishing " + this.getClass().getSimpleName());
		timer.stop();
		PIDapproach.reset();
		PIDcenter.reset();
		valueApproach = 0;
		valueCenter = 0;
		Robot.drivetrain.stopDrive();
    }

    @Override
	protected void interrupted() {
        System.out.println("Camera: Canceling " + this.getClass().getSimpleName());
		timer.stop();
		PIDapproach.reset();
		PIDcenter.reset();
		valueApproach = 0;
		valueCenter = 0;
		Robot.drivetrain.stopDrive();
    }
    
    

}