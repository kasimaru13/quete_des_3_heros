package main.java.com.quete_des_3_heros.view.combat_ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import main.java.com.quete_des_3_heros.view.Constants;
import main.java.com.quete_des_3_heros.view.components.Profile;

/**
 * Panel on the left of the combat UI. Contains the playing order, displaying it with characters' profiles.
 */
public class LeftPanel extends JPanel{
    private JLabel title;
    private List<Profile> priority_queue;
    private JPanel profile_queue;
    private JScrollPane scrollable_profile_queue;

    public LeftPanel(){
        priority_queue = new ArrayList<>();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        title = new JLabel("Ordre de jeu :");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        profile_queue = new JPanel();
        profile_queue.setLayout(new BoxLayout(profile_queue, BoxLayout.Y_AXIS));
        scrollable_profile_queue = new JScrollPane(profile_queue);
        profile_queue.setAutoscrolls(true);
        scrollable_profile_queue.setBorder(new EmptyBorder(0, 0, 0, 0));

        add(Box.createRigidArea(new Dimension(0,15)));
        add(title);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(scrollable_profile_queue);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (!priority_queue.isEmpty()) {
            int i = 1;
            profile_queue.removeAll();
            for (Profile prof : priority_queue) {
                profile_queue.add(prof);
                profile_queue.add(Box.createRigidArea(new Dimension(0, 50)));
                prof.changeBorderColor(Color.black);
                if (priority_queue.indexOf(prof) != priority_queue.size() - 1){
                    drawArrowLine(g, Constants.LEFTPANEL_WIDTH/2, scrollable_profile_queue.getY() + i * (75 + 50) - 5, Constants.LEFTPANEL_WIDTH/2, scrollable_profile_queue.getY() + i * (75 + 50) - 45, 10, 5);
                }
                i++;
            }
        }
    }

    /**
     * Draw an arrow line between two points.
     * @param g the graphics component.
     * @param x1 x-position of first point.
     * @param y1 y-position of first point.
     * @param x2 x-position of second point.
     * @param y2 y-position of second point.
     * @param d  the width of the arrow.
     * @param h  the height of the arrow.
     */
    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }

    public void setPriority_queue(List<Profile> priority_queue) {
        if (this.priority_queue != null) {
            this.priority_queue = priority_queue;
            revalidate();
            repaint();
        } 
    }

    public List<Profile> getPriority_queue() {
        return priority_queue;
    }
}
