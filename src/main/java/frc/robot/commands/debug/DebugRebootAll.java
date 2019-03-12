package frc.robot.commands.debug;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;
import frc.robot.subsystems.CameraSubsystem;

public class DebugRebootAll extends InstantCommand {

    /**
     * In one loop: sets all motor speeds to zero, resets all solenoids, and stops
     * all commands. This effectively reboots every component on the robot except
     * encoders. Use with care!
     * 
     * <p>
     * The static method <code>rebootAll()</code> is also made available for running
     * this outside of a command.
     * 
     * @see #rebootAll()
     */
    public DebugRebootAll() {
        requires(Robot.arm);
        requires(Robot.camera);
        requires(Robot.cargoPickupWheels);
        requires(Robot.climb);
        requires(Robot.drivetrain);
        requires(Robot.elevator);
        requires(Robot.hatchPickupWheels);
        requires(Robot.lineFollowers);
        requires(Robot.pneumatics);
    }

    @Override
    protected void execute() {
        rebootAll();
    }

    /**
     * In one loop: sets all motor speeds to zero, resets all solenoids, and stops
     * all commands. This effectively reboots every component on the robot except
     * encoders. Use with care!
     */
    public static void rebootAll() {
        System.out.println("WWDEBUG: DebugRebootAll");
        Robot.arm.stopArm();
        Robot.camera.setPipeline(CameraSubsystem.PIPELINE_DRIVER);
        Robot.cargoPickupWheels.stopPickup();
        Robot.climb.stopClimb();
        Robot.drivetrain.stopDrive();
        Robot.elevator.stopElevator();
        Robot.hatchPickupWheels.stopPickup();
        Robot.pneumatics.neutralizeAll();
        Robot.pneumatics.enableCompressor();
    }

    @Override
    protected void end() {
        // kills all currently-running commands
        Scheduler.getInstance().removeAll();
    }

}