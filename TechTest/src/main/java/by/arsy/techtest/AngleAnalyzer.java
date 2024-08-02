package by.arsy.techtest;

public class AngleAnalyzer {

    private static final double ROUND_ANGLE = 360;
    private static final int CORRECT_Y_UP_IS_GROW = -1;

    private final double angle;
    private final double pixels;
    private final double refreshRate;

    private double xValue = -1;
    private double yValue = -1;


    public AngleAnalyzer(double angle, double pixels, double refreshRate) {
        this.angle = angle;
        this.pixels = pixels;
        this.refreshRate = refreshRate;
    }

    public double getX() {
        if (xValue < 0) {
            double angelToRadians = angleToRadians(angle);
            xValue = pixels * Math.sin(angelToRadians) * refreshRate;
        }
        return xValue;
    }

    public double getY() {
        if(yValue < 0) {
            double angelToRadians = angleToRadians(angle);
            yValue = pixels * Math.cos(angelToRadians) * refreshRate * CORRECT_Y_UP_IS_GROW;
        }
        return yValue;
    }

    private double angleToRadians(double angle) {
        angle = fixAngle(angle);
        return Math.toRadians(fixAngle(angle));
    }

    private double fixAngle(double angle) {
        angle = angle % ROUND_ANGLE;
        if (angle < 0) {
            angle = ROUND_ANGLE - angle;
        }
        return angle;
    }
}
