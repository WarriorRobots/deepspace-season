/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.CargoPickupSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.HatchPickupSubsystem;
import frc.robot.subsystems.PneumaticSubsystem;
import frc.robot.subsystems.LineFollowerSubsystem;

/**
 * Main class of the Robot.
 */
public class Robot extends TimedRobot {

	public static final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
	public static final HatchPickupSubsystem hatchPickupWheels = new HatchPickupSubsystem();
	public static final PneumaticSubsystem pneumatics = new PneumaticSubsystem();
	public static final ElevatorSubsystem elevator = new ElevatorSubsystem();
	public static final ClimbSubsystem climb = new ClimbSubsystem();
	public static final CameraSubsystem camera = new CameraSubsystem();
	public static final LineFollowerSubsystem lineFollowers = new LineFollowerSubsystem();
	public static final ArmSubsystem arm = new ArmSubsystem();
	public static final CargoPickupSubsystem cargoPickupWheels = new CargoPickupSubsystem();

	/** Reference this to get input from our joysticks. */
	public static ControlHandler input;

	private Timer t; // TODO remove debug code later

	@Override
	public void robotInit() {
		input = new ControlHandler();
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(elevator);
		SmartDashboard.putData(climb);
		SmartDashboard.putData(hatchPickupWheels);
		SmartDashboard.putData(pneumatics);
		SmartDashboard.putData(lineFollowers);
		SmartDashboard.putData(arm);
		SmartDashboard.putData(cargoPickupWheels);
		t = new Timer();
		t.start();
	}

	@Override
	public void robotPeriodic() {
		elevator.loop();
		//System.out.println(t.get());
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