package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Driver extends SubsystemBase {
  
  private WPI_TalonSRX leftLeader, rightLeader;
  private WPI_VictorSPX leftFollower, rightFollower;
  private SpeedControllerGroup left, right;
  private DifferentialDrive diffDrive;
  private PigeonIMU gyro;

  public Driver() {
    this.leftLeader = new WPI_TalonSRX(RobotMap.DriverPorts.LEFT_LEADER);
    this.rightLeader = new WPI_TalonSRX(RobotMap.DriverPorts.RIGHT_LEADER);
    this.leftFollower = new WPI_VictorSPX(RobotMap.DriverPorts.LEFT_FOLLOWER);
    this.rightFollower = new WPI_VictorSPX(RobotMap.DriverPorts.RIGHT_FOLLOWER);
    this.left = new SpeedControllerGroup(leftLeader, leftFollower);
    this.right = new SpeedControllerGroup(rightLeader, rightFollower);
    this.diffDrive = new DifferentialDrive(left, right);
    this.gyro = new PigeonIMU(0);
    
  }

  @Override
  public void periodic() {
  }

  public void arcadeDrive(double speed, double rotation){
    this.diffDrive.arcadeDrive(-speed, rotation);
  }

  public void tankDriveVolts(double leftDemand, double rightDemand){
    this.left.setVoltage(leftDemand);
    this.right.setVoltage(rightDemand);
  }

  public void tankDrive (double leftDemand, double rightDemand){
    this.left.set(leftDemand);
    this.right.set(rightDemand);
  }
  
  public void resetGyro(){

  }

  public void setGyro(double angle){

  }

  public double getLeftPositionInMeters(){
    return this.leftLeader.getSelectedSensorPosition()*Constants.METER_PER_TICK;
  }
  public double getRightPositionInMeters(){
    return this.rightLeader.getSelectedSensorPosition()*Constants.METER_PER_TICK;
  }
  
}
