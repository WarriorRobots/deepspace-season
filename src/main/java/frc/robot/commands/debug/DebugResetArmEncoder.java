package frc.robot.commands.debug;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class DebugResetArmEncoder extends Command {

    /**
     * Slowly moves the arm upwards until the hall effect sensor gets triggered. Use
     * if, in the rare case, the encoder becomes out-of-sync.
     */
    public DebugResetArmEncoder() {
        requires(Robot.arm);
    }

    @Override
    protected void execute() {
        Robot.arm.rotateArmLinear(QuickAccessVars.ARM_RESET_SPEED);
        System.out.println("WWDEBUG: DebugResetArmEncoder is running");
    }
    
    @Override
    protected boolean isFinished() {
        return Robot.arm.isLimitSwitchTriggered();
    }

    @Override
    protected void end() {
        Robot.arm.resetArmAngle(0);
        System.out.println("WWDEBUG: DebugResetArmEncoder finished successfully");
    }

    @Override
    protected void interrupted() {
        System.out.println("WWDEBUG: DebugResetArmEncoder was interrupted");
    }
}