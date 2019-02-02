package frc.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.util.enums.StartingPosition;

/**
 * Sends miscellaneous data to the dashboard. Call in robotInit() of Robot.java
 */
public class DashboardHandler {
	
	@SuppressWarnings("unused") //TODO delete this line
	private static StartingPosition positionTarget = StartingPosition.UNSELECTED;
	private static SendableChooser<StartingPosition> positionDropdown;

	// Do not use this constructor
	private DashboardHandler() throws Exception {
		throw new Exception("Do not create an DashboardHandler object, call it statically");
	}

	public static void init() {
		positionDropdown = new SendableChooser<>();
		positionDropdown.setDefaultOption("Please select:", StartingPosition.UNSELECTED);
		positionDropdown.addOption("LEVEL 1 LEFT", StartingPosition.RAMP_LEFT);
		positionDropdown.addOption("LEVEL 1 MIDDLE", StartingPosition.RAMP_MIDDLE);
		positionDropdown.addOption("LEVEL 1 RIGHT", StartingPosition.RAMP_RIGHT);
		positionDropdown.addOption("LEVEL 2 LEFT", StartingPosition.PLATFORM_LEFT);
		positionDropdown.addOption("LEVEL 2 RIGHT", StartingPosition.PLATFORM_RIGHT);
		
		SmartDashboard.putData("Position Selector", positionDropdown);
	}

	public static StartingPosition getStartingPosition(){
		return positionDropdown.getSelected();
	}
	
}