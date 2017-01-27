package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Elijah Sauder on 1/21/17.
 **/

@TeleOp(name="BamTeleOp", group="Bambusa")
public class BamTeleOp extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private Definitions robot = new Definitions();
    private boolean motor = false;
    private boolean toggle = true;
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        robot.init(hardwareMap);
        runtime.reset();
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
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
                robot.frontLeftMotor.setPower(0.1 * (-robot.y - robot.x + robot.r));
                robot.frontRightMotor.setPower(0.1 * (robot.y - robot.x + robot.r));
                robot.backRightMotor.setPower(0.1 * (robot.y + robot.x + robot.r));
                robot.backLeftMotor.setPower(0.1 * (-robot.y + robot.x + robot.r));
            }
            else if (gamepad1.right_bumper) {
                robot.frontLeftMotor.setPower(0.25 * (-robot.y - robot.x + robot.r));
                robot.frontRightMotor.setPower(0.25 * (robot.y - robot.x + robot.r));
                robot.backRightMotor.setPower(0.25 * (robot.y + robot.x + robot.r));
                robot.backLeftMotor.setPower(0.25 * (-robot.y + robot.x + robot.r));
            }
            else {
                robot.frontLeftMotor.setPower(-robot.y - robot.x + robot.r);
                robot.frontRightMotor.setPower(robot.y - robot.x + robot.r);
                robot.backRightMotor.setPower(robot.y + robot.x + robot.r);
                robot.backLeftMotor.setPower(-robot.y + robot.x + robot.r);
            }

            if (gamepad2.left_bumper) {
                if (toggle && gamepad2.right_bumper) {
                    toggle = false;
                    if (motor) {
                        motor= false;
                        robot.intakeMotor.setPower(-1);
                    }
                    else {
                        motor= true;
                        robot.intakeMotor.setPower(0);
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
                        robot.intakeMotor.setPower(1);
                    } else {
                        motor= true;
                        robot.intakeMotor.setPower(0);
                    }
                }
                else {
                    if (!gamepad2.right_bumper) {
                        toggle = true;
                    }
                }
            }

            if (gamepad2.a) {
                robot.setLaunchRotateForward();
                robot.restartLauncherEncoders();
                robot.setLauncherRotateDistance(1600);
                robot.runToLauncherPosition();
                robot.setLaunchPower(1);
                robot.waitForLauncherMotorStop();
                robot.setLaunchPower(0);
                idle();
            }
            else if (gamepad2.b) {
                robot.launcherMotor.setPower(1);
            }
            else {
                robot.launcherMotor.setPower(0);
            }
        }
    }
}
