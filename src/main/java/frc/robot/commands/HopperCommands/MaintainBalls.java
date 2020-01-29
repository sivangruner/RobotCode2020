/*----------------------------------------------------------------------------*/
/**
 * Basad
 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.HopperCommands;

import AutoLib.LimeLight.LimeLight;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Shooter;

public class MaintainBalls extends CommandBase {
  private Hopper hopper;
  private double speedBelts;

  public MaintainBalls(Hopper hopper, double sBelts) {
    this.hopper = hopper;
    this.speedBelts = sBelts;

    addRequirements(this.hopper);
  }

  @Override
  public void initialize() {
    this.hopper.setBeltsSpeed(this.speedBelts);
  }

  @Override
  public void execute() {
    this.hopper.setBeltsSpeed(this.speedBelts);
  }

  @Override
  public void end(boolean interrupted) {
    
    }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
