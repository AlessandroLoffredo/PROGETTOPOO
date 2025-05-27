package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import controller.*;

/**
 * Classe che permette ad un utente qualsiasi di modificare la propria password.
 */
public class CambiaPassword {
    private JPanel oldPasswordPanel;
    private JPasswordField oldPasswordArea;
    private JLabel oldPasswordLabel;
    private JPanel passwordPanel;
    private JPasswordField passwordArea;
    private JLabel passwordLabel;
    private JPanel buttonPanel;
    private JButton changeButton;
    private JPanel newPasswordPanel;
    private JPasswordField newPasswordArea;
    private JLabel newPasswordLabel;
    private JPanel panel;
    private JFrame frame;



    /**
     * Instanzia una nuova CambiaPassword.
     * <p>
     * La classe CambiaPassword permette agli utenti di modificare la propria password.
     * </p>
     * @param frameChiamante il frame che istanzia la nuova AreaPersonaleOrganizzatore
     * @param controller     Il controller istanziato dalla classe Home.java
     */
    public CambiaPassword(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Cambia password");
        frame.setContentPane(panel);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
        frame.setPreferredSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


        panel.setBackground(new Color(10, 10, 30)); // Blu notte/nero futuristico
        oldPasswordPanel.setBackground(new Color(15, 15, 50));// Leggermente più chiaro
        passwordPanel.setBackground(new Color(15, 15, 50)); // Uguale a hackListPanel
        newPasswordPanel.setBackground(new Color(15, 15, 50)); // Uguale a hackListPanel


        oldPasswordLabel.setForeground(new Color(0, 255, 0)); // Verde neon tipo Matrix
        passwordLabel.setForeground(new Color(0, 255, 0)); // Verde neon tipo Matrix
        newPasswordLabel.setForeground(new Color(0, 255, 0)); // Verde neon tipo Matrix
        changeButton.setForeground(new Color(255, 0, 150)); // Magenta neon


        changeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                int code = controller.changePassword(oldPasswordArea.getPassword(), passwordArea.getPassword(), newPasswordArea.getPassword());
                if(code == -2){
                    JOptionPane.showMessageDialog(panel, "Vecchia password errata");
                }else if(code == -1){
                    JOptionPane.showMessageDialog(panel, "La password deve essere lunga tra gli 8 ed i 16 caratteri");
                }else if(code == -4){
                    JOptionPane.showMessageDialog(panel, "Le password non coincidono");
                }else if(code == -3){
                    JOptionPane.showMessageDialog(panel, "Inserire i dati mancanti");
                }else{
                    JOptionPane.showMessageDialog(panel, "Password cambiata con successo!");
                    frameChiamante.setVisible(true);
                    frame.dispose();
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
