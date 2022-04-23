package Vue;

import Utils.Etat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VueZone extends JPanel{
    private Integer idZone;
    private JButton zone;
    private JPanel caseJoueur;
    private ArrayList<Color> joueursSurZone;
    private final Color couleur = Color.lightGray;
    private final Color couleurInondee = Color.cyan;
    private final Color couleurCoulee = Color.BLUE;
    private Etat etat;


    public VueZone(Integer id, String nom, Etat etat, ArrayList<Color> joueursSurZone) {
        this.idZone = id;
        this.etat = etat;
        this.joueursSurZone = joueursSurZone;
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        this.zone = new JButton(nom);
        this.add(zone, BorderLayout.CENTER);

        JPanel panelBas = new JPanel(new GridLayout());
        this.add(panelBas, BorderLayout.SOUTH);
        this.caseJoueur = new JPanel();
        setCasesJoueurs();
    }
    public void setCasesJoueurs(){
        int i = 0;
        while(i <= joueursSurZone.size()-1){
            caseJoueur.setBackground(joueursSurZone.get(i));
            i++;
        }
    }
}
