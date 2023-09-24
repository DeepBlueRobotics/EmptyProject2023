
package frc.robot.subsystems;


import java.util.Arrays;
import java.util.function.Supplier;
import edu.wpilipj.GenericHID();

import org.carlmontrobotics.MotorConfig;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.XboxController.Axis;

public class Drivetrain extends emptySubsystem {
    

    CANSparkMax leftMotor = MotorControllerFactory.createSparkMax(1, MotorConfig.NEO);
    CANSparkMax rightMotor = MotorControllerFactory.createSparkMax(2, MotorConfig.NEO);
    public static final Axis drivetrainController = new XboxController(0);
    

        
    public Drivetrain() {
         
    }

    public void periodic() {
        leftMotor.set(0-getStickValue(1,Axis.kLeftY));
        rightMotor.set(0-getStickValue(2,Axis.kRightY));

    }
}

