package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {
    private JPanel panel1;
    private JButton loginButton;
    private JButton signInButton;
    private JPanel loginPanel;
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
            }
        });
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignIn signIn = new SignIn(frame);
                frame.setVisible(false);
                signIn.frame.setVisible(true);


                JLabel newLabel = new JLabel("Nuova etichetta aggiunta!");
                panel1.add(newLabel);
                panel1.revalidate(); // Aggiorna il layout
                panel1.repaint(); // Ridisegna la GUI
            }
        });
    }
}
