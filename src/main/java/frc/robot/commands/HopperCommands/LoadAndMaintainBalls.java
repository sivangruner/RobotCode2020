/*----------------------------------------------------------------------------*/
/**
 * Basad
 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.HopperCommands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Hopper;

public class LoadAndMaintainBalls extends CommandBase {
  private Hopper hopper;
  private BooleanSupplier load; // getting from the Intake subsystem if it is currently intaking

  public LoadAndMaintainBalls(Hopper hopper, BooleanSupplier load) {
    this.hopper = hopper;
    this.load = load;

    addRequirements(this.hopper);
  }

  @Override
  public void initialize() {
    SmartDashboard.putNumber("Hopper Speed", Constants.HopperConstants.HOPPER_SPEED);
    SmartDashboard.putNumber("Hopper Intake Speed", Constants.HopperConstants.HOPPER_LOAD_BALLS_SPEED);
  }

  @Override
  public void execute() {
    if (this.load.getAsBoolean())
      this.hopper.setBeltsSpeed(
          SmartDashboard.getNumber("Hopper Intake Speed", Constants.HopperConstants.HOPPER_LOAD_BALLS_SPEED));
    else
      this.hopper.setBeltsSpeed(SmartDashboard.getNumber("Hopper Speed", Constants.HopperConstants.HOPPER_SPEED));
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
