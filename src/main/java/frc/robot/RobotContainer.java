// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.SubSystems.Drivetrain;

import java.sql.Driver;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.InstantCommand;
public class RobotContainer {
  public XboxController controller = new XboxController(0);
  public final GenericHID driverController = new GenericHID(0);
  public Drivetrain drivetrain = new Drivetrain();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
      new JoystickButton(driverController, Button.kX.value).onTrue(new InstantCommand(()->{drivetrain.swap();}));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
