package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class CreaHackathon {
    private JPanel panel;
    private JButton creaHackathonButton;
    private JTextField textField1;
    private JTextField textField2;
    private JSpinner startSpinner;
    private JSpinner endSpinner;
    private JTextField textField3;
    private JComboBox comboBox1;
    private JPanel buttonPanel;
    private JPanel dataPanel;
    private JPanel labelPanel;
    private JFrame frame;

    public CreaHackathon(JFrame frameChiamante) {
        frame = new JFrame("CreaHackathontry");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(500,500));
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

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


    public JFrame getFrame() {
        return frame;
    }

}
