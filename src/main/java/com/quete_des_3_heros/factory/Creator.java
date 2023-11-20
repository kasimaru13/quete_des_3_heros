package main.java.com.quete_des_3_heros.factory;

import main.java.com.quete_des_3_heros.elements.Element;


/**
 * Classe abstraite Créatrice, dont les sous-classes désigneront les créateurs des différentes classes de la factory 
 * (design pattern).
 */
public abstract class Creator {
    public abstract Element createElement();
}
