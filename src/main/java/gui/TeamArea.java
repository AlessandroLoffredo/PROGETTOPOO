package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * Classe che contiene tutte le informazioni e le azioni relative ad un team.
 */
public class TeamArea {
    private JPanel panel;
    private JList participantsList;
    private JList docList;
    private JPanel headerPanel;
    private JPanel docPanel;
    private JPanel participantsPanel;
    private JButton loadDocButton;
    private JTextArea nickArea;
    private JButton hackButton;
    private JLabel nickLabel;
    private JLabel hackathonLabel;
    private JTextArea hackathonArea;
    private JTextArea dateArea;
    private JLabel dateLabel;
    private JButton homeButton;
    private JButton sendButton;
    private JPanel loadSendPanel;
    private JFrame frame;
    private File file;



    /**
     * Instanzia una nuova TeamArea.
     * <p>
     * La classe TeamArea presenta informazioni generali sul team e sull'Hackathon a cui partecipa, una lista dei partecipanti che fanno parte di quel team e la lista di documenti caricati.
     * Permette inoltre di poter caricare un documento e inviarlo nella lista documenti del team.
     * </p>
     * @param frameChiamante Il frame che istanzia la nuova AreaPersonale.
     * @param controller     Il controller istanziato dalla classe Home.java
     */
    public TeamArea(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Area Team");
        frame.setContentPane(panel);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
        frame.pack();
        frame.setVisible(true);
        frame.setSize(950, 800);
        frame.setLocationRelativeTo(null);




        panel.setBackground(new Color(10, 10, 30)); // Blu notte/nero futuristico
        headerPanel.setBackground(new Color(15, 15, 50)); // Uguale a hackListPanel
        docPanel.setBackground(new Color(15, 15, 50));
        participantsPanel.setBackground(new Color(15, 15, 50));
        nickArea.setBackground(new Color(15, 15, 50));
        hackathonArea.setBackground(new Color(15, 15, 50));
        dateArea.setBackground(new Color(15, 15, 50));
        docList.setBackground(new Color(15, 15, 50));
        participantsList.setBackground(new Color(15, 15, 50));

        nickLabel.setForeground(new Color(0, 255, 0)); // Verde neon tipo Matrix
        hackathonLabel.setForeground(new Color(0, 255, 0)); // Verde neon tipo Matrix
        dateLabel.setForeground(new Color(0, 255, 0));
        nickArea.setForeground(new Color(0, 255, 0));
        hackathonArea.setForeground(new Color(0, 255, 0));
        dateArea.setForeground(new Color(0, 255, 0));
        docList.setForeground(new Color(0, 255, 0));
        participantsList.setForeground(new Color(0, 255, 0));




        hackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HackathonGui hackathonGui = new HackathonGui(frame, controller);
                hackathonGui.getFrame().setVisible(true);
                frame.dispose();

                //BISOGNA FARE VERIFICA SU HACKATHON A CUI SI E' REGISTRATO IL TEAM
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getHome().getFrame().setVisible(true);
                frame.dispose();
            }
        });

        loadDocButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager.put("FileChooser.background", new Color(238, 238, 238));
                UIManager.put("FileChooser.foreground", new Color(0, 0, 0));
                UIManager.put("FileChooser.listViewBackground", new Color(255, 255, 255));
                UIManager.put("FileChooser.listViewForeground", new Color(0, 0, 0));
                UIManager.put("FileChooser.buttonBackground", new Color(240, 240, 240));
                UIManager.put("Panel.background", new Color(240, 240, 240));
                JFileChooser fileChooser = new JFileChooser();
                int scelta = fileChooser.showOpenDialog(frame);
                if(scelta == JFileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();
                    //ogni persona può mandare un file? quanti ne mandiamo?
                }
            };
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GESTIONE CON DB PER VERIFICA DOC CON STESSO NOME GIA' INVIATO
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
