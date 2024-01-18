package Game;

import Pieces.Piece;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Board {
    private Piece[][] board;  //[row][column]
    private ArrayList<Piece> sideboard;

    public Board (int boardSize){
        board = new Piece[boardSize][boardSize];
        this.sideboard = new ArrayList<>();
    }

    public MovePieceResponse movePiece(Piece piece, int newRow, int newCol){
        MovePieceResponse response = new MovePieceResponse(false, null);
        //check if Move is legit
        if (piece.checkIfMoveIsLegit(newRow, newCol, this)){
            //-> if move is possible
            if(checkIfSquareHasOpponentPiece(piece, newRow, newCol)) {
                sideboard.add(this.getPiece(newRow, newCol)); //move enemy Piece to the Sidebar if taken
            }
            board[newRow][newCol] = piece;  //write Piece into new position
            board[piece.getRow()][piece.getCol()] = null;     //Delete Piece from old position
            piece.movePiece(newRow, newCol);  //change coordinate fields of piece instance

            response.setSuccess(true);
            return response;
        }
        //-> if move is not possible
        response.setMessage("Move not possible");
        response.setSuccess(false);
        return response;
    }


    private boolean checkIfSquareHasOpponentPiece(Piece piece, int newRow, int newCol) {
        return this.getPiece(newRow, newCol) != null && !this.getPiece(newRow, newCol).getColour().equals(piece.getColour());
    }
    public Piece getPiece(int row, int col) {
        return this.board[row][col];
    }
    public void addPiece(Piece piece){
        board[piece.getRow()][piece.getCol()] = piece;
    }
}

