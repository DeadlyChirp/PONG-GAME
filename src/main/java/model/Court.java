package model;

public class Court {
    // instance parameters
    private final RacketController playerA, playerB, playerC, playerD;
    private final double width, height; // m
    private final double racketSpeed = 300.0; // m/s
    private final double racketSize = 100.0; // m
    private final double ballRadius = 10.0; // m
    // instance state
    private double racketA; // m
    private double racketB; // m
    private double racketC; // m
    private double racketD; // m
    private double ballX, ballY; // m
    private double ballSpeedX, ballSpeedY; // m

    public Court(RacketController playerA, RacketController playerB, RacketController playerC, RacketController playerD, double width, double height) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.playerC = playerC;
        this.playerD = playerD;
        this.width = width;
        this.height = height;
        reset();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getRacketSize() {
        return racketSize;
    }

    public double getRacketA() {
        return racketA;
    }

    public double getRacketB() {
        return racketB;
    }
    
    public double getRacketC() {
        return racketC;
    }
    
    public double getRacketD() {
        return racketD;
    }

    public double getBallX() {
        return ballX;
    }

    public double getBallY() {
        return ballY;
    }

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
	            if (racketC < 0.0) racketC = 0.0;
	            break;
        	case IDLE:
        		break;
        	case GOING_DOWN:
        		racketC += racketSpeed * deltaT;
        		if (racketC + racketSize > height) racketC = height - racketSize;
        		break;
        }
        switch (playerD.getState()) {
	        case GOING_UP:
	            racketD -= racketSpeed * deltaT;
	            if (racketD < 0.0) racketD = 0.0;
	            break;
	        case IDLE:
	            break;
	        case GOING_DOWN:
	            racketD += racketSpeed * deltaT;
	            if (racketD + racketSize > height) racketD = height - racketSize;
	            break;
    }
        if (updateBall(deltaT)) reset();
    }


    /**
     * @return true if a player lost
     */
    private boolean updateBall(double deltaT) {
        // first, compute possible next position if nothing stands in the way
        double nextBallX = ballX + deltaT * ballSpeedX;
        double nextBallY = ballY + deltaT * ballSpeedY;
        // next, see if the ball would meet some obstacle
        if (nextBallY < 0 || nextBallY > height) {
            ballSpeedY = -ballSpeedY;
            nextBallY = ballY + deltaT * ballSpeedY;
        }
        if ((nextBallX < 0 && nextBallY > racketA && nextBallY < racketA + racketSize)
        		|| (nextBallX < 0 && nextBallY > racketC && nextBallY < racketC + racketSize)
                || (nextBallX > width && nextBallY > racketB && nextBallY < racketB + racketSize)
                || (nextBallX > width && nextBallY > racketD && nextBallY < racketD + racketSize)) {
            ballSpeedX = -ballSpeedX;
            nextBallX = ballX + deltaT * ballSpeedX;
        } else if (nextBallX < 0) {
            return true;
        } else if (nextBallX > width) {
            return true;
        }
        ballX = nextBallX;
        ballY = nextBallY;
        return false;
    }

    public double getBallRadius() {
        return ballRadius;
    }

    void reset() {
        this.racketA = height / 2;
        this.racketB = height / 2;
        this.ballSpeedX = 200.0;
        this.ballSpeedY = 200.0;
        this.ballX = width / 2;
        this.ballY = height / 2;
    }
}
