package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Constants;

/**
 * Contains the pneumatics required to place hatches on the velcro targets.
 */
public class HatchPlacerSubsystem extends Subsystem {

    private static final int SCISSOR_FORWARD = 1;
    private static final int SCISSOR_REVERSE = 6;
    private static final int L_LAUNCH_FORWARD = 2;
    private static final int L_LAUNCH_REVERSE = 5;
    private static final int R_LAUNCH_FORWARD = 3;
    private static final int R_LAUNCH_REVERSE = 4;

    private DoubleSolenoid scissorHolder, leftLauncher, rightLauncher;

    /**
     * Instantiates new subsystem; make ONLY ONE.
     * <p>
     * <code> public static final HatchPlacerSubsystem hatchPlacer = new
     * HatchPlacerSubsystem();
     */
    public HatchPlacerSubsystem() {
        scissorHolder = new DoubleSolenoid(Constants.PCM_2, SCISSOR_FORWARD, SCISSOR_REVERSE);
        leftLauncher = new DoubleSolenoid(Constants.PCM_2, L_LAUNCH_FORWARD, L_LAUNCH_REVERSE);
        rightLauncher = new DoubleSolenoid(Constants.PCM_2, R_LAUNCH_FORWARD, R_LAUNCH_REVERSE);
    }

    /**
     * Secures the hatch in place by opening the scissors.
     */
    public void secureHatch() {
        scissorHolder.set(Value.kForward);
    }

    /**
     * Releases the hatch by closing the scissors; it will hang loosely and can be
     * knocked off.
     */
    public void releaseHatch() {
        scissorHolder.set(Value.kReverse);
    }

    /**
     * Extend the pistons that push the hatch off the scissors.
     * <p>
     * <b>Warning:</b> only use if the hatch is loose (scissors are closed)!
     */
    public void extendLauncher() {
        leftLauncher.set(Value.kForward);
        rightLauncher.set(Value.kForward);
    }

    /**
     * Sets solenoids to off.
     */
    public void neutralizePneumatics() {
        neutralizeScissors();
        neutralizeLauncher();
    }

    /** 
     * Set scissors solenoid to off. TODO fix documentation
     */
    public void neutralizeScissors() {
        scissorHolder.set(Value.kOff);
    }

    /**
     * Set both launcher solenoids to off. TODO fix documentation
     */
    public void neutralizeLauncher() {
        leftLauncher.set(Value.kOff);
        rightLauncher.set(Value.kOff);
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

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("hatchplacer-subsystem");
        builder.addStringProperty("scissor state", () -> scissorHolder.get().toString(), null);
        builder.addStringProperty("launcher state", () -> {
            String left = leftLauncher.get().toString();
            String right = rightLauncher.get().toString();
            return "left:" + left + " " + "right:" + right;
        }, null);
    }

}