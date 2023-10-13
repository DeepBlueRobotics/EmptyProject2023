package frc.robot.subsystems;

import org.carlmontrobotics.lib199.MotorControllerFactory;

import com.revrobotics.CANSparkMax;

public class Arm {
    CANSparkMax armLeftMotor = MotorControllerFactory.createSparkMax(3, MotorConfig.NEO);
    CANSparkMax armRightMotor = MotorControllerFactory.createSparkMax(4, MotorConfig.NEO);
    
}
