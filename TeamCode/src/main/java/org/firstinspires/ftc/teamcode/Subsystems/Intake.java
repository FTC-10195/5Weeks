package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower;


public class Intake extends Subsystem {
    public enum States{
        RESTING,
        INTAKING,
        EJECTING
    }
    States currentState = States.RESTING;
    public DcMotorEx motor;
    public void initiate(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class,"intake");
    }

   public Command setState(States newState){
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
   public void update(){
        switch (currentState){
            case RESTING:
                motor.setPower(0);
                break;
            case INTAKING:
                motor.setPower(1);
                break;
            case EJECTING:
                motor.setPower(-1);
        }
   }
}
