package gui;

import javax.swing.*;
import java.awt.*;

public class Login {
    private JPanel panel;
    private JTextField usernameArea;
    private JPasswordField passwordArea;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JPanel usernamePanel;
    private JPanel passwordPanel;
    private JPanel buttonPanel;
    public JFrame frame;


    public Login(JFrame frameChiamante){
        frame = new JFrame("Login");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(500,500));
        frame.setMaximumSize(new Dimension(500,500));
        frame.setLocationRelativeTo(null);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0; // Posizione X (colonna)
        gbc.gridy = 0; // Posizione Y (riga)
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        panel.add(usernameLabel, gbc);

        gbc.gridy = 1;
        panel.add(usernameArea, gbc);

        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        gbc.gridy = 3;
        panel.add(passwordArea, gbc);

        gbc.gridy = 4;
        panel.add(loginButton, gbc);

        usernameArea.setPreferredSize(new Dimension(150, 25)); // 🔹 Imposta larghezza e altezza
        passwordArea.setPreferredSize(new Dimension(150, 25));

        gbc.fill = GridBagConstraints.HORIZONTAL; // 🔹 Espande il campo orizzontalmente
        gbc.weightx = 1.0; // 🔹 Occupa tutto lo spazio disponibile

    }

}
