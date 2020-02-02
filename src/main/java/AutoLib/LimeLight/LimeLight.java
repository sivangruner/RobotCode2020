package AutoLib.LimeLight;

import java.util.Vector;
import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Notifier;
import frc.robot.Constants;

public class LimeLight {
    private static LimeLight instance;
    private NetworkTable table;

    private Vector3D TargetPos;

    public double angleX,angleY,distance,skew;
    

    private static final double X_ANGLE_OFFSET = 0;
    private static final double Y_ANGLE_OFFSET = 0;

    private static final double X_POSITION_OFFSET = 0;
    private static final double Y_POSITION_OFFSET = 0;

    private static final double TARGET_HEIGHT = 0;

    private DoubleSupplier angle;
    private Notifier noty;
    public LimeLight(DoubleSupplier angle){
        this.TargetPos = new Vector3D(0,0,0);
        this.table = NetworkTableInstance.getDefault().getTable("limelight");
        this.noty = new Notifier(() -> {
        if(this.table.getEntry("tv").getBoolean(false))
            process();
        });
        noty.startPeriodic(50);

        this.angle = angle;
    }

    

    private void process(){
        offSetCameraCalc();
        this.skew = getSkew(angle.getAsDouble());
        alignTargetVectorToCenter(angle.getAsDouble());
        this.angleX = getXAngle();
        this.angleY = getYAngle();
        this.distance = getDistance();
    }

    private double getXAngle(){
        return Math.toDegrees(Math.atan2(this.TargetPos.x, this.TargetPos.z));
    }
    
    private double getYAngle(){
        return Math.toDegrees(Math.atan2(this.TargetPos.y, this.TargetPos.z));
    }

    private double getDistance(){
        return this.TargetPos.z;
    }
    private double getSkew(double RobotAngle) // asummes the angle is between 180 to -180
    {
        return getXAngle() + RobotAngle;
    }

    /**
     * align the target position to the center of the inner circle
     * @param RobotAngle
     */
    public void alignTargetVectorToCenter(double RobotAngle){
        Vector3D inner = new Vector3D(Math.sin(Math.toRadians(RobotAngle)),0,Math.cos(Math.toRadians(RobotAngle)));
        this.TargetPos.add(inner);
    }

    /**
     * find the vector from the center of the robot to the target
     * using the camera offSet from the center of the robot
     */
    public void offSetCameraCalc(){ 
        double y = Constants.GeneralConstants.POWER_PORT_HEIGHT - Constants.GeneralConstants.ROBOT_HEIGHT;
        double z = y/Math.tan(Math.toRadians(table.getEntry("ty").getDouble(0) + Y_ANGLE_OFFSET + 24.85));
        double x = Math.tan(Math.toRadians(table.getEntry("tx").getDouble(0) + X_ANGLE_OFFSET))*z;

        Vector3D OFFset = new Vector3D(X_POSITION_OFFSET,Y_POSITION_OFFSET,Constants.GeneralConstants.ROBOT_HEIGHT);
        Vector3D Target = new Vector3D(x,y,z);
        this.TargetPos = Vector3D.subtract(Target,OFFset);
    }
}