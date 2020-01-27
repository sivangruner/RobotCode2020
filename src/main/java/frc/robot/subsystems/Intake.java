/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Intake extends SubsystemBase {
  private WPI_VictorSPX VictorJoint;
  private WPI_TalonSRX TalonRoller;
  private DigitalInput LimitTop;
  private DigitalInput LimitBottom;
  private boolean LastUpOrDown;
  
  public Intake() {
    this.VictorJoint = new WPI_VictorSPX(RobotMap.IntakePorts.VictorJoint);
    this.TalonRoller = new WPI_TalonSRX(RobotMap.IntakePorts.TalonRoller);
    this.LimitTop = new DigitalInput(RobotMap.IntakePorts.LimitTop);
    this.VictorJoint = new DigitalInput(RobotMap.IntakePorts.LimitBottom);
    this.LastUpOrDown = new boolean(RobotMap.IntakePorts.LastUpOrDown)
  }  

  public boolean getPressLimitT(){
    return LimitBottom.get();
  }
  
  public boolean getPressLimitB(){
    return LimitRight.get();
  }
  
  public double getSpeedJoint(){
  return VictorJoint.get();
  }
 
  public void setSpeedJoint(double s){
    if(getPressLimitB() && s>0){ //when joint is down and joint is lowering
      this.VictorJoint.set(ControlMode.PercentOutput, -s);
    }
    if(getPressLimitT() && s<0){ //when joint is up and joint is upering
      this.VictorJoint.set(ControlMode.PercentOutput, -s);
    }
  }

  public double getSpeedRoller(){
    return TalonRoller.get();
  }

  public void setSpeedRoller(double s){
    this.TalonRoller.set(s);
  }

  public boolean getUpOrDown(){
    return LastUpOrDown.get();
    }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
