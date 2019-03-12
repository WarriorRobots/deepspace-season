## Heights
All are in inches to the floor

Upper and lower bounds of elevator's range of motion.
- `ELEVATOR_MINIMUM_TARGET = 0.5;`
- `ELEVATOR_MAXIMUM_TARGET = 75;`

Amount to adjust elevator by when using +2
- `ADJUST_ELEVATOR_BY = 4;`

Height of elevator when placing cargo in the bay and when trying to climb to hab platform 3.
- `CARGO_SCORING_HEIGHT = 29;`

Heights for scoring on the rocket.
- `LVL1_HEIGHT = 21;`
- `LVL2_HEIGHT = 47;`
- `LVL3_HEIGHT = 74;`

Height of scissors off of the floor.
- `SCISSORS_HEIGHT = 13;`

Top height of the zone where the elevator doesn't rush down to not crash it at the bottom
- `ELEVATOR_DOWNWARD_DRIFT_THRESHOLD = 10;`

Upper and lower bounds of the elevator's range of motion.
- `CLIMB_MINIMUM_TARGET = -22;`
- `CLIMB_MAXIMUM_TARGET = 0;`

Amount to adjust climb by when using +1/2
- `ADJUST_CLIMB_BY = 0.5;`

## Motor Speeds
All motor speeds are from -1 to 1.

Amounts to scale the motor speeds down for arcade mode.
- `ARCADE_FORWARD_MODIFIER = 0.5;`
- `ARCADE_TURN_MODIFIER = 0.75;`

Motor speed the elevator goes down at.
- `ELEVATOR_DROP_SPEED = -1;`

Speed the cargo pickup should idle run to hold the ball in it.
- `CARGO_PICKUP_IDLE_SPEED = 0.1;`

Speed the elevator goes down when the drift range to not rush down and crash it at the bottom.
- `ELEVATOR_DOWNWARD_DRIFT_SPEED = -0.05;`

Speed the pickup arm goes up at when reseting the encoder.
- `ARM_RESET_SPEED = -0.2;`

## Degrees

Upper and lower bounds of arm's range of motion.
- `ARM_MINIMUM_ANGLE = 0;`
- `ARM_MAXIMUM_ANGLE = 160;`

Angle of the arm when picking up cargo from the floor.
- `ARM_PICKUP_CARGO_ANGLE = 90;`

Angle of the arm when climbing on either platform.
- `ARM_CLIMB_ANGLE = 145;`

What angle the magnet sets the arm to be when the encoder is reset.
- `ARM_RESET_ANGLE = -3;`

## Delay timers
All delays are measured in seconds

The time it takes after the launcher goes out and before the scissors go loose.
- `PLACE_HATCH_DELAY = 0.2;`

The time between the hatch pickup being extended and the wheels on the pickup running.
- `HATCH_PICKUP_DELAY = 0.3;`

The time it takes for the drive moters to swap directions.
- `DRIVETRAIN_RAMPRATE = 0.25;`

## Settings

The inversions on specific moters and subsystems.
- `CLIMB_WINCH_INVERTED = true;`
- `CLIMB_ENCODER_INVERTED = true;`
- `ARM_ROTATOR_INVERTED = true;`
- `ARM_ROTATOR_CLONE_INVERTED = false;`
- `ARM_ENCODER_INVERTED = false;`
- `CARGO_PICKUP_WHEELS_INVERTED = false;`
- `HATCH_PICKUP_WHEELS_INVERTED = true;`
- `ELEVATOR_WINCH_INVERTED = true;`
- `ELEVATOR_ENCODER_INVERTED = false;`

If true, the line follower seeing a line is required in order to launch a hatch
- `HATCH_LAUNCH_SAFETY = false;`

The size of the range the two joysticks must be held to drive straight when using turn lock.
- `TURNLOCK_THRESHOLD = 0.2;`

The number of loops the pneumatics will run (this is because pneumatics must be told to fire until they have fully fired).
- `PNEUMATIC_LOOP_COUNT = 5;`

How far the joystick must be pushed before they fire their command.
- `XBOX_JOYSTICK_THRESHOLD = 0.7;`

The multiplier applied to all linear motion (such as linear motion on the elevator, arm, and climb).
- `LINEAR_CONTROLS_MODIFIER = 0.5;`

## Camera

The tilt of the camera in radians (the number types should be in degrees).
- `CAMERA_TILT = 22 * Math.PI / 180;`

Height of the camera lens off of the ground in inches.
- `ELEVATION = 40;`

How far the driver push the joystick to activate arcade drive during ApproachCurve
- `CAMERA_DRIVE_THRESHOLD = 0.2;`

## PIDs

- `ELEVATOR_P = 0.4;`
- `ARM_P = 1.2;`
- `CLIMB_P = 0.4;`

## Camera PIDs

PID values for driving towards the target.
- `KP_APPROACH = 0.015;`
- `KI_APPROACH = 0;`
- `KD_APPROACH = 0;`

The distance away form the target the camera tries to approach.
- `SETPOINT_APPROACH = 23;`

The tolerance the camera will allow for being within the acceptable range away from the target.
- `TOLERANCE_APPROACH = 2;`

PID values for center the target within the camera's vision.
- `KP_CENTER = 0.045;`
- `KI_CENTER = 0;`
- `KD_CENTER = 0;`

The value for where to center the target in the camera's vision.
- `SETPOINT_CENTER = 0;`