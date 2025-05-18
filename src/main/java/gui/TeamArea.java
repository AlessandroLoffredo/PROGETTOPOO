package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeamArea {
    private JPanel panel;
    private JList participantsList;
    private JList docList;
    private JPanel headerPanel;
    private JPanel docPanel;
    private JPanel participantsPanel;
    private JButton sendButton;
    private JButton loadDocButton;
    private JTextArea nickArea;
    private JButton hackButton;
    private JLabel nickLabel;
    private JLabel hackathonLabel;
    private JTextArea hackathonArea;
    private JTextArea dateArea;
    private JLabel dateLabel;
    private JFrame frame;

    public JFrame getFrame() {
        return frame;
    }

    public TeamArea(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Area Team");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);

        hackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.dispose();

                //BISOGNA FARE VERIFICA SU HACKATHON A CUI SI E' REGISTRATO IL TEAM
            }
        });
    }
}
