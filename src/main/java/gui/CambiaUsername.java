package gui;

import javax.swing.*;
import java.awt.*;

public class CambiaUsername {
    private JPanel panel;
    private JPanel usernamePanel;
    private JTextField usernameArea;
    private JLabel usernameLabel;
    private JPanel passwordPanel;
    private JPasswordField passwordArea;
    private JLabel passwordLabel;
    private JPanel buttonPanel;
    private JButton changeButton;
    public JFrame frame;

    public CambiaUsername(JFrame frameChiamante){
        frame = new JFrame("Cambia username");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);

    }


}
