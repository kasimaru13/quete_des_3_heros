package main.java.com.quete_des_3_heros.view.combat_ui;


import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import main.java.com.quete_des_3_heros.element.Element;
import main.java.com.quete_des_3_heros.element.Entity;
import main.java.com.quete_des_3_heros.element.heros.Warrior;
import main.java.com.quete_des_3_heros.view.Constants;

/**
 * Panel in the center of the combat UI. It is a grid, containing characters, monsters and obstacles. 
 * When the player can move, his available moves are displayed on the grid as green squares.
 */
public class Board extends JPanel implements MouseMotionListener{
    private int length, width;
    private Element[][] grid; // Array of 256 (16*16) values containing all the elements of the game to draw (characters, monsters and obstacles)
    private Image backgroundImage;
    private int mousePosition[]; // Position of the mouse as an index of an int[][]
    private int possibleMoves[][]; // TO GET RID OF WHEN BACKEND IS COMPLETE

    private ArrayList<Entity> entities; // Entities alive on the board

    /**
     *
     * @param length length of the board
     * @param width width of the board
     */
    public Board(int length, int width, ArrayList<Entity> entities){
        this.length = length;
        this.width = width;
        this.entities = entities;
        grid = new Element[this.length][this.width];

        // Initialize mousePosition to not be in the board at the start of the game
        mousePosition = new int[2];
        mousePosition[0] = -1;
        mousePosition[1] = -1;

        possibleMoves = new int[16][16]; // TO GET RID OF WHEN BACKEND IS COMPLETE

        // Import background image and player sprites (GET RID OF THE PLAYER SPRITE WHEN BACKEND IS COMPLETE)
        try {
            backgroundImage = ImageIO.read(new File("src/main/java/com/quete_des_3_heros/ressources/backgrounds/grass.png")).getSubimage(0, 0, Constants.BOARD_SIZE, Constants.BOARD_SIZE);
        } catch (IOException e) {
            System.out.println("Erreur dans la lecture des images du jeu");
            System.exit(0);
        }

        addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw background
        g.drawImage(backgroundImage, 0, 0, null);

        // Draw grid
        if (Constants.GRID != 0){
            g.setColor(new Color(0, 0, 0, 50));
            for (int i = Constants.SPRITE_SIZE; i < Constants.BOARD_SIZE; i += Constants.SPRITE_SIZE + 1){
                g.drawLine(0, i, Constants.BOARD_SIZE, i);
                g.drawLine(i, 0, i, Constants.BOARD_SIZE);
            }
        }

        // Draw sprites
        for(Entity e : entities){
            g.drawImage(e.getSprite(), e.getX() + (e.getX()*48), e.getY() + (e.getY()*48), null);
        }


        // Draw possible moves
        drawPossibleMoves(possibleMoves, g);
    }

    /**
     * Function to call in a paint method. It paints green squares at the positions passed in parameter, on the graphics
     * passed in parameter. It is used to indicate to the player where he can move.
     * On the square hovered by the mouse, the color will be darker for HMI.
     * @param positions Array of array of ints, containing the position of the avaible moves to draw. The positions
     * are put as indexes (ex: [0,1] for first column, second row).
     * @param g Graphics to draw on
     */
    private void drawPossibleMoves(int positions[][], Graphics g){
        int x,y;

        for (int i[] : positions){
            // Calculation of x and y in pixels :
            // Equivalent to the following calculation but with only one array acces i[0] * Constants.SPRITE_SIZE + (i[0] - 1) * Constants.GRID
            x = i[0] * (Constants.SPRITE_SIZE + Constants.GRID);
            y = i[1] * (Constants.SPRITE_SIZE + Constants.GRID); 

            // Select color of rectangle
            if (i[0] == mousePosition[0] && i[1] == mousePosition[1]){
                g.setColor(new Color(0, 125, 0, 125)); // If mouse hover
            }
            else{
                g.setColor(new Color(0, 175, 0, 125)); // If not mouse hover
            }

            // Draw the square
            g.fillRect(x, y, Constants.SPRITE_SIZE, Constants.SPRITE_SIZE);
        }
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        // DO NOTHING
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // x and y from pixels to index
        int x = e.getX() / Constants.SPRITE_SIZE;
        int y = e.getY() / Constants.SPRITE_SIZE;
        boolean redraw = false; // Need to redraw panel

        // Check if the mouse is mooved significantly
        if (x != mousePosition[0]){
            mousePosition[0] = x;
            redraw = true;
        }
        if (y != mousePosition[1]){
            mousePosition[1] = y;
            redraw = true;
        }

        // Repaint board if mouse moved significantly
        if (redraw){
            revalidate();
            repaint();
        }
    }

    /**
     * Function addEntity() add Entity on the coordinates indicated on the grid
     * @param entity Entity (Hero, Monster, Obstacle)
     * @param x x-axis position on the grid
     * @param y y-axis position on the grid
     */
    public void addEntity(Entity entity, int x, int y){
        entity.setX(x);
        entity.setY(y);
        grid[entity.getX()][entity.getY()] = entity;
    }

    /**
     * Function moveEntity() move the Entity on new coordinates indicated on the grid
     * @param entity Entity (Hero, Monster, Obstacle)
     * @param newX new x-axis position on the grid
     * @param newY new y-axis position on the grid
     */
    public void moveEntity(Entity entity, int newX, int newY){
        grid[entity.getX()][entity.getY()] = null;
        entity.setX(newX);
        entity.setY(newY);
        grid[entity.getX()][entity.getY()] = entity;
    }

    /**
     * Function getEntity() return the Element of the coordinates indicated on the grid
     * @param x x-axis position on the grid
     * @param y y-axis position on the grid
     * @return Element
     */
    public Element getEntity(int x, int y){
        return grid[x][y];
    }

    public int getBoardLength(){
        return length;
    }

    public void setBoardLength(int length){
        this.length = length;
    }

    public int getBoardWidth(){
        return width;
    }

    public void setBoardWidth(int width){
        this.width = width;
    }

    public Element[][] getGrid() {
        return grid;
    }
    public void setGrid(Element[][] grid){
        this.grid = grid;
    }
}
