
import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;



public class StartLoadMenu extends GameState {
    
    private enum Selectors {
        // Fill out
        option1(0),
        option2(1),
        etc(2);
        
        private final int _value;
        
        private Selectors(int value) {
            this._value = value;
        }
        
        public int getValue() {
            return _value;
        }
    };     
    
    private String[] loadLabels = {"OPTION1", "OPTION2", "ETC"};
    
    private int selector;
    
    private boolean select;    
    private boolean up;
    private boolean down;
    private boolean escape;
    
    private LoadMenuImage[] images;
    
    
    /* Experiment with putting this on its own thread via SwingUtilities.invokeLater */
    public StartLoadMenu(GameManager parent) {
        super(parent);
        init();
        
    }
    
    private void init() {
        loadImages();
        //selector = Selectors.NEW_GAME;
    }
    
    private boolean loadImages() {
        images = new LoadMenuImage[1];
        
        // use the ImageLoader to load each image ie create an imageManager.
        // use the imageManager to create the StartMenuImage with a bufferedImage.
        // then discard
        /*
        ImageLoader loader = new ImageLoader();
        
        BufferedImage spriteSheet = loader.load("path/to/images");   
                
        spriteSheet = loader.load("image");
        ss = new SpriteSheet(spriteSheet);
        im2 = new ImageManager(ss);        
        */
        return true;
    }
    
    // @Override
    // public void manageEvents(); ?? 
    
    @Override
    public void logic() {
        
        /*
        if (select) {
            
            if (selector == Selectors.NEW_GAME) {
                parent.setNextState(parent.GameStates.START_NEW_GAME);    
            }
            else if (selector == Selectors.LOAD_GAME) {
                parent.setNextState(parent.GameStates.START_LOAD_GAME);  
            }
            else if (selector == Selectors.OPTIONS) {
                parent.setNextState(parent.GameStates.START_OPTIONS);  
            }
            else if (selector == Selectors.EXIT) {
                // could prompt user if they're sure
                parent.setNextState(parent.GameStates.EXIT);  
            }
            else
                ;
        } 
        else if (escape) {
            // could prompt user if they're sure
            parent.setNextState(parent.GameStates.EXIT);  
        } 
        else
            ;
        */
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        if (paintInProgress)
            return;
        paintInProgress = true;
        
        // do painting...
        //super.paintComponent(g);
        
        // draw background...
        g.setColor(Color.BLACK);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        
        // draw images...
        for (int i=0; i<images.length; i++) {
            //g.drawImage(images[index].image, images[index].x, images[index].y, this.width, this.height, null);
        }
        
        /*
        // draw labels...
        for (int i=0; i<introLabels.length; i++) {
            if (selector == i) {
                g.setColor(Color.YELLOW);
                g.drawString(introLabels[i], 50, 50 + 60*i);    // note: replace with constants based on screensize
            } else {
                g.setColor(Color.WHITE);
                g.drawString(introLabels[i], 50, 50 + 60*i);    // note: replace with constants based on screensize
            }
        } 
        */
        
        paintInProgress = false;
    }
    
    private class LoadMenuImage {
        private BufferedImage image;
        private int x;
        private int y;
        
        public LoadMenuImage(BufferedImage image, int x, int y) {
            this.image = image;
            this.x = x;
            this.y = y;            
        }
    }
    
    /* Handle KeyPressed events from parents frame */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
				selector = (selector + loadLabels.length - 1) % loadLabels.length;
			} 
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				selector = (selector + 1) % loadLabels.length;
			} 
            else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				select = true;
			} 
            else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {   // may need to change this
                escape = true;
			} else
                ;
    }
    
    /* Handle KeyReleased events from parents frame */
    @Override
    public void keyReleased(KeyEvent e) {
        
	}
}