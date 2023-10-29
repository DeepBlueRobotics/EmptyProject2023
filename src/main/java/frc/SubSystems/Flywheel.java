package frc.SubSystems;

import org.carlmontrobotics.lib199.MotorConfig;
import org.carlmontrobotics.lib199.MotorControllerFactory;

import com.revrobotics.CANSparkMax;

public class Flywheel {
    CANSparkMax flyWheel1 = MotorControllerFactory.createSparkMax(3,MotorConfig.NEO);
    CANSparkMax flyWheel2 = MotorControllerFactory.createSparkMax(3, MotorConfig.NEO);
    public void intake() {
        
    }
    public void out_take() {

    }
}
