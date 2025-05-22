package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp {
    private JButton signUpButton;
    private JTextField fNameArea;
    private JTextField lNameArea;
    private JTextField usernameArea;
    private JPasswordField passwordArea;
    private JPanel panel;
    private JPanel namePanel;
    private JPanel signUpPanel;
    private JLabel fNameLabel;
    private JLabel lNameLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JRadioButton ageRadioButton;
    private JFrame frame;

    public JFrame getFrame() {
        return frame;
    }

    public SignUp(JFrame frameChiamante, Controller controller){
        frame = new JFrame("SignUp");
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

        frame.setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 2;
        panel.add(ageRadioButton, gbc);
        gbc.gridy = 3;
        panel.add(signUpButton, gbc);


        panel.setBackground(new Color(10, 10, 30));
        namePanel.setBackground(new Color(15, 15, 50));
        signUpPanel.setBackground(new Color(15, 15, 50));

        fNameLabel.setForeground(new Color(0, 255, 0));
        lNameLabel.setForeground(new Color(0, 255, 0));
        usernameLabel.setForeground(new Color(0, 255, 0));
        passwordLabel.setForeground(new Color(0, 255, 0));
        signUpButton.setForeground(new Color(255, 0, 150));
        ageRadioButton.setForeground(new Color(0, 255, 0));

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int code = controller.handleSignUp(usernameArea.getText(), passwordArea.getPassword(), fNameArea.getText(), lNameArea.getText());
                    if (code == -1) {
                        JOptionPane.showMessageDialog(panel, "Il nome ed il cognome non devono superare i 20 caratteri");
                    } else if (code == -2) {
                        JOptionPane.showMessageDialog(panel, "L'username deve avere tra i 3 ed i 15 caratteri\nLa password deve avere tra gli 8 ed i 16 caratteri");
                    }else if(code == -3){
                        JOptionPane.showMessageDialog(panel, "Riempi tutti i campi");
                    } else if(!ageRadioButton.isSelected()){
                        JOptionPane.showMessageDialog(panel,"Devi confermare di avere più di 16 anni");
                    }else {
                        JOptionPane.showMessageDialog(panel, "Registrazione completata!");
                        frameChiamante.setVisible(true);
                        frame.dispose();
                    }
                }
                catch (IllegalArgumentException ex){  //Creiamo classe Exception nuova?
                    System.out.println("Qualcosa è andato storto durante la registrazione");
                }


            }
        });
    }
}
