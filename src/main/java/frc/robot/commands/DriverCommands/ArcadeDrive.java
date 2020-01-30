package frc.robot.commands.DriverCommands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Driver;

public class ArcadeDrive extends CommandBase {
  DoubleSupplier speedAxis, rotationAxis;
  BooleanSupplier isClimbing;
  Driver driver;

  public ArcadeDrive(DoubleSupplier speedAxis, DoubleSupplier rotationAxis, BooleanSupplier isClimbing, Driver driver) {
    this.driver = driver;
    this.speedAxis = speedAxis;
    this.rotationAxis = rotationAxis;
    this.isClimbing = isClimbing;

    addRequirements(this.driver);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if(!this.isClimbing.getAsBoolean())
    this.driver.arcadeDrive(this.speedAxis.getAsDouble(), this.rotationAxis.getAsDouble());
    else
    this.driver.arcadeDrive(this.speedAxis.getAsDouble()*0.6, this.rotationAxis.getAsDouble()*0.7);

    
  }

  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
