package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import controller.*;


/**
 * La classe che contiene tutti gli elementi che permettono ad un utente di inserire le sue credenziali e quindi di eseguire il LogIn
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
     * Istanzia un nuovo Login
     *
     * @param frameChiamante il frame da cui si accede alla pagina Login
     * @param controller     il controller istanziato nella home
     */
    public Login(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("HackManager");
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setMaximumSize(new Dimension(500, 500));
        frame.setLocationRelativeTo(null);
        
        String emoji = "\uD83D\uDD12";

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

        lockButton.setText(emoji);
        lockButton.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        lockButton.setBackground(new Color(30, 30, 47));
        lockButton.setForeground(new Color(236, 240, 241));


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logIn(controller, frameChiamante);
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
                JFrame signUpFrame = signUp.getFrame();

                // Configurazione iniziale
                frame.dispose();
                signUpFrame.setAlwaysOnTop(true);
                signUpFrame.setVisible(true);

                // Gestione intelligente del focus
                signUpFrame.addWindowStateListener(new WindowStateListener() {
                    @Override
                    public void windowStateChanged(WindowEvent e) {
                        if ((e.getNewState() & Frame.ICONIFIED) == 0) {
                            // Solo se NON è iconizzata, mantieni il focus
                            signUpFrame.toFront();
                            signUpFrame.requestFocus();
                        }
                    }
                });

                signUpFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        frameChiamante.setEnabled(true);
                        frameChiamante.toFront();
                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {
                        // Quando viene ripristinata dalla barra delle applicazioni
                        signUpFrame.toFront();
                        signUpFrame.requestFocus();
                    }
                });
            }
        });

        lockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lockButton.getText().equals(emoji)) {
                    lockButton.setText("\uD83D\uDD13");
                    passwordArea.setEchoChar((char) 0);
                } else {
                    lockButton.setText(emoji);
                    passwordArea.setEchoChar('*');
                }
            }
        });


        passwordArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    loginButton.doClick();
                }
            }
        });
    }

    /**
     * Restituisce il frame che viene creato quando viene istanziata la pagina Login
     *
     * @return il frame di Login
     */
    public JFrame getFrame() {
        return frame;
    }

    private void logIn(Controller controller, JFrame frameChiamante){
        if (usernameArea.getText().isEmpty() || Arrays.toString(passwordArea.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Riempi tutti i campi");
        } else {
            try {
                int code = controller.handleLogin(usernameArea.getText(), passwordArea.getPassword());
                switch (code) {
                    case -1:
                        JOptionPane.showMessageDialog(panel, "Username o password errati");
                        break;
                    case 1, 2, 3, 4, 5:
                        JOptionPane.showMessageDialog(panel, "Accesso riuscito!");
                        controller.getHome().getAreaPersonaleButton().setEnabled(true);
                        controller.getHome().getLoginButton().setText("Logout");
                        frameChiamante.setEnabled(true);
                        frame.dispose();
                        break;
                    default:
                        JOptionPane.showMessageDialog(panel, "Qualcosa è andato storto durante il login");
                        usernameArea.setText("");
                        passwordArea.setText("");
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel, "Qualcosa è andato storto durante il login");
                usernameArea.setText("");
                passwordArea.setText("");
            }
        }
    }
}
