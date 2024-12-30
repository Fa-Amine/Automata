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
import javafx.util.Pair;
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
    private Label welcomeText  , detailsText , transitioncount , statecount;
    private List<State> states  = new ArrayList<>();
    private Set<Transition> transitions  = new HashSet<>();
    // At the top of your HelloController class
    private Map<Pair<State, State>, List<Transition>> transitionsMap = new HashMap<>();

    @FXML
    private Button addTransitionButton;
    private String symbol;

    private State selectedSourceState = null; // Keeps track of the source state
    private State selectedTargetState = null; // Keeps track of the target state

    @FXML
    public void initialize() {
        addTransitionButton.setOnAction(event -> {
            if (selectedSourceState != null && selectedTargetState != null) {
                symbol = transitionsymbolfield.getText();
                if (symbol.isEmpty() || symbol.equals("Symbol here...")) {
                    symbol = "ε";
                }

                // Create new transition
                Transition newTransition = new Transition(selectedSourceState, selectedTargetState, symbol);

                // Add the transition to the pane and the transitions set
                mainPane.getChildren().add(newTransition);
                transitions.add(newTransition);

                // Adjust the transition positions
                adjustTransitionPositions(selectedSourceState, selectedTargetState);

                // Update transition count
                transitioncount.setText("" + transitions.size());

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
    private void handleStateClick(javafx.scene.input.MouseEvent event, State state) {
        if (selectedSourceState == null) {
            selectedSourceState = state;
            detailsText.setText("Source State clicked");
            state.setStyle("-fx-border-color: blue; -fx-border-width: 2;");
        } else if (selectedTargetState == null && state != selectedSourceState) {
            selectedTargetState = state;
            detailsText.setText("Target State clicked");
            state.setStyle("-fx-border-color: green; -fx-border-width: 2;");
        } else {
            clearSelections();  // Reset the selections if a state is clicked again
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
    private void adjustTransitionPositions(State fromState, State toState) {
        List<Transition> overlappingTransitions = new ArrayList<>();

        // Find all transitions between the same states
        for (Transition t : transitions) {
            if (t.getFromState() == fromState && t.getToState() == toState) {
                overlappingTransitions.add(t);
            }
        }

        double offset = 15; // Vertical offset between transitions
        int index = 0;

        // Adjust positions of all overlapping transitions
        for (Transition t : overlappingTransitions) {
            double shiftAmount = (index - (overlappingTransitions.size() - 1) / 2.0) * offset;
            t.shiftLabel(shiftAmount); // Shift each transition label and line vertically
            t.updateTransition();  // Recalculate and update transition line and arrowhead
            index++;
        }
    }





}