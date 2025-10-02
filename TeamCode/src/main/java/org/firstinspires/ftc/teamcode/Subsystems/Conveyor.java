package org.firstinspires.ftc.teamcode.Subsystems;


import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Conveyor {
    public enum BeltStates {
        ON,
        OFF,
    }
    public BeltStates currentBeltState = BeltStates.OFF;
    public void setBeltstate(BeltStates newBeltState){
        currentBeltState = newBeltState;
    }
    public BeltStates getCurrentBeltState(){
        return currentBeltState;
    }
    DcMotor BeltMotor;
    public void initiate(HardwareMap hardwaremap){
        BeltMotor = hardwaremap.dcMotor.get("BeltMotor");
        BeltMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void update(){
        switch(currentBeltState){
            case ON:
                BeltMotor.setPower(0.5);
                break;
            case OFF:
                BeltMotor.setPower(0.0);
        }
    }
}

