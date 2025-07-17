package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    private JLabel titleLabel;
    private JButton lockButton1;
    private JButton lockButton2;
    private JButton lockButton3;
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
        frame.setPreferredSize(new Dimension(400, 550));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


        panel.setBackground(new Color(30, 30, 47));
        oldPasswordPanel.setBackground(new Color(30, 30, 47));
        passwordPanel.setBackground(new Color(30, 30, 47));
        newPasswordPanel.setBackground(new Color(30, 30, 47));


        titleLabel.setForeground(new Color(236, 240, 241));
        oldPasswordLabel.setForeground(new Color(236, 240, 241));
        passwordLabel.setForeground(new Color(236, 240, 241));
        newPasswordLabel.setForeground(new Color(236, 240, 241));
        changeButton.setForeground(new Color(37, 99, 235));


        changeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(new String(oldPasswordArea.getPassword()).isEmpty() || new String(passwordArea.getPassword()).isEmpty() || new String(newPasswordArea.getPassword()).isEmpty()){
                    JOptionPane.showMessageDialog(panel, "Inserire i dati mancanti");
                }
                else {
                    try {
                        int code = controller.changePassword(oldPasswordArea.getPassword(), passwordArea.getPassword(), newPasswordArea.getPassword());
                        switch (code){
                            case -1:
                               JOptionPane.showMessageDialog(panel, "Vecchia password errata");
                               oldPasswordArea.setText("");
                               break;
                            case -2:
                               JOptionPane.showMessageDialog(panel, "La password deve essere lunga tra gli 8 ed i 16 caratteri");
                               newPasswordArea.setText("");
                               passwordArea.setText("");
                               break;
                            case -3:
                               JOptionPane.showMessageDialog(panel, "Le password non coincidono");
                               newPasswordArea.setText("");
                               passwordArea.setText("");
                               break;
                            case -4:
                               JOptionPane.showMessageDialog(panel, "La password non rispecchia il formato\n1 lettera maiuscola, 1 lettera minuscola, 1 numero, 1 carattere speciale");
                               newPasswordArea.setText("");
                               passwordArea.setText("");
                               break;
                            case 1:
                               JOptionPane.showMessageDialog(panel, "Password cambiata con successo!");
                               frameChiamante.setVisible(true);
                               frame.dispose();
                               break;
                            default:
                               JOptionPane.showMessageDialog(panel, "Qualcosa è andato storto durante l'aggiornamento delle credenziali");
                               passwordArea.setText("");
                               oldPasswordArea.setText("");
                               newPasswordArea.setText("");
                               break;
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, "Qualcosa è andato storto durante l'aggiornamento delle credenziali");
                        passwordArea.setText("");
                        oldPasswordArea.setText("");
                        newPasswordArea.setText("");
                    }
                }
            }
        });

        newPasswordArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    changeButton.doClick();
                }
            }
        });

        lockButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lockButton1.getText().equals("\uD83D\uDD12")) {
                    lockButton1.setText("\uD83D\uDD13");
                    oldPasswordArea.setEchoChar((char) 0);
                } else {
                    lockButton1.setText("\uD83D\uDD12");
                    oldPasswordArea.setEchoChar('*');
                }
            }
        });

        lockButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lockButton2.getText().equals("\uD83D\uDD12")) {
                    lockButton2.setText("\uD83D\uDD13");
                    passwordArea.setEchoChar((char) 0);
                } else {
                    lockButton2.setText("\uD83D\uDD12");
                    passwordArea.setEchoChar('*');
                }
            }
        });

        lockButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lockButton3.getText().equals("\uD83D\uDD12")) {
                    lockButton3.setText("\uD83D\uDD13");
                    newPasswordArea.setEchoChar((char) 0);
                } else {
                    lockButton3.setText("\uD83D\uDD12");
                    newPasswordArea.setEchoChar('*');
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
