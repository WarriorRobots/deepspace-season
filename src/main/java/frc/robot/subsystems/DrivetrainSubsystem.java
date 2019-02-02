package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Constants;
import frc.robot.commands.TeleopTankDrive;

/**
 * Contains the drivetrain, the encoders for the left and right wheels, and the
 * NavX gyroscope.
 */
public class DrivetrainSubsystem extends Subsystem {

	private static final int LEFT_FRONT = 1;
	private static final int LEFT_MIDDLE = 2;
	private static final int LEFT_BACK = 3;
	private static final int RIGHT_FRONT = 4;
	private static final int RIGHT_MIDDLE = 5;
	private static final int RIGHT_BACK = 6;

	private static final int LEFT_ENCODER_PORTA = 0;
	private static final int LEFT_ENCODER_PORTB = 1;
	private static final int RIGHT_ENCODER_PORTA = 2;
	private static final int RIGHT_ENCODER_PORTB = 3;

	private static final double RAMPRATE_SECONDS = 0.25;
	private static final int TIMEOUT_MS = 10;

	private WPI_TalonSRX leftFront, leftMiddle, leftBack, rightFront, rightMiddle, rightBack;
	private SpeedControllerGroup leftGroup, rightGroup;
	private DifferentialDrive differentialDrive;

	private Encoder leftEnc, rightEnc;
	private AHRS navx;

	public DrivetrainSubsystem() {
		leftFront = new WPI_TalonSRX(LEFT_FRONT);
		leftMiddle = new WPI_TalonSRX(LEFT_MIDDLE);
		leftBack = new WPI_TalonSRX(LEFT_BACK);
		rightFront = new WPI_TalonSRX(RIGHT_FRONT);
		rightMiddle = new WPI_TalonSRX(RIGHT_MIDDLE);
		rightBack = new WPI_TalonSRX(RIGHT_BACK);

		leftFront.configOpenloopRamp(RAMPRATE_SECONDS, TIMEOUT_MS);
		leftMiddle.configOpenloopRamp(RAMPRATE_SECONDS, TIMEOUT_MS);
		leftBack.configOpenloopRamp(RAMPRATE_SECONDS, TIMEOUT_MS);
		rightFront.configOpenloopRamp(RAMPRATE_SECONDS, TIMEOUT_MS);
		rightMiddle.configOpenloopRamp(RAMPRATE_SECONDS, TIMEOUT_MS);
		rightBack.configOpenloopRamp(RAMPRATE_SECONDS, TIMEOUT_MS);

		leftGroup = new SpeedControllerGroup(leftFront, leftMiddle, leftBack);
		rightGroup = new SpeedControllerGroup(rightFront, rightMiddle, rightBack);

		leftGroup.setInverted(true);
		rightGroup.setInverted(true);

		differentialDrive = new DifferentialDrive(leftGroup, rightGroup);
		differentialDrive.setSafetyEnabled(false);

		// if NavX is missing, this code will handle errors and prevent a crash
		try {
			navx = new AHRS(I2C.Port.kMXP);
		} catch (Exception ex) {
			DriverStation.reportError(ex.getMessage(), true);
		}

		leftEnc = new Encoder(LEFT_ENCODER_PORTA, LEFT_ENCODER_PORTB);
		rightEnc = new Encoder(RIGHT_ENCODER_PORTA, RIGHT_ENCODER_PORTB);

		leftEnc.setReverseDirection(Constants.Inversions.LEFT_ENCODER_REVERSED);
		rightEnc.setReverseDirection(Constants.Inversions.RIGHT_ENCODER_REVERSED);
	}

	/**
	 * Drives the left and right sides of the robot independently. DO NOT USE WITH
	 * PID.
	 * <p>
	 * The arguments provided are squared to create a more intuitive control
	 * sensitivity.
	 * 
	 * @param leftSpeed  Percentage speed of left side, from -1 to 1.
	 * @param rightSpeed Percentage speed of right side, from -1 to 1.
	 */
	public void tankDriveTeleop(double leftSpeed, double rightSpeed) {
		differentialDrive.tankDrive(leftSpeed, rightSpeed, true);
	}

	/**
	 * Drives the left and right sides of the robot independently. USE WITH PID
	 * ONLY.
	 * <p>
	 * The arguments provided are not squared to prevent PID overcompensation.
	 * 
	 * @param leftSpeed  Percentage speed of left side, from -1 to 1.
	 * @param rightSpeed Percentage speed of right side, from -1 to 1.
	 */
	public void tankDriveRaw(double leftSpeed, double rightSpeed) {
		differentialDrive.tankDrive(leftSpeed, rightSpeed, false);
	}

	/**
	 * Sets the forward and turning speeds of the robot independently. DO NOT USE
	 * WITH PID.
	 * <p>
	 * The arguments provided are squared to create a more intuitive control
	 * sensitivity.
	 * 
	 * @param forwardSpeed Percentage speed for driving forwards or backwards, from
	 *                     -1 to 1.
	 * @param turnSpeed    Percentage speed for turning, from -1 (left) to 1
	 *                     (right).
	 */
	public void arcadeDriveTeleop(double forwardSpeed, double turnSpeed) {
		turnSpeed = -turnSpeed; // turning is inverted on the robot
		differentialDrive.arcadeDrive(forwardSpeed, turnSpeed, true);
	}

	/**
	 * Sets the forward and turning speeds of the robot independently. USE WITH PID
	 * ONLY.
	 * <p>
	 * The arguments provided are not squared to prevent PID overcompensation.
	 * 
	 * @param forwardSpeed Percentage speed for driving forwards or backwards, from
	 *                     -1 to 1.
	 * @param turnSpeed    Percentage speed for turning, from -1 (left) to 1
	 *                     (right).
	 */
	public void arcadeDriveRaw(double forwardSpeed, double turnSpeed) {
		turnSpeed = -turnSpeed; // turning is inverted on the robot
		differentialDrive.arcadeDrive(forwardSpeed, turnSpeed, false);
	}

	/**
	 * Shuts off all drive motors and feeds watchdog timer.
	 */ //TODO redo documentation
	public void stopDrive() {
		differentialDrive.stopMotor();
	}

	public int getLeftEncoderTicks() {
		return leftEnc.get();
	}

	public int getRightEncoderTicks() {
		return rightEnc.get();
	}

	public void resetLeftEncoder() {
		leftEnc.reset();
	}

	public void resetRightEncoder() {
		rightEnc.reset();
	}

	/**
	 * Resets all drive encoders to 0 ticks.
	 * <p>
	 * Shorthand for {@code resetEncoderTicks(Side.LEFT)} and
	 * {@code resetEncoderTicks(Side.RIGHT)}.
	 */
	public void resetEncoders() {
		resetLeftEncoder();
		resetRightEncoder();
	}

	/**
	 * Gets current angle (yaw) that the robot is facing.
	 * 
	 * @return Double value representing angle in degrees, can fall outside the set
	 *         [0,360].
	 */
	public double getAngleDegrees() {
		return navx.getAngle();
	}

	public double getAngleRadians() {
		return navx.getAngle() * (Math.PI / 180.0);
	}

	/**
	 * Sets current robot angle to 0 degrees.
	 */
	public void resetAngle() {
		navx.zeroYaw();
	}

	public double getCompassHeading() {
		return navx.getCompassHeading();
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("subsystem-drivetrain");
		builder.addStringProperty("encoder-ticks", () -> {
			return (Integer.toString(getLeftEncoderTicks()) + " "
					+ Integer.toString(getRightEncoderTicks()));
		}, null);
		builder.addDoubleProperty("angle", () -> getAngleDegrees(), null);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new TeleopTankDrive());
	}
}