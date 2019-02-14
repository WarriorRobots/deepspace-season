package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Contains the pneumatics required to place hatches on the velcro targets.
 */
public class HatchPlacerSubsystem extends Subsystem {

    private static final int MAIN_PCM = 1;
    private static final int EXTRA_PCM = 2;

    private static final int SCISSOR_IN = 1;
    private static final int SCISSOR_OUT = 6;
    private static final int L_LAUNCH_IN = 2;
    private static final int L_LAUNCH_OUT = 5;
    private static final int R_LAUNCH_IN = 3;
    private static final int R_LAUNCH_OUT = 4;

    private DoubleSolenoid scissorHolder, leftLauncher, rightLauncher;

    /**
     * Instantiates new subsystem; make ONLY ONE.
     * <p>
     * <code> public static final HatchPlacerSubsystem hatchPlacer = new HatchPlacerSubsystem();
     */
    public HatchPlacerSubsystem() {
        scissorHolder = new DoubleSolenoid(EXTRA_PCM, SCISSOR_IN, SCISSOR_OUT);
        leftLauncher = new DoubleSolenoid(EXTRA_PCM, L_LAUNCH_IN, L_LAUNCH_OUT);
        rightLauncher = new DoubleSolenoid(EXTRA_PCM, R_LAUNCH_IN, R_LAUNCH_OUT);
    }

    /**
     * Secures the hatch in place by opening the scissors.
     */
    public void secureHatch() {
        scissorHolder.set(Value.kReverse);
    }

    /**
     * Releases the hatch by closing the scissors;
     * it will hang loosely and can be knocked off.
     */
    public void releaseHatch() {
        scissorHolder.set(Value.kForward);
    }

    /**
     * Extend the pistons that push the hatch off the scissors.
     * <p><b>Warning:</b> only use if the hatch is loose (scissors are closed)!
     */
    public void extendLauncher() {
        leftLauncher.set(Value.kForward);
        rightLauncher.set(Value.kForward);
    }

    /**
     * Retract the pistons that push the hatch off the scissors.
     */
    public void retractLauncher() {
        leftLauncher.set(Value.kReverse);
        rightLauncher.set(Value.kReverse);
    }

    @Override // TODO default command
    protected void initDefaultCommand() {
    }

}