package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HackathonGui {
    private JPanel panel;
    private JTextArea titleArea;
    private JTextArea organizerArea;
    private JPanel headerPanel;
    private JList judjeList;
    private JTextArea infoArea;
    private JPanel infoPanel;
    private JTextArea descProbArea;
    private JButton accessButton;
    private JButton subscribeHackButton;
    private JPanel imagePanel;
    public JFrame frame;

    public HackathonGui(JFrame frameChiamante){
        frame = new JFrame("hack");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);


        accessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //UTILIZZO MOMENTANEO DI QUESTO ACTION LISTENER
                //BISOGNA TENERE CONTO DI CHI CLICCA IL PULSANTE EPR APRIRE UN AREA PERSONALE SPECIFICA
                //AreaPersonale areaPersonale = new AreaPersonale(frame, contoller);
                //frame.setVisible(false);
                //areaPersonale.getFrame().setVisible(true);
            }
        });

        /*subscribeHackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiornamento nel DB, in cui l'utente diventa Partecipante se Loggato
            }
        });*/
    }

}

