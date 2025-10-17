package org.firstinspires.ftc.teamcode.example.java.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.delays.Delay;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class Kicker extends Subsystem {
    // BOILERPLATE
    public static final Kicker INSTANCE = new Kicker();
    private Kicker() { }

    // USER CODE
    public Servo servo;
    
    public String name = "kicker";

    public Command rest() {
        return new ServoToPosition(servo, // SERVO TO MOVE
                0.5, // POSITION TO MOVE TO
                this); // IMPLEMENTED SUBSYSTEM
    }

    public Command kick() {
        return new ServoToPosition(servo, // SERVO TO MOVE
                0.37, // POSITION TO MOVE TO
                this); // IMPLEMENTED SUBSYSTEM
    }
    public Command shoot(){
        return new SequentialGroup(
                kick(),
                new Delay(0.5),
                rest()
        );
    }

    @Override
    public void initialize() {
        servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, name);
    }
}
