package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.delays.Delay;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class Kicker extends Subsystem {
    public enum States{
        RESTING,
        KICKING,
        RESETTING
    }
    public Servo servo;
    public static double restPos = 0.5;
    public static double kickPos = .37;
    public static double waitTime = 0.5;
    States currentState = States.RESTING;
    public void initiate(HardwareMap hardwareMap) {
        servo = hardwareMap.servo.get("kicker");
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
                setState(States.KICKING),
                new Delay(0.5),
                setState(States.RESETTING),
                new Delay(0.5)
        );
    }
    public void update(){
        switch (currentState){
            case RESTING:
            case RESETTING:
                servo.setPosition(restPos);
                break;
            case KICKING:
                servo.setPosition(kickPos);
                break;
        }
    }

}
