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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * 
 */
public final class Lobby extends JPanel implements ReturnStringParser {
    
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
    private final JLabel jlPlayer6Username;
    
    //
    private final JLabel jlPlayer6IRLName;
    
    //
    private final JLabel jlPlayer6Color;
    
    //
    private final JComboBox jcbPlayer6GameCharacter;
    
    //
    private JCheckBox jchkPlayer6Ready;
    
    //
    private JButton jbStart;
    
    private final JPanel jpPlayer1Username;
    
    private final JPanel jpPlayer1IRLName;
    
    private final JPanel jpPlayer1Color;
    
    private final JPanel jpPlayer1GameChar;
    
    private final JPanel jpPlayer1Ready;
    
    private final JPanel jpPlayer2Username;
    
    private final JPanel jpPlayer2IRLName;
    
    private final JPanel jpPlayer2Color;
    
    private final JPanel jpPlayer2GameChar;
    
    private final JPanel jpPlayer2Ready;
    
    private final JPanel jpPlayer3Username;
    
    private final JPanel jpPlayer3IRLName;
    
    private final JPanel jpPlayer3Color;
    
    private final JPanel jpPlayer3GameChar;
    
    private final JPanel jpPlayer3Ready;
    
    private final JPanel jpPlayer4Username;
    
    private JPanel jpPlayer4IRLName;
    
    private JPanel jpPlayer4Color;
    
    private JPanel jpPlayer4GameChar;
    
    private JPanel jpPlayer4Ready;
    
    private JPanel jpPlayer5Username;
    
    private JPanel jpPlayer5IRLName;
    
    private JPanel jpPlayer5Color;
    
    private JPanel jpPlayer5GameChar;
    
    private JPanel jpPlayer5Ready;
    
    private JPanel jpPlayer6Username;
    
    private JPanel jpPlayer6IRLName;
    
    private JPanel jpPlayer6Color;
    
    private JPanel jpPlayer6GameChar;
    
    private JPanel jpPlayer6Ready;
    
    private CardLayout cl;
    
    private SimpleDateFormat df = new SimpleDateFormat("hh:mm");
    
    private Player player;
    
    /**
     * 
     * @param frame
     * @param player 
     */
    public Lobby(JFrame frame, Player player) {
        
        this.player = player;
        prompt = "[" + player.getUsername() + "]: ";
        Socket socket = player.getSocket();
        try {
            toServer = new DataOutputStream(socket.getOutputStream());
            fromServer = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        
        // 1. The image files must be under the src folder for this to work
        // 2. also, clean & build if that doesnt work.
        final ImageIcon yellow = new ImageIcon(
                getClass().getResource("/yellow.jpg"));
        final ImageIcon red = new ImageIcon(
                getClass().getResource("/red.jpg"));
        final ImageIcon purple = new ImageIcon(
                getClass().getResource("/purple.jpg"));
        final ImageIcon green = new ImageIcon(
                getClass().getResource("/green.jpg"));
        final ImageIcon white = new ImageIcon(
                getClass().getResource("/white.jpg"));
        final ImageIcon blue = new ImageIcon(
                getClass().getResource("/blue.jpg"));
                
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
        
        JLabel jlIRLName = new JLabel("Detective Level");
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
        
        jpPlayer1Username = new JPanel(new CardLayout());
        JLabel jlPlayer1UsernameInvis = new JLabel();
        jlPlayer1UsernameInvis.setMinimumSize(min);
        jpPlayer1Username.add(jlPlayer1UsernameInvis, "invisible");
        jlPlayer1Username = new JLabel("Player1");
        jlPlayer1Username.setMinimumSize(min);
        jpPlayer1Username.add(jlPlayer1Username, "visible");
        playerStatusConstraint.weightx = nameWeight;
        playerStatusConstraint.gridx = usernameXGrid;
        playerStatusPanel.add(jpPlayer1Username, playerStatusConstraint);
        
        jpPlayer1IRLName = new JPanel(new CardLayout());
        JLabel jlPlayer1IRLNameInvis = new JLabel();
        jlPlayer1IRLNameInvis.setMinimumSize(min);
        jpPlayer1IRLName.add(jlPlayer1IRLNameInvis, "invisible");
        jlPlayer1IRLName = new JLabel("name");
        jlPlayer1IRLName.setMinimumSize(min);
        jpPlayer1IRLName.add(jlPlayer1IRLName, "visible");
        playerStatusConstraint.gridx = irlNameXGrid;
        playerStatusPanel.add(jpPlayer1IRLName, playerStatusConstraint);
        
        jpPlayer1Color = new JPanel(new CardLayout());
        JLabel jlPlayer1ColorInvis = new JLabel();
        jlPlayer1ColorInvis.setMinimumSize(min);
        jpPlayer1Color.add(jlPlayer1ColorInvis, "invisible");
        jlPlayer1Color = new JLabel();
        jlPlayer1Color.setMinimumSize(min);
        jpPlayer1Color.add(jlPlayer1Color, "visible");
        playerStatusConstraint.weightx = colorWeight;
        playerStatusConstraint.gridx = colorXGrid;
        playerStatusPanel.add(jpPlayer1Color, playerStatusConstraint);
        
        jpPlayer1GameChar = new JPanel(new CardLayout());
        JLabel jlPlayer1GameCharInvis = new JLabel();
        jlPlayer1GameCharInvis.setMinimumSize(min);
        jpPlayer1GameChar.add(jlPlayer1GameCharInvis, "invisible");
        jcbPlayer1GameCharacter = new JComboBox(gameCharacters);
        jcbPlayer1GameCharacter.setMinimumSize(min);
        jcbPlayer1GameCharacter.setEnabled(false);
        jpPlayer1GameChar.add(jcbPlayer1GameCharacter, "visible");
        jcbPlayer1GameCharacter.setSelectedIndex(-1);
        playerStatusConstraint.weightx = gameCharWeight;
        playerStatusConstraint.gridx = gameCharXGrid;
        playerStatusPanel.add(jpPlayer1GameChar, playerStatusConstraint);
        jcbPlayer1GameCharacter.addItemListener((ItemEvent ie) -> {
            int num = -1;
            String name = "";
            Color color = Color.BLACK;
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Colonel Mustard")) {
                    jlPlayer1Color.setIcon(yellow);
                    color = Color.yellow;
                    num = 0;
                    name = "Colonel Mustard";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Miss Scarlet")) {
                    jlPlayer1Color.setIcon(red);
                    color = Color.red;
                    num = 1;
                    name = "Miss Scarlet";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Professor Plum")) {
                    jlPlayer1Color.setIcon(purple);
                    color = new Color(255,0,255);
                    num = 2;
                    name = "Professor Plum";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Mr. Green")) {
                    jlPlayer1Color.setIcon(green);
                    color = Color.green;
                    num = 3;
                    name = "Mr. Green";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Mrs. White")) {
                    jlPlayer1Color.setIcon(white);
                    color = Color.white;
                    num = 4;
                    name = "Mrs. White";
                }
                else {
                    jlPlayer1Color.setIcon(blue);
                    color = Color.blue;
                    num = 5;
                    name = "Mrs. Peacock";
                }
                if (player.getPlayerNum() == 1) {
                    player.setColor(color);
                    player.setCharNum(num);
                    player.setCharacterName(name);

                    try {
                        toServer.writeUTF("[all][" + player.getPlayerNum() 
                            + "][set][char][" + player.getCharNum() + "]");
                    } catch (IOException ex) {
                        Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        jpPlayer1Ready = new JPanel(new CardLayout());
        JLabel jlPlayer1ReadyInvis = new JLabel();
        jlPlayer1ReadyInvis.setMinimumSize(min);
        jpPlayer1Ready.add(jlPlayer1ReadyInvis, "invisible");
        jchkPlayer1Ready = new JCheckBox("Ready");
        jchkPlayer1Ready.setMinimumSize(min);
        jchkPlayer1Ready.setEnabled(false);
        jpPlayer1Ready.add(jchkPlayer1Ready, "visible");
        playerStatusConstraint.weightx = readyWeight;
        playerStatusConstraint.gridx = playerReadyXGrid;
        playerStatusPanel.add(jpPlayer1Ready, playerStatusConstraint);
        jchkPlayer1Ready.addActionListener((ActionEvent ae) -> {
            try{
                if(jchkPlayer1Ready.isSelected()) {
                    player.setReady("check");
                    toServer.writeUTF("[all][1][set][ready][check]");
                }
                else {
                    player.setReady("uncheck");
                    toServer.writeUTF("[all][1][set][ready][uncheck]");
                }
            } catch(IOException ex){
                Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        jchkPlayer1Ready.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("Player1Ready changed!");
            }
        });
                
        //row3
        playerStatusConstraint.gridy = 2;
        
        jpPlayer2Username = new JPanel(new CardLayout());
        JLabel jlPlayer2UsernameInvis = new JLabel();
        jlPlayer2UsernameInvis.setMinimumSize(min);
        jpPlayer2Username.add(jlPlayer2UsernameInvis, "invisible");
        jlPlayer2Username = new JLabel("Player2");
        jlPlayer2Username.setMinimumSize(min);
        jpPlayer2Username.add(jlPlayer2Username, "visible");
        playerStatusConstraint.weightx = nameWeight;
        playerStatusConstraint.gridx = usernameXGrid;
        playerStatusPanel.add(jpPlayer2Username, playerStatusConstraint);
        
        jpPlayer2IRLName = new JPanel(new CardLayout());
        JLabel jlPlayer2IRLNameInvis = new JLabel();
        jlPlayer2IRLNameInvis.setMinimumSize(min);
        jpPlayer2IRLName.add(jlPlayer2IRLNameInvis, "invisible");
        jlPlayer2IRLName = new JLabel("name");
        jlPlayer2IRLName.setMinimumSize(min);
        jpPlayer2IRLName.add(jlPlayer2IRLName, "visible");
        playerStatusConstraint.gridx = irlNameXGrid;
        playerStatusPanel.add(jpPlayer2IRLName, playerStatusConstraint);
        
        jpPlayer2Color = new JPanel(new CardLayout());
        JLabel jlPlayer2ColorInvis = new JLabel();
        jlPlayer2ColorInvis.setMinimumSize(min);
        jpPlayer2Color.add(jlPlayer2ColorInvis, "invisible");
        jlPlayer2Color = new JLabel();
        jlPlayer2Color.setMinimumSize(min);
        jpPlayer2Color.add(jlPlayer2Color, "visible");
        playerStatusConstraint.weightx = colorWeight;
        playerStatusConstraint.gridx = colorXGrid;
        playerStatusPanel.add(jpPlayer2Color, playerStatusConstraint);
        
        jpPlayer2GameChar = new JPanel(new CardLayout());
        JLabel jlPlayer2GameCharInvis = new JLabel();
        jlPlayer2GameCharInvis.setMinimumSize(min);
        jpPlayer2GameChar.add(jlPlayer2GameCharInvis, "invisible");
        jcbPlayer2GameCharacter = new JComboBox(gameCharacters);
        jcbPlayer2GameCharacter.setSelectedIndex(-1);
        jcbPlayer2GameCharacter.setMinimumSize(min);
        jcbPlayer2GameCharacter.setEnabled(false);
        jpPlayer2GameChar.add(jcbPlayer2GameCharacter, "visible");
        playerStatusConstraint.weightx = gameCharWeight;
        playerStatusConstraint.gridx = gameCharXGrid;
        playerStatusPanel.add(jpPlayer2GameChar, playerStatusConstraint);
        jcbPlayer2GameCharacter.addItemListener((ItemEvent ie) -> {
            Color color = Color.black;
            int num = -1;
            String name = "";
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Colonel Mustard")) {
                    jlPlayer2Color.setIcon(yellow);
                    color = Color.yellow;
                    num = 0;
                    name = "Colonel Mustard";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Miss Scarlet")) {
                    jlPlayer2Color.setIcon(red);
                    color = Color.red;
                    num = 1;
                    name = "Miss Scarlet";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Professor Plum")) {
                    jlPlayer2Color.setIcon(purple);
                    color = new Color(255,0,255);
                    num = 2;
                    name = "Professor Plum";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Mr. Green")) {
                    jlPlayer2Color.setIcon(green);
                    color = Color.green;
                    num = 3;
                    name = "Mr. Green";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Mrs. White")) {
                    jlPlayer2Color.setIcon(white);
                    color = Color.white;
                    num = 4;
                    name = "Mrs. White";
                }
                else {
                    jlPlayer2Color.setIcon(blue);
                    color = Color.blue;
                    num = 5;
                    name = "Mrs. Peacock";
                }
                if (player.getPlayerNum() == 2) {
                    player.setColor(color);
                    player.setCharNum(num);
                    player.setCharacterName(name);

                    try {
                        toServer.writeUTF("[all][" + player.getPlayerNum() 
                            + "][set][char][" + player.getCharNum() + "]");
                    } catch (IOException ex) {
                        Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        jpPlayer2Ready = new JPanel(new CardLayout());
        JLabel jlPlayer2ReadyInvis = new JLabel();
        jlPlayer2ReadyInvis.setMinimumSize(min);
        jpPlayer2Ready.add(jlPlayer2ReadyInvis, "invisible");
        jchkPlayer2Ready = new JCheckBox("Ready");
        jchkPlayer2Ready.setMinimumSize(min);
        jchkPlayer2Ready.setEnabled(false);
        jpPlayer2Ready.add(jchkPlayer2Ready, "visible");
        playerStatusConstraint.weightx = readyWeight;
        playerStatusConstraint.gridx = playerReadyXGrid;
        playerStatusPanel.add(jpPlayer2Ready, playerStatusConstraint);
        jchkPlayer2Ready.addActionListener((ActionEvent ae) -> {
            try{
                if(jchkPlayer2Ready.isSelected()) {
                    player.setReady("check");
                    toServer.writeUTF("[all][2][set][ready][check]");
                }
                else {
                    player.setReady("uncheck");
                    toServer.writeUTF("[all][2][set][ready][uncheck]");
                }
            } catch(IOException ex){
                Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        jchkPlayer2Ready.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("Player2Ready changed!");
            }
        });
        
        //row4
        playerStatusConstraint.gridy = 3;
        
        jpPlayer3Username = new JPanel(new CardLayout());
        JLabel jlPlayer3UsernameInvis = new JLabel();
        jlPlayer3UsernameInvis.setMinimumSize(min);
        jpPlayer3Username.add(jlPlayer3UsernameInvis, "invisible");
        jlPlayer3Username = new JLabel("Player3");
        jlPlayer3Username.setMinimumSize(min);
        jpPlayer3Username.add(jlPlayer3Username, "visible");
        playerStatusConstraint.weightx = nameWeight;
        playerStatusConstraint.gridx = usernameXGrid;
        playerStatusPanel.add(jpPlayer3Username, playerStatusConstraint);
        
        jpPlayer3IRLName = new JPanel(new CardLayout());
        JLabel jlPlayer3IRLNameInvis = new JLabel();
        jlPlayer3IRLNameInvis.setMinimumSize(min);
        jpPlayer3IRLName.add(jlPlayer3IRLNameInvis, "invisible");
        jlPlayer3IRLName = new JLabel("name");
        jlPlayer3IRLName.setMinimumSize(min);
        jpPlayer3IRLName.add(jlPlayer3IRLName, "visible");
        playerStatusConstraint.gridx = irlNameXGrid;
        playerStatusPanel.add(jpPlayer3IRLName, playerStatusConstraint);
        
        jpPlayer3Color = new JPanel(new CardLayout());
        JLabel jlPlayer3ColorInvis = new JLabel();
        jlPlayer3ColorInvis.setMinimumSize(min);
        jpPlayer3Color.add(jlPlayer3ColorInvis, "invisible");
        jlPlayer3Color = new JLabel();
        jlPlayer3Color.setMinimumSize(min);
        jpPlayer3Color.add(jlPlayer3Color, "visible");
        playerStatusConstraint.weightx = colorWeight;
        playerStatusConstraint.gridx = colorXGrid;
        playerStatusPanel.add(jpPlayer3Color, playerStatusConstraint);
        
        jpPlayer3GameChar = new JPanel(new CardLayout());
        JLabel jlPlayer3GameCharInvis = new JLabel();
        jlPlayer3GameCharInvis.setMinimumSize(min);
        jpPlayer3GameChar.add(jlPlayer3GameCharInvis, "invisible");
        jcbPlayer3GameCharacter = new JComboBox(gameCharacters);
        jcbPlayer3GameCharacter.setSelectedIndex(-1);
        jcbPlayer3GameCharacter.setMinimumSize(min);
        jcbPlayer3GameCharacter.setEnabled(false);
        jpPlayer3GameChar.add(jcbPlayer3GameCharacter, "visible");
        playerStatusConstraint.weightx = gameCharWeight;
        playerStatusConstraint.gridx = gameCharXGrid;
        playerStatusPanel.add(jpPlayer3GameChar, playerStatusConstraint);
        jcbPlayer3GameCharacter.addItemListener((ItemEvent ie) -> {
            Color color = Color.black;
            int num = -1;
            String name = "";
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Colonel Mustard")) {
                    jlPlayer3Color.setIcon(yellow);
                    color = Color.yellow;
                    num = 0;
                    name = "Colonel Mustard";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Miss Scarlet")) {
                    jlPlayer3Color.setIcon(red);
                    color = Color.red;
                    num = 1;
                    name = "Miss Scarlet";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Professor Plum")) {
                    jlPlayer3Color.setIcon(purple);
                    color = new Color(255,0,255);
                    num = 2;
                    name = "Professor Plum";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Mr. Green")) {
                    jlPlayer3Color.setIcon(green);
                    color = Color.green;
                    num = 3;
                    name = "Mr. Green";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Mrs. White")) {
                    jlPlayer3Color.setIcon(white);
                    color = Color.white;
                    num = 4;
                    name = "Mrs. White";
                }
                else {
                    jlPlayer3Color.setIcon(blue);
                    color = Color.blue;
                    num = 5;
                    name = "Mrs. Peacock";
                }
                if (player.getPlayerNum() == 3) {
                    player.setColor(color);
                    player.setCharNum(num);
                    player.setCharacterName(name);

                    try {
                        toServer.writeUTF("[all][" + player.getPlayerNum() 
                            + "][set][char][" + player.getCharNum() + "]");
                    } catch (IOException ex) {
                        Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        jpPlayer3Ready = new JPanel(new CardLayout());
        JLabel jlPlayer3ReadyInvis = new JLabel();
        jlPlayer3ReadyInvis.setMinimumSize(min);
        jpPlayer3Ready.add(jlPlayer3ReadyInvis, "invisible");
        jchkPlayer3Ready = new JCheckBox("Ready");
        jchkPlayer3Ready.setMinimumSize(min);
        jchkPlayer3Ready.setEnabled(false);
        jpPlayer3Ready.add(jchkPlayer3Ready, "visible");
        playerStatusConstraint.weightx = readyWeight;
        playerStatusConstraint.gridx = playerReadyXGrid;
        playerStatusPanel.add(jpPlayer3Ready, playerStatusConstraint);
        jchkPlayer3Ready.addActionListener((ActionEvent ae) -> {
            try{
                if(jchkPlayer3Ready.isSelected()) {
                    player.setReady("check");
                    toServer.writeUTF("[all][3][set][ready][check]");
                }
                else {
                    player.setReady("uncheck");
                    toServer.writeUTF("[all][3][set][ready][uncheck]");
                }
            } catch(IOException ex){
                Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        jchkPlayer3Ready.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("Player3Ready changed!");
            }
        });
        
        //row 5
        playerStatusConstraint.gridy = 4;
        
        jpPlayer4Username = new JPanel(new CardLayout());
        JLabel jlPlayer4UsernameInvis = new JLabel();
        jlPlayer4UsernameInvis.setMinimumSize(min);
        jpPlayer4Username.add(jlPlayer4UsernameInvis, "invisible");
        jlPlayer4Username = new JLabel("Player4");
        jlPlayer4Username.setMinimumSize(min);
        jpPlayer4Username.add(jlPlayer4Username, "visible");
        playerStatusConstraint.weightx = nameWeight;
        playerStatusConstraint.gridx = usernameXGrid;
        playerStatusPanel.add(jpPlayer4Username, playerStatusConstraint);
        
        jpPlayer4IRLName = new JPanel(new CardLayout());
        JLabel jlPlayer4IRLNameInvis = new JLabel();
        jlPlayer4IRLNameInvis.setMinimumSize(min);
        jpPlayer4IRLName.add(jlPlayer4IRLNameInvis, "invisible");
        jlPlayer4IRLName = new JLabel("name");
        jlPlayer4IRLName.setMinimumSize(min);
        jpPlayer4IRLName.add(jlPlayer4IRLName, "visible");
        playerStatusConstraint.gridx = irlNameXGrid;
        playerStatusPanel.add(jpPlayer4IRLName, playerStatusConstraint);
        
        jpPlayer4Color = new JPanel(new CardLayout());
        JLabel jlPlayer4ColorInvis = new JLabel();
        jlPlayer4ColorInvis.setMinimumSize(min);
        jpPlayer4Color.add(jlPlayer4ColorInvis, "invisible");
        jlPlayer4Color = new JLabel();
        jlPlayer4Color.setMinimumSize(min);
        jpPlayer4Color.add(jlPlayer4Color, "visible");
        playerStatusConstraint.weightx = colorWeight;
        playerStatusConstraint.gridx = colorXGrid;
        playerStatusPanel.add(jpPlayer4Color, playerStatusConstraint);
        
        jpPlayer4GameChar = new JPanel(new CardLayout());
        JLabel jlPlayer4GameCharInvis = new JLabel();
        jlPlayer4GameCharInvis.setMinimumSize(min);
        jpPlayer4GameChar.add(jlPlayer4GameCharInvis, "invisible");
        jcbPlayer4GameCharacter = new JComboBox(gameCharacters);
        jcbPlayer4GameCharacter.setSelectedIndex(-1);
        jcbPlayer4GameCharacter.setMinimumSize(min);
        jcbPlayer4GameCharacter.setEnabled(false);
        jpPlayer4GameChar.add(jcbPlayer4GameCharacter, "visible");
        playerStatusConstraint.weightx = gameCharWeight;
        playerStatusConstraint.gridx = gameCharXGrid;
        playerStatusPanel.add(jpPlayer4GameChar, playerStatusConstraint);
        jcbPlayer4GameCharacter.addItemListener((ItemEvent ie) -> {
            Color color = Color.black;
            int num = -1;
            String name = "";
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Colonel Mustard")) {
                    jlPlayer4Color.setIcon(yellow);
                    color = Color.yellow;
                    num = 0;
                    name = "Colonel Mustard";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Miss Scarlet")) {
                    jlPlayer4Color.setIcon(red);
                    color = Color.red;
                    num = 1;
                    name = "Miss Scarlet";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Professor Plum")) {
                    jlPlayer4Color.setIcon(purple);
                    color = new Color(255,0,255);
                    num = 2;
                    name = "Professor Plum";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Mr. Green")) {
                    jlPlayer4Color.setIcon(green);
                    color = Color.green;
                    num = 3;
                    name = "Mr. Green";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Mrs. White")) {
                    jlPlayer4Color.setIcon(white);
                    color = Color.white;
                    num = 4;
                    name = "Mrs. White";
                }
                else {
                    jlPlayer4Color.setIcon(blue);
                    color = Color.blue;
                    num = 5;
                    name = "Mrs. Peacock";
                }
                if (player.getPlayerNum() == 4) {
                    player.setColor(color);
                    player.setCharNum(num);
                    player.setCharacterName(name);

                    try {
                        toServer.writeUTF("[all][" + player.getPlayerNum() 
                            + "][set][char][" + player.getCharNum() + "]");
                    } catch (IOException ex) {
                        Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        jpPlayer4Ready = new JPanel(new CardLayout());
        JLabel jlPlayer4ReadyInvis = new JLabel();
        jlPlayer4ReadyInvis.setMinimumSize(min);
        jpPlayer4Ready.add(jlPlayer4ReadyInvis, "invisible");
        jchkPlayer4Ready = new JCheckBox("Ready");
        jchkPlayer4Ready.setMinimumSize(min);
        jchkPlayer4Ready.setEnabled(false);
        jpPlayer4Ready.add(jchkPlayer4Ready, "visible");
        playerStatusConstraint.weightx = readyWeight;
        playerStatusConstraint.gridx = playerReadyXGrid;
        playerStatusPanel.add(jpPlayer4Ready, playerStatusConstraint);
        jchkPlayer4Ready.addActionListener((ActionEvent ae) -> {
            try{
                if(jchkPlayer4Ready.isSelected()) {
                    player.setReady("check");
                    toServer.writeUTF("[all][4][set][ready][check]");
                }
                else {
                    player.setReady("uncheck");
                    toServer.writeUTF("[all][4][set][ready][uncheck]");
                }
            } catch(IOException ex){
                Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        jchkPlayer4Ready.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("Player4Ready changed!");
            }
        });
        
        //row 6
        playerStatusConstraint.gridy = 5;
        
        jpPlayer5Username = new JPanel(new CardLayout());
        JLabel jlPlayer5UsernameInvis = new JLabel();
        jlPlayer5UsernameInvis.setMinimumSize(min);
        jpPlayer5Username.add(jlPlayer5UsernameInvis, "invisible");
        jlPlayer5Username = new JLabel("Player5");
        jlPlayer5Username.setMinimumSize(min);
        jpPlayer5Username.add(jlPlayer5Username, "visible");
        playerStatusConstraint.weightx = nameWeight;
        playerStatusConstraint.gridx = usernameXGrid;
        playerStatusPanel.add(jpPlayer5Username, playerStatusConstraint);
        
        jpPlayer5IRLName = new JPanel(new CardLayout());
        JLabel jlPlayer5IRLNameInvis = new JLabel();
        jlPlayer5IRLNameInvis.setMinimumSize(min);
        jpPlayer5IRLName.add(jlPlayer5IRLNameInvis, "invisible");
        jlPlayer5IRLName = new JLabel("name");
        jlPlayer5IRLName.setMinimumSize(min);
        jpPlayer5IRLName.add(jlPlayer5IRLName, "visible");
        playerStatusConstraint.gridx = irlNameXGrid;
        playerStatusPanel.add(jpPlayer5IRLName, playerStatusConstraint);
        
        jpPlayer5Color = new JPanel(new CardLayout());
        JLabel jlPlayer5ColorInvis = new JLabel();
        jlPlayer5ColorInvis.setMinimumSize(min);
        jpPlayer5Color.add(jlPlayer5ColorInvis, "invisible");
        jlPlayer5Color = new JLabel();
        jlPlayer5Color.setMinimumSize(min);
        jpPlayer5Color.add(jlPlayer5Color, "visible");
        playerStatusConstraint.weightx = colorWeight;
        playerStatusConstraint.gridx = colorXGrid;
        playerStatusPanel.add(jpPlayer5Color, playerStatusConstraint);
        
        jpPlayer5GameChar = new JPanel(new CardLayout());
        JLabel jlPlayer5GameCharInvis = new JLabel();
        jlPlayer5GameCharInvis.setMinimumSize(min);
        jpPlayer5GameChar.add(jlPlayer5GameCharInvis, "invisible");
        jcbPlayer5GameCharacter = new JComboBox(gameCharacters);
        jcbPlayer5GameCharacter.setSelectedIndex(-1);
        jcbPlayer5GameCharacter.setMinimumSize(min);
        jcbPlayer5GameCharacter.setEnabled(false);
        jpPlayer5GameChar.add(jcbPlayer5GameCharacter, "visible");
        playerStatusConstraint.weightx = gameCharWeight;
        playerStatusConstraint.gridx = gameCharXGrid;
        playerStatusPanel.add(jpPlayer5GameChar, playerStatusConstraint);
        jcbPlayer5GameCharacter.addItemListener((ItemEvent ie) -> {
            Color color = Color.black;
            int num = -1;
            String name = "";
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Colonel Mustard")) {
                    jlPlayer5Color.setIcon(yellow);
                    color = Color.yellow;
                    num = 0;
                    name = "Colonel Mustard";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Miss Scarlet")) {
                    jlPlayer5Color.setIcon(red);
                    color = Color.red;
                    num = 1;
                    name = "Miss Scarlet";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Professor Plum")) {
                    jlPlayer5Color.setIcon(purple);
                    color = new Color(255, 0, 255);
                    num = 2;
                    name = "Professor Plum";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Mr. Green")) {
                    jlPlayer5Color.setIcon(green);
                    color = Color.green;
                    num = 3;
                    name = "Mr. Green";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Mrs. White")) {
                    jlPlayer5Color.setIcon(white);
                    color = Color.white;
                    num = 4;
                    name = "Mrs. White";
                }
                else {
                    jlPlayer5Color.setIcon(blue);
                    color = Color.blue;
                    num = 5;
                    name = "Mrs. Peacock";
                }
                if (player.getPlayerNum() == 5) {
                    player.setColor(color);
                    player.setCharNum(num);
                    player.setCharacterName(name);

                    try {
                        toServer.writeUTF("[all][" + player.getPlayerNum() 
                            + "][set][char][" + player.getCharNum() + "]");
                    } catch (IOException ex) {
                        Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        jpPlayer5Ready = new JPanel(new CardLayout());
        JLabel jlPlayer5ReadyInvis = new JLabel();
        jlPlayer5ReadyInvis.setMinimumSize(min);
        jpPlayer5Ready.add(jlPlayer5ReadyInvis, "invisible");
        jchkPlayer5Ready = new JCheckBox("Ready");
        jchkPlayer5Ready.setMinimumSize(min);
        jchkPlayer5Ready.setEnabled(false);
        jpPlayer5Ready.add(jchkPlayer5Ready, "visible");
        playerStatusConstraint.weightx = readyWeight;
        playerStatusConstraint.gridx = playerReadyXGrid;
        playerStatusPanel.add(jpPlayer5Ready, playerStatusConstraint);
        jchkPlayer5Ready.addActionListener((ActionEvent ae) -> {
            try{
                if(jchkPlayer5Ready.isSelected()) {
                    player.setReady("check");
                    toServer.writeUTF("[all][5][set][ready][check]");
                }
                else {
                    player.setReady("uncheck");
                    toServer.writeUTF("[all][5][set][ready][uncheck]");
                }
            } catch(IOException ex){
                Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        jchkPlayer5Ready.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("Player5Ready changed!");
            }
        });
        
        //row 7
        playerStatusConstraint.gridy = 6;
        
        jpPlayer6Username = new JPanel(new CardLayout());
        JLabel jlPlayer6UsernameInvis = new JLabel();
        jlPlayer6UsernameInvis.setMinimumSize(min);
        jpPlayer6Username.add(jlPlayer6UsernameInvis, "invisible");
        jlPlayer6Username = new JLabel("Player6");
        jlPlayer6Username.setMinimumSize(min);
        jpPlayer6Username.add(jlPlayer6Username, "visible");
        playerStatusConstraint.weightx = nameWeight;
        playerStatusConstraint.gridx = usernameXGrid;
        playerStatusPanel.add(jpPlayer6Username, playerStatusConstraint);
        
        jpPlayer6IRLName = new JPanel(new CardLayout());
        JLabel jlPlayer6IRLNameInvis = new JLabel();
        jlPlayer6IRLNameInvis.setMinimumSize(min);
        jpPlayer6IRLName.add(jlPlayer6IRLNameInvis, "invisible");
        jlPlayer6IRLName = new JLabel("name");
        jlPlayer6IRLName.setMinimumSize(min);
        jpPlayer6IRLName.add(jlPlayer6IRLName, "visible");
        playerStatusConstraint.gridx = irlNameXGrid;
        playerStatusPanel.add(jpPlayer6IRLName, playerStatusConstraint);
        
        jpPlayer6Color = new JPanel(new CardLayout());
        JLabel jlPlayer6ColorInvis = new JLabel();
        jlPlayer6ColorInvis.setMinimumSize(min);
        jpPlayer6Color.add(jlPlayer6ColorInvis, "invisible");
        jlPlayer6Color = new JLabel();
        jlPlayer6Color.setMinimumSize(min);
        jpPlayer6Color.add(jlPlayer6Color, "visible");
        playerStatusConstraint.weightx = colorWeight;
        playerStatusConstraint.gridx = colorXGrid;
        playerStatusPanel.add(jpPlayer6Color, playerStatusConstraint);
        
        jpPlayer6GameChar = new JPanel(new CardLayout());
        JLabel jlPlayer6GameCharInvis = new JLabel();
        jlPlayer6GameCharInvis.setMinimumSize(min);
        jpPlayer6GameChar.add(jlPlayer6GameCharInvis, "invisible");
        jcbPlayer6GameCharacter = new JComboBox(gameCharacters);
        jcbPlayer6GameCharacter.setSelectedIndex(-1);
        jcbPlayer6GameCharacter.setMinimumSize(min);
        jcbPlayer6GameCharacter.setEnabled(false);
        jpPlayer6GameChar.add(jcbPlayer6GameCharacter, "visible");
        playerStatusConstraint.weightx = gameCharWeight;
        playerStatusConstraint.gridx = gameCharXGrid;
        playerStatusPanel.add(jpPlayer6GameChar, playerStatusConstraint);
        jcbPlayer6GameCharacter.addItemListener((ItemEvent ie) -> {
            Color color = Color.black;
            int num = -1;
            String name = "";
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Colonel Mustard")) {
                    jlPlayer6Color.setIcon(yellow);
                    color = Color.yellow;
                    num = 0;
                    name = "Colonel Mustard";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Miss Scarlet")) {
                    jlPlayer6Color.setIcon(red);
                    color = Color.red;
                    num = 1;
                    name = "Miss Scarlet";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Professor Plum")) {
                    jlPlayer6Color.setIcon(purple);
                    color = new Color(255,0,255);
                    num = 2;
                    name = "Professor Plum";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Mr. Green")) {
                    jlPlayer6Color.setIcon(green);
                    color = Color.green;
                    num = 3;
                    name = "Mr. Green";
                }
                else if (Arrays.toString(
                        ie.getItemSelectable().getSelectedObjects())
                        .contains("Mrs. White")) {
                    jlPlayer6Color.setIcon(white);
                    color = Color.white;
                    num = 4;
                    name = "Mrs. White";
                }
                else {
                    jlPlayer6Color.setIcon(blue);
                    color = Color.blue;
                    num = 5;
                    name = "Mrs. Peacock";
                }
                if (player.getPlayerNum() == 6) {
                    player.setColor(color);
                    player.setCharNum(num);
                    player.setCharacterName(name);

                    try {
                        toServer.writeUTF("[all][" + player.getPlayerNum() 
                            + "][set][char][" + player.getCharNum() + "]");
                    } catch (IOException ex) {
                        Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        jpPlayer6Ready = new JPanel(new CardLayout());
        JLabel jlPlayer6ReadyInvis = new JLabel();
        jlPlayer6ReadyInvis.setMinimumSize(min);
        jpPlayer6Ready.add(jlPlayer6ReadyInvis, "invisible");
        jchkPlayer6Ready = new JCheckBox("Ready");
        jchkPlayer6Ready.setMinimumSize(min);
        jchkPlayer6Ready.setEnabled(false);
        jpPlayer6Ready.add(jchkPlayer6Ready, "visible");
        playerStatusConstraint.weightx = readyWeight;
        playerStatusConstraint.gridx = playerReadyXGrid;
        playerStatusPanel.add(jpPlayer6Ready, playerStatusConstraint);
        jchkPlayer6Ready.addActionListener((ActionEvent ae) -> {
            try{
                if(jchkPlayer6Ready.isSelected()) {
                    player.setReady("check");
                    toServer.writeUTF("[all][6][set][ready][check]");
                }
                else {
                    player.setReady("uncheck");
                    toServer.writeUTF("[all][6][set][ready][uncheck]");
                }
            } catch(IOException ex){
                Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        jchkPlayer6Ready.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("Player6Ready changed!");
            }
        });
        
        //row 8
        playerStatusConstraint.gridy = 7;
        
        jbStart = new JButton("Start");
        jbStart.setEnabled(false);
        jbStart.setMinimumSize(min);
        playerStatusPanel.add(jbStart, playerStatusConstraint);
        jbStart.addActionListener((ActionEvent ae) -> {
        });
        
        //
        JPanel chatPanel = new JPanel();
        chatPanel.setBorder(lineBorder);
        Dimension chatWindowPSize =
                new Dimension(
                        (int)componentWidth, (int)(componentHeight * 4.8));
        jtaChatWindow = new JTextArea();
        jtaChatWindow.setEditable(false);
        jtaChatWindow.setLineWrap(true);
        jtaChatWindow.setWrapStyleWord(true);
        JScrollPane jspChatWindow = new JScrollPane(jtaChatWindow);
        jspChatWindow.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jspChatWindow.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspChatWindow.setPreferredSize(chatWindowPSize);
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
        JTextField jtfChatEntry = new JTextField();
        jtfChatEntry.setForeground(Color.lightGray);
        jtfChatEntry.setText(prompt);
        Dimension chatEntryPSize =
                new Dimension(
                        (int)componentWidth, (int)(componentHeight * 0.9));
        jtfChatEntry.setPreferredSize(chatEntryPSize);
        textEntryPanel.add(jtfChatEntry, BorderLayout.WEST);
        mainPanel.add(textEntryPanel, mainConstraint);
        jtfChatEntry.addKeyListener(new KeyListenerImpl(
                jtfChatEntry, player.getUsername(), toServer));
        jtfChatEntry.addFocusListener(new FocusListenerImpl(jtfChatEntry));
        
        if(player.getPlayerNum() == 1){
            jlPlayer1Username.setText(player.getUsername());
            jlPlayer1IRLName.setText(player.getDetectiveLevel());
            jlPlayer1Color.setEnabled(true);
            jcbPlayer1GameCharacter.setEnabled(true);
            jchkPlayer1Ready.setEnabled(true);
            this.setPlayer1FieldsVis("visible");
        }
        else if(player.getPlayerNum() == 2) {
            jlPlayer2Username.setText(player.getUsername());
            jlPlayer2IRLName.setText(player.getDetectiveLevel());
            jlPlayer2Color.setEnabled(true);
            jcbPlayer2GameCharacter.setEnabled(true);
            jchkPlayer2Ready.setEnabled(true);
            this.setPlayer2FieldsVis("visible");
            this.newPlayerConnected();
        }
        else if(player.getPlayerNum() == 3) {
            jlPlayer3Username.setText(player.getUsername());
            jlPlayer3IRLName.setText(player.getDetectiveLevel());
            jlPlayer3Color.setEnabled(true);
            jcbPlayer3GameCharacter.setEnabled(true);
            jchkPlayer3Ready.setEnabled(true);
            this.setPlayer3FieldsVis("visible");
            this.newPlayerConnected();
        }
        else if(player.getPlayerNum() == 4) {
            jlPlayer4Username.setText(player.getUsername());
            jlPlayer4IRLName.setText(player.getDetectiveLevel());
            jlPlayer4Color.setEnabled(true);
            jcbPlayer4GameCharacter.setEnabled(true);
            jchkPlayer4Ready.setEnabled(true);
            this.setPlayer4FieldsVis("visible");
            this.newPlayerConnected();
        }
        else if(player.getPlayerNum() == 5) {
            jlPlayer5Username.setText(player.getUsername());
            jlPlayer5IRLName.setText(player.getDetectiveLevel());
            jlPlayer5Color.setEnabled(true);
            jcbPlayer5GameCharacter.setEnabled(true);
            jchkPlayer5Ready.setEnabled(true);
            this.setPlayer5FieldsVis("visible");
            this.newPlayerConnected();
        }
        else if(player.getPlayerNum() == 6) {
            jlPlayer6Username.setText(player.getUsername());
            jlPlayer6IRLName.setText(player.getDetectiveLevel());
            jlPlayer6Color.setEnabled(true);
            jcbPlayer6GameCharacter.setEnabled(true);
            jchkPlayer6Ready.setEnabled(true);
            this.setPlayer6FieldsVis("visible");
            this.newPlayerConnected();
        }
        
        NetworkListener nl = new NetworkListener(this, fromServer);
        nl.start();
        
        frame.add(mainPanel);
    }
    
    /**
     * 
     * @param value 
     */
    public void setPlayer1FieldsVis(String value) {
        cl = (CardLayout)(jpPlayer1Username.getLayout());
        cl.show(jpPlayer1Username, value);
        cl = (CardLayout)(jpPlayer1IRLName.getLayout());
        cl.show(jpPlayer1IRLName, value);
        cl = (CardLayout)(jpPlayer1Color.getLayout());
        cl.show(jpPlayer1Color, value);
        cl = (CardLayout)(jpPlayer1GameChar.getLayout());
        cl.show(jpPlayer1GameChar, value);
        cl = (CardLayout)(jpPlayer1Ready.getLayout());
        cl.show(jpPlayer1Ready, value);
    }
    
    /**
     * 
     * @param value 
     */
    public void setPlayer2FieldsVis(String value) {
        cl = (CardLayout)(jpPlayer2Username.getLayout());
        cl.show(jpPlayer2Username, value);
        cl = (CardLayout)(jpPlayer2IRLName.getLayout());
        cl.show(jpPlayer2IRLName, value);
        cl = (CardLayout)(jpPlayer2Color.getLayout());
        cl.show(jpPlayer2Color, value);
        cl = (CardLayout)(jpPlayer2GameChar.getLayout());
        cl.show(jpPlayer2GameChar, value);
        cl = (CardLayout)(jpPlayer2Ready.getLayout());
        cl.show(jpPlayer2Ready, value);
    }
    
    /**
     * 
     * @param value 
     */
    public void setPlayer3FieldsVis(String value) {
        cl = (CardLayout)(jpPlayer3Username.getLayout());
        cl.show(jpPlayer3Username, value);
        cl = (CardLayout)(jpPlayer3IRLName.getLayout());
        cl.show(jpPlayer3IRLName, value);
        cl = (CardLayout)(jpPlayer3Color.getLayout());
        cl.show(jpPlayer3Color, value);
        cl = (CardLayout)(jpPlayer3GameChar.getLayout());
        cl.show(jpPlayer3GameChar, value);
        cl = (CardLayout)(jpPlayer3Ready.getLayout());
        cl.show(jpPlayer3Ready, value);
    }
    
    /**
     * 
     * @param value 
     */
    public void setPlayer4FieldsVis(String value) {
        cl = (CardLayout)(jpPlayer4Username.getLayout());
        cl.show(jpPlayer4Username, value);
        cl = (CardLayout)(jpPlayer4IRLName.getLayout());
        cl.show(jpPlayer4IRLName, value);
        cl = (CardLayout)(jpPlayer4Color.getLayout());
        cl.show(jpPlayer4Color, value);
        cl = (CardLayout)(jpPlayer4GameChar.getLayout());
        cl.show(jpPlayer4GameChar, value);
        cl = (CardLayout)(jpPlayer4Ready.getLayout());
        cl.show(jpPlayer4Ready, value);
    }
    
    /**
     * 
     * @param value 
     */
    public void setPlayer5FieldsVis(String value) {
        cl = (CardLayout)(jpPlayer5Username.getLayout());
        cl.show(jpPlayer5Username, value);
        cl = (CardLayout)(jpPlayer5IRLName.getLayout());
        cl.show(jpPlayer5IRLName, value);
        cl = (CardLayout)(jpPlayer5Color.getLayout());
        cl.show(jpPlayer5Color, value);
        cl = (CardLayout)(jpPlayer5GameChar.getLayout());
        cl.show(jpPlayer5GameChar, value);
        cl = (CardLayout)(jpPlayer5Ready.getLayout());
        cl.show(jpPlayer5Ready, value);
    }
    
    /**
     * 
     * @param value 
     */
    public void setPlayer6FieldsVis(String value) {
        cl = (CardLayout)(jpPlayer6Username.getLayout());
        cl.show(jpPlayer6Username, value);
        cl = (CardLayout)(jpPlayer6IRLName.getLayout());
        cl.show(jpPlayer6IRLName, value);
        cl = (CardLayout)(jpPlayer6Color.getLayout());
        cl.show(jpPlayer6Color, value);
        cl = (CardLayout)(jpPlayer6GameChar.getLayout());
        cl.show(jpPlayer6GameChar, value);
        cl = (CardLayout)(jpPlayer6Ready.getLayout());
        cl.show(jpPlayer6Ready, value);
    }
    
    /**
     * 
     * @param text 
     */
    public void appendChat(String text){
        jtaChatWindow.append(text);
    }
    
    /**
     * 
     */
    public void refresh() {
        this.revalidate();
        this.repaint();
    }
    
    public void newPlayerConnected() {
        for(int i = 1; i < player.getPlayerNum(); i++) {
            try {
                toServer.writeUTF("[" + i + "][" + player.getPlayerNum()
                        + "][set][conn][]");
                toServer.writeUTF("[" + i + "][" + player.getPlayerNum()
                        + "][set][name][" + player.getUsername() + "]");
                toServer.writeUTF("[" + i + "][" + player.getPlayerNum()
                        + "][set][det][" + player.getDetectiveLevel() + "]");
            } catch (IOException ex) {
                Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void sendAllInfo(String toPlayer) {
        try {
            toServer.writeUTF(
                    "[" + toPlayer + "][" + player.getPlayerNum()
                    + "][set][all][visible]");
            toServer.writeUTF(
                    "[" + toPlayer + "][" + player.getPlayerNum() 
                    + "][set][name][" + player.getUsername() + "]");
            toServer.writeUTF(
                    "[" + toPlayer + "][" + player.getPlayerNum() 
                    + "][set][det][" + player.getDetectiveLevel() + "]");
            toServer.writeUTF(
                    "[" + toPlayer + "][" + player.getPlayerNum()
                    + "][set][char][" + player.getCharNum() + "]");
            toServer.writeUTF(
                    "[" + toPlayer + "][" + player.getPlayerNum()
                    + "][set][ready][" + player.getReady() + "]");
        } catch (IOException ex) {
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 
     * @param response 
     * @throws java.io.IOException 
     */
    @Override
    public void stringParser(String response) throws IOException {
        int[] index = {0,0,0,0,0,0,0,0,0,0};
        String dest, src, cmd, field, info;
        
        index[0] = response.indexOf("[") + 1;
        index[1] = response.indexOf("]", index[0]);
        index[2] = response.indexOf("[", index[1]) + 1;
        index[3] = response.indexOf("]", index[2]);
        index[4] = response.indexOf("[", index[3]) + 1;
        index[5] = response.indexOf("]", index[4]);
        index[6] = response.indexOf("[", index[5]) + 1;
        index[7] = response.indexOf("]", index[6]);
        index[8] = response.indexOf("[", index[7]) + 1;
        index[9] = response.length() - 1;
        
        dest = response.substring(index[0], index[1]);
        src = response.substring(index[2], index[3]);
        cmd = response.substring(index[4], index[5]);
        field = response.substring(index[6], index[7]);
        info = response.substring(index[8], index[9]);
        
        System.out.println(
            dest + " " + src + " " + cmd + " " + field + " " + info + "\n");
        
        if(src.matches("1")) {
            if(cmd.matches("set")) {
                if(field.matches("all")) {
                    if(info.matches("visible")) {
                        setPlayer1FieldsVis(info);
                    }
                }
                else if(field.matches("conn")) {
                    // a new player connected
                    setPlayer1FieldsVis("visible");
                    sendAllInfo("1");
                }
                else if(field.matches("name")) {
                    jlPlayer1Username.setText(info);
                }
                else if(field.matches("det")) {
                    jlPlayer1IRLName.setText(info);
                }
                else if(field.matches("char")) {
                    jcbPlayer1GameCharacter.setSelectedIndex(Integer.parseInt(info));
                }
                else if(field.matches("ready")) {
                    if(info.matches("check")) {
                        jchkPlayer1Ready.setSelected(true);
                    }
                    else if(info.matches("uncheck")) {
                        jchkPlayer1Ready.setSelected(false);
                    }
                }
            }
            else if(cmd.matches("get")) {
                if(field.matches("all")) {
                    sendAllInfo(src);
                }
            }
            else if(cmd.matches("chat")) {
                appendChat(info + "\n");
            }
        }
        else if(src.matches("2")) {
            if(cmd.matches("set")) {
                if(field.matches("all")) {
                    if(info.matches("visible")) {
                        setPlayer2FieldsVis(info);
                    }
                }
                else if(field.matches("conn")) {
                    // a new player connected
                    setPlayer2FieldsVis("visible");
                    sendAllInfo("2");
                }
                else if(field.matches("name")) {
                    jlPlayer2Username.setText(info);
                }
                else if(field.matches("det")) {
                    jlPlayer2IRLName.setText(info);
                }
                else if(field.matches("char")) {
                    jcbPlayer2GameCharacter.setSelectedIndex(Integer.parseInt(info));
                }
                else if(field.matches("ready")) {
                    if(info.matches("check")) {
                        jchkPlayer2Ready.setSelected(true);
                    }
                    else if(info.matches("uncheck")) {
                        jchkPlayer2Ready.setSelected(false);
                    }
                }
            }
            else if(cmd.matches("get")) {
                if(field.matches("all")) {
                    sendAllInfo(src);
                }
            }
            else if(cmd.matches("chat")) {
                appendChat(info + "\n");
            }
        }
        else if(src.matches("3")) {
            if(cmd.matches("set")) {
                if(field.matches("all")) {
                    if(info.matches("visible")) {
                        setPlayer3FieldsVis(info);
                    }
                }
                else if(field.matches("conn")) {
                    // a new player connected
                    setPlayer3FieldsVis("visible");
                    sendAllInfo("3");
                }
                else if(field.matches("name")) {
                    jlPlayer3Username.setText(info);
                }
                else if(field.matches("det")) {
                    jlPlayer3IRLName.setText(info);
                }
                else if(field.matches("char")) {
                    jcbPlayer3GameCharacter.setSelectedIndex(Integer.parseInt(info));
                }
                else if(field.matches("ready")) {
                    if(info.matches("check")) {
                        jchkPlayer3Ready.setSelected(true);
                    }
                    else if(info.matches("uncheck")) {
                        jchkPlayer3Ready.setSelected(false);
                    }
                }
            }
            else if(cmd.matches("get")) {
                if(field.matches("all")) {
                    sendAllInfo(src);
                }
            }
            else if(cmd.matches("chat")) {
                appendChat(info + "\n");
            }
        }
        else if(src.matches("4")) {
            if(cmd.matches("set")) {
                if(field.matches("all")) {
                    if(info.matches("visible")) {
                        setPlayer4FieldsVis(info);
                    }
                }
                else if(field.matches("conn")) {
                    // a new player connected
                    setPlayer4FieldsVis("visible");
                    sendAllInfo("4");
                }
                else if(field.matches("name")) {
                    jlPlayer4Username.setText(info);
                }
                else if(field.matches("det")) {
                    jlPlayer4IRLName.setText(info);
                }
                else if(field.matches("char")) {
                    jcbPlayer4GameCharacter.setSelectedIndex(Integer.parseInt(info));
                }
                else if(field.matches("ready")) {
                    if(info.matches("check")) {
                        jchkPlayer4Ready.setSelected(true);
                    }
                    else if(info.matches("uncheck")) {
                        jchkPlayer4Ready.setSelected(false);
                    }
                }
            }
            else if(cmd.matches("get")) {
                if(field.matches("all")) {
                    sendAllInfo(src);
                }
            }
            else if(cmd.matches("chat")) {
                appendChat(info + "\n");
            }
        }
        else if(src.matches("5")) {
            if(cmd.matches("set")) {
                if(field.matches("all")) {
                    if(info.matches("visible")) {
                        setPlayer5FieldsVis(info);
                    }
                }
                else if(field.matches("conn")) {
                    // a new player connected
                    setPlayer5FieldsVis("visible");
                    sendAllInfo("5");
                }
                else if(field.matches("name")) {
                    jlPlayer5Username.setText(info);
                }
                else if(field.matches("det")) {
                    jlPlayer5IRLName.setText(info);
                }
                else if(field.matches("char")) {
                    jcbPlayer5GameCharacter.setSelectedIndex(Integer.parseInt(info));
                }
                else if(field.matches("ready")) {
                    if(info.matches("check")) {
                        jchkPlayer5Ready.setSelected(true);
                    }
                    else if(info.matches("uncheck")) {
                        jchkPlayer5Ready.setSelected(false);
                    }
                }
            }
            else if(cmd.matches("get")) {
                if(field.matches("all")) {
                    sendAllInfo(src);
                }
            }
            else if(cmd.matches("chat")) {
                appendChat(info + "\n");
            }
        }
        else if(src.matches("6")) {
            if(cmd.matches("set")) {
                if(field.matches("all")) {
                    if(info.matches("visible")) {
                        setPlayer6FieldsVis(info);
                    }
                }
                else if(field.matches("conn")) {
                    // a new player connected
                    setPlayer6FieldsVis("visible");
                    sendAllInfo("6");
                }
                else if(field.matches("name")) {
                    jlPlayer6Username.setText(info);
                }
                else if(field.matches("det")) {
                    jlPlayer6IRLName.setText(info);
                }
                else if(field.matches("char")) {
                    jcbPlayer6GameCharacter.setSelectedIndex(Integer.parseInt(info));
                }
                else if(field.matches("ready")) {
                    if(info.matches("check")) {
                        jchkPlayer6Ready.setSelected(true);
                    }
                    else if(info.matches("uncheck")) {
                        jchkPlayer6Ready.setSelected(false);
                    }
                }
            }
            else if(cmd.matches("get")) {
                if(field.matches("all")) {
                    sendAllInfo(src);
                }
            }
            else if(cmd.matches("chat")) {
                appendChat(info + "\n");
            }
        }
        
        refresh();
    }

    /**
     * 
     */
    private class KeyListenerImpl implements KeyListener {

        private final JTextField jtfChatEntry;
        private final String username;
        private final DataOutputStream toServer;

        public KeyListenerImpl(JTextField jtfChatEntry, String username, DataOutputStream toServer) {
            this.jtfChatEntry = jtfChatEntry;
            this.username = username;
            this.toServer = toServer;
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                if(!jtfChatEntry.getText().isEmpty()) {
                    String returnString = "";
                    
                    returnString +=
                            "[all][" + player.getPlayerNum()
                            + "][chat][chat][[" + username 
                            + " " + df.format(new Date()) + "]: " +
                            jtfChatEntry.getText() + "]";
                    
                    try {
                        toServer.writeUTF(returnString);
                    } catch (IOException ex) {
                        Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    jtfChatEntry.setText("");
                }
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    /**
     * 
     */
    private class FocusListenerImpl implements FocusListener {

        private final JTextField jtfChatEntry;

        public FocusListenerImpl(JTextField jtfChatEntry) {
            this.jtfChatEntry = jtfChatEntry;
        }

        @Override
        public void focusGained(FocusEvent e) {
            jtfChatEntry.setText("");
            jtfChatEntry.setForeground(Color.black);
        }

        @Override
        public void focusLost(FocusEvent e) {
            jtfChatEntry.setText(prompt);
            jtfChatEntry.setForeground(Color.lightGray);
        }
    }
}