package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by elijah on 2/3/17.
 **/
@Disabled
@Autonomous(name="AUTOOP",group = "Bambusa")
public class AUTOOP extends LinearOpMode {
    private Definitions robot = new Definitions();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        robot.sensorColorBottom.enableLed(true);
        waitForStart();
        robot.setDriveDiag();
        while (robot.sensorColorBottom.alpha() < 20) {
            robot.setPowerDiag(0.5);
        }
        robot.setPowerDiag(0);
        while (robot.sensorColorBottom.alpha() > 20) {
            if (robot.sensorDistanceLeft.getUltrasonicLevel() == robot.sensorDistanceRight.getUltrasonicLevel()) {
                robot.setPower(0.15);
            } else if (robot.sensorDistanceLeft.getUltrasonicLevel() > robot.sensorDistanceRight.getUltrasonicLevel()) {
                robot.setDriveRotateLeft();
                robot.setPower(0.15);
            } else if (robot.sensorDistanceLeft.getUltrasonicLevel() < robot.sensorDistanceRight.getUltrasonicLevel()) {
                robot.setDriveRotateRight();
                robot.setPower(0.25);
            } else if (robot.sensorDistanceLeft.getUltrasonicLevel() == 20 && robot.sensorDistanceRight.getUltrasonicLevel() == 20) {
                robot.setPower(0);
            } else {
                robot.motorDriveBackRight.setPower(0.5);
                robot.motorDriveBackLeft.setPower(-0.5);
                robot.motorDriveFrontLeft.setPower(-0.5);
                robot.motorDriveFrontRight.setPower(0.5);
            }
            while (robot.sensorColorLeft.blue() > 20 || robot.sensorColorRight.blue() > 20) {
                if (robot.sensorColorRight.red() == robot.sensorColorLeft.red()) {
                    robot.servoButtonLeft.setPosition(0.4);
                    sleep(100);
                    robot.servoButtonLeft.setPosition(0.1);
                    sleep(1000);
                } else if (robot.sensorColorLeft.red() > 20 && robot.sensorColorRight.red() < 20) {
                    robot.servoButtonLeft.setPosition(0.4);
                    sleep(100);
                    robot.servoButtonLeft.setPosition(0.1);
                    sleep(1000);
                } else if (robot.sensorColorLeft.red() < 20 && robot.sensorColorRight.red() > 20) {
                    robot.servoButtonRight.setPosition(0.9);
                    sleep(100);
                    robot.servoButtonRight.setPosition(0.56);
                    sleep(1000);
                } else {
                    robot.servoButtonRight.setPosition(0.56);
                    robot.servoButtonLeft.setPosition(0.1);
                }
            }

            robot.driveForwardAndOrBack(400, -1);
        }
        while (robot.sensorColorBottom.alpha() < 20) {
            robot.motorDriveBackRight.setPower(-0.5);
            robot.motorDriveBackLeft.setPower(0.5);
            robot.motorDriveFrontLeft.setPower(0.5);
            robot.motorDriveFrontRight.setPower(-0.5);
        }
    }
}
