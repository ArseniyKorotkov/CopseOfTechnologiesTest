package by.arsy.techtest.controller;


import by.arsy.techtest.dto.CircleDto;
import by.arsy.techtest.entity.FloatingCircleTransition;
import by.arsy.techtest.entity.SmartCircle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class FloatingCirclesController {

    private static final List<FloatingCircleTransition> CIRCLE_TRANSITIONS = new ArrayList<>();
    private static final String EXCEPTION_INPUT_MESSAGE = "Only numbers bigger than 0";
    private static final String EXCEPTION_SIZE_MESSAGE = "Radius is too big for this screen ";
    private static final String END_EXCEPTION_SIZE_MESSAGE_WIDTH = "width";
    private static final String END_EXCEPTION_SIZE_MESSAGE_HEIGHT = "height";
    private static final String NO_EXCEPTION = "";
    private static final String PLAY_BUTTON_NAME = "play";
    private static final String RESUME_BUTTON_NAME = "resume";
    private static final String PAUSE_BUTTON_NAME = "pause";
    private static final String TERMINATE_BUTTON_NAME = "terminate";
    private static final Color[] FILL_COLORS = {Color.GREEN, Color.AQUA, Color.ORANGE, Color.RED};
    private static final Color[] STROKE_COLORS = {Color.BLACK, Color.GREY};
    private static final int CEILING_LIMIT = 10_000;
    private static final int EXCEPTION_TEXT_FONT = 10;


    private boolean isProgramPaused = false;
    private boolean areAllValuesCorrect;
    private double screenHeight;
    private double screenWidth;

    @FXML
    private TextField circleSize;
    @FXML
    private TextField circleSpeed;
    @FXML
    private TextField circleStartAngle;
    @FXML
    private Button pauseButton;
    @FXML
    private Button playButton;
    @FXML
    private Text exceptionText;
    @FXML
    private Pane circleField;


    @FXML
    public void playFloating() {

        if (isProgramPaused) {
            resume();
            return;
        }

        prepare();

        CircleDto circleDto = takeInput();

        SmartCircle circle = createCircle(
                screenWidth,
                screenHeight,
                circleDto.getRadius(),
                FILL_COLORS[(int) (Math.random() * FILL_COLORS.length)],
                STROKE_COLORS[(int) (Math.random() * STROKE_COLORS.length)]);

        if (areAllValuesCorrect) {
            circleField.getChildren().add(circle);
            FloatingCircleTransition circleTransition = new FloatingCircleTransition(circle);
            circleTransition.play(circleDto.getSpeed(), circleDto.getAngle());
            CIRCLE_TRANSITIONS.add(circleTransition);
        }
    }

    @FXML
    public void pause() {

        exceptionText.setText(NO_EXCEPTION);

        if (circleField.getChildren().isEmpty()) {
            return;
        }

        if (isProgramPaused) {
            stop();
            return;
        }

        for (FloatingCircleTransition transition : CIRCLE_TRANSITIONS) {
            transition.stop();
        }

        switchToPauseState();
    }


    private void resume() {

        for (FloatingCircleTransition transition : CIRCLE_TRANSITIONS) {
            transition.resume();
        }

        switchToPlayState();
    }


    private void prepare() {
        areAllValuesCorrect = true;
        exceptionText.setText(NO_EXCEPTION);
        exceptionText.setFont(new Font(EXCEPTION_TEXT_FONT));
        screenHeight = circleField.getHeight();
        screenWidth = circleField.getWidth();
    }


    private CircleDto takeInput() {
        double radius = -1;
        double speed = -1;
        double angle = -1;
        try {
            radius = Double.parseDouble(circleSize.getText());
            speed = Double.parseDouble(circleSpeed.getText());
            angle = Double.parseDouble(circleStartAngle.getText());
            if (radius < 0 || speed < 0 || angle < 0) {
                throw new RuntimeException(EXCEPTION_INPUT_MESSAGE);
            }
        } catch (RuntimeException e) {
            showException(EXCEPTION_INPUT_MESSAGE);
        }

        speed = fixCeilingLimit(speed);

        return new CircleDto(radius, speed, angle);
    }


    private SmartCircle createCircle(double animationPlaceWidth,
                                     double animationPlaceHeight,
                                     double radius,
                                     Color colorFill,
                                     Color colorStroke) {

        checkCircleRadiusToScreenSize(radius);
        double centerX = animationPlaceWidth / 2;
        double centerY = animationPlaceHeight / 2;
        centerX = fixCenterXCircleInScreen(centerX, radius);
        centerY = fixCenterYCircleInScreen(centerY, radius);

        SmartCircle circle = new SmartCircle(animationPlaceHeight, animationPlaceWidth);
        circle.setTranslateX(centerX);
        circle.setTranslateY(centerY);
        circle.setRadius(radius);
        circle.setFill(colorFill);
        circle.setStroke(colorStroke);

        return circle;
    }


    private void stop() {
        circleField.getChildren().clear();
        CIRCLE_TRANSITIONS.clear();

        switchToPlayState();
    }


    private void switchToPauseState() {
        isProgramPaused = true;
        playButton.setText(RESUME_BUTTON_NAME);
        pauseButton.setText(TERMINATE_BUTTON_NAME);
    }


    private void switchToPlayState() {
        playButton.setText(PLAY_BUTTON_NAME);
        pauseButton.setText(PAUSE_BUTTON_NAME);
        isProgramPaused = false;
    }


    private double fixCeilingLimit(double value) {
        return value < CEILING_LIMIT ? value : CEILING_LIMIT;
    }


    private void checkCircleRadiusToScreenSize(double radius) {
        String endErrorMessage;
        if (screenWidth <= radius * 2) {
            endErrorMessage = END_EXCEPTION_SIZE_MESSAGE_WIDTH;
        } else if (screenHeight <= radius * 2) {
            endErrorMessage = END_EXCEPTION_SIZE_MESSAGE_HEIGHT;
        } else {
            return;
        }
        showException(EXCEPTION_SIZE_MESSAGE + endErrorMessage);
    }


    private void showException(String exceptionMessage) {

        areAllValuesCorrect = false;
        exceptionText.setText(exceptionMessage);

    }


    private double fixCenterXCircleInScreen(double centerX, double radius) {

        if (centerX < radius) {
            centerX = radius;
        }

        if (centerX > screenWidth - radius) {
            centerX = screenWidth - radius;
        }

        return centerX;
    }


    private double fixCenterYCircleInScreen(double centerY, double radius) {

        if (centerY < radius) {
            centerY = radius;
        }

        if (centerY > screenHeight - radius) {
            centerY = screenHeight - radius;
        }

        return centerY;
    }

}
