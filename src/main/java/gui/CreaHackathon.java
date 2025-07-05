package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe che permette la creazione di un nuovo Hackathon.
 */
public class CreaHackathon {
    private JPanel panel;
    private JButton creaHackathonButton;
    private JTextField titleArea;
    private JTextField venueArea;
    private JSpinner startSpinner;
    private JSpinner endSpinner;
    private JTextField maxParArea;
    private JComboBox comboBox;
    private JPanel buttonPanel;
    private JPanel dataPanel;
    private JPanel labelPanel;
    private JLabel titleLabel;
    private JLabel venueLabel;
    private JLabel startHackLabel;
    private JLabel endHackLabel;
    private JLabel maxParLabel;
    private JLabel maxParTeamLabel;
    private JComboBox organizerComboBox;
    private JLabel organizerLabel;
    private JFrame frame;

    /**
     * Instanzia una nuova CreaHackathon.
     * <p>
     * La classe CreaHackathon permette agli admin di inserire le informazioni necessarie alla creazione dell'Hackathon.
     * </p>
     * @param frameChiamante il frame che istanzia la nuova AreaPersonaleOrganizzatore
     */
    public CreaHackathon(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("CreaHackathon");
        frame.setContentPane(panel);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
        frame.pack();
        frame.setMinimumSize(new Dimension(500,500));
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


        panel.setBackground(new Color(10, 10, 30));
        labelPanel.setBackground(new Color(15, 15, 50));
        dataPanel.setBackground(new Color(15, 15, 50));
        buttonPanel.setBackground(new Color(15, 15, 50));

        titleLabel.setForeground(new Color(0, 255, 0));
        venueLabel.setForeground(new Color(0, 255, 0));
        startHackLabel.setForeground(new Color(0, 255, 0));
        endHackLabel.setForeground(new Color(0, 255, 0));
        maxParLabel.setForeground(new Color(0, 255, 0));
        maxParTeamLabel.setForeground(new Color(0, 255, 0));
        organizerLabel.setForeground(new Color(0, 255, 0));
        creaHackathonButton.setForeground(new Color(255, 0, 150));


        comboBox.addItem("-");
        for(int i=2; i<6; i++){
            comboBox.addItem(i);
        }

        ArrayList<String> organizers = new ArrayList<>();
        try{
            controller.getOrganizers(organizers);
            for(String organizer : organizers){
                organizerComboBox.addItem(organizer);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(panel, "ERRORE DURANTE LA RICERCA DEGLI ORGANIZZATORI");
        }
        //POPOLARE L'ORGANIZERCOMBOBOX CON SOLI UTENTI LIBERI...
        //VALUTARE NUOVAMENTE QUESTIONE ISBUSY

        SpinnerDateModel model = new SpinnerDateModel(new Date(), new Date(), null , Calendar.DAY_OF_MONTH);
        startSpinner.setModel(model);

        // Formattazione della visualizzazione della data
        JSpinner.DateEditor editor = new JSpinner.DateEditor(startSpinner, "dd/MM/yyyy");
        startSpinner.setEditor(editor);


        SpinnerDateModel model2 = new SpinnerDateModel(new Date(), null, null , Calendar.DAY_OF_MONTH);
        endSpinner.setModel(model2);

        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(endSpinner, "dd/MM/yyyy");
        endSpinner.setEditor(editor2);

        startSpinner.addChangeListener(e->{
            Date startDate = (Date) startSpinner.getValue();
            Date endDate = (Date) endSpinner.getValue();
            if(endDate.before(startDate))
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
            if (startDate.before(new Date())){
                startSpinner.setValue(new Date());
            }

            endModel.setStart(startDate);
        });

        endSpinner.addChangeListener(e->{
            Date startDate = (Date) endSpinner.getValue();
            if (startDate.before(new Date())){
                endSpinner.setValue(new Date());
            }
        });

        creaHackathonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = (Date) startSpinner.getValue();
                LocalDate startLDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                date = (Date) endSpinner.getValue();
                LocalDate endLDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if(titleArea.getText().isEmpty() || venueArea.getText().isEmpty() || maxParArea.getText().isEmpty()|| comboBox.getSelectedItem().equals("-")){
                    JOptionPane.showMessageDialog(panel, "Inserire i valori in tutti i campi", "ERRORE", JOptionPane.ERROR_MESSAGE);
                }else {
                    int n = 0;
                    try {
                        n = Integer.parseInt(maxParArea.getText());
                    }
                    catch (Exception ex){
                        JOptionPane.showMessageDialog(panel, "Inserire il numero di partecipanti in cifre", "ERRORE", JOptionPane.ERROR_MESSAGE);
                    }
                    try{
                        int code = controller.handleCreateHackathon(titleArea.getText(), venueArea.getText(), startLDate, endLDate, n, ((int) comboBox.getSelectedItem()), organizerComboBox.getSelectedItem().toString());
                        switch (code) {
                            case -2:
                                JOptionPane.showMessageDialog(panel, "Il titolo deve avere massimo 50 caratteri\nLa sede deve avere massimo 25 caratteri");
                                titleArea.setText("");
                                venueArea.setText("");
                                break;
                            case -3:
                                JOptionPane.showMessageDialog(panel, "L'Hackathon deve distare almeno una settimana dal giorno corrente");
                                startSpinner.setValue(Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                                endSpinner.setValue(Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                                break;
                            case 0:
                                JOptionPane.showMessageDialog(panel, "Creazione del nuovo evento avvenuta con successo");
                                frameChiamante.setVisible(true);
                                frame.dispose();
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
