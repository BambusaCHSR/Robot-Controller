package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;

import static android.R.attr.x;
import static android.R.attr.y;

/**
 * Created by Elijah Sauder on 1/18/17, 10:02PM.
 **/

public class DefineOpTeleOp {

    /** sets all the motors/servo **/
    //Sets the driver motors
    public DcMotor frontRightMotor = null;
    public DcMotor frontLeftMotor = null;
    public DcMotor backRightMotor = null;
    public DcMotor backLeftMotor = null;

    //Sets the launcher/intake motors
    public DcMotor intakeMotor = null;
    public DcMotor launcherMotor = null;

    //Sets the yoga ball lifter
    public DcMotor forkliftMotorRight = null;
    public DcMotor forkliftMotorLeft = null;

    //Sets the servos for the fork prongs
    public Servo forkLeftServo = null;
    public Servo forkRightServo = null;


    HardwareMap map=null;

    private ElapsedTime period  = new ElapsedTime();
    private ElapsedTime runtime = new ElapsedTime();

    public DefineOpTeleOp() {

    }

    public void init(HardwareMap hwm) {
        map=hwm;

        /** Maps the names so they can be called in configuration **/
        //Initiates the names of the driving motors
        frontRightMotor = map.dcMotor.get("frontRightMotor");
        frontLeftMotor = map.dcMotor.get("frontLeftMotor");
        backRightMotor = map.dcMotor.get("backRightMotor");
        backLeftMotor = map.dcMotor.get("backLeftMotor");

        //Initiates the names the intake/launcher system motors
        intakeMotor = map.dcMotor.get("intakeMotor");
        launcherMotor = map.dcMotor.get("launcherMotor");

        //Initiates the names of the forklift motors
        forkliftMotorRight = map.dcMotor.get("forkliftRight");
        forkliftMotorLeft = map.dcMotor.get("forkliftLeft");

        //Initiates the names of the servos for the forklift prongs
        forkRightServo = map.servo.get("forkRight");
        forkLeftServo = map.servo.get("forkLeft");

        /** Sets the start speeds and positions of all the motors and servos **/
        //sets the start speeds of all the driving motors
        int START_DRIVE_POWER = 0;
        frontRightMotor.setPower(START_DRIVE_POWER);
        frontLeftMotor.setPower(START_DRIVE_POWER);
        backRightMotor.setPower(START_DRIVE_POWER);
        backLeftMotor.setPower(START_DRIVE_POWER);

        //sets start speeds of the intake/launcher motors
        intakeMotor.setPower(START_DRIVE_POWER);
        launcherMotor.setPower(START_DRIVE_POWER);

        //sets the start speeds for the forklift
        forkliftMotorRight.setPower(START_DRIVE_POWER);
        forkliftMotorLeft.setPower(START_DRIVE_POWER);

        //sets the default servo positions
        int START_SERVO_POSITION = 0;
        forkRightServo.setPosition(START_SERVO_POSITION);
        forkLeftServo.setPosition(START_SERVO_POSITION);

        /** Sets encoder mode **/
        //sets the encoder mode for the driver motors
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //sets the encoder mode for the intake/launcher motors
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcherMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //sets the encoder mode for the forklift motors
        forkliftMotorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        forkliftMotorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }



    public void move(int enc_val, float power, float drive_power) {
        frontRightMotor.setTargetPosition(enc_val);
        frontLeftMotor.setTargetPosition(enc_val);
        backRightMotor.setTargetPosition(enc_val);
        backLeftMotor.setTargetPosition(enc_val);

        intakeMotor.setTargetPosition(enc_val);
        launcherMotor.setTargetPosition(enc_val);

        frontRightMotor.setPower(drive_power);
        frontLeftMotor.setPower(drive_power);
        backRightMotor.setPower(drive_power);
        backLeftMotor.setPower(drive_power);

        intakeMotor.setPower(power);
        launcherMotor.setPower(power);
    }

    public void reset_encoders() {
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //sets the encoder mode for the intake/launcher motors
        intakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launcherMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public double getRuntime() {
        return runtime.seconds();
    }

    public void waitForTick(long periodMs)  throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }

    private Gamepad gamepad1;
     void variables() {

        int m;
        if(gamepad1.left_stick_x < 0) {
            m = -1;
        }
        else {
            m =1;
        }

        int n;
        if(gamepad1.left_stick_y < 0) {
            n=1;
        }
        else {
            n=-1;
        }

        float x = m * ((float) Math.sqrt(1 - Math.pow((double) gamepad1.left_stick_x, 2)) - 1);
        float y = n * ((float) Math.sqrt(1 - Math.pow((double) gamepad1.left_stick_y, 2)) - 1);
        float r = gamepad1.right_stick_x;
    }

    void driving_Full() {
        variables();
        float r = gamepad1.right_stick_x;
        frontLeftMotor.setPower(-y - x + r);
        frontRightMotor.setPower(y - x + r);
        backRightMotor.setPower(y + x + r);
        backLeftMotor.setPower(-y + x + r);
    }
    void driving_Quarter() {
        variables();
        float r = gamepad1.right_stick_x;
        frontLeftMotor.setPower(0.25 * (-y - x + r));
        frontRightMotor.setPower(0.25 * (y - x + r));
        backRightMotor.setPower(0.25 * (y + x + r));
        backLeftMotor.setPower(0.25 * (-y + x + r));
    }
    void driving_Tenth() {
        variables();
        float r = gamepad1.right_stick_x;
        frontLeftMotor.setPower(0.1 * (-y - x + r));
        frontRightMotor.setPower(0.1 * (y - x + r));
        backRightMotor.setPower(0.1 * (y + x + r));
        backLeftMotor.setPower(0.1 * (-y + x + r));
    }
}
