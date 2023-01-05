package gui;
import javafx.scene.text.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import model.*;

public class GameView4J extends GameView {
	
	private Court4J court;
    private final Rectangle racketC, racketD;
    private final Circle ball2;

    public GameView4J(Court4J court, Pane root, double scale) {
        super(court,root,scale);
        this.court = court;
		racketC = new Rectangle();
		racketC.setWidth(court.getRacketSize() * scale);
		racketC.setHeight(getRacketThickness());
		racketC.setFill(Color.LIGHTBLUE);
		
		racketC.setX(court.getRacketC() * scale + getMargin()/2);
		racketC.setY(getInTerface() + getMargin()/2 - getRacketThickness());
		    
		racketD = new Rectangle();
		racketD.setWidth(court.getRacketSize() * scale);
		racketD.setHeight(getRacketThickness());
		racketD.setFill(Color.LIGHTBLUE);
		
		racketD.setX(court.getRacketD() * scale + getMargin()/2);
		racketD.setY(getInTerface() + getMargin()/2 + court.getHeight() + getRacketThickness());

        ball2 = new Circle();
        ball2.setRadius(court.getBallRadius());
        ball2.setFill(Color.LIGHTBLUE);

        ball2.setCenterX(court.getBallX() * scale + getMargin());
        ball2.setCenterY(court.getBallY() * scale + getInTerface() +  getMargin()/2);
		    
        getGameRoot().getChildren().addAll(racketC, racketD, ball2);
    }

    public void animate() {
        new AnimationTimer() {
            long last = 0;
            @Override
            public void handle(long now) {
                if(!pause && !finGame){
                    if (last == 0) { // ignore the first tick, just compute the first deltaT
                        last = now;
                        return;
                    }
                    getCourt().update((now - last) * 1.0e-9); // convert nanoseconds to seconds
                    last = now;
                    getRacketA().setY(getCourt().getRacketA() * getScale() + getMargin()/2 + getInTerface());
                    getRacketB().setY(getCourt().getRacketB() * getScale() + getMargin()/2 + getInTerface());
                    racketC.setX(getMargin() + court.getRacketC() * getScale());
                    racketD.setX(getMargin() + court.getRacketD() * getScale());
                    getBall().setCenterX(getCourt().getBallX() * getScale() + getMargin());
                    getBall().setCenterY(getCourt().getBallY() * getScale() + getMargin()/2 + getInTerface());
                    ball2.setCenterX(court.getBallX2() * getScale() + getMargin());
                    ball2.setCenterY(court.getBallY2() * getScale() + getMargin()/2 + getInTerface());
                }else{
                    last = 0;
                }    
                Timer--;
            }
        }.start();
    }



}
