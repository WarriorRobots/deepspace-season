### Before competition (wifi required)

`./gradlew downloadAll`

This will download all dependencies that are needed to deploy code without an internet connection.

Locate these items and put them in the computer bag:

- Locate multiple long ethernet cables, spare USB splitters, and computer chargers.
- Download the latest version of the code (as a Github release?), zip it, and save it to a USB drive.

### Competition begins, robot is barely unbagged

1. Cut the radio off the robot, grab any other radios, flash ALL OF THEM at the official kiosk (which probably won't be open tbh).
2. Put a long ethernet cable underneath the cart.
3. Run `./gradlew build` to initialize a "Gradle daemon" process.

Do these tasks as they become available (robot starts to be put together):

- Tell people what wires go in what ports, if they ask.
- Check for loose wires, especially:
    - The main breaker (use a ratcheting socket wrench)
    - Green/yellow CAN wires
    - RoboRIO DIO ports
- Verify that the correct breakers are in all PDP ports.
- 


- Connect driver station to computer
- DO NOT power on robot yet

-Check polarity of wires and devices (pdp, can, solenoids, dio)
-Check RSL

- Do power on robot
- Run `./gradle deploy`
- Open shuffleboard (consumes time)
- Open driver station (consumes time)

- Check pcms
- Check talon ids in phoenix tuners
- Check linefollowers
- Check drive encoders (left and right)
- Check arm encoders
- Check elevator encoders
- Check elevator hall sensor
- Check arm hall sesnor

- Check compressor and if not compressing, fill robot with air manually
- Check solenoid ids
- Change ids in code (& excell if time)
- Run `./gradle deploy` if changes made

- Test drive train SLOW (gears mesh, break order)
- Check for inversions in driving and for encoders
- Intake wheels on hatch
- Intake whells on ball

- Keep a person ready on enter key (DO NOT USE space)
- Put the arm up
- Use the reset arm encoders mechanism
- Put out the ball pickup
- Put in the ball pickup

- Keep a person ready on enter key (DO NOT USE space)
- Try all elevator buttons
- Manually try and put the elevator up and down
- Let the elevator go home

- Solenoid hatch pickup down (should touch the floor)
- Solenoid hatch pickup up
- Solenoid scissors tense
- Solenoid scissors release
- Solenoid launcher out
- Solenoid launcher in

- Sweep dashboard (check all variables to see if they are right)