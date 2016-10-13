/**
 * Contributors: Ryan D Bonisch
 * Course: EN.605.401 Foundations of Software Engineering
 * Start: 04-Oct-16 14:15
 * End: 
 */
package Game;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 */
public class NetworkListener extends Thread {

    //
    private DataInputStream fromNetworkObject;
    
    //
    private Lobby lobby = null;
    
    //
    private ClueLessServer server = null;
    
    //
    volatile boolean complete = false;
     
    /**
     * 
     * @param lobby
     * @param fromNetworkObject 
     */
    public NetworkListener(
            Lobby lobby, DataInputStream fromNetworkObject) {
        this.fromNetworkObject = fromNetworkObject;
        this.lobby = lobby;
    }
    
    /**
     * 
     * @param server
     * @param fromNetworkObject 
     */
    public NetworkListener(
            ClueLessServer server, DataInputStream fromNetworkObject) {
        this.server = server;
        this.fromNetworkObject = fromNetworkObject;
    }
    
    /**
     * 
     */
    @Override
    public void run() {
        while(!complete) {
            String response = "";

            try {
                response = fromNetworkObject.readUTF();
            } catch (IOException ex) {
                complete = true;
                Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(lobby != null)
            {
                try {
                    lobby.stringParser(response);
                } catch (IOException ex) {
                    Logger.getLogger(NetworkListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(server != null)
            {
                try {
                    server.stringParser(response);
                } catch (IOException ex) {
                    Logger.getLogger(NetworkListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * 
     */
    public void stopMe() {
        complete = true;
    }
}
