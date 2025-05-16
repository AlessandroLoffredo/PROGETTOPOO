package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {
    private JPanel panel1;
    private JButton loginButton;
    private JButton signUpButton;
    private JPanel loginPanel;
    private JLabel titleLabel;
    private JPanel hackListPanel;
    private static JFrame frame;

    public static void main(String[] args){
        frame = new JFrame("Home");
        frame.setContentPane(new Home().panel1);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(700,700);
        frame.setLocationRelativeTo(null);
    }

    public Home() {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login(frame);
                frame.setVisible(false);
                login.frame.setVisible(true);
                //JOptionPane.showMessageDialog(panel1, String.valueOf(loginButton.getX()) + loginButton.getY() );
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp signUp = new SignUp(frame);
                frame.setVisible(false);
                signUp.frame.setVisible(true);


                /*JLabel newLabel = new JLabel("Nuova etichetta aggiunta!");
                panel1.add(newLabel);
                panel1.revalidate(); // Aggiorna il layout
                panel1.repaint(); // Ridisegna la GUI


                AreaPersonale areaPersonale = new AreaPersonale(frame);
                frame.setVisible(false);
                areaPersonale.frame.setVisible(true);

                HackathonGui hack = new HackathonGui(frame);
                frame.setVisible(false);
                hack.frame.setVisible(true);

                CambiaUsername cambia = new CambiaUsername(frame);
                frame.setVisible(false);
                cambia.frame.setVisible(true);

                CreaHackathon crea = new CreaHackathon(frame);
                frame.setVisible(false);
                crea.frame.setVisible(true);*/
            }

        });

    }
}
