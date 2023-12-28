/*package main.java.com.votrenomdeprojet.modele.ennemis;



public class Dragon extends Monster {

    public Dragon() {
        // Les dragons ont généralement des attributs plus élevés, donc vous pourriez augmenter ces valeurs par rapport à un Monster standard
        super(200, 200, 100, 100, 50, 20, 10, 30, 20, 10, 15, "image.pnj");
    }

}*/

package main.java.com.quete_des_3_heros.element.monsters;

import main.java.com.quete_des_3_heros.element.Monster;

public class Dragon extends Monster {

    public Dragon() {
        super(0,
                0,
                "src/main/java/com/quete_des_3_heros/ressources/sprites/test_sprite.png",
                100,
                100,
                30,
                30,
                20,
                10,
                10,
                10,
                20,
                10,
                2);
    }
}

