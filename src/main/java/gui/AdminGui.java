package gui;

// TODO SISTEMARE LA GUI, RIMUOVERE LE PARTI IN ECCESSO

import controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

/**
 *  Classe che gestisce tutte le azioni che un Admin della piattaforma è in grado di eseguire.
 */
public class AdminGui {
    private JPanel panel;
    private JPanel listPanel;
    private JTextPane welcomeText;
    private JButton createButton;
    private JTextPane infoText;
    private JButton goToButton;
    private JPanel operationPanel;
    private JLabel iconLabel;
    private JFrame frame;


    /**
     * Instanzia una nuova AdminGui.
     * <p>
     * La classe AdminGui permette ad un Admin di creare un Hackathon e di tornare alla home.
     * <br>La creazione di un Hackathon richiede ad un Admin di inserire i dati della gara, e l'organizzatore che la gestirà.
     * </p>
     *
     * @param frameChiamante Il frame che istanzia la nuova AdminGui.
     * @param controller     Il controller istanziato dalla classe Home.java
     */
    public AdminGui(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("SignIn");
        panel = new JPanel(new BorderLayout()); // Usa BorderLayout per separare le sezioni
        frame.setContentPane(panel);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        panel.setBackground(new Color(5, 0, 54));


        // Pannello operazioni
        operationPanel = new JPanel();
        operationPanel.setLayout(new GridLayout(0, 1)); // Disposizione verticale

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/AdminGuiIcon.jpg"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(315, 640, Image.SCALE_SMOOTH);

        // CONVERTO DI NUOVO IN IMAGEICON PER ASSEGNARLA ALLA LABLE
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        iconLabel.setIcon(resizedIcon);
        panel.add(iconLabel);

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

        /*// Pannello lista
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
*/
        /*
        * questo tipo di gui non è destinata solo ad admin gui, che in realtà non necessità neanche id questa disposizione
        * degli elementi, ma è stata inserita qui al fine di trovare il modo di implementarla
        */
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreaHackathon crea = new CreaHackathon(frame);
                frame.setVisible(false);
                crea.getFrame().setVisible(true);
            }
        });
        goToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getHome().getFrame().setVisible(true);
                frame.dispose();
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
