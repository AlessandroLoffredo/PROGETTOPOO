package gui;

import javax.swing.*;
import java.awt.*;

public class CreaHackathon {
    private JPanel dataPanel;
    private JLabel titleLabel;
    private JTextField titleArea;
    private JLabel startLabel;
    private JLabel endLabel;
    private JLabel venueLable;
    private JTextField venueArea;
    private JPanel createPanel;
    private JButton createButton;
    private JPanel panel;
    private JSpinner startSpinner;
    private JSpinner endSpinner;
    private JComboBox comboBox1;
    private JLabel maxTeamPartLabel;
    private JLabel maxParticipantLabel;
    private JTextField textField1;
    private JPanel prova;
    public JFrame frame;

    public CreaHackathon(JFrame frameChiamante) {
        frame = new JFrame("Crea Hackathon");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setMaximumSize(new Dimension(500, 500));
        panel.setLayout(new GridBagLayout());

        titleArea.setPreferredSize(new Dimension(150, 25));
        venueArea.setPreferredSize(new Dimension(150, 25));


        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 2;
        panel.add(createButton, gbc);
    }
}
