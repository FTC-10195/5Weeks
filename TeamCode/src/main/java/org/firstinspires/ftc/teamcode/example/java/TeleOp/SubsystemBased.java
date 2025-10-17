package org.firstinspires.ftc.teamcode.example.java.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.delays.Delay;
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;

import org.firstinspires.ftc.teamcode.example.java.Subsystems.Conveyor;
import org.firstinspires.ftc.teamcode.example.java.Subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.example.java.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.example.java.Subsystems.Kicker;
import org.firstinspires.ftc.teamcode.example.java.Subsystems.Trigger;

@TeleOp
public class SubsystemBased extends NextFTCOpMode {
    public SubsystemBased() {
        super(Conveyor.INSTANCE, Flywheel.INSTANCE, Intake.INSTANCE, Kicker.INSTANCE, Trigger.INSTANCE);
    }

    public Command initiate() {
        return new ParallelGroup(
                Conveyor.INSTANCE.rest(),
                Flywheel.INSTANCE.rest(),
                Intake.INSTANCE.rest(),
                Kicker.INSTANCE.rest(),
                Trigger.INSTANCE.close()
        );
    }
    public Command conveyor(){
        return new ParallelGroup(
                Conveyor.INSTANCE.rest()
        );
    }


    @Override
    public void onStartButtonPressed() {
        initiate().invoke();
    }

    @Override
    public void runOpMode() {
        waitForStart();
        if (isStopRequested()) return;
        Gamepad previousGamepad1 = new Gamepad();
        while (opModeIsActive()) {
            boolean LB = gamepad1.left_bumper && !previousGamepad1.left_bumper;
            boolean RB = gamepad1.right_bumper && !previousGamepad1.right_bumper;
            boolean X = gamepad1.x && !previousGamepad1.x;
            boolean LT = gamepad1.left_trigger > 0.1 && previousGamepad1.left_trigger <= 0.1;
            boolean RT = gamepad1.right_trigger > 0.1 && previousGamepad1.right_trigger <= 0.1;
            previousGamepad1.copy(gamepad1);
            if (LB){


            }
        }
    }
}
