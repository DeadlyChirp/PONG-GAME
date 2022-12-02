package model;

public class CourtQuatreJoueurs {
    // instance parameters
    private final RacketController playerA, playerB, playerC, playerD;
    private double width, height; // m
    private double racketSpeed = 300.0; // m/s
    private double racketSize = 100.0; // m
    private double ballRadius = 10.0; // m
    // instance state
    private double racketA; // m
    private double racketB; // m
    private double ballX, ballY; // m
    private double ballSpeedX, ballSpeedY; // m
    private double racketC; // m
    private double racketD; // m

	public CourtQuatreJoueurs(RacketController playerA, RacketController playerB, RacketController playerC,RacketController playerD, double width, double height) {
		this.playerA = playerA;
        this.playerB = playerB;
		this.playerC = playerC;
        this.playerD = playerD;
        this.width = width;
        this.height = height;
        reset();
    }
    
	public double getWidth() { return width;}
	public double getHeight() { return height;}
	public double getRacketSize() { return racketSize;}
	public double getRacketSpeed() {return racketSpeed;}
	public double getBallRadius() {return ballRadius;}
	public double getBallSpeedX() {return ballSpeedX;}
	public double getBallSpeedY() {return ballSpeedY;}
	public double getBallX() {return ballX;}
	public double getBallY() {return ballY;}
	
	public RacketController getPlayerA() { return playerA;}
	public RacketController getPlayerB() { return playerB;}
    public RacketController getPlayerC() { return playerC;}
    public RacketController getPlayerD() { return playerD;}
    
    public void setRacketA(double r) { this.racketA = r;}
    public void setRacketB(double r) { this.racketB = r;}
    public void setRacketC(double r) { this.racketC = r;}
    public void setRacketD(double r) { this.racketD = r;}
    
    public double getRacketA() { return racketA;}
    public double getRacketB() { return racketB;}
    public double getRacketC() { return racketC;}
    public double getRacketD() { return racketD;}
	
    public void update(double deltaT) {
    	switch (playerA.getState()) {
	        case GOING_UP:
	            racketA -= racketSpeed * deltaT;
	            if (racketA < 0.0) racketA = 0.0;
	            break;
	        case IDLE:
	            break;
	        case GOING_DOWN:
	            racketA += racketSpeed * deltaT;
	            if (racketA + racketSize > height) racketA = height - racketSize;
	            break;
	    }
	    switch (playerB.getState()) {
	        case GOING_UP:
	            racketB -= racketSpeed * deltaT;
	            if (racketB < 0.0) racketB = 0.0;
	            break;
	        case IDLE:
	            break;
	        case GOING_DOWN:
	            racketB += racketSpeed * deltaT;
	            if (racketB + racketSize > height) racketB = height - racketSize;
	            break;
	    }
        switch (playerC.getState()) {
        	case GOING_UP:
	            racketC -= racketSpeed * deltaT;
	            if (racketC < 50.0) racketC = 50.0;
	            break;
        	case IDLE:
        		break;
        	case GOING_DOWN:
        		racketC += racketSpeed * deltaT;
        		if (racketC + racketSize - 50.0 > width) racketC = width - racketSize + 50.0;
        		break;
        }
        switch (playerD.getState()) {
	        case GOING_UP:
	            racketD -= racketSpeed * deltaT;
	            if (racketD < 50.0) racketD = 50.0;
	            break;
	        case IDLE:
	            break;
	        case GOING_DOWN:
	            racketD += racketSpeed * deltaT;
	            if (racketD + racketSize - 50.0 > width) racketD = width - racketSize + 50.0;
	            break;
        }
        if (updateBall(deltaT)) reset();
    }
    public boolean updateBall(double deltaT) {
        // first, compute possible next position if nothing stands in the way
        double nextBallX = ballX + deltaT * ballSpeedX;
        double nextBallY = ballY + deltaT * ballSpeedY;
        // next, see if the ball would meet some obstacle
        if (nextBallY < 0 && nextBallX > racketC && nextBallX < racketC + racketSize 
    	|| nextBallY > height && nextBallX > racketD && nextBallX < racketD + racketSize) {
            ballSpeedY = -ballSpeedY;
            nextBallY = ballY + deltaT * ballSpeedY;
        }
        if ((nextBallX < 0 && nextBallY > racketA && nextBallY < racketA + racketSize)
                || (nextBallX > width && nextBallY > racketB && nextBallY < racketB + racketSize)) {
            ballSpeedX = -ballSpeedX;
            nextBallX = ballX + deltaT * ballSpeedX;
        } else if (nextBallX < 0 || nextBallY < 0) {
            return true;
        } else if (nextBallX > width || nextBallY > height) {
            return true;
        }
        ballX = nextBallX;
        ballY = nextBallY;
        return false;
    }
    
    public void reset() {
    	this.racketA = height / 2;
        this.racketB = height / 2;
        this.racketC = width / 2;
        this.racketD = width / 2;
        this.ballSpeedX = 200.0;
        this.ballSpeedY = 200.0;
        this.ballX = width / 2;
        this.ballY = height / 2;
    }
}
