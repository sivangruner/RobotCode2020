package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShootPercentOutputWhileHeld extends CommandBase {
  private Shooter shooter;
  private double demand;

  public ShootPercentOutputWhileHeld(Shooter shooter, double demand) {
    this.shooter = shooter;
    this.demand = demand;

    addRequirements(this.shooter);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    this.shooter.setSpeed(this.demand);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
