
import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class Game //implements Runnable  {
{
    //private final boolean quickStart;
    public static GameManager gameManager;
    
    public Game() {
        gameManager = new GameManager();
        //new GameManager(quickStart);
    }
    
    /*
	@Override
	public void run() {
		//new GameManager(quickStart);
        new GameManager();
	} */
	
	public static void main(String[] args) {	
	
        boolean quickStart = false;
        for (String arg : args) {
            if (arg.equals("-quickstart"))
               quickStart = true;
        }

        //Game game = new Game();
      //  gameManager.start();
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Game();
                gameManager.start();
            }
        });
        
        //SwingUtilities.invokeLater(new Game());
		//SwingUtilities.invokeLater(new Game(quickStart));
	}
}