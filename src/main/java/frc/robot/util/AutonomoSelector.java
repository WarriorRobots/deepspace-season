package frc.robot.util;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.util.annotations.Incomplete;

/**
 * Contains methods that select the Autonomous case of the robot. Place these
 * methods inside autonomousInit() of Robot.java
 */
@Deprecated // TODO write this
public class AutonomoSelector {

	/**
	 * This stores the autonomous sequence, which is chosen 
	 */
	private static Command autoCommand = null;

	@Deprecated
	private AutonomoSelector() throws Exception {
		throw new Exception("Do not create an AutonomoSelector object, call it statically");
	}

	/**
	 * Selects an autonomous case based on the data received by the robot.
	 */
	@Incomplete
	public static void selectAutoSequence() {
		// write logic to select Autonomous sequence, probably using dashboard
	}

	/**
	 * Selects the debug autonomous sequence.
	 */
	@Incomplete
	public static void selectDebugSequence() {
	}

	/**
	 * Begins the autonomous sequence immediately; this needs to run only once.
	 */
	public static void startAuto() {
		try {
			autoCommand.start();
		} catch (Exception e) {
			DriverStation.reportError("No autonomous was selected, defaulting to teleop", false);
		}
	}

	/**
	 * Stops autonomous immediately, releasing any stored data.
	 */
	public static void stopAuto() {
		autoCommand.cancel();
	}

}