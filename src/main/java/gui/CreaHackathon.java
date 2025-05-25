package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    public CreaHackathon(JFrame frameChiamante) {
        frame = new JFrame("CreaHackathon");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                if(titleArea.getText().isEmpty() || venueArea.getText().isEmpty() || maxParArea.getText().isEmpty()|| comboBox.getSelectedItem().equals("-")){
                    JOptionPane.showMessageDialog(panel, "Inserire i valori in tutti i campi", "ERRORE", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    try {
                        int n = Integer.parseInt(maxParArea.getText());
                    }
                    catch (Exception ex){
                        JOptionPane.showMessageDialog(panel, "Inserire il numero di partecipanti in cifre", "ERRORE", JOptionPane.ERROR_MESSAGE);
                    }
                    //controller.handleCreateHackathon(titleArea.getText(), venueArea.getText(),startDate con cast , endDate con cast, n, comboBox.getSelectedItem(), organizerComboBox.getSelectedItem());
                    frameChiamante.setVisible(true);
                    frame.dispose();
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
