package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che permette di creare un Team.
 */
public class CreaTeam {
    private JPanel createPanel;
    private JButton signUpButton;
    private JPanel panel;
    private JTextField nickArea;
    private JPanel dataPanel;
    private JLabel nickLabel;
    private JFrame frame;



    /**
     * Instanzia una nuova CreaTeam.
     * <p>
     * La classe CreaTeam permette ai partecipanti ad un Hackathon di inserire le informazioni necessarie per la creazione di un Team.
     * </p>
     * @param frameChiamante il frame che istanzia la nuova AreaPersonaleOrganizzatore
     * @param controller     Il controller istanziato dalla classe Home.java
     */
    public CreaTeam(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Crea Team");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Restituisce il frame principale della gui.
     *
     * @return JFrame: Il frame principale.
     */
    public JFrame getFrame() {
        return frame;
    }
}
