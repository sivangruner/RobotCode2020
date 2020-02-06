package frc.robot.commands.ShooterCommands;

import AutoLib.LimeLight.LimeLight;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class Accelerate extends CommandBase {
  Shooter shooter;
  LimeLight limelight;
  double speed;
  
  public Accelerate(Shooter shooter,LimeLight limeLight) {
   this.shooter = shooter;
   this.limelight = limeLight;
   addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.shooter.setVelocity(shooter.getDesiredVelocity(limelight.distance));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false; 
  }
}
