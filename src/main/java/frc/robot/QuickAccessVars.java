package frc.robot;

/** Anything you need to access and edit quickly */
public final class QuickAccessVars {

	// in inches from the floor
	public static final double LVL1_HEIGHT = 27;
	public static final double LVL2_HEIGHT = 47;
	public static final double LVL3_HEIGHT = 74;

	// motor speeds
	public static final double ARCADE_FORWARD_MODIFIER = 0;
	public static final double ARCADE_TURN_MODIFIER = 0;
	public static final double DROP_ELEVATOR_SPEED = -0.8;

	// degrees
	public static final double ARM_EXTENSION_ANGLE = 75;

	// settings
	public static final boolean ARM_ROTATOR_CLONE_INVERTED = false;
	public static final boolean HATCH_LAUNCH_SAFETY = false;

	// pid
	public static final double ELEVATOR_P = 0.4;
	public static final double ARM_P = 1;

	// DO NOT USE THIS CONSTRUCTOR
	private QuickAccessVars() throws Exception {
		throw new Exception("Do not create a QuickAccessVars object!");
	}

}
