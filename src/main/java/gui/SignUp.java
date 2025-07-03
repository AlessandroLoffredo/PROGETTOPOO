package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Classe che permette di registrarsi.
 */
public class SignUp {
    private JPanel panel;
    private JPanel namePanel;
    private JPanel agePanel;
    private JPanel buttonPanel;
    private JPanel dataPanel;
    private JTextField fNameArea;
    private JTextField lNameArea;
    private JTextField usernameArea;
    private JPasswordField passwordArea;
    private JRadioButton ageRadioButton;
    private JButton signUpButton;
    private JLabel fNameLabel;
    private JLabel lNameLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel titleLabel;
    private JTextField formatoPassLabel;
    private JFrame frame;


    /**
     * Instanzia una nuova SignUp.
     * <p>
     * La classe SignUp permette ai visitatori di registrarsi come utenti, inserendo tutte le informazioni necessarie.
     * </p>
     * @param frameChiamante Il frame che istanzia la nuova AreaPersonaleOrganizzatore
     * @param controller     Il controller istanziato dalla classe Home.java
     */
    public SignUp(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("SignUp");
        frame.setContentPane(panel);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                frameChiamante.setEnabled(true);
                frame.dispose();
            }
        });
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setMaximumSize(new Dimension(500, 500));
        usernameArea.setPreferredSize(new Dimension(150, 25));
        passwordArea.setPreferredSize(new Dimension(150, 25));
        fNameArea.setPreferredSize(new Dimension(150, 25));
        lNameArea.setPreferredSize(new Dimension(150, 25));
        frame.setLocationRelativeTo(null);


        panel.setBackground(new Color(30, 30, 47));
        namePanel.setBackground(new Color(30, 30, 47));
        buttonPanel.setBackground(new Color(30, 30, 47));

        fNameLabel.setForeground(new Color(236, 240, 241));
        lNameLabel.setForeground(new Color(236, 240, 241));
        usernameLabel.setForeground(new Color(236, 240, 241));
        passwordLabel.setForeground(new Color(236, 240, 241));
        signUpButton.setForeground(new Color(37, 99, 235));
        ageRadioButton.setForeground(new Color(37, 99, 235));
        titleLabel.setForeground(new Color(236, 240, 241));


        signUpButton.addActionListener(new ActionListener() { //SE FACESSIMO VERIFICA TUTTI I CAMPI VUOTI E PALLINO ETà SELEZIONATO E POI NELLO SWITCH CASE IL RESTO?
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameArea.getText().isEmpty() || passwordArea.getPassword().toString().isEmpty() || fNameArea.getText().isEmpty() || lNameArea.getText().isEmpty()){
                    JOptionPane.showMessageDialog(panel, "Riempi tutti i campi");
                } else if(!ageRadioButton.isSelected()) {
                    JOptionPane.showMessageDialog(panel, "Devi confermare di avere più di 16 anni");
                } else {
                    try {
                        int code = controller.handleSignUp(usernameArea.getText(), new String(passwordArea.getPassword()), fNameArea.getText(), lNameArea.getText());
                        switch (code) {
                            case -1:
                                JOptionPane.showMessageDialog(panel, "Il nome ed il cognome non devono superare i 20 caratteri");
                                break;
                            case -2:
                                JOptionPane.showMessageDialog(panel, "L'username deve avere tra i 3 ed i 16 caratteri\nLa password deve avere tra gli 8 ed i 16 caratteri");
                                break;
                            /*case -3:
                                JOptionPane.showMessageDialog(panel, "Riempi tutti i campi");
                                break;*/
                            case -4:
                                JOptionPane.showMessageDialog(panel, "La password non rispecchia il formato\n1 lettera maiuscola, 1 lettera minuscola, 1 numero, 1 carattere speciale");
                                break;
                            case 1:
                                JOptionPane.showMessageDialog(panel, "Registrazione completata!");
                                frameChiamante.setVisible(true);
                                frame.dispose();
                        }
                    } catch (IllegalArgumentException ex) {  //Creiamo classe Exception nuova?
                        System.out.println("Qualcosa è andato storto durante la registrazione");
                    }
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
