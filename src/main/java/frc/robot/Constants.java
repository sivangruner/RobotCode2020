package frc.robot;

public final class Constants {
    public static class HopperConstants {
        public static final double HOPPER_SPEED = 0;
        public static final double HOPPER_LOAD_BALLS_SPEED = 0;
        public static final double HOPPER_FEEDER_SPEED = 0;
		public static final double BELTS_SHOOTING_SPEED = 1;
    }
    public static class DriverConstants {
        public static final int TICKS_PER_REVOLTION = 4096;
        public static final double WHEEL_DIAMITER = 0.2032;
        public static final double METER_PER_TICK = .001558524480;
        public static final double TRACK_WIDTH = 0;
        public static final double timesRawVelocityToMeters = 0;
        public static final double WEIGHT_DATA = 0;
        public static final double WEIGHT_SMOOTH = 0;
        public static final double LOOKAHEAD_DISTANCE = 0;
		public static final double CLIMB_ROTATION_LIMITER = 0;
		public static final double DEADBAND = 0;
		public static final double SPEED_LIMITER = 0;
		public static final double ROTATION_LIMITER = 0;
		public static final double CLIMB_SPEED_LIMITER = 0;
    }
    public static class IntakeConstants {
        
    }
    public static class ClimbConstants {
        public static double OPEN_LIMITER = 0;
        public static double CLIMBERS_LIMITER = 0;

    }
    public static class ShooterConstants{
        public static final double TICKS_PER_100MS_TO_PRM = 60 * 10 / DriverConstants.TICKS_PER_REVOLTION;
        public static final double LOWER_SHOOT_SPEED = 0;
        public static final double THROW_ANGLE = Math.toRadians(0);
		public static final double ACCELETATE_RPM = 0;
		public static final double ManualShootDistance = 0;

    }
    public static class GeneralConstants {
        // field constants
        public static final double ROBOT_HEIGHT = 0;
        public static final double POWER_PORT_HEIGHT = 0;
        // physics constants
        public static final double G = 9.807;
        ////////////////////////////////////////////////////////////////////
        public static final int JOYSTICK_DRIVER_PORT = 0;
        public static final int JOYSTICK_OPERATOR_PORT = 0;
        public static final int XBOX_B_PORT = 0;
        public static final int XBOX_A_PORT = 0;
        public static final int XBOX_X_PORT = 0;
        public static final int XBOX_Y_PORT = 0;
		public static final double TalonVelocityDT = 10;
    }
}
