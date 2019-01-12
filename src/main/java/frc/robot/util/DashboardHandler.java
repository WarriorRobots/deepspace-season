package frc.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.util.enums.StartingPosition;

/**
 * Sends miscellaneous data to the dashboard. Call in robotInit() of Robot.java
 */
public class DashboardHandler {
	
	private static DashboardHandler instance = null;
	
	@SuppressWarnings("unused")
	private static StartingPosition positionTarget = StartingPosition.MIDDLE;
	@SuppressWarnings("unused")
	
	private static SendableChooser<StartingPosition> positionDropdown;
	
	@Deprecated
	private DashboardHandler() {
		positionDropdown = new SendableChooser<>();
		positionDropdown.addDefault("MIDDLE", StartingPosition.MIDDLE);
		positionDropdown.addObject("LEFT", StartingPosition.LEFT);
		positionDropdown.addObject("RIGHT", StartingPosition.RIGHT);
	}
	
	public void init() {
		SmartDashboard.putData("Position Selector", positionDropdown);
	}
	
	public static DashboardHandler getInstance() {
		if (instance == null) {
			instance = new DashboardHandler();
		} return instance;
	}

	public StartingPosition getStartingPosition() {
		return positionDropdown.getSelected();
	}
	
}