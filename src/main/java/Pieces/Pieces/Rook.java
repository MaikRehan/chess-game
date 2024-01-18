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
        return false;
    }
}
