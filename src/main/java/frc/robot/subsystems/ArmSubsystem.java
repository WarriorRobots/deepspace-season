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
import frc.robot.commands.cargo.StabilizeArm;

/**
 * Contains the motors used to pickup cargo, and to rotate the mechanism in and
 * out.
 */
public class ArmSubsystem extends Subsystem {

    private static final double ARM_P = 1; // TODO refine this
    public static final double CLICKS_PER_DEGREE = 12288/360; // 8.533

    private static final int ROTATOR_PORT = 8;

    private WPI_TalonSRX armRotator;

    /**
     * Instantiates new subsystem; make ONLY ONE.
     * <p>
     * <code> public static final CargoSubsystem cargo = new CargoSubsystem();
     */
    public ArmSubsystem() {
        armRotator = new WPI_TalonSRX(ROTATOR_PORT);
        armRotator.setInverted(true);

        armRotator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.PID_ID, Constants.TIMEOUT_MS);
        armRotator.setSensorPhase(false);
        armRotator.config_kP(Constants.PID_ID, ARM_P, Constants.TIMEOUT_MS);

        armRotator.configClosedLoopPeakOutput(Constants.PID_ID, 1);
    }

    /**
     * Rotate the pickup assembly to the specified angle in degrees.
     * 
     * @param degrees Intended position in degrees (TODO specify range).
     */
    public void rotateArmTo(double degrees) {
        armRotator.set(ControlMode.Position, toClicks(degrees)); // TODO degrees and ticks
    }

    public void rotateArmLinear(double speed) {
        armRotator.set(speed); // XXX write safety constraints
    }

    /**
     * Returns the angular position, in degrees, of the pickup assembly.
     */
    public double getArmAngle() {
        return toDegrees(armRotator.getSelectedSensorPosition()); // TODO degrees conversion
    }

    /**
     * Set the position of the Arm back to 0.
     */
    public void resetArmAngle() {
        armRotator.setSelectedSensorPosition(0);
    }

    /**
     * <b>Do not use except in emergencies! </b>Shuts off the rotator motor.
     * <p>
     * <i>Warning:</i> Leaving the pickup in a half-rotated position could cause
     * damage.
     */
    public void stopArm() {
        armRotator.stopMotor();
    }

    public double toDegrees(int clicks) {
        return clicks / CLICKS_PER_DEGREE;
    }

    public int toClicks(double degrees) {
        return (int) Math.round(degrees * CLICKS_PER_DEGREE);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new StabilizeArm());
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("arm-subsystem");
        builder.addDoubleProperty("Rotator motor angle", () -> getArmAngle(), null);
    }

}
