// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController.Axis;
import org.carlmontrobotics.robotcode2023.subsystems.Drivetrain;

import org.tea199.EmptyProject2023.subsystems.Drivetrain;
public class RobotContainer {

  public RobotContainer() {

   public final Drivetrain drivetrain = new Drivetrain();

    
  }
    
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
