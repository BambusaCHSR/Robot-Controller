/*
        This is the code for bambusa's 2016-17 holonomic omnidirectional wheel robot for TeleOp which,
    was coded by Elijah Sauder and Šimon Hutař. The competition this was made for was the FTC Velocity Vortex.
    Our launcher system is a potential energy design made using "rubber bands" (surgical tubing),
    which we pull back to then release and fire our "particle". The intake is a sweeper design which
    is made using zip-ties, it can also be used to score particles in the corner vortexes if the
    launcher doesn't work.

        If you are not part of team 6226 Bambusa and would like to use all of or only part of on your own robot
    please give the proper credit, otherwise feel free use it. If you are having any problems with the code
    contact the bambusa team at bambusachsr@gmail.com.
*/

package org.firstinspires.ftc.teamcode;

//imports all the necessary libraries
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by CHSRobotics - Elijah Sauder on 12/15/16, 4:34PM.
 **/

//Creates the and names the TeleOp to control the robot
    @Disabled
//@TeleOp(name="MainOpMode", group="Bambusa")
public class MainOpMode extends LinearOpMode {

    //initiates the runtime variable and sets it to show the runtime of the TeleOp
    private ElapsedTime runtime = new ElapsedTime();

    //init the motor and toggle variable for the intake toggle
    private boolean motor = false;
    private boolean toggle = true;

    //Sets the driver motors
    private DcMotor frontRightMotor = null;
    private DcMotor frontLeftMotor = null;
    private DcMotor backRightMotor = null;
    private DcMotor backLeftMotor = null;

    //Sets the launcher/intake motors
    private DcMotor intakeMotor = null;
    private DcMotor launcherMotor = null;

    //Sets the yoga ball lifter
    private DcMotor forkliftMotorRight = null;
    private DcMotor forkliftMotorLeft = null;

    //Sets the servos for the fork prongs
    private Servo forkLeftServo = null;
    private Servo forkRightServo = null;


    //Starts the initiation for the robot when you press the init on the app
    @Override
    public void runOpMode() throws InterruptedException {

        //Adds details on the phone screen that say when the robot is initialized
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //Initiates the names of the driving motors
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");

        //Initiates the names the intake/launcher system motors
        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
        launcherMotor = hardwareMap.dcMotor.get("launcherMotor");

        //Initiates the names of the forklift motors
        forkliftMotorRight = hardwareMap.dcMotor.get("forkliftRight");
        forkliftMotorLeft = hardwareMap.dcMotor.get("forkliftLeft");

        //Initiates the names of the servos for the forklift prongs
        forkRightServo = hardwareMap.servo.get("forkRight");
        forkLeftServo = hardwareMap.servo.get("forkLeft");

        //Makes sure that the servos are in the upright position to start
        forkRightServo.setPosition(0);
        forkLeftServo.setPosition(0);

        //Initiates all the variables that will be used
        float x,y,r,m,n;

        //Makes sure the TeleOp timer is set to 0 when TeleOp starts
        runtime.reset();

        //tells the program to wait for you to start the robot
        waitForStart();

        //Starts and Specifies what happens in TeleOp when you press the play button on the phone
        while (opModeIsActive()) {

            //starts the TeleOp timer on the phone
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

            /** sets everything for controller one **/
            //This is used to give us the proper ratios for moving forward an backwards.
            //If robot is moving the wrong direction play with these first.

            //Sets the m Variable to -1 if the gamepad left stick input is less then zero and 1 if it is greater then zero.
            if(gamepad1.left_stick_x < 0) {
                m=-1;
            }
            else {
                m=1;
            }

            //Sets the n Variable to 1 if the gamepad left stick input is less then zero and -1 if it is greater then zero
            if(gamepad1.left_stick_y < 0) {
                n=1;
            }
            else {
                n=-1;
            }

            //Sets the x, y, and r variables to the gamepad controller sticks and converts degrees to radians
            //so we get a smooth acceleration in all directions

            //the x variable is set to gamepad1 left stick x axis
            //the y variable is set to gamepad1 left stick y axis
            //the r variable is set to the gamepad1 right stick x axis
            //x and y are responsible for forward/left/right/backwards movement and diagonals,
            //r is responsible for the rotational movement
            x = m * ((float) Math.sqrt(1 - Math.pow((double) gamepad1.left_stick_x, 2)) - 1);
            y = n * ((float) Math.sqrt(1 - Math.pow((double) gamepad1.left_stick_y, 2)) - 1);
            r = gamepad1.right_stick_x;

            /*sets the motor speeds*/

            //adds a super slow mode and a slow mode for better control dor detailed maneuvers and sets the
            //regular speed to the max of 1

            //sets the motor speeds to 1/10 when the gamepad1 left bumper is pressed
            if (gamepad1.left_bumper) {
                frontLeftMotor.setPower(0.1 * (-y - x + r));
                frontRightMotor.setPower(0.1 * (y - x + r));
                backRightMotor.setPower(0.1 * (y + x + r));
                backLeftMotor.setPower(0.1 * (-y + x + r));
            }
            //sets the motor speeds to 1/4 when the gamepad1 right bumper is pressed
            else if (gamepad1.right_bumper) {
                frontLeftMotor.setPower(0.25 * (-y - x + r));
                frontRightMotor.setPower(0.25 * (y - x + r));
                backRightMotor.setPower(0.25 * (y + x + r));
                backLeftMotor.setPower(0.25 * (-y + x + r));
            }
            //sets the default motor speeds to 1
            else {
                frontLeftMotor.setPower(-y - x + r);
                frontRightMotor.setPower(y - x + r);
                backRightMotor.setPower(y + x + r);
                backLeftMotor.setPower(-y + x + r);
            }

            /** sets everything for controller two **/
            //sets the intake toggle and a reverse
            //sets the reverse of the toggle to a press and hold of the gamepad2 left bumper
            if (gamepad2.left_bumper) {
                //sets the toggle of the motor to the gamepad2 right bumper
                if (toggle && gamepad2.right_bumper) {
                    //if the toggle variable is false activate the toggle code
                    toggle = false;
                    //if the motor is off toggle the motor on and reverse
                    if (motor) {
                        motor= false;
                        intakeMotor.setPower(-1);
                    }
                    //if the motor is on toggle it off
                    else {
                        motor= true;
                        intakeMotor.setPower(0);
                    }
                }
                //sets a lock on the toggle variable so it works properly
                else {
                    //if the toggle variable is false set it to true
                    if (!gamepad2.right_bumper) {
                        toggle = true;
                    }
                }
            }
            //if the left bumper is not pressed activate the non-reversed code
            //same thing as above just with the motor moving forward
            else {
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

            //sets the launcher motor to gamepad buttons a and b
            //sets the pulling back of the rack to gamepad2 button a
            if (gamepad2.a) {
                launcherMotor.setPower(1);
            }
            //sets a reverse (forward due to the design of the robot) of the motor to gamepad2 button b
            else if (gamepad2.b) {
                launcherMotor.setPower(-0.5);
            }
            //turns off the motor if neither of the buttons are pressed
            else {
                launcherMotor.setPower(0);
            }

            //sets the fork lift to the gamepad2 dpad up and down
            if (gamepad2.dpad_up) {
                forkliftMotorRight.setPower(0.5);
                forkliftMotorLeft.setPower(-0.5);
            }
            else if (gamepad2.dpad_down){
                forkliftMotorRight.setPower(-0.5);
                forkliftMotorLeft.setPower(0.5);
            }
            else {
                forkliftMotorRight.setPower(0);
                forkliftMotorLeft.setPower(0);
            }
            //Allows the hardware to catch up if no input is specified
            idle();
        }
    }

}
