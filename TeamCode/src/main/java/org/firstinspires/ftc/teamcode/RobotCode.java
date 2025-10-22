package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;

@TeleOp
public class RobotCode extends OpMode {

    MecanumDrive drive = new MecanumDrive();
    double forward, strafe, rotate;

    int boolean_to_integer(boolean value){
        /*
        The following is a ternary expression.
        Ternary expression is a way to simplify this type of if statement

        > if (argument==true){
        >     return a;
        > } else{
        >     return b;
        > }

        into

        > argument ? 1 : 0;

         So, the following code return 1 if the value is true and 0 if it is false.
         */
        return value ? 1 : 0;
    }

    // Ran once when robot is turned on and TeleOp is enabled
    @Override
    public void init() {
        drive.init(hardwareMap);
        telemetry.addData("Message", "Hello World");
        telemetry.update();

    }

    // Ran about 50 times per second during TeleOp
    @Override
    public void loop() {
        forward = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        drive.driveFieldRelative(forward, strafe, rotate);



    }
}