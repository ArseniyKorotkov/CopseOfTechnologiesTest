package by.arsy.techtest.dto;


public class CircleDto {

    private final double radius ;
    private final double speed;
    private final double angle;

    public CircleDto(double radius, double speed, double angle) {
        this.radius = radius;
        this.speed = speed;
        this.angle = angle;
    }

    public double getRadius() {
        return radius;
    }

    public double getSpeed() {
        return speed;
    }

    public double getAngle() {
        return angle;
    }
}
