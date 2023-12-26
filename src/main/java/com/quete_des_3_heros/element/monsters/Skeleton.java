/*package main.java.com.votrenomdeprojet.modele.ennemis;



public class Skeleton extends Monster {

    public Skeleton() {
        // Les dragons ont généralement des attributs plus élevés, donc vous pourriez augmenter ces valeurs par rapport à un Monster standard
        super(100, 100, 50, 50, 15, 15, 10, 10, 8, 10, 8, "image.pnj");
    }

}*/

package main.java.com.quete_des_3_heros.element.monsters;

import main.java.com.quete_des_3_heros.element.Monster;

public class Skeleton extends Monster {

    public Skeleton() {
        super(30, 30, 100, 100, 10, 15, 10,
         18, 14, 10, 10, "image.pnj");
    }
}