package frc.robot.subsystems;
import org.carlmontrobotics.lib199.MotorControllerFactory;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase{

    CANSparkMax leftMotor = MotorControllerFactory.createSparkMax(0, MotorConfig.NEO);
    CANSparkMax rightMotor = MotorControllerFactory.createSparkMax(1, MotorConfig.NEO);
    public Drivetrain(){

    }  

    public void drive(double lefty, double righty){
        leftMotor.set(lefty);
        rightMotor.set(righty);
    }
    
}
