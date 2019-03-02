package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * Contains methods used for reading three line followers; two on the outside of
 * a 2-inch line and one inside.
 * <p>
 * When properly lined up, the middle line follower should read true, and the
 * other two should read false.
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
    public boolean getLeftLineFollower() {
        return !leftFollower.get();
    }

    /**
     * Returns true if middle follower sees a white line, false otherwise.
     */
    public boolean getMiddleLineFollower() {
        return !middleFollower.get();
    }

    /**
     * Returns true if right follower sees a white line, false otherwise.
     */
    public boolean getRightLineFollower() {
        return !rightFollower.get();
    }

    @Override
    protected void initDefaultCommand() {}

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("line-follow-subsystem");
        builder.addBooleanProperty("left", () -> getLeftLineFollower(), null);
        builder.addBooleanProperty("middle", () -> getMiddleLineFollower(), null);
        builder.addBooleanProperty("right", () -> getRightLineFollower(), null);
    }

}