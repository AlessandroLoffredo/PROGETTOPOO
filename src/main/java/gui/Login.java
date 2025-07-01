package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import controller.*;


/**
 * Classe che permette di effettuare il login.
 */
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



    /**
     * Instanzia una nuova Login.
     * <p>
     * La classe Login permette ai visitatori di accedere come utenti, partecipanti, organizzatori, giudici o admin in base alle loro credenziali.
     * </p>
     * @param frameChiamante Il frame che istanzia la nuova AreaPersonaleOrganizzatore
     * @param controller     Il controller istanziato dalla classe Home.java
     * @param home           L'oggetto Home istanziato all'inizio dell'avvio del programma
     */
    public Login(JFrame frameChiamante, Controller controller, Home home) {
        frame = new JFrame("Login");
        frame.setContentPane(panel);
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
                /*try {
                    int code = controller.handleLogin(usernameArea.getText(), passwordArea.getPassword());
                    JOptionPane.showMessageDialog(panel, "Accesso riuscito!");
                    home.getAreaPersonaleButton().setEnabled(true);
                    home.getLoginButton().setText("Logout");
                    frameChiamante.setVisible(true);
                    frame.dispose();
                } catch (Exception ex){  //Creiamo classe Exception nuova?
                    JOptionPane.showMessageDialog(panel, ex.getMessage());
                }*/
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
    }

    /**
     * Restituisce il frame principale della gui.
     *
     * @return JFrame: Il frame principale.
     */
    public JFrame getFrame() {
        return frame;


    }
}
