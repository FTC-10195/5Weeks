package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.delays.Delay;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;


public class Flywheel extends Subsystem {
    public enum States{
        RESTING,
        SPINNING
    }
    public MotorEx motor;
    States currentState = States.RESTING;
    public void initiate(HardwareMap hardwareMap) {
        motor = hardwareMap.get(MotorEx.class,"flywheel");
        motor.resetEncoder();
    }


    public PIDFController controller = new PIDFController(kP, 0.0, 0.0);
    public static double kP = 0.005;
    public static double targetVelocity = 1600;
    public boolean readyToFire = false;
    double error = 0;
    double tolerance = 10;
    double waitTime = 1.5;

    public String name = "flywheel";
    public States getState(){
        return currentState;
    }
    Command setReady(boolean newReady){
        readyToFire = newReady;
        return new Command() {
            @Override
            public boolean isDone() {
                return true;
            }
        };
    };
    public Command setState(States newState){
        currentState = newState;
        return new Command() {
            @Override
            public boolean isDone() {
                return true;
            }
        };
    };
    Command checkVelocity(){
        return new Command() {
            @Override
            public boolean isDone() {
                return Math.abs(error) < tolerance;
            }
        };
    }
    public Command spin() {
       return new SequentialGroup(
               setState(States.SPINNING),
               setReady(false),
               new ParallelGroup(
                       //If either velocity is up to speed OR 1.5 seconds passed
                       checkVelocity(),
                       new Delay(waitTime)
               ),
               //Flywheel is ready!
               setReady(true)
       );
    }
    public void update(){
        double power = 0;
        error = 0;
        switch (currentState){
            case RESTING:
                power = 0;
                readyToFire = false;
                break;
            case SPINNING:
                error = targetVelocity - motor.getVelocity();
                power = controller.calculate(motor.getVelocity(),targetVelocity);
                break;
        }
        motor.setPower(power);
    }
}
