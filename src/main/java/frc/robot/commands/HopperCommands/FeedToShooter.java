package frc.robot.commands.HopperCommands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Hopper;

public class FeedToShooter extends CommandBase {
  private Hopper hopper;
  private BooleanSupplier isReady;

  public FeedToShooter(Hopper hopper, BooleanSupplier isReady) {
    this.hopper = hopper;
    this.isReady = isReady;

    addRequirements(hopper);

    SmartDashboard.putNumber("Feed Speed", Constants.HopperConstants.HOPPER_FEEDER_SPEED);
  }

  @Override
  public void initialize() {
    SmartDashboard.putNumber("Feed Speed", Constants.HopperConstants.HOPPER_FEEDER_SPEED);
  }

  @Override
  public void execute() {
    if (this.isReady.getAsBoolean())
      this.hopper.setFeederSpeed(SmartDashboard.getNumber("Feed Speed", Constants.HopperConstants.HOPPER_FEEDER_SPEED));

  }

  @Override
  public void end(boolean interrupted) {
    this.hopper.setFeederSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
