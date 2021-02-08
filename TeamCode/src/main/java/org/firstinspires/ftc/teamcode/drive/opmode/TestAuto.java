package org.firstinspires.ftc.teamcode.drive.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import static org.firstinspires.ftc.teamcode.drive.opmode.EasyOpenCVExample.RingDeterminationPipeline.RingPosition.FOUR;
import static org.firstinspires.ftc.teamcode.drive.opmode.EasyOpenCVExample.RingDeterminationPipeline.RingPosition.NONE;
import static org.firstinspires.ftc.teamcode.drive.opmode.EasyOpenCVExample.RingDeterminationPipeline.RingPosition.ONE;

@Autonomous
public class TestAuto extends LinearOpMode {



    @Override
    public void runOpMode() {
             OpenCvCamera webcam;
             EasyOpenCVExample.RingDeterminationPipeline pipeline = new EasyOpenCVExample.RingDeterminationPipeline();
             SampleMecanumDrive driveTrain = new SampleMecanumDrive(hardwareMap);




            int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

            // We set the viewport policy to optimized view so the preview doesn't appear 90 deg
            // out when the RC activity is in portrait. We do our actual image processing assuming
            // landscape orientation, though.
            webcam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);

            webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
            {
                @Override
                public void onOpened()
                {
                    webcam.startStreaming(640,360, OpenCvCameraRotation.UPRIGHT);
                    webcam.setPipeline(pipeline);
                }
            });

            waitForStart();

            while (opModeIsActive())
            {
                telemetry.addData("Analysis", pipeline.getAnalysis());
                telemetry.addData("Position", pipeline.position);
                telemetry.update();

                // Don't burn CPU cycles busy-looping in this sample
                sleep(50);
                if (pipeline.position == FOUR) {
                    sleep(5000);
                    webcam.stopStreaming();
                    //code to run to zone C
                    telemetry.addLine("Running to zone C");

                }
                if (pipeline.position == ONE) {
                    sleep(5000);
                    webcam.stopStreaming();
                    //code to run to zone B
                    telemetry.addLine("Running to zone B");

                }
                if (pipeline.position == NONE) {
                    sleep(5000);
                    webcam.stopStreaming();
                    //code to run to zone A
                    telemetry.addLine("Running to zone A");
                    /*
                    run these in this order:
                    Localization test
                    Velocity Pid Tuner
                    Feed Forward tuner
                    Straight test
                    Track Width tuner
                    Turn test
                    Spline test
                    Follower Pid
                     */
                }
            }





    }


}
