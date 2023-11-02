package frc.robot.Subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.carlmontrobotics.lib199.MotorControllerFactory;
import org.carlmontrobotics.lib199.MotorConfig;

public class Drivetrain extends SubsystemBase {
CANSparkMax leftMotors = MotorControllerFactory.createSparkMax(1,MotorConfig.NEO);
CANSparkMax rightMotors = MotorControllerFactory.createSparkMax(2,MotorConfig.NEO);
public RelativeEncoder leftEncoder = leftMotors.getEncoder();
public RelativeEncoder rightEncoder = rightMotors.getEncoder();
public boolean isTank = false;
public boolean isAuto = false;
private XboxController controller;

public Drivetrain(XboxController controller) {
    this.controller = controller;

}

public void driveForward(double y1,double y2) {
    leftMotors.set(y1);
    rightMotors.set(y2);
}

public void swap () {
    isTank = !isTank;
}


@Override
public void periodic() {
    if(isAuto) {
        return;
    }
    double speed = -controller.getLeftY();
    double turn = controller.getLeftX();
    double left = speed+turn;
    double right = speed-turn;

    if(!isTank) {
        driveForward(left,right);
    } else if(isTank) {
        driveForward(speed, -(controller.getRightY()));
    }

    }
}
