package by.arsy.techtest.entity;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class FloatingCircleTransition {

    private static final double DURATION = 0.02;
    private static final int QUARTER_CIRCLE = 90;
    private static final int HALF_CIRCLE = 180;

    private final Circle circle;
    private final double animationPlaceWidth;
    private final double animationPlaceHeight;

    private TranslateTransition transition;
    private AngleAnalyzer angleAnalyzer;
    private double speedPixelOnSecond;
    private boolean isRightCircleDirection;
    private boolean isUpCircleDirection;


    public FloatingCircleTransition(Circle circle, double animationPlaceWidth, double animationPlaceHeight) {
        this.circle = circle;
        this.animationPlaceHeight = animationPlaceHeight;
        this.animationPlaceWidth = animationPlaceWidth;
    }


    public void play(double speedPixelOnSecond, double startAngle) {
        this.speedPixelOnSecond = speedPixelOnSecond;
        angleAnalyzer = new AngleAnalyzer(startAngle, speedPixelOnSecond, DURATION);

        transition = new TranslateTransition();
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setByX(angleAnalyzer.getX());
        transition.setByY(angleAnalyzer.getY());
        transition.setNode(circle);
        transition.setDuration(Duration.seconds(DURATION));
        transition.setCycleCount(0);
        transition.setOnFinished(event ->
                handlingWallCollision()
        );
        transition.play();

        updateDirection();
    }


    public void stop() {
        transition.stop();
    }


    public void resume() {
        transition.play();
    }


    private void handlingWallCollision() {

        angleAnalyzer = null;

        checkCollisionRightWall();
        checkCollisionLeftWall();
        checkCollisionUpWall();
        checkCollisionDownWall();

        if (angleAnalyzer != null) {
            transition.setByX(angleAnalyzer.getX());
            transition.setByY(angleAnalyzer.getY());
        }

        resume();
    }


    private void updateDirection() {

        updateUpDownDirection();
        updateRightLeftDirection();

    }


    private void checkCollisionRightWall() {

        if (circle.getTranslateX() > animationPlaceWidth - circle.getRadius() && isRightCircleDirection) {

            double randomRight = (Math.random() + 1) * HALF_CIRCLE;

            angleAnalyzer = new AngleAnalyzer(randomRight, speedPixelOnSecond, DURATION);
            updateUpDownDirection();
            isRightCircleDirection = false;
        }
    }


    private void checkCollisionLeftWall() {

        if (circle.getTranslateX() < circle.getRadius() && !isRightCircleDirection) {

            double randomLeft = Math.random() * HALF_CIRCLE;

            angleAnalyzer = new AngleAnalyzer(randomLeft, speedPixelOnSecond, DURATION);
            updateUpDownDirection();
            isRightCircleDirection = true;
        }
    }


    private void checkCollisionUpWall() {

        if (circle.getTranslateY() < circle.getRadius() && isUpCircleDirection) {

            double randomUp = Math.random() * HALF_CIRCLE + QUARTER_CIRCLE;

            angleAnalyzer = new AngleAnalyzer(randomUp, speedPixelOnSecond, DURATION);
            updateRightLeftDirection();
            isUpCircleDirection = false;
        }
    }


    private void checkCollisionDownWall() {

        if (circle.getTranslateY() > animationPlaceHeight - circle.getRadius() && !isUpCircleDirection) {

            double randomDown = Math.random() * HALF_CIRCLE;
            randomDown = randomDown > QUARTER_CIRCLE ? randomDown + HALF_CIRCLE : randomDown;

            angleAnalyzer = new AngleAnalyzer(randomDown, speedPixelOnSecond, DURATION);
            updateRightLeftDirection();
            isUpCircleDirection = true;
        }
    }


    private void updateUpDownDirection() {
        isUpCircleDirection = angleAnalyzer.getY() < 0;
    }


    private void updateRightLeftDirection() {
        isRightCircleDirection = angleAnalyzer.getX() > 0;
    }
}
