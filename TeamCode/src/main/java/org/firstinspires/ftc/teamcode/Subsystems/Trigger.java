package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.delays.Delay;

public class Trigger extends Subsystem {
    public enum States{
        CLOSED,
        OPEN,
        RESETTING
    }
    public Servo servo;
    public static double closedPos = 0.5;
    public static double openPos = .2;
    public static double waitTime = 0.5;
    States currentState = States.CLOSED;
    public void initiate(HardwareMap hardwareMap) {
        servo = hardwareMap.servo.get("trigger");
    }

    public Command setState(States newState) {
        currentState = newState;
        return new Command() {
            @Override
            public boolean isDone() {
                return true;
            }
        };
    }
    public States getState(){
        return currentState;
    }
    public Command shoot() {
        return new SequentialGroup(
                setState(States.OPEN),
                new Delay(0.5),
                setState(States.RESETTING),
                new Delay(0.5)
        );
    }
    public void update(){
        switch (currentState){
            case CLOSED:
            case RESETTING:
                servo.setPosition(closedPos);
                break;
        case OPEN:
                servo.setPosition(openPos);
                break;
        }
    }

}
