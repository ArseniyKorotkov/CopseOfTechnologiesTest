package by.arsy.techtest;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class FloatingCircles extends Application {

    private static final String APP_NAME = "Floating Circles";
    private static final String ERROR_MESSAGE = "Radius is too big for this screen ";
    private static final String END_ERROR_MESSAGE_WIDTH = "width";
    private static final String END_ERROR_MESSAGE_HEIGHT = "height";
    private static final double SCREEN_HEIGHT = 700;
    private static final double SCREEN_WIDTH = 700;


    @Override
    public void start(Stage stage) {
        Circle circle1 = createCircle(0, 0, 100, Color.ORANGE, Color.BLACK);
        Circle circle2 = createCircle(SCREEN_WIDTH, SCREEN_HEIGHT / 4, 105, Color.GREEN, Color.GREY);

        new FloatingCircleTransition(circle1, SCREEN_WIDTH, SCREEN_HEIGHT).play(1000, 120);
        new FloatingCircleTransition(circle2, SCREEN_WIDTH, SCREEN_HEIGHT).play(500, 275);


        Group group = new Group();
        group.getChildren().add(circle1);
        group.getChildren().add(circle2);

        Scene scene = new Scene(group, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setTitle(APP_NAME);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private Circle createCircle(double centerX, double centerY, double radius, Color colorFill, Color colorStroke) {

        checkCircleRadiusToScreenSize(radius);

        centerX = fixCenterXCircleInScreen(centerX, radius);
        centerY = fixCenterYCircleInScreen(centerY, radius);

        Circle circle = new Circle();
        circle.setTranslateX(centerX);
        circle.setTranslateY(centerY);
        circle.setRadius(radius);
        circle.setFill(colorFill);
        circle.setStroke(colorStroke);

        return circle;
    }

    private void checkCircleRadiusToScreenSize(double radius) {
        String endErrorMessage;
        if(SCREEN_WIDTH <= radius * 2) {
            endErrorMessage = END_ERROR_MESSAGE_WIDTH;
        } else if(SCREEN_HEIGHT <= radius * 2) {
            endErrorMessage = END_ERROR_MESSAGE_HEIGHT;
        } else {
            return;
        }
        throw new RuntimeException(ERROR_MESSAGE + endErrorMessage);
    }


    private double fixCenterXCircleInScreen(double centerX, double radius) {
        if (centerX < radius) {
            centerX = radius;
        }
        if (centerX > SCREEN_WIDTH - radius) {
            centerX = SCREEN_WIDTH - radius;
        }
        return centerX;
    }

    private double fixCenterYCircleInScreen(double centerY, double radius) {
        if (centerY < radius) {
            centerY = radius;
        }
        if (centerY > SCREEN_HEIGHT - radius) {
            centerY = SCREEN_HEIGHT - radius;
        }
        return centerY;
    }
}