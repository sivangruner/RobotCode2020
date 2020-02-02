package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import AutoLib.PIDConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
  private WPI_TalonSRX leader;
  private WPI_VictorSPX follower;
  PIDConfig config;

  public Shooter() {
    this.leader = new WPI_TalonSRX(RobotMap.ShooterPorts.TALON_PORT);
    this.follower = new WPI_VictorSPX(RobotMap.ShooterPorts.VICTOR_PORT);
    this.follower.follow(this.leader);
    this.config = new PIDConfig(0, 0, 0, 0);
  }

  public void configMotorControllers() {
  }

  public double getEncoderPosition() {
    return this.leader.getSelectedSensorPosition();
  }

  public double getVelocityInRawPerSecond() {
    return (double) this.leader.getSelectedSensorVelocity() * Constants.GeneralConstants.TalonVelocityDT;
  }

  public void setSpeed(double demand) {
    // sets the motor output in PercentOutput
    this.leader.set(ControlMode.PercentOutput, demand);
  }

  public void setVelocity(double RPM) {
    this.leader.set(ControlMode.Velocity, RPM / Constants.ShooterConstants.TICKS_PER_100MS_TO_PRM);
  }

  /**
   * @param distance calculates the
   * @return desired motor velocity
   */
  public double getDesiredVelocity(double distance) {
    double Vpow2 = (Constants.GeneralConstants.G * Math.pow(distance, 2))
        / (/* d **/Math.sin(2 * Constants.ShooterConstants.THROW_ANGLE)
            - (Constants.GeneralConstants.POWER_PORT_HEIGHT - Constants.GeneralConstants.ROBOT_HEIGHT)
                * (Math.cos(2 * Constants.ShooterConstants.THROW_ANGLE)));
    return Math.sqrt(Vpow2);
  }

  /**
   * @param distance returns true if the shooter is ready to shoot by the velocity
   *                 of the shooter
   */
  public boolean isReadyForShooting(double distance) {
    return Math.abs(getDesiredVelocity(distance) - getVelocityInRawPerSecond()) <= .5;
  }

  @Override
  public void periodic() {
  }

  private double calculateMinDistance() {
    return (Constants.GeneralConstants.POWER_PORT_HEIGHT - Constants.GeneralConstants.ROBOT_HEIGHT)
        / Math.tan(Constants.ShooterConstants.THROW_ANGLE);
  }

  // private double calculateMinSpeed(){
  // double sin = Math.sin(THROW_ANGLE);
  // double heightDifference = Constants.POWER_PORT_HEIGHT -
  // Constants.ROBOT_HEIGHT;
  // return Math.sqrt(
  // (2*Constants.G/Math.pow(sin, 2))*heightDifference
  // );
  // }

}