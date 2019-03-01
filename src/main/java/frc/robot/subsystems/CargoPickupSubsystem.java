/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Constants;
import frc.robot.commands.cargo.StabilizePickup;

/**
 * Contains the motors used to pickup cargo, and to rotate the mechanism in and
 * out.
 */
public class CargoPickupSubsystem extends Subsystem {

    private static final double ARM_P = 1;
    private static final double ARM_I = 0;
    private static final double ARM_D = 0;

    private static final int PICKUP_PORT = 1;
    private static final int ROTATOR_PORT = 8;

    private WPI_VictorSPX pickup;
    private WPI_TalonSRX rotator;

    /**
     * Instantiates new subsystem; make ONLY ONE.
     * <p>
     * <code> public static final CargoSubsystem cargo = new CargoSubsystem();
     */
    public CargoPickupSubsystem() {
        pickup = new WPI_VictorSPX(PICKUP_PORT);
        rotator = new WPI_TalonSRX(ROTATOR_PORT);

        rotator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.PID_ID, Constants.TIMEOUT_MS);
        rotator.config_kP(Constants.PID_ID, ARM_P, Constants.TIMEOUT_MS);
        rotator.config_kI(Constants.PID_ID, ARM_I, Constants.TIMEOUT_MS);
        rotator.config_kD(Constants.PID_ID, ARM_D, Constants.TIMEOUT_MS);
    }

    /**
     * Run the motor that holds cargo balls.
     * 
     * @param speed Decimal value from -1 to 1.
     */
    public void runPickupMotor(double speed) {
        pickup.set(speed);
    }

    /**
     * Rotate the pickup assembly to the specified angle in degrees.
     * 
     * @param positionDegrees Intended position in degrees (TODO specify range).
     */
    public void rotatePickupDegrees(double positionDegrees) { // TODO pick a more logical name for the command
        rotator.set(ControlMode.Position, positionDegrees); // TODO degrees and ticks
    }

    /**
     * Returns the angular position, in [degrees or ticks], of the pickup assembly.
     */
    public int getPickupPosition() {
        return rotator.getSelectedSensorPosition(); // TODO degrees conversion
    }

    /**
     * Set the position of the Arm back to 0.
     */
    public void resetPickupPosition() {
        rotator.setSelectedSensorPosition(0);
    }

    /**
     * Shuts off the pickup motor.
     */
    public void stopPickup() {
        pickup.stopMotor();
    }

    /**
     * <b>Do not use except in emergencies! </b>Shuts off the rotator motor.
     * <p>
     * <i>Warning:</i> Leaving the pickup in a half-rotated position could cause
     * damage.
     */
    @Deprecated
    public void stopRotator() {
        rotator.stopMotor();
    }

    @Override
    public void initDefaultCommand() {
        // TODO run pickup command? possibly joystick, or leave blank
        setDefaultCommand(new StabilizePickup());
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("cargo-subsystem");
        // XXX convert below lambda to degrees
        builder.addDoubleProperty("Rotator motor angle", () -> getPickupPosition(), null);
        builder.addDoubleProperty("Rotator motor speed", () -> rotator.get(), null);
    }

}
