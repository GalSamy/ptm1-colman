package test;
import test.Word;
import test.Tile;

/**
 * The type Board.
 */
public class Board {
    String[] stringWords;
    /**
     * The Words.
     */
    Word[] words;
    /**
     * The Board.
     */
    Tile[][] board = new Tile[15][15];
    /**
     * The Board legal.
     */
    private static Board b = null;
    private Board(){
        for(int i = 0; i< 15; i++){
            for(int j = 0; j < 15;j++){
                board[i][j] = null;
            }
        }
        for (int i = 0; i < words.length; i++){
            stringWords[i] = words[i].getStringedWord();
        }
    }

    /**
     * Get board.
     *
     * @return the board
     */
    public static  Board getBoard(){
        if(b == null)
            b = new Board();
        return b;
    }
    /**
     * Add Words.
     * @param {
     * add words to the board. throws an exception in case of illegal add. acts as a validator and a word added helper function.
     * }
     */
    public boolean boardLegal(Word w) throws IllegalArgumentException{ // need validation for placing words near other words.
        if (w.vertical) {
            return validateWord(w, w.getCol(), w.getRow(), board);
        } else {
            return validateWord(w,w.getRow(), w.getCol() ,transposeBoard());
        }
    }
    private boolean isEmpty(){
        for (int i = 0; i< 15; i++)
        {
            for (int j= 0; j <15;j++){
                if(board[i][j] != null){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean validateWord (Word w, int Col, int Row, Tile[][] b) throws IllegalArgumentException{
        boolean flag = false;
        if ( w.getTiles().length + Row > 15 || Col < 0 || Col > 14 || Row < 0 || Row > 14){ // out of boundaries
            return false;
        }
        if(isEmpty() && Row+w.getTiles().length > 7 && Col == 7){ // board is empty and first word not on the star tile
            return true;
        }
        try{
            if(b[Col-1][Row] != null || b[Col][Row-1] != null || b[Col][Row+1] != null) // check the first tile surrounding tiles
                flag = true;
        }catch(Exception e){
            System.out.println("Tile placed on an edge" + " Word : " + w);
        }
        try{
            if(b[Col+w.getTiles().length+1][Row] != null || b[Col+w.getTiles().length][Row-1] != null || b[Col+w.getTiles().length][Row+1] != null) // check the last tile surroundings
                flag = true;
        }catch(Exception e){
            System.out.println("Tile placed on an edge" + " Word : " + w);
        }
        for (int i = 0; i < w.getTiles().length;i++){
            if (board[Col][Row + i] != null && board[Col][Row + i] != w.getTiles()[i]){
                return false;
            }
            try {
                if (b[Col-1][(Row+i)] != null)
                    flag = true;
            }catch(Exception e){}
            try{
                if (b[Col+1][(Row+i)] != null)
                    flag = true;
            }catch (Exception e){}
        }
        return flag;
    }
    boolean dictionaryLegal(Word w){
        return true;
    }


    Tile[][] transposeBoard() {

        int n = board.length, m = board[0].length;

        // create empty transpose matrix of size m*n
        Tile[][] M_transpose = new Tile[m][n];

        // traverse matrix M
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //assign M_transpose[j][i] as M[i][j]
                M_transpose[j][i] = board[i][j];
            }
        }
        return M_transpose;
    }
}
