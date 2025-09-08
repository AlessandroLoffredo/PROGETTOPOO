package gui;

import controller.Controller;



import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 * La classe che istanzia l'area personale del giudice, al cui interno si trovano tutti gli elementi che consentono
 * a quest'ultimo di assolvere ai suoi compiti, quali commentare i documenti caricati dai team, mettere dei voti ai team,
 * e definire qual è la descrizione del problema dell'hackathon di cui è un giudice. Oltre a mostrargli tutte le informazioni
 * dell'evento a cui si è dedicato.
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
    private JList<String> docList;
    private JPanel markPanel;
    private JTextArea problemArea;
    private JPanel problemPanel;
    private JButton sendProbButton;
    private JPanel selectExaminePanel;
    private JComboBox<String> teamComboBox;
    private JButton selectButton;
    private JTextArea commentArea;
    private JButton loadButton;
    private JButton assigneButton;
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
     * Istanzia una nuova AreaPersonaleGiudice
     *
     * @param frameChiamante il frame da cui si richiede di accedere all'area personale
     * @param controller     il controller istanziato nella home
     */
    public AreaPersonaleGiudice(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("HackManager");
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
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        String font = "Segoe UI Emoji";
        String error = "ERRORE";
        String teamSelection = "Seleziona un team";

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
        currentVenueLabel.setFont(new Font(font, Font.PLAIN, 14));
        currentVenueLabel.setForeground(new Color(30, 30, 47));
        currentVenueLabel.setBackground(new Color(236, 240, 241));
        currentTitleLabel.setText("\uD83D\uDCCC Titolo:");
        currentTitleLabel.setFont(new Font(font, Font.PLAIN, 14));
        currentTitleLabel.setForeground(new Color(30, 30, 47));
        currentTitleLabel.setBackground(new Color(236, 240, 241));
        currentStartLabel.setText("\uD83D\uDDD3 Inizio Hackathon:");
        currentStartLabel.setFont(new Font(font, Font.PLAIN, 14));
        currentStartLabel.setForeground(new Color(30, 30, 47));
        currentStartLabel.setBackground(new Color(236, 240, 241));
        currentEndLabel.setText("\uD83D\uDDD3 Fine Hackathon:");
        currentEndLabel.setFont(new Font(font, Font.PLAIN, 14));
        currentEndLabel.setForeground(new Color(30, 30, 47));
        currentEndLabel.setBackground(new Color(236, 240, 241));
        currentStartRegLabel.setText("\uD83D\uDDD3 Inizio iscrizioni:");
        currentStartRegLabel.setFont(new Font(font, Font.PLAIN, 14));
        currentStartRegLabel.setForeground(new Color(30, 30, 47));
        currentStartRegLabel.setBackground(new Color(236, 240, 241));
        currentMaxRegLabel.setText("\uD83C\uDF9F Partecipanti massimi:");
        currentMaxRegLabel.setFont(new Font(font, Font.PLAIN, 14));
        currentMaxRegLabel.setForeground(new Color(30, 30, 47));
        currentMaxRegLabel.setBackground(new Color(236, 240, 241));

        currentCounterLabel.setText("\uD83D\uDEB9 Contatore iscritti:");
        currentCounterLabel.setFont(new Font(font, Font.PLAIN, 14));
        currentCounterLabel.setForeground(new Color(30, 30, 47));
        currentCounterLabel.setBackground(new Color(236, 240, 241));
        currentMaxTeamParLabel.setText("\uD83D\uDC6B Partecipanti massimi per team:");
        currentMaxTeamParLabel.setFont(new Font(font, Font.PLAIN, 14));
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

        DefaultListModel<String> model = new DefaultListModel<>();
        docList.setModel(model);
        ArrayList<byte[]> files = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> comments = new ArrayList<>();

        controller.findHack();
        controller.setHackValue(currentTitleArea, currentVenueArea, currentStartArea, currentEndArea, currentStartRegArea, currentMaxRegArea, currentCounterArea, currentMaxTeamParArea, problemArea);
        this.loadDescription(controller);
        this.fillTeams(controller, teamSelection);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                controller.findHack();
                controller.getHome().fillHacks(controller.getHome().getFont());
                frame.dispose();
            }
        });

        cambiaUsernameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CambiaUsername cambiaUsername = new CambiaUsername(frame, controller, userArea);
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
                sendProblem(controller);
            }
        });



        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDocs(controller, teamSelection, error, files, names, comments, model);
                if(Date.valueOf(currentStartArea.getText()).after(new java.util.Date())){
                    assigneButton.setEnabled(false);
                    loadButton.setEnabled(false);
                }
            }
        });

        docList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openDocs(e, comments, files);
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadComment(controller, error);
            }
        });

        assigneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(teamComboBox.getSelectedItem().equals(teamSelection)){
                    JOptionPane.showMessageDialog(panel, teamSelection, error, JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int scelta = JOptionPane.showConfirmDialog(panel, "Sei sicuro di voler assegnare: " + markSlider.getValue() + "\nal team: " + teamComboBox.getSelectedItem(),"Assegna voto", JOptionPane.YES_NO_OPTION);
                    if(scelta == JOptionPane.YES_OPTION){
                        int code = controller.handleAssignMark((String) teamComboBox.getSelectedItem(), markSlider.getValue());
                        if (code == 1) {
                            JOptionPane.showMessageDialog(panel, "Voto inserito correttamente");
                            markSlider.setValue(markSlider.getValue());
                            markSlider.setEnabled(false);
                            markSlider.setToolTipText("Hai già inserito il voto per questo team");
                            assigneButton.setEnabled(false);
                        } else {
                            JOptionPane.showMessageDialog(panel, "Errore durante il caricamento del voto");
                        }
                    }
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getHome().fillHacks(controller.getHome().getFont());
                controller.getHome().getFrame().setVisible(true);
                controller.logout();
                JOptionPane.showMessageDialog(controller.getHome().getFrame(), "Logout eseguito");
                controller.getHome().getAreaPersonaleButton().setEnabled(false);
                controller.getHome().getLoginButton().setText("Login");
                frame.dispose();
            }
        });

        teamComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lockMark(controller);
            }
        });

        checkDate(controller);
    }


    /**
     * Restituisce il frame che viene creato quando viene istanziata la pagina AreaPersonaleGiudice
     *
     * @return il frame di AreaPersonaleGiudice
     */
    public JFrame getFrame() {
        return frame;
    }

    private void loadDescription(Controller controller){
        if(problemArea.getText().equals("Descrizione problema ancora non definita")){
            sendProbButton.setText("Carica descrizione problema");
        } else {
            sendProbButton.setText("Modifica la descrizione del problema");
        }

        if(controller.isHackStarted()){
            sendProbButton.setEnabled(false);
            sendProbButton.setToolTipText("L'evento  è già cominciato.\n" +
                    "Non è più possibile caricare/modificare la descrizione");
            problemArea.setEditable(false);
        }
    }

    private void fillTeams(Controller controller, String teamSelection){
        teamComboBox.addItem(teamSelection);
        ArrayList<String> teams = new ArrayList<>();

        controller.getTeams(teams);
        for(String team : teams) {
            teamComboBox.addItem(team);
        }
    }

    private void sendProblem(Controller controller){
        if (problemArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Inserisci la descrizione del problema", "Send Problem Description", JOptionPane.ERROR_MESSAGE);
        } else {
            int code = controller.handleProblemDescription(problemArea.getText());
            switch (code) {
                case 1:
                    if (sendProbButton.getText().equalsIgnoreCase("Carica descrizione problema")) {
                        JOptionPane.showMessageDialog(panel, "Descrizione caricata", "Descrizione problema", JOptionPane.INFORMATION_MESSAGE);
                        sendProbButton.setText("Modifica descrizione problema");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Descrizione modificata", "Descrizione problema", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                case 0:
                    JOptionPane.showMessageDialog(panel, "L'evento è già cominciato, non è possibile inserire o modificare la descrione");
                    break;
                default :
                    JOptionPane.showMessageDialog(panel, "Errore nel caricamento della descrizione");
                    break;
            }
        }
    }

    private void loadDocs(Controller controller, String teamSelection, String error,
                          ArrayList<byte[]> files, ArrayList<String> names, ArrayList<String> comments,
                          DefaultListModel<String> model){
        if(teamComboBox.getSelectedItem().equals(teamSelection)){
            JOptionPane.showMessageDialog(panel, teamSelection, error, JOptionPane.ERROR_MESSAGE);
        }
        else {
            files.clear();
            names.clear();
            comments.clear();
            model.removeAllElements();
            controller.handleLoadFile((String) teamComboBox.getSelectedItem(), files, names, comments);
            if(files.isEmpty() || names.isEmpty()){
                model.addElement("Questo team non ha caricato documenti");
                docList.setEnabled(false);
                loadButton.setEnabled(false);
                assigneButton.setEnabled(false);
            }else{
                docList.setEnabled(true);
                loadButton.setEnabled(true);
                assigneButton.setEnabled(true);
                for(String name : names){
                    model.addElement(name);
                }
            }
        }
    }

    private void openDocs(MouseEvent e, ArrayList<String> comments, ArrayList<byte[]> files){
        int selectedIndex = docList.locationToIndex(e.getPoint());
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

    private void uploadComment(Controller controller, String error){
        if(commentArea.getText().isEmpty()){
            JOptionPane.showMessageDialog(panel, "Scrivi un commento", error, JOptionPane.ERROR_MESSAGE);
        }else if(docList.isSelectedIndex(-1) || teamComboBox.getSelectedItem().equals("Selezione un team")){
            JOptionPane.showMessageDialog(panel, "Seleziona sia il team che il documento");
        }else{
            int code = controller.handleComment(commentArea.getText(), docList.getSelectedValue(), (String)teamComboBox.getSelectedItem());
            switch (code){
                case 0:
                    JOptionPane.showMessageDialog(panel, "Seleziona un documento da commentare");
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

    private void lockMark(Controller controller){
        int grade = controller.getMark((String) teamComboBox.getSelectedItem());
        if (grade != -1) {
            markSlider.setValue(grade);
            markSlider.setEnabled(false);
            markSlider.setToolTipText("Hai già inserito il voto per questo team");
            assigneButton.setEnabled(false);
        } else {
            markSlider.setToolTipText(null);
        }
    }

    private void checkDate(Controller controller){
        LocalDate localDate = LocalDate.parse(currentStartArea.getText()).minusDays(3);
        java.util.Date date = java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        if(!controller.isSignUpInserted() && !(date.after(new java.util.Date()))){
            problemArea.setText("L'evento è stato annullato a causa di mancata organizzazione");
            problemArea.setEditable(false);
            problemPanel.setEnabled(false);
            commentArea.setEditable(false);
            markPanel.setEnabled(false);
            teamComboBox.setEnabled(false);
            selectButton.setEnabled(false);
            docList.setEnabled(false);
        }
    }
}
