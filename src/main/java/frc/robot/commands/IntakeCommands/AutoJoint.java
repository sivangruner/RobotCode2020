package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class AutoJoint extends CommandBase {
  private Intake intake; 
  private double speed;
  private boolean lastUpOrDown;
//hello this is dolev hi :) -doelv
//need help? no ok :)      -sivan
//dolev tamot :)           -sivan
  public AutoJoint(Intake m_intake, double speed) {
    this.intake = m_intake;
    this.speed = speed;
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {  
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(intake.getLimitBottom() && intake.lastUpOrDown())
      while(!intake.getLimitTop())
        intake.setSpeedJoint(-speed);
    if(intake.getLimitTop() && intake.lastUpOrDown()==false)
      while(!intake.getLimitBottom())
        intake.setSpeedJoint(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.setSpeedJoint(0);
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
