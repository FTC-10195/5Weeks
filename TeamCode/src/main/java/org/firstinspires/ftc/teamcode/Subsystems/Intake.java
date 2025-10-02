package org.firstinspires.ftc.teamcode.Subsystems;



import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class Intake {
    public enum IntakeState {
        ON,
        OFF,
        OUTTAKE,
    }


    public IntakeState currentIntakeState = IntakeState.OFF;


    public void setIntakeState(IntakeState newIntakeState){
        currentIntakeState = newIntakeState;
    }


    DcMotor IntakeMotor;
    public IntakeState getCurrentIntakeState() {
        return currentIntakeState;
    }


    public void initiate(HardwareMap hardwaremap){
        IntakeMotor = hardwaremap.dcMotor.get("IntakeMotor");
        IntakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void update(){
        switch(currentIntakeState){
            case ON:
                IntakeMotor.setPower(0.9);
                break;
            case OFF:
                IntakeMotor.setPower(0.0);
            case OUTTAKE:
                IntakeMotor.setPower(-0.9);
        }
    }
}


