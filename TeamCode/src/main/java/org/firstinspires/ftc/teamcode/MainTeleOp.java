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

/**
 * Created by CHSRobotics - Elijah Sauder on 12/15/16, 4:34PM.
 **/

@TeleOp(name="MainOpMode", group="Bambusa")
public class MainTeleOp extends LinearOpMode {

    private DefineOpTeleOp robot= new DefineOpTeleOp();
    private int waitTimeMs = 1;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {
            robot.Driving();
            robot.intakeToggle();
            robot.launcher();
            robot.forklift();
            robot.waitForTick(waitTimeMs);
            idle();
        }
    }

}
