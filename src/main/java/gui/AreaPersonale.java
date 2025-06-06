package gui;

import controller.Controller;
import model.Request;
import model.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Classe che contiene tutte le azioni e le informazioni di un utente o di un partecipante.
 */
public class AreaPersonale {
    private JPanel panel;
    private JPanel dataPanel;
    private JTextArea fNameArea;
    private JTextArea lNameArea;
    private JTextArea userArea;
    private JLabel fNameLabel;
    private JLabel lNameLabel;
    private JLabel userLabel;
    private JPanel operationPanel;
    private JButton cambiaUsernameButton;
    private JButton cambiaPasswordButton;
    private JPanel participantPanel;
    private JButton inviaRichiestaButton;
    private JComboBox participantComboBox;
    private JPanel messagePanel;
    private JList<Request> requestList;
    private JButton homeButton;
    private JTextField messageArea;
    private JPanel teamPanel;
    private JLabel teamLabel;
    private JTextArea textArea1;
    private JButton teamButton;
    private JLabel descLabel;
    private JLabel messageLabel;
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
        frame = new JFrame("Area Personale");
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
        frame.setLocationRelativeTo(null);


        panel.setBackground(new Color(10, 10, 30)); // Blu notte/nero futuristico
        textArea1.setBackground(new Color(15, 15, 50)); // Uguale a hackListPanel
        fNameArea.setBackground(new Color(15, 15, 50));
        lNameArea.setBackground(new Color(15, 15, 50));
        userArea.setBackground(new Color(15, 15, 50));
        requestList.setBackground(new Color(15, 15, 50));

        fNameLabel.setForeground(new Color(0, 255, 0)); // Verde neon tipo Matrix
        lNameLabel.setForeground(new Color(0, 255, 0)); // Verde neon tipo Matrix
        userLabel.setForeground(new Color(0, 255, 0));
        teamLabel.setForeground(new Color(0, 255, 0));
        descLabel.setForeground(new Color(0, 255, 0));
        messageLabel.setForeground(new Color(0, 255, 0));

        cambiaUsernameButton.setForeground(new Color(0, 200, 255)); // Azzurro cyberR
        cambiaPasswordButton.setForeground(new Color(255, 0, 150)); // Magenta neon
        textArea1.setForeground(new Color(0, 255, 0)); // Verde neon tipo Matrix
        requestList.setForeground(new Color(0, 255, 0));


        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getHome().getFrame().setVisible(true);
                frame.dispose();
            }
        });

        participantComboBox.addItem("-");
        participantComboBox.addItem("MAMMT");


        DefaultListModel<Request> model = new DefaultListModel<>();

        ArrayList<Request> richieste = new ArrayList<>();
        richieste.add(new Request("ciao", new User(null, null, "mammt", "pippo")));
        richieste.add(new Request("suca", new User(null, null, "patt", "pippo")));
        richieste.add(new Request("ciuccm e pall",  new User(null, null, "nonnt", "pippo")));
        richieste.add(new Request("ciao", new User(null, null, "mammt", "pippo")));
        richieste.add(new Request("suca", new User(null, null, "patt", "pippo")));
        richieste.add(new Request("ciuccm e pall",  new User(null, null, "nonnt", "pippo")));
        richieste.add(new Request("ciao", new User(null, null, "mammt", "pippo")));
        richieste.add(new Request("suca", new User(null, null, "patt", "pippo")));
        richieste.add(new Request("ciuccm e pall",  new User(null, null, "nonnt", "pippo")));
        richieste.add(new Request("ciao", new User(null, null, "mammt", "pippo")));
        richieste.add(new Request("suca", new User(null, null, "patt", "pippo")));
        richieste.add(new Request("ciuccm e pall",  new User(null, null, "nonnt", "pippo")));
        richieste.add(new Request("ciao", new User(null, null, "mammt", "pippo")));
        richieste.add(new Request("suca", new User(null, null, "patt", "pippo")));
        richieste.add(new Request("ciuccm e pall",  new User(null, null, "nonnt", "pippo")));
        richieste.add(new Request("ciao", new User(null, null, "mammt", "pippo")));
        richieste.add(new Request("suca", new User(null, null, "patt", "pippo")));
        richieste.add(new Request("ciuccm e pall",  new User(null, null, "nonnt", "pippo")));
        richieste.add(new Request("ciao", new User(null, null, "mammt", "pippo")));
        richieste.add(new Request("suca", new User(null, null, "patt", "pippo")));
        richieste.add(new Request("ciuccm e pall",  new User(null, null, "nonnt", "pippo")));
        //for(Request r : controller.getRequests())
          //  model.addElement(r);

        for(Request r : richieste)
            model.addElement(r);

        requestList.setModel(model);
//        frame.add(new JScrollPane(requestList));

        requestList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int scelta = JOptionPane.showConfirmDialog(null, "Accettare?", "REQUEST", JOptionPane.YES_NO_OPTION);
                if (scelta == JOptionPane.YES_OPTION) {
                    System.out.println("L'utente ha scelto Sì.");
                    // Split della stringa
                    String[] parti = requestList.getSelectedValue().toString().split(":", 2); // Il '2' limita il numero di suddivisioni

                    int t = controller.handleAccRequest(parti[0], frame);
                } else {
                    System.out.println("L'utente ha scelto No.");
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
                if (((String) participantComboBox.getSelectedItem()).equalsIgnoreCase("-") || messageArea.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Inserire il messaggio e/o scegliere l'utente", "WARNING", JOptionPane.WARNING_MESSAGE);
                } else {
                    int code = controller.sendRequest(messageArea.getText(), (String) participantComboBox.getSelectedItem());
                    if (code == -2) {
                        JOptionPane.showMessageDialog(panel, "Non sei autorizzato a mandare richieste", "WARNING", JOptionPane.WARNING_MESSAGE);
                    } else if (code == -1) {
                        JOptionPane.showMessageDialog(panel, "Il team dell'utente a cui vuoi unirti è pieno", "INFO", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(panel, "Richiesta inviata con successo", "INFO", JOptionPane.INFORMATION_MESSAGE);
                        messageArea.setText("");
                        participantComboBox.setSelectedIndex(0);
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
    public JPanel getParticipantPanel() {
        return participantPanel;
    }
}
