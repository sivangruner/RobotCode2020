package frc.robot;

import AutoLib.LimeLight.LimeLight;
import edu.wpi.first.hal.PDPJNI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ClimbCommands.ClimbByJoystick;
import frc.robot.commands.ClimbCommands.ClimbOpen;
import frc.robot.commands.DriverCommands.ArcadeDrive;
import frc.robot.commands.HopperCommands.FeedToShooter;
import frc.robot.commands.ShooterCommands.Accelerate;
import frc.robot.commands.ShooterCommands.AutoShoot;
import frc.robot.commands.ShooterCommands.ShootLowerWhileHeld;
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
  private PDPJNI pdp;
  private LimeLight limelight;
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
    this.driverController = new XboxController(Constants.GeneralConstants.JOYSTICK_DRIVER_PORT);
    this.B_DRIVER = new JoystickButton(this.driverController, XboxController.Button.kB.value);//Constants.GeneralConstants.XBOX_B_PORT);
    this.A_DRIVER = new JoystickButton(this.driverController, Constants.GeneralConstants.XBOX_A_PORT);
    this.X_DRIVER = new JoystickButton(this.driverController, Constants.GeneralConstants.XBOX_X_PORT);
    this.Y_DRIVER = new JoystickButton(this.driverController, Constants.GeneralConstants.XBOX_Y_PORT);
    this.B_OPERATOR = new JoystickButton(this.operatorController, Constants.GeneralConstants.XBOX_B_PORT);
    this.A_OPERATOR = new JoystickButton(this.operatorController, Constants.GeneralConstants.XBOX_A_PORT);
    this.X_OPERATOR = new JoystickButton(this.operatorController, Constants.GeneralConstants.XBOX_X_PORT);
    this.Y_OPERATOR = new JoystickButton(this.operatorController, Constants.GeneralConstants.XBOX_Y_PORT);
    //////////////////////////////////////////////////////////
    // CLIMBER COMMANDS
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
    this.B_OPERATOR.whenPressed(new InstantCommand(() -> this.climb.flipState(), this.climb));
    //////////////////////////////////////////////////////////
    // DRIVER COMMANDS
    this.driver.setDefaultCommand(new ArcadeDrive(() -> this.driverController.getRawAxis(1),
        () -> this.driverController.getRawAxis(4), () -> this.climb.getState(), this.driver));
    this.X_DRIVER.whenPressed(new AutoShoot(), true);
    //////////////////////////////////////////////////////////
    // SHOOTER COMMANDS
    // Lower Shoot
    this.A_DRIVER.whileHeld(
        new ParallelRaceGroup(new ShootLowerWhileHeld(this.shooter,this.hopper), new FeedToShooter(this.hopper, () -> true)));
    // MANUAL Higher Shoot from fixed distance
    this.X_DRIVER.whileHeld(new ParallelRaceGroup(new Accelerate(this.shooter,this.limelight), new FeedToShooter(this.hopper,
        () -> this.shooter.isReadyForShooting(Constants.ShooterConstants.ManualShootDistance))));
    // ACCELERATE 
    
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
    this.pdp = new PDPJNI();
    this.limelight = new LimeLight(() -> driver.getYaw());
  }

  public void teleopInit(){

  }

  public void autoInit(){
    
  }

}
