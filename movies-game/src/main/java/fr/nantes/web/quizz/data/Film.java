package fr.nantes.web.quizz.data;

public class Film {

    private String nom;
    private String pays;
    private String realisateur;
    private int année;
    private String id_wiki;
    private String id_wiki_realisateur;


    public Film(String nom, String pays, String realisateur, int année, String id_wiki, String id_wiki_realisateur) {

        this.nom = nom;
        this.pays = pays;
        this.realisateur = realisateur;
        this.année = année;
        this.id_wiki=id_wiki;
        this.id_wiki_realisateur=id_wiki_realisateur;
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

    public String getId_wiki() {
        return id_wiki;
    }

    public void setId_wiki(String id_wiki) {
        this.id_wiki = id_wiki;
    }

    public String getId_wiki_realisateur() {
        return id_wiki_realisateur;
    }

    public void setId_wiki_realisateur(String id_wiki_realisateur) {
        this.id_wiki_realisateur = id_wiki_realisateur;
    }
}
