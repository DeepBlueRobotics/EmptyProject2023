package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Drivetrain;
import frc.robot.Constants;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.Timer;
public class Autodrive extends CommandBase {
    private final Drivetrain drivetrain;
    private final Timer timer = new Timer();
    
    private boolean previous;
    
    public Autodrive(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }
 
    @Override
    public void initialize() {
        previous = drivetrain.isTank;
        drivetrain.isTank = true;
        drivetrain.isAuto = true;
        timer.start();
        drivetrain.driveForward(0.2, 0.2);
    }
    @Override
    public void execute() {

        }
    
    @Override
    public void end(boolean interrupted) {
        drivetrain.isTank = previous;
        drivetrain.driveForward(0, 0);
        drivetrain.isAuto = false;
          
    }
    @Override
    public boolean isFinished() {
        return timer.get()>2;
    }
}

