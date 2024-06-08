package model;

import java.io.Serializable;

public class Employe implements Serializable {
    private String nom;
    private String codeAdresse;
    private String matricule;
    private String photoPath; // Ajout du chemin de la photo de profil

    public Employe(String nom, String codeAdresse, String matricule, String photoPath) {
        this.nom = nom;
        this.codeAdresse = codeAdresse;
        this.matricule = matricule;
        this.photoPath = photoPath;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodeAdresse() {
        return codeAdresse;
    }

    public void setCodeAdresse(String codeAdresse) {
        this.codeAdresse = codeAdresse;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Employe employe = (Employe) obj;

        if (!nom.equals(employe.nom)) return false;
        if (!codeAdresse.equals(employe.codeAdresse)) return false;
        return matricule.equals(employe.matricule);
    }

    @Override
    public int hashCode() {
        int result = nom.hashCode();
        result = 31 * result + codeAdresse.hashCode();
        result = 31 * result + matricule.hashCode();
        return result;
    }
}
