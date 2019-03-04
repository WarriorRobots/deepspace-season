package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class DisableCompressor extends InstantCommand {

    public DisableCompressor() {
        requires(Robot.pneumaticBase);
    }

    @Override
    protected void execute() {
        Robot.pneumaticBase.disableCompressor();
        DriverStation.reportWarning("compressor disabled", false);
    }

}