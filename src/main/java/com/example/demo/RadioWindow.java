package com.example.demo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RadioWindow  {

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Radio Buttons Example");

        // Create radio buttons
        RadioButton radioButton1 = new RadioButton("Option 1");
        RadioButton radioButton2 = new RadioButton("Option 2");
        RadioButton radioButton3 = new RadioButton("Option 3");

        // Create a toggle group and add radio buttons to it
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        radioButton3.setToggleGroup(toggleGroup);

        // Create buttons
        Button okButton = new Button("OK");
        Button cancelButton = new Button("Cancel");

        // Create a vertical layout and add components to it
        VBox vbox = new VBox(10); // spacing between components
        vbox.setPadding(new Insets(10)); // padding around the layout
        vbox.getChildren().addAll(radioButton1, radioButton2, radioButton3, okButton, cancelButton);

        // Create a scene and set it on the primary stage
        Scene scene = new Scene(vbox, 200, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private String button Selected
}
