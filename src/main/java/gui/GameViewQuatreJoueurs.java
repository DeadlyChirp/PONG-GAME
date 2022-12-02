package gui;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
//import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.CourtQuatreJoueurs;
//import model.Feature;

public class GameViewQuatreJoueurs{

	private CourtQuatreJoueurs court;
	private Pane gameRoot; // main node of the game
    private double scale;
    private double xMargin = 50.0, yMargin = 25.0, racketThickness = 10.0; // pixels
    private final Rectangle racketA, racketB, racketC, racketD;
    private final Circle ball;
	
    public GameViewQuatreJoueurs(CourtQuatreJoueurs court, Pane root, double scale) {
    	this.court = court;
        this.gameRoot = root;
        this.scale = scale;

        root.setMinWidth(court.getWidth() * scale + 2 * xMargin);
        root.setMinHeight(court.getHeight() * scale + 2 * yMargin);

        racketA = new Rectangle();
        racketA.setHeight(court.getRacketSize() * scale);
        racketA.setWidth(racketThickness);
        racketA.setFill(Color.BLACK);

        racketA.setX(xMargin - racketThickness);
        racketA.setY(court.getRacketA() * scale);

        racketB = new Rectangle();
        racketB.setHeight(court.getRacketSize() * scale);
        racketB.setWidth(racketThickness);
        racketB.setFill(Color.CYAN);

        racketB.setX(court.getWidth() * scale + xMargin);
        racketB.setY(court.getRacketB() * scale);
        
		racketC = new Rectangle();
		racketC.setWidth(court.getRacketSize() * scale);
		racketC.setHeight(racketThickness);
		racketC.setFill(Color.RED);
		
		racketC.setY(yMargin - racketThickness);
		racketC.setX(court.getRacketC() * scale);
		    
		racketD = new Rectangle();
		racketD.setWidth(court.getRacketSize() * scale);
		racketD.setHeight(racketThickness);
		racketD.setFill(Color.LIGHTBLUE);
		
		racketD.setY(court.getRacketD() * scale);
		racketD.setX(court.getWidth() * scale + yMargin);
		
		ball = new Circle();
        ball.setRadius(court.getBallRadius());
        ball.setFill(Color.BLACK);

        ball.setCenterX(court.getBallX() * scale + xMargin);
        ball.setCenterY(court.getBallY() * scale);
		
		gameRoot.getChildren().addAll(racketA, racketB, racketC, racketD,ball);
    }
    
    Rectangle getRacketC() {
    	return racketC;
    }
    
    Rectangle getRacketD() {
    	return racketD;
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
                racketC.setX(court.getRacketC() * scale);
                racketD.setX(court.getRacketD() * scale);
                ball.setCenterX(court.getBallX() * scale + xMargin);
                ball.setCenterY(court.getBallY() * scale + xMargin);
            }
        }.start();
    }
}
