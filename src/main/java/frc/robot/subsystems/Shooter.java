package frc.robot.subsystems;

import frc.robot.*;
import frc.robot.commands.ShooterCommands.Accelerate;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.*;

public class Shooter extends SubsystemBase {
  private WPI_TalonSRX leader;
  private WPI_VictorSPX follower;
  

  // constants
  public static final double THROW_ANGLE = Math.toRadians(0);
 
  public Shooter() {
    this.leader = new WPI_TalonSRX(RobotMap.ShooterPorts.TALON_PORT);
    this.follower = new WPI_VictorSPX(RobotMap.ShooterPorts.VICTOR_PORT);
    this.follower.follow(this.leader);

    
    SmartDashboard.putNumber("Shooter_kP",0);
    SmartDashboard.putNumber("Shooter_kI",0);
    SmartDashboard.putNumber("Shooter_kD",0);
    SmartDashboard.putNumber("Shooter_kF",0);

  }


  public void configMotors(){
    this.leader.config_kP(0,PIDF.kP.value);
    this.leader.config_kI(0,PIDF.kI.value);
    this.leader.config_kD(0,PIDF.KD.value);
    this.leader.config_kF(0,PIDF.kF.value);
  }
  public int getEncoderPos(){
    return this.leader.getSelectedSensorPosition();
  }
  public double getVelocity(){
    return (double)this.leader.getSelectedSensorVelocity()*10;
  }
  public void setSpeed(double speed){
    // sets the Velocity in RPM
    this.leader.set(ControlMode.PercentOutput,speed);
  }
  public void setVelocity(double RPM){
    this.leader.set(ControlMode.Velocity,RPM/Constants.TICKS_PER_100MS_TO_PRM);
  }

  /**
   * @param distance calculates the 
   * 
   */
  public double getDesiredVelocity(double distance){
    double Vpow2 = (Constants.G*Math.pow(distance,2))/(/*d**/Math.sin(2*THROW_ANGLE) - (Constants.POWER_PORT_HEIGHT - Constants.ROBOT_HEIGHT)*(Math.cos(2*THROW_ANGLE)));
    return Math.sqrt(Vpow2);
  }


  /**
   * @param distance 
   * returns true if the shooter is ready to shoot by the velocity of the shooter
   */
  public boolean isReadyForShooting(double distance){
    return Math.abs(getDesiredVelocity(distance) -getVelocity()) <= .5;
  }

  @Override
  public void periodic() {
  }

  private double calculateMinDistance(){
    return (Constants.POWER_PORT_HEIGHT - Constants.ROBOT_HEIGHT)/Math.tan(THROW_ANGLE);
  }
  // private double calculateMinSpeed(){
  //   double sin = Math.sin(THROW_ANGLE);
  //   double heightDifference = Constants.POWER_PORT_HEIGHT - Constants.ROBOT_HEIGHT;
  //   return Math.sqrt(
  //     (2*Constants.G/Math.pow(sin, 2))*heightDifference
  //     );
  // }
  enum PIDF{
    kP(0),
    kI(0),
    KD(0),
    kF(0),
    SD_kP(SmartDashboard.getNumber("Shooter_kP",0)),
    SD_kI(SmartDashboard.getNumber("Shooter_kI",0)),
    SD_KD(SmartDashboard.getNumber("Shooter_kD",0)),
    SD_kF(SmartDashboard.getNumber("Shooter_kF",0));
    
    public double value;
    private PIDF(double value){
      this.value = value;
    }
  }
}
