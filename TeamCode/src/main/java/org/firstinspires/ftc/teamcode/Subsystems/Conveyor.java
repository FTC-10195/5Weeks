package org.firstinspires.ftc.teamcode.Subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Conveyor {
    public enum BeltStates {
        ON,
        OFF,
        EJECT
    }
    public BeltStates currentBeltState = BeltStates.OFF;
    public void setBeltstate(BeltStates newBeltState){
        currentBeltState = newBeltState;
    }
    DcMotor beltMotor;
    public void initiate(HardwareMap hardwaremap){
        beltMotor = hardwaremap.dcMotor.get("belt");
        beltMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



    }
    public void update(){
        switch(currentBeltState){
            case ON:
                beltMotor.setPower(1);
                break;
            case OFF:
                beltMotor.setPower(0.0);
                break;
            case EJECT:
                beltMotor.setPower(-1);
        }
    }
}



