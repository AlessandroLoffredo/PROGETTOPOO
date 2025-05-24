package gui;

import controller.Controller;
import model.Organizer;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class AreaPersonaleOrganizzatore {
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
    private JTextArea infoHackathon;
    private JPanel hackathonPanel;
    private JButton startSingUpButton;
    private JSpinner spinner1;
    private JButton inviaRichiestaButton;
    private JLabel invLabel;
    private JLabel hackLabel;
    private JLabel dateLabel;
    private JPanel invPanel;
    private JPanel organizerPanel;
    private JComboBox comboBox1;
    private JFrame frame;

    public AreaPersonaleOrganizzatore(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Area Personale");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);


        SpinnerDateModel startModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        spinner1.setModel(startModel);
        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(spinner1, "dd/MM/yyyy");
        spinner1.setEditor(startEditor);

        panel.setBackground(new Color(10, 10, 30)); // Blu notte/nero futuristico
        infoHackathon.setBackground(new Color(15, 15, 50)); // Uguale a hackListPanel
        fNameArea.setBackground(new Color(15, 15, 50));
        lNameArea.setBackground(new Color(15, 15, 50));
        userArea.setBackground(new Color(15, 15, 50));


        fNameLabel.setForeground(new Color(0, 255, 0)); // Verde neon tipo Matrix
        lNameLabel.setForeground(new Color(0, 255, 0)); // Verde neon tipo Matrix
        userLabel.setForeground(new Color(0, 255, 0));
        hackLabel.setForeground(new Color(0, 255, 0));
        infoHackathon.setForeground(new Color(0, 200, 255));
        invLabel.setForeground(new Color(0, 255, 0));
        dateLabel.setForeground(new Color(0, 255, 0));


        cambiaUsernameButton.setForeground(new Color(0, 200, 255)); // Azzurro cyberR
        cambiaPasswordButton.setForeground(new Color(255, 0, 150)); // Magenta neon


        if(controller.verifyingStartHack()){
            organizerPanel.setVisible(false);
        }
        else{
            //INSERIRE UTENTI NELLA COMBOBOX CHE NON SONO IMPEGNATI A FARE ALTRO...
            //VALUTARE SE INSERIRE NUOVAMENTE ISBUSY
            comboBox1.addItem("-");
        }

        spinner1.addChangeListener(e -> {
            Date data = (Date) spinner1.getValue();
            if (data.before(new Date())) {
                spinner1.setValue(new Date());
            }
        });

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

        startSingUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controller.verifyingStartRegDate()){
                    JOptionPane.showMessageDialog(panel, "Hai già inserito la data di inizio iscrizioni per l'Hackathon", "ERRORE", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    SimpleDateFormat dateFormatted = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = (Date) spinner1.getValue();
                    int scelta = JOptionPane.showConfirmDialog(panel, "Sei sicuro di voler far partire le iscrizioni da: " + dateFormatted.format(date) + "?", "Data inizio iscrizioni", JOptionPane.YES_NO_OPTION);
                    if (scelta == JOptionPane.YES_OPTION) {
                        controller.handleStartSignUp(date);
                    }
                }
                spinner1.setValue(new Date());
            }
        });

        inviaRichiestaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox1.getSelectedItem().equals("-")){
                    JOptionPane.showMessageDialog(panel, "Devi scegliere un utente", "ERRORE", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    int code = controller.sendRequest((User)comboBox1.getSelectedItem());
                    if(code == 1){
                        JOptionPane.showMessageDialog(panel, "Hai già inviato una richiesta a questo utente", "INFO", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(panel, "Richiesta inviata con successo");
                    }
                }
            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }
}
