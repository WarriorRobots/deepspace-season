/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class ElevatorSubsystem extends Subsystem {
  
  public ElevatorSubsystem(){}

  // Make elevator move all the way up
  public void elevator_up(){
    elevator_percent(100);
  }

  // Make elevator move all the way down
  public void elevator_down(){
    elevator_percent(0);
  }

  // Make elevator move some percent up (0 being the bottom, 100 being the top)
  public void elevator_percent(double percent){
    // make sure percent is not over 100 or under 0
    if (percent>100)  percent=100;
    if (percent<0)    percent=0;

    // move elevator using PID

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
