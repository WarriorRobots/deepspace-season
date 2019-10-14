package frc.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.util.enums.AutoAction;

public class DashboardHandler {

    private static DashboardHandler instance = null;

    // The dropdowns on the dashboard
    private static SendableChooser<AutoAction> actionDropdown;

    /**
     * @return The only instance of DashboardHandler.
     */
    public static DashboardHandler getInstance() {
        if (instance == null) {
            instance = new DashboardHandler();
        }
        return instance;
    }

    /**
     * Create DashboardHandler and setup dropdowns.
     */
    private DashboardHandler() { 
        actionDropdown = new SendableChooser<>();
        
        // Give actionDropdown options
        actionDropdown.setDefaultOption("Forwards", AutoAction.FORWARDS);
        actionDropdown.addOption("None", AutoAction.NONE);
        actionDropdown.addOption("Right Rocket Hab1", AutoAction.RIGHTROCKETHAB1);
        actionDropdown.addOption("Right Rocket Hab2", AutoAction.RIGHTROCKETHAB2);
        actionDropdown.addOption("Left Rocket Hab1", AutoAction.LEFTROCKETHAB1);
        actionDropdown.addOption("Left Rocket Hab2", AutoAction.LEFTROCKETHAB2);
    }

    /**
     * Put the dropdowns on the dashboard.
     */
    public void init() {
        SmartDashboard.putData("Action selector", actionDropdown);
    }

    /**
     * @return The selected destination to place a hatch (or location to drive to).
     */
    public AutoAction getAutoAction() {
        return actionDropdown.getSelected();
    }
}