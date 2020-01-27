/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Hopper;

public class ManualShoot extends CommandBase {
  /**
   * Creates a new ManualShoot.
   */
 
  double shooting_speed = 0.5;
  double feeding_speed = 0.7;
  private Shooter shooter;
  private Hopper hopper;
  public ManualShoot() {
    this.shooter = RobotContainer.shooter;
    this.hopper = RobotContainer.hopper;
    addRequirements(shooter,hopper);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.setSpeed(shooting_speed);
    hopper.setFeedTalon(feeding_speed);
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
