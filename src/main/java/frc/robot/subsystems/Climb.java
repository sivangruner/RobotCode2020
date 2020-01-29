///בס"ד
//___.                                    .___
//\_ |__   _____      ______ _____      __| _/
// | __ \  \__  \    /  ___/ \__  \    / __ | 
// | \_\ \  / __ \_  \___ \   / __ \_ / /_/ | 
// |___  / (____  / /____  > (____  / \____ | 
//                                 
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Climb extends SubsystemBase {
  private WPI_TalonSRX left, right;
  private WPI_VictorSPX open;
  private boolean active;

  public Climb() {
    this.right = new WPI_TalonSRX(RobotMap.ClimbPorts.TALON_RIGHT);
    this.left = new WPI_TalonSRX(RobotMap.ClimbPorts.TALON_LEFT);
    this.open = new WPI_VictorSPX(RobotMap.ClimbPorts.VICTOR_CLIMB);
    this.active = false;
  }

  @Override
  public void periodic() {
    if(Math.abs(getRightMotorPrecentOutput()) > 0 || Math.abs(getLeftMotorPrecentOutput()) > 0 || Math.abs(getOpenMotorPrecentOutput()) > 0)
      this.active = true;
  }

  public double getLeftMotorPrecentOutput(){
    return this.left.getMotorOutputPercent();
  }
  public double getRightMotorPrecentOutput(){
    return this.right.getMotorOutputPercent();
  }

  public double getOpenMotorPrecentOutput(){
    return this.open.getMotorOutputPercent();
  }
  
  
  public void setRightMotorPrecent(double percant){
    this.right.set(ControlMode.PercentOutput, percant);

  }
  public void setLeftMotorPrecent(double percant){
    this.left.set(ControlMode.PercentOutput, percant);
  }
  
}
