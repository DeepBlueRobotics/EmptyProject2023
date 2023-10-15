package frc.robot.Subsystems;
import com.revrobotics.CANSparkMax;


import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import org.carlmontrobotics.lib199.MotorControllerFactory;
import org.carlmontrobotics.lib199.MotorConfig;
public class Drivetrain {
CANSparkMax motor1 = MotorControllerFactory.createSparkMax(1,MotorConfig.NEO_550);
CANSparkMax motor2 = MotorControllerFactory.createSparkMax(2,MotorConfig.NEO_550);
CANSparkMax motor3 = MotorControllerFactory.createSparkMax(3,MotorConfig.NEO_550);
CANSparkMax motor4 = MotorControllerFactory.createSparkMax(4,MotorConfig.NEO_550);
CANSparkMax motor5 = MotorControllerFactory.createSparkMax(5,MotorConfig.NEO_550);
CANSparkMax motor6 = MotorControllerFactory.createSparkMax(6,MotorConfig.NEO_550);

public void drive(double x1, double x2, double y1, double y2) {
    
}
}
