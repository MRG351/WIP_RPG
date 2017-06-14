
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

/* GameState base class */
public abstract class GameState extends JPanel {
    
    protected GameManager parent; // save a reference to this so that events can change GameState
    protected boolean paintInProgress;
    
    public GameState(GameManager parent) {
        this.parent = parent;
        
        this.setIgnoreRepaint(true);
        this.setPreferredSize(new Dimension(parent.screenSize));
    }

    // public abstract void manageEvents(); ?? 
    
    public abstract void logic();
    
    public abstract void paintComponent(Graphics g); 
    
    public abstract void keyPressed(KeyEvent e);
    
    public abstract void keyReleased(KeyEvent e);
    
}