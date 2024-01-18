package Game;

public class BoardTest {

    public void testIfBoardFillsCorrectly(){
        Board board = new Board(8);

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(board.getBoard()[i][j]);
            }
            System.out.println();
        }
    }
}
