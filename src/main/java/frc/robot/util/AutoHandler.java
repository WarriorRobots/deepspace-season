package frc.robot.util;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.autonomous.paths.AutoDrive;
import frc.robot.commands.autonomous.paths.routines.Rocket;

/**
 * Add your docs here.
 */
public class AutoHandler {

    // object of this class
    private static AutoHandler instance = null;

    // The command to run for auto
    private Command autocommand = null;

    /** Leftover amount of degrees that the PathFinder did not get */
    private double leftover = 0;

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
            case RIGHTROCKETHAB2:
                autocommand = new Rocket("RocketRightHab2");
                break;
            case LEFTROCKETHAB2:
                autocommand = new Rocket("RocketLeftHab2");
                break;
            case FORWARDS:
                autocommand = new AutoDrive("ForwardsHab1");
                break;
            case NONE:
                // does nothing
                break;
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
        autocommand = null;
    }


    /**
     * Set the leftover amount of degrees after attempting to do a path
     * @param leftover degrees left that PathFinder was unable to complete
     */
    public void setLeftOver(double leftover) {
        this.leftover = leftover;
    }

    /**
     * @return Degrees left over that Pathfinder was unable to complete
     */
    public double getLeftOver() {
        return this.leftover;
    }
}
