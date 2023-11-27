package main.java.com.quete_des_3_heros.UI;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UI extends JFrame {
    static UI instance;

    private UI(){
        TitleScreen titleScreen = new TitleScreen();
        add(titleScreen);
        
        setTitle("Gnir's Needle");
        setSize(new Dimension(280 + Constants.BOARD_SIZE + 280, 720));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static UI getInstance(){
        if (instance == null){
            instance = new UI();
        }
        return instance;
    }

    public void startGame(){
        getContentPane().removeAll();

        add(new CombatUI());

        revalidate();

        repaint();
    }
}
