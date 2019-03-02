package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class EnableCompressor extends InstantCommand {

    public EnableCompressor() {
        requires(Robot.pneumaticBase);
    }

    @Override
    protected void execute() {
        Robot.pneumaticBase.enableCompressor();
        DriverStation.reportWarning("compressor enabled", false);
    }

}