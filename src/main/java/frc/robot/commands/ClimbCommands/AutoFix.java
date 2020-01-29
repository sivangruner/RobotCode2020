/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
///בס"ד
//___.                                    .___
//\_ |__   _____      ______ _____      __| _/
// | __ \  \__  \    /  ___/ \__  \    / __ | 
// | \_\ \  / __ \_  \___ \   / __ \_ / /_/ | 
// |___  / (____  / /____  > (____  / \____ | 
//    
package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climb;

public class AutoFix extends CommandBase {
  /**
  
  */
  private int p;
  private int change;
  private Climb climbSubSystem;
  public AutoFix(Climb m_climb) {
    this.climbSubSystem = m_climb;
    addRequirements(this.climbSubSystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    p = 5;
    change = 3;
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while(climbSubSystem.getLeftMotorPrecentOutput() > climbSubSystem.getRightMotorPrecentOutput() + p){
      climbSubSystem.setRightMotorPercante(climbSubSystem.getRightMotorPrecentOutput() + change);
      change *= 0.8;
    }
    while(climbSubSystem.getLeftMotorPrecentOutput() < climbSubSystem.getRightMotorPrecentOutput() + p){
      climbSubSystem.setLeftMotorPercante(climbSubSystem.getLeftMotorPrecentOutput() + change);
      change *= 0.8;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
