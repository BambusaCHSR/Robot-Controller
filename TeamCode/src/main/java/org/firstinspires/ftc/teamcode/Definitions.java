package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by Elijah Sauder on 1/20/17.
 **/

/* Creates the definition java file (Class) */
class Definitions {
    /** Initiates all the motors, servos, and sensors **/
    /*Initiates all the motors */
    //Initiates the drive motors
    DcMotor motorDriveFrontRight;
    DcMotor motorDriveFrontLeft;
    DcMotor motorDriveBackRight;
    DcMotor motorDriveBackLeft;
    
    //initiates the intake motor
    DcMotor motorIntake;
    
    //initiates the launcher motors
    DcMotor motorLauncherRight;
    DcMotor motorLauncherLeft;
    
    //initiates the cap ball lifter motor
    DcMotor motorCapballLifter;
    
    //-----------------------------------------------//
    
    /* Initiates all the servos */
    //initiates the button pressing servos
    Servo servoButtonLeft;
    Servo servoButtonRight;

    /* Initiates all the sensors */
    //Initiates the color sensors
    ColorSensor sensorColorLeft;
    ColorSensor sensorColorRight;
    
    //initiates the light sensors
    LightSensor sensorLightBottom;
    
    //initiates the distance sensors
    UltrasonicSensor sensorDistance;
    
    //initiates the gyro sensor
    GyroSensor sensorGyro;
    
    //===============================================//
    
    /** Initiates variables **/
    //Initiates local variables that are used in TeleOp
    float x, y, r, m, n;
    
    //===============================================//

    /** creates all the defined actions called upon in the other programs **/
    /* Creates a hardware map */
    public void init(HardwareMap Map) {

        /** Initiates all the motor, servo, and sensor names **/
        /* Initiates all the motors */
        //initiates the drive motor names
        motorDriveFrontRight = Map.dcMotor.get("motorDriveFrontRight");
        motorDriveFrontLeft = Map.dcMotor.get("motorDriveFrontLeft");
        motorDriveBackRight = Map.dcMotor.get("motorDriveBackRight");
        motorDriveBackLeft = Map.dcMotor.get("motorDriveBackLeft");

        //Initiates the launcher motor names
        motorLauncherRight = Map.dcMotor.get("motorLauncherRight");
        motorLauncherLeft = Map.dcMotor.get("motorLauncherLeft");

        //initiates the intake motor names
        motorIntake = Map.dcMotor.get("motorIntake");

        //initiates the capball lifter motor names
        motorCapballLifter = Map.dcMotor.get("motorCapballLifter");

        //--------------------------------------------------------//

        /*Initiates the names of all the servos*/
        //sets name for Servos
        servoButtonLeft = Map.servo.get("servoButtonLeft");
        servoButtonRight = Map.servo.get("servoButtonRight");

        //--------------------------------------------------------//

        /*Initiates the names of all the sensors*/
        //Initiates the names of the color sensors
        sensorColorLeft = Map.colorSensor.get("sensorColorLeft");
        sensorColorRight = Map.colorSensor.get("sensorColorRight");

        //initiates the names of the light sensors
        sensorLightBottom = Map.lightSensor.get("sensorLightBottom");

        //initiates the names of the distance sensors
        sensorDistance = Map.ultrasonicSensor.get("sensorDistance");

        //initiates the names of the gyro sensors
        sensorGyro = Map.gyroSensor.get("sensorGyro");
    }

    /*-----------------------------------------------------------------------*/
    /* ********************** */ /**Autonomous*/ /* ************************ */
    /*-----------------------------------------------------------------------*/

    /**
     * Movement Directions
     **/

    void setDriveForward() {
        motorDriveFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorDriveBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorDriveFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorDriveBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    void setLaunchRotateForward() {
        motorLauncherRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorLauncherLeft.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    void setIntakeUp() {
        motorIntake.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    void setLaunchRotateBackward() {
        motorLauncherRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorLauncherLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    /**
     * Rotate
     **/

    void setDriveRotateLeft() {
        motorDriveFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorDriveBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorDriveFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorDriveBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    void setDriveRotateRight() {
        motorDriveFrontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorDriveBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorDriveFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorDriveBackRight.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    /**
     * restart encoders
     **/

    void restartDriveEncoders() {
        motorDriveFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorDriveFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorDriveBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorDriveBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    void restartLauncherEncoders() {
        motorLauncherRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLauncherLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    void restartIntakeEncoders() {
        motorIntake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * movement distance
     **/

    void setDriveDistance(int distance) {
        motorDriveFrontLeft.setTargetPosition(distance);
        motorDriveFrontRight.setTargetPosition(distance);
        motorDriveBackLeft.setTargetPosition(distance);
        motorDriveBackRight.setTargetPosition(distance);
    }

    void setLauncherRotateDistance(int distance) {
        motorLauncherRight.setTargetPosition(distance);
        motorLauncherLeft.setTargetPosition(distance);
    }

    void setIntakeRotateDistance(int distance) {
        motorIntake.setTargetPosition(distance);
    }

    /**
     * run to position
     **/

    void runToPosition() {
        motorDriveFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorDriveFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorDriveBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorDriveBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    void runToLauncherPosition() {
        motorLauncherRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLauncherLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    void runToIntakePosition() {
        motorIntake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * drive speed
     **/

    void setPower(int power) {
        motorDriveFrontLeft.setPower(power);
        motorDriveFrontRight.setPower(power);
        motorDriveBackLeft.setPower(power);
        motorDriveBackRight.setPower(power);
    }

    void setLaunchPower(int power) {
        motorLauncherRight.setPower(power);
        motorLauncherLeft.setPower(power);
    }

    void setIntakePower(double power) {
        motorIntake.setPower(power);
    }

    /**
     * wait
     **/

    void waitForDriveMotorStop() {
        while (true) {
            if (!(motorDriveFrontLeft.isBusy())) break;
        }
    }

    void waitForLauncherMotorStop() {
        while (true) {
            if (!(motorLauncherRight.isBusy())) break;

        }
    }

    void waitFormotorIntakeStop() {
        while (true) {
            if (!(motorIntake.isBusy())) break;
        }
    }

    void waitForColorSensorsToReadBeacon() {
        sensorColorRight.red();
        sensorColorRight.blue();
        sensorColorRight.alpha();
        sensorColorRight.argb();

        sensorColorLeft.red();
        sensorColorLeft.blue();
        sensorColorLeft.alpha();
        sensorColorLeft.argb();

    }




    /*-----------------------------------------------------------------------*/
    /*TeleOp*/
    /*-----------------------------------------------------------------------*/


    void launcherMotor(int power) {
        motorLauncherLeft.setPower(power);
        motorLauncherRight.setPower(power);
    }
}


