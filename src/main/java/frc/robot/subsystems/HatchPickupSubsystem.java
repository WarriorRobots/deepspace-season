package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Constants;
import frc.robot.commands.hatchpickup.DefaultStopHatchPickupWheels;

/**
 * Contains the motor and pneumatics used to pick up hatches from the ground.
 */
public class HatchPickupSubsystem extends Subsystem {

    private static final boolean MOTOR_INVERTED = true;

    private static final int PICKUP_MOTOR_PORT = 2;
    private static final int ROTATOR_SOL_FORWARD = 0;
    private static final int ROTATOR_SOL_REVERSE = 7;

    private WPI_VictorSPX pickupMotor;
    private DoubleSolenoid rotatorSol;

    /**
     * Instantiates new subsystem; make ONLY ONE.
     * <p>
     * <code> public static final HatchPickupSubystem hatchPickup = new
     * HatchPickupSubsystem();
     */
    public HatchPickupSubsystem() {
        pickupMotor = new WPI_VictorSPX(PICKUP_MOTOR_PORT);
        pickupMotor.setInverted(MOTOR_INVERTED);
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
     * Shuts off power to the solenoid. Use after extending or retracting; this will
     * not move the piston.
     */
    public void neutralizePneumatics() {
        rotatorSol.set(Value.kOff);
    }

    /**
     * Run the pickup motors to pull a hatch into the mechanism.
     * 
     * @param speed Speed of motor, from -1 (out) to 1 (in).
     */
    public void runPickup(double speed) {
        pickupMotor.set(speed);
    }

    /**
     * Shuts off the pickup motor.
     */
    public void stopPickup() {
        pickupMotor.stopMotor();
    }

    /**
     * Set the pickup motor to brake mode, making it more difficult for hatches to
     * fall out of the mechanism.
     */
    public void setBrakeMode() {
        pickupMotor.setNeutralMode(NeutralMode.Brake);
    }

    /**
     * Set the pickup motor to coast mode, making it easy for hatches to slip out of
     * the mechanism.
     */
    public void setCoastMode() {
        pickupMotor.setNeutralMode(NeutralMode.Coast);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DefaultStopHatchPickupWheels());
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("hatchpickup-subsystem");
        builder.addDoubleProperty("pickup motor speed", () -> pickupMotor.get(), null);
        builder.addStringProperty("solenoid state", () -> rotatorSol.get().toString(), null);
    }

}