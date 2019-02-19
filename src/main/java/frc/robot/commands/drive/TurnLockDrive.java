package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * A drive command for use at high speeds, which will not be sensitive to small
 * changes in joystick position. Allows drivers to drive in straight lines
 * without perfect control of joysticks.
 */
public class TurnLockDrive extends Command {

    /**
     * How far apart the joystick values have to be (in decimal percentage) before
     * TurnLock disables.
     */
    private static final double TURNLOCK_THRESHOLD = 0.2;

    public TurnLockDrive() {
        requires(Robot.drivetrain);
    }

    @Override
    protected void execute() {
        double leftSpeed = Robot.input.getLeftY();
        double rightSpeed = Robot.input.getRightY();

        double difference = Math.abs(leftSpeed - rightSpeed);
        double average = (leftSpeed + rightSpeed) / 2.0;

        if (difference < TURNLOCK_THRESHOLD) {
            // the values are averaged and the robot drives straight
            Robot.drivetrain.tankDriveTeleop(average, average);
        } else {
            // normal TeleopTankDrive behavior
            Robot.drivetrain.tankDriveTeleop(leftSpeed, rightSpeed);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}