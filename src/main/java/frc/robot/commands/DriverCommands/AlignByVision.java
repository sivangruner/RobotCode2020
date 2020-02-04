package frc.robot.commands.DriverCommands;

import AutoLib.LimeLight.LimeLight;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Driver;

public class AlignByVision extends CommandBase {

  private LimeLight limelight;
  private Driver driver;

  private double p,i,d,kP,kI,kD;
  private double setPoint,error,prevError;
  private double currentPos;
  public AlignByVision(LimeLight limelight,Driver driver) {
    this.limelight = limelight;
    this.driver = driver;
    addRequirements(this.driver);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    i = 0;
    error = 0;prevError = 0;
    currentPos = driver.getYaw();
    setPoint = currentPos+limelight.angleX;

    
    SmartDashboard.putNumber("kP",0);
    SmartDashboard.putNumber("kI",0);
    SmartDashboard.putNumber("kD",0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    configKValues();
    currentPos = driver.getYaw();
    error = setPoint - currentPos;
    
    p = error*kP;
    i += (prevError+error)*0.02*kI/2;
    d = (error - prevError)/0.02*kD;
    prevError = error;

    this.driver.arcadeDrive(0,p+i+d);
    
  }

  private void configKValues(){
    kP = SmartDashboard.getNumber("kP",0);
    kI = SmartDashboard.getNumber("kI",0);
    kD = SmartDashboard.getNumber("kD",0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
