package gui;

import javax.swing.*;

public class AreaPersonale {
    private JPanel panel;
    private JPanel modifyPanel;
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
    private JComboBox comboBox1;
    private JSplitPane pulsanti;
    private JButton accettaButton;
    private JButton rifiutaButton;
    private JPanel infoPanel;
    private JList list1;
    public JFrame frame;

    public AreaPersonale(JFrame frameChiamante){
        frame = new JFrame("SignIn");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 800);

    }
}
