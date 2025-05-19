package gui;

import controller.Controller;
import model.Hackathon;

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
    private JButton homeButton;
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
        frame.setSize(950, 800);
        frame.setLocationRelativeTo(null);

        hackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HackathonGui hackathonGui = new HackathonGui(frame, controller);
                hackathonGui.getFrame().setVisible(true);
                frame.dispose();

                //BISOGNA FARE VERIFICA SU HACKATHON A CUI SI E' REGISTRATO IL TEAM
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getHome().setVisible(true);
                frame.dispose();
            }
        });
        loadDocButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
            }
        });

    }
}
