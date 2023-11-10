package UI;

import javax.swing.JPanel;

import Elements.Element;

public class Board extends JPanel{
    private Element[] board;

    Board(){
        board = new Element[256];
    }

    public Element[] getBoard() {
        return board;
    }
}
