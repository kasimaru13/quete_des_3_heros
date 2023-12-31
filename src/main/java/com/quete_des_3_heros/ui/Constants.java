package main.java.com.quete_des_3_heros.ui;

/**
 * Class containing all constant values of the game
 */
public class Constants {
    public static final int SPRITE_SIZE = 48;
    public static final int GRID = 1; // 1 if grid, 0 if no grid
    public static final int NUMBER_OF_SQUARES = 16;
    public static final int BOARD_SIZE = NUMBER_OF_SQUARES * SPRITE_SIZE + (NUMBER_OF_SQUARES - 1) * GRID; // 15 is for the grid (there are 15 lines, each of 1 pixel)
    public static final int WINDOW_WIDTH = BOARD_SIZE + 2 * 280;
    public static final int WINDOW_HEIGHT = BOARD_SIZE;
    public static final int LEFTPANEL_WIDTH = 280;
    public static final int RIGHTPANEL_WIDTH = 280;
}
