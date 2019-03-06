package frc.robot.commands.debug;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class DebugRebootAll extends InstantCommand {

    public DebugRebootAll() {
        requires(Robot.drivetrain);
        requires(Robot.elevator);
        requires(Robot.hatchPickupWheels);
        requires(Robot.pneumatics);
        requires(Robot.arm);
        requires(Robot.pneumatics);
        requires(Robot.lineFollowers);
    }

    @Override
    protected void execute() {
        DriverStation.reportWarning("WWDEBUG ===FULL RESET===", false);
        Robot.drivetrain.stopDrive();
        Robot.elevator.stopElevator();
        Robot.hatchPickupWheels.stopPickup();
        Robot.pneumatics.neutralizeAll();
        Robot.arm.stopArm();
        Robot.cargoPickupWheels.stopPickup();
        Robot.pneumatics.disableCompressor();
    }

    @Override
    protected void end() {
        Robot.pneumatics.enableCompressor();
    }

}