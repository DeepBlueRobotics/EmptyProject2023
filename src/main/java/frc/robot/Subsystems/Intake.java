package frc.robot.Subsystems;

import java.lang.constant.Constable;

import org.carlmontrobotics.lib199.MotorConfig;
import org.carlmontrobotics.lib199.MotorControllerFactory;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
//backwheels 2600 Front wheels 4000
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    private CANSparkMax frontLeftFlyWheel = MotorControllerFactory.createSparkMax(Constants.Intake.FRONT_LEFT_FLYWHEEL_PORT,MotorConfig.NEO_550);
    private CANSparkMax frontRightFlyWheel = MotorControllerFactory.createSparkMax(Constants.Intake.FRONT_RIGHT_FLYWHEEL_PORT,MotorConfig.NEO_550);
    private CANSparkMax backLeftFlyWheel = MotorControllerFactory.createSparkMax(Constants.Intake.BACK_LEFT_FLYWHEEL_PORT,MotorConfig.NEO_550);
    private CANSparkMax backRightFlyWheel = MotorControllerFactory.createSparkMax(Constants.Intake.BACK_RIGHT_FLYWHEEL_PORT,MotorConfig.NEO_550);
    private MotorControllerGroup frontFlywheels = new MotorControllerGroup(frontLeftFlyWheel,frontRightFlyWheel);
    private MotorControllerGroup backFlywheels = new MotorControllerGroup(backLeftFlyWheel,backRightFlyWheel);
    RelativeEncoder leftEncoder1 = frontLeftFlyWheel.getEncoder();
    RelativeEncoder rightEncoder1 = frontRightFlyWheel.getEncoder();
    private double savedIntakeVelocity;
    private double outtakeMaxVelocity; // ASSIGN THIS TO THE MAXIMUM VELOCITY
    private boolean firstTime = true;
    private boolean checkOuttakeReachedSpeed = false;
    private boolean checkIntakeSpeedDecrease = false;
    private boolean checkIntakeSpeedIncreased = false;
    private final Timer timer = new Timer();

    

    public void shoot() {
        frontFlywheels.set(1);
        checkOuttakeReachedSpeed = true;
    }
    public void stop() {
        frontFlywheels.set(0);
        backFlywheels.set(0);
    }
    
    public void outtakeEnded() {
        checkOuttakeReachedSpeed = false;
        frontFlywheels.set(-0.1);
        backFlywheels.set(-0.1);
        Timer.delay(0.5);
        checkIntakeSpeedDecrease = true;
    }

    public void initialize() {
        timer.start();
        frontFlywheels.set(-0.1);
        backFlywheels.set(-0.1);
    }

    @Override
    public void periodic() {
        
        
        if(checkOuttakeReachedSpeed) { // Checks if front wheels are at max speed
            if (leftEncoder1.getVelocity() >= outtakeMaxVelocity - 0.05) { // the 0.05 is to make sure it checks because the battery level might decrease speed of mottors
                backFlywheels.set(0.5);
            }
        }
        
        if(timer.get() >= 0.5 && firstTime) {
            savedIntakeVelocity = leftEncoder1.getVelocity();
            firstTime = true;
            checkIntakeSpeedDecrease = true;
            timer.reset();
        }

        if(leftEncoder1.getVelocity() < savedIntakeVelocity && checkIntakeSpeedDecrease) { // Checks if ball is in
            checkIntakeSpeedIncreased = true;
            checkIntakeSpeedDecrease = false;
           }
        
        if(leftEncoder1.getVelocity() >= savedIntakeVelocity && checkIntakeSpeedIncreased) {
            stop();
            checkIntakeSpeedIncreased = false;
        }   
        SmartDashboard.putNumber("left Encoder Velocity", savedIntakeVelocity);
        SmartDashboard.getBoolean("First time", firstTime);
    }
}
