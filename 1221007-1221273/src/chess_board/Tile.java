package chess_board;

import chess_pieces.Piece;

public abstract class Tile {

	protected final int tileCoordinate; //only set once at construction time
	
	Tile(int tileCoordinate) {
		this.tileCoordinate = tileCoordinate;
	}
	
	
	public abstract boolean isTileOccupied();
	
	public abstract Piece getPiece();
	
	public static final class EmptyTile extends Tile{
		
		EmptyTile(int coordinate) {
			super(coordinate); //Call the superclass constructor
		}
		
		@Override
		public boolean isTileOccupied() {
			
			return false;
			
		}
		
		@Override
		public Piece getPiece() {
			return null;
		}
		
		
	}
	
	public static final class OccupiedTile extends Tile {
		
		Piece pieceOnTile;
		
		OccupiedTile(int tileCoordinate, Piece pieceOnTile) {
			super(tileCoordinate);
			this.pieceOnTile = pieceOnTile;
		}
		
		@Override
		public boolean isTileOccupied () {
			return true;
		}
		
		@Override
		public Piece getPiece() {
			return this.pieceOnTile;
		}
		
	}
	
	
	
}
