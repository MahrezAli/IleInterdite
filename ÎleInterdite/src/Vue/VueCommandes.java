package Vue;

import Controler.Controleur;
import Modele.Grille;
import Modele.Joueur;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class VueCommandes extends JPanel  {

    private Grille grille;
    private HashMap<Integer,Joueur> joueurs;
    private Controleur ctrl;

    public VueCommandes(Grille grille, Controleur controleur) {
        this.grille = grille;
        this.joueurs = this.grille.getJoueur();
        //System.out.println(this.grille.getJoueur().size());
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.setSize(400,100);
        JButton asseche = new JButton("Assecher la zone");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.add(asseche, c);

        JButton finDeTour = new JButton("Fin de tour !");
        c.gridx = 0;
        c.gridy = 1;
        this.add(finDeTour,c);

        JButton left = new JButton("Left");
        c.gridx = 1;
        c.gridy = 1;
        this.add(left,c);

        JButton up = new JButton("Up");
        c.gridx = 2;
        c.gridy = 0;
        this.add(up,c);


        JButton right = new JButton("Right");
        c.gridx = 3;
        c.gridy = 1;
        this.add(right,c);

        JButton down = new JButton("Down");
        c.gridx = 2;
        c.gridy = 2;
        this.add(down,c);

        JButton prendreArtefact = new JButton("Récuperer Artefact");
        c.gridx = 0;
        c.gridy = 2;
        this.add(prendreArtefact,c);

        JButton donnerCarte = new JButton("Donner une carte");
        c.gridx = 4;
        c.gridy = 0;
        this.add(donnerCarte,c);

        JButton retirerCarte = new JButton("Défausser une carte");
        c.gridx = 4;
        c.gridy = 1;
        this.add(retirerCarte,c);

        JButton utiliser = new JButton("Utiliser");
        c.gridx = 4;
        c.gridy = 2;
        this.add(utiliser,c);

        JButton deplacerNavig = new JButton("deplace Navigateur");
        c.gridx = 2;
        c.gridy = 1;
        this.add(deplacerNavig,c);

        this.ctrl = controleur;
        asseche.addActionListener(ctrl);
        asseche.addKeyListener(ctrl);
        finDeTour.addActionListener(ctrl);
        left.addActionListener(ctrl);
        left.addKeyListener(ctrl);
        down.addActionListener(ctrl);
        down.addKeyListener(ctrl);
        up.addActionListener(ctrl);
        up.addKeyListener(ctrl);
        right.addActionListener(ctrl);
        right.addKeyListener(ctrl);
        prendreArtefact.addActionListener(ctrl);
        prendreArtefact.addKeyListener(ctrl);
        donnerCarte.addActionListener(ctrl);
        donnerCarte.addKeyListener(ctrl);
        retirerCarte.addActionListener(ctrl);
        retirerCarte.addKeyListener(ctrl);
        utiliser.addActionListener(ctrl);
        utiliser.addKeyListener(ctrl);
        deplacerNavig.addKeyListener(ctrl);
        deplacerNavig.addActionListener(ctrl);
    }
    public Controleur getCtrl(){
        return ctrl;
    }
}
