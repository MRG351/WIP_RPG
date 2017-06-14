import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * GameFrame: Extends GameManager
 * Contains the JFrame (main component for the GUI) as well as all auxillary information (screen dimension) and
 * components (key/mouse listeners and addition swing componants such as the JPanel...)
 */
public class GameFrame
{
    
    public double WIDTH, HEIGHT;
    public Dimension screenSize;    
    //private GraphicsDevice device; // alternative
    
    protected JFrame frame;
      
    public GameFrame() { 
        createPartControl();     
    }
    
    private void createPartControl() {
        
    // 1) Get Dimensions
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = screenSize.getWidth();
        HEIGHT = screenSize.getHeight();
       
        /* Alternatively
         * GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
         * device = environment.getDefaultScreenDevice(); // get the main monitor
         * device.setFullScreenWindow(frame); // add at the bottom
          
         * device = (GraphicsEnvironment.getLocalGraphicsEnvironment()).getDefaultScreenDevice();
         * device.setFullScreenWindow(frame);
        */
       
        // create sub-panel components, if necessary
        // e.g. buttonPanel = new ButtonPanel(this, game);
       
    // 2) Create frame and set default operations
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });
        
    // 3) Set frame properties
        frame.setUndecorated(true); // may remove this
        frame.setResizable(false); // experiment with true
        frame.setSize(screenSize);
       
    // 4) Create and attach listeners, may want to attach these to the panels.
        // frame.addKeyListener(new KeyManager());
        // frame.addMouseListener(new MouseManager());
    
        //frame.setBounds(getBounds()); // use instead of frame.setSize, if at all.  
    }   
    
    protected Rectangle getBounds() {
        Rectangle f = frame.getBounds();
        Rectangle w = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getMaximumWindowBounds();
        f.x = (w.width - f.width) / 2;
        f.y = (w.height - f.height) / 2;
        return f;
    }
    
    /* exit procedure just for the Frame on frame exit. GameManager will have its own exitProcedure() method */
    public void exitProcedure() {
       frame.dispose();
       System.exit(0);
    }
}
