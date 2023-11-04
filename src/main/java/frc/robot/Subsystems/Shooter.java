package frc.robot.Subsystems;

import org.carlmontrobotics.lib199.MotorConfig;
import org.carlmontrobotics.lib199.MotorControllerFactory;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    CANSparkMax leftShooter1 = MotorControllerFactory.createSparkMax(7,MotorConfig.NEO_550);
    CANSparkMax rightShooter1 = MotorControllerFactory.createSparkMax(10,MotorConfig.NEO_550);
    CANSparkMax leftShooter2 = MotorControllerFactory.createSparkMax(2,MotorConfig.NEO_550);
    CANSparkMax rightShooter2 = MotorControllerFactory.createSparkMax(3,MotorConfig.NEO_550);
    MotorControllerGroup firstSet = new MotorControllerGroup(leftShooter1,rightShooter1);
    MotorControllerGroup secondSet = new MotorControllerGroup(leftShooter2,rightShooter2);
    public boolean canIntake;

    public void shoot() {
        firstSet.set(1);
        secondSet.set(1);
    }
    public void stop() {
        firstSet.set(0);
        secondSet.set(0);
    }
    @Override
    public void periodic() {
        if (canIntake) {
            firstSet.set(-1);
            secondSet.set(-1);
        }
    }
}
