public class Minimax implements Player
	{
	    private final String defaultName = "Minimax";
	    private String name;
	    private int id;
	    public Minimax() { 
	        this(null); 
	    }
	    public Minimax (String name) { 
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
	    public void init (int id2, int msecPerMove, int rows, int cols) {
	    	id = id2;
	    }
	    public int findBestMoveRecursive(int depth, Connect4Board board) {
	    	int max = 0;
	    	if(depth == 0) {
	    		Connect4Game game = null;
	    		return game.calcScore(board, id);
	    	}
	    	for (int i = 0; i < board.numCols(); i ++) {
	    		board.move(i, id);
	    		int current = findBestMoveRecursive (depth - 1, board);
	    		board.unmove (i, id);
	    		if (current > max) {
	    			max = current;
	    		}
	    	}
	    	return max;		
	    }
	    public int findBestMove(int depth, Connect4Board board) {
	    	int max = 0;
	    	int bestMove = 0;
	    	for (int i = 0; i < board.numCols(); i ++) {
	    		board.move(i, id);
	    		int current = findBestMoveRecursive (depth - 1, board);
	    		board.unmove (i, id);
	    		if (current > max) {
	    			max = current;
	    			bestMove = i;
	    		}
	    	}
	    	return bestMove;		
	    }
	    public void calcMove (
	        Connect4Board board, int oppMoveCol, Arbitrator arb) {
	    	int maximumSearchDepth = 1;
	    	while (!arb.isTimeUp() && board.numEmptyCells() < maximumSearchDepth) {
	    	    int bestMove = findBestMove(maximumSearchDepth, board);
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


