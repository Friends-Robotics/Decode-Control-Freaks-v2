package org.firstinspires.ftc.teamcode.friends;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class hardwareMap {

    // Add April tag objects to HardwareMap
    // Add shooterTest motor

    /*
        -----------------------------------------------------------------------
        | Driver Station    | Name                  | Control HUB Location    |
        -----------------------------------------------------------------------
        | FRM               | Front Right Wheel     | Control Hub Motor 0     |
        --------------------+-----------------------+--------------------------
        | BRM               | Back Right Wheel      | Control Hub Motor 1     |
        --------------------+-----------------------+--------------------------
        | BLM               | Back Left Wheel       | Control Hub Motor 2     |
        --------------------+-----------------------+--------------------------
        | FLM               | Front Left Wheel      | Control Hub Motor 3     |
        -----------------------------------------------------------------------
        | Intake            | Intake Motor          | Expansion Hub Motor 0   |
        -----------------------------------------------------------------------
        | Shooter1          | Shooter Motor 1       | Expansion Hub Motor 1   |
        -----------------------------------------------------------------------
        | Shooter2          | Shooter Motor 2       | Expansion Hub Motor 2   |
        -----------------------------------------------------------------------
        | TurretMotor       | Turret Motor          | Expansion Hub Motor 3   |
        -----------------------------------------------------------------------
        | TurretServo       | Turret Servo          | Control Hub Servo 0     |
        -----------------------------------------------------------------------
     */

    public DcMotor frontLeftMotor;
    public DcMotor backLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor backRightMotor;

    public DcMotor intakeMotor;

    //public DcMotor shooterMotor1;
    //public DcMotor shooterMotor2;
    //public Servo turretServo;

    public hardwareMap(HardwareMap hardwaremap) {

        frontRightMotor = hardwaremap.get(DcMotor.class, "FRM");
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor = hardwaremap.get(DcMotor.class, "FLM");
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor = hardwaremap.get(DcMotor.class, "BRM");
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor = hardwaremap.get(DcMotor.class, "BLM");
        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        intakeMotor = hardwaremap.get(DcMotor.class, "Intake");

        //shooterMotor1 = hardwaremap.get(DcMotor.class, "Shooter1");
        //shooterMotor2 = hardwaremap.get(DcMotor.class, "Shooter2");
        //turretMotor = hardwaremap.get(DcMotor.class, "TurretMotor");
        //turretServo = hardwaremap.get(Servo.class, "TurretServo");
    }
}
