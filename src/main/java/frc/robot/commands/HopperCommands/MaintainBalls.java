package frc.robot.commands.HopperCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Hopper;

/**
 * לא צריך את זה בכלל מי עשה את זה?!?!?!?!?!?!?this.
 */
public class MaintainBalls extends CommandBase {
  private Hopper hopper;
  /**
   * Creates a new MaintainBalls.
   */
  public MaintainBalls(Hopper hopper) {
    this.hopper = hopper;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.hopper.configControl();
  }

  // Called every time the scheduler runs while the command is scheduled.this.
  @Override
  public void execute() {
    this.hopper.updateCurrents();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.isFinished();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
