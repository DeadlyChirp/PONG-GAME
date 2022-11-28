package gui;
import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*********************************************************************************************************************** */

//Classe de départ pour lancer le menu

public class Start extends Application {

    static Audio son = new Audio("src/Audio/MonsterCo.wav");

    public void start (Stage primaryStage) {
        Pane root = new Pane() ;
        Scene gameScene = new Scene(root) ;
        Menu a = new Menu(root, gameScene);                                             
        son.start();
        a.start(primaryStage);
    }

}
