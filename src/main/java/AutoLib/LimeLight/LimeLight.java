package AutoLib.LimeLight;
import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LimeLight{
    private NetworkTable table;
    private Vector3D TargetPos;
    public double angleX,angleY,distance,skew;

    private static final double X_ANGLE_OFFSET = 0;
    private static final double Y_ANGLE_OFFSET = 0;

    private static final double X_POSITION_OFFSET = 0;
    private static final double Y_POSITION_OFFSET = 0;
    private static final double Z_POSITION_OFFSET = 0;

    private static final double POWER_PORT_HEIGHT = 2.5;
    private static final double CAMERA_HEIGHT = 0.76;

    private DoubleSupplier angle;

    Notifier notifier; 
    public LimeLight(DoubleSupplier angle){
        this.TargetPos = new Vector3D(0,0,0);
        this.table = NetworkTableInstance.getDefault().getTable("limelight");
        this.angle = angle;

        notifier = new Notifier(()-> process());
        notifier.startPeriodic(20);
    }

    

    public void process(){
        offSetCameraCalc();
        this.skew = getSkew(angle.getAsDouble());
        alignTargetVectorToCenter(angle.getAsDouble());
        this.angleX = getXAngle();
        this.angleY = getYAngle();
        this.distance = getDistance();

        
        SmartDashboard.putNumber("Angle Y", this.angleY);
        SmartDashboard.putNumber("Angle X", this.angleX);
        SmartDashboard.putNumber("Distance", this.distance);
    }

    private double getXAngle(){
        return Math.toDegrees(Math.atan2(this.TargetPos.x, this.TargetPos.y));
    }
    
    private double getYAngle(){
        return Math.toDegrees(Math.atan2(this.TargetPos.z, this.TargetPos.y));
    }

    private double getDistance(){
        return Math.sqrt(Math.pow(this.TargetPos.y,2)+Math.pow(this.TargetPos.x,2));
    }

    // asummes the angle is between 180 to -180
    @Experimental
    private double getSkew(double RobotAngle){
        // keep the angle between 90 and -90
        // the angle gets closer to 0 when the gyro nears 180 or 0
        double angle = getXAngle();
        double step = 90;
        if(angle < -step){
            angle += 2*step;
        }else if(angle >step){
            angle -= 2*step;
        }
        return angle + RobotAngle;
    }

    /**
     * align the target position to the center of the inner circle
     * @param RobotAngle
     */
    @Experimental
    private void alignTargetVectorToCenter(double RobotAngle){
        Vector3D inner = new Vector3D(Math.sin(Math.toRadians(RobotAngle)),Math.cos(Math.toRadians(RobotAngle)),0);
        this.TargetPos.add(inner);
    }

    /**
     * find the vector from the center of the robot to the target
     * using the camera offSet from the center of the robot
     * 
     * this algorithem assumes that the camera is at pos (0,0,0)
     * 
     * X Axis - robot's right
     * Y Axis - robot's forward
     * Z Axis - robot's up
     */
    public void offSetCameraCalc(){
        double z = POWER_PORT_HEIGHT - CAMERA_HEIGHT;
        double y = z/Math.tan(Math.toRadians(table.getEntry("ty").getDouble(0)+Y_ANGLE_OFFSET));
        double x = y*Math.tan(Math.toRadians(table.getEntry("tx").getDouble(0)+X_ANGLE_OFFSET));
        Vector3D simpleTarget = new Vector3D(x,y,z);
        Vector3D robotCenter = new Vector3D(X_POSITION_OFFSET,Y_POSITION_OFFSET,Z_POSITION_OFFSET);
        this.TargetPos = Vector3D.subtract(simpleTarget, robotCenter);
    }
}