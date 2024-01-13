package main.java.com.quete_des_3_heros.view.combat_ui.zones;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
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

        try {
            Scanner scanner = new Scanner(new File("src/main/java/com/quete_des_3_heros/view/combat_ui/zones/zone" + phase_number + ".txt"));
            String current_line;

            while (scanner.hasNextLine()){
                current_line = scanner.nextLine();
                if (current_line.equals("background:")) {
                    // Load background image
                    try {
                        current_line = "src/main/java/com/quete_des_3_heros/ressources/backgrounds/" + scanner.nextLine();
                        System.out.println(current_line);
                        background = ImageIO.read(new File(current_line));
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
        } 
        catch (FileNotFoundException e) {
            System.err.println("Impossible de trouver le fichier d'initialisation de zone");
            UI.getInstance().nextStep(phase_number + 1, "Dialogue");
        }
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
            case "rock":
            try{
                return new Obstacle(ImageIO.read(new File("src/main/java/com/quete_des_3_heros/ressources/sprites/test_sprite.png")));
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
