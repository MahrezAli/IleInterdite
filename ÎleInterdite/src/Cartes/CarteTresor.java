package Cartes;

import Modele.Artefact;

public class CarteTresor extends CarteTiree {
    private String nom;
    private Artefact artefact;

    public CarteTresor(Artefact a){
        super(a.getNom());
        this.nom = a.getNom();
        this.artefact = a;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public Artefact getArtefact(){
        return this.artefact;
    }
    public void setArtefact(Artefact a){
        this.artefact = a;
    }



}
