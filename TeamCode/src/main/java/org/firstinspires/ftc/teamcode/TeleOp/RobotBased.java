package org.firstinspires.ftc.teamcode.TeleOp;

import static org.firstinspires.ftc.teamcode.Subsystems.Conveyor.BeltStates.OFF;
import static org.firstinspires.ftc.teamcode.Subsystems.Conveyor.BeltStates.ON;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystems.Conveyor;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Trigger;

@TeleOp
public class RobotBased extends LinearOpMode {
    public enum State{
        RESTING,
        INTAKING,
        SPINNING,
        EJECTING

    }
    @Override
    public void runOpMode() throws InterruptedException {
        State currentState = State.RESTING;
        waitForStart();
        if (isStopRequested()) return;

        Conveyor conveyor = new Conveyor();
        conveyor.initiate(hardwareMap);
        Flywheel flywheel = new Flywheel();
        flywheel.initiate(hardwareMap);
        Trigger trigger = new Trigger();
        trigger.initiate(hardwareMap);
        Intake intake = new Intake();
        intake.initiate(hardwareMap);
        Drivetrain drivetrain = new Drivetrain();
        drivetrain.initiate(hardwareMap);
        Gamepad previousGamepad1 = new Gamepad();
        while (opModeIsActive()) {
            boolean LB = gamepad1.left_bumper && !previousGamepad1.left_bumper;
            boolean RB = gamepad1.right_bumper && !previousGamepad1.right_bumper;
            boolean X = gamepad1.cross && !previousGamepad1.cross;
            boolean LT = gamepad1.left_trigger > 0.1 && previousGamepad1.left_trigger <= 0.1;
            boolean RT = gamepad1.right_trigger > 0.1 && previousGamepad1.right_trigger <= 0.1;
            previousGamepad1.copy(gamepad1);

            switch (currentState) {
                case RESTING:
                    if (RT) {
                        currentState = State.SPINNING;
                    } else if (LT) {
                        currentState = State.INTAKING;
                    }
                    break;


                case SPINNING:
                    if (RT) {
                        trigger.setState(Trigger.ServoState.SHOOTING);
                    }
                    break;


                case INTAKING:
                    if (LT) {
                        currentState = State.RESTING;
                    } else if (X) {
                        currentState = State.EJECTING;
                    }
                    break;
                case EJECTING:
                    if (X) {
                        currentState = State.INTAKING;
                    }
                    break;

            }
            if (LB) {
                currentState = State.RESTING;
            }

            switch (currentState) {
                case RESTING:
                    flywheel.setState(Flywheel.FlywheelStates.RESTING);
                    intake.setIntakeState(Intake.IntakeState.OFF);
                    conveyor.setBeltstate(Conveyor.BeltStates.OFF);
                    break;


                case SPINNING:
                    flywheel.setState(Flywheel.FlywheelStates.SPINNING); // adjust power
                    conveyor.setBeltstate(Conveyor.BeltStates.ON);
                    intake.setIntakeState(Intake.IntakeState.OFF);
                    break;


                case INTAKING:
                    flywheel.setState(Flywheel.FlywheelStates.RESTING);
                    conveyor.setBeltstate(Conveyor.BeltStates.ON);
                    intake.setIntakeState(Intake.IntakeState.ON);
                    break;


                case EJECTING:
                    flywheel.setState(Flywheel.FlywheelStates.RESTING);
                    conveyor.setBeltstate(Conveyor.BeltStates.OFF);
                    intake.setIntakeState(Intake.IntakeState.OUTTAKE);
                    break;
            }

            drivetrain.update(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            conveyor.update();
            flywheel.update();
            intake.update();
            trigger.update();
            telemetry.addData("State", currentState);
            telemetry.update();
        }
    }
}
