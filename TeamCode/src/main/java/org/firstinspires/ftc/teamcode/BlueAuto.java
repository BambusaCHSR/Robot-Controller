package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by esauder on 1/26/17.
 **/

@Autonomous(name="BlueAuto",group = "Bambusa")
class BlueAuto extends LinearOpMode{
    private Definitions robot = new Definitions();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();

        robot.driveForwardAndOrBack(700, 1);
        sleep(10);
        idle();

        //robot is tilting so this tilts it back in order to make the shot
        robot.rotateLeft(200);
        sleep(100);
        idle();

        //Launch particle
        robot.launchOneBall();
        sleep(500);
        idle();

        robot.rotateLeft(400);
        sleep(100);
        idle();

        robot.colorSensorColors();

        while (robot.sensorColorBottom.alpha() < 20) {
            robot.setDriveForward();
            robot.setPower(0.5);
        }
    }
}
