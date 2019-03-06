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
	
	// DO NOT USE THIS CONSTRUCTOR
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

	/** Number of encoder clicks of a Vex encoder per revolution */
	public static final int VEX_PER_REV = 1024;


	/**
	 * @param ratio gear ratio in/out
	 * @see gearbox
	 */
	public static final double vexDegreesToClicks(double degrees, double ratio) {
		// degrees * rev/deg * enc/rev * in/out
		return degrees / 360 * VEX_PER_REV * ratio;
	}
	/**
	 * @param ratio gear ratio in/out
	 * @see gearbox
	*/
	public static final double vexClicksToDegrees(double clicks, double ratio) {
		// encoder * deg/rev * rev/enc * out/in
		return clicks * 360 / VEX_PER_REV / ratio;
	}

	public static final double ftToM(double ftPerSec) {
		return ftPerSec * 0.3048; // TODO documentation
	}

	public static final double mToFt(double mPerSec) {
		return mPerSec * (1 / 0.3048);
	}

	/**
	 * All gearbox ratios of important non-driving moters
	 * All ratios should be in terms of inner rotations to outer rotations
	 */
	public static final class gearbox {
		
		/**
		 * Cargo intake
		 * 10:1 in:out
		 */
		public static final double intakeCargo = 10;

		/**
		 * Cargo arm
		 * 300:1 in:out 
		 */
		public static final double arm = 300;

		/**
		 * Hatch intake
		 * 10:1 in:out
		 */
		public static final double intakeHatch = 10;

		/**
		 * Elevator
		 * 50:1 in:out
		 */
		public static final double elevator = 50;

	}

	// TODO move to QuickAccess
	/** Contains PID constants used for autonomous closed-loop control. */
	public static final class AutoDrive {

		/** kP value for {@link ApproachWall#PIDapproach} */
		public static final double KP_APPROACH = 0.020;
		/** */
		public static final double KI_APPROACH = 0;
		/** */
		public static final double KD_APPROACH = 0;
		/** SetPoint value for {@link ApproachWall#PIDapproach} */
		public static final double SETPOINT_APPROACH = 41;
		/** Tolerance value for {@link ApproachWall#PIDapproach} */
		public static final double TOLERANCE_APPROACH = 2;


		/** kP value for {@link ApproachWall#PIDcenter} */
		public static final double KP_CENTER = 0.025;
		/** */
		public static final double KI_CENTER = 0;
		/** */
		public static final double KD_CENTER = 0;
		/** SetPoint value for {@link ApproachWall#PIDcenter} */
		public static final double SETPOINT_CENTER = 0;
		//public static final double TOLERANCE_CENTER = ??;

	}

	// TODO move to QuickAccess
	/**
	 * Contains values and methods related to the camera.
	 * Conversions of degrees and radians unincluded.
	 */
	public static final class Camera {

		/** Angle the camera is tilted downards in radians, 40 degrees */
		public static final double CAMERA_TILT = 9 *Math.PI/180;
		/** Height the lens is off of the ground in inches*/
		public static final double ELEVATION = 47;

		// this applies to most but not all targets
		/** Height between the bottom of the target and the ground in inches */
		public static final double TARGET_ELEVATION = 25;

		/** Values applicable only to Ll2 */
		public static final class Limelight2 {

			/** Horizontal FOV in pixels */
			public static final double PIXELS_H = 320;
			/** Vertical FOV in pixels */
			public static final double PIXELS_V = 240;
			
			// 59.6 degrees
			/** Horizontal FOV in radians */
			public static final double RAD_H = 1.04;
			// 49.7 degrees
			/** Vertical FOV in radians */
			public static final double RAD_V = 0.867;

			/** Pixels per radian horizontally; {@value} pixels/rad */
			public static final double PPR_H = PIXELS_H/RAD_H;
			/** Pixels per radian vertically; {@value} pixels/rad */
			public static final double PPR_V = PIXELS_V/RAD_V;

		}

		/*
		Height	= 0.5in + arbitrary side

		(5.5in)^2			= arbitrary side ^2 + (1.35in)^2	// 1.35 is from field sketch
		arbitrary side ^2	= 30.25 in^2 - 1.8225 in^2
		arbitrary side		= sqrt (28.4275)in

		Height	= 0.5in + sqrt(28.4275)in
		Height	~ 5.83in
		 */
		/** Vision target height in inches */
		public static final double TARGET_HEIGHT = 5.83;
		
	}

}
