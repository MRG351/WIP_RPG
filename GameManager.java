
/* GameFrame will this class.
    GameManager will contain
        1) infomration regarding game state (start screen, options, newGame, inGame, deathscreen, ingame options, etc...)
        2) data for game resources (files from which game data objects are constructed, saved data for inbetween executions, etc...)
        3) Perhaps the controls, mouse/keyboard, that sort of thing.
        4) Perhaps the game data itself, like the player reference, enemies references, map reference, level referemce
*/


/* 
 * I think I'm going to flip this around and have GameManager extend GameFrame instead, since certain management methods
 * e.g. setGameState(), may need references found in GameFrame
*/
public class GameManager extends GameFrame {

    public enum GameState {     
        /* I'm playing around with a naming scheme where the first 
         * word is START || INGAME || ... ie. the primary state and
         * the second word refers to the secondary state ie. a "state
         * within a state"
         */
         
        // DO NOT CHANGE THE ORDER OF THESE ENUMS! The names can change but once I save the values of these within a file I do not want the order, ie, the values, to change. 
        START_MENU,
        START_OPTIONS, //OPTIONS_MENU,
        START_LOAD, //LOAD_MENU,
        START_NEWGAME, // NEWGAME_MENU
        INGAME,
        // the following may be unnecessary and instead included in the panel associated with INGAME state
        INGAME_PAUSED,
        INGAME_OPTIONS,
        INGAME_SAVE,
        INGAME_LOAD,
        INGAME_DEATH,
        EXIT
        // DO NOT CHANGE THE ORDER OF THESE ENUMS! The names can change but once I save the values of these within a file I do not want the order, ie, the values, to change.
    };
    
    public GameState currentState;
    
    // references for data objects ie models
    // ...
        
    public GameManager(boolean quickStart) {       
        if (!quickStart) { // normal start
            this.currentState = GameState.START_MENU;        
            currentPanel = new StartMenuPanel(this);
            frame.add(currentPanel);
            frame.setSize(screenSize);
            frame.pack();
            frame.setVisible(true);
            // initialize START_MENU-state data compenents
        } else { // quickstart (for testing and until !quickstart is finalized)
           // init player, map, levels, enemies, etc...
            this.currentState = GameState.INGAME;
            currentPanel = new InGamePanel(this); 
            
            frame.add(currentPanel);
            frame.setSize(screenSize);
            frame.pack();
            frame.setVisible(true); 
        }        
    }
    
    public void setGameState(GameState nextState) {
        switch (nextState) {
            /* generic form:
               case a:
                1) save any necessary data to files
                2) save any necessary data to GameManager
                3) cleanup old data objects that are no longer needed
                4) initialize any new data objects that are needed.
                5) load any data needed from files
                6) load any data needed from between game states
                7) break
                This may require sub-swtich statements i.e. switch (nextState) {... switch (currentState) {... } ...}
            */
            case START_MENU:
                break;
            case START_OPTIONS:
                break;
            case START_LOAD:
                break;
            case START_NEWGAME:
                // need cases for currentState = START_MENU and currentState = INGAME
                currentState = nextState;
                newGame();
                break;
            case INGAME:
                // need cases for INGAME_LOAD and START_NEWGAME                
                currentState = nextState;
                saveGame();
                startGame();
                break;
            default:
                System.out.println("error in GameManager.setGameState(GameState nextState): unknown nextState argument");
                System.exit(1);
                break;
        }
    }
    
    // will need to reinit the key/mouse listeners for the following, but this can be done I think in the panels, if the listeners are attached to them.
    private void newGame() {
        //currentPanel.dispose(); // necessary?
        //player = new Player();
        currentPanel = new NewGamePanel(this);        
    }
    
    private void startGame() {
        // init map, level, enemies, etc...
        currentPanel = new InGamePanel(this);
    }
    
    private boolean saveGame() {
        
    }
    
    private boolean loadGame() {
        
    }
    
    private boolean saveGameOptions() {
        
    }
    
    private boolean loadGameOptions() {
        
    }
    
    @Override
    public void exitProcedure() {
       frame.dispose();
       System.exit(0);
    }
}