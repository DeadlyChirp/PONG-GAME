package model;

import gui.GameView;
import model.Court;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class CompteurVie extends Court {
    public static Text s1;
    public static Text s2;

    public int limiteVie;

    public CompteurVie(RacketController playerA, RacketController playerB, double width, double height, int t) {
        super(playerA, playerB, width, height, t);
    }

    public void addCompteurVie1() {
        s1.setText(String.valueOf(Integer.valueOf(s1.getText()) - 1));
    }

    public void addCompteurVie2() {
        s2.setText(String.valueOf(Integer.valueOf(s2.getText()) - 1));
    }

    public void LimiteVie() {
    }

    public void setFill(Color valueOf) {
    }
}