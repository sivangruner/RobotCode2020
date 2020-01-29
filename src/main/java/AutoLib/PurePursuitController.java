package AutoLib;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class PurePursuitController {

    private WPI_TalonSRX right;
    private WPI_TalonSRX left;

    private DrivePIDConfig config;

    private double lookDistance;
    private int lastClosestPoint;
    public double lastLookaheadindex;
    private ArrayList<Waypoint> path;

    public PurePursuitController(ArrayList<Waypoint> path, double lookDis,
     WPI_TalonSRX right, WPI_TalonSRX left, DrivePIDConfig config) {
        this.lookDistance = lookDis;
        this.path = path;
        this.lastClosestPoint = 0;
        this.lastLookaheadindex = 0;
        this.right = right;
        this.left = left;
        this.config = config;
        configPIDConstants();
        
    }

    public void drive(Vector robotOdometry, double robotAngle){
        Waypoint closest = getClosestPoint(robotOdometry);
        Vector look = getLookaheadPoint(robotOdometry);
    
        double sc = getCurvature(robotAngle, robotOdometry, look);   
        double ls = getLeftTargetVelocity(closest, sc, Constants.TRACK_WIDTH);
        double rs = getRightTargetVelocity(closest, sc, Constants.TRACK_WIDTH);
    
        right.set(ControlMode.Velocity, -rs/Constants.timesRawVelocityToMeters);
        left.set(ControlMode.Velocity, ls/Constants.timesRawVelocityToMeters);
        SmartDashboard.putNumber("LS", ls);
        SmartDashboard.putNumber("RS", -rs);
    }
    
    private void configPIDConstants(){
        this.left.configClosedLoopPeriod(0, 1);
        this.right.configClosedLoopPeriod(0, 1);
        this.left.config_kP(0, config.LkP);
        this.left.config_kI(0, config.LkI);
        this.left.config_kD(0, config.LkD);
        this.left.config_kF(0, config.LkF);
        this.right.config_kP(0, config.RkP);
        this.right.config_kI(0, config.RkI);
        this.right.config_kD(0, config.RkD);
        this.right.config_kF(0, config.RkF);
    }

    public DrivePIDConfig getConfig() {
        return config;
    }

    public void setConfig(DrivePIDConfig config) {
        this.config = config;
    }

    public void resetPurePursuit() {
        this.lastClosestPoint = 0;
        this.lastLookaheadindex = 0;
    }

    public Waypoint getClosestPoint(Vector robotPos){
        int closest = lastClosestPoint + 1;
        int length = path.size();
        if(lastClosestPoint >= length -1) {
             return path.get(length-1);}

        for(int i = lastClosestPoint; i < path.size(); i++){
            if(robotPos.distance(path.get(i))< robotPos.distance(path.get(closest))) closest = i;
        }

        this.lastClosestPoint = closest;
        return path.get(closest);
    }


    /**
     * @param RobotAngle
     * @param pos robots position
     * @param look look ahead point 
     * @return the path curvature
     */
    public double getCurvature(double RobotAngle,Vector pos,Vector look){
        RobotAngle = Math.toRadians(RobotAngle);
        Vector delta = Vector.subtract(look,pos);
        double beta = delta.getAngle(),toAngle = beta - RobotAngle;
        Vector relitiVector = new Vector();
        relitiVector = Vector.toCartesian(this.lookDistance, toAngle);
        return (2*relitiVector.y)/Math.pow(this.lookDistance,2);
    }

    public double geteLeftDesiredVelocity(double targetV, double curveture, double TRACK_WIDTH){
        return targetV*(2+(curveture * TRACK_WIDTH))/2;
    }
    public double getRightDesiredVelocity(double targetV, double curveture, double TRACK_WIDTH){
        return targetV*(2-(curveture * TRACK_WIDTH))/2;
    }

    /**
     * this function gets the closest look ahead point in the path
     * @link source https://stackoverflow.com/questions/1073336/circle-line-segment-collision-detection-algorithm/1084899#1084899      
     * @param pos
     * @param path
     * @return the look ahead point
     */
    public Vector getLookaheadPoint(Vector pos) {
        Vector lookahead = null;
        double length;
        int size = path.size();
        // iterate through all pairs of points
        for (int i = 0; i < size - 1; i++) {
            // form a segment from each two adjacent points
            Vector E = path.get(i);
            Vector L = path.get(i + 1);

            Vector d = Vector.subtract(L,E);
            Vector f = Vector.subtract(E,pos);
            length = d.getLength();
            double a = d.dot(d);
            double b = 2*f.dot(d);
            double c = f.dot(f) - Math.pow(this.lookDistance,2);

            double discriminant = b*b - 4*a*c;

            if(discriminant >= 0){

                discriminant = Math.sqrt(discriminant);
                double t1 = (-b - discriminant)/(2*a);
                double t2 = (-b + discriminant)/(2*a);
                if(t1>= 0 && t1<=1){
                    lookahead = new Vector(E);
                    d.normalize();
                    d.multiply(t1*length);
                    lookahead.add(d);
                }
                if(t2>= 0 && t2<=1){
                    d.normalize();
                    lookahead = new Vector(E);
                    d.multiply(t2*length);
                    lookahead.add(d);
                }
            }
        }

        if(lookahead == null) lookahead = (Vector)path.get(0);
        return lookahead;

    }
    public double getSignesCurvature(Vector lookaheadpoint, double robotAngle, Vector robotPos){
        return Math.signum(Math.sin(robotAngle)*(lookaheadpoint.x - robotPos.x) - Math.cos(robotAngle)*(lookaheadpoint.y - robotPos.y));
    }

    // public double getCurv(Vector lookahead,double lookDis){return (2*lookahead.x)/(lookDis*lookDis);}

    public double getRightTargetVelocity(Waypoint closestWaypoint, double curvature, double trackWidth){
        return closestWaypoint.velocity* (2 - curvature * trackWidth)/2;
    }
    
    public double getLeftTargetVelocity(Waypoint closestWaypoint, double curvature, double trackWidth){
        return closestWaypoint.velocity* (2 + curvature * trackWidth)/2;
    }

}
