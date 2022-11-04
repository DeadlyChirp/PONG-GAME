package gui;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.Event;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import model.Court;
import model.RacketController;
import java.io.InputStream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import java.io.File; 
import java.io.IOException; 
import java.util.Scanner;

import javafx.scene.control.TextField;

/* ------------------------------------------------------------------------------------------------------*/

public class Commande extends Application{

    public Pane root;
    public Scene gameScene;

    Commande(Pane root, Scene a){
        this.root = root;
        gameScene = a;
    }

    public static String[] com = App.commandes;

    public void start (Stage primaryStage) {
        
        primaryStage.setScene(gameScene);
        primaryStage.show();


        Button Retour= new Button("Retour") ;
        Retour.setLayoutX(1100);
        Retour.setLayoutY(25);
        Retour.setEffect(new ImageInput(new Image("file:src/Pictures/retour.png")));
        Retour.setSkin(new MyButtonSkin(Retour));

        Retour.setOnAction(ev1 -> {
            primaryStage.close();
            Menu q = new Menu();
            q.start(primaryStage);
        });
        
        
        TextField bUp1 = new TextField(App.commandes[0]);
        bUp1.setEditable(false);
        bUp1.setOnKeyPressed(ev -> {
            com[0] = ev.getCode().toString();
            bUp1.setText(com[0]);
        });
        bUp1.setLayoutX(50);
        bUp1.setLayoutY(100);

   
        TextField bDown1 = new TextField(App.commandes[1]);
        bDown1.setEditable(false);
        bDown1.setOnKeyPressed(ev -> {
            com[1] = ev.getCode().toString();
            bDown1.setText(com[1]);
        });
        bDown1.setLayoutX(50);
        bDown1.setLayoutY(120);
        
  
        TextField bUp2 = new TextField(App.commandes[2]);
        bUp2.setEditable(false);
        bUp2.setOnKeyPressed(ev -> {
            com[2] = ev.getCode().toString();
            bUp2.setText(com[2]);
        });
        bUp2.setLayoutX(50);
        bUp2.setLayoutY(140);
        
    
        TextField bDown2 = new TextField(App.commandes[3]);
        bDown2.setEditable(false);
        bDown2.setOnKeyPressed(ev -> {
            com[3] = ev.getCode().toString();
            bDown2.setText(com[3]);
        });
        bDown2.setLayoutX(50);
        bDown2.setLayoutY(160);
        

        Button save = new Button("Sauvegarder");
        save.setOnAction(evl -> {
            App.setCommandes(com);
        });
        save.setLayoutX(50);
        save.setLayoutY(200);


        root.getChildren().addAll(Retour, save, bUp1, bDown1, bUp2, bDown2);

    }
}