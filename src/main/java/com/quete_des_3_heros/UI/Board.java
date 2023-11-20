package main.java.com.quete_des_3_heros.UI;


import javax.swing.JPanel;

import main.java.com.quete_des_3_heros.elements.Element;

public class Board extends JPanel{
    private Element[] board;

    public Board(){
        board = new Element[256];
    }

    public Element[] getBoard() {
        return board;
    }
}
