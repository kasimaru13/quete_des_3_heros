package main.java.com.quete_des_3_heros.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.border.LineBorder;


/**
 * Button component with specific styling.
 */
public class GameButton extends JButton implements MouseListener {
    String text;

    public GameButton(String text){
        super(text);

        setFocusable(false);
        setSize(new Dimension(500, 80));
        setFont(new Font("Arial", Font.PLAIN, 18));
        setBackground(Color.gray);
        setForeground(Color.WHITE);
        setPreferredSize(new Dimension(250, 60));
        setMaximumSize(new Dimension(250, 60));
        setBorder(new LineBorder(Color.lightGray));
        setAlignmentX(CENTER_ALIGNMENT);

        addMouseListener(this);
    }


    // Mouse listener methods to create a hover effect -------------------------

    @Override
    public void mouseEntered(MouseEvent arg0) {
        setBackground(new Color(100,100,100));
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        setBackground(Color.gray);
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // DO NOTHING
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // DO NOTHING
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // DO NOTHING
    }
}
