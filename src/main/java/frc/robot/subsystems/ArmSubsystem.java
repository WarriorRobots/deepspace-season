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

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Constants;
import frc.robot.QuickAccessVars;
import frc.robot.commands.cargo.DefaultStabilizeArm;

/**
 * Contains the motors used to pickup cargo, and to rotate the mechanism in and
 * out.
 */
public class ArmSubsystem extends Subsystem {

    public static final double CLICKS_PER_DEGREE = 12288 / 360; // 8.533

    private static final int ROTATOR_PORT = 8;
    private static final int CLONE_PORT = 11;
    private static final int LIMIT_SWITCH_PORT = 5;

    /** Main motor (receives the encoder signals) */
    private WPI_TalonSRX armRotator;

    /** Clone motor (copies all output from armRotator) */
    private WPI_TalonSRX armRotatorClone;

    /** Magnetic "hall effect" sensor */
    private DigitalInput limitSwitch;

    /**
     * Instantiates new subsystem; make ONLY ONE.
     * <p>
     * <code> public static final CargoSubsystem cargo = new CargoSubsystem();
     */
    public ArmSubsystem() {
        armRotator = new WPI_TalonSRX(ROTATOR_PORT);
        armRotator.setInverted(QuickAccessVars.ELEVATOR_WINCH_INVERTED);

        armRotator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.PID_ID, Constants.TIMEOUT_MS);
        armRotator.setSensorPhase(QuickAccessVars.ELEVATOR_ENCODER_INVERTED);
        armRotator.config_kP(Constants.PID_ID, QuickAccessVars.ARM_P, Constants.TIMEOUT_MS);

        armRotator.configClosedLoopPeakOutput(Constants.PID_ID, 1);

        armRotatorClone = new WPI_TalonSRX(CLONE_PORT);
        armRotatorClone.setInverted(QuickAccessVars.ARM_ROTATOR_CLONE_INVERTED);
        armRotatorClone.follow(armRotator);

        limitSwitch = new DigitalInput(LIMIT_SWITCH_PORT);
    }

    /**
     * Rotate the pickup assembly to the specified angle in degrees.
     * 
     * @param degrees Intended position in degrees (stay within about 0-85 degrees).
     */
    public void rotateArmTo(double degrees) {
        armRotator.set(ControlMode.Position, toClicks(degrees));
        if (belowMinimum(degrees)) {
            armRotator.set(ControlMode.Position, toClicks(0));
        } else if (aboveMaximum(degrees)) {
            armRotator.set(ControlMode.Position, toClicks(160));
        } else {
            armRotator.set(ControlMode.Position, toClicks(degrees));
        }
    }

    /**
     * Rotate the pickup assembly at a constant speed. This has no safeties and has
     * potential to cause damage, so be careful!
     * 
     * @param speed Percentage speed of the motor, from -1 (towards the robot) to 1
     *              (away from the robot).
     */
    public void rotateArmLinear(double speed) {
        double angle = getArmAngle();
		if (belowMinimum(angle)) {
			if (speed > 0) {
				armRotator.set(speed);
			} else {
				armRotator.stopMotor();
			}
		} else if (aboveMaximum(angle)) {
			if (speed < 0) {
				armRotator.set(speed);
			} else {
				armRotator.stopMotor();
			}
		} else {
			armRotator.set(speed);
		}
    }

    /**
     * Returns the angular position, in degrees, of the pickup assembly.
     */
    public double getArmAngle() {
        return toDegrees(armRotator.getSelectedSensorPosition());
    }

    /**
     * Set the current angle of the arm as the specified number of degrees
     * (preferably using a hall effect sensor).
     * 
     * @param degrees The new degree value of the arm's current position.
     */
    public void resetArmAngle(double degrees) {
        armRotator.setSelectedSensorPosition(toClicks(degrees));
    }

    /**
     * <b>Do not use except in end() !</b> Shuts off the rotator motor.
     * <p>
     * <i>Warning:</i> Shutting off the pickup motor will make it fall and snap off
     * the hatch scissors.
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

    public boolean isLimitSwitchTriggered() {
        return !limitSwitch.get(); // sensor reads true if there is NO magnet
    }

    /** true is bad */
	public boolean belowMinimum(double degrees) {
		return degrees < 0; // XXX quickaccess
	}

	/** true is bad */
	public boolean aboveMaximum(double degrees) {
		return degrees > 160;
	}

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DefaultStabilizeArm());
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("angle", () -> getArmAngle(), null);
        builder.addDoubleProperty("master speed", () -> armRotator.get(), null);
        builder.addDoubleProperty("clone speed", () -> armRotatorClone.get(), null);
    }

}
