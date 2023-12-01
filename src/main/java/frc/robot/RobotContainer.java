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
import frc.robot.commands.IntakeStart;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
public class RobotContainer {
  private final Intake shooter = new Intake();
  private final XboxController driverController = new XboxController(Constants.OI.DRIVER_CONTROLLER_PORT);
  private final XboxController manipulatorController = new XboxController(Constants.OI.MANIPULATOR_CONTROLLER_PORT);
  private Drivetrain drivetrain = new Drivetrain(driverController);
  
  
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    //Driver buttons
    new JoystickButton(driverController, Button.kRightBumper.value).onTrue(new InstantCommand(shooter::shoot));
    new JoystickButton(driverController, Button.kLeftBumper.value).onTrue(new InstantCommand(drivetrain::speedSwap));
    new JoystickButton(driverController, Button.kX.value).onTrue(new InstantCommand(drivetrain::swap));
    //Manipulator buttons
    new JoystickButton(manipulatorController, Button.kRightBumper.value).onTrue(new InstantCommand(shooter::stop));
    new JoystickButton(manipulatorController, Button.kA.value).onTrue(new InstantCommand(() -> {shooter.switchPower(Constants.Intake.MAX_SPEED);}));
    new JoystickButton(manipulatorController, Button.kB.value).onTrue(new InstantCommand(() -> {shooter.switchPower(Constants.Intake.LOW_SPEED);}));
    new JoystickButton(manipulatorController, Button.kY.value).onTrue(new InstantCommand(() -> {shooter.switchPower(Constants.Intake.MID_SPEED);}));
    new JoystickButton(manipulatorController, Button.kX.value).onTrue(new InstantCommand(() -> {shooter.switchPower(Constants.Intake.STRONG_SPEED);}));
  }
  public Command startIntake() {
    return new IntakeStart(shooter, shooter.frontFlywheels(), shooter.backFlywheels());
  }

  public Command getAutonomousCommand() {
    return new Autodrive(drivetrain);
  }
}
