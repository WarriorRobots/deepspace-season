/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous.paths;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.util.AutoHandler;

public class StopAuto extends InstantCommand {

    /**
     * Command to stop the autonomous code from being run so that if something goes wrong it can be
     * manually stopped.
     */
    public StopAuto() {
        super();
    }

    @Override
    protected void initialize() {
        if (AutoHandler.getInstance().getCase() != null) {
            AutoHandler.getInstance().getCase().cancel();
        }
    }

}
