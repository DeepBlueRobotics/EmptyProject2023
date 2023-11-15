package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.Subsystems.Intake;

public class IntakeStart extends CommandBase {
    private final Intake intake;
    private final MotorControllerGroup frontGroup;
    private final MotorControllerGroup backGroup;
    Timer startingTimer = new Timer();
    //TODO: Have intake outake rather have logic in command
    public IntakeStart(Intake intake, MotorControllerGroup frontGroup, MotorControllerGroup backGroup) {
        this.intake = intake;
        this.frontGroup = frontGroup;
        this.backGroup = backGroup;
    }
    @Override 
    public void initialize() {
        frontGroup.set(-0.1);
        backGroup.set(-0.1);
    }
}
