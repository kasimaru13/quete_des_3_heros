package Controllers;

import java.util.LinkedList;
import java.util.Queue;
import java.awt.EventQueue;
import Elements.Element;
import UI.Board;
import UI.UI;

public class GameController {
    //UI ui;
    Queue<Element> playOrder;
    Board board;// Temporary, to modify when implementing the UI


    public GameController() throws Exception {
        // EventQueue.invokeLater(() -> {
        //     UI ui = new UI();
        //     ui.setVisible(true);
        // });
        playOrder = new LinkedList<Element>();
        board = new Board();
    }
}
