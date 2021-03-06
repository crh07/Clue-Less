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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    // Continue to play
    private boolean continueToPlay = true;

    // Wait for the player to mark a cell
    private boolean waiting = true;
    
    // Wait to be connected to server to do other things
    private boolean connected = false;
    
    //
    private Player player;
    
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
        
        /**
         * Here is the Window Listener.  Essentially when the action of close
         * the window happens, we will issue a System.exit(0); but not before 
         * performing other actions.
         */
        addWindowListener(new WindowAdapter()
        {
            /**
             * Here is the windowClosing method for when the window is preparing
             * to close.
             * @param e 
             */
            @Override
            public void windowClosing(WindowEvent e)
            {
                /**
                 * 
                 */
                if (player != null) {
                    if (player.getSocket().isConnected()) {
                        try {
                            DataOutputStream toServer = new DataOutputStream(player.getSocket().getOutputStream());
                            toServer.writeUTF("[server][" + player.getPlayerNum() + "][set][disconn][0]");
                            player.getSocket().shutdownInput();
                            player.getSocket().shutdownOutput();
                            player.getSocket().close();
                        } catch (IOException ex) {
                            Logger.getLogger(ClueLessClient.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
                /**
                 * exit the system
                 */
                System.exit(0);
            }
        });
    }
    
    /**
     * 
     */
    public final void connectionUI() {
        
        //
        Connection connectPanel = new Connection(this);
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
        Lobby lobby = new Lobby(this, player);
        
        //
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
    @Override
    public void notifyListeners() {
    }
    
    /**
     * 
     * @param player
     */
    public void notified(Player player) {
        this.connected = true;
        this.player = player;
        setupGameUI();
    }
    
    /**
     * 
     */
    @Override
    public void run() {
        
    }
}
