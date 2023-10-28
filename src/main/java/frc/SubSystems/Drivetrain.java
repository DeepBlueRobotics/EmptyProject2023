package frc.SubSystems;
import org.carlmontrobotics.lib199.MotorControllerFactory;
import org.carlmontrobotics.lib199.MotorConfig;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class Drivetrain {
    boolean tank = false;
    CANSparkMax rightMotor = MotorControllerFactory.createSparkMax(1,MotorConfig.NEO);
    CANSparkMax leftMotor = MotorControllerFactory.createSparkMax(2,MotorConfig.NEO);
   
    public void drive(double left,double right) {
        rightMotor.set(right);
        leftMotor.set(left);
    }
    public boolean swap() {
        if (tank) {
            tank = false;
        }
        if (!tank) {
            tank = true;
        }
        return tank;
    }
}