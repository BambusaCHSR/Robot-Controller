package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Elijah Sauder on 1/20/17.
 **/

class Definitions {
    DcMotor frontRightMotor;
    DcMotor frontLeftMotor;
    DcMotor backRightMotor;
    DcMotor backLeftMotor;

    //Sets the launcher/intake motors
    DcMotor intakeMotor;
    DcMotor launcherMotor;

    float x,y,r,m,n;

    public void init(HardwareMap Map) {

        frontRightMotor = Map.dcMotor.get("frontRightMotor");
        frontLeftMotor = Map.dcMotor.get("frontLeftMotor");
        backRightMotor = Map.dcMotor.get("backRightMotor");
        backLeftMotor = Map.dcMotor.get("backLeftMotor");

        //Initiates the names the intake/launcher system motors
        intakeMotor = Map.dcMotor.get("intakeMotor");
        launcherMotor = Map.dcMotor.get("launcherMotor");

    }

    /*-----------------------------------------------------------------------*/
    /*Autonomous*/
    /*-----------------------------------------------------------------------*/

    /**
     * Movement Directions
     **/

    void setDriveForward() {
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    void setLaunchRotateForward() {
        launcherMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    void setIntakeUp() {
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    /**
     * Rotate
     **/

    void setDriveRotateLeft() {
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    void setDriveRotateRight() {
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    /**
     * restart encoders
     **/

    void restartDriveEncoders() {
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    void restartLauncherEncoders() {
        launcherMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    void restartIntakeEncoders() {
        intakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * movement distance
     **/

    void setDriveDistance(int distance) {
        frontLeftMotor.setTargetPosition(distance);
        frontRightMotor.setTargetPosition(distance);
        backLeftMotor.setTargetPosition(distance);
        backRightMotor.setTargetPosition(distance);
    }

    void setLauncherRotateDistance(int distance) {
        launcherMotor.setTargetPosition(distance);
    }

    void setIntakeRotateDistance(int distance) {
        intakeMotor.setTargetPosition(distance);
    }

    /**
     * run to position
     **/

    void runToPosition() {
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    void runToLauncherPosition() {
        launcherMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    void runToIntakePosition() {
        intakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * drive speed
     **/

    void setPower(int power) {
        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(power);
        backLeftMotor.setPower(power);
        backRightMotor.setPower(power);
    }

    void setLaunchPower(int power) {
        launcherMotor.setPower(power);
    }

    void setIntakePower(double power) {
        intakeMotor.setPower(power);
    }

    /**
     * wait
     **/

    void waitForDriveMotorStop() {
        while (true) {
            if (!(frontLeftMotor.isBusy())) break;
        }
    }

    void waitForLauncherMotorStop() {
        while (true) {
            if (!(launcherMotor.isBusy())) break;

        }
    }

    void waitForIntakeMotorStop() {
        while (true) {
            if (!(intakeMotor.isBusy())) break;
        }
    }

    /*-----------------------------------------------------------------------*/
    /*TeleOp*/
    /*-----------------------------------------------------------------------*/
}
