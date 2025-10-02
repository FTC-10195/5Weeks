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
    }


    public void update() {
        switch (currentFlywheelState) {
            case RESTING:
                flywheelMotor.setPower(0);
                break;
            case SPINNING:
                flywheelMotor.setPower(0.5);
                break;


        }
    }
}
