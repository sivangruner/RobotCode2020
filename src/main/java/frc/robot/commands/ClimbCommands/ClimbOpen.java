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

public class ClimbOpen extends CommandBase {
  
  private Climb climbSubSystem;
  private DoubleSupplier input;
  
  public ClimbOpen(Climb m_Climb, DoubleSupplier input) {
    this.climbSubSystem = m_Climb;
    this.input = input;
  
    addRequirements(this.climbSubSystem);
    SmartDashboard.putNumber("Climb Open Limiter", Constants.ClimbConstants.OPEN_LIMITER);
  }

  @Override
  public void initialize() {
    SmartDashboard.putNumber("Climb Open Limiter", Constants.ClimbConstants.OPEN_LIMITER);
  }

  @Override
  public void execute() {
    this.climbSubSystem.setOpenMotorSpeed(this.input.getAsDouble()*SmartDashboard.getNumber("Climb Open Limiter", 0));
  }

  @Override
  public void end(boolean interrupted) {
    this.climbSubSystem.setOpenMotorSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
