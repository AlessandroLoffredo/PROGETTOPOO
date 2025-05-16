package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.*;

public class Login {
    private JPanel panel;
    private JTextField usernameArea;
    private JPasswordField passwordArea;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JPanel buttonPanel;
    private JPanel dataPanel;
    private JPanel usernamePanel;
    private JPanel passwordPanel;
    public JFrame frame;
    private Controller controller;

    public Login(JFrame frameChiamante) {
        frame = new JFrame("Login");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setMaximumSize(new Dimension(500, 500));
        frame.setLocationRelativeTo(null);



        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MANIPOLARE ACCESSI DIVERSI PER UTENTI E ADMIN, CHE ENTRERANNO CON LO STESSO BUTTON
                /*
                try {
                    int code = controller.handleLogin(usernameArea.getText(), passwordArea.getPassword());
                    if (code == -1) {
                        JOptionPane.showMessageDialog(panel, "Username o password non inseriti");
                    } else if (code == -2) {
                        JOptionPane.showMessageDialog(panel, "Username o password errati");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Accesso riuscito");
                    }
                }
                catch (IllegalArgumentException ex){  //Creiamo classe Exception nuova?
                    System.out.println("Dati non validi");
                }

                 */
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                //JButton bottone = (JButton) frameChiamante.getContentPane().getComponentAt(0, 0);
                //bottone.setText("MAMMT");
                frame.dispose();
            }
        });
    }

}
