// package model;

// import gui.GameView;

// public class VariationRaquette extends Court {

// double racketSizeA;
// double racketSizeB;

// public VariationRaquette(RacketController playerA, RacketController playerB,
// double width, double height,
// int RacketSizeA, int RacketSizeB) {
// super(playerA, playerB, width, height);
// this.racketSizeA = getRacketSize();
// this.racketSizeB = getRacketSize();
// }

// @Override
// public void update(double deltaT) {

// switch (getPlayerA().getState()) {
// case GOING_UP:
// racketA -= getRacketSpeed() * deltaT; // la vitesse de la racket * delta
// if (getRacketA() < 0.0)
// racketA = 0.0; // position racket Y axis 0 cao nhat
// break;
// case IDLE:
// break;
// case GOING_DOWN:
// racketA += racketSpeed * deltaT;
// if (racketA + racketSizeA > height)
// racketA = height - racketSize; //
// break;
// }
// switch (super.playerB.getState()) {
// case GOING_UP:
// racketB -= racketSpeed * deltaT;
// if (racketB < 0.0)
// racketB = 0.0;
// break;
// case IDLE:
// break;
// case GOING_DOWN:
// racketB += racketSpeed * deltaT;
// if (racketB + racketSizeB > height)
// racketB = height - racketSize;
// break;
// }
// if (updateBall(deltaT))
// reset();
// }

// @Override
// public boolean updateBall(double deltaT) {
// // first, compute possible next position if nothing stands in the way
// double nextBallX = ballX + deltaT * ballSpeedX;
// double nextBallY = ballY + deltaT * ballSpeedY;

// // next, see if the ball would meet some obstacle
// if (nextBallY < 0 || nextBallY > height) {
// ballSpeedY = -ballSpeedY; // rebondir
// nextBallY = ballY + deltaT * ballSpeedY;
// } // ? = if
// if ((nextBallX < 0 && nextBallY > racketA && nextBallY < racketA +
// racketSizeB)) {
// GameView.racketB.setHeight(GameView.court.getRacketSize() + 15 *
// GameView.scale);
// racketSizeB += 15;
// ballSpeedX = -ballSpeedX; // rebondir ball x balle tombe dans la raquette
// nextBallX = ballX + deltaT * ballSpeedX;
// } //
// if (nextBallX > width && nextBallY > racketB && nextBallY < racketB +
// racketSizeA) {
// GameView.racketA.setHeight(GameView.court.getRacketSize() + 15 *
// GameView.scale);
// racketSizeA += 15;
// ballSpeedX = -ballSpeedX; // rebondir ball x balle tombe dans la raquette
// nextBallX = ballX + deltaT * ballSpeedX; // nextball = new vitesse next
// position
// } else if (nextBallX < 0) { // if en haut une des condition est fausse, balle
// a pas touche la raquette
// scoreVie.addCompteurVie1();
// return true;
// } else if (nextBallX > width) { // player 2

// scoreVie.addCompteurVie2();
// // racket size reduce size if loose
// return true;

// } else if ((CompteurVie.s2.getText().equals("0") ||
// CompteurVie.s1.getText().equals("0"))) {
// scoreVie.LimiteVie();
// GameView.racketB.setHeight(GameView.court.getRacketSize() * GameView.scale);
// GameView.racketA.setHeight(GameView.court.getRacketSize() * GameView.scale);
// }
// ballX = nextBallX;
// ballY = nextBallY;
// return false;
// }
// }
