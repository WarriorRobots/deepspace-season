/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Contains all values used program-wide or constants that need to be changed
 * easily.
 */
public final class Constants {
	// Do not use this constructor
	private Constants() throws Exception {
		throw new Exception("Do not create an Constants object!");
	}

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

	public static final double WHEEL_DIAMETER = 6;
	public static final int CLICKS_PER_REVOLUTION = 128;
	/** {@value} */
	public static final double INCHES_PER_CLICK = (WHEEL_DIAMETER * Math.PI) / CLICKS_PER_REVOLUTION;

	public static final double clicksToDegrees(double clicks) {
		return clicks * (360 / CLICKS_PER_REVOLUTION);
	}

	public static final double clicksToRadians(double clicks) {
		return clicks * (2 * Math.PI / CLICKS_PER_REVOLUTION);
	}

	public static final double clicksToInches(int clicks) {
		return ((double) clicks) * INCHES_PER_CLICK;
	}

	public static final int inchesToClicks(double inches) {
		return (int) (inches / INCHES_PER_CLICK);
	}

	public static final double ftToM(double ftPerSec) {
		return ftPerSec * 0.3048; // TODO documentation
	}

	public static final double mToFt(double mPerSec) {
		return mPerSec * (1 / 0.3048);
	}

	/**
	 * Contains booleans that define whether certain motor or encoder polarities are
	 * reversed.
	 */
	public static final class Inversions {
		/** {@value} */
		public static final boolean LEFT_ENCODER_REVERSED = true;
		/** {@value} */
		public static final boolean RIGHT_ENCODER_REVERSED = false;
		/** {@value} */
		public static final boolean LEFT_DRIVE_REVERSED = true;
		/** {@value} */
		public static final boolean RIGHT_DRIVE_REVERSED = true;
	}
}
