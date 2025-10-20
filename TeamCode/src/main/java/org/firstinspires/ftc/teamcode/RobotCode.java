package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class RobotCode extends OpMode {

    // Left Joystick
    double left_stick_x;   // How much along the x-axis the left joystick is moved [-1, 1]
    double left_stick_y;   // How much along the y-axis the left joystick if moved [-1, 1]
    int left_stick_button; // If the left joystick is pushed down. 0 for not pushed and 1 for pushed


    // Right Joystick
    double right_stick_x; // How much along the x-axis the right joystick is moved [-1, 1]
    double right_stick_y; // How much along the y-axis the right joystick if moved [-1, 1]
    int right_stick_button; // If the right joystick is pushed down. 0 for not pushed and 1 for pushed

    // D-pad. All booleans (True or False), but the math is easier with integers. 0 is false, 1 is true.
    int dpad_down; // If down dpad up is pressed
    int dpad_left; // If left dpad up is pressed
    int dpad_right; // If right dpad up is pressed
    int dpad_up; // If up dpad up is pressed

    // Bumpers. All booleans (True or False), but the math is easier with integers. 0 is false, 1 is true.
    int left_bumper; // If the left bumper is pressed
    int right_bumper; // If the right bumper is pressed

    // Triggers. Float of [0, 1] for how far down it is pressed.
    float left_trigger;
    float right_trigger;

    // letters. All booleans (True or False), but the math is easier with integers. 0 is false, 1 is true.
    int y;
    int b;
    int x;
    int a;

    // Accessory buttons. All booleans (True or False), but the math is easier with integers. 0 is false, 1 is true.
    int start_button_pressed;
    int start_button_released;
    int back_button_button_pressed;
    int back_button_button_released;

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
        telemetry.addData("Message", "Hello World");
        telemetry.update();
    }

    // Ran about 50 times per second during TeleOp
    @Override
    public void loop() {
        // Get all of the gamepad values every time. We technically do not need to do this, but it makes it easier to code without having to worry about datatypes.
        left_stick_x = gamepad1.left_stick_x;
        left_stick_y = gamepad1.left_stick_y;
        left_stick_button = boolean_to_integer(gamepad1.left_stick_button);
        right_stick_x = gamepad1.right_stick_x;
        right_stick_y = gamepad1.right_stick_y;
        right_stick_button = boolean_to_integer(gamepad1.right_stick_button);
        dpad_down = boolean_to_integer(gamepad1.dpad_down);
        dpad_left = boolean_to_integer(gamepad1.dpad_left);
        dpad_right = boolean_to_integer(gamepad1.dpad_right);
        dpad_up = boolean_to_integer(gamepad1.dpad_up);
        left_bumper = boolean_to_integer(gamepad1.left_bumper);
        right_bumper = boolean_to_integer(gamepad1.right_bumper);
        left_trigger = gamepad1.left_trigger;
        right_trigger = gamepad1.right_trigger;
        y = boolean_to_integer(gamepad1.y);
        b = boolean_to_integer(gamepad1.b);
        x = boolean_to_integer(gamepad1.x);
        a = boolean_to_integer(gamepad1.a);
        start_button_pressed = boolean_to_integer(gamepad1.startWasPressed());
        start_button_released = boolean_to_integer(gamepad1.startWasPressed());
        back_button_button_pressed = boolean_to_integer(gamepad1.backWasPressed());
        back_button_button_released = boolean_to_integer(gamepad1.backWasReleased());



    }
}