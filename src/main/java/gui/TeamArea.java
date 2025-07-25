package gui;

import controller.Controller;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Classe che contiene tutte le informazioni e le azioni relative ad un team.
 */
public class TeamArea {
    private JPanel panel;
    private JList participantsList;
    private JList docList;
    private JPanel docPanel;
    private JButton loadDocButton;
    private JLabel nickArea;
    private JButton hackButton;
    private JLabel nickLabel;
    private JButton homeButton;
    private JButton sendButton;
    private JPanel loadSendPanel;
    private JPanel profilePanel;
    private JPanel dataPanel;
    private JButton cambiaNicknameButton;
    private JLabel teamLabel;
    private JLabel profileLabel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JLabel imageLabel;
    private JLabel docLabel;
    private JFrame frame;
    private File file;
    private JLabel pathLabel;




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
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);

        controller.getTeam();
        nickArea.setText(controller.getNickname());


        panel.setBackground(new Color(30, 30, 47));
        profilePanel.setBackground(new Color(30, 30, 47));
        profileLabel.setForeground(new Color(236, 240, 241));
        teamLabel.setForeground(new Color(236, 240, 241));
        docPanel.setBackground(new Color(236, 240, 241));
        loadSendPanel.setBackground(new Color(236, 240, 241));
        nickArea.setBackground(new Color(30, 30, 47));
        docList.setBackground(new Color(236, 240, 241));
        participantsList.setBackground(new Color(236, 240, 241));
        participantsList.setForeground(new Color(30, 30, 47));
        nickLabel.setForeground(new Color(236, 240, 241));
        nickArea.setForeground(new Color(236, 240, 241));
        docLabel.setForeground(new Color(30, 30, 47));
        docList.setForeground(new Color(30, 30, 47));
        participantsList.setForeground(new Color(30, 30, 47));
        titleLabel.setForeground(new Color(236, 240, 241));
        cambiaNicknameButton.setForeground(new Color(37, 99, 235));
        homeButton.setForeground(new Color(37, 99, 235));
        hackButton.setForeground(new Color(37, 99, 235));
        loadDocButton.setForeground(new Color(37, 99, 235));
        sendButton.setForeground(new Color(37, 99, 235));

        docList.setBorder(new LineBorder(new Color(30, 30, 47)));
        docList.setPreferredSize(new Dimension(100, 100));

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/iconaUser.png"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(resizedIcon);

        ArrayList<String> teammates = new ArrayList<>();
        controller.findTeammates(teammates);
        DefaultListModel<String> model = new DefaultListModel<>();
        for(String s : teammates){
            model.addElement(s);
        }
        participantsList.setModel(model);
        System.out.println(teammates);

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
                controller.findHack();
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
                    pathLabel.setText(file.toString());
                }
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int code = controller.sendFile(file, file.getName());
                UIManager.put("Panel.background", new Color(30, 30, 47));
                System.out.println(code);
                switch (code){
                    case -1:
                        JOptionPane.showMessageDialog(panel, "Hai già caricato questo documento");
                        System.out.println("Hai già caricato questo documento");
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(panel, "Inserimento del file andato a buon fine");
                        System.out.println("SI");
                        break;
                    default:
                        JOptionPane.showMessageDialog(panel, "Errore durante il caricamento del file");
                        System.out.println("NO");
                        break;
                }
            UIManager.put("Panel.background", new Color(240, 240, 240));
            }
        });

        cambiaNicknameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CambiaNickname cambiaNickname = new CambiaNickname(frame, controller);
                JFrame cambiaNicknameFrame = cambiaNickname.getFrame();

                // Configurazione iniziale
                frame.setEnabled(false);
                cambiaNicknameFrame.setAlwaysOnTop(true);
                cambiaNicknameFrame.setVisible(true);

                // Gestione intelligente del focus
                cambiaNicknameFrame.addWindowStateListener(new WindowStateListener() {
                    @Override
                    public void windowStateChanged(WindowEvent e) {
                        if ((e.getNewState() & Frame.ICONIFIED) == 0) {
                            // Solo se NON è iconizzata, mantieni il focus
                            cambiaNicknameFrame.toFront();
                            cambiaNicknameFrame.requestFocus();
                        }
                    }
                });

                cambiaNicknameFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        frame.setEnabled(true);
                        frame.toFront();
                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {
                        // Quando viene ripristinata dalla barra delle applicazioni
                        cambiaNicknameFrame.toFront();
                        cambiaNicknameFrame.requestFocus();
                    }
                });
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
