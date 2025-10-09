package org.firstinspires.ftc.teamcode.Subsystems;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Kicker {
    public enum ServoState {
        SHOOTING,
        RESTING,
    }
    public long waitTime = 250;
    long timeSnapshot = System.currentTimeMillis();

    public ServoState currentServoState = ServoState.RESTING;
    Servo KickerServo;
    public void initiate(HardwareMap hardwareMap) {
        KickerServo = hardwareMap.servo.get("kicker");

    }
    public static double servoShootingPos = 0.4;
    public static double servoRestingPos = 0;
    public void setState(ServoState newState) {
        currentServoState = newState;
        timeSnapshot = System.currentTimeMillis();
    }
    public ServoState getCurrentServoState() {
        return currentServoState;
    }
    public void update() {
        switch (currentServoState) {
            case SHOOTING:
                KickerServo.setPosition(servoShootingPos);
                if (System.currentTimeMillis() - timeSnapshot > waitTime){
                    currentServoState = ServoState.RESTING;
                }
                break;
            case RESTING:
                KickerServo.setPosition(servoRestingPos);
                break;
        }
    }
}
