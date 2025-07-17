package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import controller.*;

/**
 * Classe che permette ad un utente qualsiasi di modificare la propria password.
 */
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
    private JLabel titleLabel;
    private JButton lockButton;
    private JFrame frame;



    /**
     * Instanzia una nuova CambiaUsername.
     * <p>
     * La classe CambiaUsername permette agli utenti di modificare il proprio username.
     * </p>
     * @param frameChiamante il frame che istanzia la nuova AreaPersonaleOrganizzatore
     * @param controller     Il controller istanziato dalla classe Home.java
     */
    public CambiaUsername(JFrame frameChiamante, Controller controller){
        frame = new JFrame("Cambia username");
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
        usernamePanel.setBackground(new Color(30, 30, 47));
        passwordPanel.setBackground(new Color(30, 30, 47));

        titleLabel.setForeground(new Color(236, 240, 241));
        usernameLabel.setForeground(new Color(236, 240, 241));
        changeButton.setForeground(new Color(37, 99, 235));
        passwordLabel.setForeground(new Color(236, 240, 241));



        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(usernameArea.getText().isEmpty() || new String(passwordArea.getPassword()).isEmpty()){
                    JOptionPane.showMessageDialog(panel, "Inserire i dati mancanti");
                } else {
                    try{
                        int code = controller.changeUsername(usernameArea.getText(), passwordArea.getPassword());
                        switch (code) {
                            case -1:
                                JOptionPane.showMessageDialog(panel, "L'username deve essere lungo tra gli 8 ed i 16 caratteri");
                                usernameArea.setText("");
                                break;
                            case -2:
                                JOptionPane.showMessageDialog(panel, "Password errata");
                                passwordArea.setText("");
                                break;
                            case -3:
                                JOptionPane.showMessageDialog(panel, "L'username deve essere diverso da quello attuale");
                                break;
                            case -4:
                                JOptionPane.showMessageDialog(panel, "Username già utilizzato");
                                break;
                            case 1:
                                JOptionPane.showMessageDialog(panel, "Username cambiato con successo");
                                frameChiamante.setVisible(true);
                                frame.dispose();
                                break;
                            default:
                                JOptionPane.showMessageDialog(panel, "Qualcosa è andato storto durante l'aggiornamento delle credenziali");
                                passwordArea.setText("");
                                usernameArea.setText("");
                                break;
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, "Qualcosa è andato storto durante l'aggiornamento delle credenziali");
                        passwordArea.setText("");
                        usernameArea.setText("");
                    }
                }
            }
        });

        passwordArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    changeButton.doClick();
                }
            }
        });

        lockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lockButton.getText().equals("\uD83D\uDD12")) {
                    lockButton.setText("\uD83D\uDD13");
                    passwordArea.setEchoChar((char) 0);
                } else {
                    lockButton.setText("\uD83D\uDD12");
                    passwordArea.setEchoChar('*');
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
