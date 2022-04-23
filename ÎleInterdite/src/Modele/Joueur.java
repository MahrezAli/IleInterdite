package Modele;

import Cartes.CarteTiree;
import Utils.Direction;
import Utils.Etat;
import Utils.Role;

import java.util.ArrayList;

public class Joueur {
    private String nom;
    private Zone zoneJoueur;
    private int id;
    private int nbActions;
    private ArrayList<CarteTiree> cartes;
    private ArrayList<Artefact> artefacts;
    private Role role;

    public Joueur(String n, Zone z){
        this.nom = n;
        this.zoneJoueur = z;
        this.nbActions = 3;
        this.cartes = new ArrayList<>();
        this.artefacts = new ArrayList<>();
    }


    public void deplace(Grille g, Direction d){
        if(d == Direction.up && g.getZone(this.zoneJoueur.getX(), this.zoneJoueur.getY()-1) != null && this.zoneJoueur.getY() > 0){
            setZone(g.getZone(this.zoneJoueur.getX(), this.zoneJoueur.getY()-1));
        }
        if(d == Direction.down && g.getZone(this.zoneJoueur.getX(), this.zoneJoueur.getY()+1) != null && this.zoneJoueur.getY() < Grille.HAUTEUR){
            setZone(g.getZone(this.zoneJoueur.getX(), this.zoneJoueur.getY()+1));
        }
        if(d == Direction.left && g.getZone(this.zoneJoueur.getX()-1, this.zoneJoueur.getY()) != null && this.zoneJoueur.getX() > 0){
            setZone(g.getZone(this.zoneJoueur.getX()-1, this.zoneJoueur.getY()));
        }
        if(d == Direction.right && g.getZone(this.zoneJoueur.getX()+1, this.zoneJoueur.getY()) != null && this.zoneJoueur.getX() < Grille.LARGEUR){
            setZone(g.getZone(this.zoneJoueur.getX()+1, this.zoneJoueur.getY()));
        }
    }
    public void decremAction(){
        this.nbActions--;
    }

    //getters:

    public String getNom() {
        return this.nom;
    }

    public Zone getZoneJoueur() {
        return this.zoneJoueur;
    }

    public int getId(){
        return this.id;
    }

    public int getNbActions() {
        return nbActions;
    }
    //setters:

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setZone(Zone zone) {
        this.zoneJoueur.enleveJoueur(this);
        zone.aJoueur(this);
        this.zoneJoueur = zone;
    }

    public void setNbActions(int nbActions) {
        this.nbActions = nbActions;
    }

    public ArrayList<Zone> zonesInondee(){
        ArrayList<Zone>resultat = new ArrayList<>();
        ArrayList<Zone> zonesAdjacantes = this.zoneJoueur.getZonesAdjacentes(this.zoneJoueur.getGrille());
        if(this.zoneJoueur.geteTat() == Etat.inondee){
            zonesAdjacantes.add(this.zoneJoueur);
        }
        for(int i = 0; i < zonesAdjacantes.size(); i++){
            if(zonesAdjacantes.get(i).geteTat() == Etat.inondee){
                resultat.add(zonesAdjacantes.get(i));
            }
        }
        return resultat;
    }

    public void asseche(Zone z){
        if(this.zoneJoueur.getGrille().isZonePossible(z.getX(),z.getY()) && z.geteTat() == Etat.inondee){
            z.seteTat(Etat.Normale);
            this.decremAction();
        }
    }

    public void addCle(CarteTiree c){
        if(this.cartes.size() < 5) {
            this.cartes.add(c);
        }
    }

    public boolean prendreArtefact(){
        if(this.cartes.size() > 0 && (this.nbCleesCalice() >= 4 || this.nbCleesZephir() >= 4 || this.nbCleesPierre() >= 4 || this.nbCleesCristal() >= 4)) {
            if(this.nbCleesCalice() >= 4 && (this.zoneJoueur.getName() == "Le Palais de Corail" || this.zoneJoueur.getName() == "Le Palais des Marées")) {
                this.artefacts.add(Artefact.CALICE);
                if (this.nbCleesCalice() == 4){
                    while (this.cartes.size() > 0) {
                        if (this.cartes.get(this.cartes.size() - 1).getNom() == Artefact.CALICE.getNom()) {
                            this.zoneJoueur.getGrille().getDefausseCartes().add(this.cartes.get(this.cartes.size() - 1));
                            this.cartes.remove(this.cartes.get(this.cartes.size() - 1));
                        }
                    }
                }
                else{
                    while (this.cartes.size() > 1) {
                        if (this.cartes.get(this.cartes.size() - 1).getNom() == Artefact.CALICE.getNom()) {
                            this.zoneJoueur.getGrille().getDefausseCartes().add(this.cartes.get(this.cartes.size() - 1));
                            this.cartes.remove(this.cartes.get(this.cartes.size() - 1));
                        }
                    }
                }
                this.zoneJoueur.getGrille().getArtefacts().remove(Artefact.CALICE);
                this.zoneJoueur.getGrille().getZoneArtefact(Artefact.CALICE).setArtefact(null);
                return true;
            }
            else if(this.nbCleesZephir() >= 4 && (this.zoneJoueur.getName() == "Le Jardin des Murmures" || this.zoneJoueur.getName() ==  "Le Jardin des Hurlements")){
                this.artefacts.add(Artefact.ZEPHYR);
                if (this.nbCleesZephir() == 4){
                    while (this.cartes.size() > 0) {
                        if (this.cartes.get(this.cartes.size() - 1).getNom() == Artefact.ZEPHYR.getNom()) {
                            this.zoneJoueur.getGrille().getDefausseCartes().add(this.cartes.get(this.cartes.size() - 1));
                            this.cartes.remove(this.cartes.get(this.cartes.size() - 1));
                        }
                    }
                }
                else{
                    while (this.cartes.size() > 1) {
                        if (this.cartes.get(this.cartes.size() - 1).getNom() == Artefact.ZEPHYR.getNom()) {
                            this.zoneJoueur.getGrille().getDefausseCartes().add(this.cartes.get(this.cartes.size() - 1));
                            this.cartes.remove(this.cartes.get(this.cartes.size() - 1));
                        }
                    }
                }
                this.zoneJoueur.getGrille().getArtefacts().remove(Artefact.ZEPHYR);
                this.zoneJoueur.getGrille().getZoneArtefact(Artefact.ZEPHYR).setArtefact(null);
                return true;
            }
            else if(this.nbCleesPierre() >= 4 && (this.zoneJoueur.getName() =="Le Temple de la Lune" || this.zoneJoueur.getName() =="Le Temple du Soleil")){
                this.artefacts.add(Artefact.PIERRE);
                if (this.nbCleesPierre() == 4){
                    while (this.cartes.size() > 0) {
                        if (this.cartes.get(this.cartes.size() - 1).getNom() == Artefact.PIERRE.getNom()) {
                            this.zoneJoueur.getGrille().getDefausseCartes().add(this.cartes.get(this.cartes.size() - 1));
                            this.cartes.remove(this.cartes.get(this.cartes.size() - 1));
                        }
                    }
                }
                else{
                    while (this.cartes.size() > 1) {
                        if (this.cartes.get(this.cartes.size() - 1).getNom() == Artefact.PIERRE.getNom()) {
                            this.zoneJoueur.getGrille().getDefausseCartes().add(this.cartes.get(this.cartes.size() - 1));
                            this.cartes.remove(this.cartes.get(this.cartes.size() - 1));
                        }
                    }
                }
                this.zoneJoueur.getGrille().getArtefacts().remove(Artefact.PIERRE);
                this.zoneJoueur.getGrille().getZoneArtefact(Artefact.PIERRE).setArtefact(null);
                return true;
            }
            else if(this.nbCleesCristal() >= 4 && (this.zoneJoueur.getName() == "La Caverne du Brasier" || this.zoneJoueur.getName() == "La Caverne des Ombres")){
                this.artefacts.add(Artefact.CRISTAL);
                if (this.nbCleesCristal() == 4){
                    while (this.cartes.size() > 0) {
                        if (this.cartes.get(this.cartes.size() - 1).getNom() == Artefact.CRISTAL.getNom()) {
                            this.zoneJoueur.getGrille().getDefausseCartes().add(this.cartes.get(this.cartes.size() - 1));
                            this.cartes.remove(this.cartes.get(this.cartes.size() - 1));
                        }
                    }
                }
                else{
                    while (this.cartes.size() > 1) {
                        if (this.cartes.get(this.cartes.size() - 1).getNom() == Artefact.CRISTAL.getNom()) {
                            this.zoneJoueur.getGrille().getDefausseCartes().add(this.cartes.get(this.cartes.size() - 1));
                            this.cartes.remove(this.cartes.get(this.cartes.size() - 1));
                        }
                    }
                }
                this.zoneJoueur.getGrille().getArtefacts().remove(Artefact.CRISTAL);
                this.zoneJoueur.getGrille().getZoneArtefact(Artefact.CRISTAL).setArtefact(null);
                return true;
            }
        }
        return false;
    }



    public ArrayList<Artefact> getArtefacts() {
        return artefacts;
    }

    public String toStringArtefact(){
        String result = "";
        if (this.artefacts.size()>0){
            for(int i = 0; i < this.artefacts.size(); i++){
                if(i < this.artefacts.size()-1){
                    result = "<html>" + result + this.artefacts.get(i).getNom() + ", <br>";
                }
                else{
                    result = result + this.artefacts.get(i).getNom() +".</html>";
                }
            }
        }
        return result;
    }
    public int nbCleesPierre(){
        int compteur = 0;
        for(int i = 0; i < this.cartes.size(); i++){
            if(this.cartes.get(i).getNom() == Artefact.PIERRE.getNom()){
                compteur++;
            }
        }
        return compteur;
    }
    public int nbCleesCristal(){
        int compteur = 0;
        for(int i = 0; i < this.cartes.size(); i++){
            if(this.cartes.get(i).getNom() == Artefact.CRISTAL.getNom()){
                compteur++;
            }
        }
        return compteur;
    }
    public int nbCleesCalice(){
        int compteur = 0;
        for(int i = 0; i < this.cartes.size(); i++){
            if(this.cartes.get(i).getNom() == Artefact.CALICE.getNom()){
                compteur++;
            }
        }
        return compteur;
    }
    public int nbCleesZephir(){
        int compteur = 0;
        for(int i = 0; i < this.cartes.size(); i++){
            if(this.cartes.get(i).getNom() == Artefact.ZEPHYR.getNom()){
                compteur++;
            }
        }
        return compteur;
    }
    public int nbCleesHelicoptere(){
        int compteur = 0;
        for(int i = 0; i < this.cartes.size(); i++){
            if(this.cartes.get(i).getNom() == "Hélicoptère"){
                compteur++;
            }
        }
        return compteur;
    }
    public int nbCleesMonteeDesEaux(){
        int compteur = 0;
        for(int i = 0; i < this.cartes.size(); i++){
            if(this.cartes.get(i).getNom() == "Montée des eaux"){
                compteur++;
            }
        }
        return compteur;
    }
    public int nbCleesSacDeSable(){
        int compteur = 0;
        for(int i = 0; i < this.cartes.size(); i++){
            if(this.cartes.get(i).getNom() == "Sacs De Sable"){
                compteur++;
            }
        }
        return compteur;
    }

    public int getSize() {
        return this.cartes.size();
    }

    public ArrayList<CarteTiree> getCartes() {
        return this.cartes;
    }
    public void donnerCle(CarteTiree c){
        if(this.zoneJoueur.getJ().size()>1) {
            boolean res = true;
            for (int i = 0; i < this.zoneJoueur.getJ().size(); i++) {
                if (this.zoneJoueur.getJ().get(i).getId() != this.id && this.zoneJoueur.getJ().get(i).cartes.size() < 5) {
                    this.zoneJoueur.getJ().get(i).addCle(c);
                    for(int j = 0; j < this.cartes.size(); j++){
                        if(this.cartes.get(j).getNom() == c.getNom() && res){
                            this.cartes.remove(this.cartes.get(j));
                            res = false;
                        }
                    }
                }
            }
        }
    }
    public void donnerCJ(CarteTiree c, Joueur J){
        boolean res = true;
        J.addCle(c);
        for(int j = 0; j < this.cartes.size(); j++){
            if(this.cartes.get(j).getNom() == c.getNom() && res){
                this.cartes.remove(this.cartes.get(j));
                res = false;
            }
        }
    }

    public void retirerCarte(CarteTiree c){
        boolean res = true;
        int x = 0;
        for (int i = 0; i < this.cartes.size(); i++) {
            if(this.cartes.get(i).getNom() == c.getNom() && res){
                x = i;
                res = false;
            }
        }
        if(res == false) {
            this.zoneJoueur.getGrille().getDefausseCartes().add(c);
            this.cartes.remove(x);
        }
    }
    public void incrementeAction(){
        this.nbActions++;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public String roleToString(){
        if(this.role == Role.PLONGEUR){
            return "Plongeur";
        }
        else if(this.role == Role.EXPLORATEUR){
            return "Explorateur";
        }
        else if(this.role == Role.MESSAGER){
            return "Messager";
        }
        else if(this.role == Role.NAVIGATEUR){
            return "Navigateur";
        }
        else if(this.role == Role.PILOTE){
            return "Pilote";
        }
        else if(this.role == Role.INGENIEUR){
            return "Ingénieur";
        }else{
            return null;
        }
    }
}
