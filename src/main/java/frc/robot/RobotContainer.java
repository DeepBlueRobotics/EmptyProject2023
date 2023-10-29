// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.SubSystems.Drivetrain;
import frc.SubSystems.Flywheel;
import frc.robot.Constants;

import java.sql.Driver;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RobotContainer {
  public final GenericHID driverController = new GenericHID(0);
  public Drivetrain drivetrain = new Drivetrain();
  public Flywheel flywheel = new Flywheel();
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
      new JoystickButton(driverController, Constants.SWITCH_BUTTON).onTrue(new InstantCommand(()->{drivetrain.swap();}));
      new JoystickButton(driverController, Constants.OUT_TAKE).onTrue(new InstantCommand(()->{flywheel.out_take(1);}));
      new JoystickButton(driverController, Constants.OUT_TAKE).onFalse(new InstantCommand(() -> {flywheel.out_take(0);}));
      new JoystickButton(driverController, Constants.INTAKE).onTrue(new InstantCommand(()->{flywheel.intake(1);}));
      new JoystickButton(driverController, Constants.INTAKE).onFalse(new InstantCommand(() -> {flywheel.out_take(0);}));
    }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
