package Vue;

import Controler.Controleur;
import Modele.Grille;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;


public class VueCreerJ extends JPanel implements ActionListener{
    private JFrame frame;
    private JTextField text1,text2,text3,text4;
    private JButton bouton;
    private String nom1,nom2,nom3,nom4;
    private boolean confirmer = false;
    private int nbJoueurs;

    public VueCreerJ(int num){
        this.frame = new JFrame();
        this.frame.setTitle("L'Ile interdite");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(3 * 10 * 12, 3 * 10 * 12));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        //instanciation zone de text
        this.text1 = new JTextField(20);
        this.text1.setBorder(BorderFactory.createLineBorder(new Color(	93, 255, 87), 2));
        this.text2 = new JTextField(20);
        this.text2.setBorder(BorderFactory.createLineBorder(new Color(	255, 144, 241 ), 2));
        this.text3 = new JTextField(20);
        this.text3.setBorder(BorderFactory.createLineBorder(new Color(	255, 112, 66), 2));
        this.text4 =new JTextField(20);
        this.text4.setBorder(BorderFactory.createLineBorder(new Color(255, 217, 90), 2));

        //Bouton pour confirmer
        this.bouton = new JButton("Confirmer");
        this.bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand() == "Confirmer") {
                    nom1 = text1.getText();
                    nom2 = text2.getText();
                    nom3 = text3.getText();
                    nom4 = text4.getText();
                    Grille grille = new Grille(num);
                    if(num == 2){
                        grille.getJoueur().get(1).setNom(nom1);
                        grille.getJoueur().get(2).setNom(nom2);
                    }
                    else if(num == 3){
                        grille.getJoueur().get(1).setNom(nom1);
                        grille.getJoueur().get(2).setNom(nom2);
                        grille.getJoueur().get(3).setNom(nom3);
                    }
                    else if(num == 4){
                        grille.getJoueur().get(1).setNom(nom1);
                        grille.getJoueur().get(2).setNom(nom2);
                        grille.getJoueur().get(3).setNom(nom3);
                        grille.getJoueur().get(4).setNom(nom4);
                    }
                    VueJeu vue = new VueJeu(grille);
                    frame.dispose();
                    Controleur ctrl = new Controleur(grille, grille.getJoueur(), vue);
                    VueJoueur joueur = new VueJoueur(grille, ctrl.getJoueurActuel());
                    vue.setCommandes(new VueCommandes(grille, ctrl));
                    vue.setJoueur(joueur);
                    ctrl.setJoueurActuel(joueur);
                    vue.init();
                }
            }
        });

        //Placement case d'Ã©criture
        if (num == 1) {
            c.gridx = 0;
            c.gridy = 0;
            this.add(text1, c);
        }
        if (num == 2) {
            c.gridx = 0;
            c.gridy = 0;
            this.add(text1, c);
            c.gridx = 0;
            c.gridy = 1;
            this.add(text2, c);
        }
        if(num == 3) {
            c.gridx = 0;
            c.gridy = 0;
            this.add(text1, c);
            c.gridx = 0;
            c.gridy = 1;
            this.add(text2, c);
            c.gridx = 0;
            c.gridy = 2;
            this.add(text3, c);
        }
        if (num == 4) {
            c.gridx = 0;
            c.gridy = 0;
            this.add(text1, c);
            c.gridx = 0;
            c.gridy = 1;
            this.add(text2, c);
            c.gridx = 0;
            c.gridy = 2;
            this.add(text3, c);
            c.gridx = 0;
            c.gridy = 3;
            this.add(text4,c);
        }
        //Placement Boutons
        c.gridx = 0;
        c.gridy = 4;
        this.add(bouton,c);
        this.frame.add(this);
        this.frame.pack();
        this.frame.setVisible(true);
        this.frame.repaint();
    }

    public void actionPerformed(ActionEvent e) {}

}
