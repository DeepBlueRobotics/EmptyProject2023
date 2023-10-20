package frc.robot.Subsystems;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import org.carlmontrobotics.lib199.MotorControllerFactory;
import org.carlmontrobotics.lib199.MotorConfig;
public class Drivetrain {
CANSparkMax motor1 = MotorControllerFactory.createSparkMax(1,MotorConfig.NEO_550);
CANSparkMax motor2 = MotorControllerFactory.createSparkMax(2,MotorConfig.NEO_550);
CANSparkMax motor3 = MotorControllerFactory.createSparkMax(3,MotorConfig.NEO_550);
CANSparkMax motor4 = MotorControllerFactory.createSparkMax(4,MotorConfig.NEO_550);
CANSparkMax motor5 = MotorControllerFactory.createSparkMax(5,MotorConfig.NEO_550);
CANSparkMax motor6 = MotorControllerFactory.createSparkMax(6,MotorConfig.NEO_550);
MotorControllerGroup leftMotors = new MotorControllerGroup(motor1,motor2,motor3);
MotorControllerGroup rightMotors = new MotorControllerGroup(motor4, motor5, motor6);


public void driveForward(double y1,double y2) {
    leftMotors.set(y1);
    rightMotors.set(y2);
}
public void turn(double x1) {
    if(x1>0) {
        leftMotors.set(x1);
        rightMotors.set(1-x1);
    } 
    if(x1<0) {
        leftMotors.set(1-x1);
        rightMotors.set(x1);
    }
}
}
