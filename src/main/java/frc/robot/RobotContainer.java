package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ClimbCommands.ClimbByJoystick;
import frc.robot.commands.ClimbCommands.ClimbOpen;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Driver;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import io.github.oblarg.oblog.Logger;

public class RobotContainer {

  //////////////////////////////////////////////////////////
  private Driver driver;
  private Intake intake;
  private Hopper hopper;
  private Shooter shooter;
  private Climb climb;
  //////////////////////////////////////////////////////////
  private XboxController driverController, operatorController;
  private JoystickButton A_DRIVER, A_OPERATOR;
  private JoystickButton B_DRIVER, B_OPERATOR;
  private JoystickButton Y_DRIVER, Y_OPERATOR;
  private JoystickButton X_DRIVER, X_OPERATOR;

  //////////////////////////////////////////////////////////
  public RobotContainer() {
    // 1. Building the Subsystems
    this.configureSubsystems();
    // 2. Building the joysticks and pairing to the commands
    this.configureButtonBindings();
    // 3. Log all
    Logger.configureLoggingAndConfig(this, true);

  }

  private void configureButtonBindings() {
    this.driverController = new XboxController(Constants.JOYSTICK_DRIVER_PORT);
    this.B_DRIVER = new JoystickButton(this.driverController, Constants.XBOX_B_PORT);
    this.A_DRIVER = new JoystickButton(this.driverController, Constants.XBOX_A_PORT);
    this.X_DRIVER = new JoystickButton(this.driverController, Constants.XBOX_X_PORT);
    this.Y_DRIVER = new JoystickButton(this.driverController, Constants.XBOX_Y_PORT);
    this.B_OPERATOR = new JoystickButton(this.operatorController, Constants.XBOX_B_PORT);
    this.A_OPERATOR = new JoystickButton(this.operatorController, Constants.XBOX_A_PORT);
    this.X_OPERATOR = new JoystickButton(this.operatorController, Constants.XBOX_X_PORT);
    this.Y_OPERATOR = new JoystickButton(this.operatorController, Constants.XBOX_Y_PORT);
    //////////////////////////////////////////////////////////
    this.climb.setDefaultCommand(new ClimbByJoystick(this.climb, () -> {
      if (this.climb.getState())
        return operatorController.getRawAxis(1);
      else
        return 0;
    }, () -> {
      if (this.climb.getState())
        return operatorController.getRawAxis(2);
      else
        return 0;
    }));
    this.A_OPERATOR.whileHeld(new ClimbOpen(this.climb, () -> (1.0)));
    this.Y_OPERATOR.whileHeld(new ClimbOpen(this.climb, () -> (-1.0)));
    this.B_DRIVER.whenPressed(new InstantCommand(() -> this.climb.flipState()));
    //////////////////////////////////////////////////////////
  }

  public Command getAutonomousCommand() {
    return null;
  }

  private void configureSubsystems() {
    this.driver = new Driver();
    this.intake = new Intake();
    this.hopper = new Hopper();
    this.shooter = new Shooter();
    this.climb = new Climb();
  }
}
