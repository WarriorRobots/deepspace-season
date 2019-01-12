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
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.FeedSubsystem;
import frc.robot.subsystems.PickupSubsystem;
import frc.robot.subsystems.PneumaticSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.util.AutonomoSelector;
import frc.robot.util.DashboardHandler;

/**
 * Main class of the Robot.
 */
public class Robot extends TimedRobot {
	
	public static final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
	public static final ShooterSubsystem shooter = new ShooterSubsystem();
	public static final PickupSubsystem pickup = new PickupSubsystem();
	public static final FeedSubsystem feed = new FeedSubsystem();
	public static final PneumaticSubsystem pneumatics = new PneumaticSubsystem();
	public static final CameraSubsystem sensors = new CameraSubsystem();
	public static final ClimbSubsystem climb = new ClimbSubsystem();
	public static ControlHandler oi;
	
	
	@Override
	public void robotInit() {
		oi = new ControlHandler();
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(pneumatics);
		SmartDashboard.putData(sensors);
	}		

	@Override
	public void disabledInit() {
		DashboardHandler.getInstance().init();
		Scheduler.getInstance().removeAll();
	}
	
	@Override
	public void autonomousInit() {
		Scheduler.getInstance().removeAll();
		AutonomoSelector.getInstance().selectAutoCase();
		AutonomoSelector.getInstance().startAuto();
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