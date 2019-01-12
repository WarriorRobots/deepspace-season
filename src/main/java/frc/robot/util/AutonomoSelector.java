package frc.robot.util;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Contains methods that select the Autonomous case of the robot.
 * Place these methods inside autonomousInit() of Robot.java
 */
public class AutonomoSelector {

	private static AutonomoSelector instance = null;
	
	private String gameData = null;
	private Command autoCommand = null;
	
	public static AutonomoSelector getInstance() {
		if (instance == null) {
			instance = new AutonomoSelector();
		}
		return instance;
	}
	
	@Deprecated
	public void selectTestCase() {
		DriverStation.reportError("Robot tried to select debugging case, check Robot.java!", false);
//		autoCommand = new TestCase();
	}
	
	public void stopAuto() {
		autoCommand.cancel();
	}
	
	/**
	 * Selects autonomous case and calls {@code start()} on the chosen {@code CommandGroup}.
	 */
	public void selectAutoCase() {
		initData();
		
		
		}

	
	public void startAuto() {
		try {
			autoCommand.start();
		} catch (Exception e) {
			DriverStation.reportError(e.getMessage(), false);
		}
	}
	
	private void initData() {
		resetData();
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
	}


	private void resetData() {
		
	}
}