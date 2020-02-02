/*----------------------------------------------------------------------------*/
/* Dolev 😠😠😠                                                              .                                                               */
/*----------------------------------------------------------------------------*/
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

public class ClimbByJoystick extends CommandBase {
  
  private Climb climbSubSystem;
  private DoubleSupplier leftJoystick;
  private DoubleSupplier rightJoystick;


  public ClimbByJoystick(Climb m_climb, DoubleSupplier left, DoubleSupplier right) {
    this.climbSubSystem = m_climb;
    this.leftJoystick = left;
    this.rightJoystick = right;

    addRequirements(this.climbSubSystem);
    SmartDashboard.putNumber("Climb Climbers Limiter", Constants.ClimbConstants.CLIMBERS_LIMITER);

  }

  @Override
  public void initialize() {
    SmartDashboard.putNumber("Climb Climbers Limiter", Constants.ClimbConstants.CLIMBERS_LIMITER);
    
  }

  @Override
  public void execute() {
    this.climbSubSystem.setLeftMotorSpeed(this.leftJoystick.getAsDouble()*SmartDashboard.getNumber("Climb Climbers Limiter", 0));
    this.climbSubSystem.setRightMotorSpeed(this.rightJoystick.getAsDouble()*SmartDashboard.getNumber("Climb Climbers Limiter", 0));
  }

  @Override
  public void end(boolean interrupted) {
    this.climbSubSystem.setLeftMotorSpeed(0);
    this.climbSubSystem.setRightMotorSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
