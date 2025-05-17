package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.*;

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
    public JFrame frame;

    public CambiaPassword(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Cambia password");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);

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
}
