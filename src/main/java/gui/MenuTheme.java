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
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import java.io.File; 
import java.io.IOException; 
import java.util.Scanner;

import javax.swing.ImageIcon;

/* ------------------------------------------------------------------------------------------------------*/

//Test pour le menu Commande, à refaire en entier. 

public class MenuTheme extends Application{

    public Pane root;
    public Scene gameScene;
    Theme[] themeListe = {Theme.t0, Theme.t1, Theme.t2, Theme.t3};
    static int current = 0;
   
    MenuTheme(Pane root, Scene a){
        this.root = root;
        gameScene = a;
    }

    public void start (Stage primaryStage) {

        primaryStage.setScene(gameScene);
        primaryStage.show();

        Button Retour= new Button("Retour") ;
        Retour.setOnAction(ev1 -> {
            Pane root1 = new Pane();
            gameScene.setRoot(root1);
            Menu a = new Menu(root1, gameScene);
            a.start(primaryStage);
        });
        Retour.setLayoutX(1100);
        Retour.setLayoutY(25);
        Retour.setEffect(new ImageInput(new Image("file:src/Pictures/retour.png")));
        Retour.setSkin(new MyButtonSkin(Retour));
        
        ImageView preview = new ImageView(new Image(themeListe[current].fichier));

        Text nom = new Text(themeListe[current].nom);
        nom.setStyle("-fx-font: 30 arial;");
        nom.setX(500);
        nom.setY(490);

        Button prev = new Button("prev") ;
        prev.setOnAction(evl -> {
            if(current == 0){
                current = themeListe.length -1;
            } else {
                current --;
            }
            nom.setText(themeListe[current].nom);
            preview.setImage(new Image(themeListe[current].fichier));
        });
        prev.setLayoutX(489);
        prev.setLayoutY(580);
        prev.setEffect(new ImageInput(new Image("file:src/Pictures/prev.png")));
        prev.setSkin(new MyButtonSkin(prev));

        Button next = new Button("next") ;
        next.setOnAction(evl -> {
            if(current == themeListe.length -1){
                current = 0;
            } else {
                current ++;
            }
            nom.setText(themeListe[current].nom);
            preview.setImage(new Image(themeListe[current].fichier));
        });
        next.setLayoutX(650);
        next.setLayoutY(580);
        next.setEffect(new ImageInput(new Image("file:src/Pictures/next.png")));
        next.setSkin(new MyButtonSkin(next));


        Button save = new Button("Sauvegarder");
        save.setOnAction(evl -> {
            GameView.theme = themeListe[current].nom;
            Pane root1 = new Pane();
                    gameScene.setRoot(root1);
                    Menu a = new Menu(root1, gameScene);
                    a.start(primaryStage);
        });
        save.setLayoutX(550);
        save.setLayoutY(570);
        save.setEffect(new ImageInput(new Image("file:src/Pictures/enregistrer.png")));
        save.setSkin(new MyButtonSkin(save));

        root.getChildren().addAll(Retour, preview, nom, prev, next, save);
    }

}
