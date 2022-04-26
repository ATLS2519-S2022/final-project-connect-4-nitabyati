
public class GreedyPlayer implements Player
{
    private final String defaultName = "Greedy Player";
    private String name;
    private int columns;
    private int id;

    public GreedyPlayer() { 
        this(null); 
    }
    public GreedyPlayer (String name) { 
        this.name = (name == null) ? defaultName : name; 
    }
    public String name() { 
        return name; 
    }
    public void setName (String name) { 
        if (name != null) {
            this.name = name; 
        }
    }
    public void init (int idTwo, int msecPerMove, int rows, int cols) {
    	columns = cols;
    	id = idTwo;
    }
    public void calcMove (
        Connect4Board board, int oppMoveCol, Arbitrator arb) {
    	Connect4Game game = null;
    	int max = 0;
    	int bestMove = 0;
    	for (int i = 0;
    			i < columns; i++) {
    		board.move(i, id);
    		if (max < game.calcScore(board, id)) {
    			bestMove = i; 
    			max = game.calcScore(board, id);
    		}
    board.unmove(i, id);
    
    		
    	
    	}
       try {
		arb.setMove(bestMove);
	} catch (TimeUpException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
}
