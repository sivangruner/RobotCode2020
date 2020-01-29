///בס"ד
//___.                                    .___
//\_ |__   _____      ______ _____      __| _/
// | __ \  \__  \    /  ___/ \__  \    / __ | 
// | \_\ \  / __ \_  \___ \   / __ \_ / /_/ | 
// |___  / (____  / /____  > (____  / \____ | 
//                                 

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

  private static double leftLimiter = 1; 
  private static double rightLimiter = 1;
  private static double openLimiter = 1;
  
  public Climb() {
    this.right = new WPI_TalonSRX(RobotMap.ClimbPorts.TALON_RIGHT);
    this.left = new WPI_TalonSRX(RobotMap.ClimbPorts.TALON_LEFT);
    this.open = new WPI_VictorSPX(RobotMap.ClimbPorts.VICTOR_CLIMB);
    this.active = false;
  }

  private void configMotorControllers(){

  }

  @Override
  public void periodic() {
    
  }

  public void setState(boolean state){
    this.active = state;
  }
  
  public boolean getState(){
    return this.active;
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
  
  public void setOpenMotorSpeed(double demand){
    this.open.set(ControlMode.PercentOutput, demand*this.openLimiter);
  }
  
  public void setRightMotorSpeed(double demand){
    this.right.set(ControlMode.PercentOutput, Math.max(demand*this.rightLimiter,0));
  }

  public void setLeftMotorSpeed(double demand){
    this.left.set(ControlMode.PercentOutput, Math.max(demand*this.leftLimiter,0));
  }
  
}
