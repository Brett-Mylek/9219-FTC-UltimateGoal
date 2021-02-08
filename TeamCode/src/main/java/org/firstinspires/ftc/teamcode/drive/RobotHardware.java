package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class RobotHardware
{
    /* Public OpMode members. */
    public DcMotor  leftFront  = null;
    public DcMotor  leftRear   = null;
    public DcMotor  rightFront = null;
    public DcMotor  rightRear  = null;
    public DcMotor  Launch     = null;
    public DcMotor  Intake     = null;
    public DcMotor  Conv       = null;
    public Servo  armPitch     = null;
    public Servo  claw         = null;
    public Servo  launchArm    = null;

    HardwareMap hardwareMap;


    public void init(HardwareMap hardwareMap) {
        // Save reference to Hardware ma

        // Define and Initialize Motors
        leftFront  = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront  = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        Launch =  hardwareMap.get(DcMotor.class, "Launch");
        Intake = hardwareMap.get(DcMotor.class, "Intake");
        Conv = hardwareMap.get(DcMotor.class, "Intake");


        armPitch = hardwareMap.get(Servo.class, "armPitch");
        claw = hardwareMap.get(Servo.class, "claw");
        launchArm = hardwareMap.get(Servo.class, "launchArm");


        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftRear.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.REVERSE);
        Launch.setDirection(DcMotorSimple.Direction.REVERSE);
        Intake.setDirection(DcMotorSimple.Direction.REVERSE);
        Conv.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set all motors to zero power
        leftFront.setPower(0);
        leftRear.setPower(0);
        rightFront.setPower(0);
        rightRear.setPower(0);
        Launch.setPower(0);
        Intake.setPower(0);
        Conv.setPower(0);
        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Launch.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Conv.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        armPitch.setPosition(0);
        claw.setPosition(0);
        launchArm.setPosition(90);

    }
 }

