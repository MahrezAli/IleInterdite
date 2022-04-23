package Vue;

import Controler.Observers;
import Modele.Artefact;
import Modele.Grille;
import Modele.Zone;
import Utils.Etat;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class VueGrille extends JPanel implements Observers {
    private Grille grille;
    private final static int TAILLE = 10;
    public VueGrille(Grille grille) {
        this.grille = grille;
        this.grille.addObserver(this);
        Dimension dim = new Dimension(TAILLE*Grille.LARGEUR*16, TAILLE*Grille.HAUTEUR*10);
        this.setPreferredSize(dim);
    }

    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.repaint();
        for(int i=0; i<Grille.LARGEUR; i++) {
            for(int j=0; j<Grille.HAUTEUR; j++) {
                if(this.grille.isZonePossible(i,j) || this.grille.zonePlacerArtefact(i, j)) {
                    paint(g, this.grille.getZone(i, j), i  * TAILLE * 15, j * TAILLE * 10);
                }
            }
        }
    }

    private void paint(Graphics g, Zone z, int x, int y) {
        ImageIcon cristal = new ImageIcon(getClass().getClassLoader().getResource("Image/Cristal.png"));
        ImageIcon pierre = new ImageIcon(getClass().getClassLoader().getResource("Image/Pierre.png"));
        ImageIcon calice = new ImageIcon(getClass().getClassLoader().getResource("Image/Calice.png"));
        ImageIcon zephir = new ImageIcon(getClass().getClassLoader().getResource("Image/Zephir.png"));
        //zone submergée
        ImageIcon zoneSubmergee = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/zoneSubmerge.png"));
        ImageIcon zoneSubmergeeSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/zoneSubmergeSelect.jpeg"));
        //zone normales
        ImageIcon LaCaverneduBrasier = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LaCaverneduBrasier.jpeg"));
        ImageIcon LePontdesAbîmes = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LePontdesAbîmes.jpeg"));
        ImageIcon LeJardindesMurmures = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LeJardindesMurmures.jpeg"));
        ImageIcon LaTourduGuet = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LaTourduGuet.jpeg"));
        ImageIcon LeValduCrépuscule = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LeValduCrépuscule.jpeg"));
        ImageIcon LePalaisdesMarées = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LePalaisdesMarées.jpeg"));
        ImageIcon LeTempledelaLune = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LeTempledelaLune.jpeg"));
        ImageIcon LeTempleduSoleil = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LeTempleduSoleil.jpeg"));
        ImageIcon LeRocherFantôme = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LeRocherFantome.jpeg"));
        ImageIcon Observatoire = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/Observatoire.jpeg"));
        ImageIcon LeMaraisBrumeux = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LeMaraisBrumeux.jpeg"));
        ImageIcon LeLagonPerdu = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LeLagonPerdu.jpeg"));
        ImageIcon LaForêtPourpre = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LaForêtPourpre.jpeg"));
        ImageIcon LeJardindesHurlements = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LeJardindesHurlements.jpeg"));
        ImageIcon LaPortedeCuivre = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LaPortedeCuivre.jpeg"));
        ImageIcon Heliport = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/Heliport.jpeg"));
        ImageIcon LesDunesdelIllusion = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LesDunesdelIllusion.jpeg"));
        ImageIcon LaPortedArgent = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LaPorted'Argent.jpeg"));
        ImageIcon LePalaisdeCorail = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LePalaisdeCorail.jpeg"));
        ImageIcon LesFalaisesdelOubli = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LesFalaisesdel'Oubli.jpeg"));
        ImageIcon LaPortedOr = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LaPorted'Or.jpeg"));
        ImageIcon LaPortedeFer = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LaPortedeFer.jpeg"));
        ImageIcon LaCavernedesOmbres = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LaCavernedesOmbres.jpeg"));
        ImageIcon LaPortedeBronze = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneNormal/LaPortedeBronze.jpeg"));
        //zone inondée
        ImageIcon LaCaverneduBrasierInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LaCaverneduBrasier.jpeg"));
        ImageIcon LePontdesAbîmesInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LePontdesAbîmes.jpeg"));
        ImageIcon LeJardindesMurmuresInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LeJardindesMurmures.jpeg"));
        ImageIcon LaTourduGuetInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LaTourduGuet.jpeg"));
        ImageIcon LeValduCrépusculeInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LeValduCrépuscule.jpeg"));
        ImageIcon LePalaisdesMaréesInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LePalaisdesMarées.jpeg"));
        ImageIcon LeTempledelaLuneInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LeTempledelaLune.png"));
        ImageIcon LeTempleduSoleilInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LeTempleduSoleil.jpeg"));
        ImageIcon LeRocherFantômeInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LeRocherFantôme.jpeg"));
        ImageIcon ObservatoireInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/Observatoire.jpeg"));
        ImageIcon LeMaraisBrumeuxInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LeMaraisBrumeux.jpeg"));
        ImageIcon LeLagonPerduInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LeLagonPerdu.jpeg"));
        ImageIcon LaForêtPourpreInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LaForêtPourpre.jpeg"));
        ImageIcon LeJardindesHurlementsInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LeJardindesHurlements.jpeg"));
        ImageIcon LaPortedeCuivreInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LaPortedeCuivre.jpeg"));
        ImageIcon HeliportInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/Heliport.jpeg"));
        ImageIcon LesDunesdelIllusionInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LesDunesdelIllusion.jpeg"));
        ImageIcon LaPortedArgentInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LaPorted'Argent.jpeg"));
        ImageIcon LePalaisdeCorailInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LePalaisdeCorail.jpeg"));
        ImageIcon LesFalaisesdelOubliInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LesFalaisesdel'Oubli.jpeg"));
        ImageIcon LaPortedOrInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LaPorted'Or.jpeg"));
        ImageIcon LaPortedeFerInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LaPortedeFer.jpeg"));
        ImageIcon LaCavernedesOmbresInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LaCavernedesOmbres.jpeg"));
        ImageIcon LaPortedeBronzeInonde = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneInonde/LaPortedeBronze.jpeg"));
        //zone selectionée
        ImageIcon LaCaverneduBrasierSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LaCaverneduBrasier.jpeg"));
        ImageIcon LePontdesAbîmesSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LePontdesAbîmes.jpeg"));
        ImageIcon LeJardindesMurmuresSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LeJardindesMurmures.jpeg"));
        ImageIcon LaTourduGuetSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LaTourduGuet.jpeg"));
        ImageIcon LeValduCrépusculeSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LeValduCrépuscule.jpeg"));
        ImageIcon LePalaisdesMaréesSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LePalaisdesMarées.jpeg"));
        ImageIcon LeTempledelaLuneSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LeTempledelaLune.jpeg"));
        ImageIcon LeTempleduSoleilSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LeTempleduSoleil.jpeg"));
        ImageIcon LeRocherFantômeSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LeRocherFantôme.jpeg"));
        ImageIcon ObservatoireSelect =new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/Observatoire.jpeg"));
        ImageIcon LeMaraisBrumeuxSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LeMaraisBrumeux.jpeg"));
        ImageIcon LeLagonPerduSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LeLagonPerdu.jpeg"));
        ImageIcon LaForêtPourpreSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LaForêtPourpre.jpeg"));
        ImageIcon LeJardindesHurlementsSelect =new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LeJardindesHurlements.jpeg"));
        ImageIcon LaPortedeCuivreSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LaPortedeCuivre.jpeg"));
        ImageIcon HeliportSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/Heliport.jpeg"));
        ImageIcon LesDunesdelIllusionSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LesDunesdelIllusion.jpeg"));
        ImageIcon LaPortedArgentSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LaPorted'Argent.jpeg"));
        ImageIcon LePalaisdeCorailSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LePalaisdeCorail.jpeg"));
        ImageIcon LesFalaisesdelOubliSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LesFalaisesdel'Oubli.jpeg"));
        ImageIcon LaPortedOrSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LaPorted'Or.jpeg"));
        ImageIcon LaPortedeFerSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LaPortedeFer.jpeg"));
        ImageIcon LaCavernedesOmbresSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LaCavernedesOmbres.jpeg"));
        ImageIcon LaPortedeBronzeSelect = new ImageIcon(getClass().getClassLoader().getResource("Image/zoneSelectionnee/LaPortedeBronze.jpeg"));

        g.setColor(new Color(238,238,238,255));
        g.fillRect(x, y, TAILLE, TAILLE);
        if(z.isArtefact() && z.getArtefact().getNom() == Artefact.CRISTAL.getNom()){
            cristal.paintIcon(this, g, x + Grille.LARGEUR * 8, y + Grille.HAUTEUR * 5 -20 );
        }
        if(z.isArtefact() && z.getArtefact().getNom() == Artefact.ZEPHYR.getNom()){
            zephir.paintIcon(this, g, x + Grille.LARGEUR * 8, y + Grille.HAUTEUR * 5 -20);
        }
        if(z.isArtefact() && z.getArtefact().getNom() == Artefact.CALICE.getNom()){
            calice.paintIcon(this, g, x + Grille.LARGEUR * 8, y + Grille.HAUTEUR * 5 -20 );
        }
        if(z.isArtefact() && z.getArtefact().getNom() == Artefact.PIERRE.getNom()){
            pierre.paintIcon(this, g, x + Grille.LARGEUR * 8, y + Grille.HAUTEUR * 5 -20);
        }
        if(z.getName() == "La Caverne du Brasier"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LaCaverneduBrasier.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LaCaverneduBrasierInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LaCaverneduBrasierSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "Le Pont des Abîmes"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LePontdesAbîmes.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LePontdesAbîmesInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LePontdesAbîmesSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "La Porte de Bronze"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LaPortedeBronze.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LaPortedeBronzeInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LaPortedeBronzeSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "La Caverne des Ombres"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LaCavernedesOmbres.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LaCavernedesOmbresInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LaCavernedesOmbresSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "La Porte de Fer"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LaPortedeFer.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LaPortedeFerInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LaPortedeFerSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "La Porte d'Or"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LaPortedOr.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LaPortedOrInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LaPortedOrSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "Les Falaises de l'Oubli"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LesFalaisesdelOubli.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LesFalaisesdelOubliInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LesFalaisesdelOubliSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "Le Palais de Corail"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LePalaisdeCorail.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LePalaisdeCorailInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LePalaisdeCorailSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "La Porte d'Argent"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LaPortedArgent.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LaPortedArgentInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LaPortedArgentSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "Les Dunes de l'Illusion"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LesDunesdelIllusion.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LesDunesdelIllusionInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LesDunesdelIllusionSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "Heliport"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                Heliport.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                HeliportInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                HeliportSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "La Porte de Cuivre"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LaPortedeCuivre.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LaPortedeCuivreInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LaPortedeCuivreSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "Le Jardin des Hurlements"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LeJardindesHurlements.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LeJardindesHurlementsInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LeJardindesHurlementsSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "La Forêt Pourpre"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LaForêtPourpre.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LaForêtPourpreInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LaForêtPourpreSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "Le Lagon Perdu"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LeLagonPerdu.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LeLagonPerduInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LeLagonPerduSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "Le Marais Brumeux"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LeMaraisBrumeux.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LeMaraisBrumeuxInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LeMaraisBrumeuxSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "Observatoire"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                Observatoire.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                ObservatoireInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                ObservatoireSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "Le Rocher Fantôme"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LeRocherFantôme.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LeRocherFantômeInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LeRocherFantômeSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "Le Temple du Soleil"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LeTempleduSoleil.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LeTempleduSoleilInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LeTempleduSoleilSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "Le Temple de la Lune"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LeTempledelaLune.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LeTempledelaLuneInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LeTempledelaLuneSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "Le Palais des Marées"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LePalaisdesMarées.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LePalaisdesMaréesInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LePalaisdesMaréesSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "Le Val du Crépuscule"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LeValduCrépuscule.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LeValduCrépusculeInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LeValduCrépusculeSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "La Tour du Guet"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LaTourduGuet.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LaTourduGuetInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LaTourduGuetSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.getName() == "Le Jardin des Murmures"){
            if(z.geteTat() == Etat.Normale && !z.getIsSelected()) {
                LeJardindesMurmures.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            else if(z.geteTat() == Etat.inondee && !z.getIsSelected()){
                LeJardindesMurmuresInonde.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
            if(z.getIsSelected()){
                LeJardindesMurmuresSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
            }
        }
        if(z.geteTat() == Etat.submergee && !z.getIsSelected()){
            zoneSubmergee.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
        }
        else if(z.geteTat() == Etat.submergee && z.getIsSelected()){
            zoneSubmergeeSelect.paintIcon(this, g, x + Grille.LARGEUR, y + Grille.HAUTEUR);
        }
        if(z.getGrille().getNbJoueurs() > 0) {
            for(int i = 0; i < z.getJ().size(); i++) {
                if(z.getJ().get(i).getId() == 1) {
                    g.setColor(new Color(	93, 255, 87));
                    g.fillOval(x +Grille.LARGEUR  , y + Grille.HAUTEUR * 8, TAILLE * 2, TAILLE * 4);
                }
                if(z.getJ().get(i).getId() == 2) {
                    g.setColor(new Color(	255, 144, 241 ));
                    g.fillOval(x+ Grille.LARGEUR * 5, y + Grille.HAUTEUR * 8, TAILLE * 2, TAILLE * 4);
                }
                if(z.getJ().get(i).getId() == 3) {
                    g.setColor(new Color(	255, 112, 66));
                    g.fillOval(x + Grille.LARGEUR * 10, y + Grille.HAUTEUR * 8, TAILLE * 2, TAILLE * 4);
                }
                if(z.getJ().get(i).getId() == 4) {
                    g.setColor(new Color(255, 217, 90));
                    g.fillOval(x + Grille.LARGEUR * 14, y + Grille.HAUTEUR * 8, TAILLE * 2, TAILLE * 4);
                }
            }
        }

    }
}
