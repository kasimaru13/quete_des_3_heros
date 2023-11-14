package UI;

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
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

        container.add(leftPanel);
        container.add(board);
        container.add(rightPanel);

        //add(container);
        add(new TitleScreen());
        
        setTitle("Quête des 3 héros");
        setSize(Constants.WINDOW_SIZE, Constants.WINDOW_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
