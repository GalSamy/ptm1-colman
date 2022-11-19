package test;
import test.Word;
import test.Tile;

/**
 * The type Board.
 */
public class Board {
    /**
     * The Words.
     */
    Word[] words;
    /**
     * The Board.
     */
    Tile[][] board = new Tile[16][16];
    /**
     * The Board legal.
     */
    boolean BoardLegal;
    private static Board b = null;
    private Board(){
        for(int i = 1; i< 16; i++){
            for(int j = 1; j < 16;j++){
                board[i][j] = null;
            }
        }
        for(int i = 0; i < words.length; i++){

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
    private void AddWord(Word w, boolean add) throws IllegalArgumentException{ // need validation for placing words near other words.
        //*********************** Validators
        if (w.getRow() == 0 || w.getCol() == 0)
            throw new IllegalArgumentException("out of board boundaries");
        boolean surround = false; // a flag to check if the desired spots is surrounded by any tiles (as needed to place)
        if(board[w.getRow()][w.getCol()] != null && board[w.getRow()][w.getCol()].getLetter() != w.getTiles()[0].getLetter()) // check if the spot is free
            throw new IllegalArgumentException("already got a tile on this place");
            // check if the word is the first word and if so, any tile is on the star
            if (w.isVertical()) {
                if (isEmpty()) {

                    if (w.getCol() != 8) {
                        throw new IllegalArgumentException("first word doesn't have a tile on the star");
                    }
                }
                if (w.getRow() + w.getTiles().length > 15) {
                    throw new IllegalArgumentException("word can't fit in the board");
                }// check if the word can fit in the board
                if (w.getCol() != 1 && w.getCol() != 15 && w.getRow() != 1) {
                    // check the surrounding of the first tile of desired word
                    if (board[w.getRow() - 1][w.getCol()] != null || board[w.getRow() + 1][w.getCol()] != null && board[w.getRow() - 1][w.getCol()] != null) {
                        surround = true;
                    }
                }
                if (w.getCol() == 1 && w.getRow() == 1){
                    if (board[w.getRow() + 1][w.getCol()] != null)
                    surround = true;
                }
            }
            else {
                if (w.getCol() + w.getTiles().length > 15) {
                    throw new IllegalArgumentException("word can't fit in the board");
                }// check if the word can fit in the board

                if (isEmpty()) { // check if the word is the first word and if so, any tile is on the star
                    if (w.getRow() != 8) {
                        throw new IllegalArgumentException("first word doesn't have a tile on the star");
                    }
                }
                if (w.getRow() != 1 && w.getRow() != 15 && w.getCol() != 1) {
                    if (board[w.getRow() - 1][w.getCol()] != null || board[w.getRow() + 1][w.getCol()] != null && board[w.getRow() - 1][w.getCol()] != null) {
                        surround = true;
                    }
                }
            }

        //***********************

        if(w.isVertical()){ // the word is vertical; we'll loop on the rows
            for (int R = 0; R < w.getTiles().length; R++){ // R stands for Row, validate that we can put tiles on the desired spots
                if (board[w.getRow()][w.getCol()] != null && board[w.getRow()][w.getCol()].getLetter() != w.getTiles()[R].getLetter()){
                    throw new IllegalArgumentException("spot taken by another word");
                } // if the spot has a different letter in it then the desired tile to insert, we can't place the word

            }
            for (int R = 0; R < w.getTiles().length;R++){
                board[w.getRow()+R][w.getCol()] = w.getTiles()[R];
            }
        }else { // the word is not vertical
            for (int C = 0; C < w.getTiles().length; C++){ // C stands for Column
                board[w.getRow()][w.getCol()+C] = w.getTiles()[C];
            }
        }
    }
    private boolean isEmpty(){
        for (int i = 1; i< 16; i++)
        {
            for (int j= 1; j <16;j++){
                if(board[i][j] != null){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean validateTile(Tile t,Word w, boolean vertical){
        if (vertical){
            if (w.getCol() != 1 && w.getCol() != 15 && w.getRow() != 1) {
                // check the surrounding of the first tile of desired word
                if (board[w.getRow() - 1][w.getCol()] != null || board[w.getRow() + 1][w.getCol()] != null && board[w.getRow() - 1][w.getCol()] != null) {

                }
            }
        }
        return true;
    }
}
