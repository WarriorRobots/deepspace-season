package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.QuickAccessVars;
import frc.robot.commands.hatchpickup.DefaultStopHatchPickupWheels;

/**
 * Contains the motor and pneumatics used to pick up hatches from the ground.
 */
public class HatchPickupSubsystem extends Subsystem {

    private static final int PICKUP_MOTOR_PORT = 2;

    private WPI_VictorSPX pickupMotor;

    /**
     * Instantiates new subsystem; make ONLY ONE.
     * <p>
     * <code> public static final HatchPickupSubystem hatchPickup = new
     * HatchPickupSubsystem();
     */
    public HatchPickupSubsystem() {
        pickupMotor = new WPI_VictorSPX(PICKUP_MOTOR_PORT);
        pickupMotor.setInverted(QuickAccessVars.HATCH_PICKUP_WHEELS_INVERTED);
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
        builder.addDoubleProperty("speed", () -> pickupMotor.get(), null);
    }

}