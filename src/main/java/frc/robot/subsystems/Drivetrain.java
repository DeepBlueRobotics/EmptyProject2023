package frc.robot.subsystems;
import org.carlmontrobotics.lib199.MotorControllerFactory;
import org.carlmontrobotics.lib199.MotorConfig;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Drivetrain extends SubsystemBase {
    public boolean isAuto;
    public boolean isTank = false;
    private XboxController controller;
    CANSparkMax rightMotor = MotorControllerFactory.createSparkMax(1,MotorConfig.NEO);
    CANSparkMax leftMotor = MotorControllerFactory.createSparkMax(2,MotorConfig.NEO);
   
    public Drivetrain(XboxController ctrlr){
        controller = ctrlr;
    }

    public void drive(double left,double right) {
        rightMotor.set(right);
        leftMotor.set(left);
    }
    
    public void swap() {
        isTank = !isTank;
    }
    
    @Override
    public void periodic(){
      if (!isAuto) {
        double speed = -controller.getLeftY();
        if (isTank) {
          double turn = controller.getLeftX();
          double left = speed - turn;
          double right = speed + turn;
          drive(left, right);
        } else {
          double rightYAxis = -controller.getRightY();
          drive(speed,rightYAxis);
        }
      }
    }
}