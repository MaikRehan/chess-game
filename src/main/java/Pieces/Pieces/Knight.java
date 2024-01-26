package Pieces.Pieces;

import Game.Board;
import Util.Colour;
import Pieces.Piece;

public class Knight extends Piece {
    public Knight(int row, int col, Colour colour) {
        super(row, col, colour);
        if (colour == Colour.WHITE) {
            this.unicode = '♘';
            this.pathToPNG = "assets/pieces/Chess_nlt60.png";
        }
        if (colour == Colour.BLACK) {
            this.unicode = '♞';
            this.pathToPNG = "assets/pieces/Chess_ndt60.png";
        }
    }

    @Override
    public boolean checkIfMoveIsLegit(int newRow, int newCol, Board board) {
        return !checkIfMoveIsBlocked(newRow, newCol, board)
        && checkIfMovementIsValid(newRow, newCol, board);
    }

    private boolean checkIfMoveIsBlocked(int newRow, int newCol, Board board) {
        //square is blocked by alli piece
        if (board.getPiece(newRow, newCol) != null) {
            //check if target square is same colour
            return board.getPiece(newRow, newCol).getColour() == this.getColour();

        }
        return false;
    }

    private boolean checkIfMovementIsValid(int newRow, int newCol, Board board) {

        //calculates the relative movement distance in regard to the original position
        int deltaRow = Math.abs(newRow - this.row);
        int deltaCol = Math.abs(newCol - this.col);

        //checks if the relative movement distance is a combination of 1 and 2 for the row and column
        return deltaRow == 2 && deltaCol == 1 || deltaRow == 1 && deltaCol == 2;

    }
}
