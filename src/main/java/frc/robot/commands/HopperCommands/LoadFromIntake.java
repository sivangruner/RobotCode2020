/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.HopperCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Shooter;

public class LoadFromIntake extends CommandBase {
  private Hopper hopperSubsystem;
  private Shooter shooterSubsystem;
  /**
   * Creates a new LoadFromIntake.
   */
  public LoadFromIntake() {
    hopperSubsystem = RobotContainer.hopper;
    shooterSubsystem = RobotContainer.shooter;
    addRequirements(hopperSubsystem, shooterSubsystem);
  }

    // Use addRequirements() here to declare subsystemMETER_PER_TICK dependencies.
  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    hopperSubsystem.setFeedTalon(Constants.HOPPER_MAINTAIN_BALLS_SPEED);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    isFinished();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
