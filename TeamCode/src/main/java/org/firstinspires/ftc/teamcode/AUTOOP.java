package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by elijah on 2/3/17.
 */
@Autonomous(name="AUTOOP",group = "Bambusa")
public class AUTOOP extends LinearOpMode {
    private Definitions robot = new Definitions();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        robot.sensorLightBottom.enableLed(true);
        waitForStart();

        robot.driveForwardAndOrBack(200, 0.5);
        sleep(10);
        idle();

        //robot is tilting so this tilts it back in order to make the shot
        robot.rotateLeft(200);
        sleep(100);
        idle();

        robot.setDriveDiag();
        while (robot.sensorLightBottom.getLightDetected() < 20) {
            robot.setPowerDiag(0.5);
        }
        robot.setPowerDiag(0);
        sleep(100);
        while (robot.sensorLightBottom.getRawLightDetected() > 20) {
            robot.setPower(0.5);
            while (robot.sensorDistanceLeft.getUltrasonicLevel() == robot.sensorDistanceRight.getUltrasonicLevel()) {
                robot.setPower(0.25);
            }
            while (robot.sensorDistanceLeft.getUltrasonicLevel() > robot.sensorDistanceRight.getUltrasonicLevel()) {
                robot.setDriveRotateLeft();
                robot.setPower(0.25);
            }
            while (robot.sensorDistanceLeft.getUltrasonicLevel() < robot.sensorDistanceRight.getUltrasonicLevel()) {
                robot.setDriveRotateRight();
                robot.setPower(0.25);
            }
            while (robot.sensorDistanceLeft.getUltrasonicLevel() == 20 && robot.sensorDistanceRight.getUltrasonicLevel() == 20) {
                robot.setPower(0);
            }
        }
    }
}
