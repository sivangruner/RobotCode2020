/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.IntakeCommands.ManualRoller;
import frc.robot.commands.ShooterCommands.Accelerate;
import frc.robot.commands.ShooterCommands.ShootLower;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Driver;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private Driver driver;
  private Intake intake;
  private Hopper hopper;
  private Shooter shooter;
  private Climb climb;
  
  // private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public static XboxController driverController;
  public JoystickButton a;
  public JoystickButton manualShoot;
  public JoystickButton shootLower;
  
  public Joystick console; //the console with buttons for the climb
  public DigitalInput button1;
  public DigitalInput button2;
  public DigitalInput button3;

  public RobotContainer() {
    this.configureSubsystems();
    this.driverController = new XboxController(0);
    this.a = new JoystickButton(this.driverController,XboxController.Button.kA.value);
    this.manualShoot = new JoystickButton(this.driverController,0);
    this.shootLower = new JoystickButton(this.driverController, XboxController.Button.kB.value);
    
    // Configure the button bindings
    this.configureButtonBindings();
  }

  private void configureSubsystems(){
    this.driver = new Driver();
    this.intake = new Intake();
    this.hopper = new Hopper();
    this.shooter = new Shooter();
    this.climb = new Climb();
  }
  private void configureButtonBindings() {
    this.a.toggleWhenPressed(new Accelerate(0.8, this.shooter));
    this.shootLower.whileHeld(new ShootLower(this.shooter, this.hopper));
  }


  public Command getAutonomousCommand() {
    return null;
  }
}
