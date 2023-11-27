package main.java.com.quete_des_3_heros.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import main.java.com.quete_des_3_heros.UI.components.GameButton;

public class TitleScreen extends JPanel implements ActionListener {
    private JLabel title;
    private Image backgroundImage;
    private GameButton start, leave;

    public TitleScreen(){
        backgroundImage = new ImageIcon("src/main/java/com/quete_des_3_heros/ressources/landscape.jpg").getImage();

        setFocusable(true);

        title = new JLabel("La Quête des 3 Héros");
        title.setFont(new Font("Serif", Font.BOLD, 50));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setForeground(Color.WHITE);

        start = new GameButton("Commencer");
        leave = new GameButton("Quitter");
        start.setBorder(new LineBorder(Color.lightGray));
        start.addActionListener(this);
        leave.setBorder(new LineBorder(Color.lightGray));
        leave.addActionListener(this);


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createRigidArea(new Dimension(0, 100)));
        add(title);
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(start);
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(leave);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start){

        }
        else if(e.getSource() == leave){
            System.exit(0);
        }
    }
}
