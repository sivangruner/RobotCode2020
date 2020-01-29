package AutoLib;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class DrivePIDConfig implements Sendable {
    private final double kMs = 30;
    public double LkP;
    public double LkI;
    public double LkD;
    public double LkF;
    public double RkP;
    public double RkI;
    public double RkD;
    public double RkF;

    public DrivePIDConfig(double LkP, double LkI, double LkD, double LkF, double RkP, double RkI, double RkD,
            double RkF) {
        this.LkD = LkD;
        this.LkF = LkF;
        this.LkI = LkI;
        this.LkP = LkP;
        this.RkD = RkD;
        this.RkF = RkF;
        this.RkI = RkI;
        this.RkP = RkP;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("Left Kp", () -> LkP, (d) -> LkP = d);
        builder.addDoubleProperty("Left Ki", () -> LkI, (d) -> LkI = d);
        builder.addDoubleProperty("Left Kd", () -> LkD, (d) -> LkD = d);
        builder.addDoubleProperty("Left Kf", () -> LkF, (d) -> LkF = d);
        builder.addDoubleProperty("right Kp", () -> RkP, (d) -> RkP = d);
        builder.addDoubleProperty("right Ki", () -> RkI, (d) -> RkI = d);
        builder.addDoubleProperty("right Kd", () -> RkD, (d) -> RkD = d);
        builder.addDoubleProperty("right Kf", () -> RkF, (d) -> RkF = d);
    }
}
