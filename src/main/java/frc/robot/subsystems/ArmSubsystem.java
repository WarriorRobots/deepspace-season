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
 * Contains the motors and sensors used to rotate the cargo pickup arm.
 */
public class ArmSubsystem extends Subsystem {

    public static final double CLICKS_PER_DEGREE = 34.0;

    private static final int ROTATOR_ID = 8;
    private static final int CLONE_ID = 11;
    private static final int LIMIT_SWITCH_PORT = 5;

    /** Main motor (reads the encoder and runs the PID. */
    private WPI_TalonSRX armRotator;
    /** Clone motor (copies all output from armRotator). */
    private WPI_TalonSRX armRotatorClone;
    /** Magnetic "hall effect" sensor. */
    private DigitalInput limitSwitch;

    /**
     * Instantiates new subsystem; make ONLY ONE.
     * <p>
     * <code> public static final CargoSubsystem cargo = new CargoSubsystem();
     */
    public ArmSubsystem() {
        armRotator = new WPI_TalonSRX(ROTATOR_ID);
        armRotator.setInverted(QuickAccessVars.ARM_ROTATOR_INVERTED);
        armRotator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.PID_ID, Constants.TIMEOUT_MS);
        armRotator.setSensorPhase(QuickAccessVars.ARM_ENCODER_INVERTED);
        armRotator.config_kP(Constants.PID_ID, QuickAccessVars.ARM_P, Constants.TIMEOUT_MS);

        armRotatorClone = new WPI_TalonSRX(CLONE_ID);
        armRotatorClone.setInverted(QuickAccessVars.ARM_ROTATOR_CLONE_INVERTED);
        armRotatorClone.follow(armRotator);

        limitSwitch = new DigitalInput(LIMIT_SWITCH_PORT);
    }

    /**
     * Rotate the pickup arm to the specified angle in degrees.
     * Has safety built in to prevent crashes.
     * @param degrees From 0 (upright) to a positive angle (out and away from the robot).
     */
    public void rotateArmTo(double degrees) {
        armRotator.set(ControlMode.Position, toClicks(degrees));
        if (belowMinimum(degrees)) {
            armRotator.set(ControlMode.Position, toClicks(QuickAccessVars.ARM_MINIMUM_ANGLE));
            System.out.println("Arm moving to " + degrees + ", cutting short to prevent crash!");
        } else if (aboveMaximum(degrees)) {
            armRotator.set(ControlMode.Position, toClicks(QuickAccessVars.ARM_MAXIMUM_ANGLE));
            System.out.println("Arm moving to " + degrees + ", cutting short to prevent crash!");
        } else {
            armRotator.set(ControlMode.Position, toClicks(degrees));
        }
    }

    /**
     * Rotate the pickup assembly at a constant speed.
     * Has safety built in to prevent crashes.
     * @param speed From -1 (up and in) to 1 (down and out).
     */
    public void rotateArmLinear(double speed) {
        double angle = getArmAngle();
        // @formatter:off
        if (belowMinimum(angle)) {
            if (speed > 0) {
                armRotator.set(speed);
            } else {
                armRotator.stopMotor();
                System.out.println("Arm over-driving backwards, cutting short to prevent crash! "
                    + angle + " " + speed);
            }
        } else if (aboveMaximum(angle)) {
            if (speed < 0) {
                armRotator.set(speed);
            } else {
                armRotator.stopMotor();
                System.out.println("Arm over-driving forwards, cutting short to prevent crash!! "
                    + angle + " " + speed);
            }
        } else {
            armRotator.set(speed);
        } // @formatter:on
    }

    /**
     * Holds the arm at the specified number of degrees.
     * This has no safeties, so be careful!
     * @param degrees Should always be positive.
     */
    public void stabilizeArm(double degrees) {
        armRotator.set(ControlMode.Position, toClicks(degrees));
    }

    /**
     * Returns the angular position, in degrees, of the arm.
     */
    public double getArmAngle() {
        return toDegrees(armRotator.getSelectedSensorPosition());
    }

    /**
     * Reset the encoder to the specified number of degrees (preferably using a hall effect sensor).
     * @param degrees The new degree value for the encoder to start at.
     */
    public void resetArmAngleTo(double degrees) {
        armRotator.setSelectedSensorPosition(toClicks(degrees));
    }

    /**
     * Shuts off the rotator motor. Do not use except in {@code end()}
     */
    public void stopArm() {
        armRotator.stopMotor();
    }

    /**
     * Converts encoder clicks to degrees.
     * @param clicks Encoder clicks measured from the output axle.
     */
    public double toDegrees(int clicks) {
        return clicks / CLICKS_PER_DEGREE;
    }

    /**
     * Converts degrees to encoder clicks.
     * @param degrees Angle measured from the output axle.
     */
    public int toClicks(double degrees) {
        return (int) Math.round(degrees * CLICKS_PER_DEGREE);
    }

    /**
     * Returns true if a magnet is within range of the limit switch.
     */
    public boolean isLimitSwitchTriggered() {
        return !limitSwitch.get(); // flipped because sensor reads true if there is NO magnet
    }

    /**
     * Returns true if the specified angle is below the lower bound of motion.
     * <p>True is BAD. Use this in code to avoid crashing the arm.
     * @param degrees Any degree measurement related to the arm.
     */
    public boolean belowMinimum(double degrees) {
        return degrees < QuickAccessVars.ARM_MINIMUM_ANGLE;
    }

    /**
     * Returns true if the specified angle is above the upper bound of motion.
     * <p>True is BAD. Use this in code to avoid crashing the arm.
     * @param degrees Any degree measurement related to the arm.
     */
    public boolean aboveMaximum(double degrees) {
        return degrees > QuickAccessVars.ARM_MAXIMUM_ANGLE;
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
