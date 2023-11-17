/*package main.java.com.votrenomdeprojet.modele.heros;


// Classe abstraite Hero qui servira de base pour tous les types de héros dans le jeu
public abstract class Hero {
    // Attributs de base pour un héros
    private int pv; // Points de vie actuels
    private int totalPV; // Points de vie maximum
    private int pm; // Points de magie actuels
    private int totalPM; // Points de magie maximum
    private int force; // Force physique du héros, affectant les dégâts des attaques physiques
    private int intelligence; // Intelligence du héros, affectant les dégâts des attaques magiques et la défense magique
    private int agilite; // Agilité du héros, affectant l'ordre d'attaque et l'esquive
    private int resistance; // Résistance aux dégâts physiques
    private int vitesse; // Vitesse de déplacement du héros
    private int esquive; // Capacité à esquiver les attaques
    private int precision; // Précision des attaques
    private String image; // Chemin d'accès à l'image du sprite du héros
    private int niveau; // Niveau actuel du héros
    private int xp; // Expérience actuelle
    private int xpAvantNiveau; // Expérience nécessaire pour atteindre le prochain niveau

    protected int defenseBonus; // Bonus de défense actuel


//---------------------------------------------------------------------------------------------------------------------------

    // Constructeur de la classe Hero
    public Hero(int pv, int totalPV, int pm, int totalPM, int force, int intelligence,
                int agilite, int resistance, int vitesse, int esquive, int precision,
                String image, int niveau, int xp, int xpAvantNiveau)
        {
        this.pv = pv;
        this.totalPV = totalPV;
        this.pm = pm;
        this.totalPM = totalPM;
        this.force = force;
        this.intelligence = intelligence;
        this.agilite = agilite;
        this.resistance = resistance;
        this.vitesse = vitesse;
        this.esquive = esquive;
        this.precision = precision;
        this.image = image;
        this.niveau = niveau;
        this.xp = xp;
        this.xpAvantNiveau = xpAvantNiveau;
        }

    // Getters et setters pour les attributs privés, Ces méthodes fournissent un accès contrôlé aux attributs de la classe
    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = Math.max(0, Math.min(pv, this.totalPV)); // Assure que les PV restent dans la plage valide
    }

    public int getTotalPV() {
        return totalPV;
    }

    public void setTotalPV(int totalPV) {
        this.totalPV = Math.max(0, totalPV); // Total PV ne doit pas être négatif
    }

    public int getPm() {
        return pm;
    }

    public void setPm(int pm) {
        this.pm = Math.max(0, Math.min(pm, this.totalPM)); // Assure que les PM restent dans la plage valide
    }

    public int getTotalPM() {
        return totalPM;
    }

    public void setTotalPM(int totalPM) {
        this.totalPM = Math.max(0, totalPM); // Total PM ne doit pas être négatif
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = Math.max(0, force); // La force ne doit pas être négative
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = Math.max(0, intelligence); // L'intelligence ne doit pas être négative
    }

    public int getAgilite() {
        return agilite;
    }

    public void setAgilite(int agilite) {
        this.agilite = Math.max(0, agilite); // L'agilité ne doit pas être négative
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = Math.max(0, resistance); // La résistance ne doit pas être négative
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = Math.max(0, vitesse); // La vitesse ne doit pas être négative
    }

    public int getEsquive() {
        return esquive;
    }

    public void setEsquive(int esquive) {
        this.esquive = Math.max(0, esquive); // L'esquive ne doit pas être négative
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = Math.max(0, precision); // La précision ne doit pas être négative
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image; // Aucune validation nécessaire pour l'instant, sauf si vous avez des règles spécifiques pour le chemin de l'image
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = Math.max(1, niveau); // Le niveau ne doit pas être inférieur à 1
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = Math.max(0, xp); // L'expérience ne doit pas être négative
    }

    public int getXpAvantNiveau() {
        return xpAvantNiveau;
    }

    public void setXpAvantNiveau(int xpAvantNiveau) {
        this.xpAvantNiveau = Math.max(0, xpAvantNiveau); // L'expérience avant le niveau ne doit pas être négative
    }

//---------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------

public void subirDegats(int degats) {
    int degatsEffectifs = Math.max(0, degats - defenseBonus); // Réduit les dégâts en fonction du bonus de défense
    //degats -= this.tempResistIncrease; // Réduire les dégâts par l'augmentation temporaire de la résistance.
    this.pv -= degatsEffectifs; // Assurez-vous que les PV ne descendent pas en dessous de 0.
    if (this.pv <= 0) {
        this.pv = 0;
        // Gérer le cas où le héros meurt.
        System.out.println("Le héros est mort.");
    }
    defenseBonus = 0;
}

public void defendre() {
    this.defenseBonus = this.getResistance();
    System.out.println("Le mage utilise un sort de protection, réduisant les dégâts des prochaines attaques de " + this.defenseBonus + " points.");
}
}*/

package main.java.com.votrenomdeprojet.modele.heros;

import main.java.com.votrenomdeprojet.modele.combattant;

public abstract class Hero extends combattant {
    private int niveau; // Niveau actuel du héros
    private int xp; // Expérience actuelle
    private int xpAvantNiveau; // Expérience nécessaire pour atteindre le prochain niveau
    protected int defenseBonus; // Bonus de défense actuel

    // Constructeur modifié de Hero
    public Hero(int pv, int totalPV, int pm, int totalPM, int force, int intelligence,
                int agilite, int resistance, int vitesse, int esquive, int precision,
                String image, int niveau, int xp, int xpAvantNiveau) {
        super(pv, totalPV, pm, totalPM, force, intelligence, agilite, resistance, vitesse, esquive, precision, image);
        this.niveau = niveau;
        this.xp = xp;
        this.xpAvantNiveau = xpAvantNiveau;
    }

    // Getters et Setters pour les attributs spécifiques à Hero
    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = Math.max(1, niveau);
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = Math.max(0, xp);
    }

    public int getXpAvantNiveau() {
        return xpAvantNiveau;
    }

    public void setXpAvantNiveau(int xpAvantNiveau) {
        this.xpAvantNiveau = Math.max(0, xpAvantNiveau);
    }


    @Override
    public void subirDegats(int degats) {
        int degatsEffectifs = Math.max(0, degats - defenseBonus);
        super.subirDegats(degatsEffectifs); // Appel de la méthode de base si nécessaire

        if (this.getPv() <= 0) {
            // Gérer le cas où le héros meurt.
            System.out.println("Le héros est mort.");
        }
        // Réinitialiser le defenseBonus après avoir subi des dégâts
        defenseBonus = 0;
    }

    // Méthode spécifique à Hero
    public void defendre() {
        this.defenseBonus = this.getResistance();
        System.out.println("Le héros se défend, augmentant sa résistance.");
    }
}

