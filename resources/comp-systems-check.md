### Before competition (wifi required)

`./gradlew downloadAll`

This will download all dependencies that are needed to deploy code without an internet connection.

Locate these items and put them in the computer bag:

- Locate multiple long ethernet cables, spare USB splitters, and computer chargers.
- Download the latest version of the code (as a Github release?), zip it, and save it to a USB drive.

### Competition begins, robot is barely unbagged

1. Cut the radio off the robot, grab any other radios, flash ALL OF THEM at the official kiosk (which probably won't be open tbh).
2. Put a long ethernet cable underneath the cart.
3. Open VSCode and run `./gradlew build` to initialize a "Gradle daemon" process.
4. Open Shuffleboard and leave it in the background.
5. Open Driver Station and leave it in the background.

Do these tasks as they become available (robot starts to be put together):

- Tell people what wires go in what ports, if they ask. Use the printed wiring guide on top of the red toolbox.
- Check for loose wires, especially:
    - The main breaker (use a ratcheting socket wrench)
    - Green/yellow CAN wires
    - RoboRIO DIO ports
- Verify that the correct breakers are in all PDP ports.
- Zip-tie the radio to the side panel and plug it in.

### Robot is wired and ready for power

1. Get a battery into the robot, but do not plug it in.
2. Glance over the robot for any backwards/loose wires.
3. Press OFF on the breaker, then plug in the battery.
4. Turn on the robot. If there is any popping or sparking, turn it off IMMEDIATELY.
5. Is the orange RSL light lit up with solid orange?
6. Are the talons, PDP, and PCM blinking red? If so, plug in the computer and fix IDs using Phoenix Tuner. Use the wiring guide in the top of the toolbox. Talons will blink yellow when you successfully finish.
7. Is the radio blinking with blue and/or orange lights? If not, check the power cable.
8. Are the hall effect sensors lit up with a green light? Wave a magnet near both hall effect sensors and verify that a yellow light turns on.
9. Wave something white under the line followers and verify that they light up red.
10. Plug the square USB/printer cable into the RoboRIO and update it to v16.
11. Stay plugged in; open Phoenix Tuner and update all the Talons to v4.17.

### Testing: stage 2

If any encoders are backwards, change the variable in QuickAccessVars.

1. Open VSCode and deploy code to the robot.
2. Open Shuffleboard and look in the left-most panel.
3. Check the following encoders and verify that they are configured in the right direction:
	- left drivetrain encoder
	- right drivetrain encoder
	- NavX angle measurement
	- elevator encoder (up should be positive)
	- arm encoder (out should be positive)
	- climb encoder (down should be negative)

### Testing: time to enable! 

If any motors are backwards, change the variable in QuickAccessVars.

1. Open QuickAccessVars and look for any COMPTEST comments: follow the instructions. The number on the end of the line is the original, if you forget.
2. Deploy code once again.
3. Open Driver Station and put the joysticks in order. Right joystick is 0, left is 1, xbox is 2.
4. Click ENABLE and be ready to DISABLE the robot (press Enter) at any moment. Does the RSL start blinking orange?
5. Does the compressor turn on? Press 8 on the left joystick base; if that doesn't work, check pneumatics and wiring.
6. Move the robot onto a platform (plastic tote) where the wheels don't touch the ground.
7. Move the joysticks slowly and check that the wheels move in the right direction.
8. Test the elevator (press the left Xbox joystick inwards).
9. Test the arm (press the right Xbox joystick inwards). Unplug the clone motor first, then switch; only plug in both once they are both going in the same direction.
10. Test the scissors and launcher mechanisms (left and right on the D-pad, and 4 on the left joystick).
11. Test the wheels on the pickup arm (left trigger for in, left bumper for out).
12. Put the robot on the floor and test the hatch pickup in (right Xbox joystick up/down, right trigger).
13. Test the PID buttons (A, B, X, Y, left joystick trigger, right joystick button 3, right joystick button 5, xbox SELECT).
14. Hang the robot off a ledge and test the climb (press X, press 5 on the right joystick, then press 6. Press left joystick 5 to cancel).
