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
    private CANSparkMax frontLeftFlyWheel = MotorControllerFactory.createSparkMax(Constants.Intake.FRONT_LEFT_FLYWHEEL_PORT,MotorConfig.NEO);
    private CANSparkMax frontRightFlyWheel = MotorControllerFactory.createSparkMax(Constants.Intake.FRONT_RIGHT_FLYWHEEL_PORT,MotorConfig.NEO);
    private CANSparkMax backLeftFlyWheel = MotorControllerFactory.createSparkMax(Constants.Intake.BACK_LEFT_FLYWHEEL_PORT,MotorConfig.NEO);
    private CANSparkMax backRightFlyWheel = MotorControllerFactory.createSparkMax(Constants.Intake.BACK_RIGHT_FLYWHEEL_PORT,MotorConfig.NEO);
    private MotorControllerGroup frontFlywheels = new MotorControllerGroup(frontLeftFlyWheel,frontRightFlyWheel);
    private MotorControllerGroup backFlywheels = new MotorControllerGroup(backLeftFlyWheel,backRightFlyWheel);
    private boolean didShoot = false;
    private boolean isHolding = false;
    private DigitalInput limitSwitch = new DigitalInput(0);
    private RelativeEncoder leftEncoder1 = frontLeftFlyWheel.getEncoder();
    private RelativeEncoder rightEncoder1 = frontRightFlyWheel.getEncoder();
    private double shooterPower = 1;
    private double maxVelocity;
    // private boolean touchingball = false;
    public Intake() {
        SmartDashboard.putNumber("Motor Voltage", 0);
    }
    //Timers
    private final Timer failSafeTimer = new Timer();
    private final Timer delayTimer = new Timer();

    public CANSparkMax frontLeftFlyWheel() {
        return frontLeftFlyWheel;
    }
    public CANSparkMax frontRightFlyWheel() {
        return frontRightFlyWheel;
    }
    public CANSparkMax backLeftFlyWheel() {
        return backLeftFlyWheel;
    }
    public CANSparkMax backRightFlyWheel() {
        return backRightFlyWheel;
    }
    public void failSafeShoot() { // Runs when ball is held for over 3.8 seconds
        frontLeftFlyWheel.set(-1);
        frontRightFlyWheel.set(1);
        backLeftFlyWheel.set(-1);
        backRightFlyWheel.set(1);
        outtakeEnded();
    }

    public void shoot() { // Runs when right bumper is held down
        frontLeftFlyWheel.set(-shooterPower);
        frontRightFlyWheel.set(shooterPower);
        delayTimer.start();
        while(!didShoot) {
            if(delayTimer.get() > 0.5) {
                backLeftFlyWheel.set(-1);
                backRightFlyWheel.set(1);
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
    }
    
    public void stop() { // stops flyhweel motors
        frontFlywheels.set(0);
        backFlywheels.set(0);
    }

    public void outtakeEnded() { // Runs when right bumper is released
        frontLeftFlyWheel.set(0.1);
        frontRightFlyWheel.set(-0.1);
        backLeftFlyWheel.set(0.1);
        backRightFlyWheel.set(-0.1);
        failSafeTimer.stop();
        failSafeTimer.reset();
        isHolding = false;
    }

    public void switchPower(double power) {
       shooterPower = power;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Left motor Velocity", leftEncoder1.getVelocity());
        SmartDashboard.putNumber("Right motor velocity", rightEncoder1.getVelocity());
        if(!limitSwitch.get() && !isHolding) {
            stop();
            failSafeTimer.start();
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


        if(isHolding && failSafeTimer.get()>3.8) {
            failSafeShoot();
        }
        if (leftEncoder1.getVelocity() > maxVelocity) {
            maxVelocity = leftEncoder1.getVelocity();
        }

        /* 
        if(leftEncoder1.getVelocity() < maxVelocity-Constants.Intake.VELOCITY_DECREASE) {
            stop();
            failSafeTimer.start();
            isHolding = true;
        }
        */

        SmartDashboard.putNumber("Max Velocity", maxVelocity);
        SmartDashboard.putBoolean("limit switch", limitSwitch.get());
    }
}