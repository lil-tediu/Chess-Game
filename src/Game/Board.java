package Game;

import Exceptions.NotValidSpotException;
import Pieces.Knight;

public class Board {
	private Spot[][] boxes;

	public Board() {
		this.resetBoard();
	}

	public Spot getBox(int x, int y) throws NotValidSpotException {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			throw new NotValidSpotException("The x and y coordinates must be between 0 and 7");
		}
		return boxes[x][y];
	}

	public void resetBoard() {
		initializeWhitePieces();
		initializeBlackPieces();
		initializeRemainingSpots();
	}

	private void initializeRemainingSpots() {
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				boxes[i][j] = new Spot(i, j, null);
			}
		}
	}

	private void initializeBlackPieces() {
		boxes[7][0] = new Spot(7, 0, new Rook(false));
		boxes[7][1] = new Spot(7, 1, new Knight(false));
		boxes[7][2] = new Spot(7, 2, new Bishop(false));
		// ...
		boxes[6][0] = new Spot(6, 0, new Pawn(false));
		boxes[6][1] = new Spot(6, 1, new Pawn(false));
		// ...
	}

	private void initializeWhitePieces() {
		boxes[0][0] = new Spot(0, 0, new Rook(true));
		boxes[0][1] = new Spot(0, 1, new Knight(true));
		boxes[0][2] = new Spot(0, 2, new Bishop(true));
		// ...
		boxes[1][0] = new Spot(1, 0, new Pawn(true));
		boxes[1][1] = new Spot(1, 1, new Pawn(true));
		// ...
	}
}
