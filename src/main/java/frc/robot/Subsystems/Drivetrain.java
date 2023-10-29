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
CANSparkMax leftMotors = MotorControllerFactory.createSparkMax(1,MotorConfig.NEO_550);
CANSparkMax rightMotors = MotorControllerFactory.createSparkMax(2,MotorConfig.NEO_550);
public boolean isTank = false;

public void driveForward(double y1,double y2) {
    leftMotors.set(y1);
    rightMotors.set(y2);
}

public void swap () {
    isTank = !isTank;
}

public boolean isTank() {
 return isTank;
}

}
