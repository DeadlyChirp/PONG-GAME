package model;

import javafx.scene.text.Text;

public class CompteurVie {
    public Text s1;
    public Text s2;

    public int limiteVie;

    CompteurVie() {
        s1 = new Text("3");
        s2 = new Text("3");
    }

    public void addCompteurVie1() {
        s1.setText(String.valueOf(Integer.valueOf(s1.getText()) - 1));
    }

    public void addCompteurVie2() {
        s2.setText(String.valueOf(Integer.valueOf(s2.getText()) - 1));
    }

    public void LimiteVie() {
        if (s2.getText().equals("0")) {

        }
    }
}