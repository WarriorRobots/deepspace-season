package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * Contains methods used for reading three line followers; two on the outside of
 * a 2-inch line and one inside.
 */
public class LineFollowerSubsystem extends Subsystem {

	private static final int LEFT_PORT = 7;
	private static final int MIDDLE_PORT = 8;
	private static final int RIGHT_PORT = 9;

	// DigitalInput is the class that reads any digital data,
	// in this case line followers
	private DigitalInput leftFollower, middleFollower, rightFollower;

	public enum Direction {
		WEAKLEFT, STRONGLEFT, WEAKRIGHT, STRONGRIGHT, STRAIGHT, STOP
	}

	/**
	 * Instantiates new subsystem; make ONLY ONE.
	 * <p>
	 * <code> public static final LineFollowerSubsystem lineFollowers = new
	 * LineFollowerSubsystem();
	 */
	public LineFollowerSubsystem() {
		leftFollower = new DigitalInput(LEFT_PORT);
		middleFollower = new DigitalInput(MIDDLE_PORT);
		rightFollower = new DigitalInput(RIGHT_PORT);
	}

	/**
	 * Returns true if left follower sees a white line, false otherwise.
	 */
	public boolean getLeftLineFollower() {
		return !leftFollower.get(); // inverted because it reads white as false
	}

	/**
	 * Returns true if middle follower sees a white line, false otherwise.
	 */
	public boolean getMiddleLineFollower() {
		return !middleFollower.get(); // inverted because it reads white as false
	}

	/**
	 * Returns true if right follower sees a white line, false otherwise.
	 */
	public boolean getRightLineFollower() {
		return !rightFollower.get(); // inverted because it reads white as false
	}

	/*
	 * Return true when any of the line followers are touching a line.
	 */
	public boolean touchingLine() {
		return (getLeftLineFollower() || getMiddleLineFollower() || getRightLineFollower());
	}

	public Direction getCorrection() { // @formatter:off
		boolean l = getLeftLineFollower();
		boolean m = getMiddleLineFollower();
		boolean r = getRightLineFollower();
		if ( (l && m && r) || (l && !m && r) ) { return Direction.STOP; }
		if (!l && !m && !r) { return Direction.STRAIGHT; }
		if (!l && m && !r) { return Direction.STRAIGHT; }
		if (l && m && !r) { return Direction.WEAKLEFT; }
		if (l && !m && !r) { return Direction.STRONGLEFT; }
		if (!l && m && r) { return Direction.WEAKRIGHT; }
		if (!l && !m && r) { return Direction.STRONGRIGHT; }
		// else
		return Direction.STOP;
		// @formatter:on
	}

	@Override
	protected void initDefaultCommand() {
		// none
	}

	public void initSendable(SendableBuilder builder) {
		// builder.addBooleanProperty("left", () -> getLeftLineFollower(), null);
		// builder.addBooleanProperty("mid", () -> getMiddleLineFollower(), null);
		// builder.addBooleanProperty("right", () -> getRightLineFollower(), null);
	}

}