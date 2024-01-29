package Pieces.Pieces;

import Game.Board;
import Util.Colour;
import Pieces.Piece;

public class Bishop extends Piece {
    public Bishop(int row, int col, Colour colour) {
        super(row, col, colour);
        if (colour == Colour.WHITE){
            this.unicode = '♗';
            this.pathToPNG = "assets/pieces/Chess_blt60.png";
        }
        if (colour == Colour.BLACK){
            this.unicode = '♝';
            this.pathToPNG = "assets/pieces/Chess_bdt60.png";
        }
    }

    @Override
    public boolean checkIfMoveIsLegit(int newRow, int newCol, Board board) {
        return checkIfMovementIsValid(newRow, newCol)
                && !checkIfMovementIsBlocked(newRow, newCol, board);
    }

    public boolean checkIfMovementIsBlocked(int newRow, int newCol, Board board){

        // check if target square is blocked by a piece of the same colour
        if (board.getPiece(newRow, newCol) != null){
            if (board.getPiece(newRow, newCol).getColour() == this.colour)
                return true;
        }

        //check for as many times as there is squares between the 2 pieces
        int currentRow = this.row;
        int currentCol = this.col;
        int distance = Math.abs(this.row - newRow) - 1; //amount of squares between the two pieces.
        for (int i = 0; i < distance; i++) {
            if (this.row < newRow)
                currentRow++;
            else
                currentRow--;
            if (this.col < newCol)
                currentCol++;
            else
                currentCol--;
            if (board.getPiece(currentRow, currentCol) != null)
                return true;
        }
        return false;
    }

    public boolean checkIfMovementIsValid(int newRow, int newCol){
        //can only move diagonal

        //calculates the relative movement distance in regard to the original position
        int deltaRow = Math.abs(newRow - this.row);
        int deltaCol = Math.abs(newCol - this.col);

        return deltaRow - deltaCol == 0;
    }
}
