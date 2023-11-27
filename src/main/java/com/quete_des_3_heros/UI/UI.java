package main.java.com.quete_des_3_heros.UI;

import java.awt.Dimension;
import javax.swing.JFrame;

public class UI extends JFrame {
    Board board;
    LeftPanel leftPanel;
    RightPanel rightPanel;

    public UI(){
       board = new Board();
       leftPanel = new LeftPanel();
       rightPanel = new RightPanel();

       initUI();
    }

    private void initUI(){
        // setLayout(null);
        // leftPanel.setBounds(0, 0, leftPanel.LEFTPANEL_WIDTH, 720);
        // board.setBounds(280, 0, Constants.BOARD_SIZE, Constants.BOARD_SIZE);
        // rightPanel.setBounds(280 + Constants.BOARD_SIZE, 0, rightPanel.RIGHTPANEL_WIDTH, 720);

        // add(leftPanel);
        // add(board);
        // add(rightPanel);

        TitleScreen titleScreen = new TitleScreen();
        add(titleScreen);
        
        setTitle("Gnir's Needle");
        setSize(new Dimension(leftPanel.LEFTPANEL_WIDTH + Constants.BOARD_SIZE + rightPanel.RIGHTPANEL_WIDTH, 720));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
