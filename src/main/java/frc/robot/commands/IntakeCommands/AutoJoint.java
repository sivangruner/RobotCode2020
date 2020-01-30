package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class AutoJoint extends CommandBase {
  private Intake intake; 
  private double speed;
  private boolean direction, prevDirection = false, isTop = false, isBottom = false;

  public AutoJoint(Intake m_intake, double speed) {
    this.intake = m_intake;
    this.speed = speed;

    addRequirements(intake);
  }

  @Override
  public void initialize() {  
    isTop = intake.isTopSwitch();
    isBottom = intake.isBottomSwitch();
    if(isTop)
      direction = true;
    else if(isBottom)
      direction = false;
      

  }

  @Override
  public void execute() {
    if(isBottom)
      speed = -speed;
    this.intake.setSpeedJoint(this.speed);
  }

  @Override
  public void end(boolean interrupted) {
    intake.setSpeedJoint(0);
  }

  @Override
  public boolean isFinished() {
    return this.intake.isTopSwitch();
  }
}
