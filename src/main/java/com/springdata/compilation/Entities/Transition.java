package com.springdata.compilation.Entities;

import com.springdata.compilation.Entities.State;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

public class Transition extends Group {
    private Line line;
    private Polygon arrowHead;
    private Text label;
    private State fromState;
    private State toState;

    public Transition(State fromState, State toState, String labelText) {
        this.fromState = fromState;
        this.toState = toState;


        line = new Line();
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);


        arrowHead = new Polygon();
        arrowHead.setFill(Color.BLACK);


        label = new Text(labelText);
        label.setStyle("-fx-font-size: 14;");


        getChildren().addAll(line, arrowHead, label);

        // Bind the transition to the positions of the states
        updateTransition();
        bindTransition();
    }

    private void updateTransition() {

        double startX = fromState.getLayoutX() + fromState.getWidth() / 2;
        double startY = fromState.getLayoutY() + fromState.getHeight() / 2;
        double endX = toState.getLayoutX() + toState.getWidth() / 2;
        double endY = toState.getLayoutY() + toState.getHeight() / 2;


        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);


        updateArrowHead(startX, startY, endX, endY);


        label.setX((startX + endX) / 2);
        label.setY((startY + endY) / 2 - 10);
    }

    private void updateArrowHead(double startX, double startY, double endX, double endY) {
        double arrowLength = 10;
        double arrowWidth = 5;


        double angle = Math.atan2(endY - startY, endX - startX);


        double x1 = endX - arrowLength * Math.cos(angle - Math.PI / 6);
        double y1 = endY - arrowLength * Math.sin(angle - Math.PI / 6);

        double x2 = endX - arrowLength * Math.cos(angle + Math.PI / 6);
        double y2 = endY - arrowLength * Math.sin(angle + Math.PI / 6);


        arrowHead.getPoints().clear();
        arrowHead.getPoints().addAll(endX, endY, x1, y1, x2, y2);
    }

    private void bindTransition() {

        fromState.layoutXProperty().addListener((obs, oldVal, newVal) -> updateTransition());
        fromState.layoutYProperty().addListener((obs, oldVal, newVal) -> updateTransition());
        toState.layoutXProperty().addListener((obs, oldVal, newVal) -> updateTransition());
        toState.layoutYProperty().addListener((obs, oldVal, newVal) -> updateTransition());
    }
}
