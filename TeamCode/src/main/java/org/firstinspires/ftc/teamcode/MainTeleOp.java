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
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by CHSRobotics - Elijah Sauder on 12/15/16, 4:34PM.
 **/

@TeleOp(name="MainOpMode", group="Bambusa")
public class MainTeleOp extends LinearOpMode {

    private DefineOp robot= new DefineOp();

    private ElapsedTime runtime = new ElapsedTime();


    private boolean motor = false;
    private boolean toggle = true;


    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        float x,y,r,m,n;
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.left_bumper) {
                robot.driving_Tenth();
            }
            //sets the motor speeds to 1/4 when the gamepad1 right bumper is pressed
            else if (gamepad1.right_bumper) {
                robot.driving_Quarter();
            }
            //sets the default motor speeds to 1
            else {
                robot.driving_Full();
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
                        robot.intakeMotor.setPower(-1);
                    }
                    //if the motor is on toggle it off
                    else {
                        motor= true;
                        robot.intakeMotor.setPower(0);
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

            //sets the launcher motor to gamepad buttons a and b
            //sets the pulling back of the rack to gamepad2 button a
            if (gamepad2.a) {
                robot.launcherMotor.setPower(1);
            }
            //sets a reverse (forward due to the design of the robot) of the motor to gamepad2 button b
            else if (gamepad2.b) {
                robot.launcherMotor.setPower(-0.5);
            }
            //turns off the motor if neither of the buttons are pressed
            else {
                robot.launcherMotor.setPower(0);
            }

            //sets the fork lift to the gamepad2 dpad up and down
            if (gamepad2.dpad_up) {
                robot.forkliftMotorRight.setPower(0.5);
                robot.forkliftMotorLeft.setPower(-0.5);
            }
            else if (gamepad2.dpad_down){
                robot.forkliftMotorRight.setPower(-0.5);
                robot.forkliftMotorLeft.setPower(0.5);
            }
            else {
                robot.forkliftMotorRight.setPower(0);
                robot.forkliftMotorLeft.setPower(0);
            }
            //Allows the hardware to catch up if no input is specified
            idle();
        }
    }

}
