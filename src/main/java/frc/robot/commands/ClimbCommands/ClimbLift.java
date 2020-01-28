///בס"ד
//___.                                    .___
//\_ |__   _____      ______ _____      __| _/
// | __ \  \__  \    /  ___/ \__  \    / __ | 
// | \_\ \  / __ \_  \___ \   / __ \_ / /_/ | 
// |___  / (____  / /____  > (____  / \____ | 
//    
package frc.robot.commands.ClimbCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climb;

public class ClimbLift extends CommandBase {
  private Climb climbSubSystem;
  private DoubleSupplier joystick;
  public ClimbLift(Climb m_Climb, DoubleSupplier joystick) {
    this.climbSubSystem = m_Climb;
    this.joystick = joystick;
    addRequirements(this.climbSubSystem);
  }

  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climbSubSystem.setRightMotorPercante(joystick); 
    climbSubSystem.setLeftMotorPercante(joystick);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climbSubSystem.setRightMotorPercante(0); 
    climbSubSystem.setLeftMotorPercante(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
