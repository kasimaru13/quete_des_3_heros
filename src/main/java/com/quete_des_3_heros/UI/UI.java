package main.java.com.quete_des_3_heros.UI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
        leftPanel.setBounds(0, 0, leftPanel.LEFTPANEL_WIDTH, 720);
        board.setBounds(280, 0, Constants.BOARD_SIZE, Constants.BOARD_SIZE);
        rightPanel.setBounds(280 + Constants.BOARD_SIZE, 0, rightPanel.RIGHTPANEL_WIDTH, 500);
        rightPanel.setBackground(Color.blue);

        add(leftPanel);
        add(board);
        add(rightPanel);
        
        
        setTitle("Quête des 3 héros");
        setSize(new Dimension(leftPanel.LEFTPANEL_WIDTH + Constants.BOARD_SIZE + rightPanel.RIGHTPANEL_WIDTH, 720));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
