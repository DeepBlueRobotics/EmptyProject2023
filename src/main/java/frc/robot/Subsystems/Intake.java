package frc.robot.Subsystems;

import java.lang.constant.Constable;

import org.carlmontrobotics.lib199.MotorConfig;
import org.carlmontrobotics.lib199.MotorControllerFactory;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
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
    private boolean shot = false;
    private boolean holding = false;
    DigitalInput limitSwitch = new DigitalInput(0);
    RelativeEncoder leftEncoder1 = frontLeftFlyWheel.getEncoder();
    RelativeEncoder rightEncoder1 = frontRightFlyWheel.getEncoder();
    PIDController pidController = new PIDController(Constants.Intake.KP, Constants.Intake.KI, Constants.Intake.KD);
    
    
    //Timers
    private final Timer failSafeTimer = new Timer();
    private final Timer delayTimer = new Timer();

    public MotorControllerGroup frontFlywheels() {
        return frontFlywheels;
    }
    public MotorControllerGroup backFlywheels() {
        return backFlywheels;
    }
    public void failSafeShoot() { // Runs when ball is held for over 3.8 seconds
        frontFlywheels.set(1);
        backFlywheels.set(1);
        outtakeEnded();
    }

     public void checkOuttakeReachedSpeed() {
        if (leftEncoder1.getVelocity() >= Constants.Intake.MAX_VELOCITY - 0.05) { // the 0.05 is to make sure it checks because the battery level might decrease speed of mottors
                backFlywheels.set(0.5);
        }
    }

    public void shoot() { // Runs when right bumper is held down
        frontFlywheels.set(1);
        delayTimer.start();
        while(!shot) {
        if(delayTimer.get() > 0.5) {
            backFlywheels.set(1);
            shot = true;
        }
    }
        delayTimer.stop();
        delayTimer.reset();
        shot=false;
    }
    public void stop() { // stops flyhweel motors
        frontFlywheels.set(0);
        backFlywheels.set(0);
    }

    public void outtakeEnded() { // Runs when right bumper is released
        frontFlywheels.set(-0.1);
        backFlywheels.set(-0.1);
        failSafeTimer.stop();
        failSafeTimer.reset();
    }
    
     
   

   

    @Override
    public void periodic() {
        SmartDashboard.putNumber("left Encoder Velocity", leftEncoder1.getVelocity());
        if(limitSwitch.get()) {
            stop();
            failSafeTimer.start();
            holding = true;
        }
        if(holding && failSafeTimer.get()>3.8) {
            failSafeShoot();
        }


    }
}

