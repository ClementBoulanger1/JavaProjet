package model;

public abstract class Produit {
    private String codeBarre;
    private String idProduit;
    private String nom;

    public Produit(String codeBarre, String idProduit, String nom) {
        this.codeBarre = codeBarre;
        this.idProduit = idProduit;
        this.nom = nom;
    }

    // Getters, setters, equals, hashCode methods

    public String getCodeBarre() {
        return codeBarre;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public String getNom() {
        return nom;
    }
}
