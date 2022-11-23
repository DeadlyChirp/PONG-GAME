package model;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CompteurVie;

public class SliderVie extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX App");

        Slider slider = new Slider(0, 100, 0);

        VBox vBox = new VBox(slider);
        Scene scene = new Scene(vBox, 960, 600);
        int a = (int) slider.getValue();
        CompteurVie.s1.setText(String.valueOf(Integer.valueOf(a)));
        CompteurVie.s2.setText(String.valueOf(Integer.valueOf(a)));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}