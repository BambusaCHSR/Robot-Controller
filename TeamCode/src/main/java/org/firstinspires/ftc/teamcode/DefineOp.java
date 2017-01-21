package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Elijah Sauder on 1/18/17, 10:02PM.
 **/

class DefineOpTeleOp {
     /** sets all the motors/servo **/
    //Sets the driver motors
    private DcMotor frontRightMotor = null;
    private DcMotor frontLeftMotor = null;
    private DcMotor backRightMotor = null;
    private DcMotor backLeftMotor = null;

    //Sets the launcher/intake motors
    DcMotor intakeMotor = null;
    DcMotor launcherMotor = null;

    //Sets the yoga ball lifter
    DcMotor forkliftMotorRight = null;
    DcMotor forkliftMotorLeft = null;

    //Sets the servos for the fork prongs
    private Servo forkLeftServo = null;
    private Servo forkRightServo = null;


    private HardwareMap map = null;

    private ElapsedTime period = new ElapsedTime();
    private ElapsedTime runtime = new ElapsedTime();

    DefineOpTeleOp() {

    }

    public void init(HardwareMap hwm) {
        map = hwm;

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

    public void resetEncoders() {
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

     private Gamepad gamepad1;
     private float stickY,stickX,rotate;
     private void variables() {

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

        stickX = m * ((float) Math.sqrt(1 - Math.pow((double) gamepad1.left_stick_x, 2)) - 1);
        stickY = n * ((float) Math.sqrt(1 - Math.pow((double) gamepad1.left_stick_y, 2)) - 1);
        rotate = gamepad1.right_stick_x;
    }

    private void driving(double power) {
        variables();
        frontLeftMotor.setPower(power*(-stickY - stickX + rotate));
        frontRightMotor.setPower(power*(stickY - stickX + rotate));
        backRightMotor.setPower(power*(stickY + stickX + rotate));
        backLeftMotor.setPower(power*(-stickY + stickX + rotate));
    }

    void Driving() {
        if (gamepad1.left_bumper) {
            driving(0.1);
        }
        else if (gamepad1.right_bumper) {
            driving(0.25);
        }
        else {
            driving(1);
        }
    }

    private boolean motor = false;
    private boolean toggle = true;
    private Gamepad gamepad2;

    private void intakeToggleForward() {
        if (toggle && gamepad2.right_bumper) {
            toggle = false;
            if (motor) {
                motor= false;
                intakeMotor.setPower(1);
            } else {
                motor= true;
                intakeMotor.setPower(0);
            }
        }
        else {
            if (!gamepad2.right_bumper) {
                toggle = true;
            }
        }
    }

    private void intakeToggleReverse(){
        if (toggle && gamepad2.right_bumper) {
            toggle = false;
            if (motor) {
                motor= false;
                intakeMotor.setPower(-1);
            }
            else {
                motor= true;
                intakeMotor.setPower(0);
            }
        }
        else {
            if (!gamepad2.right_bumper) {
                toggle = true;
            }
        }
    }

    void intakeToggle() {
        if (gamepad2.left_bumper) {
            intakeToggleReverse();
        }
        else {
            intakeToggleForward();
        }
    }

    void launcher() {
        double launcherPower = 1;

        if (gamepad2.a) {
            launcherMotor.setPower(launcherPower);
        }
        else if (gamepad2.b) {
            launcherMotor.setPower(launcherPower/2);
        }
        else {
            launcherMotor.setPower(0*launcherPower);
        }
    }
    void forklift() {
        double forkliftPower = 0.5;
        if (gamepad2.dpad_up) {
            forkliftMotorRight.setPower(forkliftPower);
            forkliftMotorLeft.setPower(-forkliftPower);
        }
        else if (gamepad2.dpad_down){
            forkliftPower = -0.5;
            forkliftMotorRight.setPower(forkliftPower);
            forkliftMotorLeft.setPower(-forkliftPower);
        }
        else {
            forkliftPower = 0;
            forkliftMotorRight.setPower(0*forkliftPower);
            forkliftMotorLeft.setPower(0*forkliftPower);
        }
    }

    public void setGamepad1(Gamepad gamepad1) {
        this.gamepad1 = gamepad1;
    }

    public void setGamepad2(Gamepad gamepad2) {
        this.gamepad2 = gamepad2;
    }
}

