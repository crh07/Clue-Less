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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.net.Socket;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * 
 */
public class Connection extends JPanel {
    
    //
    private ArrayList<ClueLessClient> listeners = new ArrayList();
    
    //
    private Player player;
    
    //
    private DataInputStream fromServer;
    private DataOutputStream toServer;
    
    //
    private JTextField jtfServerIP;
    
    //
    private JTextField jtfServerPort;
    
    //
    private JTextField jtfUsername;
    
    //
    private JPasswordField jpfPassword;
    
    //
    private String host = "127.0.0.1";
    
    //
    private int serverPort = 8000;
    
    public Connection(JFrame frame) {
        
        //
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
        jbConnect.addActionListener((ActionEvent ae) -> {
            connectToServer();
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
        
        frame.add(panel1);
    }
    
    /**
     * 
     */
    private void connectToServer() {
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
                int playerNumber = fromServer.readInt();
                String detectiveLevel = fromServer.readUTF();
                player = new Player(
                        playerNumber, jtfUsername.getText(),
                        detectiveLevel, socket);
                //toServer.close();
                //fromServer.close();
                notifyListeners();
            }
            else if(response == 0){
                System.out.println("Incorrect username or password");
                //socket.close();
            }
        }
        catch (Exception ex) {
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @return 
     */
    public DataInputStream getFromServer() {
        return fromServer;
    }
    
    /**
     * 
     * @return 
     */
    public DataOutputStream getToServer() {
        return toServer;
    }
    
    /**
     * 
     * @return 
     */
    public JTextField getJTFServerIP() {
        return jtfServerIP;
    }
    
    /**
     * 
     * @return 
     */
    public JTextField getJTFServerPort() {
        return jtfServerPort;
    }
    
    /**
     * 
     * @return 
     */
    public JTextField getJTFUsername() {
        return jtfUsername;
    }
    
    /**
     * 
     * @return 
     */
    public JPasswordField getJPFPassword() {
        return jpfPassword;
    }
    
    /**
     * 
     * @return 
     */
    public String getHost () {
        return host;
    }
    
    /**
     * 
     * @return 
     */
    public int getServerPort() {
        return serverPort;
    }
    
    /**
     * 
     * @param client 
     */
    public void addListeners(ClueLessClient client) {
        listeners.add(client);
    }
    
    /**
     * 
     */
    public void notifyListeners() {
        listeners.stream().forEach((client) -> {
            client.notified(player);
        });
    }
}
