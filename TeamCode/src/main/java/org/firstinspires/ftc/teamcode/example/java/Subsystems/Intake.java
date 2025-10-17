package org.firstinspires.ftc.teamcode.example.java.Subsystems;

import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower;


public class Intake extends Subsystem {
    // BOILERPLATELift
    public static final Intake INSTANCE = new Intake();
    private Intake() { }

    // USER CODE
    public MotorEx motor;

    public String name = "intake";

    public Command rest() {
        return new SetPower(motor, // MOTOR TO MOVE
                0.0, // TARGET POSITION, IN TICKS
                this); // IMPLEMENTED SUBSYSTEM
    }

    public Command intake() {
        return new SetPower(motor, // MOTOR TO MOVE
                0.9, // TARGET POSITION, IN TICKS
                this); // IMPLEMENTED SUBSYSTEM
    }

    public Command eject() {
        return new SetPower(motor, // MOTOR TO MOVE
                -0.9, // TARGET POSITION, IN TICKS
                this); // IMPLEMENTED SUBSYSTEM
    }
    
    @Override
    public void initialize() {
        motor = new MotorEx(name);
    }
}
