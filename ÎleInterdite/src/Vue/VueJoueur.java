package Vue;

import Controler.Observers;
import Modele.Grille;
import Modele.Joueur;

import javax.swing.*;
import java.awt.*;

public class VueJoueur extends JPanel implements Observers {
    //private Grille grille;
    private Joueur joueurActuel;
    private Grille grille;
    //affichage des caracterisriques pour le joueur
    private JLabel nActions;
    private String nbActions;
    private JPanel panel;
    private JLabel[] cartesArtefact;
    private String[] carteArtefact;
    private JLabel[] cartesSpeciale;
    private String[] carteSpeciale;
    private JLabel nomJoueur;
    private String nJoueur;
    private JLabel artefacts;
    private String artefact;
    //surbrillance des clées
    private boolean subCalice = false;
    private boolean subCristal = false;
    private boolean subPierre = false;
    private boolean subZephir = false;
    private boolean subHelicoptere = false;
    private boolean subSacDeSable = false;
    private boolean subMonteeDesEaux = false;

    public VueJoueur(Grille grille, Joueur actuelle) {
        this.setBackground(Color.WHITE);
        this.panel = new JPanel();
        this.cartesArtefact = new JLabel[5];
        for(int i = 0; i < this.cartesArtefact.length; i++){
            this.cartesArtefact[i] = new JLabel();
        }
        this.cartesSpeciale = new JLabel[3];
        for(int i = 0; i < this.cartesSpeciale.length; i++){
            this.cartesSpeciale[i] = new JLabel();
        }
        this.nomJoueur = new JLabel();
        this.artefacts = new JLabel();
        this.carteArtefact = new String[5];
        this.carteSpeciale = new String[3];
        this.panel.setBackground(Color.WHITE);
        this.panel.setLayout(new BoxLayout(this.panel,  BoxLayout.Y_AXIS));
        this.grille = grille;
        grille.addObserver(this);
        this.joueurActuel = actuelle;

        this.nActions = new JLabel();
        if(this.joueurActuel.getId() == 1) {
            this.setBackground(new Color(	93, 255, 87));
            String test = "10101010101010";
            this.nJoueur = "<html><center><font color = '#32CD32'>" + this.joueurActuel.roleToString() +":  "  + this.joueurActuel.getNom() + "</font></center></html>";
            this.nbActions = "<html><center>Vous avez " + this.joueurActuel.getNbActions() + " restante. </center></html>";
            for(int i = 0; i < this.cartesArtefact.length; i++){
                if(i == 0){
                    this.carteArtefact[i] = "<html><center>Vous avez comme clées :</center></html>";
                }
                if(i == 1){
                    this.carteArtefact[i] = "<html><center>Vous avez " + this.joueurActuel.nbCleesCalice() + " clées de <font color = '#00bfff'>Calice.</font></center></html>";
                }
                if(i == 2){
                    this.carteArtefact[i] = "<html><center>Vous avez " + this.joueurActuel.nbCleesPierre() + " clées de <font color = '#a0522d'>Pierre.</font></center></html>";
                }
                if(i == 3){
                    this.carteArtefact[i] = "<html><center>Vous avez " + this.joueurActuel.nbCleesCristal() + " clées de <font color = '#ff0000'>Cristal.</font></center></html>";
                }
                if(i == 4){
                    this.carteArtefact[i] = "<html><center>Vous avez " + this.joueurActuel.nbCleesZephir() + " clées de <font color = '#ffd700'>Zéphir.</font></center></html>";
                }
            }
            if(this.joueurActuel.getArtefacts().size() == 0){
                this.artefact = "<html><center>Vous n'avez aucun artefact.</center></html>";
            }else{
                this.artefact = "<html><center>Vous avez un " + this.joueurActuel.toStringArtefact() + "</center></html>";
            }
            for(int i = 0; i < this.carteSpeciale.length; i++){
                if(i == 0){
                    this.carteSpeciale[i] =  "<html><center>Vous avez " + this.joueurActuel.nbCleesHelicoptere() + " clées d'hélicoptère.</center></html>";
                }
                if(i == 2){
                    this.carteSpeciale[i] =  "<html><center>Vous avez " + this.joueurActuel.nbCleesMonteeDesEaux() + " clées de montée des eaux.</center></html>";
                }
                if(i == 1){
                    this.carteSpeciale[i] =  "<html><center>Vous avez " + this.joueurActuel.nbCleesSacDeSable() + " clées de sac de sables.</center></html>";
                }
            }
        }
        this.nActions.setText(this.nbActions);
        this.nomJoueur.setText(this.nJoueur);
        for(int i = 0; i < this.cartesArtefact.length; i++){
            this.cartesArtefact[i].setText(this.carteArtefact[i]);
        }
        for(int i = 0; i < this.cartesSpeciale.length; i++){
            this.cartesSpeciale[i].setText(this.carteSpeciale[i]);
        }
        this.artefacts.setText(this.artefact);
        this.panel.add(this.nomJoueur);
        this.panel.add(this.nActions);
        for(int i = 0; i < this.cartesArtefact.length; i++){
            this.panel.add(this.cartesArtefact[i]);
        }
        for(int i = 0; i < this.cartesSpeciale.length; i++){
            this.panel.add(this.cartesSpeciale[i]);
        }
        this.panel.add(this.artefacts);
        this.add(this.panel);
    }

    public void update(){
        String test = "10101010101010";
        this.remove(this.panel);
        this.panel = new JPanel();
        this.cartesArtefact = new JLabel[5];
        for(int i = 0; i < this.cartesArtefact.length; i++){
            this.cartesArtefact[i] = new JLabel();
        }
        this.cartesSpeciale = new JLabel[3];
        for(int i = 0; i < this.cartesSpeciale.length; i++){
            this.cartesSpeciale[i] = new JLabel();
        }
        this.carteArtefact = new String[5];
        this.carteSpeciale = new String[3];
        this.panel.setBackground(Color.WHITE);
        this.panel.setLayout(new BoxLayout(this.panel,  BoxLayout.Y_AXIS));
        this.nActions = new JLabel();
        if(this.joueurActuel.getId() == 1) {
            this.setBackground(new Color(93, 255, 87));
            this.nJoueur = "<html><center><font color = '#32CD32'>" + this.joueurActuel.roleToString() +":  " + this.joueurActuel.getNom() + "</font></center></html>";
        }
        if(this.joueurActuel.getId() == 2) {
            this.setBackground(new Color(255, 144, 241));
            this.nJoueur = "<html><center><font color = '#FF69B4'>" + this.joueurActuel.roleToString() + ":  " + this.joueurActuel.getNom() + "</font></center></html>";
        }
        if(this.joueurActuel.getId() == 3) {
            this.setBackground(new Color(255, 112, 66));
            this.nJoueur = "<html><center><font color = '#800000'>" + this.joueurActuel.roleToString() + ":  " + this.joueurActuel.getNom() + "</font></center></html>";
        }
        if(this.joueurActuel.getId() == 4) {
            this.setBackground(new Color(255, 217, 90));
            this.nJoueur = "<html><center><font color = '#DAA520'>" + this.joueurActuel.roleToString() + ":  " + this.joueurActuel.getNom() + "</font></center></html>";
        }
        this.nbActions = "<html><center>Vous avez " + this.joueurActuel.getNbActions() + " restante. </center></html>";
        for(int i = 0; i < this.cartesArtefact.length; i++){
            if(i == 0){
                this.carteArtefact[i] = "<html><center>Vous avez comme clées :</center></html>";
            }
            if(i == 1){
                if(this.subCalice == false) {
                    this.carteArtefact[i] = "<html><center>Vous avez " + this.joueurActuel.nbCleesCalice() + " clées de <font color = '#00bfff'>Calice.</font></center></html>";
                }
                else{
                    this.carteArtefact[i] = "<html><center><span bgcolor= 'yellow'>Vous avez " + this.joueurActuel.nbCleesCalice() + " clées de <font color = '#00bfff'>Calice.</font></span></center></html>";
                }
            }
            if(i == 2) {
                if (this.subPierre == false) {
                    this.carteArtefact[i] = "<html><center>Vous avez " + this.joueurActuel.nbCleesPierre() + " clées de <font color = '#a0522d'>Pierre.</font></center></html>";
                }
                else{
                    this.carteArtefact[i] = "<html><center><span bgcolor= 'yellow'>Vous avez " + this.joueurActuel.nbCleesPierre() + " clées de <font color = '#a0522d'>Pierre.</font></span></center></html>";
                }
            }
            if(i == 3){
                if(this.subCristal == false) {
                    this.carteArtefact[i] = "<html><center>Vous avez " + this.joueurActuel.nbCleesCristal() + " clées de <font color = '#ff0000'>Cristal.</font></center></html>";
                }
                else{
                    this.carteArtefact[i] = "<html><center><span bgcolor= 'yellow'>Vous avez " + this.joueurActuel.nbCleesCristal() + " clées de <font color = '#ff0000'>Cristal.</font></span></center></html>";
                }
            }
            if(i == 4){
                if(this.subZephir == false) {
                    this.carteArtefact[i] = "<html><center>Vous avez " + this.joueurActuel.nbCleesZephir() + " clées de <font color = '#ffd700'>Zéphir.</font></center></html>";
                }
                else{
                    this.carteArtefact[i] = "<html><center><span bgcolor= 'yellow'>Vous avez " + this.joueurActuel.nbCleesZephir() + " clées de <font color = '#ffd700'>Zéphir.</font></span></center></html>";
                }
            }
        }
        if(this.joueurActuel.getArtefacts().size() == 0){
            this.artefact = "<html><center>Vous n'avez aucun artefact.</center></html>";
        }else{
            this.artefact = "<html><center>Vous avez un " + this.joueurActuel.toStringArtefact() + "</center></html>";
        }
        for(int i = 0; i < this.cartesSpeciale.length; i++ ){
            if(i == 0){
                if(this.subHelicoptere == false) {
                    this.carteSpeciale[i] =  "<html><center>Vous avez " + this.joueurActuel.nbCleesHelicoptere() + " clées d'hélicoptère.</center></html>";
                }else{
                    this.carteSpeciale[i] =  "<html><center><span bgcolor= 'yellow'>Vous avez " + this.joueurActuel.nbCleesHelicoptere() + " clées d'hélicoptère.</span></center></html>";
                }
            }
            if(i == 2){
                if(this.subMonteeDesEaux == false) {
                    this.carteSpeciale[i] =  "<html><center>Vous avez " + this.joueurActuel.nbCleesMonteeDesEaux() + " clées de montée des eaux.</center></html>";
                }else{
                    this.carteSpeciale[i] =  "<html><center><span bgcolor= 'yellow'>Vous avez " + this.joueurActuel.nbCleesMonteeDesEaux() + " clées de montée des eaux.</span></center></html>";
                }
            }
            if(i == 1){
                if(this.subSacDeSable == false) {
                    this.carteSpeciale[i] =  "<html><center>Vous avez " + this.joueurActuel.nbCleesSacDeSable() + " clées de sac de sables.</center></html>";
                }
                else{
                    this.carteSpeciale[i] =  "<html><center><span bgcolor= 'yellow'>Vous avez " + this.joueurActuel.nbCleesSacDeSable() + " clées de sac de sables.</span></center></html>";
                }
            }
        }
        this.nActions.setText(nbActions);
        this.nomJoueur.setText(this.nJoueur);
        for(int i = 0; i < this.cartesArtefact.length; i++){
            this.cartesArtefact[i].setText(this.carteArtefact[i]);
        }
        for(int i = 0; i < this.cartesSpeciale.length; i++){
            this.cartesSpeciale[i].setText(this.carteSpeciale[i]);
        }
        this.artefacts.setText(this.artefact);
        this.panel.add(this.nomJoueur);
        this.panel.add(this.nActions);
        for(int i = 0; i < this.cartesArtefact.length; i++){
            this.panel.add(this.cartesArtefact[i]);
        }
        for(int i = 0; i < this.cartesSpeciale.length; i++){
            this.panel.add(this.cartesSpeciale[i]);
        }
        this.panel.add(this.artefacts);

        this.add(this.panel);
    }

    public void setJoueurActuel(Joueur joueurActuel) {
        this.joueurActuel = joueurActuel;
    }

    public void setSubCalice(boolean subCalice) {
        this.subCalice = subCalice;
    }
    public boolean getSubCalice() {
        return this.subCalice;
    }
    public void setSubCristal(boolean subCristal) {
        this.subCristal = subCristal;
    }
    public boolean getSubCristal() {
        return this.subCristal;
    }
    public void setSubPierre(boolean subPierre) {
        this.subPierre = subPierre;
    }
    public boolean getSubPierre() {
        return this.subPierre;
    }
    public void setSubZephir(boolean subZephir) {
        this.subZephir = subZephir;
    }
    public boolean getSubZephir() {
        return this.subZephir;
    }
    public void setSubHelicoptere(boolean subHelicoptere) {
        this.subHelicoptere = subHelicoptere;
    }
    public boolean getSubHelicoptere() {
        return this.subHelicoptere;
    }
    public void setSubSacDeSable(boolean subSacDeSable) {
        this.subSacDeSable = subSacDeSable;
    }
    public boolean getSubSacDeSable() {
        return this.subSacDeSable;
    }
    public void setSubMonteeDesEaux(boolean subMonteeDesEaux) {
        this.subMonteeDesEaux = subMonteeDesEaux;
    }
    public boolean getSubMonteeDesEaux() {
        return this.subMonteeDesEaux;
    }
}
