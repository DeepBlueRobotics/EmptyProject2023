package frc.robot.Subsystems;

import java.lang.constant.Constable;

import org.carlmontrobotics.lib199.MotorConfig;
import org.carlmontrobotics.lib199.MotorControllerFactory;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
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
    public MotorControllerGroup frontFlywheels = new MotorControllerGroup(frontLeftFlyWheel,frontRightFlyWheel);
    public MotorControllerGroup backFlywheels = new MotorControllerGroup(backLeftFlyWheel,backRightFlyWheel);
    RelativeEncoder leftEncoder1 = frontLeftFlyWheel.getEncoder();
    RelativeEncoder rightEncoder1 = frontRightFlyWheel.getEncoder();
    private double savedIntakeVelocity;
    
    //Conditional booleans
    private boolean firstTime = true;
    private boolean checkOuttakeReachedSpeed = false;
    private boolean checkIntakeSpeedDecrease = false;
    private boolean checkIntakeSpeedIncreased = false;
    //Timers
    private final Timer startingTimer = new Timer();
    private final Timer delayTimer = new Timer();
    private final Timer failSafeTimer = new Timer();
    
    public void failSafeShoot() { // Runs when ball is held for over 3.8 seconds
        frontFlywheels.set(1);
        backFlywheels.set(1);
        Timer.delay(0.4);
        outtakeEnded();
    }

    public void shoot() { // Runs when right bumper is held down
        frontFlywheels.set(1);
        checkOuttakeReachedSpeed = true;
    }
    public void stop() { // stops flyhweel motors
        frontFlywheels.set(0);
        backFlywheels.set(0);
    }
    
    public void outtakeEnded() { // Runs when right bumper is released
        checkOuttakeReachedSpeed = false;
        frontFlywheels.set(-0.1);
        backFlywheels.set(-0.1);
        delayTimer.start();
        failSafeTimer.stop();
        failSafeTimer.reset();
    }
     
    public void initialize() { // Gets the intake automatically moving at the start
        startingTimer.start();
        frontFlywheels.set(-0.1);
        backFlywheels.set(-0.1);
    }
    public void savedVelocity() {
        savedIntakeVelocity = leftEncoder1.getVelocity();
        firstTime = false;
        checkIntakeSpeedDecrease = true;
        startingTimer.stop();
        startingTimer.reset();
    }

    @Override
    public void periodic() {
        
        if(checkOuttakeReachedSpeed) { // Checks if front wheels are at max speed for outtake
            if (leftEncoder1.getVelocity() >= Constants.Intake.MAX_VELOCITY - 0.05) { // the 0.05 is to make sure it checks because the battery level might decrease speed of mottors
                backFlywheels.set(0.5);
            }
        }
        
        if(startingTimer.get() >= 0.5 && firstTime) { // Saves the normal intake velocity at the start
            this.savedVelocity();
        }

        if(leftEncoder1.getVelocity() < savedIntakeVelocity && checkIntakeSpeedDecrease) { // Checks if inttake speed decrease
            checkIntakeSpeedIncreased = true;
            checkIntakeSpeedDecrease = false;
           }
        
        if(leftEncoder1.getVelocity() >= savedIntakeVelocity && checkIntakeSpeedIncreased) { // checks if inttake speed is restored to normal
            stop();
            failSafeTimer.start(); // Since the ball is inside fail safe starts counting
            checkIntakeSpeedIncreased = false;
        }  

        if(failSafeTimer.get() > 3.8) { //If ball is held for too long it is shot out to avoid penalty
            failSafeShoot();
            failSafeTimer.stop();
            failSafeTimer.reset();
        }

        if(delayTimer.get() > 0.5) { // Delay timer to give intake motors time to speed up after outtaking
            checkIntakeSpeedDecrease = true;
            delayTimer.stop();
            delayTimer.reset();
        }
        SmartDashboard.putNumber("left Encoder Velocity", savedIntakeVelocity);
        SmartDashboard.getBoolean("First time", firstTime);
    }
}

