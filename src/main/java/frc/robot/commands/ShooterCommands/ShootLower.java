package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Hopper;
import frc.robot.RobotContainer;

public class ShootLower extends CommandBase{
     /**
   * Creates a new AutoShoot.
   */
  public static final double FEED_VEL = 0.6;
  public static final double SHOOT_VEL = 0.6;
  private Shooter shooter;
  private Hopper hopper;
  public ShootLower() {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooter = RobotContainer.shooter;
    this.hopper = RobotContainer.hopper;
    addRequirements(this.hopper,this.hopper);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.setSpeed(SHOOT_VEL);
    hopper.setFeedTalon(FEED_VEL);
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