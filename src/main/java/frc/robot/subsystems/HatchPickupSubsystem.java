package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.QuickAccessVars;
import frc.robot.commands.hatchpickup.DefaultStopHatchPickupWheels;

/**
 * Contains the motor used to pick up hatches from the ground.
 */
public class HatchPickupSubsystem extends Subsystem {

    private static final int PICKUP_MOTOR_ID = 2;

    private WPI_VictorSPX pickupMotor;

    /**
     * Instantiates new subsystem; make ONLY ONE.
     * <p>
     * <code> public static final HatchPickupSubystem hatchPickup = new
     * HatchPickupSubsystem();
     */
    public HatchPickupSubsystem() {
        pickupMotor = new WPI_VictorSPX(PICKUP_MOTOR_ID);
        pickupMotor.setInverted(QuickAccessVars.HATCH_PICKUP_WHEELS_INVERTED);
    }

    /**
     * Run the pickup motors to pull a hatch into the mechanism.
     * <p>Warning: Running the pickup in while it isn't extended will damage the ramp.
     * @param speed From -1 (out) to 1 (in).
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

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DefaultStopHatchPickupWheels());
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("speed", () -> pickupMotor.get(), null);
    }

}