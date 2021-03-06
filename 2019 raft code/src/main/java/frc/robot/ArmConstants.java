/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class ArmConstants {

    public static final double ACCEPTABLE_ANGLE = 4;

    public static final double ELBOW_RADIUS = 4.0;
    public static final double ENCODER_TICKS_PER_REVOLUTION = 4100D;
    public static final double MIN_ELBOW_VOLTAGE = 0.384;
    public static final double MAX_ELBOW_VOLTAGE = 0.684;
    public static final double MIN_ELBOW_ANGLE = -180.0;
    public static final double MAX_ELBOW_ANGLE = 90;
    
    public static final double MIN_EXTENDER_VOLTAGE = 0.0795;
    public static final double MAX_EXTENDER_VOLTAGE = .505;
    public static final double MIN_EXTENDER_LENGTH = 0.0;
    public static final double MAX_EXTENDER_LENGTH = 14.0;
    public static final double EXTENDER_SPEED = 0.3;

    public static final double ARM_LENGTH = 24.5;
    public static final double ARM_WEIGHT = 1.0;
    public static final double ARM_CENTER_OF_MASS = 8.604;
    
    public static final double EXTENDER_ARM_WEIGHT = 1.0;
    public static final double EXTENDER_ARM_CENTER_OF_MASS = 23.5/2;
    
    public static final double GRABBER_WEIGHT = 15.0;
    public static final double GRABBER_CENTER_OF_MASS = 21.3;
    
    public static final double DISTANCE_FROM_AXIS_ROTATION_TO_RETRACTED_EXTENDER = 20.0;
    
    public static final double EXTENSION_TOTAL_CENTER_OF_MASS = 
    (((GRABBER_CENTER_OF_MASS*GRABBER_WEIGHT) + (EXTENDER_ARM_CENTER_OF_MASS*EXTENDER_ARM_WEIGHT) / 
    (EXTENDER_ARM_WEIGHT + GRABBER_WEIGHT)) + DISTANCE_FROM_AXIS_ROTATION_TO_RETRACTED_EXTENDER);

    public static final double TOTAL_WEIGHT = ARM_WEIGHT + EXTENDER_ARM_WEIGHT + GRABBER_WEIGHT;
    public static final double ARM_HEIGHT_INCHES = 39.5;
    public static final double ARM_DISTANCE_X_FROM_FRAME = 5;
    public static final double MAX_REACH_X_BEYOND_FRAME = 30;
    public static final double GRABBER_LENGTH = 12;
    public static final double MAX_REACH_X = ARM_DISTANCE_X_FROM_FRAME + MAX_REACH_X_BEYOND_FRAME - GRABBER_LENGTH;  

    public static final double GEAR_RATIO = 266.78 ;
    public static final double NUMBER_OF_MOTORS = 2.0 ;
    public static final double MOTOR_STALL_TORQUE = 0.43;

    public static final double MAX_ELBOW_ACCELERATION = 0.0131*7;

    public static final double ELBOW_P = 0.028;
	public static final double ELBOW_I = 0;
    public static final double ELBOW_D = 0;

    public static final double EXTENDER_P = 0.15;
    public static final double EXTENDER_I = 0;
    public static final double EXTENDER_D = 0;

    
    public static final double BALL_PICKUP_ANGLE = -90;

    public static final double BALL_LOW = 27.5;
    public static final double BALL_MID = 55.5;
    public static final double BALL_HIGH = 83.5;
    public static final double HATCH_LOW = 19;
    public static final double HATCH_MID = 47;
    public static final double HATCH_HIGH = 75;
    public static final double CARGO_BALL = 37;

    public static final double ELBOW_CLEARANCE_POSITION = -80;
    public static final double ELBOW_MIN_SAFE_ANGLE = ELBOW_CLEARANCE_POSITION - 5;
    public static final double WIRST_SAFE_ANGLE = 15;
    public static final double EXTENDO_SAFE_ANGLE = 10;

}
