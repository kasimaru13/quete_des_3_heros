package main.java.com.quete_des_3_heros.view.dialogues;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import main.java.com.quete_des_3_heros.view.Constants;
import main.java.com.quete_des_3_heros.view.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Scanner;

/**
 * JPanel class displaying a string for the dialogue phase of the game.
 */
public class Dialogue extends JPanel implements KeyListener{
    private Image backgroundImage;
    private JLabel shownDialogue;
    private int phase;

    /**
     * Create a dialogue panel
     * @param phase_number Number of the current phase, used to find which dialogue to use
     */
    public Dialogue(int phase_number){
        phase = phase_number;

        setFocusable(true);

        // Load background image
        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/main/java/com/quete_des_3_heros/ressources/backgrounds/grass.png"))
                .getScaledInstance(Constants.WINDOW_WIDTH, Constants.WINDOW_WIDTH, Image.SCALE_DEFAULT);
        } catch (Exception e) {
            System.err.println("Erreur dans le chargement de l'image de fond de la phase de dialogue");
            System.exit(0);
        }

        setLayout(new GridBagLayout()); // Set layout to center vertically JLabel

        shownDialogue = new DialogueLabel(phase_number);
        // Add JLabel to the JPanel
        add(shownDialogue);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null); // Draws the background image
    }

    // KeyListener methods ------------------------------------------
    // Only when Enter or Space is released by the user do we start the next phase of the game

    @Override
    public void keyReleased(KeyEvent arg0) {
        // If the user presses Enter or the Spacebar, we start the next phase
        if (arg0.getKeyCode() == 10 || arg0.getKeyCode() == 32){
            UI.getInstance().nextStep(phase + 1, "Combat");
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // DO NOTHING
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        // DO NOTHING
    }


    /**
     * Label to display on a JPanel containing the dialogue of the current phase
     */
    private class DialogueLabel extends JLabel implements MouseListener {
        public DialogueLabel(int phase_number){
            // Load JLabel icon (down arrow)
            ImageIcon arrow = null;

            try {
                arrow = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/main/java/com/quete_des_3_heros/ressources/icons/arrow_down.png")));
            } catch (IOException e) {
                System.err.println("Impossible de charger l'icone de flèche");
            }

            setPreferredSize(new Dimension(Constants.WINDOW_WIDTH * 60/100, Constants.WINDOW_HEIGHT * 70/100));
            setFont(new Font(getFont().getName(), Font.PLAIN, 22));
            setForeground(Color.white);
            setBorder(new EmptyBorder(50, 50, 50, 50));
            setIcon(arrow);
            setVerticalTextPosition(JLabel.TOP);
            setHorizontalTextPosition(JLabel.CENTER);
            setIconTextGap(30);
            setText(findDialogue(phase_number));

            addMouseListener(this);
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(new Color(0, 0, 0, 50));
            g.fillRect(0, 0, getWidth(), getHeight());
            
            super.paintComponent(g);
        }

        /**
         * Finds the dialogue to display from the number passed. Prints an error if number mismatched.
         * Finds the dialogue text in files in the folder text. Each break line in the file means a forced break line
         * on the UI.
         * @param phase_number The current phase of the game (in String)
         * @return String containing the html format of the dialogue
         */
        private String findDialogue(int phase_number){
            String dialogue = "";

            dialogue = "<html><p>";
            
            Scanner scanner = new Scanner(getClass().getResourceAsStream("/main/java/com/quete_des_3_heros/view/dialogues/text/dialogue" + phase_number + ".txt"));

            // Read the file
            while (scanner.hasNextLine()){
                dialogue += scanner.nextLine() + "<br>";
            }
            dialogue += "</p></html>";

            scanner.close();

            return dialogue;
        }

        // Mouse listener methods --------------------------------------
        // Only when the mouse is released do we launch the next phase of the game

        @Override
        public void mouseReleased(MouseEvent arg0) {
            UI.getInstance().nextStep(phase + 1, "Combat");   
        }

        @Override
        public void mouseClicked(MouseEvent arg0) {
            // DO NOTHING
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
            // DO NOTHING
        }

        @Override
        public void mouseExited(MouseEvent arg0) {
            // DO NOTHING
        }

        @Override
        public void mousePressed(MouseEvent arg0) {
            // DO NOTHING
        }
    }
}