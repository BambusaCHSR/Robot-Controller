package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsUsbLegacyModule;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsUsbServoController;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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
    Servo servoCapLifterReleaseLeft;
    Servo servoCapLifterReleaseRight;

    ModernRoboticsUsbServoController ServoController;
    /* Initiates all the sensors */
    //Initiates the color sensors
    ColorSensor sensorColorLeft;
    ColorSensor sensorColorRight;
    ColorSensor sensorColorBottom;

    LightSensor sensorLightBottom;
    
    //initiates the distance sensors
    UltrasonicSensor sensorDistanceLeft;
    UltrasonicSensor sensorDistanceRight;
    
    //initiates the gyro sensor
    //GyroSensor sensorGyro;

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


        servoCapLifterReleaseLeft = Map.servo.get("servoCapLifterReleaseLeft");
        servoCapLifterReleaseRight = Map.servo.get("servoCapLifterReleaseRight");

        //--------------------------------------------------------//

        /*Initiates the names of all the sensors*/
        //Initiates the names of the color sensors
        sensorColorLeft = Map.colorSensor.get("sensorColorLeft");
        sensorColorRight = Map.colorSensor.get("sensorColorRight");
        sensorColorBottom = Map.colorSensor.get("sensorColorBottom");

        sensorLightBottom = Map.lightSensor.get("sensorLightBotom");
        //initiates the names of the distance sensors
        sensorDistanceLeft = Map.ultrasonicSensor.get("sensorDistanceLeft");
        sensorDistanceRight = Map.ultrasonicSensor.get("sensorDistanceRight");

        //initiates the names of the gyro sensors
        //sensorGyro = Map.gyroSensor.get("sensorGyro");
    }

    /*-----------------------------------------------------------------------*/
    /* ********************** */ /**Autonomous**/ /* *********************** */
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

    private void setLaunchRotateForward() {
        motorLauncherRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorLauncherLeft.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    void setDriveDiag() {
        motorDriveFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorDriveBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void setIntakeUp() {
        motorIntake.setDirection(DcMotorSimple.Direction.FORWARD);
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

    private void restartLauncherEncoders() {
        motorLauncherRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLauncherLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    private void restartIntakeEncoders() {
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

    void setDriveDiagDistance(int distance) {
        motorDriveFrontLeft.setTargetPosition(distance);
        motorDriveBackRight.setTargetPosition(distance);
    }

    private void setLauncherRotateDistance(int distance) {
        motorLauncherRight.setTargetPosition(distance);
        motorLauncherLeft.setTargetPosition(distance);
    }

    private void setIntakeRotateDistance(int distance) {
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
    void runToPositionDiagonal() {
        motorDriveFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorDriveBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void runToLauncherPosition() {
        motorLauncherRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLauncherLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void runToIntakePosition() {
        motorIntake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * drive speed
     **/

    void setPower(double power) {
        motorDriveFrontLeft.setPower(power);
        motorDriveFrontRight.setPower(power);
        motorDriveBackLeft.setPower(power);
        motorDriveBackRight.setPower(power);
    }

    private void setLaunchPower(double power) {
        motorLauncherRight.setPower(power);
        motorLauncherLeft.setPower(power);
    }

    void setPowerDiag(double power) {
        motorDriveFrontLeft.setPower(power);
        motorDriveBackRight.setPower(power);
    }

    private void setIntakePower(double power) {
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

    private void waitForLauncherMotorStop() {
        while (true) {
            if (!(motorLauncherRight.isBusy())) break;

        }
    }

    private void waitForMotorIntakeStop() {
        while (true) {
            if (!(motorIntake.isBusy())) break;
        }
    }
    
    void launchOneBall() {
        setLaunchRotateForward();
        restartLauncherEncoders();
        setLauncherRotateDistance(1600);
        runToLauncherPosition();
        setLaunchPower(1);
        waitForLauncherMotorStop();
        setLaunchPower(0);
    }
    
    void launchTwoBalls() {
        setLaunchRotateForward();
        restartLauncherEncoders();
        setLauncherRotateDistance(1600);
        runToLauncherPosition();
        setLaunchPower(1);
        waitForLauncherMotorStop();
        setLaunchPower(0);

        //bring the second particle up
        setIntakeUp();
        restartIntakeEncoders();
        setIntakeRotateDistance(4000);
        runToIntakePosition();
        setIntakePower(1);
        waitForMotorIntakeStop();
        setIntakePower(0);

        //launch second particle
        setLaunchRotateForward();
        restartLauncherEncoders();
        setLauncherRotateDistance(1600);
        runToLauncherPosition();
        setLaunchPower(1);
        waitForLauncherMotorStop();
        setLaunchPower(0);
    }

    void driveForwardAndOrBack(int distance, double power) {
        setDriveForward();
        restartDriveEncoders();
        setDriveDistance(distance);
        runToPosition();
        setPower(power);
        waitForDriveMotorStop();
        setPower(0);
    }

    void rotateLeft(int distance) {
         setDriveRotateLeft();
         restartDriveEncoders();
         setDriveDistance(distance);
         runToPosition();
         setPower(1);
         waitForDriveMotorStop();
         setPower(0);
     }

    void rotateRight(int distance) {
         setDriveRotateRight();
         restartDriveEncoders();
         setDriveDistance(distance);
         runToPosition();
         setPower(1);
         waitForDriveMotorStop();
         setPower(0);
     }
    /*-----------------------------------------------------------------------*/
    /* ************************ */ /**TeleOp**/ /* ************************* */
    /*-----------------------------------------------------------------------*/


    void launcherMotors(double power) {
        motorLauncherLeft.setPower(power);
        motorLauncherRight.setPower(power);
    }
}


