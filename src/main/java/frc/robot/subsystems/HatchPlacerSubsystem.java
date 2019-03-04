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

    private static final int SCISSOR_FORWARD = 5; // XXX fix ids by checking if kForward = forward
    private static final int SCISSOR_REVERSE = 2;
    private static final int LAUNCH_FORWARD = 4;
    private static final int LAUNCH_REVERSE = 3;

    private DoubleSolenoid scissorHolder, launcher;

    /**
     * Instantiates new subsystem; make ONLY ONE.
     * <p>
     * <code> public static final HatchPlacerSubsystem hatchPlacer = new
     * HatchPlacerSubsystem();
     */
    public HatchPlacerSubsystem() {
        scissorHolder = new DoubleSolenoid(Constants.PCM_2, SCISSOR_FORWARD, SCISSOR_REVERSE);
        launcher = new DoubleSolenoid(Constants.PCM_2, LAUNCH_FORWARD, LAUNCH_REVERSE);
    }

    /**
     * Secures the hatch in place by opening the scissors.
     */
    public void lockScissors() {
        scissorHolder.set(Value.kReverse);
    }

    /**
     * Releases the hatch by closing the scissors; it will hang loosely and can be
     * knocked off.
     */
    public void loosenScissors() {
        scissorHolder.set(Value.kForward);
    }

    /**
     * Extend the pistons that push the hatch off the scissors.
     * <p>
     * <b>Warning:</b> only use if the hatch is loose (scissors are closed)!
     */
    public void extendLaunchers() {
        launcher.set(Value.kForward);
    }

    /**
     * Retract the pistons that push the hatch off the scissors.
     */
    public void retractLaunchers() {
        launcher.set(Value.kReverse);
    }

    /**
     * Shuts off power to the scissors solenoid. Use after extending or retracting;
     * this will not move the piston.
     */
    public void neutralizeScissors() {
        scissorHolder.set(Value.kOff);
    }

    /**
     * Shuts off power to the launcher solenoid(s). Use after extending or
     * retracting; this will not move the piston.
     */
    public void neutralizeLaunchers() {
        launcher.set(Value.kOff);
    }

    /**
     * Shuts off power to both solenoids. Use after extending or retracting; this
     * will not move the piston.
     */
    public void neutralizePneumatics() {
        neutralizeScissors();
        neutralizeLaunchers();
    }

    @Override
    protected void initDefaultCommand() {
        // none
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("hatchplacer-subsystem");
        builder.addStringProperty("scissor state", () -> scissorHolder.get().toString(), null);
        builder.addStringProperty("launcher state", () -> {
            return "launcher: " + launcher.get().toString();
        }, null);
    }

}