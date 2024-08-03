package by.arsy.techtest.entity;

import javafx.scene.shape.Circle;

public class SmartCircle extends Circle {

    private double animationPlaceHeight;
    private double animationPlaceWidth;

    public SmartCircle(double animationPlaceHeight, double animationPlaceWidth) {
        this.animationPlaceHeight = animationPlaceHeight;
        this.animationPlaceWidth = animationPlaceWidth;
    }

    public double getAnimationPlaceHeight() {
        return animationPlaceHeight;
    }

    public void setAnimationPlaceHeight(double animationPlaceHeight) {
        this.animationPlaceHeight = animationPlaceHeight;
    }

    public double getAnimationPlaceWidth() {
        return animationPlaceWidth;
    }

    public void setAnimationPlaceWidth(double animationPlaceWidth) {
        this.animationPlaceWidth = animationPlaceWidth;
    }
}
