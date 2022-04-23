package Cartes;

import Modele.Artefact;

public class CarteMonteeDesEaux extends CarteTiree{
    private String nom;

    public CarteMonteeDesEaux(){
        super("Montée des eaux");
        this.nom = "Montée des eaux";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
