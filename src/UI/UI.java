package UI;

import javax.swing.JPanel;

public class UI extends JPanel {
    Board board;
    LeftPanel leftPanel;
    RightPanel rightPanel;

    public UI(){
       board = new Board();
       leftPanel = new LeftPanel();
       rightPanel = new RightPanel();
    }
}
