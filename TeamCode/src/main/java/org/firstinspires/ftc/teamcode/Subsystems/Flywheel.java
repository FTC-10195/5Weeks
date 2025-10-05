package org.firstinspires.ftc.teamcode.Subsystems;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


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





    public void initiate(HardwareMap hardwareMap) {
        flywheelMotor = hardwareMap.dcMotor.get("flywheel");
    }


    public void setState(FlywheelStates newState) {
        currentFlywheelState = newState;
        if (currentFlywheelState == FlywheelStates.SPINNING) {
            timeShot = System.currentTimeMillis();
        }
    }


    public void update() {
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
}
