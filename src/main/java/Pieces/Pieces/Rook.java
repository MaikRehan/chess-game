package Pieces.Pieces;

import Game.Board;
import Util.Colour;
import Pieces.Piece;

public class Rook extends Piece {
    public Rook(int row, int col, Colour colour) {
        super(row, col, colour);
        if (colour == Colour.WHITE){
            this.unicode = '♖';
            this.pathToPNG = "assets/pieces/Chess_rlt60.png";
        }
        if (colour == Colour.BLACK){
            this.unicode = '♜';
            this.pathToPNG = "assets/pieces/Chess_rdt60.png";
        }

    }

    @Override
    public boolean checkIfMoveIsLegit(int newRow, int newCol, Board board) {
        return !checkIfMoveIsBlocked(newRow, newCol, board)
                && checkIfMovementIsValid(newRow, newCol);
    }

    public boolean checkIfMoveIsBlocked(int newRow, int newCol, Board board){
        // check if target square is blocked by a piece of the same colour
        if (board.getPiece(newRow, newCol) != null){
            if (board.getPiece(newRow, newCol).getColour() == this.colour)
                return true;
        }
        //check for as many times as there is squares between the 2 pieces
        int currentCol = this.col;
        int currentRow = this.row;

        //movement is horizontal
        if(this.row == newRow){
            int distance = Math.abs(this.col - newCol) - 1;

            for (int i = 0; i < distance; i++){
                if(this.col < newCol)
                    currentCol++;
                else
                    currentCol--;
                if (board.getPiece(currentRow, currentCol) != null)
                    return true;
            }
        }
        //movement is vertical
        if(this.col == newCol){
            int distance = Math.abs(this.row - newRow) - 1;

            for ( int i = 0; i < distance; i++) {
                if (this.row < newRow)
                    currentRow++;
                else
                    currentRow--;
                if (board.getPiece(currentRow, currentCol) != null)
                    return true;
            }
        }
        // if all spaces in between are null
        return false;
    }
    public boolean checkIfMovementIsValid(int newRow, int newCol){
        //can only move straight

        //calculates the relative movement distance in regard to the original position
        int deltaRow = Math.abs(newRow - this.row);
        int deltaCol = Math.abs(newCol - this.col);

        //either only move horizontal or only move vertical
        return (deltaRow > 0 && deltaCol == 0) || (deltaRow == 0 && deltaCol > 0);
    }
}
