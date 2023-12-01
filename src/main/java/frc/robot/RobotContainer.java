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
  private final XboxController controller = new XboxController(Constants.OI.CONTROLLER_PORT);
  private Drivetrain drivetrain = new Drivetrain(controller);
  
  
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(controller, Button.kBack.value).onTrue(new InstantCommand(drivetrain::swap));
    new JoystickButton(controller, Button.kStart.value).onTrue(new InstantCommand(shooter::stop));
    new JoystickButton(controller, Button.kRightBumper.value).onTrue(new InstantCommand(shooter::shoot));
    new JoystickButton(controller, Button.kLeftBumper.value).onTrue(new InstantCommand(drivetrain::speedSwap));
    new JoystickButton(controller, Button.kA.value).onTrue(new InstantCommand(() -> {shooter.switchPower(Constants.Intake.MAX_SPEED);}));
    new JoystickButton(controller, Button.kB.value).onTrue(new InstantCommand(() -> {shooter.switchPower(Constants.Intake.SLOW_SPEED);}));
    new JoystickButton(controller, Button.kY.value).onTrue(new InstantCommand(() -> {shooter.switchPower(Constants.Intake.MID_SPEED);}));
    new JoystickButton(controller, Button.kX.value).onTrue(new InstantCommand(() -> {shooter.switchPower(Constants.Intake.STRONG_SPEED);}));
  }
  public Command startIntake() {
    return new IntakeStart(shooter.frontLeftFlyWheel(), shooter.frontRightFlyWheel(), shooter.backLeftFlyWheel(), shooter.backRightFlyWheel());
  }

  public Command getAutonomousCommand() {
    return new Autodrive(drivetrain);
  }
}
