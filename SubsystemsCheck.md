Systems
- check polarity
- check ALL talon ids
- check solenoid ids
- check dio ids
- check pcm ids!!!!!!!
- check reversed motors

Cargo pickup (arm and intake)
	- do we have constants for PID for the elevator
	- inconsistancy between degrees and ticks in rotatePickupDegrees and getPickupPosition
	- XXX comment that follows said inconsistancy

climb (--)
	- incomplete

drivetrain (--)
- check if encoders are reversed
- systems: check if motors are reversed

elevator (elevator)
+ limit switch port? what is this? the hall sensor?
	- do we have constants for PID for the elevator?
	- Elevator lacks definite units on moveElevatorTo 
	- Elevator lacks definite units on getElevatorPosition
	- (two above should be in the same units)
	- getElevatorPosition is an int?

hatch (pickup and intake)
- inversions on motors? is this important now?
+ a blank todo above neutralize pneumatics

hatch (scissor and launcher)
- subsystem good

linefollower (3x ir sensor)
	- subsystem good

pneumatic base (compressor)
+ is enable compressor run?
