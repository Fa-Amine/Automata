package com.springdata.compilation.Entities;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class State extends Group {
    private final Circle circle;
    private final Label label;

    public State(String name, double x, double y, double radius) {

        this.circle = new Circle(x, y, radius);
        circle.setFill(Color.LIGHTBLUE);
        circle.setStroke(Color.BLACK);

        this.label = new Label(name);
        label.setLayoutX(x - radius / 2);
        label.setLayoutY(y - radius / 2);

        this.getChildren().addAll(circle, label);

        enableDrag();
    }

    private void enableDrag() {
        this.setOnMousePressed(e -> {
            this.setUserData(new Point2D(e.getSceneX(), e.getSceneY()));
        });

        this.setOnMouseDragged(e -> {
            Point2D initial = (Point2D) this.getUserData();
            double offsetX = e.getSceneX() - initial.getX();
            double offsetY = e.getSceneY() - initial.getY();
            this.setLayoutX(this.getLayoutX() + offsetX);
            this.setLayoutY(this.getLayoutY() + offsetY);
            this.setUserData(new Point2D(e.getSceneX(), e.getSceneY()));
        });
    }

    public Circle getCircle() {
        return circle;
    }

    public Label getLabel() {
        return label;
    }
}
