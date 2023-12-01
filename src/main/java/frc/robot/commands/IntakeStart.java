package frc.robot.commands;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.Subsystems.Intake;

public class IntakeStart extends CommandBase {
    private final CANSparkMax frontLeft;
    private final CANSparkMax frontRight;
    private final CANSparkMax backLeft;
    private final CANSparkMax backRight;
    Timer startingTimer = new Timer();
    //TODO: Have intake outake rather have logic in command
    public IntakeStart(CANSparkMax frontLeft, CANSparkMax frontRight, CANSparkMax backLeft, CANSparkMax backRight) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;
    }
    @Override 
    public void initialize() {
        frontLeft.set(0.1);
        frontRight.set(-0.1);
        backLeft.set(0.1);
        backRight.set(-0.1);
    }
}
