package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Classe principale che mostra la lista di tutti gli Hackathon e permette di svolgere varie azioni per accedere e registrarsi
 */
public class Home {
    private JPanel panel;
    private JButton loginButton;
    private JButton signUpButton;
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
    public static void main(String[] args) throws MalformedURLException {
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
    public Home() throws MalformedURLException {

        //CREO IMAGEICON, LA CONVERTO IN IMAGE PER RIDIMENSIONARE
        //ImageIcon imageIcon = new ImageIcon(new URL("https://static.vecteezy.com/system/resources/previews/014/487/777/original/hacker-logo-simple-minimal-illustration-vector.jpg"));
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
        signUpButton.setForeground(new Color(37, 99, 235));
        textArea1.setForeground(new Color(236, 240, 241));

        UIManager.put("OptionPane.background", new Color(10, 10, 30));
        UIManager.put("Panel.background", new Color(15, 15, 50));
        UIManager.put("OptionPane.messageForeground", new Color(0, 255, 0));
        UIManager.put("OptionPane.messageFont", new Font("PT Mono", Font.PLAIN, 14));


        /*scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                thumbColor = new Color(0, 200, 255); // Verde neon
                trackColor = new Color(10, 10, 30); // Blu notte
            }
        });*/


        controller = new Controller(this);
        //aggiungere la negazione quando effettivamente istanziamo un utente nel controller

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loginButton.getText().equalsIgnoreCase("LOGIN")){
                    Login login = new Login(frame, controller, Home.this);
                    frame.setVisible(false);
                    login.getFrame().setVisible(true);
                }if(loginButton.getText().equalsIgnoreCase("LOGOUT")){
                    System.out.println(controller.getPlAdmin());
                    controller.logout();
                    System.out.println(controller.getPlAdmin());
                    JOptionPane.showMessageDialog(panel, "Logout eseguito");
                    areaPersonaleButton.setEnabled(false);
                    loginButton.setText("LogIn");
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp signUp = new SignUp(frame, controller);
                frame.setVisible(false);
                signUp.getFrame().setVisible(true);


                /*JLabel newLabel = new JLabel("Nuova etichetta aggiunta!");
                panel1.add(newLabel);
                panel1.revalidate(); // Aggiorna il layout
                panel1.repaint(); // Ridisegna la GUI


                AreaPersonale areaPersonale = new AreaPersonale(frame);
                frame.setVisible(false);
                areaPersonale.getFrame().setVisible(true);

                HackathonGui hack = new HackathonGui(frame);
                frame.setVisible(false);
                hack.getFrame().setVisible(true);

                CambiaUsername cambia = new CambiaUsername(frame);
                frame.setVisible(false);
                cambia.getFrame().setVisible(true);
                */

                /*CreaHackathon crea = new CreaHackathon(frame);
                frame.setVisible(false);
                crea.getFrame().setVisible(true);*/

                /*TeamArea team = new TeamArea(frame, controller);
                frame.setVisible(false);
                team.getFrame().setVisible(true);*/

                /*CreaTeam creaTeam = new CreaTeam(frame, controller);
                frame.setVisible(false);
                creaTeam.getFrame().setVisible(true);*/

                /*HackathonGui hack = new HackathonGui(frame, controller);
                frame.setVisible(false);
                hack.getFrame().setVisible(true);*/

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
