package Modele;

import java.awt.*;

public enum Artefact {
    PIERRE("Pierre", new Color(	160,82,45)),
    ZEPHYR("Zéphyr", new Color(255,215,0)),
    CRISTAL("Cristal", new Color(	255,0,0)),
    CALICE("Calice", new Color(0,191,255)) ;

    String nom;
    Color bgColor ;

    Artefact(String libelle, Color bgColor) {
        this.nom = libelle;
        this.bgColor = bgColor ;
    }


    public String getNom() {
        return this.nom;
    }

    public Color getBgColor() {
        return this.bgColor ;
    }
}
