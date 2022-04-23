package Cartes;

import Modele.Artefact;

public class CarteHelicoptere extends CarteTiree {
    private String nom;

    public CarteHelicoptere(){
        super("Hélicoptère");
        this.nom = "Hélicoptère";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
