package gui;

import controller.Controller;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * La classe che contiene tutti gli elementi che permettono ad un admin di svolgere le operazioni di creazione di hackathon
 */
public class AdminGui {
    private JPanel panel;
    private JPanel listPanel;
    private JButton  createButton;
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
    private JComboBox<String> comboBox;
    private JComboBox<String> organizerComboBox;
    private JPanel buttonPanel;
    private JButton creaHackathonButton;
    private JPanel createPanel;
    private JPanel fieldPanel;
    private JTextArea welcomeArea;
    private JLabel immaginePanel;
    private JButton caricaFotoButton;
    private JTextArea pathLabel;
    private JLabel friends;
    private JPanel topPanel;
    private JLabel iconLabel;
    private JFrame frame;
    private File file = null;
    private JSpinner.DateEditor startEditor;
    private JSpinner.DateEditor endEditor;

    /**
     * Instantiates a new Admin gui.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     */
    public AdminGui(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("HackManager");
        frame.setContentPane(panel);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setSize(800, 800);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        organizerComboBox.addItem("-");
        
        String panelBg = "Panel.background";
        Color bg = new Color(236, 240, 241);
        Color text = new Color(30, 30, 47);
        Color accent = new Color(37, 99, 235);

        panel.setBackground(text);
        createHackathonPanel.setBackground(bg);
        createPanel.setBackground(bg);
        labelPanel.setBackground(bg);
        buttonPanel.setBackground(bg);
        fieldPanel.setBackground(bg);

        profileLabel.setForeground(bg);
        adminArea.setForeground(bg);
        welcomeArea.setForeground(bg);

        welcomeArea.setBackground(text);
        adminArea.setBackground(text);

        createButton.setForeground(accent);
        creaHackathonButton.setForeground(accent);
        goToButton.setForeground(accent);

        titleLabel.setForeground(bg);
        venueLabel.setForeground(text);
        startHackLabel.setForeground(text);
        endHackLabel.setForeground(text);
        maxParLabel.setForeground(text);
        maxParTeamLabel.setForeground(text);
        organizerLabel.setForeground(text);
        createPanel.setVisible(false);
        pathLabel.setForeground(text);
        pathLabel.setLineWrap(true);
        pathLabel.setWrapStyleWord(true);
        pathLabel.setEditable(false); // se vuoi solo visualizzare
        pathLabel.setPreferredSize(new Dimension(380, 20));

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/iconaUser.png"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(resizedIcon);

        welcomeArea.setText("Benvenuto nell'area di gestione!\nClicca 'Crea Hackathon' per iniziare.");
        adminArea.append(controller.getPlAdmin().getUsername());

        ImageIcon imageIcon2 = new ImageIcon(getClass().getResource("/prova.png"));
        Image scaledImage2 = imageIcon2.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(scaledImage2);
        imageLabel2.setIcon(resizedIcon2);

        ImageIcon imageIcon3 = new ImageIcon(getClass().getResource("/friends.png"));
        Image scaledImage3 = imageIcon3.getImage().getScaledInstance(1150,350, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon3 = new ImageIcon(scaledImage3);
        friends.setIcon(resizedIcon3);

        this.configuraDatePickers();
        this.updateOrganizers(controller);

        comboBox.addItem("-");
        for (int i = 2; i < 6; i++) {
            comboBox.addItem(String.valueOf(i));
        }

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPanel.setVisible(true);
                createPanel.setBorder(new LineBorder(new Color(30, 30, 47)));
            }
        });

        goToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getHome().fillHacks(controller.getHome().getFont());
                controller.getHome().getFrame().setVisible(true);
                frame.dispose();
            }
        });

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
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(panel, "Inserire il numero di partecipanti in cifre", "ERRORE", JOptionPane.ERROR_MESSAGE);
                    }
                    try {
                        int code = controller.handleCreateHackathon(titleArea.getText(), venueArea.getText(), startLDate, endLDate, n, Integer.parseInt(comboBox.getItemAt(comboBox.getSelectedIndex())), organizerComboBox.getSelectedItem().toString(), file);
                        switch (code) {
                            case -4:
                                JOptionPane.showMessageDialog(null, "L'Hackathon deve durare almeno un giorno");
                                break;
                            case -3:
                                JOptionPane.showMessageDialog(null, "L'Hackathon deve distare almeno una settimana dal giorno corrente");
                                startSpinner.setValue(Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                                endSpinner.setValue(Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                                break;
                            case -2:
                                JOptionPane.showMessageDialog(null, "Il titolo deve avere massimo 50 caratteri\nLa sede deve avere massimo 25 caratteri");
                                titleArea.setText("");
                                venueArea.setText("");
                                break;
                            case 0:
                                JOptionPane.showMessageDialog(null, "Creazione del nuovo evento avvenuta con successo");
                                createPanel.setVisible(false);
                                titleArea.setText("");
                                maxParArea.setText("");
                                venueArea.setText("");
                                startSpinner.setEditor(startEditor);
                                endSpinner.setEditor(endEditor);
                                comboBox.setSelectedIndex(0);
                                organizerComboBox.setSelectedIndex(0);
                                file = null;
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Errore durante la creazione del nuovo hackathon");
                                titleArea.setText("");
                                maxParArea.setText("");
                                venueArea.setText("");
                                startSpinner.setEditor(startEditor);
                                endSpinner.setEditor(endEditor);
                                comboBox.setSelectedIndex(0);
                                organizerComboBox.setSelectedIndex(0);
                                pathLabel.setText("");
                                pathLabel.setBorder(null);
                                break;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Qualcosa è andato storto durante la creazione dell'evento");
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


        organizerComboBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                updateOrganizers(controller);
            }
        });

        caricaFotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               caricaFoto(panelBg);
            }
        });
    }


    /**
     * Restituisce il frame che viene creato quando viene istanziata la pagina AdminGui
     *
     * @return il frame di AdminGui
     */
    public JFrame getFrame() {
        return frame;
    }

    private void configuraDatePickers() {
        SpinnerDateModel model = new SpinnerDateModel(Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()), null, Calendar.DAY_OF_MONTH);
        startSpinner.setModel(model);
        String dateFormat = "dd/MM/yyyy";
        // Formattazione della visualizzazione della data
        JSpinner.DateEditor editor = new JSpinner.DateEditor(startSpinner, dateFormat);
        startSpinner.setEditor(editor);


        SpinnerDateModel model2 = new SpinnerDateModel(Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()), null, Calendar.DAY_OF_MONTH);
        endSpinner.setModel(model2);

        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(endSpinner, dateFormat);
        endSpinner.setEditor(editor2);


        // Creazione modello per la data di inizio
        SpinnerDateModel startModel = new SpinnerDateModel(Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()), null, Calendar.DAY_OF_MONTH);
        startSpinner.setModel(startModel);
        this.startEditor = new JSpinner.DateEditor(startSpinner, dateFormat);
        startSpinner.setEditor(startEditor);

        // Creazione modello per la data di fine
        SpinnerDateModel endModel = new SpinnerDateModel(Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()), null, Calendar.DAY_OF_MONTH);
        endSpinner.setModel(endModel);
        this.endEditor = new JSpinner.DateEditor(endSpinner, dateFormat);
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

        startSpinner.addChangeListener(e -> {
            Date startDate = (Date) startSpinner.getValue();
            Date endDate = (Date) endSpinner.getValue();
            if (endDate.before(startDate))
                endSpinner.setValue(startDate);
        });
    }

    private void updateOrganizers(Controller controller){
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

    private void caricaFoto(String panelBg){
        UIManager.put("FileChooser.background", new Color(238, 238, 238));
        UIManager.put("FileChooser.foreground", new Color(0, 0, 0));
        UIManager.put("FileChooser.listViewBackground", new Color(255, 255, 255));
        UIManager.put("FileChooser.listViewForeground", new Color(0, 0, 0));
        UIManager.put("FileChooser.buttonBackground", new Color(240, 240, 240));
        UIManager.put(panelBg, new Color(240, 240, 240));
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtroImmagini = new FileNameExtensionFilter(
                "Immagini (*.jpg, *.png, *.jpeg)", "jpg", "png", "jpeg"
        );
        fileChooser.setFileFilter(filtroImmagini);
        int scelta = fileChooser.showOpenDialog(frame);
        if (scelta == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            if(file.getName().toLowerCase().endsWith(".jpg") || file.getName().toLowerCase().endsWith(".png") || file.getName().toLowerCase().endsWith(".jpeg")) {
                pathLabel.setText(file.toString());
                pathLabel.setBorder(new LineBorder(Color.black));
            }else{
                UIManager.put(panelBg, new Color(30, 30, 47));
                JOptionPane.showMessageDialog(panel,"Formato non supportato. Seleziona un file JPG, PNG o GIF.",
                            "Errore formato",
                            JOptionPane.ERROR_MESSAGE);
                file = null; // Annulla la selezione
                pathLabel.setText("");
                pathLabel.setBorder(null);
            }
        }
        UIManager.put(panelBg, new Color(30, 30, 47));
    }
}

