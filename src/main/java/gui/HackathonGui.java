package gui;

import controller.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 * The type Hackathon gui.
 */
public class HackathonGui {
    private JPanel panel;
    private JPanel headerPanel;
    private JPanel infoPanel;
    private JButton accessButton;
    private JButton subscribeHackButton;
    private JPanel imagePanel;
    private JButton homeButton;
    private JPanel descPanel;
    private JLabel titleLabel;
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
    private JPanel problemPanel;
    private JTextArea problemArea;
    private JLabel problemLabel;
    private JScrollPane problemScrollPane;
    private JScrollPane scrollPane;
    private JPanel scrollPanel;
    private JTextArea judjesArea;
    private JPanel staffPanel;
    private JLabel judjesLabel;
    private JLabel organizerLabel;
    private JLabel organizerArea;
    private JPanel organizerAreaPanel;
    private JLabel imageLabel;
    private JTextArea rankingTextArea;
    private JLabel rankingLabel;
    private JScrollPane rankingScrollPane;
    private JFrame frame;


    /**
     * Instantiates a new Hackathon gui.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     */
    public HackathonGui(JFrame frameChiamante, Controller controller){
        frame = new JFrame("HackManager");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension(1000, 700));
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        String font = "Segoe UI Emoji";
        ArrayList<String> teams = new ArrayList<>();

        panel.setBackground(new Color(30, 30, 47));
        infoPanel.setBackground(new Color(236, 240, 241));
        imagePanel.setBackground(new Color(30, 30, 47));
        hackathonPanel.setBackground(new Color(236, 240, 241));
        problemPanel.setBackground(new Color(236, 240, 241));
        staffPanel.setBackground(new Color(236, 240, 241));
        judjesArea.setBackground(new Color(236, 240, 241));
        organizerAreaPanel.setBackground(new Color(236, 240, 241));
        organizerAreaPanel.setForeground(new Color(30, 30, 47));
        titleLabel.setForeground(new Color(236, 240, 241));
        judjesLabel.setForeground(new Color(30, 30, 47));
        organizerLabel.setForeground(new Color(30, 30, 47));
        judjesArea.setForeground(new Color(30, 30, 47));
        organizerArea.setForeground(new Color(30, 30, 47));


        homeButton.setForeground(new Color(37, 99, 235));
        subscribeHackButton.setForeground(new Color(37, 99, 235));
        accessButton.setForeground(new Color(37, 99, 235));

        currentVenueLabel.setText("\uD83C\uDF10 Sede:");
        currentVenueLabel.setFont(new Font(font, Font.PLAIN, 14));
        currentVenueLabel.setForeground(new Color(30, 30, 47));
        currentVenueLabel.setBackground(new Color(236, 240, 241));
        currentTitleLabel.setText("\uD83D\uDCCC Titolo:");
        currentTitleLabel.setFont(new Font(font, Font.PLAIN, 14));
        currentTitleLabel.setForeground(new Color(30, 30, 47));
        currentTitleLabel.setBackground(new Color(236, 240, 241));
        currentStartLabel.setText("\uD83D\uDDD3 Inizio Hackathon:");
        currentStartLabel.setFont(new Font(font, Font.PLAIN, 14));
        currentStartLabel.setForeground(new Color(30, 30, 47));
        currentStartLabel.setBackground(new Color(236, 240, 241));
        currentEndLabel.setText("\uD83D\uDDD3 Fine Hackathon:");
        currentEndLabel.setFont(new Font(font, Font.PLAIN, 14));
        currentEndLabel.setForeground(new Color(30, 30, 47));
        currentEndLabel.setBackground(new Color(236, 240, 241));
        currentStartRegLabel.setText("\uD83D\uDDD3 Inizio iscrizioni:");
        currentStartRegLabel.setFont(new Font(font, Font.PLAIN, 14));
        currentStartRegLabel.setForeground(new Color(30, 30, 47));
        currentStartRegLabel.setBackground(new Color(236, 240, 241));
        currentMaxRegLabel.setText("\uD83C\uDF9F Partecipanti massimi:");
        currentMaxRegLabel.setFont(new Font(font, Font.PLAIN, 14));
        currentMaxRegLabel.setForeground(new Color(30, 30, 47));
        currentMaxRegLabel.setBackground(new Color(236, 240, 241));
        currentCounterLabel.setText("\uD83D\uDEB9 Contatore iscritti:");
        currentCounterLabel.setFont(new Font(font, Font.PLAIN, 14));
        currentCounterLabel.setForeground(new Color(30, 30, 47));
        currentCounterLabel.setBackground(new Color(236, 240, 241));
        currentMaxTeamParLabel.setText("\uD83D\uDC6B Partecipanti massimi per team:");
        currentMaxTeamParLabel.setFont(new Font(font, Font.PLAIN, 14));
        currentMaxTeamParLabel.setForeground(new Color(30, 30, 47));
        currentMaxTeamParLabel.setBackground(new Color(236, 240, 241));
        problemLabel.setForeground(new Color(30, 30, 47));
        problemLabel.setBackground(new Color(236, 240, 241));
        problemArea.setForeground(new Color(30, 30, 47));
        problemArea.setBackground(new Color(236, 240, 241));
        problemScrollPane.setBorder(new LineBorder(null));
        problemArea.setBorder(new LineBorder(new Color(30, 30, 47)));
        int width = problemArea.getWidth();
        problemArea.setPreferredSize(new Dimension(width, 100));
        rankingLabel.setForeground(new Color(223,225,229));
        rankingTextArea.setForeground(new Color(223,225,229));
        rankingTextArea.setBackground(new Color(30, 30, 47));
        rankingTextArea.setBorder(new LineBorder(new Color(223,225,229)));
        rankingScrollPane.setBorder(null);

        hackathonPanel.setBorder(new LineBorder(new Color(30, 30, 47)));
        problemPanel.setBorder(new LineBorder(new Color(30, 30, 47)));
        staffPanel.setBorder(new LineBorder(new Color(30, 30, 47)));
        judjesArea.setBorder(new LineBorder(new Color(30, 30, 47)));
        organizerAreaPanel.setBorder(new LineBorder(new Color(30, 30, 47)));

        titleLabel.setText(controller.getHackathon().getTitle());
        controller.setHackValue(currentTitleArea, currentVenueArea, currentStartArea, currentEndArea, currentStartRegArea, currentMaxRegArea, currentCounterArea, currentMaxTeamParArea, problemArea);
        controller.fillRanking(teams);
        rankingTextArea.setLineWrap(true);
        if(!teams.isEmpty()){
            for (String t : teams) {
                rankingTextArea.append(t + "\n");
            }
        }else{
            rankingTextArea.append("La classifica per quest'evento non è ancora disponibile");
        }

        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(controller.getPhoto());
            BufferedImage image = ImageIO.read(bis);
            Image scaledImage2 = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon2 = new ImageIcon(scaledImage2);
            imageLabel.setIcon(resizedIcon2);
        } catch (NullPointerException e) {
            e.printStackTrace();
            ImageIcon imageIcon2 = new ImageIcon(getClass().getResource("/Hackerlogo.jpg"));
            Image scaledImage2 = imageIcon2.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon2 = new ImageIcon(scaledImage2);
            imageLabel.setIcon(resizedIcon2);
        } catch (Exception e){
            e.printStackTrace();
            ImageIcon imageIcon2 = new ImageIcon(getClass().getResource("/errorImage.jpg"));
            Image scaledImage2 = imageIcon2.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon2 = new ImageIcon(scaledImage2);
            imageLabel.setIcon(resizedIcon2);
        }

        ArrayList<String> judges = new ArrayList<>();
        if(controller.getCurrIdHack() == -1){
            controller.getJudgesList(judges);
            organizerArea.setText(controller.getOrganizer());
        } else {
            controller.getActJudgesList(judges);
            organizerArea.setText(controller.getActOrganizer());
        }

        organizerArea.setText(controller.getOrganizer());
        if (judges.isEmpty()) {
            judjesArea.setText("Al momento non sono stati ancora determinati i giudici per questo Hackathon");
        } else {
            judjesArea.setText("");
            for (String s : judges) {
                judjesArea.append(s + "\n");
            }
        }


        accessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.areaPersonale(frame);
                controller.findHack();
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getHome().getFrame().setVisible(true);
                controller.getHome().getFrame().repaint();
                controller.getHome().fillHacks(controller.getHome().getFont());
                controller.setPhoto(null);
                controller.findHack();
                frame.dispose();
            }
        });

        subscribeHackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate start = LocalDate.parse(currentStartArea.getText(), formatter);
                LocalDate end = LocalDate.parse(currentEndArea.getText(), formatter);
                int code = controller.subscribe(start, end);
                switch (code){
                    case -4:
                        subscribeHackButton.setToolTipText("Non è più possibile iscriversi a quest'evento");
                        subscribeHackButton.setEnabled(false);
                        break;
                    case -3:
                        JOptionPane.showMessageDialog(panel, "Sei già impegnato durante il periodo di questo Hackathon", "INFO", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case -2:
                        JOptionPane.showMessageDialog(panel, "Le iscrizioni non sono ancora cominciate o non sono state ancora definite");
                        break;
                    case -1:
                        JOptionPane.showMessageDialog(panel, "L'Hackathon ha raggiunto il massimo degli iscritti", "INFO", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(panel, "Iscrizione effettuata con successo", "INFO", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 0:
                    default:
                        JOptionPane.showMessageDialog(panel, "Qualcosa è andato storto durante l'iscrizione", "INFO", JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
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

}

