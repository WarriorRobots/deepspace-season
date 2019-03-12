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
	private boolean getLeftLineFollower() {
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
	private boolean getRightLineFollower() {
		return !rightFollower.get(); // inverted because it reads white as false
	}

	/**
	 * Returns true when properly lined up (the middle line follower should detect a line, and the
	 * other two should not).
	 */
	public boolean onCenter() {
		return (!getLeftLineFollower() && getMiddleLineFollower() && !getRightLineFollower());
	}

	/**
	 * Returns true if ANY line follower detects a line.
	 */
	public boolean onLine() {
		return (getLeftLineFollower() || getRightLineFollower() || getMiddleLineFollower());
	}

	/**
	 * Returns true if robot is too far right of the line (the left follower detects a line)
	 */
	public boolean onLeftOfLine() {
		return getRightLineFollower();
	}

	/**
	 * Returns true if robot is too far left of the line (the right follower detects a line).
	 */
	public boolean onRightOfLine() {
		return getLeftLineFollower();
	}

	@Override
	protected void initDefaultCommand() {
		// none
	}

	public void initSendable(SendableBuilder builder) {
		builder.addBooleanProperty("left", () -> getLeftLineFollower(), null);
		builder.addBooleanProperty("mid", () -> getMiddleLineFollower(), null);
		builder.addBooleanProperty("right", () -> getRightLineFollower(), null);
	}

}