// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.XboxController;

public class RobotContainer {
  public final Drivetrain drivetrain = new Drivetrain();
  
  private static XboxController drivetrainController = new XboxController(2);
  public RobotContainer() {
    configureBindings();
    
  }
  public void periodic(){
    drivetrain.drive(0-drivetrainController.getLeftY(),0-drivetrainController.getRightY());
    
}

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
