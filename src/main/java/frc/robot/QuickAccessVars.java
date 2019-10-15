package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;

/** Anything you need to access and edit quickly */ // @formatter:off
public final class QuickAccessVars {

	// in inches from the floor
	public static final double LVL1_HEIGHT = 20.5;
	public static final double LVL2_HEIGHT = 42;
	public static final double LVL3_HEIGHT = 63;
	public static final double CARGO_BAY_HEIGHT = 34; // CARGO_SCORING_HEIGHT + ELEVATOR_DELTA
	public static final double ELEVATOR_CLIMB_HEIGHT_HAB3 = 32; // CARGO_SCORING_HEIGHT used during climbing
	public static final double LOCK_AND_RAISE_HEIGHT = 23.5;
	public static final double ELEVATOR_DOWNWARD_DRIFT_THRESHOLD = 13; // 10 original
	public static final double ELEVATOR_MINIMUM_TARGET = 0.5;
	public static final double ELEVATOR_MAXIMUM_TARGET = 63; // 76 relative to floor
	public static final double ELEVATOR_BASE_HEIGHT = 13;
	
	public static final double CLIMB_TARGET_HAB2 = -6; // -7 originally
	public static final double CLIMB_TARGET_HAB3 = -20;
	public static final double CLIMB_DELTA = 0.5;
	public static final double CLIMB_MINIMUM_TARGET = -22;
	public static final double CLIMB_MAXIMUM_TARGET = 0;
	
	// degrees
	public static final double ARM_DEFAULT_ANGLE = 2;
	public static final double ARM_MINIMUM_ANGLE = -3;
	public static final double ARM_MAXIMUM_ANGLE = 157;
	public static final double ARM_PICKUP_CARGO_ANGLE = 90;
	public static final double ARM_CLIMB_ANGLE = 151;
	public static final double ARM_RESET_ANGLE = -3;
	public static final double ARM_EMERGENCY_CLIMB_DELTA = 5;

	// motor speeds (-1.0 to 1.0)
	public static final double ELEVATOR_DROP_SPEED = -1; // Deprecated
	public static final double ELEVATOR_DOWNWARD_DRIFT_SPEED = -0.05; // -0.15
	public static final double ELEVATOR_CLIMB_EXTRA = 0.07;
		// percent increase of distance the elevator goes when climbing (compared to the climb)
	public static final double CARGO_PICKUP_IDLE_SPEED = 0.15;
	public static final double ARM_RESET_SPEED = -0.3;
	public static final double ARCADE_FORWARD_MODIFIER = 1;
	public static final double ARCADE_TURN_MODIFIER = 1;

	// delay timers (seconds)
	public static final double PLACE_HATCH_DELAY = 0.2; // seconds
	public static final double HATCH_PICKUP_DELAY = 0.3;
	public static final double DRIVETRAIN_RAMPRATE = 0.25;
		// how long will it take for the drive motors to switch directions?

	// settings COMPTEST be careful
	public static final boolean ELEVATOR_WINCH_INVERTED = false;
	public static final boolean ELEVATOR_WINCH_CLONE_INVERTED = true;
	public static final boolean ELEVATOR_ENCODER_INVERTED = true;
	public static final boolean CLIMB_WINCH_INVERTED = true;
	public static final boolean CLIMB_ENCODER_INVERTED = true;
	public static final boolean ARM_ROTATOR_INVERTED = true;
	public static final boolean ARM_ROTATOR_CLONE_INVERTED = false;
	public static final boolean ARM_ENCODER_INVERTED = false;
	public static final boolean LEFT_DRIVE_REVERSED = false;
	public static final boolean RIGHT_DRIVE_REVERSED = false;
	public static final boolean LEFT_DRIVE_ENCODER_REVERSED = false;
	public static final boolean RIGHT_DRIVE_ENCODER_REVERSED = true;
	public static final boolean CARGO_PICKUP_WHEELS_INVERTED = false;
	public static final boolean HATCH_PICKUP_WHEELS_INVERTED = true;

	public static final boolean HATCH_LAUNCH_SAFETY = false; // true means line follower required
	public static final double TURNLOCK_THRESHOLD = 0.2;
	public static final double PNEUMATIC_LOOP_COUNT = 5; // how many loops will a pneumatic command run?
	public static final double XBOX_JOYSTICK_THRESHOLD = 0.7;
		// this seems high, but the xbox joysticks reach 1 before hitting the edge
	public static final double LINEAR_CONTROLS_MODIFIER = 1;
		// how much are the linear elevator/TreeUIarm/climb commands modified by?

	// camera
	// public static final double CAMERA_TILT = 22 * Math.PI / 180;
	public static final double ELEVATION = 40;
	public static final double TARGET_ELEVATION = 25;
	public static final double TARGET_HEIGHT = 5.83;
	public static final double TARGET_WIDTH = 14.64;
	public static final double CAMERA_DRIVE_THRESHOLD = 0.2;
		// how far does the driver push the joystick to activate arcade drive during ApproachCurve
	
	// pid
	public static final double ELEVATOR_P = 0.18; // 0.4
	public static final double ELEVATOR_CLIMB_P = 0.4; // P value for elevator for climbing
	public static final double ARM_P = 1.4; // 1.2
	public static final double ARM_I = 0; // 0.00015
	public static final double CLIMB_P = 0.4;
	
	// camera pid
	public static final double KP_APPROACH = 0.030; // 0.015
	public static final double KI_APPROACH = 0;
	public static final double KD_APPROACH = 0.08;
	public static final double SETPOINT_APPROACH = 15;
	public static final double TOLERANCE_APPROACH = 2; // in inches for leds
	public static final double KP_CENTER = 0.055; // 0.045
	public static final double KI_CENTER = 0;
	public static final double KD_CENTER = 0;
	public static final double SETPOINT_CENTER = 0;
	public static final double CAMERA_BIAS = 0.5;
	// amount of degrees added to the center the target when driving in
	
	// auto
	// wheel diameter in DrivetrainSubsystem
	// clicks per rev in DrivetrainSubsystem
	public static final double MAX_VELOCITY = 114; // inches/sec

	// auto pid
	public static final double KP_LEFTAUTO = 0.2;
	public static final double KI_LEFTAUTO = 0;
	public static final double KD_LEFTAUTO = 0.02;
	public static final double KV_LEFTAUTO = 1 / MAX_VELOCITY;
	public static final double KA_LEFTAUTO = 0.0175; // The acceleration term for left. Adjust this if you want to reach higher or lower speeds faster. 0.0 is the default
	public static final double KG_AUTO = 0.8 * (-1.0/80.0);

	public static final double KP_RIGHTAUTO = KP_LEFTAUTO;
	public static final double KI_RIGHTAUTO = KI_LEFTAUTO;
	public static final double KD_RIGHTAUTO = KD_LEFTAUTO;
	public static final double KV_RIGHTAUTO = KV_LEFTAUTO;
	public static final double KA_RIGHTAUTO = KA_LEFTAUTO; // The acceleration term for right. 0.0 is the default

	public static final double AUTO_TURN_P = 0.05;
	public static final double AUTO_TURN_I = 0;
	public static final double AUTO_TURN_D = 0.0;
	public static final double AUTO_TURN_TOLERANCE = 10; // degrees

	public static final double AUTO_DISTANCE_ANGLE_P = 0.05;
	public static final double AUTO_DISTANCE_P = 0.03;
	public static final double AUTO_DISTANCE_I = 0;
	public static final double AUTO_DISTANCE_D = 0;
	public static final double AUTO_DISTANCE_TOLERANCE = 2; // inches

	// DO NOT USE THIS CONSTRUCTOR
	private QuickAccessVars() {
		DriverStation.reportError("Do not create a QuickAccessVars object!", false);
	}

}
