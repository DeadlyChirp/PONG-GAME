package model;

import java.util.Random;

import gui.GameView;
import model.Court;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Score;

public class CompteurVie extends Court {
    private Text s1;
    private Text s2;
    private int compt = 3;
    private int compt4 = 4;
    private int compt2 = 2;
    private int compt5 = 5;
    private int compt1 = 1;
    private int comptad = 3;
    private int compt4ad = 4;
    private int compt2ad = 2;
    private int compt5ad = 5;
    private int compt1ad = 1;

    public int limiteVie;

    public CompteurVie(RacketController playerA, RacketController playerB, double width, double height, int t) {
        super(playerA, playerB, width, height, t);
        s1 = new Text();
        s2 = new Text();
        limiteVie = t;

        s1.setText(String.valueOf(t));
        s2.setText(String.valueOf(t));
    }

    public void restart() {
        s1.setText(String.valueOf(limiteVie));
        s2.setText(String.valueOf(limiteVie));
    }

    public Text getS1() {
        return s1;
    }

    public Text getS2() {
        return s2;
    }

    public int desCompt() {
        return compt--;
    }

    public int desComptad() {
        return comptad--;
    }

    public int desCompt2ad() {
        return compt2ad--;
    }

    public int desCompt4ad() {
        return compt4ad--;
    }

    public int desCompt1ad() {
        return compt1ad--;
    }

    public int desCompt4() {
        return compt4--;
    }

    public int desCompt5() {
        return compt5--;
    }

    public int desCompt2() {
        return compt2--;
    }

    public int desCompt1() {
        return compt1--;
    }

    public boolean decreaseCompteurVie1() {
        s1.setText(String.valueOf(Integer.valueOf(s1.getText()) - 1));
        return true;
    }

    public boolean decreaseCompteurVie2() {
        s2.setText(String.valueOf(Integer.valueOf(s2.getText()) - 1));
        return true;
    }

    public boolean upCompteurVie1() {
        s1.setText(String.valueOf(Integer.valueOf(s1.getText()) + 1));
        return true;
    }

    public boolean upCompteurVie2() {
        s2.setText(String.valueOf(Integer.valueOf(s2.getText()) + 1));
        return true;
    }

    // public void LimiteVie() {
    // if (s2.getText().equals("0") || s1.getText().equals("0")) {
    // }
    // }
    // public boolean pVie(double deltaT) {
    // double nextBallX = getBallX() + deltaT * getBallSpeedX();
    // double nextBallY = getBallY() + deltaT * getBallSpeedY();
    // if (nextBallX > this.getWidth()) {
    // decreaseCompteurVie1();
    // }
    // return true;
    // }

    public boolean updateBall(double deltaT) {

        // first, compute possible next position if nothing stands in the way
        double nextBallX = getBallX() + deltaT * getBallSpeedX();
        double nextBallY = getBallY() + deltaT * getBallSpeedY();
        // next, see if the ball would meet some obstacle
        if (nextBallY < 0 || nextBallY > getHeight()) {
            setBallSpeedY(-getBallSpeedY());
            nextBallY = getBallY() + deltaT * getBallSpeedY();
            nextBallX = getBallX()
                    + ((getBallSpeedX() < 0) ? -1 : +1) * deltaT * (new Random()).nextDouble(Math.abs(getBallSpeedX()));
        }

        if ((nextBallX < 0 && nextBallY > getRacketA() && nextBallY < getRacketA() + getRacketSize())
                || (nextBallX > getWidth() && nextBallY > getRacketB() && nextBallY < getRacketB() + getRacketSize())) {
            setBallSpeedX(-getBallSpeedX());
            nextBallX = getBallX() + deltaT * getBallSpeedX();
            nextBallY = getBallY()
                    + ((getBallSpeedY() < 0) ? -1 : +1) * deltaT * (new Random()).nextDouble(Math.abs(getBallSpeedY()));

        } else if (nextBallX < 0 && limiteVie == 3) {
            // decreaseCompteurVie2();
            if (compt == 3) {
                decreaseCompteurVie1();
                // GameView.coeur3.setImage(null);
                GameView.coeur3.setVisible(false);
            }
            // GameView.coeur3.setImage(null);
            if (compt == 2) {
                decreaseCompteurVie1();
                GameView.coeur2.setVisible(false);
            }
            if (compt == 1) {
                decreaseCompteurVie1();
                GameView.coeur.setVisible(false);

            }
            if (s1.getText().equals("0")) {
                GameView.finGame = true;
                GameView.endGame(2);
            }
            if (s2.getText().equals("0")) {
                GameView.finGame = true;
                GameView.endGame(1);
            }

            desCompt();
            return true;
        } else if (nextBallX < 0 && limiteVie == 4) {
            // decreaseCompteurVie2();
            if (compt4 == 4) {
                decreaseCompteurVie1();
                GameView.coeur4.setVisible(false);
            }
            // GameView.coeur3.setImage(null);
            if (compt4 == 3) {
                decreaseCompteurVie1();
                GameView.coeur3.setVisible(false);

            }
            if (compt4 == 2) {
                decreaseCompteurVie1();
                GameView.coeur2.setVisible(false);

            }
            if (compt4 == 1) {
                decreaseCompteurVie1();
                GameView.coeur.setVisible(false);

            }
            if (s1.getText().equals("0")) {
                GameView.finGame = true;
                GameView.endGame(2);
            }
            if (s2.getText().equals("0")) {
                GameView.finGame = true;
                GameView.endGame(1);
            }
            desCompt4();
            return true;
        } else if (nextBallX < 0 && limiteVie == 2) {
            // decreaseCompteurVie2();
            if (compt2 == 2) {
                decreaseCompteurVie1();
                GameView.coeur2.setVisible(false);
            }
            // GameView.coeur3.setImage(null);
            if (compt2 == 1) {
                decreaseCompteurVie1();
                GameView.coeur.setVisible(false);
            }
            if (s1.getText().equals("0")) {
                GameView.finGame = true;
                this.getScore().reset();
                restart();
                GameView.coeur.setVisible(true);
                GameView.coeur2.setVisible(true);
                GameView.coeurad.setVisible(true);
                GameView.coeur2ad.setVisible(true);
                GameView.endGame(2);
            }
            if (s2.getText().equals("0")) {
                GameView.finGame = true;
                this.getScore().reset();
                restart();
                GameView.coeur.setVisible(true);
                GameView.coeur2.setVisible(true);
                GameView.coeurad.setVisible(true);
                GameView.coeur2ad.setVisible(true);
                GameView.endGame(1);
            }
            desCompt2();
            return true;
        } else if (nextBallX < 0 && limiteVie == 1) {
            // decreaseCompteurVie2();
            if (compt1 == 1) {
                decreaseCompteurVie1();
                GameView.coeur.setVisible(false);
            }
            // GameView.coeur3.setImage(null);
            if (s1.getText().equals("0")) {
                GameView.finGame = true;
                GameView.endGame(2);

            }
            if (s2.getText().equals("0")) {
                GameView.finGame = true;
                GameView.endGame(1);
            }
            desCompt1();
            return true;
        } else if (nextBallX > this.getWidth() && limiteVie == 4) {
            if (compt4ad == 4) {
                decreaseCompteurVie2();
                GameView.coeur4ad.setVisible(false);
            }
            // GameView.coeur3.setImage(null);
            if (compt4ad == 3) {
                decreaseCompteurVie2();
                GameView.coeur3ad.setVisible(false);
            }
            if (compt4ad == 2) {
                decreaseCompteurVie2();
                GameView.coeur2ad.setVisible(false);

            }
            if (compt4ad == 1) {
                decreaseCompteurVie2();
                GameView.coeurad.setVisible(false);

            }
            if (s2.getText().equals("0")) {
                GameView.finGame = true;
                GameView.endGame(1);
            }
            if (s1.getText().equals("0")) {
                GameView.finGame = true;
                GameView.endGame(2);
            }
            desCompt4ad();
            return true;
        } else if (nextBallX > this.getWidth() && limiteVie == 3) {
            if (comptad == 3) {
                System.out.print("Compt 3");
                decreaseCompteurVie2();
                GameView.coeur3ad.setVisible(false);
            }
            // GameView.coeur3.setImage(null);
            if (comptad == 2) {
                decreaseCompteurVie2();
                GameView.coeur2ad.setVisible(false);
                ;
            }
            if (comptad == 1) {
                decreaseCompteurVie2();
                GameView.coeurad.setVisible(false);

            }
            if (s2.getText().equals("0")) {
                GameView.finGame = true;
                GameView.endGame(1);
            }
            if (s1.getText().equals("0")) {
                GameView.finGame = true;
                GameView.endGame(2);
            }
            desComptad();
            return true;
        } else if (nextBallX > this.getWidth() && limiteVie == 2) {
            if (compt2ad == 2) {
                decreaseCompteurVie2();
                GameView.coeur2ad.setVisible(false);
            }
            // GameView.coeur3.setImage(null);
            if (compt2ad == 1) {
                decreaseCompteurVie2();
                GameView.coeurad.setVisible(false);
                ;
            }
            if (s2.getText().equals("0")) {
                GameView.finGame = true;
                this.getScore().reset();
                restart();
                GameView.coeurad.setVisible(true);
                GameView.coeur2ad.setVisible(true);
                GameView.coeur.setVisible(true);
                GameView.coeur2.setVisible(true);
                GameView.endGame(1);
            }
            if (s1.getText().equals("0")) {
                GameView.finGame = true;
                this.getScore().reset();
                restart();
                GameView.coeurad.setVisible(true);
                GameView.coeur2ad.setVisible(true);
                GameView.coeur.setVisible(true);
                GameView.coeur2.setVisible(true);
                GameView.endGame(2);
            }
            desCompt2ad();
            return true;
        } else if (nextBallX > this.getWidth() && limiteVie == 1) {
            if (compt1ad == 1) {
                decreaseCompteurVie2();
                GameView.coeurad.setVisible(false);
            }
            // GameView.coeur3.setImage(null);
            if (s2.getText().equals("0")) {
                GameView.finGame = true;
                GameView.endGame(1);
            }
            if (s1.getText().equals("0")) {
                GameView.finGame = true;
                GameView.endGame(2);
            }
            desCompt1ad();
            return true;
        }
        // if (limiteVie == 1) {
        // if (nextBallX < 0) {
        // GameView.coeur.setImage(null);
        // }
        // }

        this.setBallX(nextBallX);
        this.setBallY(nextBallY);
        return false;
    }
}
