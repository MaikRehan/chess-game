package GUI;

import Game.Controller;
import Game.MovePieceResponse;
import Pieces.Piece;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@Getter
@Setter
public class GUI extends JFrame{

    private static int BOARD_SIZE = 8;
    private static int CHESSBOARD_PANEL_SIZE = 475;
    private static int SQUARE_SIZE = 60;

    private JFrame frame;
    private JButton[][] chessBoardSquares = new JButton[BOARD_SIZE][BOARD_SIZE];
    private JPanel chessBoard;
    private Controller controller;
    private JButton selectedSquare; // To keep track of the selected square
    private Piece selectedPiece; // To keep track of the selected piece
    private boolean aPieceIsCurrentlySelected = false;
    private int selectedRow = -1, selectedCol = -1;
    private Component lastEntered;


    Color lightColor = new Color(255, 206, 158);  // Light color for squares
    Color darkColor = new Color(209, 139, 71);    // Dark color for squares
    Color selectedColor = new Color(246, 78, 24);
    Color possibleMoveColour = new Color(176, 213, 88, 255);


    public GUI() {
        this.controller = new Controller();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chessBoard = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        add(chessBoard, BorderLayout.CENTER);

        paintBoard();
        updateIcons();

        setSize(CHESSBOARD_PANEL_SIZE, CHESSBOARD_PANEL_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void updateIcons() {
        for(int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (controller.getPiece(row, col) != null) {
                    chessBoardSquares[row][col].setIcon(new ImageIcon(controller.getPiece(row, col).getPathToPNG()));
                }else {
                    chessBoardSquares[row][col].setIcon(null);
                }
            }
        }
    }
    private void paintBoard() {

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton square = new JButton();

                MyMouseAdapter mouseListener = new MyMouseAdapter();
                square.addMouseListener(mouseListener);
                square.addMouseMotionListener(mouseListener);
                square.setFocusPainted(false);
                if ((row + col) % 2 == 0) {
                    square.setBackground(lightColor);
                } else {
                    square.setBackground(darkColor);
                }
                chessBoardSquares[row][col] = square;
                chessBoard.add(square);
            }
        }

    }

    private void handleMousePressed(MouseEvent e) {

        selectedSquare = (JButton) e.getSource();
        selectedRow = getSelectedRow(selectedSquare);
        selectedCol = getSelectedCol(selectedSquare);
        selectedPiece = controller.getPiece(selectedRow, selectedCol);
        selectedSquare = (JButton) e.getSource();
        highlightAllPossibleMoves();
        turnOnHighlightColorSquare(selectedRow, selectedCol);


    }
    private void handleMouseReleased(MouseEvent e) {
        int targetRow = getSelectedRow((JButton) lastEntered);
        int targetCol = getSelectedCol((JButton) lastEntered);

        //if selected square has no piece
        if(selectedPiece == null)
            return;

        //if a piece is dragged on its original square -> i.e. it's a click
        if ((selectedPiece.getRow() == targetRow) && (selectedPiece.getCol() == targetCol)){

            turnOffHighlightColorSquare();
            clearSelectedSquare();
            return;
        }

        //move the piece to a new square
        MovePieceResponse response = controller.movePiece(selectedPiece.getRow(), selectedPiece.getCol(), targetRow, targetCol);
         //   controller.printBoard();
        if (response.isSuccess()) {
            updateIcons();
        }else{
            JOptionPane.showMessageDialog(this, response.getMessage());
            updateIcons();
        }
        turnOffHighlightColorSquare();
        clearSelectedSquare();
    }
    private void turnOnHighlightColorSquare(int selectedRow, int selectedCol){
        selectedPiece = controller.getPiece(selectedRow, selectedCol);
        if (selectedPiece != null) {
            chessBoardSquares[selectedPiece.getRow()][selectedPiece.getCol()].setBackground(selectedColor);
        }
    }
    private void highlightAllPossibleMoves(){
        boolean[][] possibleMovesArray = controller.getAllValidMovesArray(selectedPiece);
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                if(possibleMovesArray[row][col]){
                    chessBoardSquares[row][col].setBackground(possibleMoveColour);
                }
            }
        }
    }
    private void turnOffHighlightColorSquare(){
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 0) {
                    chessBoardSquares[row][col].setBackground(lightColor);
                } else {
                    chessBoardSquares[row][col].setBackground(darkColor);
                }

            }
        }
    }
    private void clearSelectedSquare(){
        selectedSquare = null;
        selectedPiece = null;
        selectedRow = -1;
        selectedCol = -1;
    }
    private int getSelectedRow(JButton square) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (chessBoardSquares[row][col] == square) {
                    return row;
                }
            }
        }
        return -1; // Square not found
    }
    private int getSelectedCol(JButton square) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (chessBoardSquares[row][col] == square) {
                    return col;
                }
            }
        }
        return -1; // Square not found
    }
    private class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            handleMousePressed(e);
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            handleMouseReleased(e);
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            lastEntered = e.getComponent();
        }

    }
    public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
          @Override
          public void run() {
              new GUI();
          }
      });
  }
}



