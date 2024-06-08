package model;

public class Goodies extends Produit {
    private String refJeux;
    private String annee;
    private double prix;

    public Goodies(String codeBarre, String idProduit, String nom, String refJeux, String annee, double prix) {
        super(codeBarre, idProduit, nom);
        this.refJeux = refJeux;
        this.annee = annee;
        this.prix = prix;
    }

    // Getters, setters, equals, hashCode methods

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;

        Goodies goodies = (Goodies) obj;

        if (Double.compare(goodies.prix, prix) != 0) return false;
        if (!refJeux.equals(goodies.refJeux)) return false;
        return annee.equals(goodies.annee);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + refJeux.hashCode();
        result = 31 * result + annee.hashCode();
        long temp = Double.doubleToLongBits(prix);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public String getRefJeux() {
        return refJeux;
    }

    public String getAnnee() {
        return annee;
    }

    public double getPrix() {
        return prix;
    }
}
