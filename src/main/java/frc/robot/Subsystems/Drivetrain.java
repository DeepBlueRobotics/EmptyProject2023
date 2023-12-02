package frc.robot.Subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.Autodrive;
import org.carlmontrobotics.lib199.MotorControllerFactory;
import org.carlmontrobotics.lib199.MotorConfig;

public class Drivetrain extends SubsystemBase {

CANSparkMax leftMotors = MotorControllerFactory.createSparkMax(Constants.Drivetrain.LEFT_MOTOR_PORT,MotorConfig.NEO);
CANSparkMax rightMotors = MotorControllerFactory.createSparkMax(Constants.Drivetrain.RIGHT_MOTOR_PORT,MotorConfig.NEO);

private boolean isAuto;
private boolean isTank;
private boolean isSlow = true;
private XboxController controller;



public Drivetrain(XboxController controller) {
    this.controller = controller;

}

public void motorSpeeds(double y1,double y2) {
    leftMotors.set(-y1);
    rightMotors.set(y2);
}

public void swap () {
    isTank = !isTank;
}

public void speedSwap () {
    isSlow = !isSlow;
}

@Override
public void periodic() {
    isAuto = Autodrive.isAuto();
    if(isAuto) {
        return;
    }
    double speed = -controller.getLeftY();
    double turn = controller.getLeftX();
    double left = speed+turn;
    double right = speed-turn;

    if(!isTank) {
        if(isSlow) {
            motorSpeeds(left * Constants.Drivetrain.SLOW_SPEED_MULTIPLIER,right * Constants.Drivetrain.SLOW_SPEED_MULTIPLIER);
        } else {
            motorSpeeds(left,right);
        }
        
    } else if(isTank) {
        if (isSlow) {
            motorSpeeds(speed * Constants.Drivetrain.SLOW_SPEED_MULTIPLIER, -(controller.getRightY() * Constants.Drivetrain.SLOW_SPEED_MULTIPLIER));
        } else {
            motorSpeeds(speed, -(controller.getRightY()));
        }
    }

    }
}
