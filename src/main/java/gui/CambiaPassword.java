package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The type Cambia password.
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
     * Instantiates a new Cambia password.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     */
    public CambiaPassword(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("HackManager");
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
        String emoji = "\uD83D\uDD12";
        String font = "Segoe UI Emoji";
        String emoji2 = "\uD83D\uDD13";
        
        panel.setBackground(new Color(30, 30, 47));
        oldPasswordPanel.setBackground(new Color(30, 30, 47));
        passwordPanel.setBackground(new Color(30, 30, 47));
        newPasswordPanel.setBackground(new Color(30, 30, 47));


        titleLabel.setForeground(new Color(236, 240, 241));
        oldPasswordLabel.setForeground(new Color(236, 240, 241));
        passwordLabel.setForeground(new Color(236, 240, 241));
        newPasswordLabel.setForeground(new Color(236, 240, 241));
        changeButton.setForeground(new Color(37, 99, 235));

        lockButton1.setText(emoji);
        lockButton1.setFont(new Font(font, Font.PLAIN, 18));
        lockButton1.setBackground(new Color(30, 30, 47));
        lockButton1.setForeground(new Color(236, 240, 241));

        lockButton2.setText(emoji);
        lockButton2.setFont(new Font(font, Font.PLAIN, 18));
        lockButton2.setBackground(new Color(30, 30, 47));
        lockButton2.setForeground(new Color(236, 240, 241));

        lockButton3.setText(emoji);
        lockButton3.setFont(new Font(font, Font.PLAIN, 18));
        lockButton3.setBackground(new Color(30, 30, 47));
        lockButton3.setForeground(new Color(236, 240, 241));


        changeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword(controller, frameChiamante);
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
                if (lockButton1.getText().equals(emoji)) {
                    lockButton1.setText(emoji2);
                    oldPasswordArea.setEchoChar((char) 0);
                } else {
                    lockButton1.setText(emoji);
                    oldPasswordArea.setEchoChar('*');
                }
            }
        });

        lockButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lockButton2.getText().equals(emoji)) {
                    lockButton2.setText(emoji2);
                    passwordArea.setEchoChar((char) 0);
                } else {
                    lockButton2.setText(emoji);
                    passwordArea.setEchoChar('*');
                }
            }
        });

        lockButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lockButton3.getText().equals(emoji)) {
                    lockButton3.setText(emoji2);
                    newPasswordArea.setEchoChar((char) 0);
                } else {
                    lockButton3.setText(emoji);
                    newPasswordArea.setEchoChar('*');
                }
            }
        });
    }

    /**
     * Gets frame.
     *
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }

    private void changePassword(Controller controller, JFrame frameChiamante){
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
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel, "Qualcosa è andato storto durante l'aggiornamento delle credenziali");
                passwordArea.setText("");
                oldPasswordArea.setText("");
                newPasswordArea.setText("");
            }
        }
    }
}
