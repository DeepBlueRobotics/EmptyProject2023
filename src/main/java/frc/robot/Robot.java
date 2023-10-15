// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  private WPI_TalonSRX leftMaster = new WPI_TalonSRX(3);
  private WPI_TalonSRX rightMaster = new WPI_TalonSRX(1);
  private WPI_VictorSPX leftSlave = new WPI_VictorSPX(1);
  private WPI_VictorSPX rightSlave = new WPI_VictorSPX(2);
  private WPI_TalonSRX armMotor = new WPI_TalonSRX(5);
  private WPI_VictorSPX armSlave = new WPI_VictorSPX(3);
  private WPI_TalonSRX rollerMotor = new WPI_TalonSRX(4);
  //private Compressor compressor = new Compressor();

  private Joystick driverJoystick = new Joystick(0);
  private final double kDriveTick2Feet = 1.0 / 4096 * 6 * Math.PI / 12;
  private DifferentialDrive drive = new DifferentialDrive(leftMaster, rightMaster);
  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    leftMaster.setInverted(true);
    rightMaster.setInverted(true);
    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);
    leftSlave.setInverted(InvertType.FollowMaster);
    rightSlave.setInverted(InvertType.FollowMaster);
    leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    leftMaster.setSensorPhase(false);
    leftMaster.setSensorPhase(true);
    leftMaster.setSelectedSensorPosition(0, 0, 10);
    rightMaster.setSelectedSensorPosition(0, 0, 10);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("Left drive encoder value", leftMaster.getSelectedSensorPosition() * kDriveTick2Feet);
    SmartDashboard.putNumber("Right drive encoder value", leftMaster.getSelectedSensorPosition() * kDriveTick2Feet);
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
  }

  @Override
  public void teleopPeriodic() {
    double power = -driverJoystick.getRawAxis(1);
    double turn = driverJoystick.getRawAxis(4);
    if(Math.abs(turn) < 0.05) {
      turn = 0;
    }
    if(Math.abs(power)  < 0.05) {
      power = 0;
    }
    drive.arcadeDrive(power * 0.6, turn * 0.3);
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
