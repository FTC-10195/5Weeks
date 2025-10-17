package org.firstinspires.ftc.teamcode.example.java.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.delays.Delay;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class Trigger extends Subsystem {
    // BOILERPLATE
    public static final Trigger INSTANCE = new Trigger();
    private Trigger() { }

    // USER CODE
    public Servo servo;
    
    public String name = "trigger";

    public Command close() {
        return new ServoToPosition(servo, // SERVO TO MOVE
                0.5, // POSITION TO MOVE TO
                this); // IMPLEMENTED SUBSYSTEM
    }

    public Command open() {
        return new ServoToPosition(servo, // SERVO TO MOVE
                0.2, // POSITION TO MOVE TO
                this); // IMPLEMENTED SUBSYSTEM
    }
    public Command shoot(){
        return new SequentialGroup(
                open(),
                new Delay(0.5),
                close()
        );
    }
    @Override
    public void initialize() {
        servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, name);
    }
}
