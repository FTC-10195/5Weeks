package org.firstinspires.ftc.teamcode.Subsystems;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Flywheel {
    public enum FlywheelStates {
        SPINNING,
        RESTING,
    }
    public boolean IsReady = false;
    public long timeShot = System.currentTimeMillis();
    public long waitTime = 1500;

    public FlywheelStates getFlywheelState() {
        return currentFlywheelState;

    }


    public FlywheelStates currentFlywheelState = FlywheelStates.SPINNING;
    DcMotor flywheelMotor;
    public int prevPosition = 0;
    public long prevTime = System.currentTimeMillis();
    public void initiate(HardwareMap hardwareMap) {
        flywheelMotor = hardwareMap.dcMotor.get("flywheel");
    }


    public void setState(FlywheelStates newState) {
        if (newState == FlywheelStates.SPINNING && currentFlywheelState != FlywheelStates.SPINNING) {
            timeShot = System.currentTimeMillis();
        }
        currentFlywheelState = newState;
    }

    long velocity = 0;
    public void update() {
        int currentPosition = flywheelMotor.getCurrentPosition();
        long currentTime = System.currentTimeMillis();
        velocity = (currentPosition - prevPosition)/(currentTime - prevTime);
        prevPosition = currentPosition;
        prevTime = currentTime;
        switch (currentFlywheelState) {
            case RESTING:
                flywheelMotor.setPower(0);
                IsReady = false;

                break;
            case SPINNING:
                flywheelMotor.setPower(1);
                if (System.currentTimeMillis() - timeShot > waitTime){
                    IsReady = true;
                }
                break;


        }
    }
    public void status (Telemetry telemetry) {
        telemetry.addData("velocity", velocity);
    }
}
