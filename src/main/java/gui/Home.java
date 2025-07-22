package gui;

import controller.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;


/**
 * Classe principale che mostra la lista di tutti gli Hackathon e permette di svolgere varie azioni per accedere e registrarsi
 */
public class Home {
    private JPanel panel;
    private JButton loginButton;
    private JPanel loginPanel;
    private JLabel titleLabel;
    private JPanel hackListPanel;
    private JButton areaPersonaleButton;
    private JTextArea textArea1;
    private JPanel imagePanel;
    private JLabel imageLabel;
    private JScrollPane scrollPane;
    private JPanel lastHackathonPanel;
    private JPanel scrollPanel;
    private JPanel hackathonPanel;
    private JLabel hackLabel;
    private JLabel currentStartLabel;
    private JLabel currentStartArea;
    private JLabel currentEndLabel;
    private JLabel currentEndArea;
    private JLabel currentStartRegLabel;
    private JLabel currentStartRegArea;
    private JLabel currentMaxTeamParLabel;
    private JLabel currentMaxTeamParArea;
    private JLabel currentCounterLabel;
    private JLabel currentMaxRegLabel;
    private JLabel currentCounterArea;
    private JLabel currentMaxRegArea;
    private JLabel currentVenueLabel;
    private JLabel currentVenueArea;
    private JLabel currentTitleLabel;
    private JLabel currentTitleArea;
    private JLabel currentProbDescLabel;
    private JScrollPane currentProbDescScroll;
    private JTextArea currentProbDescArea;
    private JLabel rankingLabel;
    private JList rankingList;
    private static JFrame frame;
    private Controller controller;
    private ArrayList<ArrayList<Object>> data = new ArrayList<>();
    private int callDelete = 0;
    /**
     * Punto in cui si entra effettivamente nel programma.
     *
     * @param args gli argomenti in input
     * @throws MalformedURLException Eccezione per gestire URL scritti male
     */
    public static void main(String[] args) {
        frame = new JFrame("Hackathon");
        frame.setContentPane(new Home().panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Dimension size = frame.getSize();
        frame.setMinimumSize(new Dimension(size.width, 400));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Instanzia una nuova Home.
     * <p>
     * La classe Home permette ai visitatori di vedere la lista di tutti gli Hackaton, poter entrare all'interno della pagina specifica di ogni Hackathon,
     * effettuare il login o il signup.
     * </p>
     */
    public Home(){

        controller = new Controller(this);
        if (this.callDelete == 0) {
            controller.getHackList(data);
            this.callDelete++;
        }
        //CREO IMAGEICON, LA CONVERTO IN IMAGE PER RIDIMENSIONARE
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Hackerlogo.jpg"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        // CONVERTO DI NUOVO IN IMAGEICON PER ASSEGNARLA ALLA LABLE
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(resizedIcon);
        panel.setBackground(new Color(30, 30, 47));
        hackListPanel.setBackground(new Color(30, 30, 47));
        loginPanel.setBackground(new Color(30, 30, 47));

        titleLabel.setForeground(new Color(236, 240, 241));
        loginButton.setForeground(new Color(37, 99, 235));
        areaPersonaleButton.setForeground(new Color(37, 99, 235));
        rankingList.setBackground(new Color(236, 240, 241));
        rankingList.setForeground(new Color(30, 30, 47));

        UIManager.put("OptionPane.background", new Color(30, 30, 47));
        UIManager.put("Panel.background", new Color(30, 30, 47));
        UIManager.put("OptionPane.messageForeground", new Color(236, 240, 241));
        UIManager.put("OptionPane.messageFont", new Font("JetBrains Mono", Font.PLAIN, 14));

        currentVenueLabel.setText("\uD83C\uDF10 Sede:");
        currentVenueLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        currentVenueLabel.setForeground(new Color(30, 30, 47));
        currentVenueLabel.setBackground(new Color(236, 240, 241));
        currentTitleLabel.setText("\uD83D\uDCCC Titolo:");
        currentTitleLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        currentTitleLabel.setForeground(new Color(30, 30, 47));
        currentTitleLabel.setBackground(new Color(236, 240, 241));
        currentStartLabel.setText("\uD83D\uDDD3 Inizio Hackathon:");
        currentStartLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        currentStartLabel.setForeground(new Color(30, 30, 47));
        currentStartLabel.setBackground(new Color(236, 240, 241));
        currentEndLabel.setText("\uD83D\uDDD3 Fine Hackathon:");
        currentEndLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        currentEndLabel.setForeground(new Color(30, 30, 47));
        currentEndLabel.setBackground(new Color(236, 240, 241));
        currentStartRegLabel.setText("\uD83D\uDDD3 Inizio iscrizioni:");
        currentStartRegLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        currentStartRegLabel.setForeground(new Color(30, 30, 47));
        currentStartRegLabel.setBackground(new Color(236, 240, 241));
        currentMaxRegLabel.setText("\uD83C\uDF9F Partecipanti massimi:");
        currentMaxRegLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        currentMaxRegLabel.setForeground(new Color(30, 30, 47));
        currentMaxRegLabel.setBackground(new Color(236, 240, 241));
        currentCounterLabel.setText("\uD83D\uDEB9 Contatore iscritti:");
        currentCounterLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        currentCounterLabel.setForeground(new Color(30, 30, 47));
        currentCounterLabel.setBackground(new Color(236, 240, 241));
        currentMaxTeamParLabel.setText("\uD83D\uDC6B Partecipanti massimi per team:");
        currentMaxTeamParLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        currentMaxTeamParLabel.setForeground(new Color(30, 30, 47));
        currentMaxTeamParLabel.setBackground(new Color(236, 240, 241));
        currentProbDescLabel.setText("\uD83D\uDCC3 Descrizione problema:");
        currentProbDescLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        currentProbDescLabel.setForeground(new Color(30, 30, 47));
        currentProbDescLabel.setBackground(new Color(236, 240, 241));
        currentProbDescArea.setForeground(new Color(30, 30, 47));
        currentProbDescArea.setBackground(new Color(236, 240, 241));
        currentProbDescScroll.setBorder(new LineBorder(null));
        currentProbDescArea.setBorder(new LineBorder(new Color(30, 30, 47)));
        int width = currentProbDescArea.getWidth();
        currentProbDescArea.setPreferredSize(new Dimension(width, 100));
        hackathonPanel.setBorder(new LineBorder(new Color(30, 30, 47)));

        int idLastHack = controller.findLastHack();
        controller.setHackValue(currentTitleArea, currentVenueArea, currentStartArea, currentEndArea, currentStartRegArea, currentMaxRegArea, currentCounterArea, currentMaxTeamParArea,currentProbDescArea);
        DefaultListModel<String> model = new DefaultListModel<>();
        rankingList.setModel(model);
        ArrayList<String> ranking = new ArrayList<>();
        controller.getRanking(ranking, idLastHack);
        for(String nickname : ranking){
            model.removeAllElements();
            model.addElement(nickname);
        }
        if(ranking.isEmpty()){
            model.addElement("Classifica ancora non disponibile");
        }



        //aggiungere la negazione quando effettivamente istanziamo un utente nel controller

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loginButton.getText().equalsIgnoreCase("LOGIN")) {
                    Login login = new Login(frame, controller);
                    JFrame loginFrame = login.getFrame();

                    // Configurazione iniziale
                    frame.setEnabled(false);
                    loginFrame.setAlwaysOnTop(true);
                    loginFrame.setVisible(true);

                    // Gestione intelligente del focus
                    loginFrame.addWindowStateListener(new WindowStateListener() {
                        @Override
                        public void windowStateChanged(WindowEvent e) {
                            if ((e.getNewState() & Frame.ICONIFIED) == 0) {
                                // Solo se NON è iconizzata, mantieni il focus
                                loginFrame.toFront();
                                loginFrame.requestFocus();
                            }
                        }
                    });

                    loginFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            frame.setEnabled(true);
                            frame.toFront();
                        }

                        @Override
                        public void windowDeiconified(WindowEvent e) {
                            // Quando viene ripristinata dalla barra delle applicazioni
                            loginFrame.toFront();
                            loginFrame.requestFocus();
                        }
                    });

                } else if(loginButton.getText().equalsIgnoreCase("LOGOUT")) {
                    System.out.println(controller.getPlAdmin());
                    controller.logout();
                    System.out.println(controller.getPlAdmin());
                    JOptionPane.showMessageDialog(panel, "Logout eseguito");
                    areaPersonaleButton.setEnabled(false);
                    controller.setHackathon(null);
                    loginButton.setText("LogIn");
                }
            }
        });


        areaPersonaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.areaPersonale(frame);
            }
        });


        scrollPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        for(ArrayList<Object> arrayList : data) {
            int idHack = (int) arrayList.get(9);
            GridBagConstraints gbc2 = new GridBagConstraints();
            gbc2.insets = new Insets(10, 10, 10, 10);
            JPanel hackListPanel = new JPanel(new GridBagLayout());

            gbc2.gridx = 0;
            gbc2.gridy = 0;
            gbc2.gridheight = 6;
            gbc2.fill = GridBagConstraints.VERTICAL;
            gbc2.anchor = GridBagConstraints.WEST;

            JLabel imageHack = new JLabel();
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream((byte[])arrayList.get(10));
                BufferedImage image = ImageIO.read(bis);
                Image scaledImage2 = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon2 = new ImageIcon(scaledImage2);
                imageHack.setIcon(resizedIcon2);
            } catch (Exception e) {
                e.printStackTrace();
                ImageIcon imageIcon2 = new ImageIcon(getClass().getResource("/Hackerlogo.jpg"));
                Image scaledImage2 = imageIcon2.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon2 = new ImageIcon(scaledImage2);
                imageHack.setIcon(resizedIcon2);
            }
            hackListPanel.add(imageHack, gbc2);
            gbc2.gridheight = 1;
            gbc2.gridy = 0;
            gbc2.gridx = 1;
            gbc2.anchor = GridBagConstraints.WEST;
            JLabel titleHack = new JLabel();
            hackListPanel.add(setStyleLabel(titleHack, arrayList.get(0), "\uD83D\uDCCC "), gbc2);
            gbc2.gridx = 2;
            JLabel venueHack = new JLabel();
            hackListPanel.add(setStyleLabel(venueHack, arrayList.get(1), "\uD83C\uDF10 "), gbc2);
            gbc2.gridy = 1;
            gbc2.gridx = 1;
            JLabel startHack = new JLabel();
            hackListPanel.add(setStyleLabel(startHack, arrayList.get(2), "\uD83D\uDDD3 Inizio Hackathon: "), gbc2);
            gbc2.gridx = 2;
            JLabel endHack = new JLabel();
            hackListPanel.add(setStyleLabel(endHack, arrayList.get(3), "\uD83D\uDDD3 Fine Hackathon: "), gbc2);
            gbc2.gridy = 2;
            gbc2.gridx = 1;
            JLabel startRegHack = new JLabel();
            String s = new String();
            Object o = new Object();
            if (arrayList.get(8) == null) {
                s = ("\uD83D\uDDD3 Inizio registrazioni: da definire");
                o = null;
            } else {
                s = ("\uD83D\uDDD3 Inizio registrazioni: ");
                o = arrayList.get(8);
            }
            hackListPanel.add(setStyleLabel(startRegHack, o, s), gbc2);
            gbc2.gridx = 2;
            JLabel maxPHack = new JLabel();
            hackListPanel.add(setStyleLabel(maxPHack, arrayList.get(4), "\uD83C\uDF9F "), gbc2);
            gbc2.gridy = 3;
            gbc2.gridx = 1;
            JLabel counterHack = new JLabel();
            hackListPanel.add(setStyleLabel(counterHack, arrayList.get(6), "\uD83D\uDEB9 "), gbc2);
            gbc2.gridx = 2;
            JLabel maxPTHack = new JLabel();
            hackListPanel.add(setStyleLabel(maxPTHack, arrayList.get(5), "\uD83D\uDC6B "), gbc2);
            gbc2.gridy = 4;
            gbc2.gridx = 1;
            hackListPanel.setBackground(new Color(236, 240, 241));
            hackListPanel.setBorder(new LineBorder(new Color(30, 30, 47)));
            hackListPanel.setPreferredSize(new Dimension(700, 250));

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.weightx = 10;
            gbc.weighty = 1;
            gbc.anchor = GridBagConstraints.WEST;

            scrollPanel.add(hackListPanel, gbc);


            //FORSE MEGLIO CREARE BOTTONE IN OGNI PANEL
            hackListPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(controller.getUser() == null && controller.getPlAdmin() == null){
                        JOptionPane.showMessageDialog(panel, "Accedi per visualizzare tutti i dettagli e partecipare all'Hackathon!", "INFO", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        controller.setHackathon((String) arrayList.get(0), (String) arrayList.get(1), (Date) arrayList.get(2),
                                (Date) arrayList.get(3), (int) arrayList.get(4), (int) arrayList.get(5),
                                (String) arrayList.get(7), (Date) arrayList.get(8), (int) arrayList.get(6));
                        controller.setIdHack(idHack);
                        controller.setPhoto((byte[]) arrayList.get(10));
                        HackathonGui hackGui = new HackathonGui(frame, controller);
                        hackGui.getFrame().setVisible(true);
                        //System.out.println(controller.getIdHack());
                        frame.setVisible(false);
                    }
                }
            });

            hackListPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if(frame.isEnabled())
                        hackListPanel.setBorder(new LineBorder(new Color(37, 99, 235), 3));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if(frame.isEnabled())
                        hackListPanel.setBorder(new LineBorder(new Color(30, 30, 47)));
                }
            });
        }

    }

    /**
     * Restituisci il bottone per accedere all'area personale.
     *
     * @return JButton: bottone per accedere all'area personale.
     */
    public JButton getAreaPersonaleButton() {
        return areaPersonaleButton;
    }

    /**
     * Restituisci il bottone per effettuare il login.
     *
     * @return JButton: bottone per effettuare il login.
     */
    public JButton getLoginButton() {
        return loginButton;
    }

    public JFrame getFrame() {
        return frame;
    }

    public ArrayList<ArrayList<Object>> getData() {
        return data;
    }

    public JLabel setStyleLabel (JLabel label, Object o, String s){
        if(o != null)
            label.setText(s + o);
        else
            label.setText(s);
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        label.setForeground(new Color(30, 30, 47));
        label.setBackground(new Color(236, 240, 241));
        return label;
    }
}
