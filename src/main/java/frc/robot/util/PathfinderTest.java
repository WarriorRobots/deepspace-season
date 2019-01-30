package frc.robot.util;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.enums.RobotSide;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class PathfinderTest {
    
    final double maxVel = 5.9;

    EncoderFollower left, right;

    public PathfinderTest() {
        Waypoint[] points = new Waypoint[] {
            new Waypoint(0, 4, 0),
            new Waypoint(0, -4, 0)
        };
        Trajectory.Config config = new Trajectory.Config(
            Trajectory.FitMethod.HERMITE_CUBIC,
            Trajectory.Config.SAMPLES_HIGH,
            0.02,
            Constants.ftToM(maxVel),
            Constants.ftToM(6),
            Constants.ftToM(30)
        );
        Trajectory trajectory = Pathfinder.generate(points, config);
        // 26.5 inches to m = 0.6731
        TankModifier modifier = new TankModifier(trajectory).modify(0.6731);
        left = new EncoderFollower(modifier.getLeftTrajectory());
        right = new EncoderFollower(modifier.getRightTrajectory());
        left.configureEncoder(
            Robot.drivetrain.getEncoderTicks(RobotSide.LEFT),
            128,
            0.1524 // 6 inches to meters
        );
        right.configureEncoder(
            Robot.drivetrain.getEncoderTicks(RobotSide.RIGHT),
            128,
            0.1524 // 6 inches to meters);
        );
        right.configurePIDVA(1.0, 0.0, 0.0, 1 / maxVel, 0);
        right.configurePIDVA(1.0, 0.0, 0.0, 1 / maxVel, 0);
    }

    public double calcLeft() {
        double l = left.calculate(Robot.drivetrain.getEncoderTicks(RobotSide.LEFT));
        double gyro_heading = Robot.drivetrain.getAngle();    // Assuming the gyro is giving a value in degrees
        double desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees
        double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
        double turn = 0.8 * (-1.0/80.0) * angleDifference;
        return l + turn;
    }

    public double calcRight() {
        double r = right.calculate(Robot.drivetrain.getEncoderTicks(RobotSide.RIGHT));
        double gyro_heading = Robot.drivetrain.getAngle();   // Assuming the gyro is giving a value in degrees
        double desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees
        double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
        double turn = 0.8 * (-1.0/80.0) * angleDifference;
        return r - turn;
    }

}