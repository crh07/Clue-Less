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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * 
 */
public class ClueLessServer extends JFrame implements ClueLessConstants {
        
    //
    private volatile ArrayList<Socket> sockets = new ArrayList();
    
    //
    //private SimpleDateFormat df = new SimpleDateFormat("hh:mm");
    
    //
    private volatile int playerNums = 0;
    
    //private DataInputStream playerInput;
    //private DataOutputStream playerOutput;
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        ClueLessServer frame = new ClueLessServer();
    }
    
    /**
     * 
     */
    public ClueLessServer() {
        
        //
        JTextArea jtaLog = new JTextArea();
        
        // Create a scroll pane to hold text area
        JScrollPane scrollPane = new JScrollPane(jtaLog);
        
        // Add the scroll pane to the frame
        add(scrollPane, BorderLayout.CENTER);
        
        //
        //NetworkListener thread;
        
        //
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setTitle("ClueLess Server");
        setVisible(true);
        
        try {
            //
            int serverSocketPort = 8000;
            int sessionNo = 1;
            ClueLessServer s = this;
            
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(serverSocketPort);
            jtaLog.append(new Date() +
                    ": Server started at socket " + serverSocketPort + "\n");
            
            ExecutorService ss = Executors.newFixedThreadPool(6);
            
            while(true){
                Socket player = serverSocket.accept();
                sockets.add(player);
                
                Runnable clientHandler = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            DataInputStream playerInput =
                                    new DataInputStream(player.getInputStream());
                            DataOutputStream playerOutput =
                                    new DataOutputStream(player.getOutputStream());

                            String username = playerInput.readUTF();
                            String password = playerInput.readUTF();
                            String text = "password";

                            System.out.println("Received\nUsername: " + username);
                            System.out.println("Password: " + password);

                            if((username.matches("Ryan") && password.matches(text))
                                || (username.matches("James") && password.matches(text))
                                || (username.matches("Scott") && password.matches(text))
                                || (username.matches("Christine") && password.matches(text))
                                || (username.matches("Spock") && password.matches(text))
                                || (username.matches("Kirk") && password.matches(text))) {
                                System.out.println("correct login");
                                playerOutput.write(1);
                                playerNums++;
                                jtaLog.append(new Date() + ": Player "
                                        + playerNums + " joined session "
                                        + sessionNo + '\n');
                                jtaLog.append("Player " + playerNums
                                        + "'s IP address" +
                                        player.getInetAddress().getHostAddress()
                                        + '\n');

                                playerOutput.writeInt(playerNums);
                                playerOutput.writeUTF("Newb");
                            }
                            else {
                                System.out.println(
                                        "incorrect username or password");
                                playerOutput.write(0);
                                sockets.remove(sockets.size() - 1);
                                player.close(); 
                            }

                            //
                            NetworkListener thread = new NetworkListener(s, playerInput);
                            thread.start();
                            System.out.println("got a client!");
                        }catch (IOException ex) {
                            System.err.println(ex);
                        }
                    }
                };
                
                ss.submit(clientHandler);
            }
        } catch (IOException ex) {
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param response 
     * @throws java.io.IOException 
     */
    public void stringParser(String response) throws IOException { 
        int[] index = {0,0,0,0,0,0,0,0,0,0};
        String dest, src, cmd, field, info;
        
        index[0] = response.indexOf("[") + 1;
        index[1] = response.indexOf("]");
        index[2] = response.indexOf("[", index[1]) + 1;
        index[3] = response.indexOf("]", index[2]);
        index[4] = response.indexOf("[", index[3]) + 1;
        index[5] = response.indexOf("]", index[4]);
        index[6] = response.indexOf("[", index[5]) + 1;
        index[7] = response.indexOf("]", index[6]);
        index[8] = response.indexOf("[", index[7]) + 1;
        index[9] = response.indexOf("]", index[8]);
        
        
        dest = response.substring(index[0], index[1]);
        src = response.substring(index[2], index[3]);
        cmd = response.substring(index[4], index[5]);
        field = response.substring(index[6], index[7]);
        info = response.substring(index[8], index[9]);
        
        System.out.println(response);
        System.out.println(dest + " " + src + " " + cmd + " " + field + " " + info);
        
        DataOutputStream toClient;
        
        if(dest.matches("all")) {
            for(int i = 0; i < sockets.size(); i++)
            {
                if((i+1) != Integer.parseInt(src) || cmd.matches("chat")){
                    toClient = new DataOutputStream(
                            sockets.get(i).getOutputStream());
                    toClient.writeUTF(response);
                }
            }
        }
        else if(dest.matches("server")) {
            System.err.println("We received something for some reason...");
        }
        else if(dest.matches("1")) {
            toClient = new DataOutputStream(sockets.get(0).getOutputStream());
            toClient.writeUTF(response);
        }
        else if(dest.matches("2")) {
            toClient = new DataOutputStream(sockets.get(1).getOutputStream());
            toClient.writeUTF(response);
        }
        else if(dest.matches("3")) {
            toClient = new DataOutputStream(sockets.get(2).getOutputStream());
            toClient.writeUTF(response);
        }
        else if(dest.matches("4")) {
            toClient = new DataOutputStream(sockets.get(3).getOutputStream());
            toClient.writeUTF(response);
        }
        else if(dest.matches("5")) {
            toClient = new DataOutputStream(sockets.get(4).getOutputStream());
            toClient.writeUTF(response);
        }
        else if(dest.matches("6")) {
            toClient = new DataOutputStream(sockets.get(5).getOutputStream());
            toClient.writeUTF(response);
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