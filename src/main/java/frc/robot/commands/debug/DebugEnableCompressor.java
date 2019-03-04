package frc.robot.commands.debug;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class DebugEnableCompressor extends InstantCommand {

    public DebugEnableCompressor() {
        requires(Robot.pneumaticBase);
    }

    @Override
    protected void execute() {
        Robot.pneumaticBase.enableCompressor();
        DriverStation.reportWarning("compressor enabled", false);
    }

}