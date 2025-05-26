package gui;

import controller.Controller;
import model.Document;
import model.Judge;
import model.Team;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Classe che contiente tutte le azioni e le informazioni generali di un giudice.
 */
public class AreaPersonaleGiudice {
    private JPanel panel;
    private JPanel dataPanel;
    private JLabel fNameLabel;
    private JTextArea fNameArea;
    private JLabel lNameLabel;
    private JTextArea lNameArea;
    private JLabel userLabel;
    private JTextArea userArea;
    private JButton homeButton;
    private JPanel operationPanel;
    private JButton cambiaUsernameButton;
    private JButton cambiaPasswordButton;
    private JPanel judjePanel;
    private JList docList;
    private JPanel markPanel;
    private JTextArea problemArea;
    private JPanel problemPanel;
    private JButton sendProbButton;
    private JPanel selectExaminePanel;
    private JPanel selectPanel;
    private JComboBox teamComboBox;
    private JButton selectButton;
    private JPanel examinePanel;
    private JPanel listPanel;
    private JPanel commentPanel;
    private JTextArea commentArea;
    private JButton loadButton;
    private JButton assigneButton;
    private JComboBox teamComboBoxMark;
    private JSlider markSlider;
    private JLabel commentLabel;
    private JLabel markLabel;
    private JFrame frame;

    /**
     * Instanzia una nuova AreaPersonaleGiudice.
     * <p>
     * La classe AreaPersonaleGiudice permette al giudice di vedere e modificare i suoi dati, caricare o modificare la descrizione del problema dell'Hackathon
     * che giudica, cercare e commentare i documenti caricati dai vari team e poter assegnare a questi ultimi un voto.
     * </p>
     * @param frameChiamante il frame che istanzia la nuova AreaPersonaleOrganizzatore
     * @param controller     Il controller istanziato dalla classe Home.java
     */
    public AreaPersonaleGiudice(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Area Personale");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);


        panel.setBackground(new Color(10, 10, 30)); // Blu notte/nero futuristico
        problemArea.setBackground(new Color(15, 15, 50)); // Uguale a hackListPanel
        fNameArea.setBackground(new Color(15, 15, 50));
        lNameArea.setBackground(new Color(15, 15, 50));
        userArea.setBackground(new Color(15, 15, 50));
        docList.setBackground(new Color(15, 15, 50));

        fNameLabel.setForeground(new Color(0, 255, 0)); // Verde neon tipo Matrix
        lNameLabel.setForeground(new Color(0, 255, 0)); // Verde neon tipo Matrix
        userLabel.setForeground(new Color(0, 255, 0));
        markLabel.setForeground(new Color(0, 255, 0));
        commentLabel.setForeground(new Color(0, 255, 0));
        markSlider.setForeground(Color.GREEN);

        cambiaUsernameButton.setForeground(new Color(0, 200, 255)); // Azzurro cyberR
        cambiaPasswordButton.setForeground(new Color(255, 0, 150)); // Magenta neon
        docList.setForeground(new Color(0, 255, 0)); // Verde neon tipo Matrix
        problemArea.setForeground(new Color(0, 255, 0));

        markSlider.setMaximum(10);
        markSlider.setMinimum(0);
        markSlider.setValue(5);

        markSlider.setMinorTickSpacing(1); // Ogni step è di 1
        markSlider.setMajorTickSpacing(1); // Mostra i tick ogni 10 unità
        markSlider.setSnapToTicks(true); // Il cursore si muove esattamente sui tick
        markSlider.setPaintTicks(true); // Mostra i tick
        markSlider.setPaintLabels(true); // Mostra i numeri


        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });

        cambiaUsernameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CambiaUsername cambiaUsername = new CambiaUsername(frame, controller);
                frame.setVisible(false);
                cambiaUsername.getFrame().setVisible(true);
            }
        });

        cambiaPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CambiaPassword cambiaPassword = new CambiaPassword(frame, controller);
                frame.setVisible(false);
                cambiaPassword.getFrame().setVisible(true);
            }
        });

        sendProbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controller.getUser() != null){
                    if (problemArea.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(panel, "Inserisci la descrizione del problema", "Send Problem Description", JOptionPane.ERROR_MESSAGE);
                    } else {
                        controller.handleProblemDescription(problemArea.getText());
                        problemArea.setText("");
                        if (sendProbButton.getText().equalsIgnoreCase("Carica descrizione problema")) {
                            JOptionPane.showMessageDialog(panel, "Descrizione caricata", "Descrizione problema", JOptionPane.INFORMATION_MESSAGE);
                            sendProbButton.setText("Modifica descrizione problema");
                        } else {
                            JOptionPane.showMessageDialog(panel, "Descrizione modificata", "Descrizione problema", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(panel, "Accedi prima di inserire la descrizione", "ERROR", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        if(!(((Judge)controller.getUser()).getJudgedHack().getProblemDescription().isEmpty())){
            sendProbButton.setText("Modifica descrizione problema");
        }

        //BISOGNERA' CARICARE NELLA TEAMCOMBOBOX E NELLA TEAMCOMBOBOXMARK I NOMI DI TUTTI I TEAM NELL'HACKATHON TRAMITE DB

        teamComboBox.addItem("-");
        teamComboBoxMark.addItem("-");


        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(teamComboBox.getSelectedItem().equals("-")){
                    JOptionPane.showMessageDialog(panel, "Seleziona un team", "ERRORE", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    if(controller.handleLoadFile((Team) teamComboBox.getSelectedItem()).isEmpty()){
                        JOptionPane.showMessageDialog(panel, "Il Team selezionato non ha ancora caricato documenti", "INFO", JOptionPane.INFORMATION_MESSAGE );
                    }

                    else{
                        ArrayList<Document> list = controller.handleLoadFile((Team) teamComboBox.getSelectedItem());
                        DefaultListModel<Document> model = new DefaultListModel<>();
                        for (Document d : list) {
                            model.addElement(d);
                        }
                    }
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(commentArea.getText().isEmpty()){
                    JOptionPane.showMessageDialog(panel, "Scrivi un commento", "ERRORE", JOptionPane.ERROR_MESSAGE);
                }
               /* else {
                    controller.handleComment(commentArea.getText(), frame);
                    DUBBIO NEL CONTROLLER
                }*/
            }
        });

        assigneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(teamComboBoxMark.getSelectedItem().equals("-")){
                    JOptionPane.showMessageDialog(panel, "Seleziona un team", "ERRORE", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int scelta = JOptionPane.showConfirmDialog(panel, "Sei sicuro di voler assegnare: " + markSlider.getValue() + " al team: " + teamComboBoxMark.getSelectedItem(),"Assegna voto", JOptionPane.YES_NO_OPTION);
                    if(scelta == JOptionPane.YES_OPTION){
                        controller.handleAssignMark((Team) teamComboBoxMark.getSelectedItem(), markSlider.getValue());
                    }
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
