/*----------------------------------------------------------------------------*/
//Basad
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
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
    beltsTalon = new TalonSRX(RobotMap.HopperPorts.TALON_BELTS_PORT);
    feedTalon = new TalonSRX(RobotMap.HopperPorts.TALON_BELTS_PORT);

    beltsTalon.configFactoryDefault();
    feedTalon.configFactoryDefault();

    /* 
    // Not sure what this is ¯\_(ツ)_/¯

    beltsTalon.setInverted(false);
    feedTalon.setInverted(false);
    beltsTalon.setSensorPhase(false);
    feedTalon.setSensorPhase(false);
    */
    this.updateCurrents();
  }

  public TalonSRX getBeltTalon(){
    return beltsTalon;
  }

  public TalonSRX getFeedTalon(){
    return feedTalon;
  }

  public void setBeltTalon(double s){
    if (startCurrentBelts - currentBelts > Constants.HOPPER_CURRENT_DELTA) {
      beltsTalon.set(ControlMode.PercentOutput, Constants.HOPPER_LOAD_BALLS_SPEED);
    } else {
      beltsTalon.set(ControlMode.PercentOutput, s);
    }
  }

  public void setFeedTalon(double s){
    feedTalon.set(ControlMode.PercentOutput, s);
  }

  public void updateCurrents() {
    currentBelts = beltsTalon.getSelectedSensorVelocity();
    currentFeed = feedTalon.getSelectedSensorVelocity();
  }

  public double getCurrentBeltsTalon(){
    return currentBelts;
  }

  public double getCurrentFeedTalon(){
    return currentFeed;
  }

  public void resetCurrentStartBelts(){
    startCurrentBelts = beltsTalon.getSelectedSensorVelocity();
  }

  public void resetCurrentStartFeed(){
    startCurrentFeed = FeedTalon.getSelectedSensorVelocity();
  }

  @Override
  public void periodic() {
    updateCurrents();
  }
}
