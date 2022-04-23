package Vue;

import Controler.Observers;
import Modele.Artefact;
import Modele.Grille;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VueCartes extends JPanel implements Observers {
    private Grille grille;
    private JPanel pile;
    private JPanel defausse;
    private JLabel imageIcon;
    BufferedImage pierre;
    BufferedImage calice;
    BufferedImage zephir;
    BufferedImage cristal;
    BufferedImage defausseVide;
    BufferedImage pileRempli;
    BufferedImage heli;
    BufferedImage monteEau;
    BufferedImage sandBag;


    public VueCartes(Grille grille){
        this.grille = grille;
        this.setLayout(new FlowLayout());
        this.pile = new JPanel();
        this.defausse = new JPanel();
        this.imageIcon = new JLabel();
        this.paints();
        if(this.grille.getPileCartes().size() > 0) {
            this.imageIcon = new JLabel(new ImageIcon(pileRempli));
            this.add(imageIcon);
        }else{
            this.pile.setBackground(new Color(119, 119, 119));
        }
        if(this.grille.getDefausseCartes().size() > 0) {
            if (this.grille.getDefausseCartes().get(this.grille.getDefausseCartes().size() - 1).getNom() == Artefact.ZEPHYR.getNom()) {
                this.imageIcon = new JLabel(new ImageIcon(zephir));
                this.add(imageIcon);
            } else if (this.grille.getDefausseCartes().get(this.grille.getDefausseCartes().size() - 1).getNom() == Artefact.PIERRE.getNom()) {
                this.imageIcon = new JLabel(new ImageIcon(pierre));
                this.add(imageIcon);
            } else if (this.grille.getDefausseCartes().get(this.grille.getDefausseCartes().size() - 1).getNom() == Artefact.CRISTAL.getNom()) {
                this.imageIcon = new JLabel(new ImageIcon(cristal));
                this.add(imageIcon);
            } else if (this.grille.getDefausseCartes().get(this.grille.getDefausseCartes().size() - 1).getNom() == Artefact.CALICE.getNom()) {
                this.imageIcon = new JLabel(new ImageIcon(calice));
                this.add(imageIcon);
            } else if (this.grille.getDefausseCartes().get(this.grille.getDefausseCartes().size() - 1).getNom() == "Hélicoptère") {
                this.imageIcon = new JLabel(new ImageIcon(heli));
                this.add(imageIcon);
            } else if (this.grille.getDefausseCartes().get(this.grille.getDefausseCartes().size() - 1).getNom() == "Montée des eaux") {
                this.imageIcon = new JLabel(new ImageIcon(monteEau));
                this.add(imageIcon);
            } else if (this.grille.getDefausseCartes().get(this.grille.getDefausseCartes().size() - 1).getNom() == "Sacs De Sable") {
                this.imageIcon = new JLabel(new ImageIcon(sandBag));
                this.add(imageIcon);
            }
        }
        else{
            this.imageIcon = new JLabel(new ImageIcon(defausseVide));
            this.add(imageIcon);
        }
        this.add(this.defausse);
        this.add(this.pile);
    }

    public void update() {
        this.removeAll();
        this.paints();
        this.pile = new JPanel();
        this.defausse = new JPanel();
        if(this.grille.getPileCartes().size() > 0) {
            this.imageIcon = new JLabel(new ImageIcon(pileRempli));
            this.add(imageIcon);
        }else{
            this.pile.setBackground(new Color(119, 119, 119));
        }
        if(this.grille.getDefausseCartes().size() > 0) {
            if (this.grille.getDefausseCartes().get(this.grille.getDefausseCartes().size() - 1).getNom() == Artefact.ZEPHYR.getNom()) {
                this.imageIcon = new JLabel(new ImageIcon(zephir));
                this.add(imageIcon);
            } else if (this.grille.getDefausseCartes().get(this.grille.getDefausseCartes().size() - 1).getNom() == Artefact.PIERRE.getNom()) {
                this.imageIcon = new JLabel(new ImageIcon(pierre));
                this.add(imageIcon);
            } else if (this.grille.getDefausseCartes().get(this.grille.getDefausseCartes().size() - 1).getNom() == Artefact.CRISTAL.getNom()) {
                this.imageIcon = new JLabel(new ImageIcon(cristal));
                this.add(imageIcon);
            } else if (this.grille.getDefausseCartes().get(this.grille.getDefausseCartes().size() - 1).getNom() == Artefact.CALICE.getNom()) {
                this.imageIcon = new JLabel(new ImageIcon(calice));
                this.add(imageIcon);
            } else if (this.grille.getDefausseCartes().get(this.grille.getDefausseCartes().size() - 1).getNom() == "Hélicoptère") {
                this.imageIcon = new JLabel(new ImageIcon(heli));
                this.add(imageIcon);
            } else if (this.grille.getDefausseCartes().get(this.grille.getDefausseCartes().size() - 1).getNom() == "Montée des eaux") {
                this.imageIcon = new JLabel(new ImageIcon(monteEau));
                this.add(imageIcon);
            } else if (this.grille.getDefausseCartes().get(this.grille.getDefausseCartes().size() - 1).getNom() == "Sacs De Sable") {
                this.imageIcon = new JLabel(new ImageIcon(sandBag));
                this.add(imageIcon);
            }
        }
        else{
            this.imageIcon = new JLabel(new ImageIcon(defausseVide));
            this.add(imageIcon);
        }
        this.add(this.defausse);
        this.add(this.pile);
    }

    public void paints() {
        try {
            this.pierre = ImageIO.read(getClass().getClassLoader().getResource("Image/cartePierre.png"));
            this.calice = ImageIO.read(getClass().getClassLoader().getResource("Image/carteCalice.png"));
            this.zephir = ImageIO.read(getClass().getClassLoader().getResource("Image/carteZephir.png"));
            this.cristal = ImageIO.read(getClass().getClassLoader().getResource("Image/carteCristal.png"));
            this.heli = ImageIO.read(getClass().getClassLoader().getResource("Image/carteHeli.png"));
            this.monteEau = ImageIO.read(getClass().getClassLoader().getResource("Image/carteMonteEau.jpeg"));
            this.defausseVide = ImageIO.read(getClass().getClassLoader().getResource("Image/carteDefausse.png"));
            this.pileRempli = ImageIO.read(getClass().getClassLoader().getResource("Image/cartePile.png"));
            this.sandBag = ImageIO.read(getClass().getClassLoader().getResource("Image/carteSandBag.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Graphics2D g = (Graphics2D) this.pierre.getGraphics();
        g.drawRect(this.defausse.getX() * 10, this.defausse.getY() *10, this.pierre.getWidth(), this.pierre.getHeight());
        g = (Graphics2D) this.cristal.getGraphics();
        g.drawRect(this.defausse.getX() * 10, this.defausse.getY() *10, this.cristal.getWidth(), this.cristal.getHeight());
        g = (Graphics2D) this.zephir.getGraphics();
        g.drawRect(this.defausse.getX() * 10, this.defausse.getY() *10, this.zephir.getWidth(), this.zephir.getHeight());
        g = (Graphics2D) this.calice.getGraphics();
        g.drawRect(this.defausse.getX() * 10, this.defausse.getY() *10, this.calice.getWidth(), this.calice.getHeight());

        g = (Graphics2D) this.heli.getGraphics();
        g.drawRect(this.defausse.getX() * 10, this.defausse.getY() *10, this.heli.getWidth(), this.heli.getHeight());
        g = (Graphics2D) this.monteEau.getGraphics();
        g.drawRect(this.defausse.getX() * 10, this.defausse.getY() *10, this.monteEau.getWidth(), this.monteEau.getHeight());
        g = (Graphics2D) this.pileRempli.getGraphics();
        g.drawRect(this.defausse.getX() * 10, this.defausse.getY() *10, this.pileRempli.getWidth(), this.pileRempli.getHeight());
        g = (Graphics2D) this.sandBag.getGraphics();
        g.drawRect(this.defausse.getX() * 10, this.defausse.getY() *10, this.sandBag.getWidth(), this.sandBag.getHeight());
        g = (Graphics2D) this.defausseVide.getGraphics();
        g.drawRect(this.defausse.getX() * 10, this.defausse.getY() *10, this.defausseVide.getWidth(), this.defausseVide.getHeight());
    }
}
