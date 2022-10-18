package gui;

import javax.swing.JPanel;

import javafx.scene.text.*;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Court;
import model.TimeMode;

public class GameView {
    // class parameters
    private final Court court;
    private final Pane gameRoot; // main node of the game
    private final double scale;
    private final double xMargin = 50.0, racketThickness = 10.0; // pixels

    // children of the game main node
    private final Rectangle racketA, racketB;
    private final Circle ball;
    private Text chrono, scoreA, scoreB;

  

    /**
     * @param court le "modèle" de cette vue (le terrain de jeu de raquettes et tout ce qu'il y a dessus)
     * @param root  le nœud racine dans la scène JavaFX dans lequel le jeu sera affiché
     * @param scale le facteur d'échelle entre les distances du modèle et le nombre de pixels correspondants dans la vue
     */
    public GameView(Court court, Pane root, double scale) {
        this.court = court;
        this.gameRoot = root;
        this.scale = scale;

        root.setMinWidth(court.getWidth() * scale + 2 * xMargin);
        root.setMinHeight(court.getHeight() * scale);

        racketA = new Rectangle();
        racketA.setHeight(court.getRacketSize() * scale);
        racketA.setWidth(racketThickness);
        racketA.setFill(Color.BLACK);

        racketA.setX(xMargin - racketThickness);
        racketA.setY(court.getRacketA() * scale);

        racketB = new Rectangle();
        racketB.setHeight(court.getRacketSize() * scale);
        racketB.setWidth(racketThickness);
        racketB.setFill(Color.BLACK);

        racketB.setX(court.getWidth() * scale + xMargin);
        racketB.setY(court.getRacketB() * scale);

        ball = new Circle();
        ball.setRadius(court.getBallRadius());
        ball.setFill(Color.BLACK);

        chrono = new Text(String.valueOf(TimeMode.getTmp()));
        chrono.setX(500);
        chrono.setY(50);

        scoreA = new Text(String.valueOf(TimeMode.getScoreA()));
        scoreA.setX(30);
        scoreA.setY(50);

        scoreB = new Text(String.valueOf(TimeMode.getScoreB()));
        scoreB.setX(1050);
        scoreB.setY(50);
       
        chrono.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        scoreA.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        scoreB.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        scoreA.setFill(Color.BLUE);
        scoreB.setFill(Color.BLUE);




        gameRoot.getChildren().addAll(racketA, racketB, ball, chrono, scoreA, scoreB);


    }

    public void animate() {
        new AnimationTimer() {
            long last = 0;

            @Override
            public void handle(long now) {
                if (last == 0) { // ignore the first tick, just compute the first deltaT
                    last = now;
                    return;
                }
                court.update((now - last) * 1.0e-9); // convert nanoseconds to seconds
                last = now;
                racketA.setY(court.getRacketA() * scale);
                racketB.setY(court.getRacketB() * scale);
                ball.setCenterX(court.getBallX() * scale + xMargin);
                ball.setCenterY(court.getBallY() * scale);
                chrono.setText(String.valueOf(TimeMode.getTmp()));
                scoreA.setText(String.valueOf(TimeMode.getScoreA()));
                scoreB.setText(String.valueOf(TimeMode.getScoreB()));


            }
        }.start();
    }
}
