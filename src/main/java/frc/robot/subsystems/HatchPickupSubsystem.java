package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Constants;

/**
 * Contains the motor and pneumatics used to pick up hatches from the ground.
 */
public class HatchPickupSubsystem extends Subsystem {

    private static final int INTAKE_MOTOR_PORT = 2;
    private static final int ROTATOR_SOL_FORWARD = 1;
    private static final int ROTATOR_SOL_REVERSE = 6;

    private WPI_VictorSPX intakeMotor;
    private DoubleSolenoid rotatorSol;

    /**
     * Instantiates new subsystem; make ONLY ONE.
     * <p>
     * <code> public static final HatchPickupSubystem hatchPickup = new
     * HatchPickupSubsystem();
     */
    public HatchPickupSubsystem() { //XXX fix ids
        intakeMotor = new WPI_VictorSPX(INTAKE_MOTOR_PORT);
        intakeMotor.setInverted(false); // TODO constants and figure out which one
        rotatorSol = new DoubleSolenoid(Constants.PCM_1, ROTATOR_SOL_FORWARD, ROTATOR_SOL_REVERSE);
    }

    /**
     * Extends the solenoid that puts the pickup mechanism on the ground.
     */
    public void extendPickup() {
        rotatorSol.set(Value.kForward);
    }

    /**
     * Pulls the pickup mechanism back up to the robot.
     * <p>
     * <b>Make sure the scissors are in place!
     */
    public void retractPickup() {
        rotatorSol.set(Value.kReverse);
    }

    /**
     * Shuts off the pneumatics. TODO fix documentation
     */
    public void neutralizePneumatics() {
        rotatorSol.set(Value.kOff);
    }

    /**
     * Run the intake motors to pull a hatch into the mechanism.
     * 
     * @param speed Speed of motor, from -1 (out) to 1 (in).
     */
    public void runIntakeMotor(double speed) {
        intakeMotor.set(speed);
    }

    public void stopIntakeMotor() {
        intakeMotor.stopMotor();
    }

    /**
     * Set the intake motor to brake mode, making it more difficult for hatches to
     * fall out of the mechanism.
     */
    public void setBrakeMode() {
        intakeMotor.setNeutralMode(NeutralMode.Brake);
    }

    /**
     * Set the intake motor to coast mode, making it easy for hatches to slip out of
     * the mechanism.
     */
    public void setCoastMode() {
        intakeMotor.setNeutralMode(NeutralMode.Coast);
    }

    @Override
    protected void initDefaultCommand() {}

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("hatchpickup-subsystem");
        builder.addDoubleProperty("intake motor speed", () -> intakeMotor.get(), null);
        builder.addStringProperty("solenoid state", () -> rotatorSol.get().toString(), null);
    }

}