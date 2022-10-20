package gui;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import model.Court;

public class GameView {

    // class parameters
    private final Court court;
    private final Pane gameRoot; // main node of the game
    private final double scale;
    private final double Margin = 100.0, racketThickness = 10.0, Interface = 100.0; // pixels

    // children of the game main node
    private final Rectangle racketA, racketB;
    private final Circle ball;

    private Text scoreP1, scoreP2;
    public boolean finGame = false;
    public boolean Pause = true;

    int Timer = 60; //1sec

    void setFin(boolean b){
        finGame = b;
    }
    boolean getFin(){
        return finGame;
    }
    void setPause(boolean b){
        Pause = b;
    }
    boolean getPause(){
        return Pause;
    }


    /**
     * @param court le "modèle" de cette vue (le terrain de jeu de raquettes et tout ce qu'il y a dessus)
     * @param root  le nœud racine dans la scène JavaFX dans lequel le jeu sera affiché
     * @param scale le facteur d'échelle entre les distances du modèle et le nombre de pixels correspondants dans la vue
     */
    public GameView(Court court, Pane root, double scale) {
        this.court = court;
        this.gameRoot = root;
        this.scale = scale;

        root.setMinWidth(court.getWidth() * scale + 2 * Margin);
        root.setMinHeight(court.getHeight() * scale + Margin + Interface);

        //Affichage de la balle et des raquettes

            racketA = new Rectangle();
            racketA.setHeight(court.getRacketSize() * scale);
            racketA.setWidth(racketThickness);
            racketA.setFill(Color.valueOf("#375745"));

            racketA.setX(Margin - racketThickness);
            racketA.setY(court.getRacketA() * scale + Interface + Margin/2);

            racketB = new Rectangle();
            racketB.setHeight(court.getRacketSize() * scale);
            racketB.setWidth(racketThickness);
            racketB.setFill(Color.valueOf("#375745"));

            racketB.setX(court.getWidth() * scale + Margin);
            racketB.setY(court.getRacketB() * scale + Interface + Margin/2);

            ball = new Circle();
            ball.setRadius(court.getBallRadius());
            ball.setFill(Color.valueOf("#375745"));

            ball.setCenterX(court.getBallX() * scale + Margin);
            ball.setCenterY(court.getBallY() * scale + Interface +  Margin/2);


        //Affichage de l'interface

            Group inter = new Group();

                Rectangle cadre = new Rectangle();
                cadre.setX(Margin/2);
                cadre.setY(Margin/4);
                cadre.setWidth(court.getWidth() + Margin );
                cadre.setHeight(Interface);
                cadre.setStroke(Color.valueOf("#375745"));
                cadre.setStrokeWidth(5);
                cadre.setFill(null);      

                court.score.s1.setX(30);
                court.score.s1.setY(59);
    
                court.score.s2.setX(1050);
                court.score.s2.setY(50);

            inter.getChildren().addAll(cadre, court.score.s1, court.score.s2);

            Line l1 = new Line();
            l1.setStartX(Margin/2);
            l1.setStartY(Interface + Margin/2 - ball.getRadius());
            l1.setEndX(Margin + Margin/2 + court.getWidth());
            l1.setEndY(Interface + Margin/2 - ball.getRadius());
            l1.setStroke(Color.valueOf("#375745"));
            l1.setStrokeWidth(5);

            Line l2 = new Line();
            l2.setStartX(Margin/2);
            l2.setStartY(Interface + Margin/2 + court.getHeight() + ball.getRadius());
            l2.setEndX(Margin + Margin/2 + court.getWidth());
            l2.setEndY(Interface + Margin/2 + court.getHeight() + ball.getRadius());
            l2.setStroke(Color.valueOf("#375745"));
            l2.setStrokeWidth(5);

            Rectangle zoneDeJeu = new Rectangle();
            zoneDeJeu.setX(Margin);
            zoneDeJeu.setY(Interface + Margin/2);
            zoneDeJeu.setWidth(court.getWidth());
            zoneDeJeu.setHeight(court.getHeight());
            zoneDeJeu.setFill(Color.valueOf("#aeb8b2"));
        
        gameRoot.getChildren().addAll(zoneDeJeu, l1, l2, racketA, racketB, ball, inter);

    }

    public void animate() {

        new AnimationTimer() {
            long last = 0;

            @Override
            public void handle(long now) {
                if(!Pause && !finGame){

                    if (last == 0) { // ignore the first tick, just compute the first deltaT
                        last = now;
                        return;
                    }
                    
                    court.update((now - last) * 1.0e-9); // convert nanoseconds to seconds
                    last = now;
                    racketA.setY(court.getRacketA() * scale + Margin/2 + Interface);
                    racketB.setY(court.getRacketB() * scale + Margin/2 + Interface);
                    ball.setCenterX(court.getBallX() * scale + Margin);
                    ball.setCenterY(court.getBallY() * scale + Margin/2 + Interface);
                }
                Timer--;
                if(Timer == 0){
                    Pause = false;
                }
            }
        }.start();
    }
    
}
