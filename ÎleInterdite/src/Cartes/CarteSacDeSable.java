package Cartes;

import Modele.Artefact;

public class CarteSacDeSable extends CarteTiree{
    private String nom;

    public CarteSacDeSable(){
        super("Sacs De Sable");
        this.nom = "Sacs De Sable";

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
