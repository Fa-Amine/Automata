package com.springdata.compilation;

import com.springdata.compilation.Entities.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    public Pane mainPane;
    @FXML
    private TextField statenameField;
    @FXML
    private Label welcomeText;
    private List<State> states  = new ArrayList<>();
    @FXML
    protected void onHelloButtonClick() {


    }

    public void onAddTransitionClick(ActionEvent actionEvent) {
    }

    public void onClearClick(ActionEvent actionEvent) {
        mainPane.getChildren().clear();
        states.clear();
    }

    public void onAddStateClick(ActionEvent actionEvent) {

        State state = new State("q" + states.size(), 100 + states.size() * 50, 100, 30);

        mainPane.getChildren().add(state);
        states.add(state);
    }
}