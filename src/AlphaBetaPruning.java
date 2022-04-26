
public class AlphaBetaPruning implements Player
{
    private final String defaultName = "Alpha Beta";
    private String name;

    public AlphaBetaPruning() { 
        this(null); 
    }
    public AlphaBetaPruning (String name) { 
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
    private int id;

    public void init (int id2, int msecPerMove, int rows, int cols) {
    	id = id2;
    }
    public int alphabeta (Connect4Board board, int depth, int alpha, int beta, boolean maximizingPlayer) {
    	if (depth == 0 || board.numEmptyCells() == 0) {
    		Connect4Game game = null;
    		return game.calcScore(board, id);

    	}
    	int value;
        if (maximizingPlayer) {
        	value = -999999;
        	
        	for (int i = 0; i < board.numCols(); i ++) {
        		board.move(i, id);
        		value = Math.max(value, alphabeta (board, depth -1, alpha, beta, false));
        		board.unmove (i, id);
        		if (value > beta) {
        			break;
        			
        		}
        		alpha = Math.max(alpha, value);
        	}
        	return value;
        }
        value = 999999;
    	
    	for (int i = 0; i < board.numCols(); i ++) {
    		board.move(i, id);
    		value = Math.min(value, alphabeta (board, depth -1, alpha, beta, true));
    		board.unmove (i, id);
    		if (value > alpha) {
    			break;
    			
    		}
    		beta = Math.max(beta, value);
    	}
    	return value;
    }
    			
    public void calcMove (Connect4Board board, int oppMoveCol, Arbitrator arb) {
    	int maximumSearchDepth = 1;

    while (!arb.isTimeUp() && board.numEmptyCells() < maximumSearchDepth) {
	    int bestMove = alphabeta(board, maximumSearchDepth, -99999999, 99999999, true);
	    try {
			arb.setMove(bestMove);
		} catch (TimeUpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    maximumSearchDepth ++;
    }
	}
}
