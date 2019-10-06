package frc.robot.util;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.autonomous.paths.AutoDrive;

/**
 * Add your docs here.
 */
public class AutoHandler {

    // object of this class
    private static AutoHandler instance = null;

    // Name of auto to run
    private String autoname = null;
    // The command to run for auto
    private Command autocommand = null;

    /**
     * @return The only instance of AutoHandler
     */
    public static AutoHandler getInstance() {
        if (instance == null) {
            instance = new AutoHandler();
        }
        return instance;
    }

    /**
     * Have AutoHandler select a case based on dashboard values
    */
    public void selectCase() {
        switch (DashboardHandler.getInstance().getAutoAction()) {
            case FORWARDS:
                switch (DashboardHandler.getInstance().getStartingHab()) {
                    case HAB1:
                        autoname = "ForwardsHab1";
                        break;
                    case HAB2:
                        autoname = "ForwardsHab2";
                        break;
                }
                break;
            case ROCKET:
                switch (DashboardHandler.getInstance().getStartingPosition()) {
                    case LEFT:
                        switch (DashboardHandler.getInstance().getStartingHab()) {
                            case HAB1:
                                autoname = "RocketLeftHab1";
                                break;
                            case HAB2:
                                autoname = "RocketLeftHab2";
                                break;
                        }
                        break;
                    case RIGHT:
                        switch (DashboardHandler.getInstance().getStartingHab()) {
                            case HAB1:
                                autoname = "RocketRightHab1";
                                break;
                            case HAB2:
                                autoname = "RocketRightHab2";
                                break;
                        }
                        break;
                }
                break;
            case CARGOSHIP:
                // There is currently no cargoship autos
                break;
        }
        // Only set autocommand to something if something was chosen for it above
        if (autocommand != null) {
            autocommand = new AutoDrive(autoname);
        }
    }

    /**
     * Gives back the command selected in {@link #selectCase()} </p>
     * NOTE: this can return {@code null} however the scheduler can handle null and does nothing with it.
     * @return The command the AutoHandler has chosen
     */
    public Command getCase() {
        return autocommand;
    }

    /**
     * Resets the data in the AutoHandler
     */
    public void reset() {
        autoname = null;
        autocommand = null;
    }
}
