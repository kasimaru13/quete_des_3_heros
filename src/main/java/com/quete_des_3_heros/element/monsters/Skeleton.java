/*package main.java.com.votrenomdeprojet.modele.ennemis;



public class Skeleton extends Monster {

    public Skeleton() {
        // Les dragons ont généralement des attributs plus élevés, donc vous pourriez augmenter ces valeurs par rapport à un Monster standard
        super(100, 100, 50, 50, 15, 15, 10, 10, 8, 10, 8, "image.pnj");
    }

}*/

package main.java.com.quete_des_3_heros.element.monsters;

import main.java.com.quete_des_3_heros.element.Monster;
import main.java.com.quete_des_3_heros.view.combat_ui.Board;

public class Skeleton extends Monster {

    public Skeleton() {
        super(0,
                0,
                "src/main/java/com/quete_des_3_heros/ressources/sprites/skeleton.png",
                100,
                100,
                30,
                30,
                20,
                10,
                10,
                10,
                10,
                10,
                0.15,
                2,
                2,
                "Squelette");
    }

    @Override
    public void attack(Board board, int targetX, int targetY) {
        getDamage(board, targetX, targetY, this.strength);
    }
}