package main.java.com.votrenomdeprojet.modele;

public abstract class combattant {
    protected int pv; // Points de vie
    protected int totalPV; // Points de vie maximum
    protected int pm; // Points de magie
    protected int totalPM; // Points de magie maximum
    protected int force; // Force physique ou magique
    protected int intelligence; // Intelligence pour les attaques magiques
    protected int agilite; // Agilité, affecte l'ordre d'attaque et l'esquive
    protected int resistance; // Résistance aux dégâts
    protected int vitesse; // Vitesse de déplacement
    protected int esquive; // Capacité à esquiver les attaques
    protected int precision; // Précision des attaques
    protected String image; // Chemin de l'image du combattant

    // Constructeur
    public combattant(int pv, int totalPV, int pm, int totalPM, int force, int intelligence,
                      int agilite, int resistance, int vitesse, int esquive, int precision, String image) {
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
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = Math.max(0, Math.min(pv, this.totalPV));
    }

    public int getTotalPV() {
        return totalPV;
    }

    public void setTotalPV(int totalPV) {
        this.totalPV = Math.max(0, totalPV);
    }

    public int getPm() {
        return pm;
    }

    public void setPm(int pm) {
        this.pm = Math.max(0, Math.min(pm, this.totalPM));
    }

    public int getTotalPM() {
        return totalPM;
    }

    public void setTotalPM(int totalPM) {
        this.totalPM = Math.max(0, totalPM);
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = Math.max(0, force);
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = Math.max(0, intelligence);
    }

    public int getAgilite() {
        return agilite;
    }

    public void setAgilite(int agilite) {
        this.agilite = Math.max(0, agilite);
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = Math.max(0, resistance);
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = Math.max(0, vitesse);
    }

    public int getEsquive() {
        return esquive;
    }

    public void setEsquive(int esquive) {
        this.esquive = Math.max(0, esquive);
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = Math.max(0, precision);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // Méthodes abstraites
    public abstract void attaquer(combattant cible);

    public void subirDegats(int degats) {
        this.pv -= degats;
        if (this.pv <= 0) {
            System.out.println(this.getClass().getSimpleName() + " est mort.");
        }
    }
}

