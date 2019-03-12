package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Contains all mathematical constants and calculations.
 */
public final class Constants {

	/**
	 * ID number ({@value}) of the main PCM, which has the compressor and pressure
	 * switch plugged in.
	 */
	public static final int PCM_1 = 1;

	/**
	 * ID number ({@value}) of the extra PCM, used only for extra solenoid ports.
	 */
	public static final int PCM_2 = 2;

	/**
	 * Profile number for any PID loop running on a Talon SRX.
	 * <p>
	 * Use in methods such as
	 * <code>configSelectedFeedbackSensor(FeedbackSensor.QuadEncoder, Constants.PID_ID, Constants.TIMEOUT_MS)</code>
	 */
	public static final int PID_ID = 0;

	/**
	 * How long a Talon PID loop waits for signals before terminating for safety.
	 * <p>
	 * Use in methods such as
	 * <code>configSelectedFeedbackSensor(FeedbackSensor.QuadEncoder, Constants.PID_ID, Constants.TIMEOUT_MS)</code>
	 */
	public static final int TIMEOUT_MS = 10;

	//camera
	public static final double PIXELS_H = 320;
	public static final double PIXELS_V = 240;
	public static final double RAD_H = 1.04;
	public static final double RAD_V = 0.867;
	public static final double PPR_H = PIXELS_H / RAD_H;
	public static final double PPR_V = PIXELS_V / RAD_V;

	// DO NOT USE THIS CONSTRUCTOR
	private Constants() {
		DriverStation.reportError("Do not create an Constants object!", false);
	}

}
