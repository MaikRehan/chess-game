package Pieces.Pieces;

import Game.Board;
import Util.Colour;
import Pieces.Piece;

public class King extends Piece {

    public King(int row, int col, Colour colour) {
        super(row, col, colour);
        if (colour == Colour.WHITE){
            this.unicode = '♔';
            this.pathToPNG = "assets/pieces/Chess_klt60.png";
        }
        if (colour == Colour.BLACK){
            this.unicode = '♚';
            this.pathToPNG = "assets/pieces/Chess_kdt60.png";
        }
    }


    @Override
    public boolean checkIfMoveIsLegit(int newRow, int newCol, Board board) {
        return !checkIfMoveIsBlocked(newRow, newCol, board)
                && checkIfMovementIsValid(newRow, newCol);
    }
    public boolean checkIfMoveIsBlocked(int newRow, int newCol, Board board) {
        if (board.getPiece(newRow, newCol) != null) {
            return board.getPiece(newRow, newCol).getColour() == this.getColour();
        }
        return false;
    }
    private boolean checkIfMovementIsValid(int newRow, int newCol) {

        //calculates the relative movement distance in regard to the original position
        int deltaRow = Math.abs(newRow - this.row);
        int deltaCol = Math.abs(newCol - this.col);

        //check if relative movement is either 0 or 1 in either direction
        return (deltaRow == 1 || deltaRow == 0) && (deltaCol == 1 || deltaCol == 0);

    }

}
