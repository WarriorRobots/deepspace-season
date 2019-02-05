package frc.robot.util;

import java.io.File;

import frc.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.followers.EncoderFollower;

public class PathfinderTest {
    
    final double maxVel = 5.9;

    EncoderFollower left, right;

    public PathfinderTest() {

        File rightP = new File("/home/lvuser/deploy/paths/GoForward.left.pf1.csv");
        File leftP = new File("/home/lvuser/deploy/paths/GoForward.right.pf1.csv");

        left = new EncoderFollower(Pathfinder.readFromCSV(leftP));
        right = new EncoderFollower(Pathfinder.readFromCSV(rightP));
        left.configureEncoder(
            Robot.drivetrain.getLeftEncoderClicks(),
            128,
            0.1524 // 6 inches to meters
        );
        right.configureEncoder(
            Robot.drivetrain.getRightEncoderClicks(),
            128,
            0.1524 // 6 inches to meters);
        );
        left.configurePIDVA(0.00, 0.0, 0.0, 1 / maxVel, 0);
        right.configurePIDVA(0.00, 0.0, 0.0, 1 / maxVel, 0);
    }

    public double calcLeft() {
        double l = left.calculate(Robot.drivetrain.getLeftEncoderClicks());
        double gyro_heading = -Robot.drivetrain.getAngleDegrees();    // Assuming the gyro is giving a value in degrees
        double desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees
        double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
        double turn = 0.8 * (-1.0/80.0) * angleDifference;
        return l + turn; 
    }

    public double calcRight() {
        double r = right.calculate(Robot.drivetrain.getRightEncoderClicks());
        double gyro_heading = -Robot.drivetrain.getAngleDegrees();   // Assuming the gyro is giving a value in degrees
        double desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees
        double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
        double turn = 0.8 * (-1.0/80.0) * angleDifference;
        return r - turn;
    }

}