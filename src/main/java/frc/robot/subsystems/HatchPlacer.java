package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

//TODO documentation
public class HatchPlacer extends Subsystem {

    private static final int MAIN_PCM = 1;
    private static final int EXTRA_PCM = 2;

    private static final int SCISSOR_IN = 1;
    private static final int SCISSOR_OUT = 6;
    private static final int L_LAUNCH_IN = 2;
    private static final int L_LAUNCH_OUT = 5;
    private static final int R_LAUNCH_IN = 3;
    private static final int R_LAUNCH_OUT = 4;

    private DoubleSolenoid scissorHolder, leftLauncher, rightLauncher;

    public HatchPlacer() {
        scissorHolder = new DoubleSolenoid(EXTRA_PCM, SCISSOR_IN, SCISSOR_OUT);
        leftLauncher = new DoubleSolenoid(EXTRA_PCM, L_LAUNCH_IN, L_LAUNCH_OUT);
        rightLauncher = new DoubleSolenoid(EXTRA_PCM, R_LAUNCH_IN, R_LAUNCH_OUT);
    }

    public void lockScissors() {
        scissorHolder.set(Value.kReverse);
    }

    public void releaseScissors() {
        scissorHolder.set(Value.kForward);
    }

    public void extendLauncher() {
        leftLauncher.set(Value.kForward);
        rightLauncher.set(Value.kForward);
    }

    public void retractLauncher() {
        leftLauncher.set(Value.kReverse);
        rightLauncher.set(Value.kReverse);
    }

    @Override //TODO default command
    protected void initDefaultCommand() {}

}