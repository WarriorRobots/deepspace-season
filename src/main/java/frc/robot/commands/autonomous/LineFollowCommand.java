package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LineFollowCommand extends Command {

	double leftSpeed, rightSpeed;

	public LineFollowCommand() {
		requires(Robot.lineFollowers);
		requires(Robot.drivetrain);
	}

	@Override
	protected void initialize() {
		leftSpeed = 0.25;
		rightSpeed = 0.25;
	}

	/**
	 * STEPS:
	 * 1. Make sure that program only searches for line, does not start following until Approach sends "stop"
	 * 2. Send value to Network table value of 'on-line' when infrared goes off
	 * 3. In Approach Curve, go until on-line says TRUE and once stopped, make sure you tell line follow it's okay to start
	 */
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double normal = 0.4;
		double weak = 0.25;
		double strong = 0.05;
		switch (Robot.lineFollowers.getCorrection()) {
		case STRAIGHT:
			leftSpeed = normal;
			rightSpeed = normal;
			break;
		case WEAKLEFT:
			leftSpeed = weak;
			rightSpeed = normal;
			break;
		case WEAKRIGHT:
			leftSpeed = normal;
			rightSpeed = weak;
			break;
		case STRONGLEFT:
			leftSpeed = strong;
			rightSpeed = normal;
			break;
		case STRONGRIGHT:
			leftSpeed = normal;
			rightSpeed = strong;
			break;
		default: // no break because you want this to fallthrough
		case STOP:
			leftSpeed = 0;
			rightSpeed = 0;
			break;
		}

		System.out.println(Robot.lineFollowers.getCorrection().toString());

		Robot.drivetrain.tankDriveRaw(leftSpeed, rightSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

}
