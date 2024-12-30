package com.springdata.compilation;

import com.springdata.compilation.Entities.State;
import com.springdata.compilation.Entities.Transition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Automata -- DFA version only! --");
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/automation.png"));
        stage.show();
        System.out.println("Starting Application ................................................");

    }

    public static void main(String[] args) {launch();}
}