package gui;

import javax.swing.*;


public class HackathonGui {
    private JPanel panel;
    private JTextArea titleArea;
    private JTextArea organizerArea;
    private JPanel headerPanel;
    private JList judjeList;
    private JTextArea infoArea;
    private JPanel infoPanel;
    private JTextArea descProbArea;
    private JButton accessButton;
    private JButton signInHackButton;
    private JPanel imagePanel;
    public JFrame frame;

    public HackathonGui(JFrame frameChiamante){
        frame = new JFrame("hack");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);



    }

}

