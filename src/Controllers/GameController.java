package Controllers;

import java.util.LinkedList;
import java.util.Queue;

import Elements.Element;
import UI.UI;

public class GameController {
    UI ui;
    Queue<Element> playOrder;

    public GameController(){
        ui = new UI();
        playOrder = new LinkedList<Element>();
    }
}
