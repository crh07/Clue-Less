/**
 * Contributors: Ryan D Bonisch
 * Course: EN.605.401 Foundations of Software Engineering
 * Start: 02-Oct-16 15:55
 * End: 
 */
package Game;

import java.awt.Color;
import java.net.Socket;

/**
 * 
 * 
 */
public class Player {
    //
    private final int playerNum;
    
    //
    private Color color;
    
    //
    private String characterName;
    
    //
    private String detectiveLevel;
    
    //
    private final String username;
    
    //
    private final Socket socket;
    
    //
    private String ready;
    
    //
    private int charNumber;
    
    /**
     * 
     * @param playerNum 
     * @param username 
     * @param detectiveLevel 
     * @param socket 
     */
    public Player(
            int playerNum, String username,
            String detectiveLevel, Socket socket) {
        this.playerNum = playerNum;
        this.username = username;
        this.detectiveLevel = detectiveLevel;
        this.socket = socket;
        this.color = null;
        this.ready = "uncheck";
        this.charNumber = -1;
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
    public int getCharNum() {
        return charNumber;
    }
    
    /**
     * 
     * @param num 
     */
    public void setCharNum(int num) {
        this.charNumber = num;
    }
    
    /**
     * 
     * @return 
     */
    public String getDetectiveLevel() {
        return detectiveLevel;
    }
    
    /**
     * 
     * @param detectiveLevel 
     */
    public void setDetectiveLevel(String detectiveLevel) {
        this.detectiveLevel = detectiveLevel;
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
    
    /**
     * 
     * @return 
     */
    public String getReady() {
        return ready;
    }
    
    /**
     * 
     * @param ready 
     */
    public void setReady(String ready) {
        this.ready = ready;
    }
}
