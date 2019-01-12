/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Contains all constants that are used in the program, grouped into subclasses.
 */
public final class Constants {
	
	/**
	 * Contains PID constants used for autonomous closed-loop control.
	 */
	//TODO tune pid
	public static final class AutonomoDrive {
		// autonomous driving forwards		
		public static final double DISTANCE_P = 0;
		public static final double DISTANCE_I = 0;
		public static final double DISTANCE_D = 0;
		public static final double DISTANCE_TOLERANCE = 0;
		// autonomous turning in place
		public static final double TURNING_P = 0;
		public static final double TURNING_I = 0;
		public static final double TURNING_D = 0;
		public static final double TURNING_TOLERANCE = 0;
		// autonomous preventing drift during driving
		public static final double COURSECORRECTION_P = 0;
		public static final double COURSECORRECTION_I = 0;
		public static final double COURSECORRECTION_D = 0;
		
		/**
		 * {@value}
		 */
		//TODO wheel size and encoder clicks
		public static final double INCHES_PER_CLICK = (5.0 * Math.PI) / 128.0;
		
		public static double ClicksToInches(int clicks) {
			return ((double) clicks) * INCHES_PER_CLICK;
		}
		
		public static int InchesToClicks(double inches) {
			return (int) (inches / INCHES_PER_CLICK);
		}
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
	 * Contains values and methods related to the shooter mechanism.
	 */
	public static final class ShooterRig {
		/**
		 * Conversion factor equal to 600*100ms / 4096clicks.
		 * Used for converting encoder clicks to revolutions per minute.
		 * <p> Equivalent to {@value}.
		 */
		public static final double MILS_PER_CLICK_RATIO = 600.0 / 4096.0; //0.1465
		
		/**
		 * Defines the gear box ratio (outer rotations per inner rotations).
		 * <p> Equivalent to {@value}.
		 */
		public static final double OUT_PER_IN_RATIO = 1.0 / 5.0;
		
		/**
		 * Convert raw encoder measurements to RPM.
		 * @param clicksPer100ms  velocity in clicks per 100ms
		 * @return velocity in revolutions per minute
		 */
	    public static double encoderClicksToRpm(double clicksPer100ms) {
			return clicksPer100ms * MILS_PER_CLICK_RATIO;
		}
	    
	    /**
		 * Convert RPM back to raw encoder measurements.
		 * @param revolutionsPerMinute  revolutions per minute
		 * @return velocity in encoder clicks per 100ms
		 */
	    public static double rpmToEncoderClicks(double revolutionsPerMinute) {
			return revolutionsPerMinute / MILS_PER_CLICK_RATIO;
		}
	    
	    /**
	     * Convert the rotation of the shooter into the rotations of the motor
	     * @param revolutions The outward shooter speed.
	     * @return The inward motor speed.
	     */
	    public static double gearboxShooterToMotor(double revolutions) {
	    	return revolutions / OUT_PER_IN_RATIO;
	    }
	    
	    /**
	     * Convert the rotation of the motor into the rotations of the shooter
	     * @param revolutions The inward motor speed.
	     * @return The outward shooter speed.
	     */
	    public static double gearboxMotorToShooter(double revolutions) {
	    	return revolutions * OUT_PER_IN_RATIO;
	    }
	}
	
	/**
	 * Contains booleans that define whether certain motor or encoder polarities are reversed.
	 */
	public static final class Inversions {
		public static final boolean LEFT_ENCODER_REVERSED = true;
		public static final boolean RIGHT_ENCODER_REVERSED = false;
	}
}
