package fr.nantes.web.quizz.data;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Films {
    List<Film> liste;

    public Films(){
        liste = new ArrayList<Film>(10);
        int nombre_films = Requetesdatastore.getcountmovies();
        Random r = new Random();
        int valeur_random;
        ArrayList<Integer> liste_random = new ArrayList<Integer>();
        ArrayList<String> liste_id_wiki = new ArrayList<String>();
        Film film_tmp;

        while(liste.size()<10&&nombre_films>9){
            valeur_random = 1 + r.nextInt(nombre_films);
            if(!liste_random.contains(valeur_random)) {
                film_tmp = Requetesdatastore.getfilm(valeur_random);
                if (!liste_id_wiki.contains(film_tmp.getId_wiki())) {
                    liste.add(film_tmp);
                    liste_random.add(valeur_random);
                    liste_id_wiki.add(film_tmp.getId_wiki());
                }
            }
        }
    }

    public void affichage_liste (HttpServletResponse response){
        for(int i = 0; i<liste.size(); ++i){
            try {
                response.getWriter().println("Num Film : " + i);
                response.getWriter().println("Nom du film : " + liste.get(i).getNom());
                response.getWriter().println("pays du film : " + liste.get(i).getPays());
                response.getWriter().println("Realisateur du film :" + liste.get(i).getRealisateur());
                response.getWriter().println("Année du film : " + liste.get(i).getAnnee());
                response.getWriter().println("id_wiki du film : " + liste.get(i).getId_wiki());
                response.getWriter().println("id_wiki_realisateur du film : " + liste.get(i).getId_wiki_realisateur());
                response.getWriter().println("-------------------------------------- ");
                response.getWriter().println("-------------------------------------- ");
            } catch (Exception e) {}
        }
    }

    public Film get(int index){
        return liste.get(index);
    }
}
