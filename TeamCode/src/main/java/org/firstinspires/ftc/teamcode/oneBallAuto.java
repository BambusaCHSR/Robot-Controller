package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by - Elijah Sauder on 1/7/17, 10:46 PM.
 **/


@Autonomous(name="OneBall", group="Bambusa")
class oneBallAuto extends LinearOpMode {
    private Definitions robot = new Definitions();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();

        //Launch particle
        robot.setLaunchRotateForward();
        robot.restartLauncherEncoders();
        robot.setLauncherRotateDistance(1600);
        robot.runToLauncherPosition();
        robot.setLaunchPower(1);
        robot.waitForLauncherMotorStop();
        robot.setLaunchPower(0);
        sleep(500);
        idle();

        //drive forward into Cap Ball
        robot.setDriveForward();
        robot.restartDriveEncoders();
        robot.setDriveDistance(3560);
        robot.runToPosition();
        robot.setPower(1);
        robot.waitForDriveMotorStop();
        robot.setPower(0);
        sleep(1000);
        idle();

        //knocks ball off if it isn't off already
        robot.setDriveRotateLeft();
        robot.restartDriveEncoders();
        robot.setDriveDistance(560);
        robot.runToPosition();
        robot.setPower(1);
        robot.waitForDriveMotorStop();
        robot.setPower(0);
        sleep(10);
        idle();
        robot.setDriveRotateRight();
        robot.restartDriveEncoders();
        robot.setDriveDistance(560);
        robot.runToPosition();
        robot.setPower(1);
        robot.waitForDriveMotorStop();
        robot.setPower(0);
        sleep(4000);
        idle();

        //drives forward onto platform
        robot.setDriveForward();
        robot.restartDriveEncoders();
        robot.setDriveDistance(1700);
        robot.runToPosition();
        robot.setPower(1);
        robot.waitForDriveMotorStop();
        robot.setPower(0);
        sleep(10);
        idle();
    }
}
