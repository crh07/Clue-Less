/**
 * Contributors: Ryan D Bonisch
 * Course: EN.605.401 Foundations of Software Engineering
 * Start: 25-Sep-16 12:55
 * End: 
 */
package Game;

/**
 * Import all necessary libraries
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * 
 */
public class ClueLessClient extends JFrame
        implements Runnable, ClueLessConstants {
    // Indicate whether the player has the turn
    private boolean myTurn = false;

    // Create and initialize a title label
    private JLabel jlblTitle = new JLabel();

    // Create and initialize a status label
    private JLabel jlblStatus = new JLabel();

    // Input and output streams from/to server
    private DataInputStream fromServer;
    private DataOutputStream toServer;

    // Continue to play
    private boolean continueToPlay = true;

    // Wait for the player to mark a cell
    private boolean waiting = true;

    // Host name or IP address
    private String host = "127.0.0.1";
    
    // Integer of port for the server
    private int serverPort = 8000;
    
    // Wait to be connected to server to do other things
    private boolean connected = false;
    
    private JTextField jtfServerIP;
    
    private JTextField jtfServerPort;
    
    private JTextField jtfUsername;
    
    private JPasswordField jpfPassword;
    
    private JTextArea jtaChatWindow;
    
    private JTextField jtfChatEntry;
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        ClueLessClient frame = new ClueLessClient();
    }
    
    /**
     * 
     */
    public ClueLessClient() {
        /** Load the first UI for connection
         * information (IP Address, port, name, password)
         */
        connectionUI();
    }
    
    /**
     * 
     */
    public final void connectionUI() {
        GridLayout gridLayout1 = new GridLayout(6, 2, 2, 10);
        
        JPanel panel1 = new JPanel(gridLayout1);
        
        JLabel jlServerIP = new JLabel("Server IP Address: ");
        JLabel jlServerPort = new JLabel("Server Port: ");
        JLabel jlUsername = new JLabel("Username: ");
        JLabel jlPassword = new JLabel("Password: ");
        jtfServerIP = new JTextField(host, 5);
        jtfServerPort = new JTextField("" + serverPort, 5);
        jtfUsername = new JTextField("Ryan", 5);
        jpfPassword = new JPasswordField("password", 5);
        JButton jbConnect = new JButton("Connect");
        jbConnect.setName("jbConnect");
        jbConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                connectToServer();
            }
        });
        
        panel1.add(jlServerIP);
        panel1.add(jtfServerIP);
        
        panel1.add(jlServerPort);
        panel1.add(jtfServerPort);
        
        panel1.add(jlUsername);
        panel1.add(jtfUsername);
        
        panel1.add(jlPassword);
        panel1.add(jpfPassword);
        
        panel1.add(new JLabel(""));
        panel1.add(new JLabel(""));
        panel1.add(new JLabel(""));
        panel1.add(jbConnect);
        
        add(panel1);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        setTitle("ClueLess Client");
        setVisible(true);
    }
    
    /**
     * Once connected, display this setup game UI
     */
    public final void setupGameUI() {
        //blank slate
        getContentPane().removeAll();
        this.setSize(700,580);
        this.setMinimumSize(this.getSize());
        double componentWidth = this.getWidth() - 35;
        double componentHeight = this.getHeight() / 20;
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
        
        JLabel jlPlayer1Username = new JLabel("Player1");
        jlPlayer1Username.setMinimumSize(min);
        playerStatusConstraint.weightx = nameWeight;
        playerStatusConstraint.gridx = usernameXGrid;
        playerStatusPanel.add(jlPlayer1Username, playerStatusConstraint);
        
        JLabel jlPlayer1IRLName = new JLabel("name");
        jlPlayer1IRLName.setMinimumSize(min);
        playerStatusConstraint.gridx = irlNameXGrid;
        playerStatusPanel.add(jlPlayer1IRLName, playerStatusConstraint);
        
        final JLabel jlPlayer1Color = new JLabel();
        jlPlayer1Color.setMinimumSize(min);
        playerStatusConstraint.weightx = colorWeight;
        playerStatusConstraint.gridx = colorXGrid;
        playerStatusPanel.add(jlPlayer1Color, playerStatusConstraint);
        
        JComboBox jcbPlayer1GameCharacter = new JComboBox(gameCharacters);
        jcbPlayer1GameCharacter.setMinimumSize(min);
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
        
        JCheckBox jchkPlayer1Ready = new JCheckBox("Ready");
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
        
        JLabel jlPlayer2Username = new JLabel("Player2");
        jlPlayer2Username.setMinimumSize(min);
        playerStatusConstraint.weightx = nameWeight;
        playerStatusConstraint.gridx = usernameXGrid;
        playerStatusPanel.add(jlPlayer2Username, playerStatusConstraint);
        
        JLabel jlPlayer2IRLName = new JLabel("name");
        jlPlayer2IRLName.setMinimumSize(min);
        playerStatusConstraint.gridx = irlNameXGrid;
        playerStatusPanel.add(jlPlayer2IRLName, playerStatusConstraint);
        
        final JLabel jlPlayer2Color = new JLabel();
        jlPlayer2Color.setMinimumSize(min);
        playerStatusConstraint.weightx = colorWeight;
        playerStatusConstraint.gridx = colorXGrid;
        playerStatusPanel.add(jlPlayer2Color, playerStatusConstraint);
        
        JComboBox jcbPlayer2GameCharacter = new JComboBox(gameCharacters);
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
        
        JCheckBox jchkPlayer2Ready = new JCheckBox("Ready");
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
        
        JLabel jlPlayer3Username = new JLabel("Player3");
        jlPlayer3Username.setMinimumSize(min);
        playerStatusConstraint.weightx = nameWeight;
        playerStatusConstraint.gridx = usernameXGrid;
        playerStatusPanel.add(jlPlayer3Username, playerStatusConstraint);
        
        JLabel jlPlayer3IRLName = new JLabel("name");
        jlPlayer3IRLName.setMinimumSize(min);
        playerStatusConstraint.gridx = irlNameXGrid;
        playerStatusPanel.add(jlPlayer3IRLName, playerStatusConstraint);
        
        final JLabel jlPlayer3Color = new JLabel();
        jlPlayer3Color.setMinimumSize(min);
        playerStatusConstraint.weightx = colorWeight;
        playerStatusConstraint.gridx = colorXGrid;
        playerStatusPanel.add(jlPlayer3Color, playerStatusConstraint);
        
        JComboBox jcbPlayer3GameCharacter = new JComboBox(gameCharacters);
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
        
        JCheckBox jchkPlayer3Ready = new JCheckBox("Ready");
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
        
        JLabel jlPlayer4Username = new JLabel("Player4");
        jlPlayer4Username.setMinimumSize(min);
        playerStatusConstraint.weightx = nameWeight;
        playerStatusConstraint.gridx = usernameXGrid;
        playerStatusPanel.add(jlPlayer4Username, playerStatusConstraint);
        
        JLabel jlPlayer4IRLName = new JLabel("name");
        jlPlayer4IRLName.setMinimumSize(min);
        playerStatusConstraint.gridx = irlNameXGrid;
        playerStatusPanel.add(jlPlayer4IRLName, playerStatusConstraint);
        
        final JLabel jlPlayer4Color = new JLabel();
        jlPlayer4Color.setMinimumSize(min);
        playerStatusConstraint.weightx = colorWeight;
        playerStatusConstraint.gridx = colorXGrid;
        playerStatusPanel.add(jlPlayer4Color, playerStatusConstraint);
        
        JComboBox jcbPlayer4GameCharacter = new JComboBox(gameCharacters);
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
        
        JCheckBox jchkPlayer4Ready = new JCheckBox("Ready");
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
        
        JLabel jlPlayer5Username = new JLabel("Player5");
        jlPlayer5Username.setMinimumSize(min);
        playerStatusConstraint.weightx = nameWeight;
        playerStatusConstraint.gridx = usernameXGrid;
        playerStatusPanel.add(jlPlayer5Username, playerStatusConstraint);
        
        JLabel jlPlayer5IRLName = new JLabel("name");
        jlPlayer5IRLName.setMinimumSize(min);
        playerStatusConstraint.gridx = irlNameXGrid;
        playerStatusPanel.add(jlPlayer5IRLName, playerStatusConstraint);
        
        final JLabel jlPlayer5Color = new JLabel();
        jlPlayer5Color.setMinimumSize(min);
        playerStatusConstraint.weightx = colorWeight;
        playerStatusConstraint.gridx = colorXGrid;
        playerStatusPanel.add(jlPlayer5Color, playerStatusConstraint);
        
        JComboBox jcbPlayer5GameCharacter = new JComboBox(gameCharacters);
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
        
        JCheckBox jchkPlayer5Ready = new JCheckBox("Ready");
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
        
        JLabel jlPlayer6Username = new JLabel("Player6");
        jlPlayer6Username.setMinimumSize(min);
        playerStatusConstraint.weightx = nameWeight;
        playerStatusConstraint.gridx = usernameXGrid;
        playerStatusPanel.add(jlPlayer6Username, playerStatusConstraint);
        
        JLabel jlPlayer6IRLName = new JLabel("name");
        jlPlayer6IRLName.setMinimumSize(min);
        playerStatusConstraint.gridx = irlNameXGrid;
        playerStatusPanel.add(jlPlayer6IRLName, playerStatusConstraint);
        
        final JLabel jlPlayer6Color = new JLabel();
        jlPlayer6Color.setMinimumSize(min);
        playerStatusConstraint.weightx = colorWeight;
        playerStatusConstraint.gridx = colorXGrid;
        playerStatusPanel.add(jlPlayer6Color, playerStatusConstraint);
        
        JComboBox jcbPlayer6GameCharacter = new JComboBox(gameCharacters);
        jcbPlayer6GameCharacter.setSelectedIndex(-1);
        jcbPlayer6GameCharacter.setMinimumSize(min);
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
        
        JCheckBox jchkPlayer6Ready = new JCheckBox("Ready");
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
        
        JButton jbStart = new JButton("Start");
        jbStart.setEnabled(false);
        jbStart.setMinimumSize(min);
        playerStatusPanel.add(jbStart, playerStatusConstraint);
        jbStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            }
        });
        
        JPanel chatPanel = new JPanel();
        chatPanel.setBorder(lineBorder);
        Dimension chatWindowPSize =
                new Dimension(
                        (int)componentWidth, (int)(componentHeight * 4.8));
        jtaChatWindow = new JTextArea("Junk");
        jtaChatWindow.setPreferredSize(chatWindowPSize);
        JScrollPane jspChatWindow = new JScrollPane(jtaChatWindow);
        mainConstraint.anchor = GridBagConstraints.CENTER;
        mainConstraint.gridy = 1;
        mainConstraint.ipady = 140;
        mainConstraint.weighty = 0.25;
        mainConstraint.insets = new Insets(10,0,0,0);
        mainPanel.add(chatPanel, mainConstraint);
        chatPanel.add(jspChatWindow, BorderLayout.CENTER);
        
        
        JPanel textEntryPanel = new JPanel();
        textEntryPanel.setBorder(lineBorder);
        mainConstraint.anchor = GridBagConstraints.PAGE_END;
        mainConstraint.gridy = 2;
        mainConstraint.ipady = 30;
        mainPanel.add(textEntryPanel, mainConstraint);
        jtfChatEntry = new JTextField("[" + jtfUsername.getText() + "]: ");
        Dimension chatEntryPSize =
                new Dimension(
                        (int)componentWidth, (int)(componentHeight * 0.9));
        jtfChatEntry.setPreferredSize(chatEntryPSize);
        textEntryPanel.add(jtfChatEntry, BorderLayout.WEST);
        
        add(mainPanel);
        this.revalidate();
        this.repaint();
    }
    
    /**
     * after game start has begun, display the clueGameUI
     */
    public final void clueGameUI() {
        
    }
    
    /**
     * 
     */
    public void connectToServer() {
        try {
            System.out.println("Attemtping connection to server");
            // Create a socket to connect to the server
            Socket socket = new Socket(host, serverPort);
            
            // Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());
            
            // Create an input stream to receive data from the server
            fromServer = new DataInputStream(socket.getInputStream());
            
            System.out.println("Sending Username");
            toServer.writeUTF(jtfUsername.getText());
        
            System.out.println("Sending Password");
            String password = new String(jpfPassword.getPassword());
            toServer.writeUTF(password);
            
            int response = fromServer.read();
            
            if(response == 1) {
                System.out.println("Correct credentials, connecting!");
                setupGameUI();
            }
            else if(response == 0){
                System.out.println("Incorrect username or password");
                socket.close();
            }
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    /**
     * 
     */
    @Override
    public void run() {
        
    }
}
