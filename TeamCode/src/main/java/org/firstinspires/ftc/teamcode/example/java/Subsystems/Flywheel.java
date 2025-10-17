package org.firstinspires.ftc.teamcode.example.java.Subsystems;

import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToVelocity;


public class Flywheel extends Subsystem {
    // BOILERPLATELift
    public static final Flywheel INSTANCE = new Flywheel();
    private Flywheel() { }

    // USER CODE
    public MotorEx motor;

    public PIDFController controller = new PIDFController(kP, 0.0, 0.0);
    public static double kP = 0.005;

    public Command resetZero() {
        return new InstantCommand(() -> { motor.resetEncoder(); });
    }

    public String name = "flywheel";

    public Command rest() {
        return new RunToVelocity(motor, // MOTOR TO MOVE
                0.0, // TARGET POSITION, IN TICKS
                controller, // CONTROLLER TO IMPLEMENT
                this); // IMPLEMENTED SUBSYSTEM
    }

    public Command shoot() {
        return new RunToVelocity(motor, // MOTOR TO MOVE
                1600, // TARGET POSITION, IN TICKS
                controller, // CONTROLLER TO IMPLEMENT
                this); // IMPLEMENTED SUBSYSTEM
    }


    
    @Override
    public void initialize() {
        motor = new MotorEx(name);
    }
}
