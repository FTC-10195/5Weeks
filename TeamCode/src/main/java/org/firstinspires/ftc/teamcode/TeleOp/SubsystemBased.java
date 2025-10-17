package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup;
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Conveyor;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Kicker;
import org.firstinspires.ftc.teamcode.Subsystems.Trigger;

@TeleOp
public class SubsystemBased extends NextFTCOpMode {

    Conveyor conveyor = new Conveyor();
    Kicker kicker = new Kicker();
    Flywheel flywheel = new Flywheel();
    Trigger trigger = new Trigger();
    Intake intake = new Intake();
    Drivetrain drivetrain = new Drivetrain();

    @Override
    public void runOpMode() {
        waitForStart();
        conveyor.initiate(hardwareMap);
        kicker.initiate(hardwareMap);
        flywheel.initiate(hardwareMap);
        intake.initiate(hardwareMap);
        trigger.initiate(hardwareMap);
        drivetrain.initiate(hardwareMap);

        if (isStopRequested()) return;
        Gamepad previousGamepad1 = new Gamepad();
        while (opModeIsActive()) {
            boolean LB = gamepad1.left_bumper && !previousGamepad1.left_bumper;
            boolean RB = gamepad1.right_bumper && !previousGamepad1.right_bumper;
            boolean X = gamepad1.x && !previousGamepad1.x;
            boolean LT = gamepad1.left_trigger > 0.1 && previousGamepad1.left_trigger <= 0.1;
            boolean RT = gamepad1.right_trigger > 0.1 && previousGamepad1.right_trigger <= 0.1;
            previousGamepad1.copy(gamepad1);
            if (LB) {
                switch (conveyor.getState()) {
                    case RESTING:
                        conveyor.setState(Conveyor.States.INTAKING);
                        break;
                    case INTAKING:
                    case EJECTING:
                        conveyor.setState(Conveyor.States.RESTING);
                        break;
                }
            }
            if (RT){
                kicker.shoot().start();
                trigger.shoot().start();
            }
            if (LT){
                switch (flywheel.getState()){
                    case RESTING:
                        flywheel.spin().start();
                        break;
                    case SPINNING:
                        flywheel.setState(Flywheel.States.RESTING);
                        break;
                }
            }
            if (RB){
                switch (intake.getState()){
                    case RESTING:
                        intake.setState(Intake.States.INTAKING);
                        break;
                    case INTAKING:
                    case EJECTING:
                        intake.setState(Intake.States.RESTING);
                        break;
                }
            }
            if (X){
                switch (intake.getState()){
                    case RESTING:
                        intake.setState(Intake.States.EJECTING);
                        break;
                    case EJECTING:
                    case INTAKING:
                        intake.setState(Intake.States.RESTING);
                        break;
                }
            }


            trigger.update();
            flywheel.update();
            conveyor.update();
            intake.update();
            kicker.update();
            drivetrain.update(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);


            telemetry.addData("trig",trigger.getState());
            telemetry.addData("fly",flywheel.getState());
            telemetry.addData("conv",conveyor.getState());
            telemetry.addData("intake",intake.getState());
            telemetry.addData("kick",kicker.getState());

            telemetry.update();


        }
    }
}
