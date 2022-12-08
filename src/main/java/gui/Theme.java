package gui;

public class Theme {
    public String nom;
    public String fichier;

    public Theme(String nom, String fichier){
        this.nom = nom;
        this.fichier = fichier;
    }

    public static Theme t0 = new Theme("default", "file:src/Pictures/testJaune.png");
    public static Theme t1 = new Theme("Night étoilée", "file:src/Pictures/nightSky.png");
    public static Theme t2 = new Theme("Aurores Boeréales", "file:src/Pictures/boreal.png");
    public static Theme t3 = new Theme("testBleu", "file:src/Pictures/testBleu.png");

}
