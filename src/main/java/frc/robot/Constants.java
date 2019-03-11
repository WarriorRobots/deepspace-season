/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Contains all mathematical constants and calculations.
 */
public final class Constants {

	// DO NOT USE THIS CONSTRUCTOR
	private Constants() {
		DriverStation.reportError("Do not create an Constants object!", false);
	}

	public static final double PIXELS_H = 320;
	public static final double PIXELS_V = 240;
	public static final double RAD_H = 1.04;
	public static final double RAD_V = 0.867;
	public static final double PPR_H = PIXELS_H / RAD_H;
	public static final double PPR_V = PIXELS_V / RAD_V;

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

	public static final double ftToM(double ftPerSec) {
		return ftPerSec * 0.3048;
	}

	public static final double mToFt(double mPerSec) {
		return mPerSec * (1 / 0.3048);
	}

	/**
	 * All gearbox ratios of important non-driving moters All ratios should be in
	 * terms of inner rotations to outer rotations
	 */
	public static final class gearbox {

		/**
		 * Cargo intake 10:1 in:out
		 */
		public static final double intakeCargo = 10;

		/**
		 * Cargo arm 300:1 in:out
		 */
		public static final double arm = 300;

		/**
		 * Hatch intake 10:1 in:out
		 */
		public static final double intakeHatch = 10;

		/**
		 * Elevator 50:1 in:out
		 */
		public static final double elevator = 50;

	}

}
