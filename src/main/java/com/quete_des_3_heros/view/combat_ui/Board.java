package main.java.com.quete_des_3_heros.view.combat_ui;


import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import main.java.com.quete_des_3_heros.element.Element;
import main.java.com.quete_des_3_heros.element.Entity;
import main.java.com.quete_des_3_heros.view.Constants;
import main.java.com.quete_des_3_heros.view.combat_ui.zones.Zone;

/**
 * Panel in the center of the combat UI. It is a grid, containing characters, monsters and obstacles. 
 * When the player can move, his available moves are displayed on the grid as green squares.
 */
public class Board extends JPanel implements MouseMotionListener{
    private Element[][] grid; // Array of 256 (16*16) values containing all the elements of the game to draw (characters, monsters and obstacles)
    private Image backgroundImage;
    private int mousePosition[]; // Position of the mouse as an index of an int[][]
    private int possibleMoves[][]; // tiles to highlight
    private int step; // 1 is move step, 2 is attack step, 3 is skill step

    /**
     *
     * @param length length of the board
     * @param width width of the board
     */
    public Board(Zone zone){
        step = 0;

        // Initialize grid
        grid = zone.getBoard();

        // Initialize background image
        backgroundImage = zone.getBackground();

        // Initialize mousePosition to not be in the board at the start of the game
        mousePosition = new int[2];
        mousePosition[0] = -1;
        mousePosition[1] = -1;

        possibleMoves = new int[16][16];

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


        // Draw entities
        Element current_element;
        for (int i = 0; i < Constants.NUMBER_OF_SQUARES; i++){
            for (int j = 0; j < Constants.NUMBER_OF_SQUARES; j++){
                current_element = grid[i][j];
                if (current_element != null) {
                    // Draw sprite
                    g.drawImage(current_element.getSprite(), i * (Constants.SPRITE_SIZE + Constants.GRID), j * (Constants.SPRITE_SIZE + Constants.GRID), null);
                }
            }
        }

        // Draw possible moves
        if (step != 0){
            drawPossibleMoves(possibleMoves, g);
        }
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
        Color col, col_hover;

        // Set color of highlight
        if (step == 1) {col = new Color(0, 125, 0, 125); col_hover = new Color(0, 175, 0, 125);}
        else {col = new Color(125, 0, 0, 125); col_hover = new Color(175, 0, 0, 125);}

        for (int i[] : positions){
            // Calculation of x and y in pixels :
            // Equivalent to the following calculation but with only one array acces i[0] * Constants.SPRITE_SIZE + (i[0] - 1) * Constants.GRID
            x = i[0] * (Constants.SPRITE_SIZE + Constants.GRID);
            y = i[1] * (Constants.SPRITE_SIZE + Constants.GRID); 

            // Select color of rectangle
            if (i[0] == mousePosition[0] && i[1] == mousePosition[1]){
                g.setColor(col); // If mouse hover
            }
            else{
                g.setColor(col_hover); // If not mouse hover
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

    public Element[][] getGrid() {
        return grid;
    }
    public void setGrid(Entity[][] grid){
        this.grid = grid;
    }

    public int[][] getPossibleMoves(){
        return possibleMoves;
    }

    public void setPossibleMoves(int[][] possibleMoves){
        this.possibleMoves = possibleMoves;
    }

    public int getStep(){
        return step;
    }
    public void setStep(int step){
        this.step = step;
    }
}
