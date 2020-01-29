/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.IntakeCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class JointByJoystick extends CommandBase {
  private Intake intake;
  private DoubleSupplier joystick;
  /**
   * Creates a new JointByJoystick.
   */
  public JointByJoystick(Intake intake, DoubleSupplier joystick) {
    this.intake = intake;
    this.joystick = joystick;
    addRequirements(intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(this.intake.lastUpOrDown()){
      if(this.intake.getLimitBottom()){
        this.intake.setSpeedJoint(0);
      }
    }
    else{
      if(this.intake.getLimitTop())
        this.intake.setSpeedJoint(0);
    }
    this.intake.setSpeedJoint(this.joystick.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
