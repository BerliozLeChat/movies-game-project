package fr.nantes.web.quizz.data;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Films {
    List<Film> liste;

    public Films(){
        liste = new ArrayList<Film>(10);
        int nombre_films = Sparql.countMovies();
        Random r = new Random();
        int valeur_random;
        ArrayList<String> liste_id_wiki = new ArrayList<String>();
        Film film_tmp;

        while(liste.size()<10){
            valeur_random = 0 + r.nextInt(nombre_films - 1);
            film_tmp = Sparql.getMovie(valeur_random);
            String id_wiki = film_tmp.getId_wiki();
            if(!liste_id_wiki.contains(id_wiki)){
                liste.add(film_tmp);
                liste_id_wiki.add(id_wiki);
            }
        }
    }

    public void affichage_liste (HttpServletResponse response){
        for(int i = 0; i<liste.size(); ++i){
            try {
                response.getWriter().println("Num Film : " + i);
                response.getWriter().println("Nom du film : " + liste.get(i).getNom());
                response.getWriter().println("Longitude du film : " + liste.get(i).getLongitude());
                response.getWriter().println("Latitude du film : " + liste.get(i).getLatitude());
                response.getWriter().println("Realisateur du film :" + liste.get(i).getRealisateur());
                response.getWriter().println("Année du film : " + liste.get(i).getAnnée());
                response.getWriter().println("id_wiki du film : " + liste.get(i).getId_wiki());
                response.getWriter().println("id_wiki_realisateur du film : " + liste.get(i).getId_wiki_realisateur());
                response.getWriter().println("-------------------------------------- ");
                response.getWriter().println("-------------------------------------- ");
            } catch (Exception e) {}
        }
    }
}
