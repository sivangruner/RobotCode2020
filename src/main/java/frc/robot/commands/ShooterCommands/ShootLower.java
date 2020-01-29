package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Hopper;
import frc.robot.RobotContainer;

public class ShootLower extends CommandBase{
  private static final double FEED_VEL = 0.6;
  private static final double SHOOT_VEL = 0.4;
  private static final double BELTS_SPEED = 0.4;
  private Shooter shooter;
  private Hopper hopper;


  public ShootLower(Shooter shooter, Hopper hopper){
    this.shooter = shooter;
    this.hopper = hopper;
    addRequirements(this.hopper,this.hopper);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter.setSpeed(SHOOT_VEL);
    hopper.setFeedTalon(FEED_VEL);
    hopper.setBeltsSpeed(BELTS_SPEED);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}