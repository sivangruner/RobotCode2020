package frc.robot.subsystems;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;

import AutoLib.PIDConfig;
import AutoLib.PurePursuitController;
import AutoLib.Waypoint;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;

public class Driver extends SubsystemBase implements Loggable {

  private WPI_TalonSRX leftLeader, rightLeader;
  private WPI_VictorSPX leftFollower, rightFollower;
  private SpeedControllerGroup leftSpeedControllerGroup, rightSpeedControllerGroup;
  private DifferentialDrive diffDrive;
  private PigeonIMU gyro;
  private PIDConfig leftConfig, rightConfig;
  private PurePursuitController controller;
  @Config
  double rotationLimiter = 0, speedLimiter = 0;

  public Driver() {
    this.leftLeader = new WPI_TalonSRX(RobotMap.DriverPorts.LEFT_LEADER);
    this.rightLeader = new WPI_TalonSRX(RobotMap.DriverPorts.RIGHT_LEADER);
    this.leftFollower = new WPI_VictorSPX(RobotMap.DriverPorts.LEFT_FOLLOWER);
    this.rightFollower = new WPI_VictorSPX(RobotMap.DriverPorts.RIGHT_FOLLOWER);
    this.leftSpeedControllerGroup = new SpeedControllerGroup(leftLeader, leftFollower);
    this.rightSpeedControllerGroup = new SpeedControllerGroup(rightLeader, rightFollower);
    this.diffDrive = new DifferentialDrive(leftSpeedControllerGroup, rightSpeedControllerGroup);
    this.gyro = new PigeonIMU(0);

  }

  public void initPurepursuit(ArrayList<Waypoint> path) {
    this.controller = new PurePursuitController(path, Constants.LOOKAHEAD_DISTANCE, this.rightLeader, this.leftLeader,
        this.leftConfig, this.rightConfig);
  }

  public void configMotorControllers() {
    this.leftLeader.setNeutralMode(NeutralMode.Brake);
    this.leftFollower.setNeutralMode(NeutralMode.Brake);
    this.rightLeader.setNeutralMode(NeutralMode.Brake);
    this.rightFollower.setNeutralMode(NeutralMode.Brake);

    this.leftFollower.follow(this.leftLeader);
    ;
    this.rightFollower.follow(this.rightLeader);

  }

  public void configMotorControllers(double robotVoltage) {

  }

  @Override
  public void periodic() {
  }

  public void arcadeDrive(double speed, double rotation) {
    this.diffDrive.arcadeDrive(-speed * this.speedLimiter, rotation * this.rotationLimiter);
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

  }

  public void setGyro(double angle) {

  }

  public double getLeftPositionInMeters() {
    return this.leftLeader.getSelectedSensorPosition() * Constants.METER_PER_TICK;
  }

  public double getRightPositionInMeters() {
    return this.rightLeader.getSelectedSensorPosition() * Constants.METER_PER_TICK;
  }

}