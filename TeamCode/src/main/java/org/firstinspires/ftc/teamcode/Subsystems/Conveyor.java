package org.firstinspires.ftc.teamcode.Subsystems;


import com.qualcomm.robotcore.hardware.CRServo;
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
    DcMotor beltMotor;
    CRServo transferServo;
    public void initiate(HardwareMap hardwaremap){
        beltMotor = hardwaremap.dcMotor.get("belt");
        beltMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        transferServo = hardwaremap.crservo.get("cr");



    }
    public void update(){
        switch(currentBeltState){
            case ON:
                beltMotor.setPower(0.5);
                transferServo.setPower(1.0);
                break;
            case OFF:
                beltMotor.setPower(0.0);
                transferServo.setPower(0.0);
                break;
        }
    }
}



