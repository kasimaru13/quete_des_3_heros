import main.java.com.quete_des_3_heros.UI.UI;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Launching the game");

        UI ui = UI.getInstance();
        ui.setVisible(true);
    }
}
