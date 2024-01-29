package Pieces.Pieces;

import Game.Board;
import Util.Colour;
import Pieces.Piece;

public class Queen extends Piece {
    public Queen(int row, int col, Colour colour) {
        super(row, col, colour);
        if (colour == Colour.WHITE){
            this.unicode = '♕';
            this.pathToPNG = "assets/pieces/Chess_qlt60.png";
        }
        if (colour == Colour.BLACK){
            this.unicode = '♛';
            this.pathToPNG = "assets/pieces/Chess_qdt60.png";
        }
    }

    @Override
    public boolean checkIfMoveIsLegit(int newRow, int newCol, Board board) {
        return checkIfMovementIsValid(newRow, newCol)
                && !checkIfMovementIsBlocked(newRow, newCol, board);
    }

    public boolean checkIfMovementIsBlocked(int newRow, int newCol, Board board) {
        // check if target square is blocked by a piece of the same colour
        if (board.getPiece(newRow, newCol) != null) {
            if (board.getPiece(newRow, newCol).getColour() == this.colour)
                return true;
        }

        //counter variables
        int currentCol = this.col;
        int currentRow = this.row;

        //movement is horizontal
        if (this.row == newRow) {
            int distance = Math.abs(this.col - newCol) - 1;

            for (int i = 0; i < distance; i++) {
                if (this.col < newCol)
                    currentCol++;
                else
                    currentCol--;
                if (board.getPiece(currentRow, currentCol) != null)
                    return true;
            }
            return false;
        }
        //movement is vertical
        if (this.col == newCol) {
            int distance = Math.abs(this.row - newRow) - 1;

            for (int i = 0; i < distance; i++) {
                if (this.row < newRow)
                    currentRow++;
                else
                    currentRow--;
                if (board.getPiece(currentRow, currentCol) != null)
                    return true;
            }
            return false;
        }
        //movement is diagonal
        if(Math.abs(newRow - this.row) == Math.abs(newCol - this.col)) {
            int distance = Math.abs(this.row - newRow) - 1;
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
        return false;
    }
    private boolean checkIfMovementIsValid(int newRow, int newCol) {
        //can move straight and diagonal

        //calculates the relative movement distance in regard to the original position
        int deltaRow = Math.abs(newRow - this.row);
        int deltaCol = Math.abs(newCol - this.col);

        //check if relative movement is either 0 or 1 in either direction
        // OR if movement is diagonal
        return (deltaRow > 0 && deltaCol == 0) || (deltaRow == 0 && deltaCol > 0)
                || deltaRow - deltaCol == 0;

    }
}
