///בס"ד
//___.                                    .___
//\_ |__   _____      ______ _____      __| _/
// | __ \  \__  \    /  ___/ \__  \    / __ | 
// | \_\ \  / __ \_  \___ \   / __ \_ / /_/ | 
// |___  / (____  / /____  > (____  / \____ | 
//    
package frc.robot.commands.ClimbCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climb;

public class AutoFix extends CommandBase {
  private Climb climb;
  private DoubleSupplier Joystick;
  private double currentRight, currentLeft;
  private double fixRight, fixLeft;
  private double diff, speedLeft, speedRight, normalizer;

  
  public AutoFix(Climb m_climb, DoubleSupplier joystick) {
    this.climb = m_climb;
    this.Joystick = joystick;
    SmartDashboard.putNumber("Climb Climbers Limiter", Constants.ClimbConstants.CLIMBERS_LIMITER);
    SmartDashboard.putNumber("Minimus diff", Constants.ClimbConstants.FIX_MIN_AMPDIFF);
    SmartDashboard.putNumber("fix proportion value", Constants.ClimbConstants.FIX_PROPORTION_VALUE);

  }

  @Override
  public void initialize() {
    this.currentLeft = this.climb.getCurrentLeft();
    this.currentRight = this.climb.getCurrentRight();
  }

  @Override
  public void execute() {
    //calculate
    this.currentLeft = this.climb.getCurrentLeft();
    this.currentRight = this.climb.getCurrentRight();
    diff = this.currentRight - this.currentLeft; //the current difference between the right and left talons
    
    //2 fix variables because there may be more difference between them in the future
    if(Math.abs(diff) > SmartDashboard.getNumber("Minimum diff", Constants.ClimbConstants.FIX_MIN_AMPDIFF)){
      fixRight = SmartDashboard.getNumber("fix proportion value", Constants.ClimbConstants.FIX_PROPORTION_VALUE) * diff;
      fixLeft = SmartDashboard.getNumber("fix proportion value", Constants.ClimbConstants.FIX_PROPORTION_VALUE) * -diff;
    }

    speedLeft = this.Joystick.getAsDouble()*SmartDashboard.getNumber("Climb Climbers Limiter", 0) + fixLeft;
    speedRight = this.Joystick.getAsDouble()*SmartDashboard.getNumber("Climb Climbers Limiter", 0) + fixRight;
    normalizer = Math.max(1, Math.max(speedLeft, speedRight));
    speedLeft /= normalizer;
    speedRight /= normalizer;
    this.climb.setLeftMotorSpeed(speedLeft);
    this.climb.setRightMotorSpeed(speedRight);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }

}
