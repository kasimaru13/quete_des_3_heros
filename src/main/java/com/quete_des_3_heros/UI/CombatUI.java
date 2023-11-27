package main.java.com.quete_des_3_heros.UI;

import java.awt.Color;

import javax.swing.JPanel;

public class CombatUI extends JPanel {
    Board board;
    LeftPanel leftPanel;
    RightPanel rightPanel;
    

    public CombatUI(){
        setBackground(Color.blue);
        setFocusable(true);
        
        board = new Board();
        leftPanel = new LeftPanel();
        rightPanel = new RightPanel();

        setLayout(null);
        leftPanel.setBounds(0, 0, leftPanel.LEFTPANEL_WIDTH, 720);
        board.setBounds(280, 0, Constants.BOARD_SIZE, Constants.BOARD_SIZE);
        rightPanel.setBounds(280 + Constants.BOARD_SIZE, 0, rightPanel.RIGHTPANEL_WIDTH, 720);

        add(leftPanel);
        add(board);
        add(rightPanel);
        setVisible(true);
    }
}
