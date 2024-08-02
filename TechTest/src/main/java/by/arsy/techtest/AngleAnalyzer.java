package by.arsy.techtest;


public class AngleAnalyzer {

    private static final double ROUND_ANGLE = 360;
    private static final int CORRECT_Y_UP_IS_GROW = -1;

    private final double pixels;
    private final double refreshRate;
    private final double angleToRadians;
    private double xValue = Double.NaN;
    private double yValue = Double.NaN;


    public AngleAnalyzer(double angle, double pixels, double refreshRate) {
        this.pixels = pixels;
        this.refreshRate = refreshRate;
        angleToRadians = angleToRadians(angle);
    }


    public double getX() {
        if (Double.isNaN(xValue)) {
            xValue = pixels * Math.sin(angleToRadians) * refreshRate;
        }
        return xValue;
    }


    public double getY() {
        if (Double.isNaN(yValue)) {
            yValue = pixels * Math.cos(angleToRadians) * refreshRate * CORRECT_Y_UP_IS_GROW;
        }
        return yValue;
    }


    private double angleToRadians(double angle) {

        angle = fixAngle(angle);
        return Math.toRadians(angle);
    }


    private double fixAngle(double angle) {

        angle = angle % ROUND_ANGLE;

        if (angle < 0) {
            angle = ROUND_ANGLE - angle;
        }

        return angle;
    }
}
