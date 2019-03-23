package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.CameraSubsystem;

public class LineFollowCommand extends Command {

	private double leftSpeed, rightSpeed;

	private double weak = 0.23;		//0.15
	private double normal = 0.45;	//0.3
	private double strong = 0.08;	//0.05

	private double porportion;

	public LineFollowCommand() {
		requires(Robot.lineFollowers);
		requires(Robot.drivetrain);
		requires(Robot.camera);
	}

	@Override
	protected void initialize() {
		System.out.println("Alignment: Starting " + this.getClass().getSimpleName());
		Robot.camera.setPipeline(CameraSubsystem.PIPELINE_DRIVER);
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

		porportion = Robot.input.getRightY();
		//porportion = ( Robot.input.getLeftY() + Robot.input.getRightY() ) / 2;

		Robot.drivetrain.tankDriveRaw(leftSpeed * porportion, rightSpeed * porportion);
	}

	@Override
	protected void end() {
		System.out.println("Alignment: Ending " + this.getClass().getSimpleName());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

}
