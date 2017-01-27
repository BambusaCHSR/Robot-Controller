package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by esauder on 1/26/17.
 **/

@Autonomous(name="BlueAuto",group = "Bambusa")
class Hopefullygoodauto extends LinearOpMode{
    private Definitions robot = new Definitions();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();

        robot.setDriveForward();
        robot.restartDriveEncoders();
        robot.setDriveDistance(700);
        robot.runToPosition();
        robot.setPower(1);
        robot.waitForDriveMotorStop();
        robot.setPower(0);
        sleep(10);
        idle();

        //robot is tilting so this tilts it back in order to make the shot
        robot.setDriveRotateLeft();
        robot.restartDriveEncoders();
        robot.setDriveDistance(200);
        robot.runToPosition();
        robot.setPower(1);
        robot.waitForDriveMotorStop();
        robot.setPower(0);
        sleep(100);
        idle();

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


        robot.setDriveRotateRight();
        robot.restartDriveEncoders();
        robot.setDriveDistance(400);
        robot.runToPosition();
        robot.setPower(1);
        robot.waitForDriveMotorStop();
        robot.setPower(0);
        sleep(100);
        idle();

        //drives to almost meet beacons
        robot.setDriveForward();
        robot.restartDriveEncoders();
        robot.setDriveDistance(200);
        robot.runToPosition();
        robot.setPower(1);
        robot.waitForDriveMotorStop();
        robot.setPower(0);
        sleep(10);
        idle();



    }



}
