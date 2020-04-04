package Game;

import java.util.List;

import Exceptions.NotValidSpotException;
import Pieces.King;
import Pieces.Move;
import Pieces.Piece;

public class Game {
	private Player[] players;
	private Board board;
	private Player currentTurn;
	private GameStatus status;
	private List<Move> movesPlayed;

	private void initialize(Player p1, Player p2) {
		players[0] = p1;
		players[1] = p2;

		board.resetBoard();

		if (p1.isWhiteSide()) {
			this.currentTurn = p1;
		} else {
			this.currentTurn = p2;
		}
		movesPlayed.clear();
	}

	public boolean isEnd() {
		return this.getStatus() != GameStatus.ACTIVE;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public boolean playerMove(Player player, int startX, int startY, int endX, int endY) throws NotValidSpotException {
		Spot startBox = board.getBox(startX, startY);
		Spot endBox = board.getBox(startY, endY);
		Move move = new Move(player, startBox, endBox);
		return this.makeMove(move, player);
	}

	private boolean makeMove(Move move, Player player) {
		Piece sourcePiece = move.getStart().getPiece();
		Piece destPiece = move.getEnd().getPiece();
		
		if (!isValidPlayer(player, sourcePiece)) {
			return false;
		}
		if (!isValidMove(move, sourcePiece)) {
			return false;
		}
		killPiece(move, destPiece);
		movesPlayed.add(move);
		movePiece(move);
		checkMate(player, destPiece);
		setPlayerTun();
		return true;
	}

	private void setPlayerTun() {
		if (this.currentTurn == players[0]) {
			this.currentTurn = players[1];
		} else {
			this.currentTurn = players[0];
		}
	}

	private void checkMate(Player player, Piece destPiece) {
		if (destPiece != null && destPiece instanceof King) {
			if (player.isWhiteSide()) {
				this.setStatus(GameStatus.WHITE_WIN);
			} else {
				this.setStatus(GameStatus.BLACK_WIN);
			}
		}
	}

	private void movePiece(Move move) {
		move.getEnd().setPiece(move.getStart().getPiece());
		move.getStart.setPiece(null);
	}

	private void killPiece(Move move, Piece destPiece) {
		if (destPiece != null) {
			destPiece.setKilled(true);
			move.setPieceKilled(destPiece);
		}
	}

	private boolean isValidPlayer(Player player, Piece sourcePiece) {
		if (player != currentTurn || sourcePiece == null || sourcePiece.isWhite() != player.isWhiteSide()) {
			return false;
		}
		return true;
	}

	private boolean isValidMove(Move move, Piece sourcePiece) {
		if (!sourcePiece.canMove(board, move.getStart(), move.getEnd())) {
			return false;
		}
		return true;
	}
}
