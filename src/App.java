import main.java.com.quete_des_3_heros.ui.UI;
import java.awt.EventQueue;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Launching the game");

        EventQueue.invokeLater(() -> {
            UI ui = UI.getInstance();
            ui.setVisible(true);
        });
    }
}
