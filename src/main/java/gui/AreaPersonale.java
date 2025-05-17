package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AreaPersonale {
    private JPanel panel;
    private JPanel dataPanel;
    private JTextArea fNameArea;
    private JTextArea lNameArea;
    private JTextArea userArea;
    private JLabel fNameLabel;
    private JLabel lNameLabel;
    private JLabel userLabel;
    private JPanel operationPanel;
    private JButton cambiaUsernameButton;
    private JButton cambiaPasswordButton;
    private JPanel participantPanel;
    private JButton inviaRichiestaButton;
    private JComboBox comboBox1;
    private JSplitPane pulsanti;
    private JButton accettaButton;
    private JButton rifiutaButton;
    private JPanel messagePanel;
    private JList requestList;
    private JButton homeButton;
    private JTextArea messageArea;
    private JFrame frame;

    public AreaPersonale(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Area Personale");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 800);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });

        cambiaUsernameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CambiaUsername cambiaUsername = new CambiaUsername(frame, controller);
                frame.setVisible(false);
                cambiaUsername.frame.setVisible(true);
            }
        });
        cambiaPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CambiaPassword cambiaPassword = new CambiaPassword(frame, controller);
                frame.setVisible(false);
                cambiaPassword.frame.setVisible(true);
            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }
}
