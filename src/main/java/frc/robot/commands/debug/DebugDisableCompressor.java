package frc.robot.commands.debug;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class DebugDisableCompressor extends InstantCommand {

    public DebugDisableCompressor() {
        requires(Robot.pneumaticBase);
    }

    @Override
    protected void execute() {
        Robot.pneumaticBase.disableCompressor();
        DriverStation.reportWarning("compressor disabled", false);
    }

}