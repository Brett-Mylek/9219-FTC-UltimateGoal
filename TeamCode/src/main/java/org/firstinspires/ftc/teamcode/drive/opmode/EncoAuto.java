package org.firstinspires.ftc.teamcode.drive.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.drive.RobotHardware;

@Autonomous
public class EncoAuto extends LinearOpMode {

    RobotHardware robot = new RobotHardware();
    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 383.6 ;
    static final double     DRIVE_GEAR_REDUCTION    = 13.7 ;
    static final double     WHEEL_DIAMETER_INCHES   = 3.93 ;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.75;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() {


        //Initialize the drive system variables

        robot.init(hardwareMap);


        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();

        robot.leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                robot.leftFront.getCurrentPosition(),
                robot.leftRear.getCurrentPosition(),
                robot.rightFront.getCurrentPosition(),
                robot.rightRear.getCurrentPosition());
        telemetry.update();

        // Wait for start
        waitForStart();

        // Step through each leg of the path,
        encoderDrive(DRIVE_SPEED,  48,  48, 48, 48, 5.0);  // S1: Forward 47 Inches with 5 Sec timeout
        encoderDrive(TURN_SPEED,   12, -12, 12, -12, 4.0);  // S2: Turn Right 12 Inches with 4 Sec timeout
        encoderDrive(DRIVE_SPEED, -24, -24, -24,-24, 4.0);  // S3: Reverse 24 Inches with 4 Sec timeout


        sleep(1000);

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }


    public void encoderDrive(double speed, double leftFrontInches, double rightFrontInches, double leftRearInches, double rightRearInches   , double timeoutS) {

        int newleftFrontTarget;
        int newleftRearTarget;
        int newrightFrontTarget;
        int newrightRearTarget;


        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newleftFrontTarget = robot.leftFront.getCurrentPosition() + (int)(leftFrontInches * COUNTS_PER_INCH);
            newleftRearTarget = robot.leftRear.getCurrentPosition() + (int)(leftRearInches * COUNTS_PER_INCH);
            newrightFrontTarget = robot.rightFront.getCurrentPosition() + (int)(rightFrontInches * COUNTS_PER_INCH);
            newrightRearTarget = robot.rightRear.getCurrentPosition() + (int)(rightRearInches * COUNTS_PER_INCH);
            robot.leftFront.setTargetPosition(newleftFrontTarget);
            robot.leftRear.setTargetPosition(newleftRearTarget);
            robot.leftFront.setTargetPosition(newrightFrontTarget);
            robot.leftRear.setTargetPosition(newrightRearTarget);

            // Turn On RUN_TO_POSITION
            robot.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // reset the timeout time and start motion.
            runtime.reset();
            robot.leftFront.setPower(Math.abs(speed));
            robot.leftRear.setPower(Math.abs(speed));
            robot.rightFront.setPower(Math.abs(speed));
            robot.rightRear.setPower(Math.abs(speed));


            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.leftFront.isBusy() && robot.leftRear.isBusy() && robot.rightFront.isBusy() && robot.rightRear.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newleftFrontTarget, newleftRearTarget, newrightFrontTarget, newrightRearTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        robot.leftFront.getCurrentPosition(),
                        robot.leftRear.getCurrentPosition(),
                        robot.rightFront.getCurrentPosition(),
                        robot.rightRear.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.leftFront.setPower(0);
            robot.leftRear.setPower(0);
            robot.rightFront.setPower(0);
            robot.rightRear.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(300);

        }
    }

}
