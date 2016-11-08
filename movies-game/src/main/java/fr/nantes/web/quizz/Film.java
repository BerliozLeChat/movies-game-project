package fr.nantes.web.quizz;

/**
 * Created by francois on 08/11/16.
 */
public class Film {

    private String nom;
    private String pays;
    private String realisateur;
    private int année;

    public Film(String nom, String pays, String realisateur, int année) {

        this.nom = nom;
        this.pays = pays;
        this.realisateur = realisateur;
        this.année = année;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public int getAnnée() {
        return année;
    }

    public void setAnnée(int année) {
        this.année = année;
    }


}
