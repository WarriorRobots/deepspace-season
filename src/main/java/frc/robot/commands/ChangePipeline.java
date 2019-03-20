package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ChangePipeline extends Command {

	int pipeline;

	/**
	 * Changes the pipeline ID of the limelight, which switches the vision tracking settings.
	 * Use the static variables in CameraSubsystem.
	 */
	public ChangePipeline(int pipeline) {
		requires(Robot.camera);
		this.pipeline = pipeline;
	}

	@Override
	protected void initialize() {
		Robot.camera.setPipeline(pipeline);
		System.out.println("Camera: Running " + this.getClass().getSimpleName());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
