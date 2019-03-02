package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ResetAll extends InstantCommand {

    public ResetAll() {
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
        Robot.hatchPickup.stopIntakeMotor();
        Robot.hatchPickup.neutralizePneumatics();
        Robot.hatchPlacer.neutralizePneumatics();
        Robot.arm.stopArmRotator();
        Robot.arm.stopPickup();
        Robot.pneumaticBase.disableCompressor();
    }

    @Override
    protected void end() {
        Robot.pneumaticBase.enableCompressor();
    }

}