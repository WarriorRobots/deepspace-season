package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * When called, robot will drive in Tank Drive using the Y-axes of both joysticks.
 */
public class TankDriveTeleop extends Command {
	
    public TankDriveTeleop() {
    	requires(Robot.drivetrain);
    }

    @Override
	protected void execute() {
    	Robot.drivetrain.tankDriveSquared(Robot.oi.getLeftY(), Robot.oi.getRightY());
//    	Robot.drivetrain.arcadeDriveSquared(Robot.oi.getRightY(0.65), Robot.oi.getRightX(0.75));
    }

    @Override
	protected boolean isFinished() {
        return false;
    }
}