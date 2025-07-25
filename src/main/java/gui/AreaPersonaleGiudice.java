package gui;

import controller.Controller;
import model.Document;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Classe che contiente tutte le azioni e le informazioni generali di un giudice.
 */
public class AreaPersonaleGiudice {
    private JPanel panel;
    private JLabel fNameLabel;
    private JLabel fNameArea;
    private JLabel lNameLabel;
    private JLabel lNameArea;
    private JLabel userLabel;
    private JLabel userArea;
    private JButton homeButton;
    private JPanel operationPanel;
    private JButton cambiaUsernameButton;
    private JButton cambiaPasswordButton;
    private JList docList;
    private JPanel markPanel;
    private JTextArea problemArea;
    private JPanel problemPanel;
    private JButton sendProbButton;
    private JPanel selectExaminePanel;
    private JComboBox teamComboBox;
    private JButton selectButton;
    private JTextArea commentArea;
    private JButton loadButton;
    private JButton assigneButton;
    private JComboBox teamComboBoxMark;
    private JSlider markSlider;
    private JLabel commentLabel;
    private JLabel markLabel;
    private JPanel profilePanel;
    private JPanel dataPanel;
    private JLabel profileLabel;
    private JLabel titleLabel;
    private JLabel imageLabel;
    private JScrollPane scrollPane;
    private JPanel scrollPanel;
    private JLabel problemLabel;
    private JScrollPane docScrollPane;
    private JLabel commentAreaLabel;
    private JButton logoutButton;
    private JPanel hackathonPanel;
    private JLabel hackLabel;
    private JLabel currentStartLabel;
    private JLabel currentStartArea;
    private JLabel currentEndLabel;
    private JLabel currentEndArea;
    private JLabel currentStartRegLabel;
    private JLabel currentStartRegArea;
    private JLabel currentMaxTeamParLabel;
    private JLabel currentMaxTeamParArea;
    private JLabel currentCounterLabel;
    private JLabel currentMaxRegLabel;
    private JLabel currentCounterArea;
    private JLabel currentMaxRegArea;
    private JLabel currentVenueLabel;
    private JLabel currentVenueArea;
    private JLabel currentTitleLabel;
    private JLabel currentTitleArea;
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
        frame = new JFrame("Hackathon");
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
        frame.setSize(1000, 800);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);


        panel.setBackground(new Color(30, 30, 47));
        scrollPanel.setBackground(new Color(236, 240, 241));
        hackathonPanel.setBackground(new Color(236, 240, 241));
        scrollPane.setBackground(new Color(236, 240, 241));
        problemPanel.setBackground(new Color(236, 240, 241));
        problemPanel.setBackground(new Color(236, 240, 241));
        markPanel.setBackground(new Color(236, 240, 241));
        titleLabel.setForeground(new Color(236, 240, 241));
        profilePanel.setBackground(new Color(30, 30, 47));
        fNameLabel.setForeground(new Color(236, 240, 241));
        lNameLabel.setForeground(new Color(236, 240, 241));
        userLabel.setForeground(new Color(236, 240, 241));
        fNameArea.setForeground(new Color(236, 240, 241));
        lNameArea.setForeground(new Color(236, 240, 241));
        userArea.setForeground(new Color(236, 240, 241));
        profileLabel.setForeground(new Color(236, 240, 241));

        problemLabel.setForeground(new Color(30, 30, 47));
        problemArea.setBackground(new Color(236, 240, 241));
        problemArea.setForeground(new Color(30, 30, 47));
        commentLabel.setForeground(new Color(30, 30, 47));
        teamComboBox.setBackground(new Color(236, 240, 241));
        teamComboBox.setForeground(new Color(30, 30, 47));
        docList.setBackground(new Color(236, 240, 241));
        docList.setForeground(new Color(30, 30, 47));
        commentArea.setBackground(new Color(236, 240, 241));
        commentArea.setForeground(new Color(30, 30, 47));
        markLabel.setForeground(new Color(30, 30, 47));
        teamComboBoxMark.setBackground(new Color(236, 240, 241));
        teamComboBoxMark.setForeground(new Color(30, 30, 47));
        markSlider.setForeground(new Color(37, 99, 235));
        markSlider.setBackground(new Color(236, 240, 241));

        cambiaUsernameButton.setForeground(new Color(37, 99, 235));
        cambiaPasswordButton.setForeground(new Color(37, 99, 235));
        homeButton.setForeground(new Color(37, 99, 235));
        sendProbButton.setForeground(new Color(37, 99, 235));
        selectButton.setForeground(new Color(37, 99, 235));
        loadButton.setForeground(new Color(37, 99, 235));
        assigneButton.setForeground(new Color(37, 99, 235));
        logoutButton.setForeground(new Color(37, 99, 235));

        currentVenueLabel.setText("\uD83C\uDF10 Sede:");
        currentVenueLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        currentVenueLabel.setForeground(new Color(30, 30, 47));
        currentVenueLabel.setBackground(new Color(236, 240, 241));
        currentTitleLabel.setText("\uD83D\uDCCC Titolo:");
        currentTitleLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        currentTitleLabel.setForeground(new Color(30, 30, 47));
        currentTitleLabel.setBackground(new Color(236, 240, 241));
        currentStartLabel.setText("\uD83D\uDDD3 Inizio Hackathon:");
        currentStartLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        currentStartLabel.setForeground(new Color(30, 30, 47));
        currentStartLabel.setBackground(new Color(236, 240, 241));
        currentEndLabel.setText("\uD83D\uDDD3 Fine Hackathon:");
        currentEndLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        currentEndLabel.setForeground(new Color(30, 30, 47));
        currentEndLabel.setBackground(new Color(236, 240, 241));
        currentStartRegLabel.setText("\uD83D\uDDD3 Inizio iscrizioni:");
        currentStartRegLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        currentStartRegLabel.setForeground(new Color(30, 30, 47));
        currentStartRegLabel.setBackground(new Color(236, 240, 241));
        currentMaxRegLabel.setText("\uD83C\uDF9F Partecipanti massimi:");
        currentMaxRegLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        currentMaxRegLabel.setForeground(new Color(30, 30, 47));
        currentMaxRegLabel.setBackground(new Color(236, 240, 241));

        currentCounterLabel.setText("\uD83D\uDEB9 Contatore iscritti:");
        currentCounterLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        currentCounterLabel.setForeground(new Color(30, 30, 47));
        currentCounterLabel.setBackground(new Color(236, 240, 241));
        currentMaxTeamParLabel.setText("\uD83D\uDC6B Partecipanti massimi per team:");
        currentMaxTeamParLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        currentMaxTeamParLabel.setForeground(new Color(30, 30, 47));
        currentMaxTeamParLabel.setBackground(new Color(236, 240, 241));
        currentVenueArea.setForeground(new Color(30, 30, 47));
        currentTitleArea.setForeground(new Color(30, 30, 47));
        currentStartArea.setForeground(new Color(30, 30, 47));
        currentEndArea.setForeground(new Color(30, 30, 47));
        currentStartRegArea.setForeground(new Color(30, 30, 47));
        currentMaxRegArea.setForeground(new Color(30, 30, 47));
        currentCounterArea.setForeground(new Color(30, 30, 47));

        hackathonPanel.setBorder(new LineBorder(new Color(30, 30, 47)));

        problemArea.setBorder(new LineBorder(new Color(30, 30, 47)));
        commentArea.setBorder(new LineBorder(new Color(30, 30, 47)));
        docScrollPane.setBorder(new LineBorder(new Color(30, 30, 47)));
        scrollPane.setBorder(null);

        controller.findHack();
        controller.setHackValue(currentTitleArea, currentVenueArea, currentStartArea, currentEndArea, currentStartRegArea, currentMaxRegArea, currentCounterArea, currentMaxTeamParArea, problemArea);

        int commentWidth = commentArea.getWidth();
        commentArea.setPreferredSize(new Dimension(commentWidth, 100));
        int problemWidth = problemArea.getWidth();
        problemArea.setPreferredSize(new Dimension(problemWidth, 100));

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/iconaUser.png"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(resizedIcon);

        fNameArea.setText(controller.getUser().getfName());
        lNameArea.setText(controller.getUser().getlName());
        userArea.setText(controller.getUser().getUsername());

        markSlider.setMaximum(10);
        markSlider.setMinimum(0);
        markSlider.setValue(5);

        markSlider.setMinorTickSpacing(1); // Ogni step è di 1
        markSlider.setMajorTickSpacing(1); // Mostra i tick ogni 1 unità
        markSlider.setSnapToTicks(true); // Il cursore si muove esattamente sui tick
        markSlider.setPaintTicks(true); // Mostra i tick
        markSlider.setPaintLabels(true); // Mostra i numeri

        problemArea.setWrapStyleWord(true);
        problemArea.setLineWrap(true);


        if(problemArea.getText().equals("Descrizione problema ancora non definita")){
            sendProbButton.setText("Carica descrizione problema");
        } else {
            sendProbButton.setText("Modifica la descrizione del problema");
        }


        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                controller.findHack();
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
                if (problemArea.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Inserisci la descrizione del problema", "Send Problem Description", JOptionPane.ERROR_MESSAGE);
                } else {
                    int code = controller.handleProblemDescription(problemArea.getText());
                    if(code == 1){
                        if (sendProbButton.getText().equalsIgnoreCase("Carica descrizione problema")) {
                            JOptionPane.showMessageDialog(panel, "Descrizione caricata", "Descrizione problema", JOptionPane.INFORMATION_MESSAGE);
                            sendProbButton.setText("Modifica descrizione problema");
                        } else {
                            JOptionPane.showMessageDialog(panel, "Descrizione modificata", "Descrizione problema", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(panel, "Errore nel caricamento della descrizione");
                    }
                }
            }
        });


        teamComboBox.addItem("Seleziona un team");
        teamComboBoxMark.addItem("Seleziona un Team");
        ArrayList<String> teams = new ArrayList<>();

        controller.getTeams(teams);
        for(String team : teams) {
            teamComboBox.addItem(team);
            teamComboBoxMark.addItem(team);
        }


        DefaultListModel<String> model = new DefaultListModel<>();
        docList.setModel(model);
        ArrayList<byte[]> files = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> comments = new ArrayList<>();

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(teamComboBox.getSelectedItem().equals("Seleziona un team")){
                    JOptionPane.showMessageDialog(panel, "Seleziona un team", "ERRORE", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    files.removeAll(files);
                    names.removeAll(names);
                    comments.removeAll(comments);
                    model.removeAllElements();
                    controller.handleLoadFile((String) teamComboBox.getSelectedItem(), files, names, comments);
                    if(files.isEmpty() || names.isEmpty()){
                        model.addElement("Questo team non ha caricato documenti");
                        docList.setEnabled(false);
                    }else{
                        docList.setEnabled(true);
                        for(String name : names){
                            model.addElement(name);
                        }
                    }
                }
            }
        });

        docList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedIndex = docList.locationToIndex(e.getPoint());
                System.out.println(selectedIndex);
                if(e.getClickCount() == 1){
                    String commento = comments.get(selectedIndex);
                    if (commento != null) {
                        commentArea.setText(commento);
                        commentArea.setEnabled(false);
                        commentArea.setToolTipText("Documento già commentato");
                        loadButton.setEnabled(false);
                    }
                }else if (e.getClickCount() == 2) { // Rileva il doppio click
                    if (selectedIndex == -1) return;
                    try {
                        // Crea un file temporaneo in memoria
                        Path tempFile = Files.createTempFile("temp_", ".pdf");
                        Files.write(tempFile, files.get(selectedIndex));

                        // Apri con l'applicazione predefinita
                        Desktop.getDesktop().open(tempFile.toFile());

                        // Elimina il file all'uscita
                        tempFile.toFile().deleteOnExit();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(docList,
                                "Errore nell'apertura del file",
                                "Errore",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(commentArea.getText().isEmpty()){
                    JOptionPane.showMessageDialog(panel, "Scrivi un commento", "ERRORE", JOptionPane.ERROR_MESSAGE);
                }else if(docList.isSelectedIndex(-1) || teamComboBox.getSelectedItem().equals("Selezione un team")){
                    JOptionPane.showMessageDialog(panel, "Selezione sia il team che il documento");
                }else{
                    int code = controller.handleComment(commentArea.getText(), (String) docList.getSelectedValue(), (String)teamComboBox.getSelectedItem());
                    switch (code){
                        case 0:
                            JOptionPane.showMessageDialog(panel, "Non è stato possibile caricare il commento");
                            break;
                        case 1:
                            JOptionPane.showMessageDialog(panel, "Commento caricato correttamente");
                            commentArea.setEnabled(false);
                            commentArea.setToolTipText("Documento già commentato");
                            break;
                        default:
                            JOptionPane.showMessageDialog(panel, "Errore nel caricamento del documento");
                            break;
                    }
                }
            }
        });

        assigneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(teamComboBoxMark.getSelectedItem().equals("Seleziona un team")){
                    JOptionPane.showMessageDialog(panel, "Seleziona un team", "ERRORE", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int scelta = JOptionPane.showConfirmDialog(panel, "Sei sicuro di voler assegnare: " + markSlider.getValue() + "\nal team: " + teamComboBoxMark.getSelectedItem(),"Assegna voto", JOptionPane.YES_NO_OPTION);
                    if(scelta == JOptionPane.YES_OPTION){
                        int code = controller.handleAssignMark((String) teamComboBoxMark.getSelectedItem(), markSlider.getValue());
                        switch (code){
                            case 1:
                                JOptionPane.showMessageDialog(panel, "Voto inserito correttamente");
                                markSlider.setValue(markSlider.getValue());
                                markSlider.setEnabled(false);
                                markSlider.setToolTipText("Hai già inserito il voto per questo team");
                                assigneButton.setEnabled(false);
                                break;
                            case  0:
                            default:
                                JOptionPane.showMessageDialog(panel, "Errore durante il caricamento del voto");
                                break;
                        }
                    }
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                controller.getHome().getFrame().setVisible(true);
                controller.logout();
                JOptionPane.showMessageDialog(controller.getHome().getFrame(), "Logout eseguito");
                controller.getHome().getAreaPersonaleButton().setEnabled(false);
                controller.getHome().getLoginButton().setText("Login");
            }
        });

        teamComboBoxMark.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int grade = controller.getMark((String) teamComboBoxMark.getSelectedItem());
                if (grade != -1) {
                    markSlider.setValue(grade);
                    markSlider.setEnabled(false);
                    markSlider.setToolTipText("Hai già inserito il voto per questo team");
                    assigneButton.setEnabled(false);
                } else {
                    markSlider.setEnabled(true);
                    markSlider.setToolTipText(null);
                    assigneButton.setEnabled(true);
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
