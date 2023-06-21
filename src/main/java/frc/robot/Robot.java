// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

public class Robot extends TimedRobot {
  private static boolean boo = false;

  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private final XboxController remote = new XboxController(0);


  public final DoubleSolenoid soley = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 1);

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

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    SmartDashboard.putBoolean("GER", boo);

    boo = SmartDashboard.getBoolean("GER", boo);
  }

  @Override
  public void teleopPeriodic() 
  {
   // How to code a solenoid with a controller
    /*if(remote.getLeftBumperPressed())
    {
      daisy.set(DoubleSolenoid.Value.kForward);
    }
    else if (remote.getRightBumperPressed())
    {
      daisy.set(DoubleSolenoid.Value.kReverse);
    } 
  */

  if (boo = true) {
      soley.set(DoubleSolenoid.Value.kForward);
    }
    else if (boo = false)

    {
      soley.set(DoubleSolenoid.Value.kReverse);
    }
   SmartDashboard.getBoolean("GER", boo);
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
