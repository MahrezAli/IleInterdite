package Vue;

import Modele.Grille;

import javax.swing.*;
import java.awt.*;

public class VueJeu extends JFrame {
    private JFrame frame;
    private VueGrille grille;
    private VueCommandes commandes;
    private VueJoueur joueur;
    private VueCartes cartes;
    int TAILLE = 10;


    public VueJeu(Grille maGrille) {
        //init
        this.frame = new JFrame();
        this.frame.setTitle("L'Ile interdite");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(6 * this.TAILLE * 22, 6 * this.TAILLE * 12));
        this.frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //build
        this.grille = new VueGrille(maGrille);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.frame.add(this.grille,c);

        this.cartes = new VueCartes(maGrille);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        this.frame.add(this.cartes,c);
    }
    public void init(){
        //apparition fenetre
        this.frame.pack();
        this.frame.setVisible(true);
        this.frame.repaint();
    }

    public void setCommandes(VueCommandes commandes) {
        this.commandes = commandes;
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        this.frame.add(this.commandes,c);
    }

    public void setJoueur(VueJoueur joueur) {
        this.joueur = joueur;
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        this.frame.add(this.joueur,c);
    }

    public VueJoueur getJoueur() {
        return joueur;
    }

    public void endGameScreen(String s) {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException ie){
        }
        this.frame.remove(this);
        JLabel label = new JLabel(s);
        this.frame.getContentPane().removeAll();
        label.setFont(new Font("Verdana", 1, 15));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        this.frame.getContentPane().add(label);
        this.frame.repaint();
    }

    public VueCartes getCartes() {
        return cartes;
    }
}
