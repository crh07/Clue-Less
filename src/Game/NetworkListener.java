/**
 * Contributors: Ryan D Bonisch
 * Course: EN.605.401 Foundations of Software Engineering
 * Start: 04-Oct-16 14:15
 * End: 
 */
package Game;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * 
 * 
 */
public class NetworkListener extends Thread {

    //
    private DataInputStream fromNetworkObject;
    
    //
    private ClueLessClient client = null;
    
    //
    private ClueLessServer server = null;
    
    //
    volatile boolean complete = false;
    
    /**
     * 
     * @param client
     * @param fromNetworkObject 
     */
    public NetworkListener(
            ClueLessClient client, DataInputStream fromNetworkObject) {
        this.fromNetworkObject = fromNetworkObject;
        this.client = client;
    }
    
    /**
     * 
     * @param server
     * @param fromNetworkObject 
     */
    public NetworkListener(
            ClueLessServer server, DataInputStream fromNetworkObject) {
        this.fromNetworkObject = fromNetworkObject;
        this.server = server;
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
                System.err.println(ex);
            }

            if (server != null)
                server.stringParser(response);
            else
                client.stringParser(response);
        }
    }
    
    /**
     * 
     */
    public void stopMe() {
        complete = true;
    }
}
