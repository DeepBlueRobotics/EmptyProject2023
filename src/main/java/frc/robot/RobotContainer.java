// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Subsystems.Drivetrain;
import frc.robot.Subsystems.Intake;
import frc.robot.commands.Autodrive;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;

public class RobotContainer {
  private final Intake shooter = new Intake();
  private final XboxController controller = new XboxController(Constants.OI.CONTROLLER_PORT);
  private Drivetrain drivetrain = new Drivetrain(controller);
  
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(controller, Button.kX.value).onTrue(new InstantCommand(drivetrain::swap));
    new JoystickButton(controller, Button.kRightBumper.value).onTrue(new InstantCommand(shooter::shoot));
    new JoystickButton(controller, Button.kRightBumper.value).onFalse(new InstantCommand(shooter::stop));
  }

  public Command getAutonomousCommand() {
    return new Autodrive(drivetrain, shooter);
  }
}
