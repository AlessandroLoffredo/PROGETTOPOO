package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {
    private JPanel panel;
    private JButton loginButton;
    private JButton signUpButton;
    private JPanel loginPanel;
    private JLabel titleLabel;
    private JPanel hackListPanel;
    private JButton areaPersonaleButton;
    private JTextArea textArea1;
    private static JFrame frame;
    private Controller controller;

    public static void main(String[] args){
        frame = new JFrame("Home");
        frame.setContentPane(new Home().panel);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setSize(new Dimension(700, 700));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public Home() {
        controller = new Controller();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login(frame, controller);
                frame.setVisible(false);
                login.getFrame().setVisible(true);
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

                CreaHackathon crea = new CreaHackathon(frame);
                frame.setVisible(false);
                crea.getFrame().setVisible(true);*/
            }

        });
        //TESTO SEGNAPOSTO CHE ROMPE LA PAGINA

        areaPersonaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controller.getUser() == null){
                    AreaPersonale areaPersonale = new AreaPersonale(frame, controller);
                    frame.setVisible(false);
                    areaPersonale.getFrame().setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(panel, "Per accedere all'area personale devi eseguire l'accesso");
                }
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
        textArea1.append("merda ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

    }
}
