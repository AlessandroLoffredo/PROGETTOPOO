package gui;

import controller.Controller;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * The type Area personale organizzatore.
 */
public class AreaPersonaleOrganizzatore {
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
    private JPanel hackathonPanel;
    private JButton startSignUpButton;
    private JSpinner spinner1;
    private JButton inviaRichiestaButton;
    private JLabel invLabel;
    private JLabel hackLabel;
    private JLabel dateLabel;
    private JPanel organizerPanel;
    private JComboBox<String> comboBox1;
    private JPanel profilePanel;
    private JLabel profileLabel;
    private JLabel titleLabel;
    private JLabel imageLabel;
    private JPanel datePanel;
    private JButton logoutButton;
    private JPanel elementsPanel;
    private JPanel dataPanel;
    private JLabel currentTitleLabel;
    private JLabel currentVenueLabel;
    private JLabel currentStartLabel;
    private JLabel currentEndLabel;
    private JLabel currentStartRegLabel;
    private JLabel currentProbDescLabel;
    private JLabel currentMaxTeamParLabel;
    private JLabel currentCounterLabel;
    private JLabel currentMaxRegLabel;
    private JLabel currentTitleArea;
    private JLabel currentVenueArea;
    private JLabel currentStartArea;
    private JLabel currentMaxRegArea;
    private JLabel currentEndArea;
    private JLabel currentCounterArea;
    private JLabel currentStartRegArea;
    private JLabel currentMaxTeamParArea;
    private JScrollPane currentProbDescScroll;
    private JTextArea currentProbDescArea;
    private JFrame frame;

    /**
     * Instantiates a new Area personale organizzatore.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     */
    public AreaPersonaleOrganizzatore(JFrame frameChiamante, Controller controller) {
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
        String dateFormat = "dd/MM/yyyy";
        LocalDate[] dates = new LocalDate[3];


        fNameArea.setText(controller.getUser().getfName());
        lNameArea.setText(controller.getUser().getlName());
        userArea.setText(controller.getUser().getUsername());


        panel.setBackground(new Color(30, 30, 47));
        profilePanel.setBackground(new Color(30, 30, 47));
        fNameLabel.setForeground(new Color(236, 240, 241));
        lNameLabel.setForeground(new Color(236, 240, 241));
        userLabel.setForeground(new Color(236, 240, 241));
        fNameArea.setForeground(new Color(236, 240, 241));
        lNameArea.setForeground(new Color(236, 240, 241));
        userArea.setForeground(new Color(236, 240, 241));
        profileLabel.setForeground(new Color(236, 240, 241));
        datePanel.setBackground(new Color(236, 240, 241));
        elementsPanel.setBackground(new Color(236, 240, 241));
        organizerPanel.setBackground(new Color(236, 240, 241));
        hackathonPanel.setBackground(new Color(236, 240, 241));

        hackLabel.setForeground(new Color(30, 30, 47));
        invLabel.setBackground(new Color(30, 30, 47));
        dateLabel.setBackground(new Color(30, 30, 47));
        spinner1.setForeground(new Color(30, 30, 47));
        comboBox1.setForeground(new Color(30, 30, 47));
        titleLabel.setForeground(new Color(236, 240, 241));
        dateLabel.setForeground(new Color(30, 30, 47));
        invLabel.setForeground(new Color(30, 30, 47));


        cambiaUsernameButton.setForeground(new Color(37, 99, 235));
        cambiaPasswordButton.setForeground(new Color(37, 99, 235));
        startSignUpButton.setForeground(new Color(37, 99, 235));
        inviaRichiestaButton.setForeground(new Color(37, 99, 235));
        homeButton.setForeground(new Color(37, 99, 235));
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
        currentProbDescLabel.setText("\uD83D\uDCC3 Descrizione problema:");
        currentProbDescLabel.setFont(new Font(font, Font.PLAIN, 14));
        currentProbDescLabel.setForeground(new Color(30, 30, 47));
        currentProbDescLabel.setBackground(new Color(236, 240, 241));
        currentProbDescArea.setForeground(new Color(30, 30, 47));
        currentProbDescArea.setBackground(new Color(236, 240, 241));
        currentProbDescScroll.setBorder(new LineBorder(null));
        currentProbDescArea.setBorder(new LineBorder(new Color(30, 30, 47)));
        int width = currentProbDescArea.getWidth();
        currentProbDescArea.setPreferredSize(new Dimension(width, 100));
        hackathonPanel.setBorder(new LineBorder(new Color(30, 30, 47)));

        controller.setHackValue(currentTitleArea, currentVenueArea, currentStartArea, currentEndArea, currentStartRegArea, currentMaxRegArea, currentCounterArea, currentMaxTeamParArea,currentProbDescArea);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/iconaUser.png"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(resizedIcon);

        datePanel.setBorder(new LineBorder(new Color(30, 30, 47)));
        organizerPanel.setBorder(new LineBorder(new Color(30, 30, 47)));

        this.blockJudge(controller, dateFormat, dates);
        this.fillJudge(controller, dates);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                controller.getHome().fillHacks(controller.getHome().getFont());
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

        startSignUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSignUp(controller, startSignUpButton, dateFormat);
            }
        });

        inviaRichiestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox1.getSelectedItem().equals("Seleziona un utente")) {
                    JOptionPane.showMessageDialog(panel, "Devi scegliere un utente", "ERRORE", JOptionPane.ERROR_MESSAGE);
                } else {
                    try{
                        int code = controller.sendRequestOrganizer((String) comboBox1.getSelectedItem());
                        switch (code) {
                            case -2:
                                JOptionPane.showMessageDialog(panel, "L'utente invitato deve essere diverso dall'organizzatore che invita");
                                break;
                            case -1:
                                JOptionPane.showMessageDialog(panel, "L'utente invitato è impegnato nel periodo di tempo dell'Hackathon che gestisci");
                                break;
                            case 0:
                                JOptionPane.showMessageDialog(panel, "Utente già invitato");
                                break;
                            case 1:
                                JOptionPane.showMessageDialog(panel, "Utente " + comboBox1.getSelectedItem() + " invitato");
                                break;
                            default:
                                JOptionPane.showMessageDialog(panel, "Errore durante l'invito");
                                break;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(panel, "Errore durante l'invito");
                    }
                    comboBox1.setSelectedIndex(0);
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

        spinner1.addChangeListener(e -> {
            Date startDateIns = (Date) spinner1.getValue();
            if(new Date().after(startDateIns)){
                spinner1.setValue(new Date());
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

    private void blockJudge(Controller controller, String dateFormat, LocalDate[] dates){
        controller.getDates(dates);
        ZonedDateTime zonedDateTime = dates[0].minusDays(3).atStartOfDay(ZoneId.systemDefault());
        Date startDate = Date.from(zonedDateTime.toInstant());
        if (controller.isStarted() || controller.isHackStarted()) {
            organizerPanel.setEnabled(false);
            datePanel.setEnabled(false);
            spinner1.setEnabled(false);
            startSignUpButton.setEnabled(false);
            organizerPanel.setToolTipText("Non puoi più inviare richieste agli utenti per partecipare come giudici");
            datePanel.setToolTipText("La data di apertura delle iscrizioni per questo evento è stata già inserita");
            if (dates[2] != null) {
                ZonedDateTime zonedDateTime1 = dates[2].atStartOfDay(ZoneId.systemDefault());
                Date startRegDate = Date.from(zonedDateTime1.toInstant());
                SpinnerDateModel startModel = new SpinnerDateModel(startRegDate, null, null, Calendar.DAY_OF_MONTH);
                spinner1.setModel(startModel);
                JSpinner.DateEditor startEditor = new JSpinner.DateEditor(spinner1, dateFormat);
                spinner1.setEditor(startEditor);
                spinner1.setValue(startRegDate);
                if (startRegDate.before(new Date()) || startRegDate.equals(new Date()) || controller.isHackStarted()) {
                    comboBox1.setEnabled(false);
                    inviaRichiestaButton.setEnabled(false);
                }
            }
        } else {
            SpinnerDateModel startModel = new SpinnerDateModel(new Date(), null, startDate, Calendar.DAY_OF_MONTH);
            spinner1.setModel(startModel);
            JSpinner.DateEditor startEditor = new JSpinner.DateEditor(spinner1, dateFormat);
            spinner1.setEditor(startEditor);
        }
    }

    private void startSignUp(Controller controller, JButton startSignUpButton, String dateFormat){
        if (controller.verifyingStartRegDate()) {
            JOptionPane.showMessageDialog(panel, "Hai già inserito la data di inizio iscrizioni per l'Hackathon", "ERRORE", JOptionPane.ERROR_MESSAGE);
        } else {
            SimpleDateFormat dateFormatted = new SimpleDateFormat(dateFormat);
            Date date = (Date) spinner1.getValue();
            int scelta = JOptionPane.showConfirmDialog(panel, "Sei sicuro di voler far partire le iscrizioni da: " + dateFormatted.format(date) + "?", "Data inizio iscrizioni", JOptionPane.YES_NO_OPTION);
            if (scelta == JOptionPane.YES_OPTION) {
                LocalDate startRegDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int code = controller.handleStartSignUp(startRegDate);
                switch (code) {
                    case -1:
                        JOptionPane.showMessageDialog(panel, "La data deve essere almeno due giorni prima dell'inizo dell'Hackathon");
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(panel, "Inserimento della data avvenuto con successo");
                        startSignUpButton.setEnabled(false);
                        spinner1.setEnabled(false);
                        startSignUpButton.setToolTipText("La data di apertura delle iscrizioni per questo evento è stata già inserita");
                        if (controller.isStarted())
                            organizerPanel.setVisible(false);
                        break;
                    default:
                        JOptionPane.showMessageDialog(panel, "Errore durante l'aggiornamento della data");
                        break;
                }
            }
        }
            spinner1.setValue(spinner1.getValue());
    }

    private void fillJudge(Controller controller, LocalDate[] dates){
        try {
            ArrayList<String> judges = new ArrayList<>();
            controller.getFreeUser(judges, dates[0], dates[1]);
            comboBox1.addItem("Seleziona un utente");
            for (String judge : judges) {
                comboBox1.addItem(judge);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(panel, "ERRORE DURANTE LA RICERCA DEI GIUDICI");
        }
    }
}