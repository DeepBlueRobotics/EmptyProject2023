package frc.robot.Subsystems;

import java.lang.constant.Constable;

import org.carlmontrobotics.lib199.MotorConfig;
import org.carlmontrobotics.lib199.MotorControllerFactory;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
//backwheels 2600 Front wheels 4000
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    //private CANSparkMax frontLeftFlyWheel = MotorControllerFactory.createSparkMax(Constants.Intake.FRONT_LEFT_FLYWHEEL_PORT,MotorConfig.NEO);
    //private CANSparkMax frontRightFlyWheel = MotorControllerFactory.createSparkMax(Constants.Intake.FRONT_RIGHT_FLYWHEEL_PORT,MotorConfig.NEO);
    private CANSparkMax backLeftFlyWheel = MotorControllerFactory.createSparkMax(Constants.Intake.BACK_LEFT_FLYWHEEL_PORT,MotorConfig.NEO);
    private CANSparkMax backRightFlyWheel = MotorControllerFactory.createSparkMax(Constants.Intake.BACK_RIGHT_FLYWHEEL_PORT,MotorConfig.NEO);
    //private MotorControllerGroup frontFlywheels = new MotorControllerGroup(frontLeftFlyWheel,frontRightFlyWheel);
    private MotorControllerGroup backFlywheels = new MotorControllerGroup(backLeftFlyWheel,backRightFlyWheel);
    private boolean didShoot = false;
    private boolean isHolding = false;
    private DigitalInput limitSwitch = new DigitalInput(0);
    //private RelativeEncoder leftEncoder1 = frontLeftFlyWheel.getEncoder();
    //private RelativeEncoder rightEncoder1 = frontRightFlyWheel.getEncoder();
    private double shooterPower = 0.5;
    private double maxVelocity;
    // private boolean touchingball = false;
    public Intake() {
        SmartDashboard.putNumber("Motor Voltage", 0);
    }
    //Timers
    private final Timer failSafeTimer = new Timer();
    private final Timer delayTimer = new Timer();
    //Getters for all motors
    /* */
    /* 
    public CANSparkMax frontLeftFlyWheel() {
        return frontLeftFlyWheel;
    }
    public CANSparkMax frontRightFlyWheel() {
        return frontRightFlyWheel;
    }
    */
    public CANSparkMax backLeftFlyWheel() {
        return backLeftFlyWheel;
    }
    public CANSparkMax backRightFlyWheel() {
        return backRightFlyWheel;
    }
    public void failSafeShoot() { // Runs when ball is held for over 3.8 seconds
      //  frontLeftFlyWheel.set(-0.25);
      //  frontRightFlyWheel.set(0.25);
        backLeftFlyWheel.set(-0.25);
        backRightFlyWheel.set(0.25);
       /* 
        failSafeDelayTimer.start();
        while(failSafeDelayTimer.get() < 0.5) {
            ;
        }
        outtakeEnded();
        failSafeDelayTimer.stop();
        failSafeDelayTimer.reset();
        */
    }

    public void shoot() { // Runs when right bumper is held down
        
        backLeftFlyWheel.set(-shooterPower);
        backRightFlyWheel.set(shooterPower);
        /* 
        delayTimer.start();
        while(!didShoot) {
            if(delayTimer.get() > 0.5) {
                backLeftFlyWheel.set(-shooterPower);
                backRightFlyWheel.set(shooterPower);
                didShoot = true;
            }
        }
        delayTimer.stop();
        delayTimer.reset();

        delayTimer.start();
        while(delayTimer.get() < 1) {
            ;
        }
        outtakeEnded();
        delayTimer.stop();
        delayTimer.reset();
        didShoot=false;
        */
    }
    
    public void stop() { // stops flyhweel motors
        //frontFlywheels.set(0);
        backFlywheels.set(0);
    }

    public void outtakeEnded() { // Runs when right bumper is released
        //frontLeftFlyWheel.set(0.15);
        //frontRightFlyWheel.set(-0.15);
        backLeftFlyWheel.set(0.15);
        backRightFlyWheel.set(-0.15);
        failSafeTimer.stop();
        failSafeTimer.reset();
        isHolding = false;
    }
    public void startIntake() { // Runs when right bumper is released
       // frontLeftFlyWheel.set(0.1);
       // frontRightFlyWheel.set(-0.1);
        backLeftFlyWheel.set(0.1);
        backRightFlyWheel.set(-0.1);
        
    }
    public void switchPower(double power) {
       shooterPower = power;
    }

    @Override
    public void periodic() {
        //Limit switch stop
        if(!limitSwitch.get() && !isHolding) {
            //frontLeftFlyWheel.set(0);
           // frontRightFlyWheel.set(0);
            backLeftFlyWheel.set(0.1);
            backRightFlyWheel.set(-0.1);
            isHolding = true;
        }

        //Back up code for intake
        // if(leftEncoder1.getVelocity() < maxVelocity) {
        //     touchingball = true;
        // }
        // if(touchingball == true && leftEncoder1.getVelocity() >= maxVelocity) {
        //     stop();
        //     failSafeTimer.start();
        //     touchingball = false;
        //     isHolding = true;
        // }

/* 
        if(isHolding && failSafeTimer.get()>3.3) {
            failSafeShoot();
        */      

        /* 
        if(leftEncoder1.getVelocity() < maxVelocity-Constants.Intake.VELOCITY_DECREASE) {
            stop();
            failSafeTimer.start();
            isHolding = true;
        }
        */
    
    
    }
}