package Game;

public class Player {
		private boolean whiteSide; 
	    private boolean humanPlayer; 
	  
	    public boolean isWhiteSide() { 
	        return this.whiteSide == true; 
	    } 
	    
	    public boolean isHumanPlayer() { 
	        return this.humanPlayer == true; 
	    }

		public void setWhiteSide(boolean whiteSide) {
			this.whiteSide = whiteSide;
		}

		public void setHumanPlayer(boolean humanPlayer) {
			this.humanPlayer = humanPlayer;
		} 
} 
