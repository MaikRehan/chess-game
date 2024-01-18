package Pieces;

import Game.Board;
import Util.Colour;
import Util.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Piece {

    protected int row;
    protected int col;
    protected Status status;
    protected Colour colour;
    protected char unicode;
    protected String pathToPNG;


    public Piece(int row, int col, Colour colour) {
        this.row = row;
        this.col = col;
        this.colour = colour;
        this.status = Status.ACTIVE;
    }

    public abstract boolean checkIfMoveIsLegit(int newRow, int newCol, Board board);
    public void movePiece(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
    }
}


