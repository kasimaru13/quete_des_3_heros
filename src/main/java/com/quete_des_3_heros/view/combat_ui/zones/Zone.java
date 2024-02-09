package main.java.com.quete_des_3_heros.view.combat_ui.zones;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import main.java.com.quete_des_3_heros.element.Element;
import main.java.com.quete_des_3_heros.element.Monster;
import main.java.com.quete_des_3_heros.element.Obstacle;
import main.java.com.quete_des_3_heros.element.monsters.Dragon;
import main.java.com.quete_des_3_heros.element.monsters.Goblin;
import main.java.com.quete_des_3_heros.element.monsters.Skeleton;
import main.java.com.quete_des_3_heros.view.Constants;
import main.java.com.quete_des_3_heros.view.UI;

public class Zone {
    Image background;
    Element[][] board;
    ArrayList<Monster> monsters;

    public Zone(int phase_number) {
        board = new Element[Constants.NUMBER_OF_SQUARES][Constants.NUMBER_OF_SQUARES];
        monsters = new ArrayList<>();

        Scanner scanner = new Scanner(getClass().getResourceAsStream("/main/java/com/quete_des_3_heros/view/combat_ui/zones/zone" + phase_number + ".txt"));
        String current_line;

        while (scanner.hasNextLine()){
            current_line = scanner.nextLine();
            if (current_line.equals("background:")) {
                // Load background image
                try {
                    current_line = "/main/java/com/quete_des_3_heros/ressources/backgrounds/" + scanner.nextLine();
                    background = ImageIO.read(getClass().getResourceAsStream(current_line));
                } catch (IOException e) {
                    System.err.println("Impossible de charger l'image d'arrière plan");
                }
            }
            else if (current_line.equals("grid:")) {
                // Run through each line to determine the grid
                String[] line;
                for (int i = 0; i < Constants.NUMBER_OF_SQUARES; i++) {
                    line = scanner.nextLine().split("\s");
                    for (int j = 0; j < Constants.NUMBER_OF_SQUARES; j++) {
                        board[j][i] = findCorrespondingElement(line[j]);
                        if (board[j][i] != null){
                            board[j][i].setX(j);
                            board[j][i].setY(i);
                        }
                        if (board[j][i] instanceof Monster) {
                            monsters.add((Monster)board[j][i]);
                        }
                    }
                }
            }
        }
        scanner.close();
    }

    private Element findCorrespondingElement(String name) {
        switch (name) {
            // Import heroes
            case "warrior":
                return UI.getInstance().getWarrior();
            case "mage":
                return UI.getInstance().getMage();
            case "thief":
                return UI.getInstance().getThief();
            
            // Create monsters
            case "skeleton":
                return new Skeleton();
            case "goblin":
                return new Goblin();
            case "dragon":
                return new Dragon();

            // Create Obstacles
            case "rock1":
                try{
                    return new Obstacle(ImageIO.read(getClass().getResourceAsStream("/main/java/com/quete_des_3_heros/ressources/sprites/obstacles/rock1.png")).getScaledInstance(48, 48, Image.SCALE_SMOOTH));
                } catch (IOException e) {
                    System.err.println("Erreur dans la lecture de l'image d'un obstacle");
                    return new Obstacle(null);
                }
            case "rock2":
                try{
                    return new Obstacle(ImageIO.read(getClass().getResourceAsStream("/main/java/com/quete_des_3_heros/ressources/sprites/obstacles/rock2.png")).getScaledInstance(48, 48, Image.SCALE_SMOOTH));
                } catch (IOException e) {
                    System.err.println("Erreur dans la lecture de l'image d'un obstacle");
                    return new Obstacle(null);
                }
            case "tree1":
                try{
                    return new Obstacle(ImageIO.read(getClass().getResourceAsStream("/main/java/com/quete_des_3_heros/ressources/sprites/obstacles/tree1.png")).getScaledInstance(48, 48, Image.SCALE_SMOOTH));
                } catch (IOException e) {
                    System.err.println("Erreur dans la lecture de l'image d'un obstacle");
                    return new Obstacle(null);
                }
            
            // If not one of the previous, empty space
            default:
                return null;
        }
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public Image getBackground() {
        return background;
    }

    public Element[][] getBoard() {
        return board;
    }
}
