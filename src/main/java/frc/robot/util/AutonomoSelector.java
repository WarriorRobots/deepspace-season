package frc.robot.util;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.TeleopTankDrive;

/**
 * Contains methods that select the Autonomous case of the robot.
 * Place these methods inside autonomousInit() of Robot.java
 */ // TODO joysticks take priority
 //TODO documentation
public class AutonomoSelector {

	private static Command autoCommand = null;
	
	// Do not use this constructor
	private AutonomoSelector() throws Exception {
		throw new Exception("Do not create an AutonomoSelector object, call it statically");
	}
	
	//TODO write this
	public static void selectDebugSequence() {
		// autoCommand = new TestCase();
	}
	
	public static void stopAuto() {
		autoCommand.cancel();
	}
	
	/**
	 * Selects autonomous case and calls {@code start()} on the chosen {@code CommandGroup}.
	 */
	public static void selectAutoSequence() {
		// write logic to select Autonomous sequence, probably using dashboard
	}
	
	public static void start() {
		try {
			autoCommand.start();
		} catch (Exception e) {
			DriverStation.reportError("No autonomous was selected, defaulting to teleop", false);
			autoCommand = new TeleopTankDrive();
			autoCommand.start();
		}
	}

}