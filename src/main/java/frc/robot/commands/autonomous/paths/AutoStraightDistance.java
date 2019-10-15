package frc.robot.commands.autonomous.paths;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.QuickAccessVars;
import frc.robot.util.SynchronousPIDF;

/**
 * When run, the robot will drive straight at the provided distance,
 * using a PID loop to stay on-course.
 */
public class AutoStraightDistance extends Command {
	
	private int distanceTargetClicks, leftCount, rightCount, avgCount;
	private double angleOutput, distanceOutput;
	
	private boolean stopsAtSetpoint = true;

	private SynchronousPIDF pidAngle, pidDistance;
	private Timer timer;
	
	/**
	 * Create a new instance of {@link DriveAuto}.
	 * @param inches  How many inches to travel.
	 */
	public AutoStraightDistance(double inches) {
		requires(Robot.drivetrain);

		pidAngle = new SynchronousPIDF( // default vals
			QuickAccessVars.AUTO_DISTANCE_ANGLE_P,
			0,
			0);
		pidDistance = new SynchronousPIDF(
			QuickAccessVars.AUTO_DISTANCE_P,
			QuickAccessVars.AUTO_DISTANCE_I,
			QuickAccessVars.AUTO_DISTANCE_D);

		this.distanceTargetClicks = DrivetrainSubsystem.inchesToClicks(inches);
		timer = new Timer();
	}
	
	/**
	 * Set the internal angular PID constants to new values
	 * @param p  P gain
	 * @param i  I gain
	 * @param d  D gain
	 */
	public void setAngularPid(double p, double i, double d) {
		pidAngle.setPID(p, i, d);
	}
	
	/**
	 * Set the internal distance PID constants to new values
	 * @param p  P gain
	 * @param i  I gain
	 * @param d  D gain
	 */
	public void setDistancePid(double p, double i, double d) {
		pidDistance.setPID(p, i, d);
	}
	
	@Override
	protected void initialize() {
		Robot.drivetrain.resetEncoders();
		Robot.drivetrain.resetAngle();
		pidAngle.reset();
		timer.start();
		pidDistance.setSetpoint(distanceTargetClicks);
		// pidDistance.setOutputRange(-0.75, 0.75);
		// pidDistance.setIzone(-0.15, 0.15);
		pidAngle.setSetpoint(0.0);
	}
	
	@Override
	protected void execute() {
		leftCount = Robot.drivetrain.getLeftEncoderClicks();
		rightCount = Robot.drivetrain.getRightEncoderClicks();
		
		avgCount = (int) ((leftCount + rightCount) / 2);
		angleOutput = pidAngle.calculate(Robot.drivetrain.getAngleDegrees(), timer.get());
		distanceOutput = pidDistance.calculate(avgCount, timer.get());
		
		Robot.drivetrain.arcadeDriveRaw(-distanceOutput, angleOutput);
	}

	@Override
	protected boolean isFinished() {
		if (pidDistance.onTarget(QuickAccessVars.AUTO_DISTANCE_TOLERANCE) && stopsAtSetpoint) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	protected void end() {
		Robot.drivetrain.stopDrive();
		timer.stop();
		pidDistance.reset();
		pidAngle.reset();
	}
}