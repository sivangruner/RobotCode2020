package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Intake extends SubsystemBase {
  private WPI_VictorSPX victorJoint;
  private WPI_TalonSRX talonRoller;
  private DigitalInput limitTop;
  private DigitalInput limitBottom;
  private boolean lastUpOrDown; //true-> the joint was goint down
  private boolean limitTopWorked=false;
  private boolean limitBottomWorked=false;
  public Intake(){
    this.victorJoint = new WPI_VictorSPX(RobotMap.IntakePorts.JOINT);
    this.talonRoller = new WPI_TalonSRX(RobotMap.IntakePorts.ROLLER);
    this.limitTop = new DigitalInput(RobotMap.IntakePorts.LIMIT_TOP);
    this.limitBottom = new DigitalInput(RobotMap.IntakePorts.LIMIT_BOTTOM);
    this.lastUpOrDown = true;
  }  

  public boolean getLimitBottom(){
    if(this.limitBottom.get()){
      this.limitBottomWorked=true;
    }
    return this.limitBottom.get();
  }
  
  public boolean getLimitTop(){
    if(!this.limitTop.get()){
      this.limitTopWorked=true;
    }
    return this.limitTop.get();
  }
  
  public double getSpeedJoint(){
  return this.victorJoint.get();
  }
 
  public void setSpeedJoint(double s){
    if(s > 0)
      lastUpOrDown = true;
    if (s < 0)
      lastUpOrDown = false;
    this.victorJoint.set(ControlMode.PercentOutput, s);

    // if(getLimitBottom() && s > 0) //when joint is down and joint is lowering
    //   this.victorJoint.set(ControlMode.PercentOutput, -s);
    // if(getLimitTop() && s < 0)//when joint is up and joint is upering
    //   this.victorJoint.set(ControlMode., -s);
  }

  public double getRollerSpeed(){
    return this.talonRoller.get();
  }

  public boolean lastUpOrDown(){
    if(getSpeedJoint()<0)
      return false; //going down
    else 
      return true; //going up
  }

  public boolean getLimits(){
    boolean ok = false;
    if(this.limitBottomWorked && this.limitTopWorked){
      ok = true;
    }
    return ok;
  }
  
  public void setSpeedRoller(double s){
    this.talonRoller.set(ControlMode.PercentOutput,s);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
