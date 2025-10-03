package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Trigger {
    public enum ServoState {
        SHOOTING,
        RESTING
    }
    public ServoState currentServoState = ServoState.RESTING;
    Servo TriggerServo;
    public long waitTime = 250;
    long timeSnapshot = System.currentTimeMillis();

    public void initiate(HardwareMap hardwareMap) {
        TriggerServo = hardwareMap.servo.get("trigger");
    }
    public static double servoShootingPos = 0.4;
    public static double servoRestingPos = 0.5;

    public void setState(ServoState newState){
        currentServoState = newState;
        timeSnapshot = System.currentTimeMillis();
    }
    public ServoState getCurrentServoState(){
        return currentServoState;
    }
    public void update(){

        switch (currentServoState){
            case SHOOTING:
                TriggerServo.setPosition(servoShootingPos);
                if (System.currentTimeMillis() - timeSnapshot > waitTime){
                    currentServoState = ServoState.RESTING;
                }
                break;
            case RESTING:
                TriggerServo.setPosition(servoRestingPos);
                break;
        }
    }
}
