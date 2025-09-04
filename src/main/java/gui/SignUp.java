package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

/**
 * The type Sign up.
 */
public class SignUp {
    private JPanel panel;
    private JPanel namePanel;
    private JPanel agePanel;
    private JPanel buttonPanel;
    private JPanel dataPanel;
    private JTextField fNameArea;
    private JTextField lNameArea;
    private JTextField usernameArea;
    private JPasswordField passwordArea;
    private JRadioButton ageRadioButton;
    private JButton signUpButton;
    private JLabel fNameLabel;
    private JLabel lNameLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel titleLabel;
    private JButton lockButton;
    private JButton logInButton;
    private JTextField formatoPassLabel;
    private JFrame frame;


    /**
     * Instantiates a new Sign up.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     */
    public SignUp(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("HackManager");
        frame.setContentPane(panel);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                frameChiamante.setEnabled(true);
                frame.dispose();
            }
        });
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setMaximumSize(new Dimension(500, 500));
        
        String emoji = "\uD83D\uDD12";
        
        usernameArea.setPreferredSize(new Dimension(150, 25));
        passwordArea.setPreferredSize(new Dimension(150, 25));
        fNameArea.setPreferredSize(new Dimension(150, 25));
        lNameArea.setPreferredSize(new Dimension(150, 25));
        frame.setLocationRelativeTo(null);


        panel.setBackground(new Color(30, 30, 47));
        namePanel.setBackground(new Color(30, 30, 47));
        buttonPanel.setBackground(new Color(30, 30, 47));

        fNameLabel.setForeground(new Color(236, 240, 241));
        lNameLabel.setForeground(new Color(236, 240, 241));
        usernameLabel.setForeground(new Color(236, 240, 241));
        passwordLabel.setForeground(new Color(236, 240, 241));
        signUpButton.setForeground(new Color(37, 99, 235));
        ageRadioButton.setForeground(new Color(37, 99, 235));
        titleLabel.setForeground(new Color(236, 240, 241));
        logInButton.setForeground(new Color(37, 99, 235));

        lockButton.setText(emoji);
        lockButton.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        lockButton.setBackground(new Color(30, 30, 47));
        lockButton.setForeground(new Color(236, 240, 241));


        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUp(controller, frameChiamante);
            }
        });

        lockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lockButton.getText().equals(emoji)){
                    lockButton.setText("\uD83D\uDD13");
                    passwordArea.setEchoChar((char) 0);
                }
                else {
                    lockButton.setText(emoji);
                    passwordArea.setEchoChar('*');
                }
            }
        });

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login(frameChiamante, controller);
                JFrame loginFrame = login.getFrame();

                // Configurazione iniziale
                frame.dispose();
                loginFrame.setAlwaysOnTop(true);
                loginFrame.setVisible(true);

                // Gestione intelligente del focus
                loginFrame.addWindowStateListener(new WindowStateListener() {
                    @Override
                    public void windowStateChanged(WindowEvent e) {
                        if ((e.getNewState() & Frame.ICONIFIED) == 0) {
                            // Solo se NON è iconizzata, mantieni il focus
                            loginFrame.toFront();
                            loginFrame.requestFocus();
                        }
                    }
                });

                loginFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        frameChiamante.setEnabled(true);
                        frameChiamante.toFront();
                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {
                        // Quando viene ripristinata dalla barra delle applicazioni
                        loginFrame.toFront();
                        loginFrame.requestFocus();
                    }
                });
            }
        });

        passwordArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    signUpButton.doClick();
                }
            }
        });
    }

    /**
     * Gets frame.
     *
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }

    private void signUp(Controller controller, JFrame frameChiamante){
        if (usernameArea.getText().isEmpty() || Arrays.toString(passwordArea.getPassword()).isEmpty() || fNameArea.getText().isEmpty() || lNameArea.getText().isEmpty()){
            JOptionPane.showMessageDialog(panel, "Riempi tutti i campi");
        } else if(!ageRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(panel, "Devi confermare di avere più di 16 anni");
        } else {
            try {
                int code = controller.handleSignUp(usernameArea.getText(), new String(passwordArea.getPassword()), fNameArea.getText(), lNameArea.getText());
                switch (code) {
                    case -1:
                        JOptionPane.showMessageDialog(panel, "Il nome ed il cognome non devono superare i 20 caratteri");
                        break;
                    case -2:
                        JOptionPane.showMessageDialog(panel, "L'username deve avere tra i 3 ed i 16 caratteri\nLa password deve avere tra gli 8 ed i 16 caratteri");
                        break;
                    case -3:
                        JOptionPane.showMessageDialog(panel, "La password non rispecchia il formato\n1 lettera maiuscola, 1 lettera minuscola, 1 numero, 1 carattere speciale");
                        passwordArea.setText("");
                        break;
                    case -4:
                        JOptionPane.showMessageDialog(panel, "Username già utilizzato");
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(panel, "Registrazione completata!");
                        controller.getHome().getAreaPersonaleButton().setEnabled(true);
                        controller.getHome().getLoginButton().setText("Logout");
                        frameChiamante.setEnabled(true);
                        frame.dispose();
                        break;
                    default:
                        JOptionPane.showMessageDialog(panel, "Qualcosa è andato storto durante la registrazione");
                        usernameArea.setText("");
                        passwordArea.setText("");
                        lNameArea.setText("");
                        fNameArea.setText("");
                        break;
                }
            } catch (Exception ex) {  //Creiamo classe Exception nuova?
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel, "Qualcosa è andato storto durante la registrazione");                        usernameArea.setText("");
                passwordArea.setText("");
                lNameArea.setText("");
                fNameArea.setText("");
            }
        }
    }
}
