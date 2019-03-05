/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.commands.cargo.DefaultIdleCargoPickupWheels;

/**
 * Contains the motors used to pickup cargo, and to rotate the mechanism in and
 * out.
 */
public class CargoPickupSubsystem extends Subsystem {

    private static final boolean MOTOR_INVERTED = true;
    private static final int PICKUP_WHEELS_PORT = 1;

    private WPI_VictorSPX pickupWheels;

    /**
     * Instantiates new subsystem; make ONLY ONE.
     * <p>
     * <code> public static final CargoSubsystem cargo = new CargoSubsystem();
     */
    public CargoPickupSubsystem() {
        pickupWheels = new WPI_VictorSPX(PICKUP_WHEELS_PORT);
        pickupWheels.setInverted(MOTOR_INVERTED);
    }

    /**
     * Run the motor that holds cargo balls.
     * 
     * @param speed Decimal value from -1 to 1.
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
        builder.setSmartDashboardType("cargopickup-subsystem");
        builder.addDoubleProperty("cargopickup wheel speed", () -> pickupWheels.get(), null);
    }

}
