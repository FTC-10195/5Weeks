package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower;


public class Conveyor extends Subsystem {

    public enum States {
        RESTING,
        INTAKING,
        EJECTING
    }

    private States currentState = States.RESTING;
    private MotorEx motor;
    public States getState() {
        return currentState;
    }

    public void initiate(HardwareMap hardwareMap) {
        motor = hardwareMap.get(MotorEx.class, "conveyor");
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
    public void update() {
        switch (currentState) {
            case RESTING:
                motor.setPower(0);
                break;
            case EJECTING:
                motor.setPower(-1);
                break;
            case INTAKING:
                motor.setPower(1);
                break;
        }
    }
}
