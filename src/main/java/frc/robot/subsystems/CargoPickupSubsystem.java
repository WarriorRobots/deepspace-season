package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.QuickAccessVars;
import frc.robot.commands.cargo.DefaultIdleCargoPickupWheels;

/**
 * Contains the motors used to pickup and shoot cargo balls.
 */
public class CargoPickupSubsystem extends Subsystem {

    private static final int PICKUP_WHEELS_PORT = 1;

    private WPI_VictorSPX pickupWheels;

    /**
     * Instantiates new subsystem; make ONLY ONE.
     * <p>
     * <code> public static final CargoSubsystem cargo = new CargoSubsystem();
     */
    public CargoPickupSubsystem() {
        pickupWheels = new WPI_VictorSPX(PICKUP_WHEELS_PORT);
        pickupWheels.setInverted(QuickAccessVars.CARGO_PICKUP_WHEELS_INVERTED);
    }

    /**
     * Run the pickup motor at a constant speed.
     * @param speed From -1 (ball shoots out) to 1 (ball gets sucked in).
     */
    public void runPickup(double speed) {
        pickupWheels.set(speed);
    }

    /**
     * Shuts off the pickup motor.
     */
    public void stopPickup() {
        pickupWheels.stopMotor();
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DefaultIdleCargoPickupWheels());
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("speed", () -> pickupWheels.get(), null);
    }
    
}
