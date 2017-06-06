
import javax.swing.EventQueue;
import javax.swing.JFrame;

public class Game implements Runnable {
	
    private final boolean quickStart;
    
    public Game(boolean quickStart) {
        this.quickStart = quickStart;
    }
    
	@Override
	public void run() {
		new GameManager(quickStart);
	}
	
	public static void main(String[] args) {		
        boolean quickStart = false;
        for (String arg : args) {
            if (arg.equals("-quickstart"))
               quickStart = true;
        }
		SwingUtilities.invokeLater(new Game(quickStart);
	}
}