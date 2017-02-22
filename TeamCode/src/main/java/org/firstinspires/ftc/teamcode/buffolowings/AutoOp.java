package org.firstinspires.ftc.teamcode.buffolowings;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Definitions;

/**
 * Created by elijah on 2/21/17.
 **/
@Autonomous(name="AutoOp",group = "Buffalo")
public class AutoOp extends LinearOpMode {
    private Definitions robotBam = new Definitions();
    boolean State1 = true;
    boolean State2 = false;
    boolean State3 = false;
    @Override
    public void runOpMode() throws InterruptedException {
        robotBam.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {

        }
    }
}
