package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminGui {
    private JPanel panel;
    private JPanel listPanel;
    private JTextPane welcomeText;
    private JButton createButton;
    private JTextPane infoText;
    private JButton goToButton;
    private JPanel operationPanel;
    private JFrame frame;

    public JFrame getFrame() {
        return frame;
    }

    public AdminGui(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("SignIn");
        panel = new JPanel(new BorderLayout()); // Usa BorderLayout per separare le sezioni
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        // Pannello operazioni
        operationPanel = new JPanel();
        operationPanel.setLayout(new GridLayout(0, 1)); // Disposizione verticale

        welcomeText = new JTextPane();
        createButton = new JButton("Crea un nuovo Hackathon");
        infoText = new JTextPane();
        goToButton = new JButton("Home");

        operationPanel.add(welcomeText);
        operationPanel.add(createButton);
        operationPanel.add(infoText);
        operationPanel.add(goToButton);

        // Aggiungi il pannello operazioni nel pannello generale a destra
        panel.add(operationPanel, BorderLayout.EAST);

        // Pannello lista
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

        /*
        questo tipo di gui non è destinata solo ad admin gui, che in realtà non necessità neanche id questa disposizione
        degli elementi, ma è stata inserita qui al fine di trovare il modo di implementarla
        */
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreaHackathon crea = new CreaHackathon(frame);
                frame.setVisible(false);
                crea.getFrame().setVisible(true);
            }
        });
    }

}
