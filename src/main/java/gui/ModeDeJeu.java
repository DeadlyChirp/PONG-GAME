package gui;
import java.util.*;






import javafx.event.EventHandler;
import javafx.scene.control.*;

import javafx.scene.control.Alert.AlertType;


import javafx.event.ActionEvent;
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
import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import java.io.File; 
import java.io.IOException; 
import java.util.Scanner;


/*********************************************************************************************************************** */


//Menu pour les différents modes de jeu. On implémentera plus tard les modes de jeu interne aux 1vs1, 1vsBot ou 2vs2.

public class ModeDeJeu extends Application {
    public Pane root;
    public Scene gameScene;

    ModeDeJeu(Pane root, Scene a){
        this.root = root;
        gameScene = a;
    }

    public void start (Stage primaryStage) {
     
    Image image = new Image("file:src/Pictures/pong1.png");
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(350);
        imageView.setLayoutY(10);

        //Bouton Play
        Button oneVSone = new Button("1 VS 1") ;
        oneVSone.setLayoutX(170);
        oneVSone.setLayoutY(570);
        oneVSone.setEffect(new ImageInput(new Image("file:src/Pictures/1vs1.png")));
        oneVSone.setSkin(new MyButtonSkin(oneVSone));

        //Bouton Option
        Button oneVSBot = new Button("1 VS Bot");
        oneVSBot.setLayoutX(451);
        oneVSBot.setLayoutY(570);
        oneVSBot.setEffect(new ImageInput(new Image("file:src/Pictures/1vsbot.png")));
        oneVSBot.setSkin(new MyButtonSkin(oneVSBot));

        //Bouton quitter
        Button twoVStwo = new Button("2 VS 2");
        twoVStwo.setLayoutX(754);
        twoVStwo.setLayoutY(570);
        twoVStwo.setEffect(new ImageInput(new Image("file:src/Pictures/2vs2.png")));
        twoVStwo.setSkin(new MyButtonSkin(twoVStwo));

        Button Retour= new Button("Retour") ;
        Retour.setLayoutX(1100);
        Retour.setLayoutY(25);
        Retour.setEffect(new ImageInput(new Image("file:src/Pictures/retour.png")));
        Retour.setSkin(new MyButtonSkin(Retour));

        Image img = new Image("file:src/Pictures/fond.png");
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        root.setBackground(bGround);

    root.getChildren().add(imageView);
    root.getChildren().addAll(oneVSone, oneVSBot, twoVStwo, Retour);

    Retour.setOnAction(ev1 -> {
        primaryStage.close();
        Menu q = new Menu();
        q.start(primaryStage);
    });
    
    
    primaryStage.setScene(gameScene);
    primaryStage.show(); 


    oneVSone.setOnAction(ev1 -> {
        ArrayList<Integer> duree = new ArrayList<Integer>();
        duree.add(15);
        duree.add(30);
        duree.add(60);
        duree.add(120);

        ArrayList<Integer> manche = new ArrayList<Integer>();
        manche.add(5);
        manche.add(10);
        manche.add(15);
        manche.add(20);


        ChoiceDialog<Integer> dialogManche = new ChoiceDialog<Integer>(5, manche);
        dialogManche.initOwner(primaryStage);
        dialogManche.setTitle("Limite de la partie");
        dialogManche.setHeaderText("Veuillez choisir un nombre de manches");
        dialogManche.setContentText("Nombre : ");

        Optional<Integer> ecouteManche = dialogManche.showAndWait();
        ecouteManche.ifPresent(limit -> {
            ChoiceDialog<Integer> dialogDuree = new ChoiceDialog<Integer>(15, duree);
            dialogDuree.initOwner(primaryStage);
            dialogDuree.setTitle("Mode Timer");
            dialogDuree.setHeaderText("Veuillez choisir la durée de chaque manche");
            dialogDuree.setContentText("durée : ");

            Optional<Integer> ecouteDuree = dialogDuree.showAndWait();
            ecouteDuree.ifPresent(time -> {

            Pane root1 = new Pane();
            gameScene.setRoot(root1);
            App a = new App(root1, gameScene);
            a.startTimer(primaryStage, limit, time);
            });
        
        });

       
    });


     

   
    }

}
