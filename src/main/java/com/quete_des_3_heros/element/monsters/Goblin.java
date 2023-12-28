/*package main.java.com.votrenomdeprojet.modele.ennemis;



public class Goblin extends Monster {

    public Goblin() {
        // Les dragons ont généralement des attributs plus élevés, donc vous pourriez augmenter ces valeurs par rapport à un Monster standard
        super(150, 150, 100, 100, 30, 15, 10, 18, 20, 10, 10, "image.pnj");
    }

}*/

package main.java.com.quete_des_3_heros.element.monsters;

import main.java.com.quete_des_3_heros.element.Monster;

public class Goblin extends Monster {

    public Goblin() {
        super(0, 0, "src/main/java/com/quete_des_3_heros/ressources/sprites/test_sprite.png", 100, 100, 30, 30,
         20, 10, 10, 10, 10, 10, 2);
    }
}
