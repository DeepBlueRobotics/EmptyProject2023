package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Subsystems.Drivetrain;
import frc.robot.Subsystems.Intake;


import edu.wpi.first.wpilibj.Timer;
public class Autodrive extends CommandBase {
    private final Drivetrain drivetrain;
    private final Timer timer = new Timer();
    public Autodrive(Drivetrain drivetrain, Intake shooter) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain, shooter);
    }
    
    public static boolean isTank(boolean tank) {
        return tank;
    }

    public static boolean isAuto(boolean auto) {
        return auto;
    }

    @Override
    public void initialize() {
        timer.reset();
        isTank(true);
        isAuto(true);
        timer.start();
        drivetrain.motorSpeeds(Constants.Drivetrain.AUTO_SPEED, Constants.Drivetrain.AUTO_SPEED);
    }
    @Override
    public void execute() {

        }
    
    @Override
    public void end(boolean interrupted) {
        drivetrain.motorSpeeds(0, 0); // Stops motors by setting voltage to 0
        isTank(false);
        isAuto(false);
    }
    @Override
    public boolean isFinished() {
        return timer.get()>2;
    }
}

