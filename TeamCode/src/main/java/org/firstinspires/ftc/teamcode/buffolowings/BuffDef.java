package org.firstinspires.ftc.teamcode.buffolowings;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by ReaperCrew on 2/21/17
 **/

public class BuffDef {
    /**
     * Initiates all the motors, servos, and sensors
     **/
    /*Initiates all the motors */
    //Initiates the drive motors
    DcMotor motorDriveLeft;
    DcMotor motorDriveRight;

    //initiates the intake motor
    DcMotor motorIntake;

    //initiates the launcher motors
    DcMotor motorLaunchLeft;
    DcMotor motorLaunchRight;

    //-----------------------------------------------//

    /* Initiates all the servos */
    //Initiates ball sweeper motors
    Servo servoSweepLeft;
    Servo servoSweepRight;

    //initiates the button pressing servos
    Servo servoButtonLeft;
    Servo servoButtonRight;

    //-----------------------------------------------//

    /* Initiates all the sensors */
    //initiates the sensor that is responsible for following the white line
    OpticalDistanceSensor sensorODS;

    //initiates our color sensor
    ColorSensor sensorColor;

    //initiates the gyro sensor (not used at this moment (2/21/17))
    GyroSensor sensorGyro;

    //initiates the distance sensor
    UltrasonicSensor sensorDistance;

    //===============================================//

    /**
     * creates all the defined actions called upon in the other programs
     **/
    /* Creates the hardware map */
    public void init(HardwareMap buffMap) {
        /** Initiates all the motor, servo, and sensor names **/
        /* Initiates all the motors */
        //initiates the drive motor names
        motorDriveLeft = buffMap.dcMotor.get("motorDriveLeft");
        motorDriveRight = buffMap.dcMotor.get("motorDriveRight");

        //Initiates the intake motor name
        motorIntake = buffMap.dcMotor.get("motorIntake");

        //Initiates the launcher motor names
        motorLaunchLeft = buffMap.dcMotor.get("motorLaunchLeft");
        motorLaunchRight = buffMap.dcMotor.get("motorLaunchRight");

        //--------------------------------------------------------//

        /*Initiates the names of all the servos*/
        //sets name for ball sweeper servos
        servoSweepLeft = buffMap.servo.get("servoSweepLeft");
        servoSweepRight = buffMap.servo.get("servoSeepRight");

        //sets name for button pressing servos
        servoButtonLeft = buffMap.servo.get("servoButtonLeft");
        servoButtonRight = buffMap.servo.get("servoButtonRight");

        //--------------------------------------------------------//

        /*Initiates the names of all the sensors*/
        //Initiates the name of the color sensor
        sensorColor = buffMap.colorSensor.get("sensorColor");

        //Initiates the name of the ods sensor
        sensorODS = buffMap.opticalDistanceSensor.get("sensorODS");

        //Initiates the name of the gyro sensor
        sensorGyro = buffMap.gyroSensor.get("sensorGyro");

        //initiates the name of the distance sensor
        sensorDistance = buffMap.ultrasonicSensor.get("sensorDistance");
    }

    /*-----------------------------------------------------------------------*/
    /* ********************** */ /**Autonomous**/ /* *********************** */
    /*-----------------------------------------------------------------------*/

    /**
     * Movement Directions
     **/

    void setDriveForward() {
        motorDriveLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorDriveRight.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    void setDriveBackwards() {
        motorDriveLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorDriveRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    void setLauncherDirection() {
        motorLaunchLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorLaunchRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    void setIntakeForward() {
        motorIntake.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    void setIntakeBackwards() {
        motorIntake.setDirection(DcMotorSimple.Direction.FORWARD);
    }
    /**
     * Rotate
     **/

    void setRotateRight() {
        motorDriveLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorDriveRight.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    void setRotateLeft() {
        motorDriveLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorDriveRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    /**
     * restart encoders
     **/

    void restartEncoders() {
        motorDriveLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorDriveRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorLaunchLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLaunchRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorIntake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * movement distance
     **/

    void setDriveDistance(int distance) {
        motorDriveLeft.setTargetPosition(distance);
        motorDriveRight.setTargetPosition(distance);
    }

    void setLauncherRotateDistance(int distance) {
        motorLaunchLeft.setTargetPosition(distance);
        motorLaunchRight.setTargetPosition(distance);
    }

    void setIntakeRotateDistance(int distance) {
        motorIntake.setTargetPosition(distance);
    }

    /**
     * run to position
     **/

    void runToDrivePosition() {
        motorDriveLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorDriveRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    void runToLauncherPosition() {
        motorLaunchLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLaunchRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    void runToIntakePosition() {
        motorIntake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * drive speed
     **/

    void setDrivePower(double power) {
        motorDriveLeft.setPower(power);
        motorDriveRight.setPower(power);
    }

    void setLaunchPower(double power) {
        motorLaunchRight.setPower(power);
        motorLaunchLeft.setPower(power);
    }

    void setIntakePower(double power) {
        motorIntake.setPower(power);
    }

    //this may not be needed in the new code
    /**
     * wait
     **/

    void waitForDriveMotorStop() {
        while (true) {
            if (!(motorDriveLeft.isBusy())) break;
        }
    }

    void waitForLauncherMotorStop() {
        while (true) {
            if (!(motorLaunchRight.isBusy())) break;

        }
    }

    void waitForMotorIntakeStop() {
        while (true) {
            if (!(motorIntake.isBusy())) break;
        }
    }
}
