/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.util.DashboardHandler;

/**
 * Main class of the Robot.
 */
public class Robot extends TimedRobot {
	
	public static final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
	public static ControlHandler input;

	@Override
	public void robotInit() {
		input = new ControlHandler();
		SmartDashboard.putData(drivetrain);
	}

	@Override
	public void disabledInit() {
		Scheduler.getInstance().removeAll();
	}
	
	@Override
	public void autonomousInit() {
		Scheduler.getInstance().removeAll();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		Scheduler.getInstance().removeAll();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

}