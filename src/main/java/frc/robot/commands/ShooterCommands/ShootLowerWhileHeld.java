package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Shooter;

public class ShootLowerWhileHeld extends CommandBase {
  private Shooter shooter;
  private Hopper hopper;
  public ShootLowerWhileHeld(Shooter shooter,Hopper hopper) {
    this.shooter = shooter;
    this.hopper = hopper;

    addRequirements(this.shooter,this.hopper);

    SmartDashboard.putNumber("Shooter Lower Speed", 1);
    SmartDashboard.putNumber("Hopper Feeding Speed", 1);
  }

  @Override
  public void initialize() {
    this.shooter.setSpeed(SmartDashboard.getNumber("Shooter Lower Speed", 1));
    this.hopper.setFeederSpeed(SmartDashboard.getNumber("Hopper Feeding Speed", 1));
    this.hopper.setBeltsSpeed(Constants.HopperConstants.HOPPER_SPEED);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    this.shooter.setSpeed(0);
    this.hopper.setFeederSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
