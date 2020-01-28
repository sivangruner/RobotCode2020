/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final double HOPPER_SPEED = 0;
    public static final double HOPPER_LOAD_BALLS_SPEED = 0;
    public static final double HOPPER_FEED_SPEED = 0;
    public static final double HOPPER_CURRENT_DELTA = 0;

    public static final int TICKS_PER_REVOLTION = 4096;
    public static final double WHEEL_DIAMITER = 0.2032;
    public static final double METER_PER_TICK = .001558524480;
    public static final double TICKS_PER_100MS_TO_PRM = 60*10/TICKS_PER_REVOLTION;


    // field constants
    public static final double ROBOT_HEIGHT = 0;
    public static final double POWER_PORT_HEIGHT = 0;

    // physics constants
    public static final double G = 9.807;

}


