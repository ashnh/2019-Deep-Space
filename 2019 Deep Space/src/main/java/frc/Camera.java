/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class Camera {

    //

    public static camState state = camState.driver;

    public static boolean isDriver = true;

    public static int DRIVER_VISION = 0;
    public static int CAMERA_VISION = 1;

    public static final double robotHeight = 10D; // hmm and a half
    public static final double visionTargetHeight = 28.5D; // lower vision target
    public static final double cameraAngle = 58D;
    //static double maxAcceleration = 0.04;
    public static final double maxSpeed = .3;
    public static final double maxSpeedDistance = 100; 

    public static final double AREA_CONSTANT = Math.pow(Math.tan(54/2), 2);
    public static final double RADIAL_CONSTANT = 2.10412;

    //Calibration
    public static double focalLength = 0d;
    public static final double testDistance = 10.5d;

    public static final double targetHeight = 5.5d;
    public static final double targetWidth = 2d;

    public static final double heightWidthRatio = targetHeight / targetWidth;


    /***
     *  states for the camera image
     */
    public static enum camState {
        driver, image;
    
        public camState flip () {
            return (this == image) ? driver : image;
        }

    };
    /***
     *  states for the state of the camera's led
     */
    public static enum ledState {
        on, off, blink, pipeline;

    };
    

    /***
     * returns the Camera network table
     * @return Camera NetworkTable
     */
    public static NetworkTable getTable () {

        return NetworkTableInstance.getDefault().getTable("limelight-chaos");
        
    }

    /***
     * get an entry from the camera network table, access with getDouble()
     * @param name the name of the limelight variable
     * @return the NetworkTableEntry value from the table
     */
    public static NetworkTableEntry getEntry (String name) {

        return getTable().getEntry(name);

    }

    /**
     * 0 = driver, 1 = camera processing
     * @param index - index
     */
    public static void changePipeline (int index) {
        getEntry("pipeline").setNumber(index);
    }

    public static double getRectHeightFromArea () {

        return Math.sqrt(getArea() * heightWidthRatio);
    } 
    
    public static void calibrateF () {
        focalLength = getRectHeightFromArea() * testDistance / targetHeight;
    }

    public static double getVerticalAngle () {
        return getEntry("ty").getDouble(0);
    }

    public static double getArea () {
        return getEntry("ta").getDouble(0);
    }

    public static double getDistanceFromArea() {

        return targetHeight * focalLength / getRectHeightFromArea();

    }

    /***
     * Gets the distance from the camera to the vision target using their heights, the angle of the camera, 
     * and the read ty vartical-angle-from-center-to-target value from the limelight
     * @return the horizontal distance from camera to vision target
     */
    public static double getDistance () {

        return (visionTargetHeight - robotHeight) / (Math.tan( Math.toRadians(getEntry("ty").getDouble(69) + cameraAngle)));

    }

    /***
     * tx: horizontal angle from the center of the camera to the vision target
     * @return angle in degrees
     */
    public static double GetHorizontalAngle () {

        return getEntry("tx").getDouble(0);

    }

    // public static double getDistanceFromArea() {

    //     return RADIAL_CONSTANT / (Math.sqrt(getEntry("ta").getDouble(0d) * AREA_CONSTANT));

    // }

    /***
     * get the drive values for following the vision target
     * @param currentSpeedLeft current left side set speed
     * @param currentSpeedRight current right side set speed
     * @return two-value area for leftSpeed and rightSpeed
     */
    public static double[] getDriveDirections(double currentSpeedLeft, double currentSpeedRight) {


        double[] driveValues = {0D, 0D};
       // double maxNewSpeedLeft = currentSpeedLeft + maxAcceleration;
        //double maxNewSpeedRight = currentSpeedRight + maxAcceleration;

        driveValues[0] = (getDistance() / maxSpeedDistance) * maxSpeed;
        driveValues[1] = (getDistance() / maxSpeedDistance) * maxSpeed;

        driveValues[0] = FunctionsThatShouldBeInTheJDK.clamp(driveValues[0], -maxSpeed, maxSpeed);
        driveValues[1] = FunctionsThatShouldBeInTheJDK.clamp(driveValues[1], -maxSpeed, maxSpeed);

        driveValues[0] += (GetHorizontalAngle() / 27D) * maxSpeed;
        driveValues[1] -= (GetHorizontalAngle() / 27D) * maxSpeed;

        driveValues[0] = FunctionsThatShouldBeInTheJDK.clamp(driveValues[0], -maxSpeed, maxSpeed);
        driveValues[1] = FunctionsThatShouldBeInTheJDK.clamp(driveValues[1], -maxSpeed, maxSpeed);

       // driveValues[0] = FunctionsThatShouldBeInTheJDK.clamp(driveValues[0], -maxSpeed, maxSpeed);
        //driveValues[1] = FunctionsThatShouldBeInTheJDK.clamp(driveValues[1], -maxSpeed, maxSpeed);

        //driveValues[0] = (driveValues[0] > maxNewSpeedLeft) ? maxNewSpeedLeft : driveValues[0]; 
        //driveValues[1] = (driveValues[1] > maxNewSpeedRight) ? maxNewSpeedRight : driveValues[1];

        return driveValues;

    }

    

    //public static 
    
    /***
     *  change the state of the camera to vision or image processing
     * @param set driver or image
     */
    public static void changeCamMode(camState set) {

        if (set.equals(camState.image)) {
            getEntry("camMode").setNumber(0);
            isDriver = false;
        } else  {
            getEntry("camMode").setNumber(1); 
            isDriver = true;
        } 

        state = set;
    
    }

    public static void toggleCamState () {
        
        //System.out.println(getEntry("camMode").getDouble(0) == 1);
       // if (getEntry("camMode").getDouble(0) == 1) {
         //   getEntry("camMode").setDouble(1);
        //} else {
         //   getEntry("camMode").setDouble(1);
        //}
        getEntry("camMode").setDouble(1);
        //System.out.println(getEntry("camState").getDouble(0));
    }

    public static camState getCameraState () {

        return state;

    }

    public static boolean isDriver() {
        return isDriver;
    }

    public static boolean cameraStateIsDriver () {
        return((double) getEntry("camMode").getNumber(0)) == 1.0;
    }

    /**
     * sets the state for the camera leds
     * @param set on, off, blink, pipeline
     */
    public static void lightsOn(ledState set) {
       

        if (set.equals(ledState.on)) {
            getEntry("ledMode").setNumber(3);
        } else if (set.equals(ledState.blink)) {
            getEntry("ledMode").setNumber(2); 
        } else if (set.equals(ledState.off)){
            getEntry("ledMode").setNumber(1);
        } else {
            getEntry("ledMode").setNumber(0);
        }
    }
}
