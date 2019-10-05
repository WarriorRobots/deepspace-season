package frc.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.util.enums.AutoAction;
import frc.robot.util.enums.StartingHab;
import frc.robot.util.enums.StartingPosition;

public class DashboardHandler {

    private static DashboardHandler instance = null;

    // The dropdowns on the dashboard
    private static SendableChooser<StartingHab> habDropdown;
    private static SendableChooser<StartingPosition> positionDropdown;
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
        habDropdown = new SendableChooser<>();
        positionDropdown = new SendableChooser<>();
        actionDropdown = new SendableChooser<>();

        // Give habDropdown options
        habDropdown.addDefault("Hab1", StartingHab.HAB1);
        habDropdown.addObject("Hab2", StartingHab.HAB2);

        // Give positionDropdown options
        positionDropdown.addDefault("Right", StartingPosition.RIGHT);
        positionDropdown.addObject("Middle", StartingPosition.MIDDLE);
        positionDropdown.addObject("Left", StartingPosition.LEFT);

        // Give actionDropdown options
        actionDropdown.addDefault("Forwards", AutoAction.FORWARDS);
        actionDropdown.addObject("None", AutoAction.NONE);
        actionDropdown.addObject("Rocket", AutoAction.ROCKET);
        actionDropdown.addObject("CargoShip", AutoAction.CARGOSHIP);
    }

    /**
     * Put the dropdowns on the dashboard.
     */
    public void init() {
        SmartDashboard.putData("Starting Hab selector", habDropdown);
        SmartDashboard.putData("Position selector", positionDropdown);
        SmartDashboard.putData("Action selector", actionDropdown);
    }

    /**
     * @return The selected starting Hab for auto.
     */
    public StartingHab getStartingHab() {
        return habDropdown.getSelected();
    }

    /**
     * @return The selected starting position for auto.
     */
    public StartingPosition getStartingPosition() {
        return positionDropdown.getSelected();
    }

    /**
     * @return The selected destination to place a hatch (or location to drive to).
     */
    public AutoAction getAutoAction() {
        return actionDropdown.getSelected();
    }
}