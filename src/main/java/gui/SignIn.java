package gui;

import javax.swing.*;
import java.awt.*;

public class SignIn {
    private JButton signInButton;
    private JTextField fNameArea;
    private JTextField lNameArea;
    private JTextField usernameArea;
    private JTextField passwordArea;
    private JPanel panel;
    private JPanel NamePanel;
    private JPanel signInPanel;
    private JLabel fNameLabel;
    private JLabel lNameLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JRadioButton ageRadioButton;
    private JTextField ageArea;
    public JFrame frame;

    public SignIn(JFrame frameChiamante){
        frame = new JFrame("SignIn");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(500,500));
        frame.setMaximumSize(new Dimension(500,500));
        panel.setLayout(new GridBagLayout());
        usernameArea.setPreferredSize(new Dimension(150, 25));
        passwordArea.setPreferredSize(new Dimension(150, 25));
        fNameArea.setPreferredSize(new Dimension(150, 25));
        lNameArea.setPreferredSize(new Dimension(150, 25));

        frame.setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 2;
        panel.add(signInButton, gbc);
    }
}
