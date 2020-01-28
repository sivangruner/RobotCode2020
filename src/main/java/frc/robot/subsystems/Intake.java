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
  private boolean lastUp;
  
  private Intake() {
    this.VictorJoint = new WPI_VictorSPX(RobotMap.IntakePorts.JOINT);
    this.TalonRoller = new WPI_TalonSRX(RobotMap.IntakePorts.ROLLER);
    this.LimitTop = new DigitalInput(RobotMap.IntakePorts.LIMIT_TOP);
    this.LimitTop = new DigitalInput(RobotMap.IntakePorts.LIMIT_BOTTOM);
    this.lastUp = true;
  }  

  public boolean getLimitBottom(){
    return LimitBottom.get();
  }
  
  public boolean getLimitTop(){
    return LimitTop.get();
  }
  
  public double getSpeedJoint(){
  return VictorJoint.get();
  }
 
  public void setSpeedJoint(double s){
    if(getLimitBottom() && s>0){ //when joint is down and joint is lowering
      this.VictorJoint.set(ControlMode.PercentOutput, -s);
    }
    if(getLimitTop() && s<0){ //when joint is up and joint is upering
      this.VictorJoint.set(ControlMode.PercentOutput, -s);
    }
  }

  public double getRollerSpeed(){
    return TalonRoller.get();
  }

  public void setSpeedRoller(double s){
    this.TalonRoller.set(ControlMode.PercentOutput,s);
  }

  public boolean getUpOrDown(){
    return LastUpOrDown.get();
    }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
