/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
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
  public static Driver driver = new Driver();
  public static Intake intake = new Intake();
  public static Hopper hopper = new Hopper();
  public static Shooter shooter = new Shooter();
  public static Climb climb = new Climb();
  
  // private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public static Joystick driverController;
  public JoystickButton a;
  public JoystickButton manualShoot;
  public JoystickButton shootLower;
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    driverController = new Joystick(0);
    a = new JoystickButton(driverController,0);
    manualShoot = new JoystickButton(driverController,0);
    shootLower = new JoystickButton(driverController, 0);
    
    // Configure the button bindings
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    a.toggleWhenPressed(new InstantCommand(shooter::switchAccelerate,shooter));
    shootLower.whileHeld(new ShootLower());
    
  }


  public Command getAutonomousCommand() {
    return null;
  }
}
