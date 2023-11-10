package UI;

import Elements.Element;

public class Board {
    private Element[] board;

    Board(){
        board = new Element[256];
    }

    public Element[] getBoard() {
        return board;
    }
}
