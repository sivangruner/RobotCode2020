package frc.robot.commands.DriverCommands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
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
    SmartDashboard.putNumber("Speed Limiter", Constants.DriverConstants.SPEED_LIMITER);
    SmartDashboard.putNumber("Rotation Limiter", Constants.DriverConstants.ROTATION_LIMITER);
    SmartDashboard.putNumber("Climb Speed Limiter", Constants.DriverConstants.CLIMB_SPEED_LIMITER);
    SmartDashboard.putNumber("Climb Rotation Limiter", Constants.DriverConstants.CLIMB_ROTATION_LIMITER);
  }

  @Override
  public void execute() {
    if(!this.isClimbing.getAsBoolean())
    this.driver.arcadeDrive(this.speedAxis.getAsDouble() * SmartDashboard.getNumber("Speed Limiter", Constants.DriverConstants.SPEED_LIMITER),
     this.rotationAxis.getAsDouble() * SmartDashboard.getNumber("Rotation Limiter", Constants.DriverConstants.ROTATION_LIMITER));
    else
    this.driver.arcadeDrive(this.speedAxis.getAsDouble() * SmartDashboard.getNumber("Climb Speed Limiter", Constants.DriverConstants.CLIMB_SPEED_LIMITER),
     this.rotationAxis.getAsDouble() * SmartDashboard.getNumber("Climb Rotation Limiter", Constants.DriverConstants.CLIMB_ROTATION_LIMITER));
  }

  @Override
  public void end(boolean interrupted) {
    
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
