/*----------------------------------------------------------------------------*/
/**
 * Basad
 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.HopperCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Shooter;

public class FeedToShooter extends CommandBase {
  private Hopper hopper;
  private Shooter shooter;
  private double speedBelts, speedFeed;
  private boolean isReady; // Is ready to feed balls to shooter
  /** 
   * Creates a new FeedToShooter.
   */
  public FeedToShooter(Hopper hopper, Shooter shooter, double sFeed, double sBelts) {
    this.shooter = shooter;
    this.hopper = hopper;
    this.speedBelts = sBelts;
    this.speedFeed = sFeed;

    addRequirements(this.hopper, this.shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.hopper.setBeltsSpeed(this.speedBelts);
    this.isReady = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.isReady = this.shooter.isReadyForShooting();

    if (this.isReady)
      this.hopper.setFeedTalon(this.speedBelts);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.hopper.setFeedTalon(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !this.isReady;
  }
}
