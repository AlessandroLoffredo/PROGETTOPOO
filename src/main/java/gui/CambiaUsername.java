package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.*;

public class CambiaUsername {
    private JPanel panel;
    private JPanel usernamePanel;
    private JTextField usernameArea;
    private JLabel usernameLabel;
    private JPanel passwordPanel;
    private JPasswordField passwordArea;
    private JLabel passwordLabel;
    private JPanel buttonPanel;
    private JButton changeButton;
    public JFrame frame;

    public CambiaUsername(JFrame frameChiamante, Controller controller){
        frame = new JFrame("Cambia username");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int code = controller.changeUsername(usernameArea.getText(), passwordArea.getPassword());
                if(code == -4){
                    JOptionPane.showMessageDialog(panel, "Password errata");
                }else if(code == -2){
                    JOptionPane.showMessageDialog(panel, "Username esistente");
                }else if(code == -1){
                   JOptionPane.showMessageDialog(panel, "L'username deve avere tra i 3 ed i 15 caratteri");
                } else if (code == -3) {
                    JOptionPane.showMessageDialog(panel, "Riempi tutti i campi");
                }else{
                    JOptionPane.showMessageDialog(panel, "Username modificato con successo");
                    frameChiamante.setVisible(true);
                    frame.dispose();
                }
            }
        });
    }


}
