package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;

/** Anything you need to access and edit quickly */ // @formatter:off
public final class QuickAccessVars {

	// in inches from the floor
	public static final double ELEVATOR_MINIMUM_TARGET = 0.5;
	public static final double ELEVATOR_MAXIMUM_TARGET = 75;
	public static final double ADJUST_ELEVATOR_BY = 4;
	public static final double CARGO_SCORING_HEIGHT = 29;
	public static final double LVL1_HEIGHT = 20.5;
	public static final double LVL2_HEIGHT = 47;
	public static final double LVL3_HEIGHT = 74;
	public static final double SCISSORS_HEIGHT = 13;
	public static final double ELEVATOR_DOWNWARD_DRIFT_THRESHOLD = 10;
	public static final double CLIMB_MINIMUM_TARGET = -22;
	public static final double CLIMB_MAXIMUM_TARGET = 0;
	public static final double ADJUST_CLIMB_BY = 0.5;
	public static final double CLIMB_TARGET_HEIGHT = -20;

	// motor speeds (-1.0 to 1.0)
	public static final double ARCADE_FORWARD_MODIFIER = 0.5;
	public static final double ARCADE_TURN_MODIFIER = 0.75;
	public static final double ELEVATOR_DROP_SPEED = -1;
	public static final double CARGO_PICKUP_IDLE_SPEED = 0.1;
	public static final double ELEVATOR_DOWNWARD_DRIFT_SPEED = -0.05;
	public static final double ARM_RESET_SPEED = -0.2;

	// degrees
	public static final double ARM_MINIMUM_ANGLE = 0;
	public static final double ARM_MAXIMUM_ANGLE = 160;
	public static final double ARM_PICKUP_CARGO_ANGLE = 90;
	public static final double ARM_CLIMB_ANGLE = 145;
	public static final double ARM_RESET_ANGLE = -3;

	// delay timers (seconds)
	public static final double PLACE_HATCH_DELAY = 0.2; // seconds
	public static final double HATCH_PICKUP_DELAY = 0.3;
	public static final double DRIVETRAIN_RAMPRATE = 0.25;
		// how long will it take for the drive motors to switch directions?

	// settings
	public static final boolean CLIMB_WINCH_INVERTED = true;
	public static final boolean CLIMB_ENCODER_INVERTED = true;
	public static final boolean ARM_ROTATOR_INVERTED = true;
	public static final boolean ARM_ROTATOR_CLONE_INVERTED = false;
	public static final boolean ARM_ENCODER_INVERTED = false;
	public static final boolean CARGO_PICKUP_WHEELS_INVERTED = false;
	public static final boolean HATCH_PICKUP_WHEELS_INVERTED = true;
	public static final boolean ELEVATOR_WINCH_INVERTED = true;
	public static final boolean ELEVATOR_ENCODER_INVERTED = false;
	public static final boolean HATCH_LAUNCH_SAFETY = false; // true means line follower required
	public static final double TURNLOCK_THRESHOLD = 0.2;
	public static final double PNEUMATIC_LOOP_COUNT = 5; // how many loops will a pneumatic command run?
	public static final double XBOX_JOYSTICK_THRESHOLD = 0.7;
		// this seems high, but the xbox joysticks reach 1 before hitting the edge
	public static final double LINEAR_CONTROLS_MODIFIER = 0.5;
		// how much are the linear elevator/arm/climb commands modified by?

	// camera
	public static final double CAMERA_TILT = 22 * Math.PI / 180; // TODO put back all camera constant comments
	public static final double ELEVATION = 40;
	public static final double TARGET_ELEVATION = 25;
	public static final double PIXELS_H = 320;
	public static final double PIXELS_V = 240;
	public static final double RAD_H = 1.04;
	public static final double RAD_V = 0.867;
	public static final double PPR_H = PIXELS_H / RAD_H;
	public static final double PPR_V = PIXELS_V / RAD_V;
	public static final double TARGET_HEIGHT = 5.83;
	public static final double CAMERA_DRIVE_THRESHOLD = 0.2;
		// how far does the driver push the joystick to activate arcade drive during ApproachCurve

	// pid
	public static final double ELEVATOR_P = 0.4;
	public static final double ARM_P = 1.2;
	public static final double CLIMB_P = 0.4;

	// camera pid
	public static final double KP_APPROACH = 0.015;
	public static final double KI_APPROACH = 0;
	public static final double KD_APPROACH = 0;
	public static final double SETPOINT_APPROACH = 23;
	public static final double TOLERANCE_APPROACH = 2;
	public static final double KP_CENTER = 0.045;
	public static final double KI_CENTER = 0;
	public static final double KD_CENTER = 0;
	public static final double SETPOINT_CENTER = 0;

	// DO NOT USE THIS CONSTRUCTOR
	private QuickAccessVars() {
		DriverStation.reportError("Do not create a QuickAccessVars object!", false);
	}

}
