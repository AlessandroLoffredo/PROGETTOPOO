package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp {
    private JButton signUpButton;
    private JTextField fNameArea;
    private JTextField lNameArea;
    private JTextField usernameArea;
    private JTextField passwordArea;
    private JPanel panel;
    private JPanel NamePanel;
    private JPanel signUpPanel;
    private JLabel fNameLabel;
    private JLabel lNameLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JRadioButton ageRadioButton;
    private JTextField ageArea;
    public JFrame frame;

    public SignUp(JFrame frameChiamante){
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
        panel.add(signUpButton, gbc);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
    }
}
