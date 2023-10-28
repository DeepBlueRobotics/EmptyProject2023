// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.SubSystems.Drivetrain;
import edu.wpi.first.wpilibj.XboxController;
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
//___________________________________________________________________
//Kenneth's Valuble Variables
  private Drivetrain drivetrain = new Drivetrain();
  private XboxController controller = new XboxController(0);
  Timer timer = new Timer();
  
//___________________________________________________________________
  private RobotContainer m_robotContainer;
  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    timer.start();
    drivetrain.drive(0.5,0.5);
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }
  

  @Override
  public void autonomousPeriodic() {
    if (timer.get() > 10) {
      drivetrain.drive(0, 0);
    } 
  }

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }
  
  @Override
  public void teleopPeriodic() {
    double speed = -controller.getLeftY();
     double turn = controller.getLeftX();
    if (drivetrain.isTank()) {
      double left = speed - turn;
      double right = speed + turn;
      drivetrain.drive(left, right);
    }
   
    if (!drivetrain.isTank()) {
      double rightYAxis = -controller.getRightY();
      drivetrain.drive(speed,rightYAxis);
    }
  }

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}
}
