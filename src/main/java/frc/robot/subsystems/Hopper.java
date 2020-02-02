/*----------------------------------------------------------------------------*/
//Basad
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Hopper extends SubsystemBase {
  private TalonSRX beltsTalon, // Bakar shel hamasoah
      feedTalon; // Bakar shel hamaacheel

  /**
   * Hoppert.
   */
  public Hopper() {
    this.beltsTalon = new TalonSRX(RobotMap.HopperPorts.TALON_BELTS_PORT);
    this.feedTalon = new TalonSRX(RobotMap.HopperPorts.TALON_BELTS_PORT);
  }

  public void configMotorControllers() {

  }

  public double getBeltSpeed() {
    return this.beltsTalon.getMotorOutputPercent();
  }

  public double getFeedSpeed() {
    return this.feedTalon.getMotorOutputPercent();
  }

  public void setBeltsSpeed(double s) {
    this.beltsTalon.set(ControlMode.PercentOutput, s);
  }

  public void setFeederSpeed(double s) {
    this.feedTalon.set(ControlMode.PercentOutput, s);
  }

  @Override
  public void periodic() {
  }
}
