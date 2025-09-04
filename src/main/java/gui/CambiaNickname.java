package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The type Cambia nickname.
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
     * Instantiates a new Cambia nickname.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     */
    public CambiaNickname(JFrame frameChiamante, Controller controller) {
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
                if (code != 1){
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
     * Gets frame.
     *
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }
}
