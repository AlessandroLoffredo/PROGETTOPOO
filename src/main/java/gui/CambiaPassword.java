package gui;

import javax.swing.*;
import java.awt.*;

public class CambiaPassword {
    private JPanel oldPasswordPanel;
    private JTextField oldPasswordArea;
    private JLabel oldPasswordLabel;
    private JPanel passwordPanel;
    private JPasswordField passwordArea;
    private JLabel passwordLabel;
    private JPanel buttonPanel;
    private JButton changeButton;
    private JPanel newPasswordPanel;
    private JPasswordField newPasswordArea;
    private JLabel newPasswordLabel;
    private JPanel panel;
    public JFrame frame;

    public CambiaPassword(JFrame frameChiamante) {
        frame = new JFrame("Cambia username");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);

    }
}
