package gui;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Court;

public class GameView {
    // class parameters
    private final Court court;
    private final Pane gameRoot; // main node of the game
    private final double scale;
    private final double xMargin = 50.0, racketThickness = 10.0; // pixels

    // children of the game main node
    private final Rectangle racketA, racketB;
    private final Circle ball;
    // private Text scoreP1, scoreP2;

    public boolean finGame = false;

    public static boolean Pause = true;

    public static int Timer = 180; // 3sec

    /**
     * @param court le "modèle" de cette vue (le terrain de jeu de raquettes et tout
     *              ce qu'il y a dessus)
     * @param root  le nœud racine dans la scène JavaFX dans lequel le jeu sera
     *              affiché
     * @param scale le facteur d'échelle entre les distances du modèle et le nombre
     *              de pixels correspondants dans la vue
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

        ball.setCenterX(court.getBallX() * scale + xMargin);
        ball.setCenterY(court.getBallY() * scale);

        // scoreP1 = new Text();
        // scoreP2 = new Text();

        court.scoreVie.s1.setX(1050); // player 1
        court.scoreVie.s1.setY(50);

        court.scoreVie.s2.setX(30); // player 2
        court.scoreVie.s2.setY(50);

        // scoreP1.setX(30);
        // scoreP1.setY(50);
        //
        // scoreP2.setX(1050);
        // scoreP2.setY(50);
        //
        //
        // scoreP1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR,
        // 40));
        // scoreP2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR,
        // 40));
        // scoreP1.setFill(Color.RED);
        // scoreP2.setFill(Color.BLUE);

        gameRoot.getChildren().addAll(racketA, racketB, ball, court.scoreVie.s1, court.scoreVie.s2);

    }

    public void animate() {
        new AnimationTimer() { // generer seulement DeltaT
            long last = 0;

            @Override
            public void handle(long now) {

                // PAUSE game
                if (!Pause) {
                    if (last == 0) { // ignore the first tick, just compute the first deltaT
                        last = now;
                        return;
                    }
                    // ERROR FAIT +1 POINT POUR PLAYER 1
                    court.update((now - last) * 1.0e-9); // convert nanoseconds to seconds
                    last = now;
                    racketA.setY(court.getRacketA() * scale);
                    racketB.setY(court.getRacketB() * scale);
                    ball.setCenterX(court.getBallX() * scale + xMargin);
                    ball.setCenterY(court.getBallY() * scale);
                }
                Timer--;
                if (Timer == 0) {
                    Pause = false;
                }
            }
        }.start();
    }
}
