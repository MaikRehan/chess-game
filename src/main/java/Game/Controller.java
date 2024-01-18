package Game;

import Pieces.Piece;
import Pieces.Pieces.*;
import Util.Colour;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.PrintWriter;

@Getter
@Setter
public class Controller {
    private Board board;
    private Color activePlayerColor;

    public Controller(){
        createBoard(8);
    }

    public void createBoard(int boardSize){
        board = new Board(boardSize);
        initialBoardFill();
    }
    public MovePieceResponse movePiece(int oldRow, int oldCol, int newRow, int newCol){
        return board.movePiece(board.getPiece(oldRow, oldCol), newRow, newCol);
    }
    public Piece getPiece(int row, int col){
        return board.getPiece(row, col);
    }
    private void initialBoardFill(){
        // initial board fill

        //fill white side
        board.addPiece(new Rook(7, 0, Colour.WHITE));
        board.addPiece(new Knight(7,1, Colour.WHITE));
        board.addPiece(new Bishop(7,2, Colour.WHITE));
        board.addPiece(new Queen(7,3, Colour.WHITE));
        board.addPiece(new King(7,4, Colour.WHITE));
        board.addPiece(new Bishop(7,5, Colour.WHITE));
        board.addPiece(new Knight(7,6, Colour.WHITE));
        board.addPiece(new Rook(7, 7, Colour.WHITE));

        for (int i = 0; i < 8; i++){
            board.addPiece(new Pawn(6, i, Colour.WHITE));
        }
        // fill black side
        board.addPiece(new Rook(0, 0, Colour.BLACK));
        board.addPiece(new Knight(0,1, Colour.BLACK));
        board.addPiece(new Bishop(0,2, Colour.BLACK));
        board.addPiece(new Queen(0,3, Colour.BLACK));
        board.addPiece(new King(0,4, Colour.BLACK));
        board.addPiece(new Bishop(0,5, Colour.BLACK));
        board.addPiece(new Knight(0,6, Colour.BLACK));
        board.addPiece(new Rook(0, 7, Colour.BLACK));

        for (int i = 0; i < 8; i++){
            board.addPiece(new Pawn(1, i, Colour.BLACK));
        }

    }
    public void printBoard(){
        PrintWriter printer = new PrintWriter(System.out, true);

        for (int row = 7; row >= 0; row--) {
            StringBuilder line = new StringBuilder();
            for (int col = 0; col < 8; col++) {
                if (board.getPiece(row, col) != null) {
                    line.append(board.getPiece(row, col).getUnicode());
                }else {
                    line.append("  ");
                }
            }
            printer.println(line);
        }
    }

    /*

    -handles game state
    -turns
    -checkmate
    -initial board fill
    */
}
