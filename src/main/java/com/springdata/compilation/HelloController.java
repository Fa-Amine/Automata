package com.springdata.compilation;

import com.almasb.fxgl.app.scene.FXGLDefaultMenu;
import com.springdata.compilation.Entities.State;
import com.springdata.compilation.Entities.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.w3c.dom.events.MouseEvent;

import java.util.*;

public class HelloController {
    @FXML
    public Pane mainPane;
    @FXML
    private TextField statenameField;
    @FXML
    private TextField transitionsymbolfield;
    @FXML
    private Label welcomeText , detailsText , transitioncount , statecount;
    private List<State> states  = new ArrayList<>();
    private Set<Transition> transitions  = new HashSet<>();
    @FXML
    private Button addTransitionButton;

    private State selectedSourceState = null; // Keeps track of the source state
    private State selectedTargetState = null; // Keeps track of the target state

    @FXML
    public void initialize() {
        addTransitionButton.setOnAction(event -> {
            if (selectedSourceState != null && selectedTargetState != null) {
                String symbol = transitionsymbolfield.getText();
                if (symbol.isEmpty() || symbol.equals("Symbol here...")) {
                    symbol = "ε";
                }
                Transition transition = new Transition(selectedSourceState, selectedTargetState, symbol);
                mainPane.getChildren().add(transition);
                selectedSourceState.transitions.put(transition,symbol.charAt(0));


                clearSelections();
            }
        });
    }


    private void createTransition(State source, State target, String label) {

    }


    public void attachStateClickListener(State state) {
        state.setOnMouseClicked(event -> handleStateClick(event, state)); // Use javafx.scene.input.MouseEvent
        System.out.println("State click listener attached.");
    }

    // Handles the selection of a state when clicked
    private void handleStateClick(javafx.scene.input.MouseEvent event, State state) { // Use javafx.scene.input.MouseEvent
        if (selectedSourceState == null) {
            selectedSourceState = state;
            detailsText.setText("Source State clicked");
            state.setStyle("-fx-border-color: blue; -fx-border-width: 2;"); // Highlight source state
        } else if (selectedTargetState == null && state != selectedSourceState) {
            selectedTargetState = state;
            detailsText.setText("Target State clicked");
            state.setStyle("-fx-border-color: green; -fx-border-width: 2;"); // Highlight target state
        } else {
            clearSelections();
        }
    }

    private void clearSelections() {
        if (selectedSourceState != null) {
            selectedSourceState.setStyle(""); // Reset style
        }
        if (selectedTargetState != null) {
            selectedTargetState.setStyle(""); // Reset style
        }
        selectedSourceState = null;
        selectedTargetState = null;
    }

    @FXML
    public void onHelloButtonClick() {
        // Create and add states
        State state1 = new State("q0", 100, 100, 30);
        State state2 = new State("q1", 300, 150, 30);

        mainPane.getChildren().addAll(state1, state2);

        // Attach click listeners
        attachStateClickListener(state1);
        attachStateClickListener(state2);
    }


    public void onAddTransitionClick(ActionEvent actionEvent) {
//
//       if (selectedSourceState != null && selectedTargetState != null) {
//                createTransition(selectedSourceState, selectedTargetState, "a"); // Example: label "a"
//                clearSelections();
//            }

    }

    public void onClearClick(ActionEvent actionEvent) {
        mainPane.getChildren().clear();
        states.clear();
    }

    public void onAddStateClick(ActionEvent actionEvent) {

        State state = new State("q" + states.size(), 100 + states.size() * 50, 100, 30);
        attachStateClickListener(state);
        mainPane.getChildren().add(state);

        states.add(state);
        statecount.setText("" + states.size());
    }
}