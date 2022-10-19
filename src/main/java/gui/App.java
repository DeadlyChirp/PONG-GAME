package gui;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Court;
import model.Feature;
import model.RacketController;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        var root = new Pane();
        var gameScene = new Scene(root);
        class Player implements RacketController {
            State state = State.IDLE;

            @Override
            public State getState() {
                return state;
            }
        }
        var playerA = new Player();
        var playerB = new Player();
        var playerC = new Player();
        var playerD = new Player();
        
        gameScene.setOnKeyPressed(ev -> {
            switch (ev.getCode()) {
                case CONTROL:
                    playerA.state = RacketController.State.GOING_UP;
                    break;
                case ALT:
                    playerA.state = RacketController.State.GOING_DOWN;
                    break;
                case UP:
                    playerB.state = RacketController.State.GOING_UP;
                    break;
                case DOWN:
                    playerB.state = RacketController.State.GOING_DOWN;
                    break;
                case Z:
                    playerC.state = RacketController.State.GOING_UP;
                    break;
                case S:
                    playerC.state = RacketController.State.GOING_DOWN;
                    break;
                case E:
                    playerD.state = RacketController.State.GOING_UP;
                    break;
                case D:
                    playerD.state = RacketController.State.GOING_DOWN;
                    break;
            }
        });
        gameScene.setOnKeyReleased(ev -> {
            switch (ev.getCode()) {
                case CONTROL:
                    if (playerA.state == RacketController.State.GOING_UP) playerA.state = RacketController.State.IDLE;
                    break;
                case ALT:
                    if (playerA.state == RacketController.State.GOING_DOWN) playerA.state = RacketController.State.IDLE;
                    break;
                case UP:
                    if (playerB.state == RacketController.State.GOING_UP) playerB.state = RacketController.State.IDLE;
                    break;
                case DOWN:
                    if (playerB.state == RacketController.State.GOING_DOWN) playerB.state = RacketController.State.IDLE;
                    break;
                case Z:
                    if (playerC.state == RacketController.State.GOING_UP) playerC.state = RacketController.State.IDLE;
                    break;
                case S:
                    if (playerC.state == RacketController.State.GOING_DOWN) playerC.state = RacketController.State.IDLE;
                    break;
                case E:
                    if (playerD.state == RacketController.State.GOING_UP) playerD.state = RacketController.State.IDLE;
                    break;
                case D:
                    if (playerD.state == RacketController.State.GOING_DOWN) playerD.state = RacketController.State.IDLE;
                    break;
            }
        });
        var court = new Court(playerA, playerB, playerC, playerD, 1000, 600);
        var gameView = new GameView(court, root, 1.0);
        primaryStage.setScene(gameScene);
        primaryStage.show();
        gameView.animate();
    }
}
