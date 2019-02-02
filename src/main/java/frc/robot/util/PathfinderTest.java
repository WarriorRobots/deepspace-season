package frc.robot.util;

import java.io.File;

import edu.wpi.first.wpilibj.Filesystem;
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
        Trajectory.Config config = new Trajectory.Config(
            Trajectory.FitMethod.HERMITE_CUBIC,
            Trajectory.Config.SAMPLES_HIGH,
            0.02,
            Constants.ftToM(maxVel),
            Constants.ftToM(6),
            Constants.ftToM(30)
        );
        // 26.5 inches to m = 0.6731

        File rightP = new File("/home/lvuser/deploy/paths/GoForward.left.pf1.csv");
        File leftP = new File("/home/lvuser/deploy/paths/GoForward.right.pf1.csv");

        left = new EncoderFollower(Pathfinder.readFromCSV(leftP));
        right = new EncoderFollower(Pathfinder.readFromCSV(rightP));
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
        left.configurePIDVA(0.00, 0.0, 0.0, 1 / maxVel, 0);
        right.configurePIDVA(0.00, 0.0, 0.0, 1 / maxVel, 0);
    }

    public double calcLeft() {
        double l = left.calculate(Robot.drivetrain.getEncoderTicks(RobotSide.LEFT));
        double gyro_heading = -Robot.drivetrain.getAngle();    // Assuming the gyro is giving a value in degrees
        double desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees
        double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
        double turn = 0.8 * (-1.0/80.0) * angleDifference;
        return l + turn; 
    }

    public double calcRight() {
        double r = right.calculate(Robot.drivetrain.getEncoderTicks(RobotSide.RIGHT));
        double gyro_heading = -Robot.drivetrain.getAngle();   // Assuming the gyro is giving a value in degrees
        double desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees
        double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
        double turn = 0.8 * (-1.0/80.0) * angleDifference;
        return r - turn;
    }

}