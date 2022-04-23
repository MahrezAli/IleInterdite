package Modele;

import Utils.Etat;

import java.util.ArrayList;

public class Zone {
    private Grille grille;
    private final int x, y;
    private String nom;
    private Etat eTat;
    private ArrayList<Joueur> j = new ArrayList<>();
    private Artefact artefact;
    private boolean isSelected = false;

    public Zone(Grille grille, int x, int y, String n) {
        this.grille = grille;
        this.x = x; this.y = y;
        this.nom = n;
        this.eTat = Etat.Normale;
        this.artefact = null;
    }



    public void aJoueur(Joueur j) {
        this.j.add(j);
    }
    public void enleveJoueur(Joueur j) {
        this.j.remove(j);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public boolean containsPlayer(){
        if(this.j.size() > 0){
            return true;
        }
        else{
            return false;
        }
    }
    public ArrayList<Joueur> getJ() {
        return this.j;
    }

    public Etat geteTat() {
        return this.eTat;
    }

    public void seteTat(Etat eTat) {
        this.eTat = eTat;
    }

    public Grille getGrille() {
        return grille;
    }

    public ArrayList<Zone> getZonesAdjacentes(Grille g){
        ArrayList<Zone>areZonesAdjacentes = g.zoneAdjacentes(this);
        return areZonesAdjacentes;
    }
    public String getName(){
        return this.nom;
    }

    public Artefact getArtefact() {
        return this.artefact;
    }
    public boolean isArtefact(){
        if(this.getArtefact() == null){
            return false;
        }else{
            return true;
        }
    }

    public void setArtefact(Artefact artefact) {
        this.artefact = artefact;
    }

    public boolean isZonePossible(Integer i, Integer j){
        boolean res = true;
        if((i==0 && j==0)||(i==0&&j==1)||(i==0&&j==4)||(i==0&&j==5)||(i==1 && j==0)||(i==1&&j==5)||(i==4&&j==0)||(i==4&&j==5)||(i==5 && j==0)||(i==5&&j==1)||(i==5&&j==4)||(i==5&&j==5)){
            res = false;
        }
        return res;
    }
    public boolean getIsSelected(){
        return this.isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}