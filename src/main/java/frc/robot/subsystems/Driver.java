package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;

import AutoLib.PIDConfig;
import AutoLib.PurePursuitController;
import AutoLib.Waypoint;
import AutoLib.LimeLight.LimeLight;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Driver extends SubsystemBase {

  private WPI_TalonSRX leftLeader, rightLeader;
  private WPI_VictorSPX leftFollower, rightFollower;
  private SpeedControllerGroup leftSpeedControllerGroup, rightSpeedControllerGroup;
  private DifferentialDrive diffDrive;
  private PigeonIMU gyro;
  private PIDConfig leftConfig, rightConfig;
  private PurePursuitController controller;
  



  public Driver() {
    this.leftLeader = new WPI_TalonSRX(RobotMap.DriverPorts.LEFT_LEADER);
    this.rightLeader = new WPI_TalonSRX(RobotMap.DriverPorts.RIGHT_LEADER);
    this.leftFollower = new WPI_VictorSPX(RobotMap.DriverPorts.LEFT_FOLLOWER);
    this.rightFollower = new WPI_VictorSPX(RobotMap.DriverPorts.RIGHT_FOLLOWER);
    this.leftSpeedControllerGroup = new SpeedControllerGroup(leftLeader, leftFollower);
    this.rightSpeedControllerGroup = new SpeedControllerGroup(rightLeader, rightFollower);
    this.diffDrive = new DifferentialDrive(leftSpeedControllerGroup, rightSpeedControllerGroup);
    this.diffDrive.setDeadband(Constants.DriverConstants.DEADBAND);
    this.gyro = new PigeonIMU(0);
  }

  public void initPurepursuit(ArrayList<Waypoint> path) {
    this.controller = new PurePursuitController(path, Constants.DriverConstants.LOOKAHEAD_DISTANCE, this.rightLeader, this.leftLeader,
      this.leftConfig, this.rightConfig);
  }

  public void configMotorControllers() {
    this.leftLeader.setNeutralMode(NeutralMode.Brake);
    this.leftFollower.setNeutralMode(NeutralMode.Brake);
    this.rightLeader.setNeutralMode(NeutralMode.Brake);
    this.rightFollower.setNeutralMode(NeutralMode.Brake);

    this.leftFollower.follow(this.leftLeader);
    this.rightFollower.follow(this.rightLeader);
  }

  public void configMotorControllers(double robotVoltage) {

  }

  @Override
  public void periodic() {
  }

  public void arcadeDrive(double speed, double rotation) {
    
    this.diffDrive.arcadeDrive(-speed, rotation);
  }

  public void tankDriveVolts(double leftDemand, double rightDemand) {
    this.leftSpeedControllerGroup.setVoltage(leftDemand);
    this.rightSpeedControllerGroup.setVoltage(rightDemand);
  }

  public void tankDrive(double leftDemand, double rightDemand) {
    this.leftSpeedControllerGroup.set(leftDemand);
    this.rightSpeedControllerGroup.set(rightDemand);
  }

  public void resetGyro() {
    this.gyro.setYaw(0);
    
  }

  public void setAngle(double angle) {
    this.gyro.setYaw(angle);
  }
  public double getYaw(){
    double angle;
    double[] ypr = new double[3];
    this.gyro.getYawPitchRoll(ypr);
    
    angle = ypr[0]%360;
    if(angle >180) angle -=360;
    if(angle <-180) angle +=360;
    setAngle(angle);
    return angle;
  }

  public double getLeftPositionInMeters() {
    return this.leftLeader.getSelectedSensorPosition() * Constants.DriverConstants.METER_PER_TICK;
  }

  public double getRightPositionInMeters() {
    return this.rightLeader.getSelectedSensorPosition() * Constants.DriverConstants.METER_PER_TICK;
  }
}