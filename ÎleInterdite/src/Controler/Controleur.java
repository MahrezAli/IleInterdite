package Controler;

import Cartes.*;
import Modele.Artefact;
import Modele.Grille;
import Modele.Joueur;
import Utils.Direction;
import Utils.Etat;
import Utils.Role;
import Vue.VueCartes;
import Vue.VueJeu;
import Vue.VueJoueur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Controleur implements ActionListener, KeyListener {
    Grille grille;
    HashMap<Integer, Joueur> joueurs;
    Joueur joueurActuel;
    VueJoueur JoueurActuel;
    VueJeu jeu;
    VueCartes cartes;
    int nAction;
    int comptInge = 0;
    int idJJ;

    public Controleur(Grille grille, HashMap<Integer, Joueur> j, VueJeu jeu) {
        this.grille = grille;
        this.joueurs = j;
        if (this.joueurs.size() > 0) {
            this.joueurActuel = this.joueurs.get(1);
        }
        this.jeu = jeu;
        this.cartes = this.jeu.getCartes();
    }

    public void actionPerformed(ActionEvent e) {
        if (this.joueurActuel.getNbActions() > 0) {
            if(e.getActionCommand() == "deplace Navigateur"){
                if(this.joueurActuel.getRole() == Role.NAVIGATEUR){
                    this.joueurActuel.getZoneJoueur().setSelected(true);
                    this.nAction = 60;
                }
            }
            if (e.getActionCommand() == "Assecher la zone") {
                if(this.joueurActuel.getRole() != Role.EXPLORATEUR && this.joueurActuel.getRole() != Role.INGENIEUR) {
                    if (!this.isSelectedZone()) {
                        this.joueurActuel.getZoneJoueur().setSelected(true);
                        this.nAction = 3;
                    } else {
                        this.deselectAllZone();
                        this.nAction = 0;
                    }
                }else if(this.joueurActuel.getRole() == Role.EXPLORATEUR){
                    if (!this.isSelectedZone()) {
                        this.joueurActuel.getZoneJoueur().setSelected(true);
                        this.nAction = 42;
                    } else {
                        this.deselectAllZone();
                        this.nAction = 0;
                    }
                }
                else if(this.joueurActuel.getRole() == Role.INGENIEUR){
                    if (!this.isSelectedZone()) {
                        this.joueurActuel.getZoneJoueur().setSelected(true);
                        this.nAction = 50;
                    } else {
                        this.deselectAllZone();
                        this.nAction = 0;
                    }
                }
                this.setAllSub(false);
            }
            if (e.getActionCommand() == "Up") {
                this.deselectAllZone();
                if(this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(),this.joueurActuel.getZoneJoueur().getY()-1) != null && (this.joueurActuel.getZoneJoueur().getGrille().getZone(this.joueurActuel.getZoneJoueur().getX(),this.joueurActuel.getZoneJoueur().getY()-1).geteTat() != Etat.submergee || this.joueurActuel.getRole() == Role.PLONGEUR) && this.joueurActuel.getRole() != Role.PILOTE && this.joueurActuel.getRole() != Role.EXPLORATEUR) {
                    this.joueurActuel.deplace(grille, Direction.up);
                    this.joueurActuel.decremAction();
                    this.setAllSub(false);
                }else if(this.joueurActuel.getRole() == Role.PILOTE){
                    this.nAction = 44;
                    this.joueurActuel.getZoneJoueur().setSelected(true);
                }
                else if(this.joueurActuel.getRole() == Role.EXPLORATEUR){
                    this.nAction = 43;
                    this.joueurActuel.getZoneJoueur().setSelected(true);
                }
            }
            if (e.getActionCommand() == "Down") {
                this.deselectAllZone();
                if(this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(),this.joueurActuel.getZoneJoueur().getY()+1) != null && (this.joueurActuel.getZoneJoueur().getGrille().getZone(this.joueurActuel.getZoneJoueur().getX(),this.joueurActuel.getZoneJoueur().getY()+1).geteTat() != Etat.submergee || this.joueurActuel.getRole() == Role.PLONGEUR) && this.joueurActuel.getRole() != Role.PILOTE && this.joueurActuel.getRole() != Role.EXPLORATEUR) {
                    this.joueurActuel.deplace(grille, Direction.down);
                    this.joueurActuel.decremAction();
                    this.setAllSub(false);
                }
                else if(this.joueurActuel.getRole() == Role.PILOTE){
                    this.nAction = 44;
                    this.joueurActuel.getZoneJoueur().setSelected(true);
                }
                else if(this.joueurActuel.getRole() == Role.EXPLORATEUR){
                    this.nAction = 43;
                    this.joueurActuel.getZoneJoueur().setSelected(true);
                }
            }
            if (e.getActionCommand() == "Left") {
                this.deselectAllZone();
                if(this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1,this.joueurActuel.getZoneJoueur().getY()) != null && (this.joueurActuel.getZoneJoueur().getGrille().getZone(this.joueurActuel.getZoneJoueur().getX()-1,this.joueurActuel.getZoneJoueur().getY()).geteTat() != Etat.submergee || this.joueurActuel.getRole() == Role.PLONGEUR) && this.joueurActuel.getRole() != Role.PILOTE && this.joueurActuel.getRole() != Role.EXPLORATEUR) {
                    this.joueurActuel.deplace(grille, Direction.left);
                    this.joueurActuel.decremAction();
                    this.setAllSub(false);
                }
                else if(this.joueurActuel.getRole() == Role.PILOTE){
                    this.nAction = 44;
                    this.joueurActuel.getZoneJoueur().setSelected(true);
                }
                else if(this.joueurActuel.getRole() == Role.EXPLORATEUR){
                    this.nAction = 43;
                    this.joueurActuel.getZoneJoueur().setSelected(true);
                }
            }
            if (e.getActionCommand() == "Right") {
                this.deselectAllZone();
                if(this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1,this.joueurActuel.getZoneJoueur().getY()) != null && (this.joueurActuel.getZoneJoueur().getGrille().getZone(this.joueurActuel.getZoneJoueur().getX()+1,this.joueurActuel.getZoneJoueur().getY()).geteTat() != Etat.submergee || this.joueurActuel.getRole() == Role.PLONGEUR) && this.joueurActuel.getRole() != Role.PILOTE && this.joueurActuel.getRole() != Role.EXPLORATEUR) {
                    this.joueurActuel.deplace(grille, Direction.right);
                    this.joueurActuel.decremAction();
                    this.setAllSub(false);
                }
                else if(this.joueurActuel.getRole() == Role.PILOTE){
                    this.nAction = 44;
                    this.joueurActuel.getZoneJoueur().setSelected(true);
                }
                else if(this.joueurActuel.getRole() == Role.EXPLORATEUR){
                    this.nAction = 43;
                    this.joueurActuel.getZoneJoueur().setSelected(true);
                }
            }
            if (e.getActionCommand() == "Récuperer Artefact") {
                if(this.joueurActuel.prendreArtefact()){
                    this.joueurActuel.decremAction();
                }
                this.deselectAllZone();
            }
            if (e.getActionCommand() == "Donner une carte") {
                this.deselectAllZone();
                if(this.JoueurActuel.getSubCalice() == false && this.JoueurActuel.getSubPierre() == false && this.JoueurActuel.getSubCristal() == false && this.JoueurActuel.getSubZephir() == false && this.JoueurActuel.getSubHelicoptere() == false && this.JoueurActuel.getSubSacDeSable() == false && this.JoueurActuel.getSubMonteeDesEaux() == false) {
                    this.JoueurActuel.setSubCalice(true);
                    if(this.joueurActuel.getRole() != Role.MESSAGER) {
                        this.nAction = 1;
                    }else{
                        this.nAction = 41;
                    }
                }else{
                    this.setAllSub(false);
                    this.nAction = 0;
                }
            }
            if (e.getActionCommand() == "Défausser une carte") {
                this.deselectAllZone();
                if(this.JoueurActuel.getSubCalice() == false && this.JoueurActuel.getSubPierre() == false && this.JoueurActuel.getSubCristal() == false && this.JoueurActuel.getSubZephir() == false && this.JoueurActuel.getSubHelicoptere() == false && this.JoueurActuel.getSubSacDeSable() == false && this.JoueurActuel.getSubMonteeDesEaux() == false) {
                    this.JoueurActuel.setSubCalice(true);
                    this.nAction = 2;
                }else{
                    this.setAllSub(false);
                    this.nAction = 0;
                }
            }
            if (e.getActionCommand() == "Utiliser") {
                if(this.JoueurActuel.getSubCalice() == false && this.JoueurActuel.getSubPierre() == false && this.JoueurActuel.getSubCristal() == false && this.JoueurActuel.getSubZephir() == false && this.JoueurActuel.getSubHelicoptere() == false && this.JoueurActuel.getSubSacDeSable() == false && this.JoueurActuel.getSubMonteeDesEaux() == false) {
                    this.JoueurActuel.setSubCalice(true);
                    this.nAction = 4;
                }else{
                    this.deselectAllZone();
                    this.setAllSub(false);
                    this.nAction = 0;
                }
            }
        }
        if (e.getActionCommand() == "Fin de tour !") {
            this.deselectAllZone();
            this.setAllSub(false);
            this.nAction = 0;
            if (this.joueurActuel.getId() == this.grille.getNbJoueurs()) {
                if(boolAleatoire() && this.joueurActuel.getSize() < 5){
                    if(this.grille.getPileCartes().size() > 0) {
                        this.joueurActuel.addCle(this.grille.getPileCartes().get(this.grille.getPileCartes().size()-1));
                        this.grille.getPileCartes().remove(this.grille.getPileCartes().get(this.grille.getPileCartes().size()-1));
                    }
                    else{
                        this.grille.setPileCartes(this.grille.melangeDefausse());
                        this.grille.setDefausseCartes(new ArrayList<>());
                        this.joueurActuel.addCle(this.grille.getPileCartes().get(this.grille.getPileCartes().size()-1));
                        this.grille.getPileCartes().remove(this.grille.getPileCartes().get(this.grille.getPileCartes().size()-1));
                    }
                }
                this.joueurActuel.setNbActions(3);
                this.joueurActuel = this.joueurs.get(1);
                this.grille.inonde();
            }
            else {
                if(boolAleatoire() && this.joueurActuel.getSize() < 5) {
                    if (this.grille.getPileCartes().size() > 0) {
                        this.joueurActuel.addCle(this.grille.getPileCartes().get(this.grille.getPileCartes().size()-1));
                        this.grille.getPileCartes().remove(this.grille.getPileCartes().get(this.grille.getPileCartes().size()-1));
                    } else {
                        this.grille.setPileCartes(this.grille.melangeDefausse());
                        this.grille.setDefausseCartes(new ArrayList<>());
                        this.joueurActuel.addCle(this.grille.getPileCartes().get(this.grille.getPileCartes().size()-1));
                        this.grille.getPileCartes().remove(this.grille.getPileCartes().get(this.grille.getPileCartes().size()-1));
                    }
                }
                this.joueurActuel.setNbActions(3);
                this.joueurActuel = this.joueurs.get(this.joueurActuel.getId() + 1);
                this.grille.inonde();
            }
        }
        this.JoueurActuel.setJoueurActuel(this.joueurActuel);
        this.JoueurActuel.update();
        this.JoueurActuel.updateUI();
        this.cartes.update();
        this.cartes.updateUI();
        this.testWin();
        this.testLoose();
    }
    public boolean boolAleatoire(){
        Random random = new Random();
        return random.nextBoolean();
    }
    public void testWin(){
        HashMap<Integer, Joueur> joueurs = this.joueurs;
        int compteurArtefact = 0;
        for(int i = 0; i < joueurs.size(); i++){
            compteurArtefact = compteurArtefact + joueurs.get(i+1).getArtefacts().size();
        }
        if(compteurArtefact == 4 && this.grille.getZoneParNom("Heliport").getJ().size() == this.grille.getNbJoueurs()){
            this.jeu.endGameScreen("<html><center>VOUS AVEZ GAGNÉ !!! </center></html>");
        }
    }
    public void testLoose(){
        //si l'Heliport est submergée
        if(this.grille.getZoneParNom("Heliport").geteTat() == Etat.submergee){
            this.jeu.endGameScreen("<html><center>VOUS AVEZ PERDU !!! </center> <br> <center>L'Héliport est submergé, vous pouvez plus vous échapper de l'île </center></html>");
        }
        //si le joueur est submerge et n'a aucune zone adjacentes ou aller en nageant
        if(this.joueurActuel.getZoneJoueur().geteTat() == Etat.submergee && this.joueurActuel.getRole() != Role.PLONGEUR) {
            for (int j = 0; j < this.grille.getNbJoueurs(); j++) {
                int s = this.grille.getJoueur().get(j + 1).getZoneJoueur().getZonesAdjacentes(this.grille.getJoueur().get(j + 1).getZoneJoueur().getGrille()).size();
                for (int i = 0; i < this.grille.getJoueur().get(j + 1).getZoneJoueur().getZonesAdjacentes(this.grille).size(); i++) {
                    if (this.grille.getJoueur().get(j + 1).getZoneJoueur().getZonesAdjacentes(this.grille).get(i).geteTat() == Etat.submergee) {
                        s--;
                    }
                }
                if (s == 0) {
                    this.jeu.endGameScreen("<html><center>VOUS AVEZ PERDU !!! </center> <br> <center>L'un des joueur s'est noyé </center></html>");
                }
            }
        }
        //si l'une des zones pour récuperer un artefact est submergée
        if(this.grille.getZoneParNom("Le Temple de la Lune").geteTat() == Etat.submergee && this.grille.getZoneParNom( "Le Temple du Soleil").geteTat() == Etat.submergee){
            this.jeu.endGameScreen("<html><center>VOUS AVEZ PERDU !!! </center> <br> <center>Vous ne pouvez plus récuperer l'artefact Pierre <br> car les deux zones correspondantes sont submergées</center></html>");
        }
        if(this.grille.getZoneParNom("Le Palais de Corail").geteTat() == Etat.submergee && this.grille.getZoneParNom( "Le Palais des Marées").geteTat() == Etat.submergee){
            this.jeu.endGameScreen("<html><center>VOUS AVEZ PERDU !!! </center> <br> <center>Vous ne pouvez plus récuperer l'artefact Calice <br> car les deux zones correspondantes sont submergées</center></html>");
        }
        if(this.grille.getZoneParNom("La Caverne du Brasier").geteTat() == Etat.submergee && this.grille.getZoneParNom( "La Caverne des Ombres").geteTat() == Etat.submergee){
            this.jeu.endGameScreen("<html><center>VOUS AVEZ PERDU !!! </center> <br> <center>Vous ne pouvez plus récuperer l'artefact Cristal <br> car les deux zones correspondantes sont submergées</center></html>");
        }
        if(this.grille.getZoneParNom("Le Jardin des Murmures").geteTat() == Etat.submergee && this.grille.getZoneParNom( "Le Jardin des Hurlements").geteTat() == Etat.submergee){
            this.jeu.endGameScreen("<html><center>VOUS AVEZ PERDU !!! </center> <br> <center>Vous ne pouvez plus récuperer l'artefact Zéphyr <br> car les deux zones correspondantes sont submergées</center></html>");
        }
    }

    public void setJoueurActuel(VueJoueur joueurActuel) {
        JoueurActuel = joueurActuel;
    }
    public void keyPressed(KeyEvent e) {
        if(this.nAction == 1 || this.nAction == 2 || this.nAction == 4 || this.nAction == 41) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (this.JoueurActuel.getSubCalice() == true) {
                    this.JoueurActuel.setSubCalice(false);
                    this.JoueurActuel.setSubMonteeDesEaux(true);
                } else if (this.JoueurActuel.getSubZephir() == true) {
                    this.JoueurActuel.setSubZephir(false);
                    this.JoueurActuel.setSubCristal(true);
                } else if (this.JoueurActuel.getSubCristal() == true) {
                    this.JoueurActuel.setSubCristal(false);
                    this.JoueurActuel.setSubPierre(true);
                } else if (this.JoueurActuel.getSubPierre() == true) {
                    this.JoueurActuel.setSubPierre(false);
                    this.JoueurActuel.setSubCalice(true);
                } else if (this.JoueurActuel.getSubSacDeSable() == true) {
                    this.JoueurActuel.setSubSacDeSable(false);
                    this.JoueurActuel.setSubHelicoptere(true);
                } else if (this.JoueurActuel.getSubHelicoptere() == true) {
                    this.JoueurActuel.setSubHelicoptere(false);
                    this.JoueurActuel.setSubZephir(true);
                } else if (this.JoueurActuel.getSubMonteeDesEaux() == true) {
                    this.JoueurActuel.setSubMonteeDesEaux(false);
                    this.JoueurActuel.setSubSacDeSable(true);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (this.JoueurActuel.getSubCalice() == true) {
                    this.JoueurActuel.setSubCalice(false);
                    this.JoueurActuel.setSubPierre(true);
                } else if (this.JoueurActuel.getSubZephir() == true) {
                    this.JoueurActuel.setSubZephir(false);
                    this.JoueurActuel.setSubHelicoptere(true);
                } else if (this.JoueurActuel.getSubCristal() == true) {
                    this.JoueurActuel.setSubCristal(false);
                    this.JoueurActuel.setSubZephir(true);
                } else if (this.JoueurActuel.getSubPierre() == true) {
                    this.JoueurActuel.setSubPierre(false);
                    this.JoueurActuel.setSubCristal(true);
                } else if (this.JoueurActuel.getSubSacDeSable() == true) {
                    this.JoueurActuel.setSubSacDeSable(false);
                    this.JoueurActuel.setSubMonteeDesEaux(true);
                } else if (this.JoueurActuel.getSubHelicoptere() == true) {
                    this.JoueurActuel.setSubHelicoptere(false);
                    this.JoueurActuel.setSubSacDeSable(true);
                } else if (this.JoueurActuel.getSubMonteeDesEaux() == true) {
                    this.JoueurActuel.setSubMonteeDesEaux(false);
                    this.JoueurActuel.setSubCalice(true);
                }

            }
        }
        else if(this.nAction == 3 || this.nAction == 50){
            if(this.joueurActuel.getZoneJoueur().getIsSelected()) {
                if (e.getKeyCode() == KeyEvent.VK_UP && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() - 1) != null && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() - 1)) {
                    this.joueurActuel.getZoneJoueur().setSelected(false);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() - 1).setSelected(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() + 1) != null &&  this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() + 1)) {
                    this.joueurActuel.getZoneJoueur().setSelected(false);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() + 1).setSelected(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()) != null &&  this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX() - 1, this.joueurActuel.getZoneJoueur().getY())) {
                    this.joueurActuel.getZoneJoueur().setSelected(false);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX() - 1, this.joueurActuel.getZoneJoueur().getY()).setSelected(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()) != null &&  this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX() + 1, this.joueurActuel.getZoneJoueur().getY())) {
                    this.joueurActuel.getZoneJoueur().setSelected(false);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX() + 1, this.joueurActuel.getZoneJoueur().getY()).setSelected(true);
                }
            }
            if(this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()-1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()-1) != null  && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()-1).getIsSelected()) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    this.joueurActuel.getZoneJoueur().setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() - 1).setSelected(false);
                }
            }
            if(this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()) != null  && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()).getIsSelected()) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    this.joueurActuel.getZoneJoueur().setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()).setSelected(false);
                }
            }
            if(this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()+1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()+1) != null && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()+1).getIsSelected()) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    this.joueurActuel.getZoneJoueur().setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() + 1).setSelected(false);
                }
            }
            if(this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY())  && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()) != null && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()).getIsSelected()) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    this.joueurActuel.getZoneJoueur().setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()).setSelected(false);
                }
            }
        }
        else if(this.nAction == 43 || this.nAction == 42){
            if(this.joueurActuel.getZoneJoueur().getIsSelected()) {
                if (e.getKeyCode() == KeyEvent.VK_UP && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() - 1) != null && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() - 1)) {
                    this.joueurActuel.getZoneJoueur().setSelected(false);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() - 1).setSelected(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() + 1) != null &&  this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() + 1)) {
                    this.joueurActuel.getZoneJoueur().setSelected(false);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() + 1).setSelected(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()) != null &&  this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX() - 1, this.joueurActuel.getZoneJoueur().getY())) {
                    this.joueurActuel.getZoneJoueur().setSelected(false);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX() - 1, this.joueurActuel.getZoneJoueur().getY()).setSelected(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()) != null &&  this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX() + 1, this.joueurActuel.getZoneJoueur().getY())) {
                    this.joueurActuel.getZoneJoueur().setSelected(false);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX() + 1, this.joueurActuel.getZoneJoueur().getY()).setSelected(true);
                }
            }
            if(this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()-1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()-1) != null  && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()-1).getIsSelected()) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    this.joueurActuel.getZoneJoueur().setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() - 1).setSelected(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()-1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()-1) != null) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()-1).setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() - 1).setSelected(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()-1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()-1) != null) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY() - 1).setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() - 1).setSelected(false);
                }
            }
            if(this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()) != null  && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()).getIsSelected()) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    this.joueurActuel.getZoneJoueur().setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()).setSelected(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()-1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()-1) != null) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY() - 1).setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()).setSelected(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()+1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()+1) != null) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY() + 1).setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()).setSelected(false);
                }
            }
            if(this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()+1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()+1) != null && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()+1).getIsSelected()) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    this.joueurActuel.getZoneJoueur().setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() + 1).setSelected(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()+1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()+1) != null) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY() + 1).setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() + 1).setSelected(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()+1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()+1) != null) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY() + 1).setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() + 1).setSelected(false);
                }
            }
            if(this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY())  && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()) != null && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()).getIsSelected()) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    this.joueurActuel.getZoneJoueur().setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()).setSelected(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()-1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()-1) != null) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY() - 1).setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()).setSelected(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()+1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()+1) != null) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY() + 1).setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()).setSelected(false);
                }
            }
            if(this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()-1)  && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()-1) != null && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()-1).getIsSelected()) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()-1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()-1) != null) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()-1).setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()-1).setSelected(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()) != null) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()).setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()-1).setSelected(false);
                }
            }
            if(this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()-1)  && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()-1) != null && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()-1).getIsSelected()) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()-1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()-1) != null) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() - 1).setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()-1).setSelected(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()) != null) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()).setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()-1).setSelected(false);
                }
            }
            if(this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()+1)  && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()+1) != null && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()+1).getIsSelected()) {
                if (e.getKeyCode() == KeyEvent.VK_UP && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()) != null) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()).setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()+1).setSelected(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()+1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()+1) != null) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() + 1).setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()+1).setSelected(false);
                }
            }
            if(this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()+1)  && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()+1) != null && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()+1).getIsSelected()) {
                if (e.getKeyCode() == KeyEvent.VK_UP && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()) != null) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()).setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()+1).setSelected(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()+1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()+1) != null) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() + 1).setSelected(true);
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()+1).setSelected(false);
                }
            }

        }
        else if(this.nAction == 5 || this.nAction == 6 || this.nAction == 44 || this.nAction == 40 || this.nAction == 60 || this.nAction == 61){
            for(int i = 0; i < Grille.LARGEUR; i++){
                for(int j = 0; j < Grille.HAUTEUR; j++){
                    if(this.grille.isZonePossible(i,j) && this.grille.getZone(i,j).getIsSelected()){
                        if (e.getKeyCode() == KeyEvent.VK_UP && this.grille.getZone(i, j-1) != null && this.grille.isZonePossible(i, j - 1)) {
                            this.grille.getZone(i, j).setSelected(false);
                            this.grille.getZone(i, j-1).setSelected(true);
                        }
                        if (e.getKeyCode() == KeyEvent.VK_DOWN && this.grille.getZone(i, j+1) != null && this.grille.isZonePossible(i, j + 1)) {
                            this.grille.getZone(i, j).setSelected(false);
                            this.grille.getZone(i, j+1).setSelected(true);
                            return;
                        }
                        if (e.getKeyCode() == KeyEvent.VK_RIGHT && this.grille.getZone(i+1, j) != null && this.grille.isZonePossible(i+1, j)) {
                            this.grille.getZone(i, j).setSelected(false);
                            this.grille.getZone(i+1, j).setSelected(true);
                            return;
                        }
                        if (e.getKeyCode() == KeyEvent.VK_LEFT && this.grille.getZone(i-1, j) != null && this.grille.isZonePossible(i-1, j)) {
                            this.grille.getZone(i, j).setSelected(false);
                            this.grille.getZone(i-1, j).setSelected(true);
                        }
                    }
                }
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(this.nAction == 1) { // Donner une carte
                if (this.JoueurActuel.getSubCalice() == true) {
                    this.JoueurActuel.setSubCalice(false);
                    this.joueurActuel.donnerCle(new CarteTresor(Artefact.CALICE));
                    this.joueurActuel.decremAction();
                } else if (this.JoueurActuel.getSubZephir() == true) {
                    this.JoueurActuel.setSubZephir(false);
                    this.joueurActuel.donnerCle(new CarteTresor(Artefact.ZEPHYR));
                    this.joueurActuel.decremAction();
                } else if (this.JoueurActuel.getSubCristal() == true) {
                    this.JoueurActuel.setSubCristal(false);
                    this.joueurActuel.donnerCle(new CarteTresor(Artefact.CRISTAL));
                    this.joueurActuel.decremAction();
                } else if (this.JoueurActuel.getSubPierre() == true) {
                    this.JoueurActuel.setSubPierre(false);
                    this.joueurActuel.donnerCle(new CarteTresor(Artefact.PIERRE));
                    this.joueurActuel.decremAction();
                }
            }
            if(this.nAction == 2){ //Defausser une carte
                if (this.JoueurActuel.getSubCalice() == true) {
                    this.JoueurActuel.setSubCalice(false);
                    this.joueurActuel.retirerCarte(new CarteTresor(Artefact.CALICE));
                } else if (this.JoueurActuel.getSubZephir() == true) {
                    this.JoueurActuel.setSubZephir(false);
                    this.joueurActuel.retirerCarte(new CarteTresor(Artefact.ZEPHYR));
                } else if (this.JoueurActuel.getSubCristal() == true) {
                    this.JoueurActuel.setSubCristal(false);
                    this.joueurActuel.retirerCarte(new CarteTresor(Artefact.CRISTAL));
                } else if (this.JoueurActuel.getSubPierre() == true) {
                    this.JoueurActuel.setSubPierre(false);
                    this.joueurActuel.retirerCarte(new CarteTresor(Artefact.PIERRE));
                } else if (this.JoueurActuel.getSubSacDeSable() == true) {
                    this.JoueurActuel.setSubSacDeSable(false);
                    this.joueurActuel.retirerCarte(new CarteSacDeSable());
                } else if (this.JoueurActuel.getSubHelicoptere() == true) {
                    this.JoueurActuel.setSubHelicoptere(false);
                    this.joueurActuel.retirerCarte(new CarteHelicoptere());
                }
                else if (this.JoueurActuel.getSubMonteeDesEaux() == true) {
                    this.JoueurActuel.setSubMonteeDesEaux(false);
                    this.joueurActuel.retirerCarte(new CarteMonteeDesEaux());
                }
            }
            if(this.nAction == 3){ //Assecher une zone
                if (this.joueurActuel.getZoneJoueur().getIsSelected()) {
                    this.joueurActuel.getZoneJoueur().setSelected(false);
                    this.joueurActuel.asseche(this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(),this.joueurActuel.getZoneJoueur().getY()));
                } else if (this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()-1) != null && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()-1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()-1).getIsSelected()) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() - 1).setSelected(false);
                    this.joueurActuel.asseche(this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()-1));
                } else if (this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()+1) != null && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()+1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()+1).getIsSelected()) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() + 1).setSelected(false);
                    this.joueurActuel.asseche(this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()+1));
                } else if (this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()) != null && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()).getIsSelected()) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()).setSelected(false);
                    this.joueurActuel.asseche(this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()+1, this.joueurActuel.getZoneJoueur().getY()));
                } else if (this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()) != null && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()).getIsSelected()) {
                    this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()).setSelected(false);
                    this.joueurActuel.asseche(this.grille.getZone(this.joueurActuel.getZoneJoueur().getX()-1, this.joueurActuel.getZoneJoueur().getY()));
                }
            }
            if(this.nAction == 4){ //carte speciale
               if(this.JoueurActuel.getSubHelicoptere()){
                   this.nAction = 6;
               }
                if(this.JoueurActuel.getSubSacDeSable()){
                    this.nAction = 5;
                }
                this.joueurActuel.getZoneJoueur().setSelected(true);
                this.setAllSub(false);
            }
            else if(this.nAction == 5){
                for(int i = 0; i < Grille.HAUTEUR; i++){
                    for(int j = 0; j < Grille.LARGEUR; j++){
                        if(this.grille.isZonePossible(i,j) && this.grille.getZone(i,j) != null && this.grille.getZone(i,j).getIsSelected()){
                            this.joueurActuel.asseche(this.grille.getZone(i,j));
                            this.joueurActuel.incrementeAction();
                        }
                    }
                }
                this.joueurActuel.retirerCarte(new CarteSacDeSable());
                deselectAllZone();
            }
            else if(this.nAction == 6){
                for(int i = 0; i < Grille.HAUTEUR; i++){
                    for(int j = 0; j < Grille.LARGEUR; j++){
                        if(this.grille.isZonePossible(i,j) && this.grille.getZone(i,j) != null && this.grille.getZone(i,j).getIsSelected()){
                            for(int k = 0; k < this.joueurActuel.getZoneJoueur().getJ().size(); k++){
                                if(this.joueurActuel.getZoneJoueur().getJ().get(k).getId() != this.joueurActuel.getId()){
                                    this.joueurActuel.getZoneJoueur().getJ().get(k).setZone(this.grille.getZone(i,j));
                                }
                            }
                            this.joueurActuel.setZone(this.grille.getZone(i,j));
                        }
                    }
                }
                this.joueurActuel.retirerCarte(new CarteHelicoptere());
                deselectAllZone();
            }
            else if(this.nAction == 44 || this.nAction == 43){
                for(int i = 0; i < Grille.HAUTEUR; i++){
                    for(int j = 0; j < Grille.LARGEUR; j++){
                        if(this.grille.isZonePossible(i,j) && this.grille.getZone(i,j) != null && this.grille.getZone(i,j).getIsSelected()){
                            this.joueurActuel.setZone(this.grille.getZone(i,j));
                        }
                    }
                }
                deselectAllZone();
                this.joueurActuel.decremAction();
            }
            else if(this.nAction == 42){
                for(int i = 0; i < Grille.HAUTEUR; i++){
                    for(int j = 0; j < Grille.LARGEUR; j++){
                        if(this.grille.isZonePossible(i,j) && this.grille.getZone(i,j) != null && this.grille.getZone(i,j).getIsSelected()){
                            this.joueurActuel.asseche(this.grille.getZone(i,j));
                        }
                    }
                }
                deselectAllZone();
            }
            else if(this.nAction == 41){
                this.joueurActuel.getZoneJoueur().setSelected(true);
                this.nAction = 40;
            }
            else if(this.nAction == 40){
                for(int i = 0; i < Grille.HAUTEUR; i++){
                    for(int j = 0; j < Grille.LARGEUR; j++){
                        if(this.grille.isZonePossible(i,j) && this.grille.getZone(i,j) != null && this.grille.getZone(i,j).getIsSelected() && this.grille.getZone(i,j).getJ().size() > 0){
                            if (this.JoueurActuel.getSubCalice() == true) {
                                this.JoueurActuel.setSubCalice(false);
                                this.joueurActuel.donnerCJ(new CarteTresor(Artefact.CALICE), this.grille.getZone(i,j).getJ().get(0));
                                this.joueurActuel.decremAction();
                            } else if (this.JoueurActuel.getSubZephir() == true) {
                                this.JoueurActuel.setSubZephir(false);
                                this.joueurActuel.donnerCJ(new CarteTresor(Artefact.ZEPHYR), this.grille.getZone(i,j).getJ().get(0));
                                this.joueurActuel.decremAction();
                            } else if (this.JoueurActuel.getSubCristal() == true) {
                                this.JoueurActuel.setSubCristal(false);
                                this.joueurActuel.donnerCJ(new CarteTresor(Artefact.CRISTAL), this.grille.getZone(i,j).getJ().get(0));
                                this.joueurActuel.decremAction();
                            } else if (this.JoueurActuel.getSubPierre() == true) {
                                this.JoueurActuel.setSubPierre(false);
                                this.joueurActuel.donnerCJ(new CarteTresor(Artefact.PIERRE), this.grille.getZone(i,j).getJ().get(0));
                                this.joueurActuel.decremAction();
                            }
                            this.deselectAllZone();
                        }
                    }
                }
            }
            else if(this.nAction == 50){
                if(comptInge < 2) {
                    if (this.joueurActuel.getZoneJoueur().getIsSelected()) {
                        this.joueurActuel.getZoneJoueur().setSelected(false);
                        this.joueurActuel.asseche(this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY()));
                    } else if (this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() - 1) != null && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() - 1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() - 1).getIsSelected()) {
                        this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() - 1).setSelected(false);
                        this.joueurActuel.asseche(this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() - 1));
                    } else if (this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() + 1) != null && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() + 1) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() + 1).getIsSelected()) {
                        this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() + 1).setSelected(false);
                        this.joueurActuel.asseche(this.grille.getZone(this.joueurActuel.getZoneJoueur().getX(), this.joueurActuel.getZoneJoueur().getY() + 1));
                    } else if (this.grille.getZone(this.joueurActuel.getZoneJoueur().getX() + 1, this.joueurActuel.getZoneJoueur().getY()) != null && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX() + 1, this.joueurActuel.getZoneJoueur().getY()) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX() + 1, this.joueurActuel.getZoneJoueur().getY()).getIsSelected()) {
                        this.grille.getZone(this.joueurActuel.getZoneJoueur().getX() + 1, this.joueurActuel.getZoneJoueur().getY()).setSelected(false);
                        this.joueurActuel.asseche(this.grille.getZone(this.joueurActuel.getZoneJoueur().getX() + 1, this.joueurActuel.getZoneJoueur().getY()));
                    } else if (this.grille.getZone(this.joueurActuel.getZoneJoueur().getX() - 1, this.joueurActuel.getZoneJoueur().getY()) != null && this.grille.isZonePossible(this.joueurActuel.getZoneJoueur().getX() - 1, this.joueurActuel.getZoneJoueur().getY()) && this.grille.getZone(this.joueurActuel.getZoneJoueur().getX() - 1, this.joueurActuel.getZoneJoueur().getY()).getIsSelected()) {
                        this.grille.getZone(this.joueurActuel.getZoneJoueur().getX() - 1, this.joueurActuel.getZoneJoueur().getY()).setSelected(false);
                        this.joueurActuel.asseche(this.grille.getZone(this.joueurActuel.getZoneJoueur().getX() - 1, this.joueurActuel.getZoneJoueur().getY()));
                    }
                    comptInge++;
                    this.joueurActuel.getZoneJoueur().setSelected(true);
                }
                if(comptInge == 2) {
                    this.joueurActuel.incrementeAction();
                    this.deselectAllZone();
                    this.comptInge = 0;
                    this.nAction = 0;
                }
            }
            else if(this.nAction == 60){
                for(int i = 0; i < Grille.HAUTEUR; i++){
                    for(int j = 0; j < Grille.LARGEUR; j++){
                        if(this.grille.isZonePossible(i,j) && this.grille.getZone(i,j) != null && this.grille.getZone(i,j).getIsSelected() && this.grille.getZone(i,j).getJ().size() > 0){
                            this.idJJ = this.grille.getZone(i,j).getJ().get(0).getId();
                            this.nAction = 61;
                        }
                    }
                }
            }
            else if(this.nAction == 61){
                for(int i = 0; i < Grille.HAUTEUR; i++){
                    for(int j = 0; j < Grille.LARGEUR; j++){
                        if(this.grille.isZonePossible(i,j) && this.grille.getZone(i,j) != null && this.grille.getZone(i,j).getIsSelected()){
                            this.grille.getJoueur(idJJ).setZone(this.grille.getZone(i,j));
                            this.joueurActuel.decremAction();
                        }
                    }
                }
                deselectAllZone();
            }
        }
        this.JoueurActuel.setJoueurActuel(this.joueurActuel);
        this.JoueurActuel.update();
        this.JoueurActuel.updateUI();
        this.cartes.update();
        this.cartes.updateUI();
    }

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}

    public void setAllSub(boolean b){
        this.JoueurActuel.setSubCalice(b);
        this.JoueurActuel.setSubPierre(b);
        this.JoueurActuel.setSubCristal(b);
        this.JoueurActuel.setSubZephir(b);
        this.JoueurActuel.setSubHelicoptere(b);
        this.JoueurActuel.setSubSacDeSable(b);
        this.JoueurActuel.setSubMonteeDesEaux(b);
    }

    public boolean isSelectedZone(){
        for(int i = 0; i < Grille.HAUTEUR; i++){
            for(int j = 0; j < Grille.LARGEUR; j++){
                if(this.grille.isZonePossible(i,j) && this.grille.getZone(i,j).getIsSelected()){
                    return true;
                }
            }
        }
        return false;
    }
    public void deselectAllZone(){
        for(int i = 0; i < Grille.LARGEUR; i++){
            for(int j = 0; j < Grille.LARGEUR; j++){
                if(this.grille.isZonePossible(i, j) && this.grille.getZone(i, j).getIsSelected()){
                    this.grille.getZone(i, j).setSelected(false);
                }
            }
        }
    }

    public Joueur getJoueurActuel() {
        return joueurActuel;
    }
}





























