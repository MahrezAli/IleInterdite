package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;

public class VueMenuSelectJ extends JPanel implements ChangeListener {
    int res;
    private JFrame frame;
    private JLabel label;
    private JSlider slider;
    private JButton bouton;
    boolean select = false;
    private VueCreerJ J;
    private boolean confirmer = false;

    public VueMenuSelectJ(){
        this.frame = new JFrame();
        this.frame.setTitle("L'Ile interdite");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(3 * 10 * 12, 3 * 10 * 12));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        this.slider = new JSlider(2,4,2);
        this.label = new JLabel();

        this.bouton = new JButton("Confirmer");
        this.bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                res = slider.getValue();
                select = true;
                if(e.getActionCommand() == "Confirmer"){
                    J = new VueCreerJ(res);
                    frame.dispose();
                }
            }
        });

        //peindre le track + l etiquette
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        //definit l'écartement des chiffres
        slider.setMajorTickSpacing(1);
        label.setText("Sélectionner Nombre de Joueurs : ");

        c.gridx = 0;
        c.gridy = 1;
        this.add(slider,c);

        c.gridx = 0;
        c.gridy = 0;
        this.add(label,c);

        c.gridx =0;
        c.gridy = 2;
        this.add(bouton, c);
        this.frame.add(this);
        this.frame.pack();
        this.frame.setVisible(true);
        this.frame.repaint();
    }

    public int getRes() {
        return this.res;
    }

    public boolean getSelect(){
        return select;
    }

    public void stateChanged(ChangeEvent e)
    {
        label.setText("Nombre de Joueurs : " + slider.getValue());
    }

    public VueCreerJ getJ() {
        return J;
    }

    public boolean isConfirmer() {
        return confirmer;
    }
     public void setConfirmer(boolean x){
        this.confirmer = x;
     }
}
