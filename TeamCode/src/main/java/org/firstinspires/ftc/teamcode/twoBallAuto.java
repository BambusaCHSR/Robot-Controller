package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by - Elijah Sauder on 1/7/17, 11:07 PM.
 **/


@Autonomous(name="TwoBall", group="Bambusa")
class twoBallAuto extends LinearOpMode {
    private Definitions robot = new Definitions();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();

        robot.setDriveForward();
        robot.restartDriveEncoders();
        robot.setDriveDistance(560);
        robot.runToPosition();
        robot.setPower(1);
        robot.waitForDriveMotorStop();
        robot.setPower(0);
        sleep(10);
        idle();

        //Launch particle one
        robot.launchTwoBalls();
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

        //knocks ball off if it wasn't off already
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