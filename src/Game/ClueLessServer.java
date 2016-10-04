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
import java.io.*;
import java.net.*;
import java.util.Date;
import javax.swing.*;

/**
 * 
 */
public class ClueLessServer extends JFrame implements ClueLessConstants {
    public static void main(String[] args) {
        ClueLessServer frame = new ClueLessServer();
    }
    
    /**
     * 
     */
    public ClueLessServer() {
        JTextArea jtaLog = new JTextArea();
        
        // Create a scroll pane to hold text area
        JScrollPane scrollPane = new JScrollPane(jtaLog);
        
        // Add the scroll pane to the frame
        add(scrollPane, BorderLayout.CENTER);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setTitle("ClueLess Server");
        setVisible(true);
        
        try {
            int serverSocketPort = 8000;
            int sessionNo = 1;
            
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(serverSocketPort);
            jtaLog.append(new Date() +
                    ": Server started at socket " + serverSocketPort + "\n");
            
            // Connect to player 1
            Socket player1 = serverSocket.accept();
            
            DataInputStream player1Input =
                    new DataInputStream(player1.getInputStream());
            DataOutputStream player1Output =
                    new DataOutputStream(player1.getOutputStream());
            
            String username = player1Input.readUTF();
            String password = player1Input.readUTF();
            String text = "password";
            
            System.out.println("Received\nUsername: " + username);
            System.out.println("Password: " + password);
            
            if(username.matches("Ryan") && password.matches(text)) {
                System.out.println("correct login");
                player1Output.write(1);
                jtaLog.append(new Date() + ": Player 1 joined session " +
                    sessionNo + '\n');
                jtaLog.append("Player 1's IP address" + 
                    player1.getInetAddress().getHostAddress() + '\n');
            }
            else {
                System.out.println("incorrect username or password");
                player1Output.write(0);
                player1.close();
            }
            
            // create a new player object
            Player player1Player = new Player(PLAYER1, "Ryan", "Ryan", player1);
            
            /*
            // Connect to player 2
            Socket player2 = serverSocket.accept();

            jtaLog.append(new Date() + 
                    ": Player 2 joined session " + sessionNo + '\n');
            jtaLog.append("Player 2's IP address" + 
                    player2.getInetAddress().getHostAddress() + '\n');

            // Notify that the player is Player 2
            new DataOutputStream(
              player2.getOutputStream()).writeInt(PLAYER2);

            // Connect to player 3
            Socket player3 = serverSocket.accept();

            jtaLog.append(new Date() + 
                    ": Player 3 joined session " + sessionNo + '\n');
            jtaLog.append("Player 3's IP address" + 
                    player3.getInetAddress().getHostAddress() + '\n');
            
            // Notify that the player is Player 3
            new DataOutputStream(
              player3.getOutputStream()).writeInt(PLAYER3);
              * /
            /**Accept the chance to start game
             * if 3 people have readied up,
             * and start game button has been pressed
             * start thread
            */
            /**
            // Connect to player 4
            Socket player4 = serverSocket.accept();

            jtaLog.append(new Date() + 
                    ": Player 4 joined session " + sessionNo + '\n');
            jtaLog.append("Player 4's IP address" + 
                    player4.getInetAddress().getHostAddress() + '\n');

            // Notify that the player is Player 4
            new DataOutputStream(
              player4.getOutputStream()).writeInt(PLAYER4);

            // Connect to player 5
            Socket player5 = serverSocket.accept();

            jtaLog.append(new Date() + 
                    ": Player 5 joined session " + sessionNo + '\n');
            jtaLog.append("Player 5's IP address" + 
                    player5.getInetAddress().getHostAddress() + '\n');

            // Notify that the player is Player 5
            new DataOutputStream(
              player5.getOutputStream()).writeInt(PLAYER5);

            // Connect to player 6
            Socket player6 = serverSocket.accept();

            jtaLog.append(new Date() + 
                    ": Player 6 joined session " + sessionNo + '\n');
            jtaLog.append("Player 6's IP address" + 
                    player6.getInetAddress().getHostAddress() + '\n');

            // Notify that the player is Player 6
            new DataOutputStream(
              player6.getOutputStream()).writeInt(PLAYER6);

            // Display this session and increment session number
            jtaLog.append(new Date() + ": Start a thread for session " + 
                    sessionNo++ + '\n');

            // Create a new thread for this session of two players
            HandleASession task = new HandleASession(
                    player1,player2,player3,player4,player5,player6);

            // Start the new thread
            new Thread(task).start();
            */
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
}

/**
 * 
 */
class HandleASession implements Runnable, ClueLessConstants {
    /**
     * 
     */
    private Socket player1;
    private Socket player2;
    private Socket player3;
    private Socket player4;
    private Socket player5;
    private Socket player6;
    
    /**
     * 
     */
    private DataInputStream fromPlayer1;
    private DataOutputStream toPlayer1;
    private DataInputStream fromPlayer2;
    private DataOutputStream toPlayer2;
    private DataInputStream fromPlayer3;
    private DataOutputStream toPlayer3;
    private DataInputStream fromPlayer4;
    private DataOutputStream toPlayer4;
    private DataInputStream fromPlayer5;
    private DataOutputStream toPlayer5;
    private DataInputStream fromPlayer6;
    private DataOutputStream toPlayer6;
    
    /**
     * 
     * @param player1
     * @param player2
     * @param player3 
     */
    public HandleASession(
            Socket player1, Socket player2, Socket player3) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        
    }
    
    /**
     * 
     * @param player1
     * @param player2
     * @param player3
     * @param player4 
     */
    public HandleASession(
            Socket player1, Socket player2, Socket player3, Socket player4) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        
    }
    
    /**
     * 
     * @param player1
     * @param player2
     * @param player3
     * @param player4
     * @param player5 
     */
    public HandleASession(
            Socket player1, Socket player2, Socket player3, Socket player4,
            Socket player5) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.player5 = player5;
        
    }
    
    /**
     * 
     * @param player1
     * @param player2
     * @param player3
     * @param player4
     * @param player5
     * @param player6 
     */
    public HandleASession(
            Socket player1, Socket player2, Socket player3, Socket player4,
            Socket player5, Socket player6) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.player5 = player5;
        this.player6 = player6;
        
    }
    
    /**
     * 
     */
    @Override
    public void run() {
    }
}