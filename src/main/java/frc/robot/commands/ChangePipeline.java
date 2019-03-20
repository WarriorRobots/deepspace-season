package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ChangePipeline extends InstantCommand {

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
	protected void execute() {
		Robot.camera.setPipeline(pipeline);
		System.out.println("Camera: Running " + this.getClass().getSimpleName());
	}
}
