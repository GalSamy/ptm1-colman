package test;
import test.Word;

public class Board {
    Word[] words;
    private static Board b = null;
    private Board(){

    }
    public static  Board getBoard(){
        if(b == null)
            b = new Board();
        return b;
    }
}
