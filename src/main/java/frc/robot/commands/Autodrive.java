package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Drivetrain;
import frc.robot.Constants;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.Timer;
public class Autodrive extends CommandBase {
    private final Drivetrain drivetrain;
    private final Timer timer = new Timer();
    private final double wheelRadius = Constants.WHEEL_RADIUS;
    private final double circumference_forumla = Math.PI * 2 * wheelRadius;
    private boolean encoderWorking;
    private boolean previous;
    private double checkpoint = 0.5;
    private double oldEncoderValue;
    public Autodrive(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }
 
    @Override
    public void initialize() {
        oldEncoderValue = drivetrain.leftEncoder.getPosition();
        previous = drivetrain.isTank;
        drivetrain.isTank = true;
        drivetrain.isAuto = true;
        encoderWorking = true;
        timer.start();
        drivetrain.driveForward(0.5, 0.5);
    }
    @Override
    public void execute() {
        if (timer.get() == checkpoint) {
            if (oldEncoderValue == drivetrain.leftEncoder.getPosition()) {
                encoderWorking = false;
            } else{
                checkpoint += 0.5;
                oldEncoderValue = drivetrain.leftEncoder.getPosition();
            }
            
        }
    }
    @Override
    public void end(boolean interrupted) {
        drivetrain.isTank = previous;
        drivetrain.driveForward(0, 0);
        drivetrain.isAuto = false;
          
    }
    @Override
    public boolean isFinished() {
        if (encoderWorking) {
            return (drivetrain.leftEncoder.getPosition() * circumference_forumla) > (8.25 * 12);
        }
        return timer.get()>2;
    }
}
