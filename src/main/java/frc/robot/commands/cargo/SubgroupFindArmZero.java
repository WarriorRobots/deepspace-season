package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.QuickAccessVars;
import frc.robot.Robot;

public class SubgroupFindArmZero extends Command {

    /**
     * Slowly moves the arm upwards until the hall effect sensor gets triggered.
     * Use if, in the rare case, the encoder becomes out-of-sync.
     */
    public SubgroupFindArmZero() {
        requires(Robot.arm);
    }

	@Override
	protected void initialize() {
		System.out.println("Arm: Starting " + this.getClass().getSimpleName());
	}
	
    @Override
    protected void execute() {
        Robot.arm.rotateArmLinearNoSafety(QuickAccessVars.ARM_RESET_SPEED);
    }

    @Override
    protected boolean isFinished() {
        return Robot.arm.isLimitSwitchTriggered();
    }

    @Override
    protected void end() {
        Robot.arm.resetArmAngleTo(QuickAccessVars.ARM_RESET_ANGLE);
		System.out.println("Arm: Finishing " + this.getClass().getSimpleName());
    }

    @Override
    protected void interrupted() {
		// do NOT reset arm angle
		System.out.println("Arm: Canceling " + this.getClass().getSimpleName());
	}
	
}