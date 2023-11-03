package frc.robot.Subsystems;

import org.carlmontrobotics.lib199.MotorConfig;
import org.carlmontrobotics.lib199.MotorControllerFactory;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    CANSparkMax leftShooter = MotorControllerFactory.createSparkMax(7,MotorConfig.NEO_550);
    CANSparkMax rightShooter = MotorControllerFactory.createSparkMax(10,MotorConfig.NEO_550);
    public void shoot() {
        leftShooter.set(1);
        rightShooter.set(1);
    }
    public void inTake() {
        leftShooter.set(-1);
        rightShooter.set(-1);
    }
    public void stop() {
        leftShooter.set(0);
        rightShooter.set(0);
    }
}
