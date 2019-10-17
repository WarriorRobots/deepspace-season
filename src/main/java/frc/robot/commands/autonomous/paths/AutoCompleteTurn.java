package frc.robot.commands.autonomous.paths;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;
import frc.robot.util.AutoHandler;
import frc.robot.util.SynchronousPIDF;

/**
 * When run, the robot will turn to the provided angle,
 * using a PID loop to maintain accuracy and control.
 */
public class AutoCompleteTurn extends Command {
	
	private double angleStart, angleTarget, output;
	private double angleTooBig = 90; // amount that it should not try to complete by turning
	
	private boolean stopsAtSetpoint = true;
	
	private SynchronousPIDF pidLoop;
	private Timer timer;
	
	/**
	 * Finishes the angle that isn't completed by the PathFinder.
	 */
	public AutoCompleteTurn() {
		requires(Robot.drivetrain);
		
		pidLoop = new SynchronousPIDF(
			QuickAccessVars.AUTO_TURN_P,
			QuickAccessVars.AUTO_TURN_I,
			QuickAccessVars.AUTO_TURN_D);
		
		timer = new Timer();
		//System.out.println("angleTarget " + Double.toString(this.angleTarget));
	}

	/**
	 * Set the internal PID constants to new values
	 * @param p  P gain
	 * @param i  I gain
	 * @param d  D gain
	 */
	public void setPID(double p, double i, double d) {
		pidLoop.setPID(p, i, d);
	}

	@Override
	protected void initialize() {
	Robot.drivetrain.resetAngle();
	
	angleStart = Robot.drivetrain.getAngleDegrees();
	angleTarget = AutoHandler.getInstance().getLeftOver();
	
    try {
      pidLoop.setIzone(-QuickAccessVars.AUTO_TURN_TOLERANCE, QuickAccessVars.AUTO_TURN_TOLERANCE);
      pidLoop.setOutputRange(-1, 1);
    } catch (Exception e) {}
		pidLoop.setSetpoint(angleTarget);
		timer.start();
	}
	
	@Override
	protected void execute() {
		output = pidLoop.calculate(Robot.drivetrain.getAngleDegrees(), timer.get());
		Robot.drivetrain.arcadeDriveRaw(0, output);
	}

	@Override
	protected boolean isFinished() {
		// if the robot turns more than it ever should turn, then stop it
		if (Math.abs(Robot.drivetrain.getAngleDegrees()-angleStart) > angleTooBig) {
			return true;
		}
		if (Math.abs(angleTarget-angleStart) > angleTooBig) {
			return true;
		}

		if (pidLoop.onTarget(QuickAccessVars.AUTO_TURN_TOLERANCE) && stopsAtSetpoint) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	protected void end() {
		timer.stop();
		pidLoop.reset();
		Robot.drivetrain.stopDrive();
	}
}