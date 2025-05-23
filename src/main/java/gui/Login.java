package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.*;

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
    private JFrame frame;

    public JFrame getFrame() {
        return frame;
    }

    public Login(JFrame frameChiamante, Controller controller, Home home) {
        frame = new JFrame("Login");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setMaximumSize(new Dimension(500, 500));
        frame.setLocationRelativeTo(null);



        panel.setBackground(new Color(10, 10, 30)); // Blu notte/nero futuristico
        dataPanel.setBackground(new Color(15, 15, 50));
        usernamePanel.setBackground(new Color(15, 15, 50));
        buttonPanel.setBackground(new Color(15, 15, 50));
        passwordPanel.setBackground(new Color(15, 15, 50));// Leggermente più chiaro
        usernameLabel.setBackground(new Color(15, 15, 50));// Leggermente più chiaro
        passwordLabel.setBackground(new Color(15, 15, 50));

        usernameLabel.setForeground(new Color(0, 255, 0)); // Verde neon tipo Matrix
        loginButton.setForeground(new Color(0, 200, 255)); // Azzurro cyber
        passwordLabel.setForeground(new Color(0, 255, 0)); // Verde neon tipo Matrix






        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MANIPOLARE ACCESSI DIVERSI PER UTENTI E ADMIN, CHE ENTRERANNO CON LO STESSO BUTTON
                try {
                    int code = controller.handleLogin(usernameArea.getText(), passwordArea.getPassword());
                    if (code == -1) {
                        JOptionPane.showMessageDialog(panel, "Riempi tutti i campi");
                    } else if (code == -2) {
                        JOptionPane.showMessageDialog(panel, "Username o password errati");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Accesso riuscito!");
                        home.getAreaPersonaleButton().setEnabled(true);
                        home.getLoginButton().setText("Logout");
                        frameChiamante.setVisible(true);
                        frame.dispose();
                    }
                } catch (Exception ex){  //Creiamo classe Exception nuova?
                    JOptionPane.showMessageDialog(panel, ex.getMessage());
                }


            }
        });
    }
}
