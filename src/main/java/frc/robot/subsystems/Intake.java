//basad
//dolev
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
  

  public Intake() {
    this.victorJoint = new WPI_VictorSPX(RobotMap.IntakePorts.JOINT);
    this.talonRoller = new WPI_TalonSRX(RobotMap.IntakePorts.ROLLER);
    this.limitTop = new DigitalInput(RobotMap.IntakePorts.LIMIT_TOP);
    this.limitBottom = new DigitalInput(RobotMap.IntakePorts.LIMIT_BOTTOM);
  }

  public boolean isIntaking(){
    return true;
  }

  public boolean isBottomSwitch() {
    return this.limitBottom.get();
  }

  public boolean isTopSwitch() {
    return this.limitBottom.get();
  }

  public double getSpeedJoint() {
    return this.victorJoint.get();
  }

  public void setSpeedJoint(double demand) {
    this.victorJoint.set(ControlMode.PercentOutput, demand);
  }

  public double getRollerSpeed() {
    return this.talonRoller.get();
  }

  public void setSpeedRoller(double demand) {
    this.talonRoller.set(ControlMode.PercentOutput, demand);
  }

  @Override
  public void periodic() {
  }
}
