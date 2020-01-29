package AutoLib.LimeLight;

import java.util.Vector;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Constants;

public class LimeLight {
    private static LimeLight instance;
    private NetworkTable table;

    private Vector3D virtualPos;

    public static final double X_ANGLE_OFFSET = 0;
    public static final double Y_ANGLE_OFFSET = 0;

    public static final double X_POSITION_OFFSET = 0;
    public static final double Y_POSITION_OFFSET = 0;

    public static final double TARGET_HEIGHT = 0;

    private LimeLight(){
        this.virtualPos = new Vector3D(0,0,0);
        this.table = NetworkTableInstance.getDefault().getTable("limelight");
    }

    public static LimeLight getInstance(){
        if(instance == null)
            instance = new LimeLight();
        return instance;
    }

    public double getXAngle(){
        return Math.toDegrees(Math.atan2(this.virtualPos.x, this.virtualPos.z));
    }
    
    public double getYAngle(){
        return Math.toDegrees(Math.atan2(this.virtualPos.y, this.virtualPos.z));
    }

    public double getDistance(){
        return this.virtualPos.z;
    }

    public void offSetCameraCalc(){ 
        double y = Constants.POWER_PORT_HEIGHT - Constants.ROBOT_HEIGHT;
        double z = y/Math.tan(Math.toRadians(table.getEntry("ty").getDouble(0) + Y_ANGLE_OFFSET + 24.85));
        double x = Math.tan(Math.toRadians(table.getEntry("tx").getDouble(0) + X_ANGLE_OFFSET))*z;
        Vector3D OFFset = new Vector3D(X_POSITION_OFFSET,Y_POSITION_OFFSET,Constants.ROBOT_HEIGHT);
        Vector3D Target = new Vector3D(x,y,z);
        this.virtualPos = Vector3D.subtract(Target,OFFset);
    }
}