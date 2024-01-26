package Pieces.Pieces;

import Game.Board;
import Util.Colour;
import Pieces.Piece;

public class Pawn extends Piece {

    public Pawn(int row, int col, Colour colour) {
        super(row, col, colour);
        if (colour == Colour.WHITE){
            this.unicode = '♙';
            this.pathToPNG = "assets/pieces/Chess_plt60.png";
        }
        if (colour == Colour.BLACK){
            this.unicode = '♟';
            this.pathToPNG = "assets/pieces/Chess_pdt60.png";
        }
    }


    @Override
    public boolean checkIfMoveIsLegit(int newRow, int newCol, Board board) {


        /*check if move is out of bounds CURRENTLY NOT POSSIBLE
        if(newRow > 7 || newRow < 0 || newCol > 7 || newCol < 0){
            return false;
        }
        */


        //can move if its one straight ahead and the tile is free OR if sideways and tile is occupied by enemy piece
        return checkForwardMove(newRow, newCol, board, this.colour) || checkDoubleForwardMove(newRow, newCol, board, this.colour)
                || checkDiagonalTake(newRow, newCol, board, this.colour);


        //if all three conditions are false
    }

    private boolean checkForwardMove(int newRow, int newCol, Board board, Colour colour) {
        //check if one forward is not blocked
        if(colour.equals(Colour.BLACK)){
            if ((newRow == this.row + 1) && (newCol == this.col)) {
                return board.getPiece(newRow, newCol) == null;
            }
        }
        if(colour.equals(Colour.WHITE)){
            if ((newRow == this.row - 1) && (newCol == this.col)) {
                return board.getPiece(newRow, newCol) == null;
            }
        }
        return false;
    }

    private boolean checkDoubleForwardMove(int newRow, int newCol, Board board, Colour colour) {
        //check if its first movement of the piece, and it's a wide opening (move 2 tiles forward)
        if(colour.equals(Colour.BLACK)){
            if ((newRow == this.row + 2) && (newCol == this.col) && (this.row == 1)){
                return (board.getPiece(newRow, newCol) == null) && (board.getPiece(newRow - 1, newCol) == null);
            }
        }
        if(colour.equals(Colour.WHITE)){
            if ((newRow == this.row - 2) && (newCol == this.col) && (this.row == 6)){
                return (board.getPiece(newRow, newCol) == null) && (board.getPiece(newRow - 1, newCol) == null);
            }
        }
        return false;
    }

    private boolean checkDiagonalTake(int newRow, int newCol, Board board, Colour colour) {
        //check if tile diagonal is taken by an enemy piece that can be taken.
        if(colour.equals(Colour.BLACK)){
            if (newRow == this.row + 1 && (newCol == this.col + 1 || newCol == this.col - 1)) {
                return (board.getPiece(newRow, newCol) != null) && (!board.getPiece(newRow, newCol).getColour().equals(this.colour));
            }
        }
        if(colour.equals(Colour.WHITE)){
            if (newRow == this.row - 1 && (newCol == this.col + 1 || newCol == this.col - 1)) {
                return (board.getPiece(newRow, newCol) != null) && (!board.getPiece(newRow, newCol).getColour().equals(this.colour));
            }
        }
        return false;
    }
}
