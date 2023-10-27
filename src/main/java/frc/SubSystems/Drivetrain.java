package frc.SubSystems;
import org.carlmontrobotics.lib199.MotorControllerFactory;
import org.carlmontrobotics.lib199.MotorConfig;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class Drivetrain {
    CANSparkMax rightMotor1 = MotorControllerFactory.createSparkMax(1,MotorConfig.NEO);
    CANSparkMax rightMotor2 = MotorControllerFactory.createSparkMax(2,MotorConfig.NEO);
    CANSparkMax leftMotor1 = MotorControllerFactory.createSparkMax(3,MotorConfig.NEO);
    CANSparkMax leftMotor2 = MotorControllerFactory.createSparkMax(4,MotorConfig.NEO);
    MotorControllerGroup rightGroup = new MotorControllerGroup(rightMotor1, rightMotor2);
    MotorControllerGroup leftGroup = new MotorControllerGroup(leftMotor1, leftMotor2);
    public void drive(double goober,double yoober) {
        rightGroup.set(yoober);
        leftGroup.set(goober);
    }
}