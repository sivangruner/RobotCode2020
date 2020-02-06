package frc.robot.commands.IntakeCommands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class AutoJoint extends CommandBase {
  private Intake intake;
  private BooleanSupplier isUp;

  // The speed value is for going down
  public AutoJoint(Intake intake, BooleanSupplier isDirectionUp) {
    this.isUp = isDirectionUp;

    addRequirements(intake);
  }

  @Override
  public void initialize() {
    double sign = this.isUp.getAsBoolean()? 1 : -1;
    this.intake.setSpeedJoint(Constants.IntakeConstants.JOINT_SPEED * sign);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    intake.setSpeedJoint(0);
  }

  @Override
  public boolean isFinished() {
    if (isUp.getAsBoolean()) {
      return this.intake.isTopSwitch();
    } else {
      return this.intake.isBottomSwitch();
    }
  }
}
