/**
 * Contributors: Ryan D Bonisch
 * Course: EN.605.401 Foundations of Software Engineering
 * Start: 02-Oct-16 15:55
 * End: 
 */
package Game;

import java.awt.Color;
import java.io.Serializable;
import java.net.Socket;

/**
 * 
 * 
 */
public class Player implements Serializable {
    //
    private final int playerNum;
    
    //
    private Color color;
    
    //
    private String characterName;
    
    //
    private final String irlName;
    
    //
    private final String username;
    
    //
    private final Socket socket;
    
    /**
     * 
     * @param playerNum 
     * @param username 
     * @param irlName 
     * @param socket 
     */
    public Player(int playerNum, String username, String irlName, Socket socket) {
        this.playerNum = playerNum;
        this.username = username;
        this.irlName = irlName;
        this.socket = socket;
    }
    
    /**
     * 
     * @return 
     */
    public int getPlayerNum() {
        return playerNum;
    }
    
    /**
     * 
     * @return 
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * 
     * @param color 
     */
    public void setColor(Color color) {
        this.color = color;
    }
    
    /**
     * 
     * @return 
     */
    public String getCharacterName() {
        return characterName;
    }
    
    /**
     * 
     * @param characterName 
     */
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
    
    /**
     * 
     * @return 
     */
    public String getIRLName() {
        return irlName;
    }
    
    /**
     * 
     * @return 
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * 
     * @return 
     */
    public Socket getSocket() {
        return socket;
    }
}
