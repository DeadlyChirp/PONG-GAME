package model;
import java.util.Random;

import gui.GameView;

public class Court4J extends Court{
    // instance parameters
    private final RacketController playerC, playerD;
    // instance state
    private double racketC; // m
    private double racketD; // m

    //Création d'une deuxieme balle    
    private double ballX2, ballY2; // position de la balle
    private double ballSpeedX2, ballSpeedY2; 

    public Court4J(RacketController playerA, RacketController playerB, RacketController playerC, RacketController playerD, double width, double height, int limit) {
    	super(playerA, playerB, width, height,limit);
        this.playerC = playerC;
        this.playerD = playerD;
        reset();
    }

    public Court4J(RacketController playerA, RacketController playerB, RacketController playerC, RacketController playerD, double width, double height) {
        super(playerA, playerB, width, height);
        this.playerC = playerC;
        this.playerD = playerD;
        reset();
    }
    
    public RacketController getPlayerC() {return playerC;}    
    public RacketController getPlayerD() {return playerD;}    
    public void setRacketC(double r) {this.racketC = r;}    
    public void setRacketD(double r) {this.racketD = r;}    
    public double getRacketC() {return racketC;}    
    public double getRacketD() {return racketD;}
    public double getBallX2() {return ballX2;}    
    public double getBallY2() {return ballY2;}

    public void update(double deltaT) {
    	super.update(deltaT);
        switch (playerC.getState()) {
        	case GOING_UP:
	            racketC -= getRacketSpeed() * deltaT;
	            if (racketC < -50.00) racketC = -50.00;
	            break;
        	case IDLE:
        		break;
        	case GOING_DOWN:
        		racketC += getRacketSpeed() * deltaT;
        		if (racketC + getRacketSize() > getWidth() - 50.00) racketC = getWidth() - getRacketSize() - 50.00;
        		break;
        }
        switch (playerD.getState()) {
	        case GOING_UP:
	            racketD -= getRacketSpeed() * deltaT;
	            if (racketD < -50.00) racketD = -50.00;
	            break;
	        case IDLE:
	            break;
	        case GOING_DOWN:
	            racketD += getRacketSpeed() * deltaT;
	            if (racketD + getRacketSize() > getWidth() - 50.00) racketD = getWidth() - getRacketSize() - 50.00;
	            break;
        }
    }


    /**
     * @return true if a player lost
     */
    public boolean updateBall(double deltaT) {
        double nextBallX = getBallX() + deltaT * getBallSpeedX();
        double nextBallY = getBallY() + deltaT * getBallSpeedY();
        double nextBallX2 = ballX2 + deltaT * ballSpeedX2;
        double nextBallY2 = ballY2 + deltaT * ballSpeedY2;
        if ((nextBallY < 0 && nextBallX > racketC+50 && nextBallX < racketC+50 + getRacketSize()) 
        || (nextBallY > getHeight() && nextBallX > racketD+50 && nextBallX < racketD+50 + getRacketSize())){
            setBallSpeedY(-getBallSpeedY());
            nextBallY = getBallY() + deltaT * getBallSpeedY();
            nextBallX = getBallX() + ((getBallSpeedX()<0)?-1:+1)*deltaT * (new Random()).nextDouble(Math.abs(getBallSpeedX())); 
        }
        else if (nextBallY < 0 || nextBallY > getHeight()) {
            return true; 
        }
        if ((nextBallX < 0 && nextBallY > getRacketA() && nextBallY < getRacketA() + getRacketSize())
                || (nextBallX > getWidth() && nextBallY > getRacketB() && nextBallY < getRacketB() + getRacketSize())) {
            setBallSpeedX(-getBallSpeedX());
            nextBallX = getBallX() + deltaT * getBallSpeedX();
        }else if (nextBallX < 0) {
            getScore().addScore1();
            if (getScore().endGame() == 1){
                GameView.finGame = true ;
                GameView.endGame(1);
            }
            return true;
        }else if (nextBallX > getWidth()) {
            getScore().addScore2();
            if (getScore().endGame() == 1){
                GameView.finGame = true ;
                GameView.endGame(2);
            }
            return true;
        }
        if ((nextBallY2 < 0 && nextBallX2 > racketC+50 && nextBallX2 < racketC+50 + getRacketSize()) 
        || (nextBallY2 > getHeight() && nextBallX2 > racketD+50 && nextBallX2 < racketD+50 + getRacketSize())){
            ballSpeedY2 = -getBallSpeedY();
            nextBallY2 = ballY2 + deltaT * ballSpeedY2;
            nextBallX2 = ballX2 + ((ballSpeedX2<0)?-1:+1)*deltaT * (new Random()).nextDouble(Math.abs(ballSpeedX2)); 
        }
        else if (nextBallY2 < 0 || nextBallY2 > getHeight()) {
            return true; 
        }
        if ((nextBallX2 < 0 && nextBallY2 > getRacketA() && nextBallY2 < getRacketA() + getRacketSize())
                || (nextBallX2 < 0 && nextBallY2 > racketC && nextBallY2 < racketC + getRacketSize())
                || (nextBallX2 > getWidth() && nextBallY2 > getRacketB() && nextBallY2 < getRacketB() + getRacketSize())
                || (nextBallX2 > getWidth() && nextBallY2 > racketD && nextBallY2 < racketD + getRacketSize())) {
            ballSpeedX2 = -ballSpeedX2;
            nextBallX2 = ballX2 + deltaT * ballSpeedX2;
        }else if (nextBallX2 < 0) {
            getScore().addScore1();
            if (getScore().endGame() == 1){
                GameView.finGame = true ;
                GameView.endGame(1);
            }
            return true;
        }else if (nextBallX2 > getWidth()) {
            getScore().addScore2();
            if (getScore().endGame() == 1){
                GameView.finGame = true ;
                GameView.endGame(2);
            }
            return true;
        }
        ballX2 = nextBallX2;
        ballY2 = nextBallY2;
        setBallX(nextBallX);
        setBallY(nextBallY);
        return false;
    }

    public void reset() {
    	super.reset();
        //this.racketC = getWidth() / 2;
        //this.racketD = getWidth() / 2;        
        this.ballSpeedX2 = (((int)(Math.random()*10))>5)?-200:200;
        this.ballSpeedY2 = (((int)(Math.random()*10))>5)?200:-200;
        this.ballX2 = getWidth() / 6;
        this.ballY2 = getHeight() / 4;
    }
}
