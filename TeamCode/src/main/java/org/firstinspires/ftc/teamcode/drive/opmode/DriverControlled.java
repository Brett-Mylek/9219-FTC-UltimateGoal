package org.firstinspires.ftc.teamcode.drive.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.drive.RobotHardware;


@TeleOp(name="Pushbot: Teleop Tank", group="Pushbot")

public class DriverControlled extends OpMode{

    /* Declare OpMode members. */
    RobotHardware robot       = new RobotHardware();



    //Code to run ONCE when the driver hits INIT

    @Override
    public void init() {

        robot.init(hardwareMap);
        telemetry.addData("Say", "Hello Driver");    //
    }


    //Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY

    @Override
    public void init_loop() {


    }


   // Code to run ONCE when the driver hits PLAY

    @Override
    public void start() {
    }

    // Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP

    @Override
    public void loop() {
        double left;
        double right;

        //Normal Robot articulation
        robot.leftFront.setPower(gamepad1.left_stick_y);
        robot.leftRear.setPower(-gamepad1.left_stick_y);
        robot.rightFront.setPower(-gamepad1.right_stick_y);
        robot.rightRear.setPower(gamepad1.right_stick_y);


        // Strafing setup
        if (gamepad1.right_bumper) {
            robot.leftFront.setPower(-.5);
            robot.leftRear.setPower(-.5);
            robot.rightFront.setPower(-.5);
            robot.rightRear.setPower(-.5);
        }
        if (gamepad1.left_bumper) {
            robot.leftFront.setPower(.5);
            robot.leftRear.setPower(.5);
            robot.rightFront.setPower(.5);
            robot.rightRear.setPower(.5);
        }
        //Spin Up the shooter

        if (gamepad1.left_trigger > 0.5) {
            robot.Launch.setPower(1);
        } else {
            robot.Launch.setPower(0);
        }


        // initiate the running of the intake
        if (gamepad1.x) {
            robot.Intake.setPower(-1);
            robot.Conv.setPower(-1);
        } else {
            robot.Intake.setPower(0);
            robot.Conv.setPower(0);
        }//updateed


        //control the pitch of the wabble goal claw
        if (gamepad1.dpad_up) {
            robot.armPitch.setPosition(90);
        } else if (gamepad1.dpad_down) {
            robot.armPitch.setPosition(0);
        }

        //controlling the wabble goal claw
        if(gamepad1.a) {
            robot.claw.setPosition(90);
        } else if (gamepad1.b){
            robot.claw.setPosition(0);
        }

        //Push rings into firing chamber
        if (gamepad1.right_trigger > 0.5) {
            robot.launchArm.setPosition(0);
        } else {
            robot.launchArm.setPosition(90);
        }





        // Send telemetry message to signify robot running;
        telemetry.addData("claw",  "Offset = %.2f");
        telemetry.addData("left",  "%.2f");
        telemetry.addData("right", "%.2f");
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}