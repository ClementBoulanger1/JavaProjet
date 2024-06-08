package model;

public class JeuxVideo extends Produit {
    private String genre;
    private String plateforme;
    private int note;
    private String anneeDistribution;
    private double prix;

    public JeuxVideo(String codeBarre, String idProduit, String nom, String genre, String plateforme, int note, String anneeDistribution, double prix) {
        super(codeBarre, idProduit, nom);
        this.genre = genre;
        this.plateforme = plateforme;
        this.note = note;
        this.anneeDistribution = anneeDistribution;
        this.prix = prix;
    }

    // Getters, setters, equals, hashCode methods

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;

        JeuxVideo jeuxVideo = (JeuxVideo) obj;

        if (note != jeuxVideo.note) return false;
        if (Double.compare(jeuxVideo.prix, prix) != 0) return false;
        if (!genre.equals(jeuxVideo.genre)) return false;
        if (!plateforme.equals(jeuxVideo.plateforme)) return false;
        return anneeDistribution.equals(jeuxVideo.anneeDistribution);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + plateforme.hashCode();
        result = 31 * result + note;
        result = 31 * result + anneeDistribution.hashCode();
        long temp = Double.doubleToLongBits(prix);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public String getGenre() {
        return genre;
    }

    public String getPlateforme() {
        return plateforme;
    }

    public int getNote() {
        return note;
    }

    public String getAnneeDistribution() {
        return anneeDistribution;
    }

    public double getPrix() {
        return prix;
    }
}
