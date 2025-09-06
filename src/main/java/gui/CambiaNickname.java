package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * La classe che istanzia un frame che permette ai partecipanti che accedono all'area del team di modificare il nickname di quest'ultimo
 */
public class CambiaNickname {
    private JPanel changePanel;
    private JButton changeButton;
    private JPanel panel;
    private JTextField nickArea;
    private JPanel dataPanel;
    private JLabel nickLabel;
    private JLabel titlaLabel;
    private JFrame frame;


    /**
     * Istanzia un nuovo CambiaNickname
     *
     * @param frameChiamante il frame da cui si richiede di accedere a questa pagina
     * @param controller     il controller istanziato nella home
     */
    public CambiaNickname(JFrame frameChiamante, Controller controller, JLabel frameLabel) {
        frame = new JFrame("HackManager");
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


        panel.setBackground(new Color(30, 30, 47));
        dataPanel.setBackground(new Color(30, 30, 47));
        changePanel.setBackground(new Color(30, 30, 47));
        titlaLabel.setForeground(new Color(236, 240, 241));

        nickLabel.setForeground(new Color(236, 240, 241));
        changeButton.setForeground(new Color(37, 99, 235));

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int code = controller.changeNickname(nickArea.getText());
                if (code == 1){
                    JOptionPane.showMessageDialog(panel, "Nickname modificato con successo");
                    frameLabel.setText(nickArea.getText());
                } else {
                    JOptionPane.showMessageDialog(panel, "Errore durante il cambio del nickname");
                }
                frameChiamante.setEnabled(true);
                frame.dispose();
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                frameChiamante.setEnabled(true);
                frame.dispose();
            }
        });
    }


    /**
     * Restituisce il frame che viene creato quando viene istanziata la pagina CambiaNickname
     *
     * @return il frame di CambiaNickname
     */
    public JFrame getFrame() {
        return frame;
    }
}
