package gui;

import controller.Controller;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * La classe che contiene le informazioni dell'utente loggato e che gli permette di visualizzare
 * gli eventi a cui ha partecipato in passato e di aprirli per vederli nello specifico, modificare le credeniziali, e se è un partecipante di accedere
 * all'area del team di cui fa parte e di inviare richieste ad altri partecipanti, oltre a vedere quelle ricevute, alle quali può rispondere.
 * Mentre se è semplicemente un utente gli dà la possibilità di vedere gli inviti ricevuti e rispondere a questi ultimi.
 */
public class AreaPersonale {
    private JPanel panel;
    private JPanel dataPanel;
    private JLabel fNameArea;
    private JLabel lNameArea;
    private JLabel userArea;
    private JLabel fNameLabel;
    private JLabel lNameLabel;
    private JLabel userLabel;
    private JButton cambiaUsernameButton;
    private JButton cambiaPasswordButton;
    private JPanel requestsPanel;
    private JButton inviaRichiestaButton;
    private JComboBox<String> participantComboBox;
    private JPanel messagePanel;
    private JList<String> requestList;
    private JButton homeButton;
    private JTextArea messageArea;
    private JPanel teamPanel;
    private JLabel teamLabel;
    private JLabel textArea1;
    private JButton teamButton;
    private JLabel descLabel;
    private JLabel messageLabel;
    private JLabel imageLabel;
    private JLabel titleLabel;
    private JLabel profileLabel;
    private JPanel lastsHackPanel;
    private JButton logoutButton;
    private JPanel profilePanel;
    private JPanel elementsPanel;
    private JList<String> lastsHackList;
    private JScrollPane scrollPane;
    private JScrollPane messageScrollPane;
    private JPanel titlePanel;
    private JLabel hackListLabel;
    private JFrame frame;

    /**
     * Istanzia una nuova AreaPersonale
     *
     * @param frameChiamante il frame da cui si richiede di accedere all'ara personale
     * @param controller     il controller istanziato nella home
     */
    public AreaPersonale(JFrame frameChiamante, Controller controller) {
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
        frame.setSize(800, 800);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);

        String motivation = "Scrivi un messaggio motivazionale";
        String selection = "Seleziona un partecipante";
        // Palette colori
        Color darkBg = new Color(30, 30, 47);
        Color lightBg = new Color(236, 240, 241);
        Color primary = new Color(37, 99, 235);
        Color darkText = new Color(30, 30, 47);
        Color lightText = new Color(236, 240, 241);

        panel.setBackground(darkBg);
        profilePanel.setBackground(darkBg);
        textArea1.setBackground(darkBg);
        textArea1.setForeground(darkText);
        fNameArea.setBackground(darkBg);
        lNameArea.setBackground(darkBg);
        userArea.setBackground(darkBg);
        titlePanel.setBackground(darkBg);

        requestList.setBackground(lightBg);
        requestList.setForeground(darkText);
        hackListLabel.setForeground(darkText);

        elementsPanel.setBackground(lightBg);
        lastsHackPanel.setBackground(lightBg);
        lastsHackList.setBackground(lightBg);
        messagePanel.setBackground(lightBg);
        requestsPanel.setBackground(lightBg);

        fNameLabel.setForeground(lightText);
        lNameLabel.setForeground(lightText);
        userLabel.setForeground(lightText);
        fNameArea.setForeground(lightText);
        lNameArea.setForeground(lightText);
        userArea.setForeground(lightText);
        teamLabel.setForeground(lightText);
        descLabel.setForeground(darkText);
        messageLabel.setForeground(darkText);
        messageArea.setForeground(darkText);
        participantComboBox.setForeground(darkText);
        profileLabel.setForeground(lightText);
        titleLabel.setForeground(lightText);

        cambiaUsernameButton.setForeground(primary);
        cambiaPasswordButton.setForeground(primary);
        logoutButton.setForeground(primary);
        homeButton.setForeground(primary);
        textArea1.setForeground(lightText);
        inviaRichiestaButton.setForeground(primary);
        teamButton.setForeground(primary);
        scrollPane.setBorder(new LineBorder(lightText));
        messageScrollPane.setBorder(new LineBorder(darkText));

        fNameArea.setText(controller.getUser().getfName());
        lNameArea.setText(controller.getUser().getlName());
        userArea.setText(controller.getUser().getUsername());

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/iconaUser.png"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(resizedIcon);

        this.participantSelector(controller, motivation, selection);
        this.fillReqInv(controller);
        this.lastHacks(controller);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getHome().getFrame().setVisible(true);
                controller.getHome().fillHacks(controller.getHome().getFont());
                controller.findHack();
                frame.dispose();
            }
        });

        messageArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                messageArea.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(messageArea.getText().equalsIgnoreCase("")){
                    messageArea.setText(motivation);
                }
            }
        });

        requestList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                handleReqInv(controller);
            }
        });

        cambiaUsernameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CambiaUsername cambiaUsername = new CambiaUsername(frame, controller, userArea);
                JFrame usernameFrame = cambiaUsername.getFrame();

                frame.setEnabled(false);
                usernameFrame.setAlwaysOnTop(true);
                usernameFrame.setVisible(true);

                usernameFrame.addWindowStateListener(new WindowStateListener() {
                    @Override
                    public void windowStateChanged(WindowEvent e) {
                        if ((e.getNewState() & Frame.ICONIFIED) == 0) {
                            // Solo se NON è iconizzata, mantieni il focus
                            usernameFrame.toFront();
                            usernameFrame.requestFocus();
                        }
                    }
                });

                usernameFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        frame.setEnabled(true);
                        frame.toFront();
                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {
                        // Quando viene ripristinata dalla barra delle applicazioni
                        usernameFrame.toFront();
                        usernameFrame.requestFocus();
                    }
                });
            }
        });

        cambiaPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CambiaPassword cambiaPassword = new CambiaPassword(frame, controller);
                JFrame passwordFrame = cambiaPassword.getFrame();

                frame.setEnabled(false);
                passwordFrame.setAlwaysOnTop(true);
                passwordFrame.setVisible(true);

                passwordFrame.addWindowStateListener(new WindowStateListener() {
                    @Override
                    public void windowStateChanged(WindowEvent e) {
                        if ((e.getNewState() & Frame.ICONIFIED) == 0) {
                            // Solo se NON è iconizzata, mantieni il focus
                            passwordFrame.toFront();
                            passwordFrame.requestFocus();
                        }
                    }
                });

                passwordFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        frame.setEnabled(true);
                        frame.toFront();
                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {
                        // Quando viene ripristinata dalla barra delle applicazioni
                        passwordFrame.toFront();
                        passwordFrame.requestFocus();
                    }
                });
            }
        });

        teamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TeamArea teamArea;
                teamArea = new TeamArea(frameChiamante, controller);
                teamArea.getFrame().setVisible(true);
                frame.dispose();
            }
        });

        inviaRichiestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((String) participantComboBox.getSelectedItem()).equalsIgnoreCase(selection) || messageArea.getText().equalsIgnoreCase(motivation)) {
                    JOptionPane.showMessageDialog(panel, "Inserire il messaggio e/o selezionare il partecipante", "WARNING", JOptionPane.WARNING_MESSAGE);
                } else {
                    int code = controller.sendRequest(messageArea.getText(), (String) participantComboBox.getSelectedItem());
                    switch (code) {
                        case -1:
                            JOptionPane.showMessageDialog(panel, "Il team del partecipante a cui vuoi unirti è pieno", "INFO", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case 0:
                            JOptionPane.showMessageDialog(panel, "Esiste già una richiesta da/verso quest'utente");
                            break;
                        case 1:
                            JOptionPane.showMessageDialog(panel, "Richiesta inviata con successo", "INFO", JOptionPane.INFORMATION_MESSAGE);
                            messageArea.setText("");
                            participantComboBox.setSelectedIndex(0);
                            break;
                        default:
                            JOptionPane.showMessageDialog(panel, "Errore durante l'invio della richiesta");
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

    }

    /**
     * Restituisce il frame che viene creato quando viene istanziata la pagina AreaPersonale
     *
     * @return il frame che viene istanziato
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Restituisce il panel che contiene tutte gli elementi legati al team presenti nell'area personale
     * quando l'utente loggato è un partecipante ad un hackathon attualmente in corso
     *
     * @return il panel che contiene gli elementi legati al team dell'utente loggato
     */
    public JPanel getTeamPanel() {
        return teamPanel;
    }

    /**
     * Restituisce il panel che contiene gli elementi che permettono al partecipante loggato
     * di chiedere ad un altro partecipante di unirsi al suo team
     *
     * @return il panel che contiene gli elementi che permettono al partecipanti loggato di inviare richieste ad altri partecipanti
     */
    public JPanel getMessagePanel() {
        return messagePanel;
    }

    private void participantSelector(Controller controller, String motivation, String selection){
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        int width = messageArea.getPreferredSize().width;
        messageArea.setPreferredSize(new Dimension(width, 100));

        if(!controller.getUserClass()){
            participantComboBox.addItem(selection);
            ArrayList<String> participants = new ArrayList<>();
            try {
                controller.getHackParticipants(participants);
                participantComboBox.removeAllItems();
                if (participants.isEmpty()) {
                    participantComboBox.addItem("Nessun partecipante disponibile");
                    participantComboBox.setEnabled(false);
                } else {
                    participantComboBox.addItem(selection);
                    for (String par : participants) {
                        participantComboBox.addItem(par);
                    }
                }
            } catch (Exception e) {
                participantComboBox.addItem("Errore nel caricamento");
                participantComboBox.setEnabled(false);
                e.printStackTrace();
            }
        }
        messageArea.setText(motivation);
    }

    private void fillReqInv(Controller controller){
        DefaultListModel<String> model = new DefaultListModel<>();
        requestList.setModel(model);
        if(controller.getUserClass()){
            model.removeAllElements();
            ArrayList<String> invites = new ArrayList<>();
            controller.getInvites(invites);
            if(invites.isEmpty()){
                model.addElement("Non hai ricevuto alcun invito");
                requestList.setEnabled(false);
            }else{
                for (String invite : invites){
                    model.addElement(invite);
                }
            }
        } else {
            model.removeAllElements();
            ArrayList<String> requests = new ArrayList<>();
            controller.getRequests(requests);
            if(requests.isEmpty()){
                model.addElement("Non hai ricevuto alcuna richiesta");
                requestList.setEnabled(false);
            }else{
                for(String request : requests){
                    model.addElement(request);
                }
            }
        }
    }

    private void lastHacks(Controller controller){
        ArrayList<ArrayList<Object>> lastsHack = new ArrayList<>();
        DefaultListModel<String> model2 = new DefaultListModel<>();
        controller.getLastsUserHack(lastsHack);
        model2.removeAllElements();
        for(ArrayList<Object> arrayList : lastsHack){
            model2.addElement((String) arrayList.get(0));
        }
        lastsHackList.setModel(model2);
        if(model2.isEmpty()){
            model2.removeAllElements();
            model2.addElement("Non hai ancora partecipato ad alcun hackathon");
            lastsHackList.setEnabled(false);
        } else {
            lastsHackList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    controller.setHackathon((String) lastsHack.get(lastsHackList.getSelectedIndex()).get(0), (String) lastsHack.get(lastsHackList.getSelectedIndex()).get(1), (Date) lastsHack.get(lastsHackList.getSelectedIndex()).get(2),
                            (Date) lastsHack.get(lastsHackList.getSelectedIndex()).get(3), (int) lastsHack.get(lastsHackList.getSelectedIndex()).get(4), (int) lastsHack.get(lastsHackList.getSelectedIndex()).get(5),
                            (String) lastsHack.get(lastsHackList.getSelectedIndex()).get(7), (Date) lastsHack.get(lastsHackList.getSelectedIndex()).get(8), (int) lastsHack.get(lastsHackList.getSelectedIndex()).get(6));
                    controller.setIdHack((int) lastsHack.get(lastsHackList.getSelectedIndex()).get(9));
                    controller.setPhoto((byte[]) lastsHack.get(lastsHackList.getSelectedIndex()).get(10));
                    controller.getJudgesList();
                    HackathonGui hackathonGui = new HackathonGui(frame, controller);
                    hackathonGui.getFrame().setVisible(true);
                    frame.dispose();
                }
            });
        }
    }

    private void handleReqInv(Controller controller){
        int selectedIndex = requestList.getSelectedIndex();
        if (selectedIndex == -1) return;
        int code;
        String[] parti = requestList.getSelectedValue().split(":", 2);
        int scelta = JOptionPane.showConfirmDialog(null, "Accettare?", "REQUEST", JOptionPane.YES_NO_OPTION);
        if(controller.getUserClass()){
            switch (scelta) {
                case JOptionPane.YES_OPTION:
                    code = controller.handleAccInvite(parti[1].trim());
                    break;
                case JOptionPane.NO_OPTION:
                    code = controller.handleDecInvite(parti[1].trim());
                    break;
                default:
                    code = -1;
                    break;
            }
        }else{
            switch (scelta) {
                case JOptionPane.YES_OPTION: {
                    code = controller.handleAccRequest(parti[0].trim());
                    break;
                }
                case JOptionPane.NO_OPTION: {
                    code = controller.handleDecRequest(parti[0].trim());
                    break;
                }
                default: {
                    code = -1;
                    break;
                }
            }
        }
        switch (code) {
            case 0:
                JOptionPane.showMessageDialog(panel, "Errore durante la gestione dell'invito");
                break;
            case -1:
                //se clicca la x sull'option pane non deve succedere nulla
                break;
            default:
                DefaultListModel<String> model = (DefaultListModel<String>) requestList.getModel();
                model.remove(selectedIndex);
                String string = scelta == JOptionPane.YES_OPTION ? "accettata" : "rifiutata";
                JOptionPane.showMessageDialog(panel, "Richiesta " + string + " con successo");
                break;
        }
    }
}
