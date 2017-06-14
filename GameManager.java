

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/* GameFrame will this class.
    GameManager will contain
        1) infomration regarding game state (start screen, options, newGame, inGame, deathscreen, ingame options, etc...)
        2) data for game resources (files from which game data objects are constructed, saved data for inbetween executions, etc...)
        3) Perhaps the controls, mouse/keyboard, that sort of thing.
        4) Perhaps the game data itself, like the player reference, enemies references, map reference, level referemce
*/

/* 
 * Highest Level Manager class for the Game. Contains the main gameloop, the frame, the panels/gamestate objects, and datamanagers.
*/
public class GameManager extends GameFrame implements Runnable {
    

    /* Will want to implement a stack based state machine on top Start and InGame states */
    public enum GameStates {     
        /* I'm playing around with a naming scheme where the first 
         * word is START || INGAME || ... ie. the primary state and
         * the second word refers to the secondary state ie. a "state
         * within a state"
         */
         
        // DO NOT CHANGE THE ORDER OF THESE ENUMS! The names can change but once I save the values of these within a file I do not want the order, ie, the values, to change. 
        START_MENU(0),
        START_OPTIONS(1), //OPTIONS_MENU,
        START_LOAD_GAME(2), //LOAD_MENU,
        START_NEW_GAME(3), // NEWGAME_MENU
        IN_GAME(4),
        NULL(5),
        EXIT(6);     
        // DO NOT CHANGE THE ORDER OF THESE ENUMS! The names can change but once I save the values of these within a file I do not want the order, ie, the values, to change.
                
        private final int _value;
        
        private GameStates(int value) {
            this._value = value;
        }
        
        public int getValue() {
            return _value;
        }
    };
    
    private int stateID = GameStates.NULL.getValue();
    private int nextState = GameStates.NULL.getValue();
    private GameState currentState;
    private Thread gameThread;
        
    // references for data objects ie models
    // ...
        
                
    public GameManager() {        
        /*
        if (!quickStart) { // normal start
            init();
        } else { // quickstart (for testing and until !quickstart is finalized)
            quickInit();            
        } */

        init();
        //gameLoop();
    }
    
    
    public void run() {
        gameLoop();
    } 
    
    public synchronized void start() {
        gameThread = new Thread(this);
        gameThread.start();
        //gameLoop();
    }
        
    /* Initializing of GameManager components */
    private void init() {
        
        //setNextState(GameStates.NULL.getValue());   // or something like this...
                
        frame.addKeyListener(new KeyManager());
        //this.addMouseListener(new MouseManager());
    }
    
    
    /* Initializing for quickStart of Game (ie skip the intro menu) */
    private void quickInit() {
        
        loadFiles();
        
        setNextState(GameStates.IN_GAME.getValue());
        changeState();
          
        frame.add(currentState);
        frame.pack();
        frame.setVisible(true); 
    }
    
    
    /* load files common to every GameState */
    public boolean loadFiles() {
        
        return true;
    }
    
    
    
    /* main game loop 
        Question: where does nextState get set/triggered?
    */
    public void gameLoop() {
        
        // set GameState to Start Menu. This must be done here, after GameManager is finished being constructed! (I think... hope...)
        setNextState(GameStates.START_MENU.getValue());
        changeState();
        
        final double amountOfTicks = 60D;
        final double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long lastTime = System.nanoTime();   
        
        
        while (stateID != GameStates.EXIT.getValue()) {
            // timer
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            
            if (delta >= 1) {
                currentState.logic();
                delta--;
            }
            // change gamestate if needed
            System.out.println("in gameloop: stateID = " + stateID);
            if (nextState != GameStates.NULL.getValue())
                changeState();
            
            currentState.repaint();
        }
        
    }         
    
    /* interface to setting the next game state 
    */
    public void setNextState(int newState) {        
        // if the user doesn't want to exit
        if ( nextState != GameStates.EXIT.getValue() ) {
            // set the next state
            nextState = newState;
        }        
    }
    
    
    
    /* handles changing the game state */
    public void changeState() {
        
        /*
        if (nextState != GameStates.EXIT) 
            delete(currentState) // from c++ code, not necessary in java but I might use.
          */  
          
        GameStates _nextState = GameStates.values()[nextState];  
        switch (_nextState) {
            /* generic form:
               case a:
                1) save any necessary data to files (ie transitional data)
                2) save any necessary data to GameManager (ie transitional data)
                4) initialize any new data objects that are needed by passing data from GameManager to GameState object
            */
            case START_MENU:
                currentState = new StartMenu(this);                                
                break;
            case START_OPTIONS:
                currentState = new StartOptionsMenu(this);
                break;
            case START_LOAD_GAME:
                currentState = new StartLoadMenu(this);                
                break;
            case START_NEW_GAME:                
                //currentState = new NewGameMain();    
                break;
            case IN_GAME:                
                //currentState = new InGame(stateID); // InGame uses stateID to determine if this is legit new game, a loaded game, an exit from a pause state, etc...
                break;
            case NULL:
                //MAKE A GAMESTATE OBJECT THAT DOESN'T REFERENCE ITS PARENT
                break;
            default: // for when nextState = EXIT
                break;
        }
        
        frame.add(currentState);
        frame.pack();
        frame.setVisible(true);
                
        // change the current stateID;
        stateID = nextState;
        
        // NULL the next state ID
        nextState = GameStates.NULL.getValue();
    }
    
    private boolean saveGame() {
        return true;
    }
    
    private boolean loadGame() {
        return true;
    }
    
    private boolean saveGameOptions() {
        return true;
    }
    
    private boolean loadGameOptions() {
        return true;
    }
    
    
    private class KeyManager extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {	
            currentState.keyPressed(e);
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			currentState.keyReleased(e);
		}
		
	}
}