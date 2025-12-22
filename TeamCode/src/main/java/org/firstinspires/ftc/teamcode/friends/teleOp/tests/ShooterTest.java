package org.firstinspires.ftc.teamcode.friends.teleOp.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

// Add a motor to spin the turret around

@TeleOp(name =  "Shooter Test")
public class ShooterTest extends LinearOpMode {
    private static float power = 0.0f;
    private static float servoPosition = 0.0f;

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor motor1 = hardwareMap.dcMotor.get("Motor1");
        DcMotor motor2 = hardwareMap.dcMotor.get("Motor2");

        Servo servo = hardwareMap.servo.get("Servo");
        servo.setDirection(Servo.Direction.FORWARD);

        waitForStart();

        if (isStopRequested()) return;

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();

        while (opModeIsActive()) {
            previousGamepad1.copy(currentGamepad1);
            currentGamepad1.copy(gamepad1);

            /// Shooting
            if(gamepad1.touchpad) {
                motor1.setPower(power);
                motor2.setPower(power);
            } else {
                motor1.setPower(0.0f);
                motor2.setPower(0.0f);
            }

            if (currentGamepad1.dpad_up && !previousGamepad1.dpad_up) {
                power += 0.1f;
            }
            if (currentGamepad1.dpad_down && !previousGamepad1.dpad_down){
                power -= 0.1f;
            }

            power = Math.min(1.0f, power);
            power = Math.max(-1.0f, power);

            /// Shooter Angle

            if(gamepad1.left_bumper && gamepad1.right_bumper)
                servo.setPosition(servoPosition);


            if(currentGamepad1.right_trigger == 1.0f && !(previousGamepad1.right_trigger == 1.0f))
                servoPosition += 0.05f;
            else if(currentGamepad1.left_trigger == 1.0f && !(previousGamepad1.left_trigger == 1.0f))
                servoPosition -= 0.05f;

            servoPosition = Math.min(1.0f, servoPosition);
            servoPosition = Math.max(0f, servoPosition);

            telemetry.addData("Power: ", power);
            telemetry.addData("Angle: ", servoPosition);
            telemetry.update();
        }
    }
}