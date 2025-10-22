package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MecanumDrive {
    private DcMotor frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
    private IMU imu;


    public void init(HardwareMap hwMap) {
        frontLeftMotor = hwMap.get(DcMotor.class, "front_left_motor");
        frontRightMotor = hwMap.get(DcMotor.class, "front_right_motor");
        backLeftMotor = hwMap.get(DcMotor.class, "back_left_motor");
        backRightMotor = hwMap.get(DcMotor.class, "back_right_motor");

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);


        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Default is logo facing up and USB ports facing forward on Robot Controller
        imu = hwMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        );

        imu.initialize(new IMU.Parameters(RevOrientation));

    }

    public void drive(double forward, double strafe, double rotate) {

        // To learn about mecanum drive, go to this video https://www.youtube.com/watch?v=0k-Ey9bS9lE
        double frontLeftPower = forward + strafe + rotate;
        double backLeftPower = forward - strafe + rotate;
        double frontRightPower = forward - strafe - rotate;
        double backRightPower = forward + strafe - rotate;

        double maxPower = Collections.max(Arrays.asList(Constants.MAX_POWER, frontLeftPower, backLeftPower, frontRightPower, backRightPower));

        frontLeftMotor.setPower(Constants.MAX_SPEED * (frontLeftPower / maxPower));
        frontRightMotor.setPower(Constants.MAX_SPEED * (backLeftPower / maxPower));
        backLeftMotor.setPower(Constants.MAX_SPEED * (frontRightPower / maxPower));
        backRightMotor.setPower(Constants.MAX_SPEED * (backRightPower / maxPower));


    }

    public void driveFieldRelative(double forward, double strafe, double rotate){
        // Converting from cartesian coordinates (x and y axis) to polar coordinates (A distance and angle, like in radar)
        double theta = Math.atan2(forward, strafe); // The angle of the direction we want to move in
        double radius = Math.hypot(strafe, forward); // How far we want to move

        theta = AngleUnit.normalizeRadians(theta -
                imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS)); // Adjusting for the angle that the robot is already facing

        double newForward = radius * Math.sin(theta);
        double newStrafe = radius * Math.cos(theta);

        this.drive(newForward, newStrafe, rotate);




    }
}
