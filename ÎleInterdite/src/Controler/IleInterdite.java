package Controler;

import Modele.Grille;
import Vue.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.Timer;

public class IleInterdite {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            VueMenuSelectJ nbJoueurs = new VueMenuSelectJ();
        });
    }
}
