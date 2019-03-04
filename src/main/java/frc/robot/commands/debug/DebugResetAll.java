package frc.robot.commands.debug;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class DebugResetAll extends InstantCommand {

    public DebugResetAll() {
        requires(Robot.drivetrain);
        requires(Robot.elevator);
        requires(Robot.hatchPickup);
        requires(Robot.hatchPlacer);
        requires(Robot.arm);
        requires(Robot.pneumaticBase);
        requires(Robot.lineFollowers);
    }

    @Override
    protected void execute() {
        DriverStation.reportWarning("FULL RESET", false);
        Robot.drivetrain.stopDrive();
        Robot.drivetrain.resetEncoders();
        Robot.drivetrain.resetAngle();
        Robot.elevator.stopElevator();
        Robot.hatchPickup.stopPickup();
        Robot.hatchPickup.neutralizePneumatics();
        Robot.hatchPlacer.neutralizePneumatics();
        Robot.arm.stopArm();
        Robot.cargoPickup.stopPickup();
        Robot.pneumaticBase.disableCompressor();
    }

    @Override
    protected void end() {
        Robot.pneumaticBase.enableCompressor();
    }

}