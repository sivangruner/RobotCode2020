package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShootLowerWhileHeld extends CommandBase {
  private Shooter shooter;

  public ShootLowerWhileHeld(Shooter shooter) {
    this.shooter = shooter;

    addRequirements(this.shooter);

    SmartDashboard.putNumber("Shooter Lower Speed", 1);
  }

  @Override
  public void initialize() {
    this.shooter.setSpeed(SmartDashboard.getNumber("Shooter Lower Speed", 1));
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    this.shooter.setSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
