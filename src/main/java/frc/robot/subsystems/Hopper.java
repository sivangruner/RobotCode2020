/*----------------------------------------------------------------------------*/
//Basad
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Hopper extends SubsystemBase {
  private TalonSRX beltsTalon, // Bakar shel hamasoah
                     feedTalon; // Bakar shel hamaacheel
  private double startCurrentBelts, startCurrentFeed, 
                  currentBelts, currentFeed;
  /**
   * Hoppert.
   */
  public Hopper() {
    this.beltsTalon = new TalonSRX(RobotMap.HopperPorts.TALON_BELTS_PORT);
    this.feedTalon = new TalonSRX(RobotMap.HopperPorts.TALON_BELTS_PORT);
  }

  public void configMotorControllers(){

  }

  public double getBeltSpeed(){
    return this.beltsTalon.getMotorOutputPercent();
  }

  public double getFeedSpeed(){
    return this.feedTalon.getMotorOutputPercent();
  }

  public void setBeltsSpeed(double s){
    if (startCurrentBelts - currentBelts > Constants.HOPPER_CURRENT_DELTA) {
      this.beltsTalon.set(ControlMode.PercentOutput, Constants.HOPPER_LOAD_BALLS_SPEED);
    } else {
      this.beltsTalon.set(ControlMode.PercentOutput, s);
    }
  }

  public void setFeederSpeed(double s){
    this.feedTalon.set(ControlMode.PercentOutput, s);
  }

  public void updateCurrents() {
    // this.currentBelts = this.beltsTalon.getOutputCurrent();
    // this.currentFeed = this.feedTalon.getOutputCurrent();
  }

  public double getCurrentBeltsTalon(){
    return this.currentBelts;
  }

  public double getCurrentFeedTalon(){
    return this.currentFeed;
  }

  private void resetCurrentStartBelts(){
    // this.startCurrentBelts = this.beltsTalon.getOutputCurrent();
  }

  private void resetCurrentStartFeed(){
    this.startCurrentFeed = this.feedTalon.getOutputCurrent();
  }

  public void configControl(){
    this.resetCurrentStartBelts();
    this.resetCurrentStartFeed();
  }

  @Override
  public void periodic() {
    this.updateCurrents();
  }
}
