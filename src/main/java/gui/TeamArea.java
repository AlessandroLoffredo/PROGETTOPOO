package gui;

import controller.Controller;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * The type Team area.
 */
public class TeamArea {
    private JPanel panel;
    private JList<String> participantsList;
    private JList<String> docList;
    private JPanel docPanel;
    private JButton loadDocButton;
    private JLabel nickArea;
    private JButton hackButton;
    private JLabel nickLabel;
    private JButton homeButton;
    private JButton sendButton;
    private JPanel loadSendPanel;
    private JPanel profilePanel;
    private JPanel dataPanel;
    private JButton cambiaNicknameButton;
    private JLabel teamLabel;
    private JLabel profileLabel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JLabel imageLabel;
    private JLabel docLabel;
    private JFrame frame;
    private File file;
    private JTextArea pathArea;
    private JTextArea commentArea;
    private JLabel commentLabel;

    /**
     * Instantiates a new Team area.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     */
    public TeamArea(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("HackManager");
        frame.setContentPane(panel);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
        frame.pack();
        frame.setVisible(true);
        frame.setSize(950, 800);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        String panelBkg = "Panel.background";
        ArrayList<String> documents = new ArrayList<>();
        ArrayList<byte[]> files = new ArrayList<>();
        ArrayList<String> comments = new ArrayList<>();

        controller.getTeam();

        nickArea.setText(controller.getNickname());
        panel.setBackground(new Color(30, 30, 47));
        profilePanel.setBackground(new Color(30, 30, 47));
        profileLabel.setForeground(new Color(236, 240, 241));
        teamLabel.setForeground(new Color(236, 240, 241));
        docPanel.setBackground(new Color(236, 240, 241));
        loadSendPanel.setBackground(new Color(236, 240, 241));
        nickArea.setBackground(new Color(30, 30, 47));
        docList.setBackground(new Color(236, 240, 241));
        participantsList.setBackground(new Color(236, 240, 241));
        participantsList.setForeground(new Color(30, 30, 47));
        nickLabel.setForeground(new Color(236, 240, 241));
        nickArea.setForeground(new Color(236, 240, 241));
        docLabel.setForeground(new Color(30, 30, 47));
        docList.setForeground(new Color(30, 30, 47));
        commentArea.setBackground(new Color(236, 240, 241));
        commentArea.setForeground(new Color(30, 30, 47));
        participantsList.setForeground(new Color(30, 30, 47));
        titleLabel.setForeground(new Color(236, 240, 241));
        cambiaNicknameButton.setForeground(new Color(37, 99, 235));
        homeButton.setForeground(new Color(37, 99, 235));
        hackButton.setForeground(new Color(37, 99, 235));
        loadDocButton.setForeground(new Color(37, 99, 235));
        sendButton.setForeground(new Color(37, 99, 235));

        docList.setBorder(new LineBorder(new Color(30, 30, 47)));
        docList.setMaximumSize(new Dimension(100, 100));
        commentArea.setBorder(new LineBorder(new Color(30, 30, 47)));
        pathArea.setMaximumSize(new Dimension(100, 100));
        pathArea.setBorder(null);
        pathArea.setBackground(null);
        pathArea.setForeground(new Color(30, 30, 47));
        pathArea.setLineWrap(true);
        pathArea.setWrapStyleWord(true);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/iconaUser.png"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(resizedIcon);

        this.fillTeammates(controller);
        this.blockHack(controller);
        this.fillDocs(controller, documents, files, comments);

        hackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.findHack();
                HackathonGui hackathonGui = new HackathonGui(frame, controller);
                hackathonGui.getFrame().setVisible(true);
                frame.dispose();
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getHome().getFrame().setVisible(true);
                controller.getHome().fillHacks(controller.getHome().getFont());
                controller.findHack();
                frame.dispose();
            }
        });

        loadDocButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager.put("FileChooser.background", new Color(238, 238, 238));
                UIManager.put("FileChooser.foreground", new Color(0, 0, 0));
                UIManager.put("FileChooser.listViewBackground", new Color(255, 255, 255));
                UIManager.put("FileChooser.listViewForeground", new Color(0, 0, 0));
                UIManager.put("FileChooser.buttonBackground", new Color(240, 240, 240));
                UIManager.put(panelBkg, new Color(240, 240, 240));
                JFileChooser fileChooser = new JFileChooser();
                int scelta = fileChooser.showOpenDialog(frame);
                if(scelta == JFileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();
                    pathArea.setText(file.toString());
                }
                UIManager.put(panelBkg, new Color(30, 30, 47));
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int code = controller.sendFile(file, file.getName());
                UIManager.put(panelBkg, new Color(30, 30, 47));
                switch (code){
                    case -1:
                        JOptionPane.showMessageDialog(panel, "Hai già caricato questo documento");
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(panel, "Inserimento del file andato a buon fine");
                        fillDocs(controller, documents, files, comments);
                        break;
                    default:
                        JOptionPane.showMessageDialog(panel, "Errore durante il caricamento del file");
                        break;
                }
            }
        });

        cambiaNicknameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CambiaNickname cambiaNickname = new CambiaNickname(frame, controller);
                JFrame cambiaNicknameFrame = cambiaNickname.getFrame();

                // Configurazione iniziale
                frame.setEnabled(false);
                cambiaNicknameFrame.setAlwaysOnTop(true);
                cambiaNicknameFrame.setVisible(true);

                // Gestione intelligente del focus
                cambiaNicknameFrame.addWindowStateListener(new WindowStateListener() {
                    @Override
                    public void windowStateChanged(WindowEvent e) {
                        if ((e.getNewState() & Frame.ICONIFIED) == 0) {
                            // Solo se NON è iconizzata, mantieni il focus
                            cambiaNicknameFrame.toFront();
                            cambiaNicknameFrame.requestFocus();
                        }
                    }
                });

                cambiaNicknameFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        frame.setEnabled(true);
                        frame.toFront();
                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {
                        // Quando viene ripristinata dalla barra delle applicazioni
                        cambiaNicknameFrame.toFront();
                        cambiaNicknameFrame.requestFocus();
                    }
                });
            }
        });


        docList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleDoc(e, comments, files);
            }
        });
    }

    /**
     * Gets frame.
     *
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }

    private void fillTeammates(Controller controller){
        ArrayList<String> teammates = new ArrayList<>();
        controller.findTeammates(teammates);
        DefaultListModel<String> model = new DefaultListModel<>();
        for(String s : teammates){
            model.addElement(s);
        }
        participantsList.setModel(model);
    }

    private void blockHack(Controller controller){
        if(!controller.isHackStarted()){
            loadDocButton.setEnabled(false);
            sendButton.setEnabled(false);
            loadDocButton.setToolTipText("L'evento non è ancora cominciato");
            sendButton.setToolTipText("L'evento non è ancora cominciato");
        }
    }

    private void fillDocs(Controller controller, ArrayList<String> documents, ArrayList<byte[]> files, ArrayList<String> comments){
        controller.getDocuments(documents, files, comments);
        DefaultListModel<String> model1 = new DefaultListModel<>();
        model1.removeAllElements();
        for(String doc : documents){
            model1.addElement(doc);
        }
        docList.setModel(model1);
    }

    private void handleDoc(MouseEvent e, ArrayList<String> comments, ArrayList<byte[]> files){
        int selectedIndex = docList.locationToIndex(e.getPoint());
        if(e.getClickCount() == 1){
            String commento = comments.get(selectedIndex);
            if (commento != null) {
                commentArea.setText(commento);
            }else{
                commentArea.setText("Il documento non è stato ancora commentato");
            }
        }else if (e.getClickCount() == 2) { // Rileva il doppio click
            if (selectedIndex == -1) return;
            try {
                // Crea un file temporaneo in memoria
                Path tempFile = Files.createTempFile("temp_", ".pdf");
                Files.write(tempFile, files.get(selectedIndex));

                // Apri con l'applicazione predefinita
                Desktop.getDesktop().open(tempFile.toFile());

                // Elimina il file all'uscita
                tempFile.toFile().deleteOnExit();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(docList,
                        "Errore nell'apertura del file",
                        "Errore",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
