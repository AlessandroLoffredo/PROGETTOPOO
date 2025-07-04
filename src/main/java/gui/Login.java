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
    private JLabel titleLabel;
    private JButton signUpButton;
    private JButton lockButton;


    /**
     * Instanzia una nuova Login.
     * <p>
     * La classe Login permette ai visitatori di accedere come utenti, partecipanti, organizzatori, giudici o admin in base alle loro credenziali.
     * </p>
     * @param frameChiamante Il frame che istanzia la nuova AreaPersonaleOrganizzatore
     * @param controller     Il controller istanziato dalla classe Home.java

     */
    public Login(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Login");
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setMaximumSize(new Dimension(500, 500));
        frame.setLocationRelativeTo(null);



        panel.setBackground(new Color(30, 30, 47));
        dataPanel.setBackground(new Color(30, 30, 47));
        usernamePanel.setBackground(new Color(30, 30, 47));
        buttonPanel.setBackground(new Color(30, 30, 47));
        passwordPanel.setBackground(new Color(30, 30, 47));
        usernameLabel.setBackground(new Color(30, 30, 47));
        passwordLabel.setBackground(new Color(30, 30, 47));

        usernameLabel.setForeground(new Color(236, 240, 241));
        loginButton.setForeground(new Color(37, 99, 235));
        passwordLabel.setForeground(new Color(236, 240, 241));
        titleLabel.setForeground(new Color(236, 240, 241));
        signUpButton.setForeground(new Color(37, 99, 235));

        lockButton.setText("\uD83D\uDD12");
        lockButton.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        lockButton.setBackground(new Color(30, 30, 47));
        lockButton.setForeground(new Color(30, 30, 47));





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
                        controller.getHome().getAreaPersonaleButton().setEnabled(true);
                        controller.getHome().getLoginButton().setText("Logout");
                        frameChiamante.setEnabled(true);
                        frame.dispose();
                    }
                } catch (Exception ex){  //Creiamo classe Exception nuova?
                    JOptionPane.showMessageDialog(panel, ex.getMessage());
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                frameChiamante.setEnabled(true);
                frame.dispose();
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp signUp = new SignUp(frameChiamante, controller);
                signUp.getFrame().setVisible(true);
                frame.dispose();
                signUp.getFrame().setAlwaysOnTop(true);
                signUp.getFrame().requestFocus();
                signUp.getFrame().setVisible(true);
                signUp.getFrame().addWindowFocusListener(new WindowAdapter() {
                    @Override
                    public void windowLostFocus(WindowEvent e) {
                        signUp.getFrame().toFront();
                        signUp.getFrame().requestFocus();
                    }
                });
            }
        });

        lockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lockButton.getText().equals("\uD83D\uDD12")){
                    lockButton.setText("\uD83D\uDD13");
                    passwordArea.setEchoChar((char) 0);
                }
                else {
                    lockButton.setText("\uD83D\uDD12");
                    passwordArea.setEchoChar('•');
                }
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
