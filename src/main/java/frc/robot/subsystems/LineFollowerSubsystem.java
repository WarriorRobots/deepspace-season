package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Contains methods used for reading three line followers;
 * two on the outside of a 2-inch line and one inside.
 * <p>When properly lined up, the middle line follower should read true,
 * and the other two should read false.
 */
public class LineFollowerSubsystem extends Subsystem {

    private static final int LEFT_PORT = 7;
    private static final int MIDDLE_PORT = 8;
    private static final int RIGHT_PORT = 9;

    private DigitalInput leftFollower, middleFollower, rightFollower;

    /**
     * Instantiates new subsystem; make ONLY ONE.
     * <p><code>
     * public static final LineFollowerSubsystem lineFollowers = new LineFollowerSubsystem();
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
        return leftFollower.get();
    }

    /**
     * Returns true if middle follower sees a white line, false otherwise.
     */
    private boolean getMiddleLineFollower() {
        return middleFollower.get();
    }

    /**
     * Returns true if right follower sees a white line, false otherwise.
     */
    private boolean getRightLineFollower() {
        return rightFollower.get();
    }

    /**
     * Returns true if left and right followers don't see line, and middle follower does; false otherwise;
     */
    public boolean onLine() {
        return (!getLeftLineFollower() && !getRightLineFollower())
                && getMiddleLineFollower();
    }

    /**
     * Returns true if robot is too far right of the line.
     */
    public boolean onLeftOfLine() {
        return getRightLineFollower();
    }

    /**
     * Returns true if robot is too far left of the line.
     */
    public boolean onRightOfLine() {
        return getLeftLineFollower();
    }

    //TODO determine if this needs a command
    @Override
    protected void initDefaultCommand() {}

}