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
    private CANSparkMax frontLeftFlyWheel = MotorControllerFactory.createSparkMax(Constants.Intake.FRONT_LEFT_FLYWHEEL_PORT,MotorConfig.NEO);
    private CANSparkMax frontRightFlyWheel = MotorControllerFactory.createSparkMax(Constants.Intake.FRONT_RIGHT_FLYWHEEL_PORT,MotorConfig.NEO);
    private CANSparkMax backLeftFlyWheel = MotorControllerFactory.createSparkMax(Constants.Intake.BACK_LEFT_FLYWHEEL_PORT,MotorConfig.NEO);
    private CANSparkMax backRightFlyWheel = MotorControllerFactory.createSparkMax(Constants.Intake.BACK_RIGHT_FLYWHEEL_PORT,MotorConfig.NEO);
    private MotorControllerGroup frontFlywheels = new MotorControllerGroup(frontLeftFlyWheel,frontRightFlyWheel);
    private MotorControllerGroup backFlywheels = new MotorControllerGroup(backLeftFlyWheel,backRightFlyWheel);
    RelativeEncoder leftEncoder1 = frontLeftFlyWheel.getEncoder();
    RelativeEncoder rightEncoder1 = frontRightFlyWheel.getEncoder();
    private double savedVelocity;
    private boolean firstTime = false;
    Timer timer = new Timer();

    

    public void shoot() {
        frontFlywheels.set(1);
        backFlywheels.set(1);
    }
    public void stop() {
        frontFlywheels.set(0);
        backFlywheels.set(0);
    }
    
    

    @Override
    public void periodic() {
        
        frontFlywheels.set(-0.1);
        backFlywheels.set(-0.1);
        
        timer.start();
        if(timer.get() == 0.5 && !firstTime) {
        savedVelocity = leftEncoder1.getVelocity();
        firstTime = true;
        timer.reset();
        }
        
        SmartDashboard.putNumber("left Encoder Velocity", savedVelocity);
        SmartDashboard.getBoolean("First time", firstTime);

        if(savedVelocity != leftEncoder1.getVelocity()) {
            frontFlywheels.set(0);
            backFlywheels.set(0);
        }
    }
}
