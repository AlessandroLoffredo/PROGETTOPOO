package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class CreaTeam {
    private JPanel createPanel;
    private JButton signUpButton;
    private JPanel panel;
    private JTextField nickArea;
    private JPanel dataPanel;
    private JLabel nickLabel;
    private JFrame frame;

    public JFrame getFrame() {
        return frame;
    }

    public CreaTeam(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Crea Team");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
