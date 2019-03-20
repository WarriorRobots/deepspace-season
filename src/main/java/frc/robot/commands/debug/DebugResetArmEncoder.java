package frc.robot.commands.debug;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class DebugResetArmEncoder extends Command {

    /**
     * Slowly moves the arm upwards until the hall effect sensor gets triggered.
     * Use if, in the rare case, the encoder becomes out-of-sync.
     */
    public DebugResetArmEncoder() {
        requires(Robot.arm);
    }

	@Override
	protected void initialize() {
		System.out.println("Debug: Starting " + this.getClass().getSimpleName());
	}
	
    @Override
    protected void execute() {
        Robot.arm.rotateArmLinear(QuickAccessVars.ARM_RESET_SPEED);
    }

    @Override
    protected boolean isFinished() {
        return Robot.arm.isLimitSwitchTriggered();
    }

    @Override
    protected void end() {
        Robot.arm.resetArmAngleTo(0);
		System.out.println("Debug: Finishing " + this.getClass().getSimpleName());
    }

    @Override
    protected void interrupted() {
		// do NOT reset arm angle
		System.out.println("Debug: Canceling " + this.getClass().getSimpleName());
	}
	
}