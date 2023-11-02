// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Autonomous;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Flywheel;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RobotContainer {
  public Flywheel flywheel = new Flywheel();
  public XboxController controller = new XboxController(0);
  private Drivetrain drivetrain = new Drivetrain(controller);
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
      new JoystickButton(controller, Constants.SWITCH_BUTTON).onTrue(new InstantCommand(()->{drivetrain.swap();}));
      new JoystickButton(controller, Constants.OUT_TAKE).onTrue(new InstantCommand(()->{flywheel.out_take(1);}));
      new JoystickButton(controller, Constants.OUT_TAKE).onFalse(new InstantCommand(() -> {flywheel.out_take(0);}));
      new JoystickButton(controller, Constants.IN_TAKE).onTrue(new InstantCommand(()->{flywheel.intake(1);}));
      new JoystickButton(controller, Constants.IN_TAKE).onFalse(new InstantCommand(() -> {flywheel.out_take(0);}));
  }

  public Command getAutonomousCommand() {
    return new Autonomous(drivetrain);
  }
}
