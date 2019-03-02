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
import frc.robot.commands.cargo.StabilizeCargoPickup;

/**
 * Contains the motors used to pickup cargo, and to rotate the mechanism in and
 * out.
 */
public class CargoPickupSubsystem extends Subsystem {

    private static final double ARM_P = 1; // TODO refine this

    private static final int PICKUP_PORT = 1;
    private static final int ROTATOR_PORT = 8;

    private WPI_VictorSPX intakeWheels;
    private WPI_TalonSRX armRotator;

    /**
     * Instantiates new subsystem; make ONLY ONE.
     * <p>
     * <code> public static final CargoSubsystem cargo = new CargoSubsystem();
     */
    public CargoPickupSubsystem() {
        intakeWheels = new WPI_VictorSPX(PICKUP_PORT);
        armRotator = new WPI_TalonSRX(ROTATOR_PORT);
        armRotator.setInverted(true);

        armRotator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.PID_ID, Constants.TIMEOUT_MS);
        armRotator.setSensorPhase(false);
        armRotator.config_kP(Constants.PID_ID, ARM_P, Constants.TIMEOUT_MS);

        // armRotator.configClosedLoopPeakOutput(Constants.PID_ID, 0.3);
    }

    /**
     * Run the motor that holds cargo balls.
     * 
     * @param speed Decimal value from -1 to 1.
     */
    public void runPickupMotor(double speed) {
        intakeWheels.set(speed);
    }

    /**
     * Rotate the pickup assembly to the specified angle in degrees.
     * 
     * @param degrees Intended position in degrees (TODO specify range).
     */
    public void rotatePickupTo(double degrees) {
        armRotator.set(ControlMode.Position, degrees); // TODO degrees and ticks
    }

    @Deprecated
    public void rotatePickupLinear(double speed) {
        armRotator.set(speed); // XXX write safety constraints
    }

    /**
     * Returns the angular position, in [degrees or ticks], of the pickup assembly.
     */
    public int getPickupPosition() {
        return armRotator.getSelectedSensorPosition(); // TODO degrees conversion
    }

    /**
     * Set the position of the Arm back to 0.
     */
    public void resetPickupPosition() {
        armRotator.setSelectedSensorPosition(0);
    }

    /**
     * Shuts off the pickup motor.
     */
    public void stopPickup() {
        intakeWheels.stopMotor();
    }

    /**
     * <b>Do not use except in emergencies! </b>Shuts off the rotator motor.
     * <p>
     * <i>Warning:</i> Leaving the pickup in a half-rotated position could cause
     * damage.
     */
    public void stopArmRotator() {
        armRotator.stopMotor();
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new StabilizeCargoPickup());
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("cargo-subsystem");
        // XXX convert below lambda to degrees
        builder.addDoubleProperty("Rotator motor angle", () -> getPickupPosition(), null);
        builder.addDoubleProperty("Rotator motor speed", () -> armRotator.get(), null);
    }

}
