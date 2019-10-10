package frc.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.util.enums.AutoAction;
import frc.robot.util.enums.StartingHab;
import frc.robot.util.enums.StartingPosition;
import frc.robot.util.enums.TestAction;

public class DashboardHandler {

    private static DashboardHandler instance = null;

    // The dropdowns on the dashboard
    private static SendableChooser<StartingHab> habDropdown;
    private static SendableChooser<StartingPosition> positionDropdown;
    private static SendableChooser<AutoAction> actionDropdown;
    private static SendableChooser<TestAction> testDropdown;

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
        testDropdown = new SendableChooser<>();

        // Give habDropdown options
        habDropdown.setDefaultOption("Hab1", StartingHab.HAB1);
        habDropdown.addOption("Hab2", StartingHab.HAB2);

        // Give positionDropdown options
        positionDropdown.setDefaultOption("Right", StartingPosition.RIGHT);
        positionDropdown.addOption("Middle", StartingPosition.MIDDLE);
        positionDropdown.addOption("Left", StartingPosition.LEFT);

        // Give actionDropdown options
        actionDropdown.setDefaultOption("Forwards", AutoAction.FORWARDS);
        actionDropdown.addOption("None", AutoAction.NONE);
        actionDropdown.addOption("Rocket", AutoAction.ROCKET);
        actionDropdown.addOption("CargoShip", AutoAction.CARGOSHIP);

        testDropdown.setDefaultOption("None", TestAction.NONE);
        testDropdown.addOption("BackStraightRocket", TestAction.BACKSTRAIGHTROCKET);
        testDropdown.addOption("Forwards", TestAction.FORWARDS);
        testDropdown.addOption("Left90", TestAction.LEFT90);
        testDropdown.addOption("Right90", TestAction.RIGHT90);
        testDropdown.addOption("RocketFrontMid", TestAction.ROCKETFRONTMID);
        testDropdown.addOption("RocketRight", TestAction.ROCKETRIGHT);
        testDropdown.addOption("TurnAround", TestAction.TURNAROUND);
    }

    /**
     * Put the dropdowns on the dashboard.
     */
    public void init() {
        SmartDashboard.putData("Starting Hab selector", habDropdown);
        SmartDashboard.putData("Position selector", positionDropdown);
        SmartDashboard.putData("Action selector", actionDropdown);
        SmartDashboard.putData("Test case selector", testDropdown);
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

    /**
     * @return The selected test case for auto.
     */
    public TestAction getTestAction() {
        return testDropdown.getSelected();
    }
}