package org.firstinspires.ftc.teamcode.drive.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.drive.RobotHardware;


@TeleOp(name="Pushbot: Teleop Tank", group="Pushbot")

public class DriverControlled extends OpMode{

    /* Declare OpMode members. */
    RobotHardware robot       = new RobotHardware(); // use the class created to define a Pushbot's hardware
    double          clawOffset  = 0.0 ;                  // Servo mid position
    final double    CLAW_SPEED  = 0.02 ;                 // sets rate to move servo

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double left;
        double right;

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        robot.leftFront.setPower(gamepad1.left_stick_y);
        robot.leftRear.setPower(-gamepad1.left_stick_y);
        robot.rightFront.setPower(-gamepad1.right_stick_y);
        robot.rightRear.setPower(gamepad1.right_stick_y);

        if (gamepad1.left_bumper) {
            robot.leftFront.setPower(-.5);
            robot.leftRear.setPower(-.5);
            robot.rightFront.setPower(-.5);
            robot.rightRear.setPower(-.5);
        }
        if (gamepad1.right_bumper) {
            robot.leftFront.setPower(.5);
            robot.leftRear.setPower(.5);
            robot.rightFront.setPower(.5);
            robot.rightRear.setPower(.5);
        }





        // Move both servos to new position.  Assume servos are mirror image of each other.




        // Send telemetry message to signify robot running;
        telemetry.addData("claw",  "Offset = %.2f", clawOffset);
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