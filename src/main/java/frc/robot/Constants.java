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

	public static final class PathfinderValues {
		// TODO tune these values or move them into the Pathfinder object
		public static final double PATHFINDER_P = 0;
		public static final double PATHFINDER_I = 0;
		public static final double PATHFINDER_D = 0;
	}

	public static final double WHEEL_DIAMETER = 6;
	public static final int CLICKS_PER_REVOLUTION = 128;
	/** {@value} */
	public static final double INCHES_PER_CLICK = (WHEEL_DIAMETER * Math.PI) / CLICKS_PER_REVOLUTION;

	public static double ClicksToInches(int clicks) {
		return ((double) clicks) * INCHES_PER_CLICK;
	}

	public static int InchesToClicks(double inches) {
		return (int) (inches / INCHES_PER_CLICK);
	}

	/**
	 * Contains values that are multiplied by joystick input to slow the robot.
	 */
	public static final class DriveScalars {
		// see ArcadeDriveAlignment
		public static final double ALIGNMENT_FORWARDSPEED = 0.5;
		public static final double ALIGNMENT_TURNSPEED = 0.6;
		// see TankDriveTurnLock
		public static final double LOCKMODE_TOLERANCE = 0.2;

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
