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
 * Classe che contiene tutte le azioni e le informazioni di un utente o di un partecipante.
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
    private JComboBox participantComboBox;
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
    private JList lastsHackList;
    private JScrollPane scrollPane;
    private JScrollPane messageScrollPane;
    private JPanel titlePanel;
    private JLabel hackListLabel;
    private JFrame frame;

    /**
     * Instanzia una nuova AreaPersonale.
     * <p>
     * La classe AreaPersonale varia in base all'utente che vi accede.
     * <br>Se vi accede un semplice utente, allora potrà soltanto vedere i suoi dati e modificarli, e rispondere alle richieste ricevute da un organizzatore
     * che lo invita a diventare giudice di un Hackthon.
     * <br>Se vi accede un partecipante ad un Hackathon in corso, potrà, oltre a vedere e modificare i suoi dati, vedere le informazioni
     * sul team di cui fa parte, creare un team se non fa parte di nessun team esiste, inviare richieste per unirsi ad un partecipante dello stesso hackathon
     * e rispondere alle richieste ricevute dagli altri utenti.
     * </p>
     * @param frameChiamante Il frame che istanzia la nuova AreaPersonale.
     * @param controller     Il controller istanziato dalla classe Home.java
     */
    public AreaPersonale(JFrame frameChiamante, Controller controller) {
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
        frame.setSize(800, 800);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);

        /*textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(true);
        textArea2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        textArea2.append("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        textArea2.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        textArea2.append("\n\n\n\n\n\n\n\n");
        textArea2.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        textArea2.append("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        textArea2.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        textArea2.append("\n\n\n\n\n\n\n\n");*/

        panel.setBackground(new Color(30, 30, 47));
        profilePanel.setBackground(new Color(30, 30, 47));
        textArea1.setBackground(new Color(30, 30, 47));
        textArea1.setForeground(new Color(30, 30, 47));
        fNameArea.setBackground(new Color(30, 30, 47));
        lNameArea.setBackground(new Color(30, 30, 47));
        userArea.setBackground(new Color(30, 30, 47));
        titlePanel.setBackground(new Color(30, 30, 47));
        requestList.setBackground(new Color(236, 240, 241));
        requestList.setForeground(new Color(30, 30, 47));
        hackListLabel.setForeground(new Color(30, 30, 47));
        elementsPanel.setBackground(new Color(236, 240, 241));
        lastsHackPanel.setBackground(new Color(236, 240, 241));
        lastsHackList.setBackground(new Color(236, 240, 241));
        messagePanel.setBackground(new Color(236, 240, 241));
        requestsPanel.setBackground(new Color(236, 240, 241));

        fNameLabel.setForeground(new Color(236, 240, 241));
        lNameLabel.setForeground(new Color(236, 240, 241));
        userLabel.setForeground(new Color(236, 240, 241));
        fNameArea.setForeground(new Color(236, 240, 241));
        lNameArea.setForeground(new Color(236, 240, 241));
        userArea.setForeground(new Color(236, 240, 241));
        teamLabel.setForeground(new Color(236, 240, 241));
        teamLabel.setForeground(new Color(236, 240, 241));
        descLabel.setForeground(new Color(30, 30, 47));
        messageLabel.setForeground(new Color(30, 30, 47));
        messageArea.setForeground(new Color(30, 30, 47));
        participantComboBox.setForeground(new Color(30, 30, 47));
        profileLabel.setForeground(new Color(236, 240, 241));
        titleLabel.setForeground(new Color(236, 240, 241));

        cambiaUsernameButton.setForeground(new Color(37, 99, 235));
        cambiaPasswordButton.setForeground(new Color(37, 99, 235));
        logoutButton.setForeground(new Color(37, 99, 235));
        homeButton.setForeground(new Color(37, 99, 235));
        textArea1.setForeground(new Color(236, 240, 241));
        inviaRichiestaButton.setForeground(new Color(37, 99, 235));
        teamButton.setForeground(new Color(37, 99, 235));

        fNameArea.setText(controller.getUser().getfName());
        lNameArea.setText(controller.getUser().getlName());
        userArea.setText(controller.getUser().getUsername());

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/iconaUser.png"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(resizedIcon);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getHome().getFrame().setVisible(true);
                controller.findHack();
                frame.dispose();
            }
        });

        scrollPane.setBorder(new LineBorder(new Color(236, 240, 241)));
        messageScrollPane.setBorder(new LineBorder(new Color(30, 30, 47)));

        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        int width = messageArea.getPreferredSize().width;
        messageArea.setPreferredSize(new Dimension(width, 100));
        if(!controller.getUserClass()){
            participantComboBox.addItem("Seleziona un partecipante");
            ArrayList<String> participants = new ArrayList<>();
            try {
                controller.getHackParticipants(participants);
                participantComboBox.removeAllItems();
                if (participants.isEmpty()) {
                    participantComboBox.addItem("Nessun partecipante disponibile");
                    participantComboBox.setEnabled(false);
                } else {
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
        messageArea.setText("Scrivi un messaggio motivazionale");
        messageArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                messageArea.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(messageArea.getText().equalsIgnoreCase("")){
                    messageArea.setText("Scrivi un messaggio motivazionale");
                }
            }
        });


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



//        frame.add(new JScrollPane(requestList));

        requestList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                int selectedIndex = requestList.getSelectedIndex();
                if (selectedIndex == -1) return;
                int code;
                String[] parti = requestList.getSelectedValue().toString().split(":", 2);
                int scelta = JOptionPane.showConfirmDialog(null, "Accettare?", "REQUEST", JOptionPane.YES_NO_OPTION);
                if(controller.getUserClass()){
                    if (scelta == JOptionPane.YES_OPTION) {
                        code = controller.handleAccInvite(parti[1].trim());
                    } else if (scelta == JOptionPane.NO_OPTION){
                        code = controller.handleDecInvite(parti[1].trim());
                    } else{
                        code = -1;
                    }
                }else{
                    if (scelta == JOptionPane.YES_OPTION) {
                        code = controller.handleAccRequest(parti[0].trim());
                    } else if(scelta == JOptionPane.NO_OPTION){
                        code = controller.handleDecRequest(parti[0].trim());
                    }else {
                        code = -1;
                    }
                }
                if(code == 0) {
                    JOptionPane.showMessageDialog(panel, "Errore durante la gestione dell'invito");
                }else if(code == -1) {
                    //se clicca la x sull'option pane non deve succedere nulla //TODO CAPIRE SE DEVE SUCCEDRE QLC
                }else{
                    DefaultListModel<String> model = (DefaultListModel<String>) requestList.getModel();
                    model.remove(selectedIndex);
                    String string = scelta == JOptionPane.YES_OPTION ? "accettata" : "rifiutata";
                    JOptionPane.showMessageDialog(panel, "Richiesta " + string + " con successo");
                }
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

        teamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TeamArea teamArea;
                teamArea = new TeamArea(frameChiamante, controller);
                teamArea.getFrame().setVisible(true);
                frame.dispose();
            }
        });

        /*
        INSERIRE CONTROLLO PER VEDERE SE IL PARTECIPANTE FA PARTE DI UN TEAM...
        OSCURARE L'AREA TEAM QUALORA NON NE FACCIA ANCORA PARTE
         */

        /*inviaRichiestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.sendRequestOrganizer(messageArea.getText(), (String) participantComboBox.getSelectedItem());
            }
        });*/
        inviaRichiestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println((String) participantComboBox.getSelectedItem() + " - " +  messageArea.getText());
                if (((String) participantComboBox.getSelectedItem()).equalsIgnoreCase("Seleziona un partecipante") || messageArea.getText().equalsIgnoreCase("Scrivi un messaggio motivazionale")) {
                    JOptionPane.showMessageDialog(panel, "Inserire il messaggio e/o selezionare il partecipante", "WARNING", JOptionPane.WARNING_MESSAGE);
                } else {
                    int code = controller.sendRequest(messageArea.getText(), (String) participantComboBox.getSelectedItem());
                    switch (code) {
                        case -1:
                            JOptionPane.showMessageDialog(panel, "Il team del partecipante a cui vuoi unirti è pieno", "INFO", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case 0:
                            JOptionPane.showMessageDialog(panel, "Hai già inviato una richiesta a quest'utente");
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
                frame.dispose();
                controller.getHome().getFrame().setVisible(true);
                controller.logout();
                JOptionPane.showMessageDialog(controller.getHome().getFrame(), "Logout eseguito");
                controller.getHome().getAreaPersonaleButton().setEnabled(false);
                controller.getHome().getLoginButton().setText("Login");
            }
        });

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
                    HackathonGui hackathonGui = new HackathonGui(frame, controller);
                    hackathonGui.getFrame().setVisible(true);
                    frame.dispose();
                }
            });
        }


    }

    /**
     * Restituisce il frame principale della gui.
     *
     * @return JFrame: Il frame principale.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Restituisce il panel del team.
     *
     * @return JPanel: Il panel che contiene le informazioni sul team.
     */
    public JPanel getTeamPanel() {
        return teamPanel;
    }

    /**
     * Restituisce il panel di inserimento del messaggio.
     *
     * @return JPanel: il panel che contiene gli elementi per inviare un messaggio.
     */
    public JPanel getMessagePanel() {
        return messagePanel;
    }

    /**
     * Restituisce il panel che contiene la lista delle richieste ricevute.
     *
     * @return JPanel: panel che contiene gli elementi di gestione della lista di richieste.
     */
    public JPanel getRequestsPanel() {
        return requestsPanel;
    }
}
