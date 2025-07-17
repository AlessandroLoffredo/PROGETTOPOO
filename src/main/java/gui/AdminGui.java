package gui;

// TODO SISTEMARE LA GUI, RIMUOVERE LE PARTI IN ECCESSO

import controller.Controller;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 *  Classe che gestisce tutte le azioni che un Admin della piattaforma è in grado di eseguire.
 */
public class AdminGui {
    private JPanel panel;
    private JPanel listPanel;
    private JButton createButton;
    private JButton goToButton;
    private JPanel createHackathonPanel;
    private JPanel profilePanel;
    private JPanel dataPanel;
    private JLabel userLabel;
    private JTextArea adminArea;
    private JLabel profileLabel;
    private JLabel titleLabel;
    private JLabel imageLabel;
    private JLabel imageLabel2;
    private JPanel labelPanel;
    private JLabel venueLabel;
    private JLabel startHackLabel;
    private JLabel endHackLabel;
    private JLabel maxParLabel;
    private JLabel maxParTeamLabel;
    private JLabel organizerLabel;
    private JTextField titleArea;
    private JTextField venueArea;
    private JSpinner startSpinner;
    private JSpinner endSpinner;
    private JTextField maxParArea;
    private JComboBox comboBox;
    private JComboBox organizerComboBox;
    private JPanel buttonPanel;
    private JButton creaHackathonButton;
    private JPanel createPanel;
    private JPanel fieldPanel;
    private JTextArea welcomeArea;
    private JLabel immaginePanel;
    private JButton caricaFotoButton;
    private JLabel pathLabel;
    private JLabel iconLabel;
    private JFrame frame;
    private File file = null;

    /**
     * Instanzia una nuova AdminGui.
     * <p>
     * La classe AdminGui permette ad un Admin di creare un Hackathon e di tornare alla home.
     * <br>La creazione di un Hackathon richiede ad un Admin di inserire i dati della gara, e l'organizzatore che la gestirà.
     * </p>
     *
     * @param frameChiamante Il frame che istanzia la nuova AdminGui.
     * @param controller     Il controller istanziato dalla classe Home.java
     */
    public AdminGui(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Area Personale");
        frame.setContentPane(panel);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        panel.setBackground(new Color(236, 240, 241));
        createHackathonPanel.setBackground(new Color(236, 240, 241));
        createPanel.setBackground(new Color(236, 240, 241));
        labelPanel.setBackground(new Color(236, 240, 241));
        buttonPanel.setBackground(new Color(236, 240, 241));
        fieldPanel.setBackground(new Color(236, 240, 241));
        profileLabel.setForeground(new Color(236, 240, 241));
        adminArea.setForeground(new Color(236, 240, 241));
        welcomeArea.setForeground(new Color(236, 240, 241));
        welcomeArea.setBackground(new Color(30, 30, 47));
        createButton.setForeground(new Color(37, 99, 235));
        creaHackathonButton.setForeground(new Color(37, 99, 235));
        goToButton.setForeground(new Color(37, 99, 235));
        createHackathonPanel.setVisible(false);

        adminArea.setBackground(new Color(30, 30, 47));
        titleLabel.setForeground(new Color(30, 30, 47));
        venueLabel.setForeground(new Color(30, 30, 47));
        startHackLabel.setForeground(new Color(30, 30, 47));
        endHackLabel.setForeground(new Color(30, 30, 47));
        maxParLabel.setForeground(new Color(30, 30, 47));
        maxParTeamLabel.setForeground(new Color(30, 30, 47));
        organizerLabel.setForeground(new Color(30, 30, 47));
        creaHackathonButton.setForeground(new Color(37, 99, 235));

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/iconaUser.png"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(resizedIcon);

        welcomeArea.setText("Benvenuto nell'area di gestione!\n" +
                "Clicca 'Crea Hackathon' per iniziare.");
        adminArea.append(controller.getPlAdmin().getUsername());

        ImageIcon imageIcon2 = new ImageIcon(getClass().getResource("/prova.png"));
        Image scaledImage2 = imageIcon2.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(scaledImage2);
        imageLabel2.setIcon(resizedIcon2);

        comboBox.addItem("-");
        for (int i = 2; i < 6; i++) {
            comboBox.addItem(i);
        }




        /*// Pannello lista
        listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(0, 2)); // Disposizione a 2 colonne: elemento + pulsante


        //lista momentanea per coontrollare il funzoinamento della dinamicità della pagina
        ArrayList<String> lista = new ArrayList<>();
        lista.add("MAMMA");
        lista.add("BABBO");
        lista.add("PIPPO");
        lista.add("PLUTO");
        lista.add("PAPERINO");
        ArrayList<JButton> bottoni = new ArrayList<>();


        // per ogni elemento della lista viene istanziata una label, a cui viene affiancata un pulsante
        for (String item : lista) {
            JLabel label = new JLabel(item);
            bottoni.add(new JButton("Aziona" + item));
            JButton button = bottoni.getLast();
            listPanel.add(label);
            listPanel.add(button);
        }

        // Aggiungi il pannello lista a sinistra il pannello operazioni
        panel.add(listPanel, BorderLayout.WEST);

        // Aggiorna la UI
        panel.revalidate();
        panel.repaint();


        // agiunge l'azione da compiere per ogni bottone
        for(JButton bottone : bottoni){
            bottone.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(listPanel, "Forza Napoli");
                }
            });
        }
*/
        /*
         * questo tipo di gui non è destinata solo ad admin gui, che in realtà non necessità neanche id questa disposizione
         * degli elementi, ma è stata inserita qui al fine di trovare il modo di implementarla
         */
        /*createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreaHackathon crea = new CreaHackathon(frame, controller);
                frame.setVisible(false);
                crea.getFrame().setVisible(true);
            }
        });*/
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createHackathonPanel.setVisible(true);
                createHackathonPanel.setBorder(new LineBorder(new Color(30, 30, 47)));
            }
        });

        goToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getHome().getFrame().setVisible(true);
                frame.dispose();
            }
        });


        //PROVA CREA HACKATHON IN QUESTA GUI
        SpinnerDateModel model = new SpinnerDateModel(new Date(), new Date(), null, Calendar.DAY_OF_MONTH);
        startSpinner.setModel(model);

        // Formattazione della visualizzazione della data
        JSpinner.DateEditor editor = new JSpinner.DateEditor(startSpinner, "dd/MM/yyyy");
        startSpinner.setEditor(editor);


        SpinnerDateModel model2 = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        endSpinner.setModel(model2);

        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(endSpinner, "dd/MM/yyyy");
        endSpinner.setEditor(editor2);

        startSpinner.addChangeListener(e -> {
            Date startDate = (Date) startSpinner.getValue();
            Date endDate = (Date) endSpinner.getValue();
            if (endDate.before(startDate))
                endSpinner.setValue(startDate);
        });

        // Creazione modello per la data di inizio
        SpinnerDateModel startModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        startSpinner.setModel(startModel);
        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(startSpinner, "dd/MM/yyyy");
        startSpinner.setEditor(startEditor);

        // Creazione modello per la data di fine
        SpinnerDateModel endModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        endSpinner.setModel(endModel);
        JSpinner.DateEditor endEditor = new JSpinner.DateEditor(endSpinner, "dd/MM/yyyy");
        endSpinner.setEditor(endEditor);

        startSpinner.addChangeListener(e -> {
            Date startDate = (Date) startSpinner.getValue();
            Date endDate = (Date) endSpinner.getValue();

            // Se la data finale è minore della data di inizio, aggiorniamo endSpinner
            if (endDate.before(startDate)) {
                endSpinner.setValue(startDate);
            }
            if (startDate.before(new Date())) {
                startSpinner.setValue(new Date());
            }
            organizerComboBox.setSelectedIndex(0);
            endModel.setStart(startDate);
        });

        endSpinner.addChangeListener(e -> {
            Date startDate = (Date) endSpinner.getValue();
            if (startDate.before(new Date())) {
                endSpinner.setValue(new Date());
            }
            organizerComboBox.setSelectedIndex(0);
        });

        Date date = (Date) startSpinner.getValue();
        LocalDate startLDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        date = (Date) endSpinner.getValue();
        LocalDate endLDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        ArrayList<String> organizers = new ArrayList<>();
        try {
            controller.getFreeUser(organizers, startLDate, endLDate);
            organizerComboBox.removeAllItems();
            organizerComboBox.addItem("-");
            for (String organizer : organizers) {
                organizerComboBox.addItem(organizer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(panel, "ERRORE DURANTE LA RICERCA DEGLI ORGANIZZATORI");
        }


        creaHackathonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = (Date) startSpinner.getValue();
                LocalDate startLDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                date = (Date) endSpinner.getValue();
                LocalDate endLDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (titleArea.getText().isEmpty() || venueArea.getText().isEmpty() || maxParArea.getText().isEmpty() || comboBox.getSelectedItem().equals("-")) {
                    JOptionPane.showMessageDialog(panel, "Inserire i valori in tutti i campi", "ERRORE", JOptionPane.ERROR_MESSAGE);
                } else {
                    int n = 0;
                    try {
                        n = Integer.parseInt(maxParArea.getText());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, "Inserire il numero di partecipanti in cifre", "ERRORE", JOptionPane.ERROR_MESSAGE);
                    }
                    try {
                        int code = controller.handleCreateHackathon(titleArea.getText(), venueArea.getText(), startLDate, endLDate, n, ((int) comboBox.getSelectedItem()), organizerComboBox.getSelectedItem().toString(), file);
                        switch (code) {
                            case -4:
                                JOptionPane.showMessageDialog(panel, "L'Hackathon deve durare almeno un giorno");
                                break;
                            case -3:
                                JOptionPane.showMessageDialog(panel, "L'Hackathon deve distare almeno una settimana dal giorno corrente");
                                startSpinner.setValue(Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                                endSpinner.setValue(Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                                break;
                            case -2:
                                JOptionPane.showMessageDialog(panel, "Il titolo deve avere massimo 50 caratteri\nLa sede deve avere massimo 25 caratteri");
                                titleArea.setText("");
                                venueArea.setText("");
                                break;
                            case 0:
                                JOptionPane.showMessageDialog(panel, "Creazione del nuovo evento avvenuta con successo");
                                createHackathonPanel.setVisible(false);
                                break;
                            case -1:
                            default:
                                JOptionPane.showMessageDialog(panel, "Errore durante la creazione del nuovo hackathon");
                                titleArea.setText("");
                                maxParArea.setText("");
                                venueArea.setText("");
                                startSpinner.setEditor(startEditor);
                                endSpinner.setEditor(endEditor);
                                comboBox.setSelectedIndex(0);
                                organizerComboBox.setSelectedIndex(0);
                                break;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(panel, "Qualcosa è andato storto durante la creazione dell'evento");
                        titleArea.setText("");
                        maxParArea.setText("");
                        venueArea.setText("");
                        startSpinner.setEditor(startEditor);
                        endSpinner.setEditor(endEditor);
                        comboBox.setSelectedIndex(0);
                        organizerComboBox.setSelectedIndex(0);
                    }
                }
            }
        });


        organizerComboBox.addItem("-");
        organizerComboBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                Date date = (Date) startSpinner.getValue();
                LocalDate startLDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                date = (Date) endSpinner.getValue();
                LocalDate endLDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                ArrayList<String> organizers = new ArrayList<>();
                try {
                    controller.getFreeUser(organizers, startLDate, endLDate);
                    organizerComboBox.removeAllItems();
                    organizerComboBox.addItem("-");
                    for (String organizer : organizers) {
                        organizerComboBox.addItem(organizer);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "ERRORE DURANTE LA RICERCA DEGLI ORGANIZZATORI");
                }
            }
        });

        caricaFotoButton.addActionListener(new ActionListener() {
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
                    System.out.println(file);
                    pathLabel.setText(file.toString());

                }
            }
        });
        pathLabel.setBorder(new LineBorder(Color.black));
        //PROVA GRAFICO
    }


    //FINE PROVA

    /**
     * Restituisce il frame principale della gui.
     *
     * @return JFrame: Il frame principale.
     */
    public JFrame getFrame() {
        return frame;
    }

}
