/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class RobotMap {

    public class DriverPorts{
        public static final int LEFT_LEADER = 0;
        public static final int RIGHT_LEADER = 0;
        public static final int LEFT_FOLLOWER = 0;
        public static final int RIGHT_FOLLOWER = 0;
    }

    public class IntakePorts{
        public static final int ROLLER = 0;
        public static final int JOINT = 0;

    }

    public class HopperPorts{
        public static final int TALON_BELTS_PORT = 0;
        public static final int TALON_FEED_PORT = 0;
    }
    //PORTS FOR THE SHOOTER SUBSYSTEM
    public class ShooterPorts{
        public static final int TALON_PORT = 0;
        public static final int VICTOR_PORT = 0;


    }
    
    
    public class ClimbPorts{
        public static final int TALON_RIGHT = 0;
        public static final int TALON_LEFT = 0;
        public static final int VICTOR_CLIMB;
        
    }
}
