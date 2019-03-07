package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;

/** Anything you need to access and edit quickly */
public final class QuickAccessVars {

	// in inches from the floor
	public static final double ELEVATOR_SAFE_MINIMUM = 5;
	public static final double LVL1_HEIGHT = 27;
	public static final double LVL2_HEIGHT = 47;
	public static final double LVL3_HEIGHT = 74;
	public static final double SCISSORS_HEIGHT = 13;

	// motor speeds (-1.0 to 1.0)
	public static final double ARCADE_FORWARD_MODIFIER = 0.5;
	public static final double ARCADE_TURN_MODIFIER = 0.75;
	public static final double DROP_ELEVATOR_SPEED = -1;
	public static final double CARGO_PICKUP_IDLE_SPEED = 0.1;
	public static final double ELEVATOR_BELOW_MINIMUM_DRIFT_SPEED = -0.05;

	// degrees
	public static final double ARM_PICKUP_ANGLE = 90;
	public static final double ARM_CLIMB_ANGLE = 155;

	// delay timers (seconds)
	public static final double PLACE_HATCH_DELAY = 0.2; // seconds
	public static final double DRIVETRAIN_RAMPRATE = 0.25; // how long will it take for the drive motors to
																	// switch directions?
	public static final double HATCH_PICKUP_DELAY = 0.3;

	// settings
	public static final boolean ARM_ROTATOR_CLONE_INVERTED = false;
	public static final boolean CARGO_PICKUP_WHEELS_INVERTED = false;
	public static final boolean HATCH_PICKUP_WHEELS_INVERTED = true;
	public static final boolean ELEVATOR_WINCH_INVERTED = true;
	public static final boolean ELEVATOR_ENCODER_INVERTED = false;
	public static final boolean HATCH_LAUNCH_SAFETY = false; // true means line follower required
	public static final double TURNLOCK_THRESHOLD = 0.2;
	public static final double PNEUMATIC_LOOP_COUNT = 5; // how many loops will a pneumatic command run?

	// pid
	public static final double ELEVATOR_P = 0.4;
	public static final double ARM_P = 1;

	// DO NOT USE THIS CONSTRUCTOR
	private QuickAccessVars() {
		DriverStation.reportError("Do not create a QuickAccessVars object!", false);
	}

}
