package gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.awt.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class CreaHackathon {
    private JPanel dataPanel;
    private JLabel titleLabel;
    private JTextField titleArea;
    private JLabel startLabel;
    private JLabel endLabel;
    private JLabel venueLable;
    private JTextField venueArea;
    private JPanel createPanel;
    private JButton createButton;
    private JPanel panel;
    public JSpinner startSpinner;
    private JSpinner endSpinner;
    private JComboBox comboBox1;
    private JLabel maxTeamPartLabel;
    private JLabel maxParticipantLabel;
    private JTextField textField1;
    private JPanel prova;
    public JFrame frame;

    public CreaHackathon(JFrame frameChiamante) {
        frame = new JFrame("Crea Hackathon");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setMaximumSize(new Dimension(500, 500));
        panel.setLayout(new GridBagLayout());



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

        titleArea.setPreferredSize(new Dimension(150, 25));
        venueArea.setPreferredSize(new Dimension(150, 25));


        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 2;
        panel.add(createButton, gbc);

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

            // Impostiamo il limite minimo senza forzare il valore
            endModel.setStart(startDate);
        });

        endSpinner.addChangeListener(e->{
            Date startDate = (Date) endSpinner.getValue();
            if (startDate.before(new Date())){
                endSpinner.setValue(new Date());
            }
        });
    }
}
