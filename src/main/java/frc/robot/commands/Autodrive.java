package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Subsystems.Drivetrain;
import frc.robot.Subsystems.Intake;


import edu.wpi.first.wpilibj.Timer;
public class Autodrive extends CommandBase {
    private final Drivetrain drivetrain;
    private final Timer timer = new Timer();
    private static boolean isAuto1 = false;
    private static boolean isTank1 = true;
    public Autodrive(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }
    
    public static boolean isTank() {
        return isTank1;
    }

    public static boolean isAuto() {
        return isAuto1;
    }

    @Override
    public void initialize() {
        isAuto1 = true;
        timer.reset();
        isTank();
        isAuto();
        timer.start();
        drivetrain.motorSpeeds(Constants.Drivetrain.AUTO_SPEED, Constants.Drivetrain.AUTO_SPEED);
    }
    @Override
    public void execute() {

        }
    
    @Override
    public void end(boolean interrupted) {
        drivetrain.motorSpeeds(0, 0); // Stops motors by setting voltage to 0
        isTank1=false;
        isAuto1=false;
    }
    @Override
    public boolean isFinished() {
        return timer.get()>1.5;
    }
}

