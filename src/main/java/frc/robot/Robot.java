package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.cargo.FindArmZero;
import frc.robot.commands.debug.DebugRebootAll;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.CargoPickupSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.HatchPickupSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.PneumaticSubsystem;
import frc.robot.subsystems.LineFollowerSubsystem;

/**
 * Main class of the Robot.
 */
public class Robot extends TimedRobot {

	public static final ArmSubsystem arm = new ArmSubsystem();
	public static final CameraSubsystem camera = new CameraSubsystem();
	public static final CargoPickupSubsystem cargoPickupWheels = new CargoPickupSubsystem();
	public static final ClimbSubsystem climb = new ClimbSubsystem();
	public static final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
	public static final ElevatorSubsystem elevator = new ElevatorSubsystem();
	public static final HatchPickupSubsystem hatchPickupWheels = new HatchPickupSubsystem();
	public static final LineFollowerSubsystem lineFollowers = new LineFollowerSubsystem();
	public static final PneumaticSubsystem pneumatics = new PneumaticSubsystem();
	public static final LauncherSubsystem launchers = new LauncherSubsystem();

	/** Reference this to get input from the joysticks and Xbox controller. */
	public static ControlHandler input;

	@Override
	public void robotInit() {
		input = new ControlHandler();
		SmartDashboard.putData(arm);
		SmartDashboard.putData(camera);
		SmartDashboard.putData(cargoPickupWheels);
		SmartDashboard.putData(climb);
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(elevator);
		SmartDashboard.putData(hatchPickupWheels);
		SmartDashboard.putData(lineFollowers);
		SmartDashboard.putData(pneumatics);
		SmartDashboard.putData(launchers);
		climb.resetEncoder();
	}

	@Override
	public void robotPeriodic() {
		elevator.resetEncoderWhenFloored();
	}

	@Override
	public void disabledInit() {
		DebugRebootAll.rebootAll();
		Scheduler.getInstance().removeAll();
	}

	@Override
	public void disabledPeriodic() {
		camera.setPipeline(CameraSubsystem.PIPELINE_DRIVER);
	}

	@Override
	public void autonomousInit() {
		Scheduler.getInstance().removeAll();
		Scheduler.getInstance().add(new FindArmZero());
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		Scheduler.getInstance().removeAll();
		Scheduler.getInstance().add(new FindArmZero()); // TODO remove at comp
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testInit() {
		Scheduler.getInstance().removeAll();
	}

	@Override
	public void testPeriodic() {
		Scheduler.getInstance().run();
	}

}