import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * WIP
 * 1) Going to use a thread(run method) instead of timer for RPG. Finish fixing this up
 * 2) I will have multiple panel classes for a Game, so right now this one is generic.
 *  
 */
public class GenericPanel extends JPanel implements Runnable
{
    private GameFrame frame;
    private GameModel gameModel; // this is used (but not necessary, I think) to obtain a reference to model components in order to call their draw() methods.
    private boolean paintInProgress;
    
    // These components are for a timer.
	//private final int DELAY = 10;
	//private Timer timer;
	
    public GamePanel(GameFrame frame, GameModel gameModel) {
        this.frame = frame;
        this.gameModel = gameModel;
        
        this.setIgnoreRepaint(true);
        this.setPreferredSize(new Dimension(frame.screenSize));
        
        paintInProgress = false;
        
        // to initialize a timer.
        //timer = new Timer(DELAY, this);
        //timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        if (paintInProgress)
            return;
        paintInProgress = true;
        
        // do painting...
        super.paintComponent(g);
        
        // draw background
        g.setColor(Color.BLUE);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        
        // draw components...
        
        // Sync and reset
        //Toolkit.getDefaultToolkit().sync();
        paintInProgress = false;
    }
    
    //@Override
    //public void actionPerformed(ActionEvent e) {
    //    repaint();      ///666+
    //}
}
