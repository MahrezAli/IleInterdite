package Modele;


import Cartes.*;
import Controler.Observables;
import Utils.Etat;
import Utils.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class Grille extends Observables {
    public static final int HAUTEUR=6, LARGEUR=6;
    private Zone[][] zones;
    private int nbJoueurs;
    private ArrayList<CarteTiree> pileCartes;
    private ArrayList<CarteTiree> defausseCartes;
    private ArrayList<Artefact> artefacts;

    public Grille(int nbJoueurs) {
        this.zones = new Zone[LARGEUR][HAUTEUR];
        this.nbJoueurs = nbJoueurs;
        ArrayList<String> nomZone = this.nomZones();
        int numNomZone = 0;
        for(int i=0; i<LARGEUR; i++) {
            for(int j=0; j<HAUTEUR; j++) {
                if(isZonePossible(i,j)) {
                    this.zones[i][j] = new Zone(this, i, j, nomZone.get(numNomZone));
                    numNomZone++;
                }
                else if(zonePlacerArtefact(i, j)){
                    this.zones[i][j] = new Zone(this, i, j, "");
                }
            }
        }
        this.artefacts = new ArrayList<>();
        this.artefacts.add(Artefact.CALICE);
        this.artefacts.add(Artefact.CRISTAL);
        this.artefacts.add(Artefact.PIERRE);
        this.artefacts.add(Artefact.ZEPHYR);
        this.pileCartes = this.initCarte();
        this.defausseCartes = new ArrayList<>();
        placeArtefacts();
        ajouterLesJoueurs();
    }

    int genererInt(int borneInf, int borneSup){
        Random random = new Random();
        int nb;
        nb = borneInf+random.nextInt(borneSup-borneInf);
        return nb;
    }

    public void inonde() {
        int compteur = 0;
        int i;
        int j;
        while(compteur < 1){
            i = genererInt(0,LARGEUR);
            j = genererInt(0,HAUTEUR);
            if(isZonePossible(i, j) && this.zones[i][j].geteTat() == Etat.Normale) {
                this.zones[i][j].seteTat(Etat.inondee);
                compteur++;
            }
            else if(isZonePossible(i, j) && this.zones[i][j].geteTat() == Etat.inondee) {
                this.zones[i][j].seteTat(Etat.submergee);
                compteur++;
            }
        }
        notifyObservers();
    }

    public Zone getZone(int x, int y) {
        if(x >= 0 && y >=0 && x < LARGEUR && y < LARGEUR) {
            return this.zones[x][y];
        }
        return null;
    }

    public boolean isZonePossible(Integer i, Integer j){
        boolean res = true;
        if((i==0 && j==0)||(i==0&&j==1)||(i==0&&j==4)||(i==0&&j==5)||(i==1 && j==0)||(i==1&&j==5)||(i==4&&j==0)||(i==4&&j==5)||(i==5 && j==0)||(i==5&&j==1)||(i==5&&j==4)||(i==5&&j==5)){
            res = false;
        }
        return res;
    }
    public boolean zonePlacerArtefact(Integer i, Integer j){
        if((i==0 && j==0)||(i==0&&j==5)||(i==5 && j==0)||(i==5&&j==5)){
            return true;
        }
        return false;
    }

    public ArrayList<Zone> zoneAdjacentes(Zone z){
        ArrayList<Zone>sontAdjacentes = new ArrayList<>();
        if(isZonePossible(z.getX()-1,z.getY()) && this.getZone(z.getX()-1,z.getY()) != null){
            sontAdjacentes.add(this.zones[z.getX()-1][z.getY()]);
        }
        if(isZonePossible(z.getX()+1,z.getY()) && this.getZone(z.getX()+1,z.getY()) != null){
            sontAdjacentes.add(this.zones[z.getX()+1][z.getY()]);
        }
        if(isZonePossible(z.getX(),z.getY()-1) && this.getZone(z.getX(),z.getY()-1) != null){
            sontAdjacentes.add(this.zones[z.getX()][z.getY()-1]);
        }
        if(isZonePossible(z.getX(),z.getY()+1) && this.getZone(z.getX(),z.getY()+1) != null){
            sontAdjacentes.add(this.zones[z.getX()][z.getY()+1]);
        }
        return sontAdjacentes;
    }
    public HashMap<Integer, Joueur> getJoueur(){
        HashMap<Integer, Joueur> result = new HashMap<>();
        int compt = 0;
        while(compt < this.nbJoueurs){
            result.put(compt+1, this.getJoueur(compt+1));
            compt++;
        }
        return result;
    }

    public int getNbJoueurs() {
        return nbJoueurs;
    }
    public void ajouterLesJoueurs(){
        int compteur = 0;
        ArrayList<Role> roleJoueur = roles();
        while(compteur < this.getNbJoueurs()) {
            int i = genererInt(0,HAUTEUR);
            int j = genererInt(0,LARGEUR);
            while(!isZonePossible(i, j) || this.zones[i][j].containsPlayer()){
                i = genererInt(0,HAUTEUR);
                j = genererInt(0,LARGEUR);
            }
            int x = genererInt(0, roleJoueur.size());
            Joueur s = new Joueur("Nom_Joueur ;)", this.zones[i][j]);
            s.setId(compteur+1);
            s.setRole(roleJoueur.get(x));
            roleJoueur.remove(x);
            this.zones[i][j].aJoueur(s);
            compteur++;
        }
    }


    ArrayList<String>nomZones(){
        ArrayList<String>result = new ArrayList<>();
        String a = "Le Pont des Abîmes";
        result.add(a);
        String b = "La Porte de Bronze";
        result.add(b);
        String c = "La Caverne des Ombres";
        result.add(c);
        String d = "La Porte de Fer";
        result.add(d);
        String e = "La Porte d'Or";
        result.add(e);
        String f = "Les Falaises de l'Oubli";
        result.add(f);
        String g = "Le Palais de Corail";
        result.add(g);
        String h = "La Porte d'Argent";
        result.add(h);
        String i = "Les Dunes de l'Illusion";
        result.add(i);
        String j = "Heliport";
        result.add(j);
        String k = "La Porte de Cuivre";
        result.add(k);
        String l = "Le Jardin des Hurlements";
        result.add(l);
        String m = "La Forêt Pourpre";
        result.add(m);
        String n = "Le Lagon Perdu";
        result.add(n);
        String o = "Le Marais Brumeux";
        result.add(o);
        String p = "Observatoire";
        result.add(p);
        String q = "Le Rocher Fantôme";
        result.add(q);
        String r = "La Caverne du Brasier";
        result.add(r);
        String s = "Le Temple du Soleil";
        result.add(s);
        String t = "Le Temple de la Lune";
        result.add(t);
        String u = "Le Palais des Marées";
        result.add(u);
        String v = "Le Val du Crépuscule";
        result.add(v);
        String w = "La Tour du Guet";
        result.add(w);
        String x = "Le Jardin des Murmures";
        result.add(x);
        ArrayList<String> ZoneMelangees = new ArrayList<>();
        while(result.size() > 0){
            int z = genererInt(0, result.size());
            ZoneMelangees.add(result.get(z));
            result.remove(result.get(z));
        }
        return ZoneMelangees;
    }
    public void placeArtefacts(){
        for(int i = 0; i < this.artefacts.size(); i++){
            int x = genererInt(0,HAUTEUR);
            int y = genererInt(0,LARGEUR);
            while(!zonePlacerArtefact(x,y) || this.getZone(x,y).isArtefact()){
                x = genererInt(0,HAUTEUR);
                y = genererInt(0,LARGEUR);
            }
            this.zones[x][y].setArtefact(this.artefacts.get(i));
        }
    }

    public ArrayList<Artefact> getArtefacts() {
        return artefacts;
    }

    public ArrayList<CarteTiree> initCarte(){
        ArrayList<CarteTiree> crts = new ArrayList<>();
        for(int i = 0; i < 28; i++){
            if(i < 5){
                crts.add(new CarteTresor(Artefact.CRISTAL));
            }
            if(i >= 5 && i < 10){
                crts.add(new CarteTresor(Artefact.CALICE));
            }
            if(i >= 10 && i < 15){
                crts.add(new CarteTresor(Artefact.ZEPHYR));
            }
            if(i >= 15 && i < 20){
                crts.add(new CarteTresor(Artefact.PIERRE));
            }
            if(i >= 20 && i < 23){
                crts.add(new CarteHelicoptere());
            }
            if(i >= 23 && i < 25){
                crts.add(new CarteSacDeSable());
            }
            if(i >= 25){
                crts.add(new CarteMonteeDesEaux());
            }
        }
        ArrayList<CarteTiree> cartesMelangees = new ArrayList<>();
        while(crts.size() > 0){
            int i = genererInt(0, crts.size());
            cartesMelangees.add(crts.get(i));
            crts.remove(crts.get(i));
        }
        return cartesMelangees;
    }

    public ArrayList<CarteTiree> getPileCartes() {
        return this.pileCartes;
    }
    public Zone getZoneParNom(String s){
        for(int i = 0; i < this.LARGEUR; i++){
            for(int j = 0; j < this.HAUTEUR; j++){
                if(this.zones[i][j] != null && this.zones[i][j].getName() == s){
                    return this.zones[i][j];
                }
            }
        }
        return null;
    }

    public ArrayList<Joueur> getJoueurs(){
        ArrayList<Joueur> result = new ArrayList<>();
        int compt= 0;
        while(compt < this.nbJoueurs){
            result.add(this.getJoueur(compt+1));
            compt++;
        }
        return result;
    }
    public Zone getZoneArtefact(Artefact a){
        for(int i = 0; i < this.LARGEUR; i++){
            for(int j = 0; j < this.HAUTEUR; j++){
                if(this.zonePlacerArtefact(i,j) && this.zones[i][j].getArtefact() == a){
                    return this.zones[i][j];
                }
            }
        }
        return null;
    }

    public ArrayList<CarteTiree> getDefausseCartes() {
        return defausseCartes;
    }
    public ArrayList<CarteTiree> melangeDefausse(){
        ArrayList<CarteTiree> result = new ArrayList<>();
        while(this.getDefausseCartes().size() > 0){
            int i = genererInt(0, this.getDefausseCartes().size());
            result.add(this.getDefausseCartes().get(i));
            this.getDefausseCartes().remove(this.getDefausseCartes().get(i));
        }
        return result;
    }

    public void setPileCartes(ArrayList<CarteTiree> pileCartes) {
        this.pileCartes = pileCartes;
    }

    public void setDefausseCartes(ArrayList<CarteTiree> defausseCartes) {
        this.defausseCartes = defausseCartes;
    }
    public Joueur getJoueur(int id){
        for(int i = 0; i < Grille.LARGEUR; i++){
            for(int j = 0; j < Grille.LARGEUR; j++){
                if(this.isZonePossible(i,j) && this.zones[i][j].getJ().size() > 0){
                    for(int k = 0; k < this.zones[i][j].getJ().size(); k++){
                        if(this.zones[i][j].getJ().get(k).getId() == id){
                            return this.zones[i][j].getJ().get(k);
                        }
                    }
                }
            }
        }
        return null;
    }
    public ArrayList<Role> roles(){
        ArrayList<Role> result = new ArrayList<>();
        result.add(Role.PILOTE);
        result.add(Role.PLONGEUR);
        result.add(Role.EXPLORATEUR);
        result.add(Role.NAVIGATEUR);
        result.add(Role.MESSAGER);
        result.add(Role.INGENIEUR);
        return result;
    }

}












