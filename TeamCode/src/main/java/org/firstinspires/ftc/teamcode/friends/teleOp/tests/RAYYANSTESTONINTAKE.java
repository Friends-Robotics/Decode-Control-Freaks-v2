package org.firstinspires.ftc.teamcode.friends.teleOp.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name = "Driving")
public class RAYYANSTESTONINTAKE extends LinearOpMode {

    private static double speedModifier = 0.8;

    // Intake variables
    private boolean intakeOn = false;
    private double intakePower = 0.9;      // always 0.9 or -0.9
    private boolean directionPositive = true;

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("FLM");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("BLM");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("FRM");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("BRM");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        DcMotor motor = hardwareMap.dcMotor.get("Motor");

        waitForStart();
        if (isStopRequested()) return;

        Gamepad current = new Gamepad();
        Gamepad previous = new Gamepad();

        while (opModeIsActive()) {

            previous.copy(current);
            current.copy(gamepad1);

            // ---------------------------
            // SPEED MODIFIER TOGGLE
            // ---------------------------
            if (current.touchpad && !previous.touchpad) {
                speedModifier = (speedModifier == 0.8) ? 1.0 : 0.8;
            }

            // ---------------------------
            // DRIVING
            //----------------------------
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower * speedModifier);
            backLeftMotor.setPower(backLeftPower * speedModifier);
            frontRightMotor.setPower(frontRightPower * speedModifier);
            backRightMotor.setPower(backRightPower * speedModifier);

            // ---------------------------
            // INTAKE TOGGLE (gamepad1.b)
            // ---------------------------
            if (current.b && !previous.b) {
                intakeOn = !intakeOn;   // toggle on/off
            }

            // ---------------------------
            // INTAKE DIRECTION TOGGLE (gamepad1.a)
            // ---------------------------
            if (current.a && !previous.a) {
                directionPositive = !directionPositive;
            }

            // Apply intake power
            if (intakeOn) {
                motor.setPower(directionPositive ? 0.9 : -0.9);
            } else {
                motor.setPower(0);
            }

            telemetry.addData("Intake ON:", intakeOn);
            telemetry.addData("Direction:", directionPositive ? "Forward (0.9)" : "Reverse (-0.9)");
            telemetry.addData("Speed Mod:", speedModifier);
            telemetry.update();
        }
    }
}
