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
	 * 0.3048 meters per foot
	 */
	public static double ftToM(double ftPerSec) {
		return ftPerSec * 0.3048; //TODO documentation
	}

	public static double mToFt(double mPerSec) {
		return mPerSec * (1/0.3048);
	}

	/**
	 * Contains booleans that define whether certain motor or encoder polarities are reversed.
	 */
	public static final class Inversions {
		public static final boolean LEFT_ENCODER_REVERSED = false;
		public static final boolean RIGHT_ENCODER_REVERSED = true;
	}
}
