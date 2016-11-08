package fr.nantes.web.quizz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by francois on 08/11/16.
 */
public class Questionnaire {
    List<Film> liste;

    public Questionnaire(){
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
            if(liste_id_wiki.contains(id_wiki)){
                liste.add(film_tmp);
                liste_id_wiki.add(id_wiki);
            }
        }
    }

    public void system_out_liste (){
        for(int i = 0; i<liste.size(); ++i){
            System.out.println("Num Film : "+i);
            System.out.println("Nom du film : "+liste.get(i).getNom());
            System.out.println("Longitude du film : "+liste.get(i).getLongitude());
            System.out.println("Latitude du film : "+liste.get(i).getLatitude());
            System.out.println("Realisateur du film :"+liste.get(i).getRealisateur());
            System.out.println("Année du film : "+liste.get(i).getAnnée());
            System.out.println("id_wiki du film : "+liste.get(i).getId_wiki());
            System.out.println("id_wiki_realisateur du film : "+liste.get(i).getId_wiki_realisateur());
        }
    }
}
