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
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

/**
 * 
 */
public class ClueLessClient extends JFrame
        implements Runnable, ClueLessConstants, CustomListener {
    // Indicate whether the player has the turn
    private boolean myTurn = false;

    // Create and initialize a title label
    private JLabel jlblTitle = new JLabel();

    // Create and initialize a status label
    private JLabel jlblStatus = new JLabel();
    
    private Socket socket;

    // Input and output streams from/to server
    private DataInputStream fromServer;
    private DataOutputStream toServer;

    // Continue to play
    private boolean continueToPlay = true;

    // Wait for the player to mark a cell
    private boolean waiting = true;
    
    // Wait to be connected to server to do other things
    private boolean connected = false;
    
    private String username;
    
    private JTextArea jtaChatWindow;
    
    private NetworkListener thread;
    
    private Lobby lobby;
    
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
        
        //
        Connection connectPanel =
                new Connection(this, socket, toServer, fromServer);
        connectPanel.addListeners(this);
        
        //
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
        this.getContentPane().removeAll();
        
        //
        this.setSize(700,580);
        this.setMinimumSize(this.getSize());
        
        //
        lobby = new Lobby(this, socket, toServer, fromServer, username);
        
        //
        this.revalidate();
        this.repaint();
        
        //
        thread = new NetworkListener(this, fromServer);
        thread.start();
    }
    
    /**
     * after game start has begun, display the clueGameUI
     */
    public final void clueGameUI() {
        
    }

    /**
     * 
     */
    @Override
    public void notifyListeners() {
    }
    
    /**
     * 
     * @param username
     * @param socket
     * @param toServer
     * @param fromServer
     */
    public void notified(
            String username, Socket socket,
            DataOutputStream toServer, DataInputStream fromServer) {
        this.username = username;
        this.connected = true;
        this.socket = socket;
        this.toServer = toServer;
        this.fromServer = fromServer;
        setupGameUI();
    }
    
    /**
     * 
     * @param response
     */
    public void stringParser(String response) {
        int[] index = {0,0,0,0,0,0,0,0,0,0};
        String from, cmd, subject, field, info;
        
        index[0] = response.indexOf("[") + 1;
        index[1] = response.indexOf("]", index[0]);
        index[2] = index[1] + 2;
        index[3] = response.indexOf("]", index[2]);
        index[4] = index[3] + 2;
        index[5] = response.indexOf("]", index[4]);
        index[6] = index[5] + 2;
        index[7] = response.indexOf("]", index[6]);
        index[8] = index[7] + 2;
        index[9] = response.length() - 1;
        
        from = response.substring(index[0], index[1]);
        cmd = response.substring(index[2], index[3]);
        subject = response.substring(index[4], index[5]);
        field = response.substring(index[6], index[7]);
        info = response.substring(index[8], index[9]);
        
        System.out.println(
            from + " " + cmd + " " + subject + " " + field + " " + info + "\n");
        
        if(from.matches("server")){
            if(cmd.matches("set")) {
                if(subject.matches("player1")) {
                    if(field.matches("username")) {
                        lobby.setJLPlayer1Username(info);
                    }
                    else if(field.matches("irl")) {
                        lobby.setJLPlayer1IRLName(info);
                    }
                    else if(field.matches("char")) {
                        lobby.setJCBPlayer1GameCharacter(
                            Integer.parseInt(info));
                    }
                    else if(field.matches("ready")) {
                        if(info.matches("enable"))
                            lobby.setJCHKPlayer1Ready(true);
                        else if(info.matches("disable"))
                            lobby.setJCHKPlayer1Ready(false);
                    }
                    else if(field.matches("chat")) {
                        lobby.appendChat(info + "\n");
                    }
                    else if(field.matches("all")) {
                        if(info.matches("enable"))
                            lobby.setPlayer1FieldsEn(true);
                        else if(info.matches("disable"))
                            lobby.setPlayer1FieldsEn(false);
                    }
                }
                else if(subject.matches("player2")) {
                    
                }
                else if(subject.matches("player3")) {
                    
                }
                else if(subject.matches("player4")) {
                    
                }
                else if(subject.matches("player5")) {
                    
                }
                else if(subject.matches("player6")) {
                    
                }
                else if(subject.matches("all")) {
                    
                }
                else {
                    System.err.println("unrecognized subject");
                }
            }
            else if(cmd.matches("chat")) {
                if(subject.matches("all")) {
                    if (field.matches("chat")) {
                        lobby.appendChat(info + "\n");
                    }
                }
                else if(subject.matches("player1")) {
                    
                }
                else if(subject.matches("player2")) {
                    
                }
                else if(subject.matches("player3")) {
                    
                }
                else if(subject.matches("player4")) {
                    
                }
                else if(subject.matches("player5")) {
                    
                }
                else if(subject.matches("player6")) {
                    
                }
                else {
                    System.err.println("unrecognized subject");
                }
            }
            
            lobby.refresh();
        }
    }
    
    /**
     * 
     */
    @Override
    public void run() {
        
    }
}
