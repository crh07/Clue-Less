/**
 * Contributors: Ryan D Bonisch
 * Course: EN.605.401 Foundations of Software Engineering
 * Start: 04-Oct-16 11:00
 * End: 
 */
package Game;

/**
 * Import necessary Libraries
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;

/**
 * 
 * 
 */
public class Lobby extends JPanel {
    
    //
    private Socket socket;
    
    //
    private DataOutputStream toServer;
    
    //
    private DataInputStream fromServer;
    
    //
    private JTextArea jtaChatWindow;
    
    //
    private String prompt;
    
    //
    private JLabel jlPlayer1Username;
    
    //
    private JLabel jlPlayer1IRLName;
    
    //
    private final JLabel jlPlayer1Color;
    
    //
    private JComboBox jcbPlayer1GameCharacter;
    
    //
    private JCheckBox jchkPlayer1Ready;
    
    //
    private JLabel jlPlayer2Username;
    
    //
    private JLabel jlPlayer2IRLName;
    
    //
    private final JLabel jlPlayer2Color;
    
    //
    private JComboBox jcbPlayer2GameCharacter;
    
    //
    private JCheckBox jchkPlayer2Ready;
    
    //
    private JLabel jlPlayer3Username;
    
    //
    private JLabel jlPlayer3IRLName;
    
    //
    private final JLabel jlPlayer3Color;
    
    //
    private JComboBox jcbPlayer3GameCharacter;
    
    //
    private JCheckBox jchkPlayer3Ready;
    
    //
    private JLabel jlPlayer4Username;
    
    //
    private JLabel jlPlayer4IRLName;
    
    //
    private final JLabel jlPlayer4Color;
    
    //
    private JComboBox jcbPlayer4GameCharacter;
    
    //
    private JCheckBox jchkPlayer4Ready;
    
    //
    private JLabel jlPlayer5Username;
    
    //
    private JLabel jlPlayer5IRLName;
    
    //
    private final JLabel jlPlayer5Color;
    
    //
    private JComboBox jcbPlayer5GameCharacter;
    
    //
    private JCheckBox jchkPlayer5Ready;
    
    //
    private JLabel jlPlayer6Username;
    
    //
    private JLabel jlPlayer6IRLName;
    
    //
    private final JLabel jlPlayer6Color;
    
    //
    private JComboBox jcbPlayer6GameCharacter;
    
    //
    private JCheckBox jchkPlayer6Ready;
    
    //
    private JButton jbStart;
    
    //
    private int playerNum;
    
    /**
     * 
     * @param frame
     * @param socket
     * @param toServer
     * @param fromServer
     * @param username 
     */
    public Lobby(
            JFrame frame, Socket socket,
            DataOutputStream toServer, DataInputStream fromServer,
            String username) {
        
        prompt = "[" + username + "]: ";
        this.socket = socket;
        this.toServer = toServer;
        this.fromServer = fromServer;
        
        double componentWidth = frame.getWidth() - 35;
        double componentHeight = frame.getHeight() / 20;
        double nameWeight = 0.5;
        double gameCharWeight = 0.8;
        double colorWeight = 0.1;
        double readyWeight = 0;
        int usernameXGrid = 0;
        int irlNameXGrid = 1;
        int colorXGrid = 2;
        int gameCharXGrid = 3;
        int playerReadyXGrid = 4;
        String[] gameCharacters = {
            "Colonel Mustard", "Miss Scarlet", "Professor Plum",
            "Mr. Green", "Mrs. White", "Mrs. Peacock"};
        
        // getClass().getResource doesnt work for some reason
        // invalid credentials?
        //ImageIcon yellow = new ImageIcon(getClass().getResource("yellow.jpg"));
        
        //Absolute path works, but is very cumbersome in the future.
        final ImageIcon yellow = new ImageIcon(
                "C:\\Users\\RyanWin7\\Documents\\NetBeansProjects\\Clue-Less\\yellow.jpg");
        final ImageIcon red = new ImageIcon(
                "C:\\Users\\RyanWin7\\Documents\\NetBeansProjects\\Clue-Less\\red.jpg");
        final ImageIcon purple = new ImageIcon(
                "C:\\Users\\RyanWin7\\Documents\\NetBeansProjects\\Clue-Less\\purple.jpg");
        final ImageIcon green = new ImageIcon(
                "C:\\Users\\RyanWin7\\Documents\\NetBeansProjects\\Clue-Less\\green.jpg");
        final ImageIcon white = new ImageIcon(
                "C:\\Users\\RyanWin7\\Documents\\NetBeansProjects\\Clue-Less\\white.jpg");
        final ImageIcon blue = new ImageIcon(
                "C:\\Users\\RyanWin7\\Documents\\NetBeansProjects\\Clue-Less\\blue.jpg");
        
        try {
            playerNum = fromServer.readInt();
        } catch (IOException ex) {
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints mainConstraint = new GridBagConstraints();
        LineBorder lineBorder = new LineBorder(Color.BLACK, 1);
        
        JPanel playerStatusPanel = new JPanel(new GridBagLayout());
        GridBagConstraints playerStatusConstraint = new GridBagConstraints();
        playerStatusPanel.setBorder(lineBorder);
        mainConstraint.fill = GridBagConstraints.HORIZONTAL;
        mainConstraint.anchor = GridBagConstraints.PAGE_START;
        mainConstraint.ipadx = 640;
        mainConstraint.gridx = 0;
        mainConstraint.gridy = 0;
        mainConstraint.ipady = 300;
        mainPanel.add(playerStatusPanel, mainConstraint);
        
        //row 1
        playerStatusConstraint.gridy = 0;
        playerStatusConstraint.ipady = 25;
        
        JLabel jlUsernameLabel = new JLabel("Username");
        Dimension min = new Dimension(1,1);
        jlUsernameLabel.setMinimumSize(min);
        playerStatusConstraint.weighty = 1;
        playerStatusConstraint.fill = GridBagConstraints.NORTHWEST;
        playerStatusConstraint.anchor = GridBagConstraints.FIRST_LINE_START;
        playerStatusConstraint.weightx = nameWeight;
        playerStatusConstraint.gridx = usernameXGrid;
        playerStatusPanel.add(jlUsernameLabel, playerStatusConstraint);
        
        JLabel jlIRLName = new JLabel("IRL Name");
        jlIRLName.setMinimumSize(min);
        playerStatusConstraint.gridx = irlNameXGrid;
        playerStatusPanel.add(jlIRLName, playerStatusConstraint);
        
        JLabel jlColor = new JLabel("Color");
        jlColor.setMinimumSize(min);
        playerStatusConstraint.weightx = colorWeight;
        playerStatusConstraint.gridx = colorXGrid;
        playerStatusPanel.add(jlColor, playerStatusConstraint);
        
        JLabel jlGameCharacter = new JLabel("Game Character");
        jlGameCharacter.setMinimumSize(min);
        playerStatusConstraint.weightx = gameCharWeight;
        playerStatusConstraint.gridx = gameCharXGrid;
        playerStatusPanel.add(jlGameCharacter, playerStatusConstraint);
        
        JLabel jlPlayerReady = new JLabel("Player Ready");
        jlPlayerReady.setMinimumSize(min);
        playerStatusConstraint.weightx = readyWeight;
        playerStatusConstraint.gridx = playerReadyXGrid;
        playerStatusPanel.add(jlPlayerReady, playerStatusConstraint);
        
        //row 2
        playerStatusConstraint.gridy = 1;
        playerStatusConstraint.ipady = 0;
        
        jlPlayer1Username = new JLabel("Player1");
        jlPlayer1Username.setEnabled(false);
        jlPlayer1Username.setMinimumSize(min);
        playerStatusConstraint.weightx = nameWeight;
        playerStatusConstraint.gridx = usernameXGrid;
        playerStatusPanel.add(jlPlayer1Username, playerStatusConstraint);
        
        jlPlayer1IRLName = new JLabel("name");
        jlPlayer1IRLName.setEnabled(false);
        jlPlayer1IRLName.setMinimumSize(min);
        playerStatusConstraint.gridx = irlNameXGrid;
        playerStatusPanel.add(jlPlayer1IRLName, playerStatusConstraint);
        
        jlPlayer1Color = new JLabel();
        jlPlayer1Color.setEnabled(false);
        jlPlayer1Color.setMinimumSize(min);
        playerStatusConstraint.weightx = colorWeight;
        playerStatusConstraint.gridx = colorXGrid;
        playerStatusPanel.add(jlPlayer1Color, playerStatusConstraint);
        
        jcbPlayer1GameCharacter = new JComboBox(gameCharacters);
        jcbPlayer1GameCharacter.setMinimumSize(min);
        jcbPlayer1GameCharacter.setEnabled(false);
        jcbPlayer1GameCharacter.setSelectedIndex(-1);
        playerStatusConstraint.weightx = gameCharWeight;
        playerStatusConstraint.gridx = gameCharXGrid;
        playerStatusPanel.add(jcbPlayer1GameCharacter, playerStatusConstraint);
        jcbPlayer1GameCharacter.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Colonel Mustard")) {
                        jlPlayer1Color.setIcon(yellow);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Miss Scarlet")) {
                        jlPlayer1Color.setIcon(red);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Professor Plum")) {
                        jlPlayer1Color.setIcon(purple);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Mr. Green")) {
                        jlPlayer1Color.setIcon(green);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Mrs. White")) {
                        jlPlayer1Color.setIcon(white);
                    }
                    else {
                        jlPlayer1Color.setIcon(blue);
                    }
                }
            }
        });
        
        jchkPlayer1Ready = new JCheckBox("Ready");
        jchkPlayer1Ready.setEnabled(false);
        jchkPlayer1Ready.setMinimumSize(min);
        playerStatusConstraint.weightx = readyWeight;
        playerStatusConstraint.gridx = playerReadyXGrid;
        playerStatusPanel.add(jchkPlayer1Ready, playerStatusConstraint);
        jchkPlayer1Ready.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            }
        });
                
        //row3
        playerStatusConstraint.gridy = 2;
        
        jlPlayer2Username = new JLabel("Player2");
        jlPlayer2Username.setEnabled(false);
        jlPlayer2Username.setMinimumSize(min);
        playerStatusConstraint.weightx = nameWeight;
        playerStatusConstraint.gridx = usernameXGrid;
        playerStatusPanel.add(jlPlayer2Username, playerStatusConstraint);
        
        jlPlayer2IRLName = new JLabel("name");
        jlPlayer2IRLName.setEnabled(false);
        jlPlayer2IRLName.setMinimumSize(min);
        playerStatusConstraint.gridx = irlNameXGrid;
        playerStatusPanel.add(jlPlayer2IRLName, playerStatusConstraint);
        
        jlPlayer2Color = new JLabel();
        jlPlayer2Color.setEnabled(false);
        jlPlayer2Color.setMinimumSize(min);
        playerStatusConstraint.weightx = colorWeight;
        playerStatusConstraint.gridx = colorXGrid;
        playerStatusPanel.add(jlPlayer2Color, playerStatusConstraint);
        
        jcbPlayer2GameCharacter = new JComboBox(gameCharacters);
        jcbPlayer2GameCharacter.setEnabled(false);
        jcbPlayer2GameCharacter.setSelectedIndex(-1);
        jcbPlayer2GameCharacter.setMinimumSize(min);
        playerStatusConstraint.weightx = gameCharWeight;
        playerStatusConstraint.gridx = gameCharXGrid;
        playerStatusPanel.add(jcbPlayer2GameCharacter, playerStatusConstraint);
        jcbPlayer2GameCharacter.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Colonel Mustard")) {
                        jlPlayer2Color.setIcon(yellow);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Miss Scarlet")) {
                        jlPlayer2Color.setIcon(red);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Professor Plum")) {
                        jlPlayer2Color.setIcon(purple);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Mr. Green")) {
                        jlPlayer2Color.setIcon(green);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Mrs. White")) {
                        jlPlayer2Color.setIcon(white);
                    }
                    else {
                        jlPlayer2Color.setIcon(blue);
                    }
                }
            }
        });
        
        jchkPlayer2Ready = new JCheckBox("Ready");
        jchkPlayer2Ready.setEnabled(false);
        jchkPlayer2Ready.setMinimumSize(min);
        playerStatusConstraint.weightx = readyWeight;
        playerStatusConstraint.gridx = playerReadyXGrid;
        playerStatusPanel.add(jchkPlayer2Ready, playerStatusConstraint);
        jchkPlayer2Ready.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            }
        });
        
        //row4
        playerStatusConstraint.gridy = 3;
        
        jlPlayer3Username = new JLabel("Player3");
        jlPlayer3Username.setEnabled(false);
        jlPlayer3Username.setMinimumSize(min);
        playerStatusConstraint.weightx = nameWeight;
        playerStatusConstraint.gridx = usernameXGrid;
        playerStatusPanel.add(jlPlayer3Username, playerStatusConstraint);
        
        jlPlayer3IRLName = new JLabel("name");
        jlPlayer3IRLName.setEnabled(false);
        jlPlayer3IRLName.setMinimumSize(min);
        playerStatusConstraint.gridx = irlNameXGrid;
        playerStatusPanel.add(jlPlayer3IRLName, playerStatusConstraint);
        
        jlPlayer3Color = new JLabel();
        jlPlayer3Color.setEnabled(false);
        jlPlayer3Color.setMinimumSize(min);
        playerStatusConstraint.weightx = colorWeight;
        playerStatusConstraint.gridx = colorXGrid;
        playerStatusPanel.add(jlPlayer3Color, playerStatusConstraint);
        
        jcbPlayer3GameCharacter = new JComboBox(gameCharacters);
        jcbPlayer3GameCharacter.setEnabled(false);
        jcbPlayer3GameCharacter.setSelectedIndex(-1);
        jcbPlayer3GameCharacter.setMinimumSize(min);
        playerStatusConstraint.weightx = gameCharWeight;
        playerStatusConstraint.gridx = gameCharXGrid;
        playerStatusPanel.add(jcbPlayer3GameCharacter, playerStatusConstraint);
        jcbPlayer3GameCharacter.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Colonel Mustard")) {
                        jlPlayer3Color.setIcon(yellow);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Miss Scarlet")) {
                        jlPlayer3Color.setIcon(red);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Professor Plum")) {
                        jlPlayer3Color.setIcon(purple);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Mr. Green")) {
                        jlPlayer3Color.setIcon(green);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Mrs. White")) {
                        jlPlayer3Color.setIcon(white);
                    }
                    else {
                        jlPlayer3Color.setIcon(blue);
                    }
                }
            }
        });
        
        jchkPlayer3Ready = new JCheckBox("Ready");
        jchkPlayer3Ready.setEnabled(false);
        jchkPlayer3Ready.setMinimumSize(min);
        playerStatusConstraint.weightx = readyWeight;
        playerStatusConstraint.gridx = playerReadyXGrid;
        playerStatusPanel.add(jchkPlayer3Ready, playerStatusConstraint);
        jchkPlayer3Ready.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            }
        });
        
        //row 5
        playerStatusConstraint.gridy = 4;
        
        jlPlayer4Username = new JLabel("Player4");
        jlPlayer4Username.setEnabled(false);
        jlPlayer4Username.setMinimumSize(min);
        playerStatusConstraint.weightx = nameWeight;
        playerStatusConstraint.gridx = usernameXGrid;
        playerStatusPanel.add(jlPlayer4Username, playerStatusConstraint);
        
        jlPlayer4IRLName = new JLabel("name");
        jlPlayer4IRLName.setEnabled(false);
        jlPlayer4IRLName.setMinimumSize(min);
        playerStatusConstraint.gridx = irlNameXGrid;
        playerStatusPanel.add(jlPlayer4IRLName, playerStatusConstraint);
        
        jlPlayer4Color = new JLabel();
        jlPlayer4Color.setEnabled(false);
        jlPlayer4Color.setMinimumSize(min);
        playerStatusConstraint.weightx = colorWeight;
        playerStatusConstraint.gridx = colorXGrid;
        playerStatusPanel.add(jlPlayer4Color, playerStatusConstraint);
        
        jcbPlayer4GameCharacter = new JComboBox(gameCharacters);
        jcbPlayer4GameCharacter.setEnabled(false);
        jcbPlayer4GameCharacter.setSelectedIndex(-1);
        jcbPlayer4GameCharacter.setMinimumSize(min);
        playerStatusConstraint.weightx = gameCharWeight;
        playerStatusConstraint.gridx = gameCharXGrid;
        playerStatusPanel.add(jcbPlayer4GameCharacter, playerStatusConstraint);
        jcbPlayer4GameCharacter.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Colonel Mustard")) {
                        jlPlayer4Color.setIcon(yellow);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Miss Scarlet")) {
                        jlPlayer4Color.setIcon(red);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Professor Plum")) {
                        jlPlayer4Color.setIcon(purple);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Mr. Green")) {
                        jlPlayer4Color.setIcon(green);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Mrs. White")) {
                        jlPlayer4Color.setIcon(white);
                    }
                    else {
                        jlPlayer4Color.setIcon(blue);
                    }
                }
            }
        });
        
        jchkPlayer4Ready = new JCheckBox("Ready");
        jchkPlayer4Ready.setEnabled(false);
        jchkPlayer4Ready.setMinimumSize(min);
        playerStatusConstraint.weightx = readyWeight;
        playerStatusConstraint.gridx = playerReadyXGrid;
        playerStatusPanel.add(jchkPlayer4Ready, playerStatusConstraint);
        jchkPlayer4Ready.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            }
        });
        
        //row 6
        playerStatusConstraint.gridy = 5;
        
        jlPlayer5Username = new JLabel("Player5");
        jlPlayer5Username.setEnabled(false);
        jlPlayer5Username.setMinimumSize(min);
        playerStatusConstraint.weightx = nameWeight;
        playerStatusConstraint.gridx = usernameXGrid;
        playerStatusPanel.add(jlPlayer5Username, playerStatusConstraint);
        
        jlPlayer5IRLName = new JLabel("name");
        jlPlayer5IRLName.setEnabled(false);
        jlPlayer5IRLName.setMinimumSize(min);
        playerStatusConstraint.gridx = irlNameXGrid;
        playerStatusPanel.add(jlPlayer5IRLName, playerStatusConstraint);
        
        jlPlayer5Color = new JLabel();
        jlPlayer5Color.setEnabled(false);
        jlPlayer5Color.setMinimumSize(min);
        playerStatusConstraint.weightx = colorWeight;
        playerStatusConstraint.gridx = colorXGrid;
        playerStatusPanel.add(jlPlayer5Color, playerStatusConstraint);
        
        jcbPlayer5GameCharacter = new JComboBox(gameCharacters);
        jcbPlayer5GameCharacter.setEnabled(false);
        jcbPlayer5GameCharacter.setSelectedIndex(-1);
        jcbPlayer5GameCharacter.setMinimumSize(min);
        playerStatusConstraint.weightx = gameCharWeight;
        playerStatusConstraint.gridx = gameCharXGrid;
        playerStatusPanel.add(jcbPlayer5GameCharacter, playerStatusConstraint);
        jcbPlayer5GameCharacter.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Colonel Mustard")) {
                        jlPlayer5Color.setIcon(yellow);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Miss Scarlet")) {
                        jlPlayer5Color.setIcon(red);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Professor Plum")) {
                        jlPlayer5Color.setIcon(purple);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Mr. Green")) {
                        jlPlayer5Color.setIcon(green);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Mrs. White")) {
                        jlPlayer5Color.setIcon(white);
                    }
                    else {
                        jlPlayer5Color.setIcon(blue);
                    }
                }
            }
        });
        
        jchkPlayer5Ready = new JCheckBox("Ready");
        jchkPlayer5Ready.setEnabled(false);
        jchkPlayer5Ready.setMinimumSize(min);
        playerStatusConstraint.weightx = readyWeight;
        playerStatusConstraint.gridx = playerReadyXGrid;
        playerStatusPanel.add(jchkPlayer5Ready, playerStatusConstraint);
        jchkPlayer5Ready.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            }
        });
        
        //row 7
        playerStatusConstraint.gridy = 6;
        
        jlPlayer6Username = new JLabel();
        jlPlayer6Username.setMinimumSize(min);
        playerStatusConstraint.weightx = nameWeight;
        playerStatusConstraint.gridx = usernameXGrid;
        playerStatusPanel.add(jlPlayer6Username, playerStatusConstraint);
        
        jlPlayer6IRLName = new JLabel();
        jlPlayer6IRLName.setMinimumSize(min);
        playerStatusConstraint.gridx = irlNameXGrid;
        playerStatusPanel.add(jlPlayer6IRLName, playerStatusConstraint);
        
        jlPlayer6Color = new JLabel();
        //jlPlayer6Color.setVisible(false);
        jlPlayer6Color.setMinimumSize(min);
        playerStatusConstraint.weightx = colorWeight;
        playerStatusConstraint.gridx = colorXGrid;
        playerStatusPanel.add(jlPlayer6Color, playerStatusConstraint);
        
        jcbPlayer6GameCharacter = new JComboBox(gameCharacters);
        //jcbPlayer6GameCharacter.setVisible(false);
        jcbPlayer6GameCharacter.setSelectedIndex(-1);
        jcbPlayer6GameCharacter.setMinimumSize(min);
        System.out.println(jcbPlayer6GameCharacter.getPreferredSize());
        System.out.println(jcbPlayer6GameCharacter.getSize());
        System.out.println(jcbPlayer6GameCharacter.getMinimumSize());
        playerStatusConstraint.weightx = gameCharWeight;
        playerStatusConstraint.gridx = gameCharXGrid;
        playerStatusPanel.add(jcbPlayer6GameCharacter, playerStatusConstraint);
        jcbPlayer6GameCharacter.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Colonel Mustard")) {
                        jlPlayer6Color.setIcon(yellow);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Miss Scarlet")) {
                        jlPlayer6Color.setIcon(red);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Professor Plum")) {
                        jlPlayer6Color.setIcon(purple);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Mr. Green")) {
                        jlPlayer6Color.setIcon(green);
                    }
                    else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                            .contains("Mrs. White")) {
                        jlPlayer6Color.setIcon(white);
                    }
                    else {
                        jlPlayer6Color.setIcon(blue);
                    }
                }
            }
        });
        
        jchkPlayer6Ready = new JCheckBox("Ready");
        jchkPlayer6Ready.setVisible(false);
        jchkPlayer6Ready.setMinimumSize(min);
        playerStatusConstraint.weightx = readyWeight;
        playerStatusConstraint.gridx = playerReadyXGrid;
        playerStatusPanel.add(jchkPlayer6Ready, playerStatusConstraint);
        jchkPlayer6Ready.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            }
        });
        
        //row 8
        playerStatusConstraint.gridy = 7;
        
        jbStart = new JButton("Start");
        jbStart.setEnabled(false);
        jbStart.setMinimumSize(min);
        playerStatusPanel.add(jbStart, playerStatusConstraint);
        jbStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            }
        });
        
        //
        JPanel chatPanel = new JPanel();
        chatPanel.setBorder(lineBorder);
        Dimension chatWindowPSize =
                new Dimension(
                        (int)componentWidth, (int)(componentHeight * 4.8));
        jtaChatWindow = new JTextArea();
        jtaChatWindow.setPreferredSize(chatWindowPSize);
        jtaChatWindow.setEditable(false);
        jtaChatWindow.setLineWrap(true);
        JScrollPane jspChatWindow = new JScrollPane(jtaChatWindow);
        mainConstraint.anchor = GridBagConstraints.CENTER;
        mainConstraint.gridy = 1;
        mainConstraint.ipady = 140;
        mainConstraint.weighty = 0.25;
        mainConstraint.insets = new Insets(10,0,0,0);
        chatPanel.add(jspChatWindow, BorderLayout.CENTER);
        mainPanel.add(chatPanel, mainConstraint);
        
        //
        JPanel textEntryPanel = new JPanel();
        textEntryPanel.setBorder(lineBorder);
        mainConstraint.anchor = GridBagConstraints.PAGE_END;
        mainConstraint.gridy = 2;
        mainConstraint.ipady = 30;
        JTextField jtfChatEntry = new JTextField(prompt);
        Dimension chatEntryPSize =
                new Dimension(
                        (int)componentWidth, (int)(componentHeight * 0.9));
        jtfChatEntry.setPreferredSize(chatEntryPSize);
        textEntryPanel.add(jtfChatEntry, BorderLayout.WEST);
        mainPanel.add(textEntryPanel, mainConstraint);
        
        frame.add(mainPanel);
    }
    
    /**
     * 
     * @return 
     */
    public JLabel getJLPlayer1Username() {
        return jlPlayer1Username;
    }
    
    /**
     * 
     * @param username 
     */
    public void setJLPlayer1Username(String username) {
        this.jlPlayer1Username.setText(username);
    }
    
    /**
     * 
     * @return 
     */
    public JLabel getJLPlayer1IRLName() {
        return jlPlayer1IRLName;
    }
    
    /**
     * 
     * @param irlName 
     */
    public void setJLPlayer1IRLName(String irlName) {
        this.jlPlayer1IRLName.setText(irlName);
    }
    
    //
    //private final JLabel jlPlayer1Color;
    
    /**
     * 
     * @return 
     */
    public JComboBox getJCBPlayer1GameCharacter() {
        return jcbPlayer1GameCharacter;
    }
    
    /**
     * 
     * @param index 
     */
    public void setJCBPlayer1GameCharacter(int index) {
        this.jcbPlayer1GameCharacter.setSelectedIndex(index);
    }
    
    /**
     * 
     * @return 
     */
    public JCheckBox getJCHKPlayer1Ready() {
        return jchkPlayer1Ready;
    }
    
    /**
     * 
     * @param value 
     */
    public void setJCHKPlayer1Ready(boolean value) {
        jchkPlayer1Ready.setSelected(value);
    }
    
    /**
     * 
     * @param value 
     */
    public void setPlayer1FieldsEn(boolean value) {
        jlPlayer1Username.setEnabled(value);
        jlPlayer1IRLName.setEnabled(value);
        jlPlayer1Color.setEnabled(value);
        jcbPlayer1GameCharacter.setEnabled(value);
        jchkPlayer1Ready.setEnabled(value);
    }
    
    /**
     * 
     * @return 
     */
    public JLabel getJLPlayer2Username() {
        return jlPlayer2Username;
    }
    
    /**
     * 
     * @param username 
     */
    public void setJLPlayer2Username(String username) {
        this.jlPlayer2Username.setText(username);
    }
    
    /**
     * 
     * @return 
     */
    public JLabel getJLPlayer2IRLName() {
        return jlPlayer2IRLName;
    }
    
    /**
     * 
     * @param irlName 
     */
    public void setJLPlayer2IRLName(String irlName) {
        this.jlPlayer2IRLName.setText(irlName);
    }
    
    //
    //private final JLabel jlPlayer2Color;
    
    /**
     * 
     * @return 
     */
    public JComboBox getJCBPlayer2GameCharacter() {
        return jcbPlayer2GameCharacter;
    }
    
    /**
     * 
     * @param index 
     */
    public void setJCBPlayer2GameCharacter(int index) {
        this.jcbPlayer2GameCharacter.setSelectedIndex(index);
    }
    
    /**
     * 
     * @return 
     */
    public JCheckBox getJCHKPlayer2Ready() {
        return jchkPlayer2Ready;
    }
    
    /**
     * 
     * @param value 
     */
    public void setJCHKPlayer2Ready(boolean value) {
        jchkPlayer2Ready.setSelected(value);
    }
    
    /**
     * 
     * @param value 
     */
    public void setPlayer2FieldsEn(boolean value) {
        jlPlayer2Username.setEnabled(value);
        jlPlayer2IRLName.setEnabled(value);
        jlPlayer2Color.setEnabled(value);
        jcbPlayer2GameCharacter.setEnabled(value);
        jchkPlayer2Ready.setEnabled(value);
    }
    
    /**
     * 
     * @return 
     */
    public JLabel getJLPlayer3Username() {
        return jlPlayer3Username;
    }
    
    /**
     * 
     * @param username 
     */
    public void setJLPlayer3Username(String username) {
        this.jlPlayer3Username.setText(username);
    }
    
    /**
     * 
     * @return 
     */
    public JLabel getJLPlayer3IRLName() {
        return jlPlayer3IRLName;
    }
    
    /**
     * 
     * @param irlName 
     */
    public void setJLPlayer3IRLName(String irlName) {
        this.jlPlayer3IRLName.setText(irlName);
    }
    
    //
    //private final JLabel jlPlayer3Color;
    
    /**
     * 
     * @return 
     */
    public JComboBox getJCBPlayer3GameCharacter() {
        return jcbPlayer3GameCharacter;
    }
    
    /**
     * 
     * @param index 
     */
    public void setJCBPlayer3GameCharacter(int index) {
        this.jcbPlayer3GameCharacter.setSelectedIndex(index);
    }
    
    /**
     * 
     * @return 
     */
    public JCheckBox getJCHKPlayer3Ready() {
        return jchkPlayer3Ready;
    }
    
    /**
     * 
     * @param value 
     */
    public void setJCHKPlayer3Ready(boolean value) {
        jchkPlayer3Ready.setSelected(value);
    }
    
    /**
     * 
     * @param value 
     */
    public void setPlayer3FieldsEn(boolean value) {
        jlPlayer3Username.setEnabled(value);
        jlPlayer3IRLName.setEnabled(value);
        jlPlayer3Color.setEnabled(value);
        jcbPlayer3GameCharacter.setEnabled(value);
        jchkPlayer3Ready.setEnabled(value);
    }
    
    /**
     * 
     * @return 
     */
    public JLabel getJLPlayer4Username() {
        return jlPlayer4Username;
    }
    
    /**
     * 
     * @param username 
     */
    public void setJLPlayer4Username(String username) {
        this.jlPlayer4Username.setText(username);
    }
    
    /**
     * 
     * @return 
     */
    public JLabel getJLPlayer4IRLName() {
        return jlPlayer4IRLName;
    }
    
    /**
     * 
     * @param irlName 
     */
    public void setJLPlayer4IRLName(String irlName) {
        this.jlPlayer4IRLName.setText(irlName);
    }
    
    //
    //private final JLabel jlPlayer4Color;
    
    /**
     * 
     * @return 
     */
    public JComboBox getJCBPlayer4GameCharacter() {
        return jcbPlayer4GameCharacter;
    }
    
    /**
     * 
     * @param index 
     */
    public void setJCBPlayer4GameCharacter(int index) {
        this.jcbPlayer4GameCharacter.setSelectedIndex(index);
    }
    
    /**
     * 
     * @return 
     */
    public JCheckBox getJCHKPlayer4Ready() {
        return jchkPlayer4Ready;
    }
    
    /**
     * 
     * @param value 
     */
    public void setJCHKPlayer4Ready(boolean value) {
        jchkPlayer4Ready.setSelected(value);
    }
    
    /**
     * 
     * @param value 
     */
    public void setPlayer4FieldsEn(boolean value) {
        jlPlayer4Username.setEnabled(value);
        jlPlayer4IRLName.setEnabled(value);
        jlPlayer4Color.setEnabled(value);
        jcbPlayer4GameCharacter.setEnabled(value);
        jchkPlayer4Ready.setEnabled(value);
    }
    
    /**
     * 
     * @return 
     */
    public JLabel getJLPlayer5Username() {
        return jlPlayer5Username;
    }
    
    /**
     * 
     * @param username 
     */
    public void setJLPlayer5Username(String username) {
        this.jlPlayer5Username.setText(username);
    }
    
    /**
     * 
     * @return 
     */
    public JLabel getJLPlayer5IRLName() {
        return jlPlayer5IRLName;
    }
    
    /**
     * 
     * @param irlName 
     */
    public void setJLPlayer5IRLName(String irlName) {
        this.jlPlayer5IRLName.setText(irlName);
    }
    
    //
    //private final JLabel jlPlayer5Color;
    
    /**
     * 
     * @return 
     */
    public JComboBox getJCBPlayer5GameCharacter() {
        return jcbPlayer5GameCharacter;
    }
    
    /**
     * 
     * @param index 
     */
    public void setJCBPlayer5GameCharacter(int index) {
        this.jcbPlayer5GameCharacter.setSelectedIndex(index);
    }
    
    /**
     * 
     * @return 
     */
    public JCheckBox getJCHKPlayer5Ready() {
        return jchkPlayer5Ready;
    }
    
    /**
     * 
     * @param value 
     */
    public void setJCHKPlayer5Ready(boolean value) {
        jchkPlayer5Ready.setSelected(value);
    }
    
    /**
     * 
     * @param value 
     */
    public void setPlayer5FieldsEn(boolean value) {
        jlPlayer5Username.setEnabled(value);
        jlPlayer5IRLName.setEnabled(value);
        jlPlayer5Color.setEnabled(value);
        jcbPlayer5GameCharacter.setEnabled(value);
        jchkPlayer5Ready.setEnabled(value);
    }
    
    /**
     * 
     * @return 
     */
    public JLabel getJLPlayer6Username() {
        return jlPlayer6Username;
    }
    
    /**
     * 
     * @param username 
     */
    public void setJLPlayer6Username(String username) {
        this.jlPlayer6Username.setText(username);
    }
    
    /**
     * 
     * @return 
     */
    public JLabel getJLPlayer6IRLName() {
        return jlPlayer6IRLName;
    }
    
    /**
     * 
     * @param irlName 
     */
    public void setJLPlayer6IRLName(String irlName) {
        this.jlPlayer6IRLName.setText(irlName);
    }
    
    //
    //private final JLabel jlPlayer6Color;
    
    /**
     * 
     * @return 
     */
    public JComboBox getJCBPlayer6GameCharacter() {
        return jcbPlayer6GameCharacter;
    }
    
    /**
     * 
     * @param index 
     */
    public void setJCBPlayer6GameCharacter(int index) {
        this.jcbPlayer6GameCharacter.setSelectedIndex(index);
    }
    
    /**
     * 
     * @return 
     */
    public JCheckBox getJCHKPlayer6Ready() {
        return jchkPlayer6Ready;
    }
    
    /**
     * 
     * @param value 
     */
    public void setJCHKPlayer6Ready(boolean value) {
        jchkPlayer6Ready.setSelected(value);
    }
    
    /**
     * 
     * @param value 
     */
    public void setPlayer6FieldsVis(boolean value) {
        jlPlayer6Username.setText("");
        jlPlayer6IRLName.setText("");
        jlPlayer6Color.setVisible(value);
        jcbPlayer6GameCharacter.setVisible(value);
        jchkPlayer6Ready.setVisible(value);
    }
    
    /**
     * 
     * @return 
     */
    public JButton getJBStart() {
        return jbStart;
    }
    
    /**
     * 
     * @param value 
     */
    public void setJBStart(boolean value) {
        jbStart.setEnabled(value);
    }
    
    /**
     * 
     * @param text 
     */
    public void appendChat(String text){
        jtaChatWindow.append(text);
    }
    
    public void refresh() {
        this.revalidate();
        this.repaint();
    }
}
