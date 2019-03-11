package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Constants;
import frc.robot.QuickAccessVars;
import frc.robot.commands.ChangePipeline;

/**
 * CameraSubsystem is supposed to recive data from the limelight to be output or processed.
 */
public class CameraSubsystem extends Subsystem {

    /** Limelight network table keyword */
    private static final String LIMELIGHT = "limelight";
    /** Whether the limelight has any valid targets (0 or 1) */
    private static final String TARGET_EXISTS = "tv";
    /** Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees) */
    private static final String TARGET_X = "tx";
    /** Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees) */
    private static final String TARGET_Y = "ty";
    /** Target Area (0% of image to 100% of image) */
    private static final String TARGET_AREA = "ta";
    /** Skew or rotation (-90 degrees to 0 degrees) */
	private static final String TARGET_SKEW = "ts";
	/** Horizontal sidelength of the rough bounding box (0 - 320 pixels) */
	private static final String TARGET_WIDTH = "thor";
	/** Vertical sidelength of the rough bounding box (0 - 320 pixels) */
	private static final String TARGET_HEIGHT = "tvert";
    
    /** Vision table for Limelight */
	private NetworkTable visionTable;
	
	/** Pipeline id for the crosshair in the center. */
	public static final int PIPELINE_CENTER = 0;
	/** Pipeline id for Driver exposure and use */
	public static final int PIPELINE_DRIVER = 1;
	/** Pipeline id for a single target on the left. */
	public static final int PIPELINE_TARGETLEFT = 3;
	/** Pipeline id for a single target on the right. */
	public static final int PIPELINE_TARGETRIGHT = 4;


    public CameraSubsystem() {
        visionTable = NetworkTableInstance.getDefault().getTable(LIMELIGHT);
    }


    /**
     * Checks for an object in the Limelight's frame.
     * @return {@code true} if object is present, else {@code false}
     */
    public boolean canSeeObject() {
		return (visionTable.getEntry(TARGET_EXISTS).getDouble(0) == 1)
				? true : false;
	}
	
	/**
	 * Gets x-coordinate of current object on screen.
	 * @return X position of object in pixels
	 */
	public double getObjectX() {
		return visionTable.getEntry(TARGET_X).getDouble(0);
	}
	
	/**
	 * Gets y-coordinate of current object on screen.
	 * @return Y position of object in pixels
	 */
	public double getObjectY() {
		return visionTable.getEntry(TARGET_Y).getDouble(0);
	}
	
	/**
	 * Gets the percentage area of the currently-seen object relative to the image size. 
	 * @return Decimal representing percentage of image taken up by object, 0 to 1.
	 */
	public double getObjectArea() {
		return visionTable.getEntry(TARGET_AREA).getDouble(0);
	}
	
	/**
	 * Gets the rotation angle of the currently-seen object.
	 * @return -90 degrees to 90 degrees
	 */
	public double getObjectRotationAngle() {
		return visionTable.getEntry(TARGET_SKEW).getDouble(0);
	}

	/**
	 * Gets aspect ratio of the veiwed target in Width/Height
	 * (This aspect ratio can be used to see when the robot is aligned facing the target)
	 * @return Aspect ratio as a double (max is 2.5 unitless)
	 */
	public double getObjectAspectRatio() {
		/*
		Solution for max aspect ratio
		Width 	= 2 * (arbitrary top + arbitrary top for side) + 8in

		arbitrary top for side = 1.35in (in field sketch)

		(2in)^2			= arbitary top ^2 + (0.5in)^2		// 0.5 is from field sketch
		arbitary top ^2	= 4 in^2 - 0.25 in^2
		arbitary top	= sqrt (3.75)in

		Width	= 2 * (sqrt(3.75)in + 1.35in) + 8in
		Width	~ 14.57in


		Height	= 0.5in + arbitrary side

		(5.5in)^2			= arbitrary side ^2 + (1.35in)^2	// 1.35 is from field sketch
		arbitrary side ^2	= 30.25 in^2 - 1.8225 in^2
		arbitrary side		= sqrt (28.4275)in

		Height	= 0.5in + sqrt(28.4275)in
		Height	~ 5.83in

		Width/Height ~ 14.57in/5.83in ~ 2.5 unitless

		*/
		double width = visionTable.getEntry(TARGET_WIDTH).getDouble(0);
		double height = visionTable.getEntry(TARGET_HEIGHT).getDouble(0);
		//System.out.printf("Width: %f\t\tHeight: %f\n",width,height); // TEMP
		return (canSeeObject()==true)
			//   visible	: not visible
			? width/height 	: -1;
	}

	/** <put comment here>
	 * (This ratio can be used to see if the robot is lined up facing the target)
	 * @return Aspect ratio of Width of target in pixels to Range in inches.
	 */
	public double getObjectWidthRange() {

		double width = visionTable.getEntry(TARGET_WIDTH).getDouble(0);
		double range = getTargetDistance();

		return (canSeeObject())
			? width/range	: -1;
	}


	/**
	 * (Adjacent because the range is the adjacent side.)
	 * Uses the conversion from pixels to radians and uses radians to figures out the trigonometry
	 * between the height of the target and the range.
	 * (Target may not be visible at very close range, use a sonar.)
	 * @return Distance from target in inches.
	 */
	@Deprecated
	public double getTargetDistanceAdjacent() {

		if (!canSeeObject()) return -1;

		// height in pixels
		double height = visionTable.getEntry(TARGET_HEIGHT).getDouble(0);

		// angle in radians
		double angle = height / Constants.PPR_V;

		// range = adj = opp/tan(Theta)
		double range = QuickAccessVars.TARGET_HEIGHT / Math.tan(angle);

		return range;
	}
	
	/**
	 * Uses the conversion from pixels to radians and uses radians to figures out the trigonometry
	 * between the height of the target and the range.
	 * (Target may not be visible at very close range, use a sonar.)
	 * @return Distance from target in inches.
	 */
	public double getTargetDistance() {

		if (!canSeeObject()) return -1;

		// offset angle upwards in radians
		double target_offset = visionTable.getEntry(TARGET_Y).getDouble(0) * Math.PI/180;

		// Height difference between the camera and the target center
		double height_difference = QuickAccessVars.CAMERA_ELEVATION - QuickAccessVars.TARGET_ELEVATION - QuickAccessVars.TARGET_HEIGHT/2;

		// Angle from the elevator to the center of the target
		double angle = Math.PI/2 - QuickAccessVars.CAMERA_TILT + target_offset;

		//System.out.println("Angle:");
		//System.out.println(angle);

		// if the math is acting weird, return a working value
		if (height_difference == 0 || angle == 0) return getTargetDistanceAdjacent();

		// range = opp = adj*tan(Theta)
		double range = height_difference * Math.tan(angle);

		return range;

	}

	/**
	 * Gives the height of the target seen by the camera
	 * @return Height of object in pixels
	 */
	public double getTargetHeight() {
		return visionTable.getEntry(TARGET_HEIGHT).getDouble(0);
	}

	/** Changes the pipeline the Limelight is using to a new specified pipeline.
	 * @param pipeline The pipeline the Limelight to change to (0 to 9).
	 */
	public void setPipeline(int pipeline) {
		
		// if pipeline is NOT between 0 and 9, warn the dashboard and quit the command.
		if(!(0<=pipeline && pipeline<=9)) {
			DriverStation.reportWarning(
				Integer.toString(pipeline)+" is not a valid pipeline to change to.", true);
			// leave function to prevent a real error from the limelight
			return;
		}
		visionTable.getEntry("pipeline").setDouble(pipeline);

	}

	/** Grabs the pipeline the Limelight is using currently */
	public int getPipeline() {

		return (int) visionTable.getEntry("getpipe").getDouble(-1.0);

	}



    @Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("subsystem-camera");
		builder.addBooleanProperty("object-exists", () -> canSeeObject(), null);
		builder.addDoubleArrayProperty("object-x&y", () -> {
		
			return new double[] {getObjectX(),getObjectY()};

		}, null);
		builder.addDoubleProperty("object-x", () -> getObjectX(), null);
		builder.addDoubleProperty("object-y", () -> getObjectX(), null);
        builder.addDoubleProperty("object-area", () -> getObjectArea(), null);
		builder.addDoubleProperty("object-skew", () -> getObjectRotationAngle(), null);
		
		builder.addDoubleProperty("object-ratio", () -> getObjectAspectRatio(), null);
		builder.addDoubleProperty("object-distance", () -> getTargetDistance(), null);
		builder.addDoubleProperty("object-widthrange", () -> getObjectWidthRange(), null);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ChangePipeline(PIPELINE_DRIVER));
	}

}
