package gui;

import controller.Controller;

import javax.swing.*;

public class AreaPersonaleGiudice {
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
    private JPanel judjePanel;
    private JTextArea commentArea;
    private JButton loadButton;
    private JList docList;
    private JPanel examinePanel;
    private JPanel selectPanel;
    private JComboBox teamComboBox;
    private JButton selectButton;
    private JPanel listPanel;
    private JPanel commentPanel;
    private JPanel markPanel;
    private JComboBox teamComboBoxMark;
    private JButton assigneButton;
    private JSlider markSlider;
    private JPanel problemPanel;
    private JTextArea problemArea;
    private JButton sendProbButton;
    private JFrame frame;

    public AreaPersonaleGiudice(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Area Personale");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
    }

    public JFrame getFrame() {
        return frame;
    }
}
