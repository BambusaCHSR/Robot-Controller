package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsUsbController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Elijah Sauder on 1/21/17.
 **/

@TeleOp(name="BamTeleOp", group="Bambusa")
public class BamTeleOp extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime(ElapsedTime.SECOND_IN_NANO);
    private Definitions robot = new Definitions();
    private boolean motor = false;
    private boolean toggle = true;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        robot.init(hardwareMap);
        runtime.reset();

        //sets positions for all servos on robot
        robot.servoCapLifterReleaseRight.setPosition(0.1);
        robot.servoButtonLeft.setPosition(0.4);
        robot.servoButtonRight.setPosition(0.56);

        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addLine();
            telemetry.addData("UltraSensor Right: ", robot.sensorDistanceRight.getUltrasonicLevel());
            telemetry.addData("UltraSensor Left: ",robot.sensorDistanceLeft.getUltrasonicLevel());
            telemetry.addLine();
            telemetry.addData("ColorSensor Right: ", robot.sensorColorRight.hashCode());
            telemetry.addData("ColorSensor Left: ", robot.sensorColorLeft.hashCode());
            telemetry.addData("ColorSensor Bottom: ", robot.sensorColorBottom.hashCode());
            telemetry.addLine();
            telemetry.addData("ServoButton Left: ", robot.servoButtonLeft.getPosition());
            telemetry.addData("ServoButton Right: ", robot.servoButtonRight.getPosition());
            telemetry.addLine();
            telemetry.addData("MotorDrive FrontRight: ",robot.motorDriveFrontRight.getPower());
            telemetry.addData("MotorDrive FrontLeft: ",robot.motorDriveFrontLeft.getPower());
            telemetry.addData("MotorDrive BackRight: ",robot.motorDriveBackRight.getPower());
            telemetry.addData("MotorDrive BackLeft: ",robot.motorDriveBackLeft.getPower());
            telemetry.addLine();
            telemetry.addData("LauncherMotor Right: ",robot.motorLauncherRight.getPower());
            telemetry.addData("LauncherMotor Left: ",robot.motorLauncherLeft.getPower());
            telemetry.addLine();
            telemetry.addData("IntakeMotor: ",robot.motorIntake.getDirection());
            telemetry.addData("CapballMotor: ",robot.motorCapballLifter.getCurrentPosition());
            telemetry.update();

            if(gamepad1.left_stick_x < 0) {
                robot.m=-1;
            }
            else {
                robot.m=1;
            }
            if(gamepad1.left_stick_y < 0) {
                robot.n=1;
            }
            else {
                robot.n=-1;
            }

            robot.x = robot.m * ((float) Math.sqrt(1 - Math.pow((double) gamepad1.left_stick_x, 2)) - 1);
            robot.y = robot.n * ((float) Math.sqrt(1 - Math.pow((double) gamepad1.left_stick_y, 2)) - 1);
            robot.r = gamepad1.right_stick_x;

            if (gamepad1.left_bumper) {
                robot.motorDriveFrontLeft.setPower(0.1 * (-robot.y - robot.x + robot.r));
                robot.motorDriveFrontRight.setPower(0.1 * (robot.y - robot.x + robot.r));
                robot.motorDriveBackRight.setPower(0.1 * (robot.y + robot.x + robot.r));
                robot.motorDriveBackLeft.setPower(0.1 * (-robot.y + robot.x + robot.r));
            }
            else if (gamepad1.right_bumper) {
                robot.motorDriveFrontLeft.setPower(0.25 * (-robot.y - robot.x + robot.r));
                robot.motorDriveFrontRight.setPower(0.25 * (robot.y - robot.x + robot.r));
                robot.motorDriveBackRight.setPower(0.25 * (robot.y + robot.x + robot.r));
                robot.motorDriveBackLeft.setPower(0.25 * (-robot.y + robot.x + robot.r));
            }
            else {
                robot.motorDriveFrontLeft.setPower(-robot.y - robot.x + robot.r);
                robot.motorDriveFrontRight.setPower(robot.y - robot.x + robot.r);
                robot.motorDriveBackRight.setPower(robot.y + robot.x + robot.r);
                robot.motorDriveBackLeft.setPower(-robot.y + robot.x + robot.r);
            }

            if (gamepad2.left_bumper) {
                if (toggle && gamepad2.right_bumper) {
                    toggle = false;
                    if (motor) {
                        motor= false;
                        robot.motorIntake.setPower(-1);
                    }
                    else {
                        motor= true;
                        robot.motorIntake.setPower(0);
                    }
                }
                else {
                    if (!gamepad2.right_bumper) {
                        toggle = true;
                    }
                }
            }
            else {
                if (toggle && gamepad2.right_bumper) {
                    toggle = false;
                    if (motor) {
                        motor= false;
                        robot.motorIntake.setPower(1);
                    } else {
                        motor= true;
                        robot.motorIntake.setPower(0);
                    }
                }
                else {
                    if (!gamepad2.right_bumper) {
                        toggle = true;
                    }
                }
            }

            if (gamepad2.a) {
                robot.motorLauncherLeft.setPower(-1);
                robot.motorLauncherRight.setPower(1);
            }
            else if (gamepad2.b) {
                robot.motorLauncherLeft.setPower(1);
                robot.motorLauncherRight.setPower(-1);
            }
            else {
                robot.motorLauncherLeft.setPower(0);
                robot.motorLauncherRight.setPower(0);
            }

            robot.motorCapballLifter.setPower(-0.5 * gamepad2.left_stick_y);

            if (gamepad2.dpad_up) {
                robot.servoCapLifterReleaseLeft.setPosition(1);
                sleep(1000);
                robot.servoCapLifterReleaseRight.setPosition(1);
            }

            if (gamepad2.right_trigger > 0) {
                robot.servoButtonRight.setPosition(0.9);
            }
            else {
                robot.servoButtonRight.setPosition(0.56);
            }

            if (gamepad2.left_trigger > 0) {
                robot.servoButtonLeft.setPosition(0.1);
            }
            else {
                robot.servoButtonLeft.setPosition(0.4);
            }
        }
    }
}