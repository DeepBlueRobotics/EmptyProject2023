// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
public class Autonomous extends CommandBase {
  /** Creates a new Autonomous. */
  private Drivetrain drivetrain;
  private final Timer timer = new Timer();
  public Autonomous(Drivetrain dtrain) {
    this.drivetrain = dtrain;
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.isAuto = true;
    timer.start();
    drivetrain.drive(0.5,0.5);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    timer.stop();
    drivetrain.isAuto = false;
    drivetrain.drive(0, 0);
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (timer.get() > 5) {
      return true;
    }
    return false;
  }
}
