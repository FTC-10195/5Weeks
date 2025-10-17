package org.firstinspires.ftc.teamcode.example.java.Subsystems;

import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower;


public class Conveyor extends Subsystem {
    // BOILERPLATELift
    public static final Conveyor INSTANCE = new Conveyor();
    private Conveyor() { }

    // USER CODE
    public MotorEx motor;

    public String name = "conveyor";

    public Command rest() {
        return new SetPower(motor, // MOTOR TO MOVE
                0.0, // TARGET POSITION, IN TICKS
                this); // IMPLEMENTED SUBSYSTEM
    }

    public Command intake() {
        return new SetPower(motor, // MOTOR TO MOVE
                1, // TARGET POSITION, IN TICKS
                this); // IMPLEMENTED SUBSYSTEM
    }

    public Command eject() {
        return new SetPower(motor, // MOTOR TO MOVE
                -1, // TARGET POSITION, IN TICKS
                this); // IMPLEMENTED SUBSYSTEM
    }
    
    @Override
    public void initialize() {
        motor = new MotorEx(name);
    }
}
