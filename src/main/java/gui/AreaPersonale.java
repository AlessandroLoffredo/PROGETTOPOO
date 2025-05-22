package gui;

import controller.Controller;
import model.Participant;

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
    private JComboBox participantComboBox;
    private JSplitPane pulsanti;
    private JButton accettaButton;
    private JButton rifiutaButton;
    private JPanel messagePanel;
    private JList requestList;
    private JButton homeButton;
    private JTextField messageArea;
    private JPanel teamPanel;
    private JLabel teamLabel;
    private JTextArea textArea1;
    private JButton teamButton;
    private JFrame frame;

    public AreaPersonale(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Area Personale");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);

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
                cambiaUsername.getFrame().setVisible(true);
            }
        });
        cambiaPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CambiaPassword cambiaPassword = new CambiaPassword(frame, controller);
                frame.setVisible(false);
                cambiaPassword.getFrame().setVisible(true);
            }
        });

        teamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TeamArea teamArea;
                teamArea = new TeamArea(frameChiamante, controller);
                teamArea.getFrame().setVisible(true);
                frame.dispose();
            }
        });
        /*
        INSERIRE CONTROLLO PER VEDERE SE IL PARTECIPANTE FA PARTE DI UN TEAM...
        OSCURARE L'AREA TEAM QUALORA NON NE FACCIA ANCORA PARTE
         */
        /*inviaRichiestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.sendRequest(messageArea.getText(), (String) participantComboBox.getSelectedItem());
            }
        });*/
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getTeamPanel() {
        return teamPanel;
    }

    public JPanel getMessagePanel() {
        return messagePanel;
    }

    public JPanel getParticipantPanel() {
        return participantPanel;
    }
}
