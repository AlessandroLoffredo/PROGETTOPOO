package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Classe che contiene tutte le informazioni realative ad un Hackahon e tutte le operazioni che possono essere svolte su esso.
 */
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
    private JButton subscribeHackButton;
    private JPanel imagePanel;
    private JButton homeButton;
    private JFrame frame;



    /**
     * Instanzia una nuova HackathonGui.
     * <p>
     * La classe HackathonGui permette ai vari utenti visitatori di vedere le informazioni generali su un determinato Hackathon.
     * <br> Se il visitatore è un utente potrà iscriversi all'Hackathon qualora non sia già iscritto ad un altro.
     * <br> Se il visitatore non è ancora registrato come utente potrà registrarsi.
     * </p>
     * @param frameChiamante il frame che istanzia la nuova AreaPersonaleOrganizzatore
     * @param controller     Il controller istanziato dalla classe Home.java
     */
    public HackathonGui(JFrame frameChiamante, Controller controller){
        frame = new JFrame("hack");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        accessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //UTILIZZO MOMENTANEO DI QUESTO ACTION LISTENER
                //BISOGNA TENERE CONTO DI CHI CLICCA IL PULSANTE EPR APRIRE UN AREA PERSONALE SPECIFICA
                //AreaPersonale areaPersonale = new AreaPersonale(frame, contoller);
                //frame.setVisible(false);
                //areaPersonale.getFrame().setVisible(true);
            }
        });

        /*subscribeHackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiornamento nel DB, in cui l'utente diventa Partecipante se Loggato
            }
        });*/

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getHome().setVisible(true);
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

