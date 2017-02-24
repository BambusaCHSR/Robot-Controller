package org.firstinspires.ftc.teamcode.buffolowings;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by elijah on 2/21/17.
 **/
@Autonomous(name="AutoOp",group = "Buffalo")
public class AutoOp extends LinearOpMode {
    private BuffDef robotBam = new BuffDef();
    private boolean DriveForwardLaunchBall = true;
    private boolean DriveToAndPressBeacon1 = false;
    private boolean DriveToAndPressBeacon2 = false;
    private boolean Stop = false;
    private boolean DriveForwardStart = false;

    private boolean beaconPressed1 = false;
    @Override
    public void runOpMode() throws InterruptedException {
        robotBam.init(hardwareMap);
        robotBam.sensorODS.enableLed(true);
        waitForStart();
        while (opModeIsActive()) {
            /** Drives Forward and Launches one ball **/
            if (DriveForwardLaunchBall && !DriveToAndPressBeacon1 && !DriveToAndPressBeacon2 && !Stop) {
                robotBam.setDriveForward();
                robotBam.restartEncoders();
                robotBam.setDriveDistance(1000);
                robotBam.runToDrivePosition();
                robotBam.setDrivePower(0.5);
                robotBam.waitForDriveMotorStop();
                robotBam.setDrivePower(0);

                robotBam.setLauncherDirection();
                robotBam.setIntakeForward();
                robotBam.restartEncoders();
                robotBam.setLauncherRotateDistance(1000);
                robotBam.setIntakeRotateDistance(1000);
                robotBam.setLaunchPower(1);
                robotBam.setIntakePower(1);
                robotBam.waitForLauncherMotorStop();
                robotBam.waitForMotorIntakeStop();
                robotBam.setLaunchPower(0);
                robotBam.setIntakePower(0);

                DriveForwardLaunchBall = false;
                DriveToAndPressBeacon1 = true;
                DriveToAndPressBeacon2 = false;
                Stop = false;
            }

            /** Drives to the beacons and presses the first one **/
            else if (!DriveForwardLaunchBall && DriveToAndPressBeacon1 && !DriveToAndPressBeacon2 && !Stop) {
                //Rotates towards the beacon wall
                robotBam.setRotateRight();
                robotBam.restartEncoders();
                robotBam.setDriveDistance(3000);
                robotBam.runToDrivePosition();
                robotBam.setDrivePower(0.5);
                robotBam.waitForDriveMotorStop();
                robotBam.setDrivePower(0);

                //Waits for a distance of less then the arbitrary value of 20 to activate button detecting and pressing
                if (robotBam.sensorDistance.getUltrasonicLevel() < 20) {
                    //rotates parallel to the wall
                    robotBam.setRotateLeft();
                    robotBam.restartEncoders();
                    robotBam.setDriveDistance(1000);
                    robotBam.runToDrivePosition();
                    robotBam.setDrivePower(0.5);
                    robotBam.waitForDriveMotorStop();
                    robotBam.setDrivePower(0);

                    //locates the button by detecting the line on the floor
                    if (robotBam.sensorODS.getLightDetected() > 20) {
                        //stops the robot from moving
                        robotBam.setDrivePower(0);

                        //rotates the robot to face the button
                        robotBam.setRotateRight();
                        robotBam.restartEncoders();
                        robotBam.setDriveDistance(1000);
                        robotBam.runToDrivePosition();
                        robotBam.setDrivePower(0.5);
                        robotBam.waitForDriveMotorStop();
                        robotBam.setDrivePower(0);

                        //moves forward to the button and stops
                        if (robotBam.sensorDistance.getUltrasonicLevel() < 10) {
                            robotBam.setDrivePower(0);

                            if (!beaconPressed1) {
                                //tells the robot to press one of the buttons on the beacon if it's one color
                                if (robotBam.sensorColor.blue() > 20) {
                                    robotBam.servoButtonRight.setPosition(0.2);
                                    sleep(10);
                                    robotBam.servoButtonRight.setPosition(0.4);

                                    beaconPressed1 = true;
                                }
                                else if (robotBam.sensorColor.blue() < 20) {
                                    robotBam.servoButtonLeft.setPosition(0.2);
                                    sleep(10);
                                    robotBam.servoButtonLeft.setPosition(0.4);

                                    beaconPressed1 = true;
                                }
                                else {
                                    robotBam.servoButtonRight.setPosition(0.4);
                                    robotBam.servoButtonLeft.setPosition(0.4);
                                }
                            }
                            else {
                                beaconPressed1 = false;
                            }
                        }
                        else {
                            robotBam.setDriveForward();
                            robotBam.setDrivePower(0.5);
                        }
                    }
                    else {
                        robotBam.setDriveForward();
                        robotBam.setDrivePower(0.5);
                    }
                }
                else {
                    robotBam.setDriveForward();
                    robotBam.setDrivePower(0.5);
                }

                DriveForwardLaunchBall = false;
                DriveToAndPressBeacon1 = false;
                DriveToAndPressBeacon2 = true;
                Stop = false;
            }

            /** Drives to and presses beacon 2 **/
            else if (!DriveForwardLaunchBall && !DriveToAndPressBeacon1 && DriveToAndPressBeacon2 && !Stop) {
                robotBam.setDriveBackwards();
                robotBam.restartEncoders();
                robotBam.setDriveDistance(500);
                robotBam.runToDrivePosition();
                robotBam.setDrivePower(0.5);
                robotBam.waitForDriveMotorStop();
                robotBam.setDrivePower(0);

                robotBam.setRotateLeft();
                robotBam.restartEncoders();
                robotBam.setDriveDistance(1000);
                robotBam.runToDrivePosition();
                robotBam.setDrivePower(0.5);
                robotBam.waitForDriveMotorStop();
                robotBam.setDrivePower(0);

                robotBam.setDriveForward();
                if (!DriveForwardStart) {
                    robotBam.setDriveForward();
                    robotBam.setDrivePower(0.5);
                }
                else {
                    if (robotBam.sensorDistance.getUltrasonicLevel() < 20) {
                        //rotates parallel to the wall
                        robotBam.setRotateLeft();
                        robotBam.restartEncoders();
                        robotBam.setDriveDistance(1000);
                        robotBam.runToDrivePosition();
                        robotBam.setDrivePower(0.5);
                        robotBam.waitForDriveMotorStop();
                        robotBam.setDrivePower(0);

                        //locates the button by detecting the line on the floor
                        if (robotBam.sensorODS.getLightDetected() > 20) {
                            //stops the robot from moving
                            robotBam.setDrivePower(0);

                            //rotates the robot to face the button
                            robotBam.setRotateRight();
                            robotBam.restartEncoders();
                            robotBam.setDriveDistance(1000);
                            robotBam.runToDrivePosition();
                            robotBam.setDrivePower(0.5);
                            robotBam.waitForDriveMotorStop();
                            robotBam.setDrivePower(0);

                            //moves forward to the button and stops
                            if (robotBam.sensorDistance.getUltrasonicLevel() < 10) {
                                robotBam.setDrivePower(0);

                                if (!beaconPressed1) {
                                    //tells the robot to press one of the buttons on the beacon if it's one color
                                    if (robotBam.sensorColor.blue() > 20) {
                                        robotBam.servoButtonRight.setPosition(0.2);
                                        sleep(10);
                                        robotBam.servoButtonRight.setPosition(0.4);

                                        beaconPressed1 = true;
                                    }
                                    else if (robotBam.sensorColor.blue() < 20) {
                                        robotBam.servoButtonLeft.setPosition(0.2);
                                        sleep(10);
                                        robotBam.servoButtonLeft.setPosition(0.4);

                                        beaconPressed1 = true;
                                    }
                                    else {
                                        robotBam.servoButtonRight.setPosition(0.4);
                                        robotBam.servoButtonLeft.setPosition(0.4);
                                    }
                                }
                                else {
                                    beaconPressed1 = false;
                                }
                            }
                            else {
                                robotBam.setDriveForward();
                                robotBam.setDrivePower(0.5);
                            }
                        }
                        else {
                            robotBam.setDriveForward();
                            robotBam.setDrivePower(0.5);
                        }
                    }
                    else {
                        robotBam.setDriveForward();
                        robotBam.setDrivePower(0.5);
                    }
                }

                DriveForwardLaunchBall = false;
                DriveToAndPressBeacon1 = false;
                DriveToAndPressBeacon2 = false;
                Stop = true;
            }

            /** Stops the program **/
            else if (!DriveForwardLaunchBall && !DriveToAndPressBeacon1 && !DriveToAndPressBeacon2 && Stop) {
                break;
            }
        }
    }
}
