package gui;

import controller.Controller;

import javax.swing.*;
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
    private JPanel organizerPanel;
    private JButton inviaRichiestaButton;
    private JComboBox comboBox1;
    private JTextArea infoHackathon;
    private JPanel hackathonPanel;
    private JButton startSingUpButton;
    private JSpinner spinner1;
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


        spinner1.addChangeListener(e -> {
            Date data = (Date) spinner1.getValue();
            if (data.before(new Date())) {
                spinner1.setValue(new Date());
            }
        });
    }
    public JFrame getFrame() {
        return frame;
    }
}
