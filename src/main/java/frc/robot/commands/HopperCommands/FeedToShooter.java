package frc.robot.commands.HopperCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hopper;

public class FeedToShooter extends CommandBase {
  private Hopper hopper;
  private double bs, fs;

  public FeedToShooter(Hopper hopper, double beltsSpeed, double feederSpeed) {
    this.hopper = hopper;
    this.bs = beltsSpeed;
    this.fs = feederSpeed;

    addRequirements(hopper);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    this.hopper.setBeltsSpeed(bs);
    this.hopper.setFeederSpeed(fs);
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
