package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Classe principale che mostra la lista di tutti gli Hackathon e permette di svolgere varie azioni per accedere e registrarsi
 */
public class Home {
    private JPanel panel;
    private JButton loginButton;
    private JPanel loginPanel;
    private JLabel titleLabel;
    private JPanel hackListPanel;
    private JButton areaPersonaleButton;
    private JTextArea textArea1;
    private JPanel imagePanel;
    private JLabel imageLabel;
    private JScrollPane scrollPane;
    private static JFrame frame;
    private Controller controller;

    /**
     * Punto in cui si entra effettivamente nel programma.
     *
     * @param args gli argomenti in input
     * @throws MalformedURLException Eccezzione per gestire URL scritti male
     */
    public static void main(String[] args) {
        frame = new JFrame("Home");
        frame.setContentPane(new Home().panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Dimension size = frame.getSize();
        frame.setMinimumSize(new Dimension(size.width, 400));
        frame.setSize(new Dimension(700, 700));
        frame.setLocationRelativeTo(null);
    }

    /**
     * Instanzia una nuova Home.
     * <p>
     * La classe Home permette ai visitatori di vedere la lista di tutti gli Hackaton, poter entrare all'interno della pagina specifica di ogni Hackathon,
     * effettuare il login o il signup.
     * </p>
     */
    public Home(){

        //CREO IMAGEICON, LA CONVERTO IN IMAGE PER RIDIMENSIONARE
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Hackerlogo.jpg"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        // CONVERTO DI NUOVO IN IMAGEICON PER ASSEGNARLA ALLA LABLE
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(resizedIcon);
        panel.setBackground(new Color(30, 30, 47));
        hackListPanel.setBackground(new Color(30, 30, 47));
        textArea1.setBackground(new Color(30, 30, 47));
        loginPanel.setBackground(new Color(30, 30, 47));

        titleLabel.setForeground(new Color(236, 240, 241));
        loginButton.setForeground(new Color(37, 99, 235));
        areaPersonaleButton.setForeground(new Color(37, 99, 235));
        textArea1.setForeground(new Color(236, 240, 241));

        UIManager.put("OptionPane.background", new Color(30, 30, 47));
        UIManager.put("Panel.background", new Color(30, 30, 47));
        UIManager.put("OptionPane.messageForeground", new Color(236, 240, 241));
        UIManager.put("OptionPane.messageFont", new Font("JetBrains Mono", Font.PLAIN, 14));



        controller = new Controller(this);
        //aggiungere la negazione quando effettivamente istanziamo un utente nel controller

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loginButton.getText().equalsIgnoreCase("LOGIN")) {
                    Login login = new Login(frame, controller);
                    JFrame loginFrame = login.getFrame();

                    // Configurazione iniziale
                    frame.setEnabled(false);
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
                            frame.setEnabled(true);
                            frame.toFront();
                        }

                        @Override
                        public void windowDeiconified(WindowEvent e) {
                            // Quando viene ripristinata dalla barra delle applicazioni
                            loginFrame.toFront();
                            loginFrame.requestFocus();
                        }
                    });

                } else if(loginButton.getText().equalsIgnoreCase("LOGOUT")) {
                    System.out.println(controller.getPlAdmin());
                    controller.logout();
                    System.out.println(controller.getPlAdmin());
                    JOptionPane.showMessageDialog(panel, "Logout eseguito");
                    areaPersonaleButton.setEnabled(false);
                    loginButton.setText("LogIn");
                }
            }
        });


        areaPersonaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.areaPersonale(frame);
            }
        });

        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);
        textArea1.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        textArea1.append("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        textArea1.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        textArea1.append("\n\n\n\n\n\n\n\n");
        textArea1.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        textArea1.append("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        textArea1.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        textArea1.append("\n\n\n\n\n\n\n\n");
        textArea1.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        textArea1.append("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        textArea1.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        textArea1.append("\n\n\n\n\n\n\n\n");
        textArea1.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        textArea1.append("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        textArea1.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        textArea1.append("\n\n\n\n\n\n\n\n");
        textArea1.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");




    }

    /**
     * Restituisci il bottone per accedere all'area personale.
     *
     * @return JButton: bottone per accedere all'area personale.
     */
    public JButton getAreaPersonaleButton() {
        return areaPersonaleButton;
    }

    /**
     * Restituisci il bottone per effettuare il login.
     *
     * @return JButton: bottone per effettuare il login.
     */
    public JButton getLoginButton() {
        return loginButton;
    }

    public JFrame getFrame() {
        return frame;
    }
}
