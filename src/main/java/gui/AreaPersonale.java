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
import java.util.ArrayList;

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
    private JFrame frame;

    public AreaPersonale(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Area Personale");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

        cambiaUsernameButton.setForeground(new Color(0, 200, 255)); // Azzurro cyberR
        cambiaPasswordButton.setForeground(new Color(255, 0, 150)); // Magenta neon
        textArea1.setForeground(new Color(0, 255, 0)); // Verde neon tipo Matrix
        requestList.setForeground(new Color(0, 255, 0));


        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
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
                controller.sendRequest(messageArea.getText(), (String) participantComboBox.getSelectedItem());
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


    public JFrame getFrame() {
        return frame;
    }

    public JPanel getTeamPanel() {
        return teamPanel;
    }

    public JPanel getMessagePanel() {
        return messagePanel;
    }

    public JPanel getParticipantPanel() {
        return participantPanel;
    }
}
